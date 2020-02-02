package com.thesis.texasholdemapp.database;

import java.sql.*;

public class ReportConnection {
    private final String DB_URL = "jdbc:mysql://localhost:3307/poker_equity_app";
    private final String USERNAME = "root";
    private final String PASSWORD = "root";

    private Connection connection;

    public ReportConnection() {
    }

    public Connection getConnection() {
        try {
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void close(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
        try {
            if (connection != null) connection.close();
            if (preparedStatement != null) preparedStatement.close();
            if (resultSet != null) resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close(Connection connection, PreparedStatement preparedStatement) {
        try {
            close(connection, preparedStatement, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close(PreparedStatement preparedStatement) {
        try {
            close(null, preparedStatement, null);
        } catch (Exception e) {
            e.printStackTrace();
       }
    }
}

