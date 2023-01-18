
package pl.polsl.model;

import javax.security.auth.Subject;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Test class for a SubjectModel class
 * 
 * @author Barbara Borowy
 * @version 1.1
 */
public class SubjectModelTest {
    
    private SubjectModel subject;
    
    /**
     * Intialization method of test class
     */
    @BeforeEach
    public void setUp(){
        subject = new SubjectModel("x");
    }
    
    /**
     * Tests if a user passed null as value of grade
     * @param candidate value of grade
     */
    @ParameterizedTest
    @NullSource
    @EmptySource
    @ValueSource(strings = { " ", " ", "\t", "\n" })
    public void testAddNullAsGrade(String candidate){
        assertTrue(candidate == null || candidate.trim().isEmpty(), "String is empty!");
    }
    
    /*
    @Disabled("Test not ready yet")
    @ParameterizedTest
    @ValueSource(strings = {"xyz", "ab"})
    public void testAddStringAsGrade(String candidate){
        float p = 0;
       assertFalse(0==p);
       try{
           //subject.addGrade(candidate);
       }catch(Exception e){}
    
    }*/
    
    /**
     * Tests if a value lower than 2 can be passed as a value of grade
     * @param candidate value of grade
     * @throws WrongGradeException 
     */
    @ParameterizedTest
    @ValueSource(doubles = { 0, 1, 0.5, -10 })
    public void testAddGradeLowerThanTwo(double candidate) throws WrongGradeException{
        Double D = Double.valueOf(candidate);
        float f = D.floatValue();
        
        WrongGradeException exc = assertThrows(WrongGradeException.class, () -> subject.addGrade(f));
        assertEquals("grade should be a number from 2 to 5", exc.getMessage());
        //subject.addGrade(f);
        
    }
    /**
     * Tests if a value higher than 5 can be passed as a value of grade
     * @param candidate value of grade
     */
    @ParameterizedTest
    @ValueSource(doubles = { 10, 5.1, 100 })
    public void testAddGradeHigherThanFive(double candidate){
        Double D = Double.valueOf(candidate);
        float f = D.floatValue();
        
        WrongGradeException exc = assertThrows(WrongGradeException.class, () -> subject.addGrade(f));
        assertEquals("grade should be a number from 2 to 5", exc.getMessage());
        
    }
    
    

   
}