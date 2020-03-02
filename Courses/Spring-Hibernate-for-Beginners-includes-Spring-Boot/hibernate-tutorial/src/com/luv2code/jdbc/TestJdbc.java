package com.luv2code.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {

    public static void main(String[] args) {

        String jdbcUrl = "jdbc:mysql://localhost:3306/hb_student_tracker?useSSL=false&serverTimezone=UTC";
        String user = "hbstudent";
        String pass = "hbstudent";

        try {

            System.out.println("Connecting to database: " + jdbcUrl);

            Connection connection = DriverManager.getConnection(jdbcUrl, user, pass);

            System.out.println("Connecting successful!!!");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
