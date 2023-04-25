package com.ting.rabbit.consumer;

import com.rabbitmq.client.Channel;
import com.ting.rabbit.common.RabbitConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class Consumer {

    /**
     * 消息者
     *
     * @param message
     * @param channel
     * @param deliveryTag
     */
    @RabbitListener(
            bindings = @QueueBinding(
                    key = RabbitConstant.Ting.TING_KEY,
                    exchange = @Exchange(
                            name = RabbitConstant.Ting.TING_EXCHANGE,
                            type = ExchangeTypes.TOPIC
                    ),
                    value = @Queue(
                            name = RabbitConstant.Ting.TING_QUEUE,
                            durable = "true"
                    )
            ))
    public void handleMsg(Message message,
                          Channel channel,
                          @Header(AmqpHeaders.DELIVERY_TAG) Long deliveryTag) {
        try {
            String value = new String(message.getBody());
            System.out.println(value);
            channel.basicAck(deliveryTag, false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
