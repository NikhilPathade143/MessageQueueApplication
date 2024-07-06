package com.test.files;

public class Producer implements Runnable {
    private MessageQueue queue;

    public Producer(MessageQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            try {
                queue.put("Message " + i);
                System.out.println("Produced: Message " + i);
                Thread.sleep(100);  // Simulate time taken to produce a message
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
