package agentfan.gitlab.io.awstest.products;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
public class ProductService {
    private final ProductRepository repository;
    private final AmazonSQS amazonSQS;
    private final ObjectMapper objectMapper;

    public ProductService(ProductRepository repository,
                          AmazonSQS amazonSQS,
                          ObjectMapper objectMapper) {
        this.repository = repository;
        this.amazonSQS = amazonSQS;
        this.objectMapper = objectMapper;
    }

    public ProductEntity getProductById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    }

    public List<ProductEntity> getAll() {
        return repository.findAll();
    }

    public ProductEntity createProduct(ProductEntity productEntity) {
        productEntity.setId(null);
        return repository.save(productEntity);
    }

    public void delayedCreateProduct(ProductEntity productEntity) {
//        productEntity.setId(null);
        publishEvent(productEntity);
    }

    public void publishEvent(ProductEntity productEntity) {
        try {
            String message = objectMapper.writeValueAsString(productEntity);
            log.info("Sending the message: {}", message);
            SendMessageRequest sendMessageRequest = new SendMessageRequest()
//                    .withQueueUrl("http://localhost:4566/000000000000/sample-queue")
                    .withQueueUrl("http://sqs.us-east-1.localhost.localstack.cloud:4566/000000000000/sample-queue")
                    .withMessageBody(message);
//                    .withMessageGroupId("sample-message");
//                    .withMessageDeduplicationId(UUID.randomUUID().toString());

            amazonSQS.sendMessage(sendMessageRequest);
        } catch (JsonProcessingException e) {
            log.error("Parsing error", e);
        } catch (Exception e) {
            log.error("Exception occurred while pushing event to sqs : {}", e.getMessage(), e);
        }
    }

}
