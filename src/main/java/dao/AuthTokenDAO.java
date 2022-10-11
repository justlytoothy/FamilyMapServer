package dao;

import model.AuthToken;

import java.sql.*;

public class AuthTokenDAO {
  /**
   * The database connection
   */
  private final Connection conn;
  /**
   * Initialize database connection
   * @param conn a new connection
   */
  public AuthTokenDAO(Connection conn) {
    this.conn = conn;
  }
  /**
   * Takes in an object of token class and inserts all of the fields into a new row in the table
   * @param token
   * @throws DataAccessException
   */
  public void insert(AuthToken token) throws DataAccessException {}

  /**
   * Takes in a username and returns the matching row from the database
   * @param username
   * @return the found username and token
   * @throws DataAccessException
   */
  public AuthToken find(String username) throws DataAccessException {
    return null;
  }

  /**
   * Clears all entries from the token table
   * @throws DataAccessException
   */
  public void clear() throws DataAccessException {}

}
