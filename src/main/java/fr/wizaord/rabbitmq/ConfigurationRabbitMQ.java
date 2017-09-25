package fr.wizaord.rabbitmq;

import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationRabbitMQ {


    private final String[] adminUris = { "http://localhost:15672" };
    private final String[] nodes = { "rabbit@host1", "rabbit@host2" };

    @Bean
    public MessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses("localhost:5672,localhost:5673,localhost:5674,localhost:5675");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setPublisherConfirms(true); // to activate ack
        return connectionFactory;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setConcurrentConsumers(3);
        factory.setMaxConcurrentConsumers(10);
        factory.setMessageConverter(jsonMessageConverter());
        return factory;
    }

//    @Bean
//    public ConnectionFactory queueAffinityCF(
//            @Qualifier("defaultConnectionFactory") ConnectionFactory defaultCF) {
//        return new LocalizedQueueConnectionFactory(defaultCF,
//                StringUtils.commaDelimitedListToStringArray(this.props.getAddresses()),
//                this.adminUris, this.nodes,
//                this.props.getVirtualHost(), this.props.getUsername(), this.props.getPassword(),
//                false, null);
//    }
}
