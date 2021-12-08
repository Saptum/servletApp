package com.example.demo.session;

import java.sql.*;

public class LoginRepository {
    public static void main(String[] args) {
        getConnection();
    }
    public static Connection getConnection(){
        Connection connection = null;
        String url = "jdbc:postgresql://localhost:5432/employee";
        String user = "postgres";
        String password = "9640";

        try {
            connection = DriverManager.getConnection(url, user, password);
            if (connection != null) {
                System.out.println("Connected to the PostgreSQL server successfully.");
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException);
        }
        return connection;
    }

    public static ResultSet getLoginInfo(){
        ResultSet rs = null;
        try{
            Connection connection = LoginRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT login.Login, login.Password FROM login");
            rs = ps.executeQuery();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return rs;
    }
}
