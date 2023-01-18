package pl.polsl.view;

import java.util.List;
import java.util.Scanner;
import pl.polsl.model.StudentModel;

/**
 * Works as a user interface. Prints data to console
 * @author Barbara Borowy
 * @version 1.1
 */
public class StudentView {
    /**
     * Non-parameter constructor
     */
    public StudentView(){
    }
    /**
     * Prints informtion of student
     * @param student 
     */
    public void printStudentGrades(StudentModel student){
      System.out.println("Student: ");
      System.out.println("Name: " + student.getStudentName());
   }
    /**
     * Prints name of subject and grades assigned to it
     * @param subjectName
     * @param grades 
     */
    public void printGrades(String subjectName, List<Float> grades){
        System.out.println("Subject: "+ subjectName);
        for(Float grade: grades){
            System.out.println(grade);
        }
    }
    /**
     * Asks user to enter name of subject to which they want to add new grade
     * @return name of subject as String
     */
    public String getSubject(){
        System.out.println("Enter the subject name");
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }
    /**
     * Ask user to enter a grade
     * @return grafe as Float
     */
    public float getGrade(){
        System.out.println("Enter the grade");
        Scanner scanner = new Scanner(System.in);
        return Float.parseFloat(scanner.next());
    }
}
