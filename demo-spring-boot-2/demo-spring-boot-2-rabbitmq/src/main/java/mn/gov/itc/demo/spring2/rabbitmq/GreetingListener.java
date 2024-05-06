package mn.gov.itc.demo.spring2.rabbitmq;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
@Slf4j
public class GreetingListener {
    @RabbitListener(queuesToDeclare = @Queue(name = "DEMO_GREETING", autoDelete = "false", declare = "true", durable = "true"))
    public void greetingsString(String message) {
        log.info("(String) Hello: {}", message);
    }

    @RabbitListener(queuesToDeclare = @Queue(name = "DEMO_GREETING", autoDelete = "false", declare = "true", durable = "true"))
    public void greetingsMessage(Message message) {
        log.info("(Message) Hello: {}, Properties: {}", new String(message.getBody(), StandardCharsets.UTF_8), message.getMessageProperties());
    }


    @RabbitListener(
            queuesToDeclare = @Queue(name = "DEMO_ACK", autoDelete = "false", declare = "true", durable = "true")
            , ackMode = "MANUAL")
    public void ack(Message message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        log.info("(ACK) Hello: {}, Properties: {}", new String(message.getBody(), StandardCharsets.UTF_8), message.getMessageProperties());
        channel.basicAck(tag, true);
    }

    @RabbitListener(queues = "DEMO_ERROR")
    public void demoError(Message message) {
        log.info("(ERROR) Hello: {}, Properties: {}", new String(message.getBody(), StandardCharsets.UTF_8), message.getMessageProperties());
        throw new NullPointerException();
    }
}
