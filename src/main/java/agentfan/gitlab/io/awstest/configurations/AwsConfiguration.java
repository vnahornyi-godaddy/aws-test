package agentfan.gitlab.io.awstest.configurations;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

//@Configuration
@ConfigurationProperties
public class AwsConfiguration {
    @Value("${cloud.aws.region.static}")
    String region;

    @Value("${cloud.aws.credentials.access-key}")
    String accessKey;

    @Value("${cloud.aws.credentials.secret-key}")
    String secretAccessKey;

    @Value("${cloud.aws.queue.url}")
    String sqsUrl;

    @Bean
    @Primary
    public AmazonSQSAsync amazonSQSAsync() {
        return AmazonSQSAsyncClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(sqsUrl, region))
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretAccessKey)))
                .build();
    }

//    @Bean
//    public QueueMessagingTemplate queueMessagingTemplate() {
//        return new QueueMessagingTemplate(amazonSQSAsync());
//    }
//
//    @Bean
//    protected MessageConverter messageConverter(ObjectMapper objectMapper) {
//        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
//        converter.setObjectMapper(objectMapper);
//        converter.setSerializedPayloadClass(String.class);
//        converter.setStrictContentTypeMatch(false);
//        return converter;
//    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
