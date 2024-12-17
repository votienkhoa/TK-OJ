package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.*;
import model.bean.Language;
import model.bean.Problem;
import model.bean.Submission;
import model.bean.User;
import model.bo.LanguageBO;
import model.bo.ProblemBO;
import model.bo.SubmissionBO;
import model.queue.SubmissionConsumer;
import model.queue.SubmissionQueue;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

@WebServlet(name = "SubmitServlet", value = "/submit")
public class SubmitServlet extends HttpServlet {
    SubmissionBO submissionBO = new SubmissionBO();
    LanguageBO languageBO = new LanguageBO();
    ProblemBO problemBO = new ProblemBO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String problemId = request.getParameter("problemId");
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("/login");
            return;
        }
        if (problemId != null && !problemId.isEmpty()) {
            Problem problem = problemBO.getProblem(Integer.parseInt(problemId));

            if (problem != null) {
                request.setAttribute("problem", problem);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Problem not found");
            }
        } else {
            response.sendRedirect("/problems");
            return;
        }
        HashMap<Integer,String> languages = languageBO.getLanguages();

        String destination = "/WEB-INF/views/submit.jsp";
        request.setAttribute("languages", languages);
        RequestDispatcher rd = request.getRequestDispatcher(destination);
//        try {
//            Thread.sleep(9000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String language = request.getParameter("language");
        String source_code = request.getParameter("sourceCode");
        String problemId = request.getParameter("problemId");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        Submission sub = new Submission();
        sub.setUsername(user.getUsername());
        sub.setLanguage(Integer.parseInt(language));
        sub.setSourceCode(source_code);
        sub.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        sub.setStatus("Pending");
        sub.setProblemId(Integer.parseInt(problemId));

        SubmissionQueue.addSubmission(sub); //add vao queue
        response.sendRedirect("/submissions");
    }
}