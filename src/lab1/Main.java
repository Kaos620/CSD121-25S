package lab1;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void registerStudentGrade() {
        int studentID;
        int numberOfGrades;
        String courseCode;
        float studentAverageGrade = 0;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Student ID");
        studentID = scanner.nextInt();

        System.out.print("Enter Course Code: ");
        courseCode = scanner.next();

        Student student = new Student(studentID, courseCode, studentAverageGrade);

        System.out.print("How many grades do you want to register? ");
        numberOfGrades = scanner.nextInt();

        int count = 0;
        while (count < numberOfGrades) {
            try {
                System.out.print("Enter Grade " + (count + 1) + ": ");
                float grade = scanner.nextFloat();

                if (grade >= 0 && grade <= 10) {
                    student.addGrade(grade);
                    count++;
                }
                else {
                    System.out.println("Grade must be between 0 and 10");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid number format. Please try using ',' instead of '.' ");
                scanner.nextLine();
            }
        }

        for (float item : student.grades) {
            student.studentAverageGrade = item + student.studentAverageGrade;
        }

        student.studentAverageGrade = student.studentAverageGrade /  student.grades.size();
        student.studentAverageGrade = Math.round(student.studentAverageGrade * 100f) / 100f;


        // Needed help from AI to figure out how registering info into Json works
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(student);

        try (FileWriter writer = new FileWriter("student_" + studentID + ".json")) {
            writer.write(json);
            System.out.println("Student data saved to student_" + studentID + ".json");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        registerStudentGrade();
    };


}

