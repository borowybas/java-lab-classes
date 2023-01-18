package pl.polsl.controller;

import pl.polsl.model.StudentModel;
import pl.polsl.model.SubjectModel;
import pl.polsl.model.WrongGradeException;
import pl.polsl.view.StudentView;

/**
 * Class, that provides interaction between model and view
 * @author Barbara Borowy
 * @version 1.1
 */
public class StudentController {
    /**
     * Provides structure of data
     */
    private StudentModel model;
    /**
     * Provides interaction with user
     */
    private StudentView view;
    /**
     * Constructor with 2 parameters
     * @param model
     * @param view 
     */
    public StudentController(StudentModel model, StudentView view){
        this.model = model;
        this.view = view;
    }
    /**
     * Gets name of actual student
     * @return 
     */
    public String getStudentName(){
        return model.getStudentName();
    }
    /**
     * Sets a name to a student
     * @param name 
     */
    public void setStudentName(String name){
        model.setStudentName(name);
    }
    /**
     * prints all details of a student, including name, all subjects and grades assigned to student 
     */
    public void printStudent(){
        view.printStudentGrades(model);
        for(SubjectModel subject: model.getStudentSubjects()){
            view.printGrades(subject.getSubjectName(), subject.getGrades());
        }
    }
    /**
     * Adds a new subject to the lis of subjects in model
     * @param subjectName 
     */
    public void addSubject(String subjectName){
        model.addSubject(new SubjectModel(subjectName));
    }
    /**
     * Method that is responsible of adding a new grade. Firstly, it takes data from user, thenchecks if this subject 
     * already exist, if no add one. Then it adds grade
     */
    public void addGrade(){
        String subjectName= view.getSubject();
        float grade = view.getGrade();
        boolean flag = false;
        for(SubjectModel subject: model.getStudentSubjects()){
            if (subject.getSubjectName().equals(subjectName)){
                //dodaj ocene
                flag = true;
                try{
                    subject.addGrade(grade);}
                catch(WrongGradeException e){
                    System.err.println(e.getMessage());
                }
            }
        }
        if(flag == false){
            model.addSubject(new SubjectModel(subjectName));
            for(SubjectModel subject: model.getStudentSubjects()){
               if (subject.getSubjectName().equals(subjectName)){
                //dodaj ocene
                try{
                     subject.addGrade(grade);
                }
                catch(WrongGradeException e){
                        System.err.println(e.getMessage());
                }
                }
            }
        }
        
    }
}
