package model.queue;

import model.bean.Submission;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class SubmissionQueue {
    private static final BlockingQueue<Submission> queue = new LinkedBlockingQueue<>();

    public static void addSubmission(Submission submission) {
        try{
            queue.put(submission);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Failed to add submission to queue: " + e.getMessage());
        }
    }
    public static Submission takeSubmission(){
        try {
            return queue.take();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Failed to take submission from queue: " + e.getMessage());
            return null;
        }
    }
}
