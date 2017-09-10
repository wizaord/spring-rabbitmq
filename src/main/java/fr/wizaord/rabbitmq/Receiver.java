package fr.wizaord.rabbitmq;


import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.concurrent.CountDownLatch;

@RabbitListener(queues = ApplicationConfiguration.queueName)
public class Receiver {

    private final int instance;

    public Receiver(int instanceId) {
        this.instance = instanceId;
    }

    @RabbitHandler
    public void receive(String in) {
        StopWatch watch = new StopWatch();
        watch.start();

        System.out.println("instance " + this.instance + " [x] Received '" + in + "'");

        watch.stop();
        System.out.println("instance " + this.instance + " [x] Done in " + watch.getTotalTimeSeconds() + "s");
    }

}
