package model.dao;

import model.bean.Problem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProblemDAO {
    public Problem getProblem(int id){
        Problem problem = null;
        try (Connection conn = DatabaseConnect.getConnection()) {
            String query = "SELECT * FROM problems WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                problem = new Problem();
                problem.setId(rs.getInt("id"));
                problem.setTitle(rs.getString("title"));
                problem.setDescription(rs.getString("description"));
                problem.setInputFormat(rs.getString("input_format"));
                problem.setOutputFormat(rs.getString("output_format"));
                problem.setInputSample(rs.getString("sample_input"));
                problem.setOutputSample(rs.getString("sample_output"));
                problem.setTime_limit(rs.getInt("time_limit"));
                problem.setMemory_limit(rs.getInt("memory_limit"));
                problem.setTestInput(rs.getString("test_input"));
                problem.setTestOutput(rs.getString("test_output"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return problem;
    }
    public ArrayList<Problem> getAllProblems(){
        ArrayList<Problem> problems = new ArrayList<Problem>();
        String query = "SELECT * FROM problems";

        try (Connection conn = DatabaseConnect.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Problem problem = new Problem();
                problem.setId(rs.getInt("id"));
                problem.setTitle(rs.getString("title"));
                problem.setDescription(rs.getString("description"));
                problem.setInputFormat(rs.getString("input_format"));
                problem.setOutputFormat(rs.getString("output_format"));
                problem.setInputSample(rs.getString("sample_input"));
                problem.setOutputSample(rs.getString("sample_output"));
                problem.setTime_limit(rs.getInt("time_limit"));
                problem.setMemory_limit(rs.getInt("memory_limit"));
                problem.setTestInput(rs.getString("test_input"));
                problem.setTestOutput(rs.getString("test_output"));

                problems.add(problem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return problems;
    }
}
