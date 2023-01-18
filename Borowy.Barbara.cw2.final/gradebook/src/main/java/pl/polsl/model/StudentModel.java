package pl.polsl.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A container for a data of student
 * 
 * @author Barbara Borowy
 * @version 1.1
 */
public class StudentModel {
    /**
     * Name of the student
     */
    private String studentName;
    /**
     * List of subjects assigned to this student
     */
    private List<SubjectModel> studentSubjects;
    /**
     * Constructor with no params
     */
    public StudentModel() {
        this.studentName = "";
        this.studentSubjects = new ArrayList<>();
    }
    /**
     * Gets name of this student
     * @return name of student
     */
    public String getStudentName() {
        return studentName;
    }
    /**
     * Sets name of this student
     * @param studentName new name to be assigned
     */
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    /**
     * Gets list of subjects assigned to this student
     * @return 
     */
    public List<SubjectModel> getStudentSubjects() {
        return studentSubjects;
    }
    /**
     * Sets new list of subjects to this student
     * @param studentSubjects 
     */
    public void setStudentSubjects(List<SubjectModel> studentSubjects) {
        this.studentSubjects = studentSubjects;
    }
    /**
     * Method thad adds a new subject to the list
     * @param subject 
     */
    public void addSubject(SubjectModel subject){
        studentSubjects.add(subject);
    }
}
