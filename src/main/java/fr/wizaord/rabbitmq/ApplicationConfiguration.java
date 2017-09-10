package fr.wizaord.rabbitmq;

import com.sun.org.apache.regexp.internal.RE;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class ApplicationConfiguration {

    final static String queueName = "spring-boot";

    @Bean
    Queue queue() {
        return new Queue(queueName, false);
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

    @Profile({"sender", "all"})
    @Bean
    public Sender sender() {
        return new Sender();
    }
}
