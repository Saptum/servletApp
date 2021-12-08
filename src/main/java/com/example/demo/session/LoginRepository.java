package com.example.demo.session;

import com.example.demo.Employee;
import com.example.demo.EmployeeRepository;

import java.sql.*;

public class LoginRepository {
    public static void main(String[] args) {
        getConnection();
        Employee employee = new Employee();

        employee.setName("Sam");
        employee.setEmail("sam@gmail.com ");
        employee.setCountry("America");
        save(employee);
    }

    public static Connection getConnection() {
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

    public static ResultSet getLoginInfo() {
        ResultSet resultSet = null;
        try {
            Connection connection = LoginRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT login.Login, login.Password FROM login");
            resultSet = ps.executeQuery();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resultSet;
    }

    public static int save(Employee employee) {
        int status = 0;
        try {
            Connection connection = EmployeeRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement("insert into users(name,email,country) values (?,?,?)");
            ps.setString(1, employee.getName());
            ps.setString(2, employee.getEmail());
            ps.setString(3, employee.getCountry());

            status = ps.executeUpdate();
            connection.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return status;
    }
}
