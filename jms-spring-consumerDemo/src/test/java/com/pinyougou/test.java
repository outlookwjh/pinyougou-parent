package com.pinyougou;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-jms-consumer-queue.xml")
public class test {

    @Test
    public void testP2P() throws IOException, InterruptedException {
        Thread.sleep(1000);
    }


    @Test
    public void topicreceiver() throws  IOException {
        System.in.read();
    }


}
