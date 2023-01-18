package pl.polsl.model;

import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Runs a test for WrongGradeException class
 * @author Barbara Borowy
 * @version 1.0
 */
public class WrongGradeExceptionTest {
    
    private SubjectModel subject;
    
    /**
     * Intialization method of test class
     */
    @BeforeEach
    public void setUp(){
        subject = new SubjectModel("x");
    }
    
    /**
     * Tests throwing Wrong grade exception
     * @param candidate values of grades
     */
    @ParameterizedTest()
    @ValueSource(doubles = { 0, 1, 0.5, -10, 100, 6 })
    public void shouldThrowWrongGradeException(double candidate)throws WrongGradeException{
        Double D = Double.valueOf(candidate);
        float f = D.floatValue();
        
        try{
            subject.addGrade(f);
            fail("An exception should be thrown when the grade is not acceptable");
        } catch (WrongGradeException e) {
            
        }
    }
}
