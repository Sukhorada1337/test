package com.example;

import java.sql.*;
import java.util.ArrayList;

public class StudentManagerImp1 implements StudentManager {
    private Connection connection;

    // Constructor to establish connection to the SQLite database
    public StudentManagerImp1() {
        try {
            // Establishing connection to the SQLite database file 'students.db'
            connection = DriverManager.getConnection("jdbc:sqlite:students.db");
            System.out.println("Connected to the database.");
        } catch (SQLException e) {
            // If there is an error in connecting, print the error message
            System.out.println("Error connecting to the database: " + e.getMessage());
        }
    }

    // Method to add a new student to the database
    @Override
    public void addStudent(Student student) {
        // SQL query to insert a new student into the 'students' table
        String query = "INSERT INTO students (name, age, grade, studentID) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            // Set values for each placeholder in the query using student object properties
            stmt.setString(1, student.getName()); // Set name
            stmt.setInt(2, student.getAge()); // Set age
            stmt.setDouble(3, student.getGrade()); // Set grade
            stmt.setString(4, student.getStudentID()); // Set studentID
            // Execute the update query to add the student to the database
            stmt.executeUpdate();
            // Inform the user that the student was successfully added
            System.out.println("Student added: " + student.getName());
        } catch (SQLException e) {
            // If an error occurs while adding the student, print the error message
            System.out.println("Error adding student: " + e.getMessage());
        }
    }

    // Method to remove a student from the database based on their student ID
    @Override
    public void removeStudent(String studentID) {
        // SQL query to delete a student from the database where the studentID matches
        String query = "DELETE FROM students WHERE studentID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            // Set the studentID in the query to match the student to be deleted
            stmt.setString(1, studentID);
            // Execute the query and get the number of affected rows
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                // If rows were affected, the student was successfully removed
                System.out.println("Student removed: " + studentID);
            } else {
                // If no rows were affected, the student was not found
                System.out.println("No student found with ID: " + studentID);
            }
        } catch (SQLException e) {
            // If an error occurs while removing the student, print the error message
            System.out.println("Error removing student: " + e.getMessage());
        }
    }

    // Method to calculate the average grade of all students in the database
    @Override
    public double calculateAverageGrade() {
        // SQL query to select the average grade of all students from the 'students' table
        String query = "SELECT AVG(grade) FROM students";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            // If the query executes successfully, get the average grade from the result set
            if (rs.next()) {
                return rs.getDouble(1); // Return the average grade
            }
        } catch (SQLException e) {
            // If an error occurs while calculating the average grade, print the error message
            System.out.println("Error calculating average grade: " + e.getMessage());
        }
        // Return 0 if there's an error or no grades in the database
        return 0.0;
    }

    // Method to display all students from the database
    @Override
    public ArrayList<Student> displayAllStudents() {
        // Create a list to store all the students
        ArrayList<Student> students = new ArrayList<>();
        // SQL query to select all students from the 'students' table
        String query = "SELECT * FROM students";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            // Loop through the result set and add each student to the list
            while (rs.next()) {
                String name = rs.getString("name"); // Get the student's name
                int age = rs.getInt("age"); // Get the student's age
                double grade = rs.getDouble("grade"); // Get the student's grade
                String studentID = rs.getString("studentID"); // Get the student's ID
                // Create a new Student object and add it to the list
                students.add(new Student(name, age, grade, studentID));
            }
        } catch (SQLException e) {
            // If an error occurs while displaying the students, print the error message
            System.out.println("Error displaying students: " + e.getMessage());
        }
        // Return the list of students
        return students;
    }

    // Method to update the details of an existing student in the database
    @Override
    public void updateStudent(String studentID, String newName, int newAge, double newGrade) {
        // SQL query to update the student's name, age, and grade based on the studentID
        String query = "UPDATE students SET name = ?, age = ?, grade = ? WHERE studentID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            // Set the new values for name, age, grade, and studentID
            stmt.setString(1, newName); // Set new name
            stmt.setInt(2, newAge); // Set new age
            stmt.setDouble(3, newGrade); // Set new grade
            stmt.setString(4, studentID); // Set studentID to identify the student to update

            // Execute the update query and get the number of affected rows
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                // If rows were affected, the student was successfully updated
                System.out.println("Student updated: " + studentID);
            } else {
                // If no rows were affected, no student was found with the given studentID
                System.out.println("No student found with ID: " + studentID);
            }
        } catch (SQLException e) {
            // If an error occurs while updating the student, print the error message
            System.out.println("Error updating student: " + e.getMessage());
        }
    }
}
