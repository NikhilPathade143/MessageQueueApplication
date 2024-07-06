package com.test.files;

public class MainClass {
    public static void main(String[] args) {
        MessageQueue queue = new MessageQueue(5);
        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);

        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);

        producerThread.start();
        consumerThread.start();

        try {
            producerThread.join();

            while (!queue.isEmpty()) {
                Thread.sleep(100);
            }
            consumerThread.interrupt();  // Stop the consumer after producer is done
            consumerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Total messages processed successfully: " + consumer.getSuccessCount());
        System.out.println("Total errors encountered: " + consumer.getErrorCount());

    }
}
