package com.student.infomation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnection {
    private static final String DB_HOST = EnvLoader.get("DB_HOST");
    private static final String DB_NAME = EnvLoader.get("DB_NAME");
    private static final String DB_USERNAME = EnvLoader.get("DB_USERNAME");
    private static final String DB_PASSWORD = EnvLoader.get("DB_PASSWORD");
    private static final String DB_URL = "jdbc:mysql://" + DB_HOST + "/" + DB_NAME;
    private static Connection connection;
    private static SingletonConnection instance = new SingletonConnection();
    private SingletonConnection() {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            System.out.println("Connected to the database successfully");
        } catch (SQLException ex) {
            System.out.println("Unable to connect to the database due to " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
