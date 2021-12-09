package com.example.demo.session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        List<String> loginID = new ArrayList<>();
        loginID.add("Vlad");
        loginID.add("Oleg");
        loginID.add("Max");
        loginID.add("Sam");
        List<String> passwordID = new ArrayList<>();
        passwordID.add("1252543");
        passwordID.add("2352354");
        passwordID.add("224342525");
        passwordID.add("5345");
        Map<String, String> logins = new HashMap<>();
        for (String value : loginID) {
            logins.put(value, login);
        }
        Map<String, String> passwords = new HashMap<>();
        for (String s : passwordID) {
            passwords.put(s, password);
        }

        for (Map.Entry<String, String> map1 : logins.entrySet()) {
            for (Map.Entry<String, String> map2 : passwords.entrySet()) {
                if (map1.getKey().equals(login) && map2.getKey().equals(password)) {
                    HttpSession session = request.getSession();
                    session.setAttribute("login", "login");
                    //setting session to expiry in 30 mins
                    session.setMaxInactiveInterval(30 * 60);
                    Cookie userName = new Cookie("login", login);
                    userName.setMaxAge(30 * 60);
                    response.addCookie(userName);
                    PrintWriter out = response.getWriter();
                    out.println("Welcome back to the team, " + login + "!");

                }
                else {
                    PrintWriter out = response.getWriter();
                    out.println("Either login name or passwordID is wrong!");
                }

            }
        }
    }
}
