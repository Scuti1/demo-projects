package mn.gov.itc.demo.spring2.rabbitmq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import mn.gov.itc.demo.spring2.rabbitmq.model.DemoNum;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.StandardCharsets;

@SpringBootTest()
@Slf4j
public class AmqpTests {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    RabbitTemplate rabbitTemplate;


    @Test
    public void testGreetings() throws JsonProcessingException {
        Message response = rabbitTemplate.sendAndReceive("amq.direct", "SUM", new Message(objectMapper.writeValueAsBytes(new DemoNum(5, 6))));
        assert response != null;
        log.info("RESP: {}", new String(response.getBody(), StandardCharsets.UTF_8));
    }
}
