package com.example.java_ee_project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class servlet_test extends HttpServlet {

    // JDBC URL, username, and password


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Retrieve username from request
        String username = request.getParameter("nom");

        try (Connection conn = jdbc_conn.getConnection()) {
            // Check if username is provided
            if (username != null && !username.isEmpty()) {
                // Prepare SQL statement to retrieve winnings for the provided username
                String sql = "SELECT winnings FROM lottery_winnings WHERE username = ?";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, username);

                    // Execute query
                    try (ResultSet rs = stmt.executeQuery()) {
                        if (rs.next()) {
                            // Retrieve winnings from the result set
                            double winnings = rs.getDouble("winnings");

                            // Display greetings with winnings
                            out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">\n");
                            out.println("<HTML>\n" + "<HEAD><TITLE>Greetings Servlet</TITLE></HEAD>\n"
                                    + "<BODY BGCOLOR=\"#FDF5E6\">\n" + "<H1>Greetings " + username.toUpperCase()
                                    + "!</H1>\n");
                            out.println("<p>Votre solde actuel est de: " + winnings + " dollars!</p>");
                            out.println("</BODY></HTML>");
                        } else {
                            out.println("Aucun gain trouvé pour l'utilisateur: " + username);
                        }
                    }
                }
            } else {
                out.println("Aucun nom d'utilisateur fourni.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            out.println("Erreur de base de données: " + e.getMessage());
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
