package agentfan.gitlab.io.awstest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

//@ConfigurationPropertiesScan(basePackages = "agentfan.gitlab.io.awstest.configs")
@SpringBootApplication
public class AwsTestApplication {

    public static void main(String[] args) {
        System.out.println("Hello, World!!!");
        SpringApplication.run(AwsTestApplication.class, args);
        System.out.println("Done!!!");
    }

}
