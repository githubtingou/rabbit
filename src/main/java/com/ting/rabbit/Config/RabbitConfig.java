package com.ting.rabbit.Config;

import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class RabbitConfig implements RabbitTemplate.ReturnsCallback, RabbitTemplate.ConfirmCallback {

    private final RabbitTemplate rabbitTemplate;

    public RabbitConfig(RabbitTemplate rabbitTemplate) {
        rabbitTemplate.setReturnsCallback(this);
        rabbitTemplate.setConfirmCallback(this);
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        System.out.println("获取消息" + correlationData + "---" + cause);

    }

    @Override
    public void returnedMessage(ReturnedMessage returned) {
        System.out.println("消息被退回" + returned.toString());

    }

    /**
     * 消息发送
     *
     * @param exchange
     * @param key
     * @param msg
     */
    public void sendMsg(String exchange, String key, Object msg) {
        this.rabbitTemplate.convertAndSend(exchange, key, msg);
    }

}
