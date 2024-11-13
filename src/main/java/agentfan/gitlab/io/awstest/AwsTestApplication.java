package agentfan.gitlab.io.awstest;

import agentfan.gitlab.io.awstest.configurations.AwsConfiguration;
import io.awspring.cloud.messaging.config.annotation.EnableSqs;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@Slf4j
@EnableSqs
@SpringBootApplication
@EnableConfigurationProperties(AwsConfiguration.class)
public class AwsTestApplication {

    public static void main(String[] args) {
        log.error("ERROR!!!");
        System.out.println("Hello, World!!!");
        SpringApplication.run(AwsTestApplication.class, args);
        System.out.println("Done!!!");
    }

}
