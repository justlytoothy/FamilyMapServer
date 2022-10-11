package dao;
import model.Event;
import model.User;
import model.Person;

import java.sql.*;

/**
 * The data access object for the user table
 */
public class UserDAO {
  /**
   * The database connection
   */
  private final Connection conn;
  /**
   * Initialize database connection
   * @param conn a new connection
   */
  public UserDAO(Connection conn) {
    this.conn = conn;
  }

  /**
   * Takes in an object of user class and inserts all of the fields into a new row in the table
   * @param user the new user to be added
   * @throws DataAccessException if unable to access data
   */
  public void insert(User user) throws DataAccessException {}
  /**
   * Takes in a username and returns the matching user from the database
   * @param username the username of the desired user
   * @return the found user
   * @throws DataAccessException if unable to access data
   */
  public User findUser(String username) throws DataAccessException {return null;}

  /**
   * Clears all entries from the users table
   * @throws DataAccessException if unable to access data
   */
  public void clear() throws DataAccessException {}

  /**
   * Finds the person associated with the given user
   * @param user
   * @return the found person object
   * @throws DataAccessException if unable to access data
   */
  public Person getPerson(User user) throws DataAccessException {return null;}

  /**
   * Finds all the events associated with the provided user object
   * @param user the user object on which to find events
   * @return an array holding event objects or empty array
   * @throws DataAccessException if unable to access data
   */
  public Event[] findEvents(User user) throws DataAccessException {
    return null;
  }



}
