package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnect {
    public static Connection getConnection(){
        try{
            String URL = "jdbc:mysql://127.0.0.1:3306/OJ";
            String USER = "root";
            String PASSWORD = "";

            Class.forName("com.mysql.cj.jdbc.Driver");

            return DriverManager.getConnection(URL, USER, PASSWORD);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
