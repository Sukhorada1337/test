package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StudentManagerGUI {
    private StudentManagerImp1 manager;

    // Constructor to set up the GUI and initialize the StudentManager
    public StudentManagerGUI() {
        manager = new StudentManagerImp1(); // Create an instance of the StudentManager
        JFrame frame = new JFrame("Student Manager"); // Create a new JFrame for the application

        // Set up the layout for the frame (using FlowLayout)
        frame.setLayout(new FlowLayout());
        frame.setSize(400, 400); // Set the size of the window

        // Create input fields for the student data
        JTextField nameField = new JTextField(20);
        JTextField ageField = new JTextField(3);
        JTextField gradeField = new JTextField(5);
        JTextField idField = new JTextField(10);

        // Create buttons for various actions
        JButton addButton = new JButton("Add Student");
        JButton removeButton = new JButton("Remove Student");
        JButton updateButton = new JButton("Update Student");
        JButton showButton = new JButton("Show All Students");
        JButton averageButton = new JButton("Calculate Average Grade");

        // ActionListener for adding a student
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String ageText = ageField.getText();
                String gradeText = gradeField.getText();
                String id = idField.getText();

                // Check if any field is empty
                if (name.isEmpty() || ageText.isEmpty() || gradeText.isEmpty() || id.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "All fields must be filled!", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Validate the age and grade inputs
                int age;
                double grade;
                try {
                    age = Integer.parseInt(ageText);
                    grade = Double.parseDouble(gradeText);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Age and grade must be valid numbers.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Validate that the student ID is not empty
                if (id.length() == 0) {
                    JOptionPane.showMessageDialog(frame, "Student ID cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Create a new student object and add it to the manager
                Student student = new Student(name, age, grade, id);
                manager.addStudent(student);
                JOptionPane.showMessageDialog(frame, "Student added: " + name);
            }
        });

        // ActionListener for removing a student
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studentID = idField.getText();

                // Check if the student ID is empty
                if (studentID.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Student ID cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    manager.removeStudent(studentID); // Remove student by ID
                    JOptionPane.showMessageDialog(frame, "Student removed: " + studentID);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Error removing student: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // ActionListener for updating student information
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studentID = idField.getText();
                String newName = nameField.getText();
                String newAgeText = ageField.getText();
                String newGradeText = gradeField.getText();

                // Check if any field is empty
                if (studentID.isEmpty() || newName.isEmpty() || newAgeText.isEmpty() || newGradeText.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "All fields must be filled!", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Validate the new age and grade inputs
                int newAge;
                double newGrade;
                try {
                    newAge = Integer.parseInt(newAgeText);
                    newGrade = Double.parseDouble(newGradeText);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Age and grade must be valid numbers.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    manager.updateStudent(studentID, newName, newAge, newGrade); // Update student information
                    JOptionPane.showMessageDialog(frame, "Student updated: " + studentID);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Error updating student: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // ActionListener for displaying all students
        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ArrayList<Student> students = manager.displayAllStudents(); // Get all students
                    StringBuilder displayText = new StringBuilder();
                    for (Student student : students) {
                        displayText.append(student).append("\n"); // Append each student's details to the display text
                    }
                    JOptionPane.showMessageDialog(frame, displayText.toString()); // Show students in a message dialog
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Error displaying students: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // ActionListener for calculating the average grade
        averageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double averageGrade = manager.calculateAverageGrade(); // Get average grade from the manager
                    JOptionPane.showMessageDialog(frame, "Average Grade: " + averageGrade); // Display average grade
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Error calculating average grade: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Add the GUI components to the frame
        frame.add(new JLabel("Name:"));
        frame.add(nameField);
        frame.add(new JLabel("Age:"));
        frame.add(ageField);
        frame.add(new JLabel("Grade:"));
        frame.add(gradeField);
        frame.add(new JLabel("ID:"));
        frame.add(idField);

        // Add buttons for the user to interact with
        frame.add(addButton);
        frame.add(removeButton);
        frame.add(updateButton);
        frame.add(showButton);
        frame.add(averageButton);

        // Set default behavior for closing the window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true); // Make the window visible
    }
}
