package model.bo;

import model.bean.Problem;
import model.dao.ProblemDAO;

import java.util.ArrayList;

public class ProblemBO {
    ProblemDAO problemDAO = new ProblemDAO();
    public Problem getProblem(int id){
        return problemDAO.getProblem(id);
    }
    public ArrayList<Problem> getAllProblems(){
        return problemDAO.getAllProblems();
    }
}
