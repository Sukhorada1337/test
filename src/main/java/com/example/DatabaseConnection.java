package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

public class DatabaseConnection {

    // Method to establish a connection to the SQLite database and create the 'students' table if it does not exist
    public static Connection connect() {
        try {
            // Define the URL of the SQLite database
            String url = "jdbc:sqlite:students.db"; // Path to the database file 'students.db'
            // Establish the connection to the SQLite database
            Connection conn = DriverManager.getConnection(url);
            // If the connection is successful, print a confirmation message
            System.out.println("Connection to SQLite has been established.");

            // SQL query to create the 'students' table if it doesn't already exist
            String createTableSQL = "CREATE TABLE IF NOT EXISTS students (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," + // 'id' column will auto-increment with each new entry
                    "name TEXT NOT NULL," + // 'name' column, which cannot be NULL
                    "age INTEGER NOT NULL," + // 'age' column, which cannot be NULL
                    "grade REAL NOT NULL," + // 'grade' column, which cannot be NULL
                    "studentID TEXT NOT NULL UNIQUE);"; // 'studentID' column, which is unique for each student

            // Try to execute the SQL query to create the table
            try (Statement stmt = conn.createStatement()) {
                // Execute the create table SQL statement
                stmt.execute(createTableSQL);
            } catch (SQLException e) {
                // If there is an error while creating the table, print the error message
                System.out.println("Error creating table: " + e.getMessage());
            }

            // Return the established connection
            return conn;
        } catch (SQLException e) {
            // If there is an error connecting to the database, print the error message
            System.out.println("Connection to SQLite failed: " + e.getMessage());
            // Return null if connection failed
            return null;
        }
    }
}
