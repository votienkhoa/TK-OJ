package model.dao;

import model.bean.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDAO {
    public User getUser(String username){
        User user = null;
        try (Connection conn = DatabaseConnect.getConnection()){
            String query = "SELECT * FROM users WHERE username = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                user = new User(rs.getString("username"), rs.getString("password"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return user;
    }
}
