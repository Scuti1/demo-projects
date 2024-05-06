package mn.gov.itc.demo.spring2.rabbitmq;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AmqpApplication {

    /*@Bean
    MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }*/

    public static void main(String[] args) {
        SpringApplication.run(AmqpApplication.class, args);
    }

}
