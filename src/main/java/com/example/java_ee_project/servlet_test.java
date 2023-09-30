package com.example.java_ee_project;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class servlet_test extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        RequestDispatcher dispatcher = request.getRequestDispatcher("src/main/webapp/index.jsp");
        dispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");


        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String name = firstname +" "+ lastname ;

        try (Connection conn = jdbc_conn.getConnection()) {
            jdbc_insertion.insertData(conn, name, email);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        request.setAttribute("lastname", lastname);
        request.setAttribute("firstname", firstname);
        request.setAttribute("email", email);
        request.getRequestDispatcher("confirmation.jsp").forward(request, response);
    }

    public void destroy() {
    }
}
