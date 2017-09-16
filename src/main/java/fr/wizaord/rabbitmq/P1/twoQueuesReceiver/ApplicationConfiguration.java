package fr.wizaord.rabbitmq.P1.twoQueuesReceiver;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("prop-2queues")
@Configuration
public class ApplicationConfiguration {

    final static String queueName = "spring-boot";

    @Bean
    Queue queue() {
        return new Queue(queueName, false);
    }

    @Profile({"sender", "all"})
    @Bean
    public Sender sender() {
        return new Sender();
    }

    @Profile({"receiver", "all"})
    private static class ReceiverConfig {

        @Bean
        public Receiver receiver1() {
            return new Receiver(1);
        }

        @Bean
        public Receiver receiver2() {
            return new Receiver(2);
        }
    }
}
