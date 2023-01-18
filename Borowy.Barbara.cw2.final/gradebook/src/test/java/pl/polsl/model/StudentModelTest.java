package pl.polsl.model;

import javax.security.auth.Subject;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.platform.commons.util.StringUtils;

/**
 * Test class for a StudentModel class
 * 
 * @author Barbara Borowy
 * @version 1.1
 */
public class StudentModelTest {
    
    private StudentModel student;
    private SubjectModel subject ;
    
    /**
     * Intialization method of test class
     */
    @BeforeEach
    public void setUp(){
        student = new StudentModel();
        subject = new SubjectModel("subName");
    }
    
    //@Disabled("Test not ready yet")
    @ParameterizedTest
    @ValueSource(strings = {"Matematyka", "ab"})
    public void testAddSubject(String candidate){
        assertTrue(StringUtils.isNotBlank(candidate), "String is empty!");
    }
    
    //@Disabled("Test not ready yet")
    @ParameterizedTest
    @NullSource
    @EmptySource
    @ValueSource(strings = { " ", " ", "\t", "\n" })
    public void testAddNullAsSubject(String candidate){
        assertTrue(candidate == null || candidate.trim().isEmpty(), "String is empty!");
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"Name1", "Name 2"})
    public void testAddSubject2(String candidate){
        subject.setSubjectName(candidate);
        student.addSubject(subject);
        
        assertTrue(student.getStudentSubjects().get(0).getSubjectName().equals(subject.getSubjectName()));
    }
    
   /* @ParameterizedTest
    @NullSource
    @EmptySource
    @ValueSource(strings = { " ", " ", "\t", "\n" })
    void nullEmptyAndBlankStrings(String text) {
        assertTrue(text == null || text.trim().isEmpty());
    }*/
}
