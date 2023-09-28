package com.example.java_ee_project;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class servlet_test extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        // Hello
        request.setAttribute("message", message);

        // Forward the request to hello.html
        RequestDispatcher dispatcher = request.getRequestDispatcher("src/main/webapp/index.jsp");
        dispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        // Get form data
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");

        // Database connection and insertion code
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Replace with your database driver
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/form test", "root", "hamza");
            String sql = "INSERT INTO ident (name, email) VALUES (?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, firstname);
            statement.setString(2, lastname);
            statement.setString(2, email);

            statement.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


        request.setAttribute("lastname", lastname);
        request.setAttribute("firstname", firstname);
        request.setAttribute("email", email);
        request.getRequestDispatcher("confirmation.jsp").forward(request, response);
    }

    public void destroy() {
    }
}
