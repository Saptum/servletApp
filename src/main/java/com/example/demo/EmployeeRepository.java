package com.example.demo;

import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository {

    public static void main(String[] args) {
        getConnection();

        Employee employee = new Employee();

        employee.setName("Nika");
        employee.setEmail("nika@gmail.com ");
        employee.setCountry("Japan ");
        save(employee);
    }

    public static Connection getConnection() {

        Connection connection = null;
        String url = "jdbc:mysql://localhost:3306/employee";
        String user = "root";
        String password = "9640";

        try {
            connection = DriverManager.getConnection(url, user, password);
            if (connection != null) {
                System.out.println("Connected to the MySQL server successfully.");
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException);
        }
        return connection;
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

    public static int update(Employee employee) {

        int status = 0;

        try {
            Connection connection = EmployeeRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement("update users set name=?,email=?,country=? where id=?");
            ps.setString(1, employee.getName());
            ps.setString(2, employee.getEmail());
            ps.setString(3, employee.getCountry());
            ps.setInt(4, employee.getId());

            status = ps.executeUpdate();
            connection.close();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return status;
    }

    public static int delete(int id) {

        int status = 0;

        try {
            Connection connection = EmployeeRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement("delete from users where id=?");
            ps.setInt(1, id);
            status = ps.executeUpdate();

            connection.close();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return status;
    }

    public static Employee getEmployeeById(int id) {

        Employee employee = new Employee();

        try {
            Connection connection = EmployeeRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from users where id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                employee.setId(rs.getInt(1));
                employee.setName(rs.getString(2));
                employee.setEmail(rs.getString(3));
                employee.setCountry(rs.getString(4));
            }
            connection.close();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return employee;
    }

    public static List<Employee> getAllEmployees() {

        List<Employee> listEmployees = new ArrayList<>();

        try {
            Connection connection = EmployeeRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from users");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Employee employee = new Employee();

                employee.setId(rs.getInt(1));
                employee.setName(rs.getString(2));
                employee.setEmail(rs.getString(3));
                employee.setCountry(rs.getString(4));

                listEmployees.add(employee);
            }
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listEmployees;
    }

    public static String getByCountry(String c) throws SQLException {
        String result = new String();
        Connection connection = new EmployeeRepository().getConnection();
        PreparedStatement ps = connection.prepareStatement("SELECT name from users WHERE country=?");
        ps.setString(1, c);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            result = rs.getString(1);
        }
        connection.close();
        return result;
    }

    public static void deleteOrder(int orderID) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement ps = connection.prepareStatement("DELETE FROM  orders WHERE id=?");
        PreparedStatement ps2 = connection.prepareStatement("DELETE FROM  OrderProducts WHERE orderID=?");
        ps.setInt(1, orderID);
        ps2.setInt(1, orderID);
        ps2.executeUpdate();
        ps.executeUpdate();
        connection.close();
    }

    public static void createOrder(int userID, int productID, int number, String date, String destination) throws SQLException {
        ResultSet rs = null;
        int result = 0;
        Connection connection = getConnection();
        PreparedStatement ps = connection.prepareStatement("INSERT INTO orders(date, destination, userID) " +
                " VALUES (?,?,?)");
        ps.setString(1, date);
        ps.setString(2, destination);
        ps.setInt(3, userID);
        ps.executeUpdate();
        PreparedStatement ps2 = connection.prepareStatement("SELECT MAX(id) FROM orders");
        rs = ps2.executeQuery();
        while (rs.next()){
            result = rs.getInt(1);
        }
        connection.close();
        addProducts(productID, result, number);
    }

    public static ResultSet viewProducts() throws SQLException {
        Connection connection = getConnection();
        ResultSet rs = null ;
        PreparedStatement ps = connection.prepareStatement("SELECT* FROM products");
        rs = ps.executeQuery();
        return rs;
    }

    public static void addProducts(int productID, int orderID,int number) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement ps = connection.prepareStatement("INSERT INTO OrderProducts VALUES (?,?,?)");
        ps.setInt(1, productID);
        ps.setInt(2, orderID);
        ps.setInt(3, number);
        ps.executeUpdate();
        connection.close();
    }

    public static void showProducts(PrintWriter out)throws SQLException {
        ResultSet rs = EmployeeRepository.viewProducts();
        List<Product> productList = new ArrayList<>();
        while (rs.next()){
            productList.add(
                    new Product(rs.getString("name"), rs.getInt("id"), rs.getInt("price"),
                            rs.getString("description"))
            );
        }
        for (Product x:productList
        ) {
            out.println("Name " + x.getName());
            out.println("Id " + x.getId());
            out.println("Price " + x.getPrice());
            out.println("Description " + x.getDescription());
        }
        out.close();
    }
}
