package com.student.infomation;

public class Student {
    private String studentID;
    private String studentName;
    private String studentEmail;
    private String studentPhone;
    private String program;
    private int batch;
    private String password;
    private String dob;

    public Student() {

    }

    public Student(String studentID, String studentName, String studentEmail, String studentPhone, String program, int batch, String password, String CGPA) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.studentPhone = studentPhone;
        this.program = program;
        this.batch = batch;
        this.password = password;
        this.dob = CGPA;
    }

    public Student(String studentID, String studentName, String studentEmail, String studentPhone, String program, int batch, String dob) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.studentPhone = studentPhone;
        this.program = program;
        this.batch = batch;
        this.dob = dob;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getStudentPhone() {
        return studentPhone;
    }

    public void setStudentPhone(String studentPhone) {
        this.studentPhone = studentPhone;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public int getBatch() {
        return batch;
    }

    public void setBatch(int batch) {
        this.batch = batch;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "Student" + " = {\n" +
                "\tID = '" + studentID + "', \n" +
                "\tName = '" + studentName + "', \n" +
                "\tEmail = '" + studentEmail + "', \n" +
                "\tPhone = '" + studentPhone + "', \n" +
                "\tProgram = '" + program + "', \n" +
                "\tBatch = " + batch + ", \n" +
                "\tPassword = '" + password + "', \n" +
                "\tDate of Birth = '" + dob +
                "'\n}";
    }
}
