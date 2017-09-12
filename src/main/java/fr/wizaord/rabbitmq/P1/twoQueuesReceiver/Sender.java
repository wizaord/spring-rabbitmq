package fr.wizaord.rabbitmq.P1.twoQueuesReceiver;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

public class Sender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Queue queue;

    @Scheduled(fixedDelay = 100, initialDelay = 500)
    public void send() throws Exception {
        String message = "Hello World!";
        this.rabbitTemplate.convertAndSend(queue.getName(), message);
        System.out.println(" [x] sent '" + message + "'");
    }

}
