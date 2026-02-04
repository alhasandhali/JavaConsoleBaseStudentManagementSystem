package com.student.infomation;

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Main {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("1. Add Student");
        System.out.println("2. Edit Student");
        System.out.println("3. Add courses");
        System.out.println("4. Search Student");
        System.out.println("5. Search All Students");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
        int option = sc.nextInt();
        System.out.println();

        switch (option) {
            case 1:
                addStudent();
                break;
            case 2:
                updateStudent();
                break;
            case 3:
                addCourses();
                break;
            case 4:
                searchStudent();
                break;
            case 5:
                getAllStudent();
                break;
            case 6:
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }
    }

    public static void insertData(Student student) {
        try {
            Connection connection = SingletonConnection.getConnection();
            Statement  statement = connection.createStatement();

            String tableCreateQuery = "CREATE TABLE IF NOT EXISTS students (id INT AUTO_INCREMENT PRIMARY KEY, sID VARCHAR(100) NOT NULL, name VARCHAR(100) NOT NULL, email VARCHAR(150) UNIQUE NOT NULL, password VARCHAR(255) NOT NULL, phone VARCHAR(20) NOT NULL, program VARCHAR(50) NOT NULL, batch INT NOT NULL, dob VARCHAR(50) NOT NULL);";
            statement.executeUpdate(tableCreateQuery);

            String query = "INSERT INTO students (sID, name, email, password, phone, program, batch, dob) VALUES('" + student.getStudentID() + "', '" + student.getStudentName() + "', '" + student.getStudentEmail() + "', '" + student.getPassword() + "', '" + student.getStudentPhone() + "', '" + student.getProgram() + "', " + student.getBatch() + ", '" + student.getDob() + "');";
            statement.execute(query);
            System.out.println("Student inserted successfully");

        } catch (SQLException ex) {
            System.out.println("Unable to insert student due to " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static void insertData(String studentId, String courseCode) {
        try {
            Connection connection = SingletonConnection.getConnection();
            Statement  statement = connection.createStatement();

            String tableCreateQuery = "CREATE TABLE IF NOT EXISTS courses (id INT AUTO_INCREMENT PRIMARY KEY, sID VARCHAR(100) NOT NULL, courses VARCHAR(100) NOT NULL);";
            statement.executeUpdate(tableCreateQuery);

            String query = "INSERT INTO courses (sID, courses) VALUES('" + studentId + "', '" + courseCode + "');";
            statement.execute(query);
            System.out.println("Course inserted successfully");

        } catch (SQLException ex) {
            System.out.println("Unable to insert course due to " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static List<Student> searchAllStudent() {
        List<Student> studentList = new ArrayList<>();
        try {
            Connection connection = SingletonConnection.getConnection();
            Statement  statement = connection.createStatement();

            String query = "SELECT * FROM students;";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String studentId = resultSet.getString("sID");
                String studentName = resultSet.getString("name");
                String studentEmail = resultSet.getString("email");
                String studentPhone = resultSet.getString("phone");
                String studentProgram = resultSet.getString("program");
                int studentBatch = parseInt(resultSet.getString("batch"));
                String studentDob = resultSet.getString("dob");

                Student student = new Student(studentId, studentName, studentEmail, studentPhone, studentProgram, studentBatch, studentDob);

                studentList.add(student);
            }

        } catch (SQLException ex) {
            System.out.println("Unable to search student due to " + ex.getMessage());
            ex.printStackTrace();
        }
        return studentList;
    }

    public static Student searchSingleStudent(String searchId) {
            Student student =  null;
        try {
            Connection connection = SingletonConnection.getConnection();
            Statement  statement = connection.createStatement();

            String query = "SELECT * FROM students WHERE sID = " + searchId + ";";
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                String studentId = resultSet.getString("sID");
                String studentName = resultSet.getString("name");
                String studentEmail = resultSet.getString("email");
                String studentPhone = resultSet.getString("phone");
                String studentProgram = resultSet.getString("program");
                int studentBatch = parseInt(resultSet.getString("batch"));
                String studentDob = resultSet.getString("dob");

                student = new Student(studentId, studentName, studentEmail, studentPhone, studentProgram, studentBatch, studentDob);

            } else {
                System.out.println("Student not found");
            }

        } catch (SQLException ex) {
            System.out.println("Unable to search student due to " + ex.getMessage());
            ex.printStackTrace();
        }
        return student;
    }

    public static void addStudent() {
        sc.nextLine();

        System.out.print("Enter Student ID: ");
        String id = sc.nextLine();

        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Student Email: ");
        String email = sc.nextLine();

        System.out.print("Enter Student Phone Number: ");
        String phone = sc.nextLine();

        System.out.print("Enter Student Program: ");
        String program = sc.nextLine();

        System.out.print("Enter Student Batch: ");
        int batch = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Student password: ");
        String password = sc.nextLine();

        System.out.print("Enter Student Date of Birth: ");
        String dob = sc.nextLine();

        Student student = new Student(id, name, email, phone, program, batch, password, dob);

        insertData(student);

        System.out.println("\n--- Student Added Successfully ---");
        System.out.println(student.toString());
    }

    public static void searchStudent() {
        sc.nextLine();
        System.out.print("Enter Student ID to search: ");
        String searchId = sc.nextLine();

        if (searchSingleStudent(searchId) != null){
            Student student = searchSingleStudent(searchId);
            System.out.println(student);
        } else {
            System.out.println("Student not found");
        }
    }

    public static void getAllStudent() {
        List<Student> studentList = searchAllStudent();

        if (studentList != null){
            for (Student student : studentList) {
                System.out.println(student.toString());
            }
        } else {
            System.out.println("No student found");
        }
    }

    public static void addCourses() {
        sc.nextLine();
        System.out.print("Enter Student ID: ");
        String studentId = sc.nextLine();

        if (searchSingleStudent(studentId) == null) {
            System.out.println("No student found with ID: " + studentId);
        } else {
            System.out.print("\nEnter Course Code: ");
            String courseCode = sc.nextLine();

            insertData(studentId, courseCode);
        }
    }

    public static void updateStudent() {
        sc.nextLine();
        System.out.print("Enter Student ID: ");
        String studentId = sc.nextLine();

        if (searchSingleStudent(studentId) != null){
            Student student = searchSingleStudent(studentId);
            System.out.println(student);

            System.out.println("Choose a option which is want to change: ");
            System.out.println("1. Name");
            System.out.println("2. Email");
            System.out.println("3. Phone Number");
            System.out.println("4. Program");
            System.out.println("5. Batch");
            System.out.println("6. Date of Birth");
            System.out.print("Enter your choice: ");
            int option = sc.nextInt();
            sc.nextLine();
            System.out.println();

            try {
                Connection connection = SingletonConnection.getConnection();
                Statement statement = connection.createStatement();

                if (option == 1) {
                    System.out.println("Currently Student Name: " + student.getStudentName());
                    System.out.print("Enter Student Name: ");
                    String name = sc.nextLine();

                    String query = "UPDATE students SET name = '"+ name +"' WHERE sID = '" + student.getStudentID() +"';";
                    statement.executeUpdate(query);
                    System.out.println("Student Name Updated Successfully");

                } else if (option == 2) {
                    System.out.println("Currently Student Email: " + student.getStudentEmail());
                    System.out.print("Enter Student Email: ");
                    String email = sc.nextLine();

                    String query = "UPDATE students SET email = '"+ email +"' WHERE sID = '" + student.getStudentID() +"';";
                    statement.executeUpdate(query);
                    System.out.println("Student Email Updated Successfully");

                } else if (option == 3) {
                    System.out.println("Currently Student Phone Number: " + student.getStudentPhone());
                    System.out.print("Enter Student Phone Number: ");
                    String phone = sc.nextLine();

                    String query = "UPDATE students SET phone = '"+ phone +"' WHERE sID = '" + student.getStudentID() +"';";
                    statement.executeUpdate(query);
                    System.out.println("Student Phone number Updated Successfully");

                } else if (option == 4) {
                    System.out.println("Currently Student Program: " + student.getProgram());
                    System.out.print("Enter Student Program: ");
                    String program = sc.nextLine();

                    String query = "UPDATE students SET program = '"+ program +"' WHERE sID = '" + student.getStudentID() +"';";
                    statement.executeUpdate(query);
                    System.out.println("Student Program Updated Successfully");

                } else if (option == 5) {
                    System.out.println("Currently Student Batch: " + student.getBatch());
                    System.out.print("Enter Student Batch: ");
                    int batch = sc.nextInt();

                    String query = "UPDATE students SET batch = '"+ batch +"' WHERE sID = '" + student.getStudentID() +"';";
                    statement.executeUpdate(query);
                    System.out.println("Student Batch Updated Successfully");

                } else if (option == 6) {
                    System.out.println("Currently Student Date of Birth: " + student.getDob());
                    System.out.print("Enter Student Date of Birth: ");
                    String dob = sc.nextLine();

                    String query = "UPDATE students SET dob = '"+ dob +"' WHERE sID = '" + student.getStudentID() +"';";
                    statement.executeUpdate(query);
                    System.out.println("Student Date of Birth Updated Successfully");

                } else {
                    System.out.println("Invalid Input");
                }

            } catch (SQLException ex){
                System.out.println("Unable to update student due to " + ex.getMessage());
            }

        } else {
            System.out.println("Student not found");
        }
    }
}