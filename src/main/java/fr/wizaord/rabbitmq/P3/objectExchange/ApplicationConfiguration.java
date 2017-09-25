package fr.wizaord.rabbitmq.P3.objectExchange;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("P3")
@Configuration
public class ApplicationConfiguration {

    @Bean
    public FanoutExchange fanout() {
        return new FanoutExchange("P3.inputFanout");
    }

    @Bean
    public Queue receiveQueue() {
        return new Queue("P3.receiveQueue", true);
    }

    @Bean
    public Binding binding1(FanoutExchange fanout,
                            Queue receiveQueue) {
        return BindingBuilder.bind(receiveQueue).to(fanout);
    }


    @Profile("receiver")
    @Bean
    public ObjectReceiver receiver() {
        return new ObjectReceiver();
    }

    @Profile("sender")
    @Bean
    public Sender sender() {
        return new Sender();
    }
}