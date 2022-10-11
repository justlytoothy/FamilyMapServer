package dao;
import model.Event;
import model.User;

import java.sql.Connection;

/**
 * The data access object for the user table
 */
public class UserDAO {
  private final Connection conn;


  public UserDAO (Connection conn) {
    this.conn = conn;
  }

  /**
   * Takes in an object of user class and inserts all of the fields into a new row in the table
   * @param user the new user to be added
   * @throws DataAccessException
   */
  public void insert(User user) throws DataAccessException {}

  /**
   * Takes in a username and returns the matching row from the database
   * @param username the username of the desired user
   * @return the found user
   * @throws DataAccessException
   */
  public User find(String username) throws DataAccessException {return null;}

  /**
   * Clears all entries from the users table
   * @throws DataAccessException
   */
  public void clear() throws DataAccessException {}



}
