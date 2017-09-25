package fr.wizaord.rabbitmq.P3.objectExchange;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.ZonedDateTime;
import java.util.UUID;

public class Sender {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private FanoutExchange fanout;

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send() {
        SampleObj so = new SampleObj();
        so.setId(UUID.randomUUID().getMostSignificantBits());
        so.setMessage("ho yeah " + ZonedDateTime.now().toString());
        so.addSub("plipplop");

        template.setConfirmCallback((correlationData, ack, cause) -> {
            System.out.println("receive ack => " + ack);
        });
        template.convertAndSend(fanout.getName(), "", so);
        System.out.println(" [x] Sent message");
    }
}
