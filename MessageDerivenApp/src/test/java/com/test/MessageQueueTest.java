package com.test;
import static org.junit.jupiter.api.Assertions.*;

import com.test.files.MessageQueue;
import org.junit.jupiter.api.Test;
public class MessageQueueTest {
    @Test
    public void testPutAndTake() throws InterruptedException {
        MessageQueue queue = new MessageQueue(2);
        queue.put("Test Message 1");
        queue.put("Test Message 2");

        assertEquals("Test Message 1", queue.take());
        assertEquals("Test Message 2", queue.take());
    }

    @Test
    public void testBlockingPut() throws InterruptedException {
        MessageQueue queue = new MessageQueue(1);
        queue.put("Test Message 1");

        Thread producerThread = new Thread(() -> {
            try {
                queue.put("Test Message 2");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producerThread.start();
        Thread.sleep(100);  // Give some time for the producer to block

        assertEquals("Test Message 1", queue.take());
        producerThread.join(1000);  // Wait for the producer to finish

        assertEquals("Test Message 2", queue.take());
    }

    @Test
    public void testBlockingTake() throws InterruptedException {
        MessageQueue queue = new MessageQueue(1);

        Thread consumerThread = new Thread(() -> {
            try {
                assertEquals("Test Message 1", queue.take());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        consumerThread.start();
        Thread.sleep(100);  // Give some time for the consumer to block

        queue.put("Test Message 1");
        consumerThread.join(1000);  // Wait for the consumer to finish
    }
}
