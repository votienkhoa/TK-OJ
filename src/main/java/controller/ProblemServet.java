package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.*;
import model.bean.Problem;
import model.bo.ProblemBO;
import model.dao.ProblemDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ProblemServet", value = "/problems/*")
public class ProblemServet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        if (pathInfo != null && pathInfo.length() > 1) {
            String problemId = pathInfo.substring(1); // Loại bỏ dấu '/'

            ProblemBO problemBO = new ProblemBO();
            Problem problem = problemBO.getProblem(Integer.parseInt(problemId));

            if (problem != null) {
                request.setAttribute("problem", problem);
                request.getRequestDispatcher("/WEB-INF/views/problem.jsp").forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Problem not found");
            }
        } else {
            ProblemBO problemBO = new ProblemBO();
            ArrayList<Problem> problems = problemBO.getAllProblems();

            if (problems != null && !problems.isEmpty()) {
                request.setAttribute("problems", problems);
                request.getRequestDispatcher("/WEB-INF/views/problems.jsp").forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "No problems found");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}