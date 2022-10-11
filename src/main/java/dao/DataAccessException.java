package dao;

/**
 * Exception thrown when unable to access data in the database
 */
public class DataAccessException extends Exception {
    DataAccessException(String message) {
        super(message);
    }
}
