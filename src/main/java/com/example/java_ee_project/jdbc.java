package com.example.java_ee_project;

import java.sql.*;

public class jdbc {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
       Class.forName("com.mysql.cj.jdbc.Driver");
       String url="jdbc:mysql://localhost:3306/restaurant";
        String username = "root";
        String password = "hamza";
        Connection connection = DriverManager.getConnection(url, username, password);
        Statement statement=connection.createStatement();
        try (ResultSet resultSet = statement.executeQuery("select * from restaurant.drinks")) {
            while (resultSet.next()) {

                System.out.print(resultSet.getString( "Dname" )+"\t");
                System.out.print("orderd a ");
                System.out.print(resultSet.getString( "Dtype" ));
            }
        }
    }

}
