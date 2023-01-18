package pl.polsl.gradebook;

import pl.polsl.controller.StudentController;
import java.util.Scanner;
import pl.polsl.model.StudentModel;
import pl.polsl.view.StudentView;

/**
 * Class allowing to submit and print the grades of a student in the console output
 *
 * @author Barbara Borowy
 * @version 1.1
 */
public class Main {
    
    /**
     * Main method of the program.
     * 
     * @param args name of a student
     */    
    public static void main(String[] args) {
        //Model, View and Controller of our programm
        StudentModel model = new StudentModel();
        StudentView view = new StudentView();
        StudentController controller = new StudentController(model, view);
        
        //check if any arguments were passes to the program, if no, ask for a name
        if(args.length==0){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Your name:");
            controller.setStudentName(scanner.next());
        }
        else{
            controller.setStudentName(args[0]);
        }
        
        //asks what the user whant to do
        char option = 'x';
        
        while(option != 'e'){
            System.out.println("Choose option: a - add new grade, p - print all grades, e - exit");
            Scanner scanner = new Scanner(System.in);
            option = scanner.next().charAt(0);
            
            switch (option){
                case 'a' -> 
                    controller.addGrade();
                case 'p' -> controller.printStudent();
                default -> {
                }
            }
        }
    }
}
