/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.lab.model;

/**
 *
 * @author Admin
 */
public class StudentSingleton {
    private static StudentSingleton instance = null;
    private static StudentModel studentModel;

    private StudentSingleton() {
        if(instance != null) {
            throw new RuntimeException("Not allowed. Please use getInstance() method");
        }
    }

    public static StudentSingleton getInstance() {

        if(instance == null) {
            instance = new StudentSingleton();
            studentModel = new StudentModel();
        }

        return instance;
    }
    
    public StudentModel getStudentModel(){
        return this.studentModel;
    }
}
