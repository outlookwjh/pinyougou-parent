package com.pinyougou;

import com.pinyougou.demo.QueueProducer;
import com.pinyougou.demo.TopicProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-jms-producer.xml")
public class test {

    @Autowired
    QueueProducer queueProducer;

    @Autowired
    TopicProducer topicProducer;
    /**
     * 测试点对点，发送方
     */
    @Test
    public void testP2P()
    {
        queueProducer.sendTextMessage("spring-jms点对点测试");
    }

    @Test
    public void testTopick()
    {
        topicProducer.sendMessage("spring-jms topic 测试");
    }
}
