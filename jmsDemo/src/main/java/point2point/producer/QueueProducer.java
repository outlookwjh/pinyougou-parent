package point2point.producer;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class QueueProducer {


    public static void main(String[] args) throws JMSException {
        ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://192.168.43.128:61616");
        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue firstQueue = session.createQueue("firstQueue");
        MessageProducer producer = session.createProducer(firstQueue);
        TextMessage message = session.createTextMessage("这是一个点对点的消息测试");
        producer.send(message);
        producer.close();
        session.close();
        connection.close();


    }



}
