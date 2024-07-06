package com.test;

import static org.junit.jupiter.api.Assertions.*;

import com.test.files.MessageQueue;
import com.test.files.Producer;
import org.junit.jupiter.api.Test;

public class ProducerTest {

    @Test
    public void testProducer() throws InterruptedException {
        MessageQueue queue = new MessageQueue(10);
        Producer producer = new Producer(queue);

        Thread producerThread = new Thread(producer);
        producerThread.start();
        producerThread.join();

        for (int i = 1; i <= 10; i++) {
            assertEquals("Message " + i, queue.take());
        }
    }
}
