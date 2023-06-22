package com.project.uaspbo;

import java.sql.*;

// Class utility ini berguna untuk membangun koneksi ke databse
public class DBConnection {
    private String databaseName = "uas-pbo-db";
    private String databaseUser = "root";
    private String databasePassword = "komangkomang";
    private Connection connection;

    // Method constructor untuk melakukan koneksi ke database
    public DBConnection() {
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost/" + databaseName,
                    databaseUser,
                    databasePassword
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Mengembalikan data koneksi databse
    public Connection getConnection() {
        return this.connection;
    }

    // Method ini mengembalikan hasil eksekusi ResultSet sesuai dengan query yang diberikan
    public ResultSet getResultSetFromQuery(String query) throws SQLException {
        Statement statement = this.connection.createStatement();
        return statement.executeQuery(query);
    }

    // Menutup koneksi database
    public void closeConnection() {
        try {
            this.connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
