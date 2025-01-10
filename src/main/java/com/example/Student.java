package com.example;

public class Student {
    private String name; // Student's name
    private int age; // Student's age
    private double grade; // Student's grade
    private String studentID; // Student's unique ID

    // Constructors
    public Student(String name, int age, double grade, String studentID) {
        this.name = name; // Set the name of the student
        this.age = (age > 0) ? age : 0; // Check if the age is positive, otherwise set to 0
        this.grade = (grade >= 0.0 && grade <= 100.0) ? grade : 0.0; // Check if the grade is within 0-100 range
        this.studentID = studentID; // Set the student ID
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age > 0) {
            this.age = age;
        }
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        if (grade >= 0.0 && grade <= 100.0) {
            this.grade = grade;
        }
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public void displayInfo() {
        System.out.println("Student ID: " + studentID);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Grade: " + grade);
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", grade=" + grade +
                ", studentID='" + studentID + '\'' +
                '}';
    }
}
