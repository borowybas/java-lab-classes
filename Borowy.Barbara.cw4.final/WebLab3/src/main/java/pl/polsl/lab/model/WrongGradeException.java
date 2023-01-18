package pl.polsl.lab.model;

/**
 * Class which throws an exception when user enters grade not from 2 to 5
 * @author Barbara Borowy
 * @version 1.1
 */
public class WrongGradeException extends Exception{
    /**
     * Non-parameter constructor
     */
    public WrongGradeException() {
    }
    /**
     * Exception class constructor
     *
     * @param message display message
     */
    public WrongGradeException(String message) {
        super(message);
    }
}
