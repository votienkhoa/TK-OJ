package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.*;
import model.bean.Problem;
import model.bean.Submission;
import model.bean.User;
import model.bo.LanguageBO;
import model.bo.ProblemBO;
import model.bo.SubmissionBO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@WebServlet(name = "SubmissionsServlet", value = "/submissions")
public class SubmissionsServlet extends HttpServlet {
    SubmissionBO submissionBO = new SubmissionBO();
    LanguageBO languageBO = new LanguageBO();
    ProblemBO problemBO = new ProblemBO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("/login");
            return;
        }
        User user = (User) session.getAttribute("user");
        String username = user.getUsername();

        List<Submission> submissions = submissionBO.getSubmissionsByUserId(username);
        HashMap<Integer, String> languages = languageBO.getLanguages();
        ArrayList<Problem> problems = problemBO.getAllProblems();
        HashMap<Integer, String> problemMap = new HashMap<>();
        for (Problem problem : problems) {
            problemMap.put(problem.getId(), problem.getTitle());
        }

        request.setAttribute("problems", problemMap);
        request.setAttribute("submissions", submissions);
        request.setAttribute("languages", languages);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/submissions.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}