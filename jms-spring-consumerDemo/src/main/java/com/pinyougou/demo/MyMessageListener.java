package com.pinyougou.demo;

import org.springframework.jms.annotation.JmsListener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class MyMessageListener implements MessageListener {
    @Override
    public void onMessage(Message message) {

        TextMessage msg= (TextMessage) message;

        try {
            System.out.println(msg.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }


    }
}
