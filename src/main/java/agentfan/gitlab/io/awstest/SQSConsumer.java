package agentfan.gitlab.io.awstest;

import io.awspring.cloud.messaging.listener.annotation.SqsListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SQSConsumer {
    @SqsListener("${cloud.aws.queue.name}")
    public void receiveMessage(String message) {
        try {
            // Process the message
            log.info("Received message: " + message);
        } catch (Exception e) {
            // Log the error and handle it gracefully
            log.error("SQS error receiving message", e);
        }
    }
}
