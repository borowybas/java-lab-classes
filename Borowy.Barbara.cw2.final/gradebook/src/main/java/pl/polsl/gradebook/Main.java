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
        
        //check if any arguments were passed to the program, if no, ask for a name
        if(args.length==0){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Your name:");
            controller.setStudentName(scanner.next());
        }
        else{
            controller.setStudentName(args[0]);
        }
        
        //enum type for chosing option from menu
        enum MenuOption{
            addGrade, print, exit, nothing;
            public MenuOption changeOpt(char i){
                switch (i) {
                    case 'a' -> {
                        return MenuOption.addGrade;
                    }
                    case 'p' -> {
                        return MenuOption.print;
                    }
                    case 'e' -> {
                        return MenuOption.exit;
                    }
                    default -> {
                    }
                }
                return MenuOption.nothing;
            }
        }
        
        //asks what the user whant to do
        MenuOption opt = MenuOption.nothing;
        while(opt != MenuOption.exit){
            System.out.println("Choose option: a - add new grade, p - print all grades, e - exit");
            Scanner scanner = new Scanner(System.in);
            opt = opt.changeOpt(scanner.next().charAt(0));
            switch (opt){
                case addGrade -> 
                    controller.addGrade();
                case print -> controller.printStudent();
                default -> {
                }
            }
        }
    }
}
