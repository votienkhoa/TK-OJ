package model.dao;

import model.bean.Problem;
import model.bean.Submission;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubmissionDAO {
    private static final String API_KEY = "57864d02f2mshd430afe70b66696p1c3b26jsnaf534ded1efc";
    private static final String API_HOST = "judge0-ce.p.rapidapi.com";
    public String createSubmission(Submission submission) {
        ProblemDAO problemDAO = new ProblemDAO();
        int problemId = submission.getProblemId();
        Problem problem = problemDAO.getProblem(problemId);
        //gia lap thoi gian xu ly
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        String API_URL = "https://judge0-ce.p.rapidapi.com/submissions?base64_encoded=false&wait=false";
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("language_id", submission.getLanguage());
        jsonBody.put("source_code", submission.getSourceCode());
        jsonBody.put("stdin", problem.getTestInput());
        jsonBody.put("expected_output", problem.getTestOutput());
        jsonBody.put("cpu_time_limit", problem.getTime_limit());
        jsonBody.put("memory_limit", problem.getMemory_limit());

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .header("X-RapidAPI-Host", API_HOST)
                .header("X-RapidAPI-Key", API_KEY)
                .header("Content-Type", "application/json")
                .method("POST",HttpRequest.BodyPublishers.ofString(jsonBody.toString()))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            JSONObject resp = new JSONObject(response.body());

            return resp.getString("token");
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    public JSONObject getSubmission(String token){
        String API_URL = "https://judge0-ce.p.rapidapi.com/submissions/" + token + "?base64_encoded=true&fields=token,status";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .header("X-RapidAPI-Host", API_HOST)
                .header("X-RapidAPI-Key", API_KEY)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            return new JSONObject(response.body());
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    public void updateSubmission(int id, String token) {
        int status_id;
        JSONObject status;
        JSONObject submission;

        String query = "UPDATE submissions SET token = ?, status = ? WHERE id = ?";

        try (Connection conn = DatabaseConnect.getConnection()) {
            // Lap lai den khi ac, wa, tle
            do {
                try {
                    Thread.sleep(4000); //4 giay cap nhat status 1 lan
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                submission = getSubmission(token);
                status = submission.getJSONObject("status");
                status_id = status.getInt("id");

                try (PreparedStatement stmt = conn.prepareStatement(query)) {
                    stmt.setString(1, token);
                    stmt.setString(2, status.getString("description"));
                    stmt.setInt(3, id);
                    stmt.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }



            } while (status_id < 3);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void saveSubmission(Submission submission) {
        String sql = "INSERT INTO submissions (username, language, source_code, status, created_at, problem_id) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnect.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, submission.getUsername());
            ps.setInt(2, submission.getLanguage());
            ps.setString(3, submission.getSourceCode());
            ps.setString(4, submission.getStatus());
            ps.setTimestamp(5, submission.getCreatedAt());
            ps.setInt(6,submission.getProblemId());


            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int generatedId = generatedKeys.getInt(1);
                        submission.setId(generatedId);
                    }
                }
                System.out.println("Submission " + submission.getId() +  " saved successfully.");
            }
        } catch (SQLException e) {
            System.err.println("Error while saving submission: " + e.getMessage());
        }
    }


    public List<Submission> getSubmissionsByUserId(String username) {
        List<Submission> submissions = new ArrayList<>();
        String query = "SELECT id, language, status, created_at, problem_id FROM submissions WHERE username = ?";

        try (Connection conn = DatabaseConnect.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Submission submission = new Submission();
                submission.setId(resultSet.getInt("id"));
                submission.setLanguage(resultSet.getInt("language"));
                submission.setStatus(resultSet.getString("status"));
                submission.setCreatedAt(resultSet.getTimestamp("created_at"));
                submission.setProblemId(resultSet.getInt("problem_id"));

                submissions.add(submission);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return submissions;
    }
}
