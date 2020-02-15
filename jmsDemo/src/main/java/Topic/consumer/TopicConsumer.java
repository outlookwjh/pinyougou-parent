package Topic.consumer;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

public class TopicConsumer {

    public static void main(String[] args) throws JMSException, IOException {

        ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://192.168.43.128:61616");

        Connection connection = factory.createConnection();

        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Topic firstTopic = session.createTopic("firstTopic");

        MessageConsumer consumer = session.createConsumer(firstTopic);

        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {

                TextMessage msg = (TextMessage) message;

                try {
                    System.out.println(msg.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }


            }
        });

        System.in.read();

        consumer.close();

        session.close();

        connection.close();


    }


}
