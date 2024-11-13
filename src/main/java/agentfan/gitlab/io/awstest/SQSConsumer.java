package agentfan.gitlab.io.awstest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SQSConsumer {
    @SqsListener("sample-queue")
//    @SqsListener("${cloud.aws.queue.name}")
    public void receiveMessage(String message) {
        log.info("SQS Message Received : {}", message);
    }
}
