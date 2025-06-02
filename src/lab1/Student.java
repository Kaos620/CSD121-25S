package lab1;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Student { //Class with the instances
    int studentID;
    String courseCode;
    List<Float> grades;
    float studentAverageGrade;

    public Student(int studentID, String courseCode, float studentAverageGrade) { //Constructor to initialize the Student
        this.studentID = studentID;
        this.courseCode = courseCode;
        this.grades = new ArrayList<>();
        this.studentAverageGrade = studentAverageGrade;
    }

    public void addGrade(float grade) { //Method to add the grades, chose to add the average grade at the main part
        grades.add(grade);
    }
}

