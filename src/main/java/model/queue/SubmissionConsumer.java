package model.queue;

import model.bean.Submission;
import model.bo.SubmissionBO;

public class SubmissionConsumer implements Runnable {
    SubmissionBO submissionBO = new SubmissionBO();
    @Override
    public void run() {
        while (true) {
            Submission submission = SubmissionQueue.takeSubmission();
            if (submission != null) processSubmission(submission);
        }
    }
    private void processSubmission(Submission submission) {
        submissionBO.saveSubmission(submission);

        System.out.println(Thread.currentThread().getName());
        submissionBO.submit(submission);
    }
}
