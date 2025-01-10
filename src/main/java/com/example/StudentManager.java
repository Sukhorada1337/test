package com.example;

import java.util.ArrayList;

public interface StudentManager {
    void addStudent(Student student); // func for adding new student
    void removeStudent(String studentID); // func for remove student
    void updateStudent(String studentID, String newName, int newAge, double newGrade); // upgrading
    ArrayList<Student> displayAllStudents(); // show all students
    double calculateAverageGrade(); // calculate average grade
}
