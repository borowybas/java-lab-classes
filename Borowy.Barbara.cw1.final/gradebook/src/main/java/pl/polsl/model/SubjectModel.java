package pl.polsl.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A container for data of a subject
 *
 * @author Barbara Borowy
 * @version 1.1
 */
public class SubjectModel {
    /**
     * Name of the subject
     */
    private String subjectName;
    /**
     * List of grades
     */
    private List<Float> grades;
    
    /**
     * Constructor with 1 parameter
     * @param subjectName name of the subject
     */
    public SubjectModel(String subjectName) {
        this.subjectName = subjectName;
        this.grades = new ArrayList<>();
    }
    /**
     * Gets the list of grades assigned to the subject
     * @return grades
     */
    public List<Float> getGrades() {
        return grades;
    }
    /**
     * Sets a new list of grades to the existing object
     * @param grades 
     */
    public void setGrades(List<Float> grades) {
        this.grades = grades;
    }
    /**
     * Gets the name of the subject
     * @return 
     */
    public String getSubjectName() {
        return subjectName;
    }
    /**
     * Sets a new name to the existing object
     * @param subject_name 
     */
    public void setSubjectName(String subject_name) {
        this.subjectName = subject_name;
    }
    /**
     * Adds a new grade to the list of grades
     * @param grade grade to be added
     * @throws WrongGradeException when grade is not from 2 to 5
     */
    public void addGrade(float grade) throws WrongGradeException{
        
        if (grade >5 || grade <2)throw new WrongGradeException("grade should be a number from 2 to 5");
        else
            this.grades.add(grade);
    }
}
