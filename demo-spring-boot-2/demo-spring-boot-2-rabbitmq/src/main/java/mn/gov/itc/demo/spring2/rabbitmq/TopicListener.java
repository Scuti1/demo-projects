package mn.gov.itc.demo.spring2.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TopicListener {

    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(name = "topicReceiver1", autoDelete = "true"),
                    exchange = @Exchange(name = "amq.topic", type = "topic"),
                    key = "DEMO_TOPIC"
            )
    )
    public void topicReceiver1(String message) {
        log.info("Topic R1: {}", message);
    }

    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(name = "topicReceiver2", autoDelete = "true"),
                    exchange = @Exchange(name = "amq.topic", type = "topic"),
                    key = "DEMO_TOPIC"
            )
    )
    public void topicReceiver2(String message) {
        log.info("Topic R2: {}", message);
    }
}
