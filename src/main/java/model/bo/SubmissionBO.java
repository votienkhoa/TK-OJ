package model.bo;

import model.bean.Submission;
import model.dao.SubmissionDAO;

import java.util.List;

public class SubmissionBO {
    SubmissionDAO submissionDAO = new SubmissionDAO();
    public void submit(Submission submission){
        String token = submissionDAO.createSubmission(submission); //submit va lay token ve
        submissionDAO.updateSubmission(submission.getId(), token);
    }
    public List<Submission> getSubmissionsByUserId(String username) {
        return submissionDAO.getSubmissionsByUserId(username);
    }
    public void saveSubmission(Submission submission) {
        submissionDAO.saveSubmission(submission);
    }
}
