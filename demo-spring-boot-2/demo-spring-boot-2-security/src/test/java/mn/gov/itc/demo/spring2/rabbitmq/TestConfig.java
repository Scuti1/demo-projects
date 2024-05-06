package mn.gov.itc.demo.spring2.rabbitmq;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class TestConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder().build();
    }
}
