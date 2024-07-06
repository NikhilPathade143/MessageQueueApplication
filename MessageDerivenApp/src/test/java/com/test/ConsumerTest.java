package com.test;

import static org.junit.jupiter.api.Assertions.*;

import com.test.files.Consumer;
import com.test.files.MessageQueue;
import org.junit.jupiter.api.Test;

public class ConsumerTest {

    @Test
    public void testConsumerSuccess() throws InterruptedException {
        MessageQueue queue = new MessageQueue(10);
        Consumer consumer = new Consumer(queue);

        for (int i = 1; i <= 10; i++) {
            queue.put("Message " + i);
        }

        Thread consumerThread = new Thread(consumer);
        consumerThread.start();
        Thread.sleep(1500);  // Give some time to consume all messages
        consumerThread.interrupt();
        consumerThread.join();

        assertEquals(10, consumer.getSuccessCount() + consumer.getErrorCount());
        assertTrue(consumer.getSuccessCount() > 0);
    }

    @Test
    public void testConsumerFailure() throws InterruptedException {
        MessageQueue queue = new MessageQueue(10);
        Consumer consumer = new Consumer(queue);

        for (int i = 1; i <= 10; i++) {
            queue.put("Message " + i);
        }

        Thread consumerThread = new Thread(consumer);
        consumerThread.start();
        Thread.sleep(1500);  // Give some time to consume all messages
        consumerThread.interrupt();
        consumerThread.join();

        assertTrue(consumer.getErrorCount() > 0);
    }
}
