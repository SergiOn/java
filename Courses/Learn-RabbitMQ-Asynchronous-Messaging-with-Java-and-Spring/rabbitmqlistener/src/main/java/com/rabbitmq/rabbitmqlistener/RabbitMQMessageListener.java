package com.rabbitmq.rabbitmqlistener;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class RabbitMQMessageListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        System.out.println("message = [" + new String(message.getBody()) + "]");
    }

}
