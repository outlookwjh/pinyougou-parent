package Topic.producer;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class TopicProducer {

    public static void main(String[] args) throws JMSException {


        ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://192.168.43.128:61616");

        Connection connection = factory.createConnection();

        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Topic firstTopic = session.createTopic("firstTopic");

        MessageProducer producer = session.createProducer(firstTopic);

        TextMessage message = session.createTextMessage("这是一个topic 消息");

        producer.send(message);

        producer.close();

        session.close();

        connection.close();



    }




}
