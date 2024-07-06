package com.test.files;

public class Consumer implements Runnable {
    private MessageQueue queue;
    private int successCount = 0;
    private int errorCount = 0;

    public Consumer(MessageQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                String message = queue.take();
                processMessage(message);
                successCount++;
            } catch (InterruptedException e) {
               // Thread.currentThread().interrupt();
                break;
            } catch (Exception e) {
                errorCount++;
                System.err.println("Failed to process message: " + e.getMessage());
            }
        }
    }

    private void processMessage(String message) throws Exception {
        if (Math.random() > 0.8) {  // Simulate random failures
            throw new Exception("Processing error");
        }
        System.out.println("Consumed: " + message);
    }

    public int getSuccessCount() {
        return successCount;
    }

    public int getErrorCount() {
        return errorCount;
    }
}
