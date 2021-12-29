package com.example.demo;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/deleteOrderServlet")
public class DeleteOrderServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        int orderId = Integer.parseInt(request.getParameter("orderID"));
        if (orderId != 0) {

            try {
                EmployeeRepository.deleteOrder(orderId);
                out.println("Order was deleted!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
