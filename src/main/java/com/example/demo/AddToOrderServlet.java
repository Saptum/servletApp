package com.example.demo;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/addToOrderServlet")
public class AddToOrderServlet  extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        int orderID = Integer.parseInt(request.getParameter("orderID"));
        int productID = Integer.parseInt(request.getParameter("productID"));
        int number = Integer.parseInt(request.getParameter("number"));

        if (orderID != 0 && productID != 0 && number!=0){
            try {
                EmployeeRepository.addProducts(productID, orderID, number);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            out.println("Order was added successfully!");
        }else{
            out.println("Something went wrong!");
        }

    }
}
