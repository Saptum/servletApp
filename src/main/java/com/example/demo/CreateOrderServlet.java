package com.example.demo;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/createOrderServlet")
public class CreateOrderServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        String date = request.getParameter("date");
        String destination = request.getParameter("destination");
        int productID = Integer.parseInt(request.getParameter("productID"));
        int userID = Integer.parseInt(request.getParameter("userID"));
        int number = Integer.parseInt(request.getParameter("number"));

        if (productID != 0 && userID != 0 && number!=0){
            try {
                EmployeeRepository.createOrder(userID, productID, number, date, destination);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            out.println("Order was created successfully!");
        }else{
            out.println("Something went wrong!");
        }
    }
}
