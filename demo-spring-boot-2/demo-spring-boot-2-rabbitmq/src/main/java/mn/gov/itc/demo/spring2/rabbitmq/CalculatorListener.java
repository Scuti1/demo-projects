package mn.gov.itc.demo.spring2.rabbitmq;

import mn.gov.itc.demo.spring2.rabbitmq.model.DemoNum;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class CalculatorListener {

    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(name = "DEMO_SUM", autoDelete = "true"),
                    exchange = @Exchange(name = "amq.direct"),
                    key = "SUM"
            )
    )
    public int sum(DemoNum demoNum) {
        return demoNum.getA() + demoNum.getB();
    }
}
