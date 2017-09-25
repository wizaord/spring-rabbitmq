package fr.wizaord.rabbitmq.P3.objectExchange;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class ObjectReceiver {

    @RabbitListener(queues = "#{receiveQueue.name}", containerFactory="rabbitListenerContainerFactory")
    public void receive(SampleObj in) throws InterruptedException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(in);
        System.out.println("receive =>" + jsonInString);
        System.out.println(in.getSubSampleObjList().get(0).getMessage());

    }
}
