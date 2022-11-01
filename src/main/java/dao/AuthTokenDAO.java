package dao;

import model.AuthToken;
import model.Event;

import java.sql.*;

/**
 * The data access object class for the auth token table
 */
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
   * Takes in an object of token class and inserts all the fields into a new row in the table
   * @param token
   * @throws DataAccessException if unable to access data
   */
  public void insert(AuthToken token) throws DataAccessException {
    String sql = "INSERT INTO AuthToken (authtoken, username) VALUES(?,?)";
    try (PreparedStatement statement = conn.prepareStatement(sql)) {
      statement.setString(1, token.getUsername());
      statement.setString(2, token.getAuthToken());
      statement.executeUpdate();
    }
    catch(SQLException e) {
      e.printStackTrace();
      throw new DataAccessException("Error encountered while inserting into the token table");
    }
  }

  /**
   * Takes in a username and returns the matching row from the database
   * @param username
   * @return the found username and token
   * @throws DataAccessException if unable to access data
   */
  public AuthToken find(String username) throws DataAccessException {
    AuthToken authToken;
    ResultSet rs;
    String sql = "SELECT * FROM AuthToken WHERE username = ?;";
    try (PreparedStatement statement = conn.prepareStatement(sql)) {
      statement.setString(1,username);
      rs = statement.executeQuery();
      if (rs.next()) {
        authToken = new AuthToken(rs.getString("username"),rs.getString("authtoken"));
        return authToken;
      }
      else {
        return null;
      }
    }
    catch(SQLException e) {
      e.printStackTrace();
      throw new DataAccessException("Error encountered while finding token");
    }
  }

  /**
   * Queries table by the auth token and gets the username of same row
   * @param authtoken the auth token of logged-in user
   * @return the username of the user associated with auth token provided
   * @throws DataAccessException if unable to access data
   */
  public String findUsername(String authtoken) throws DataAccessException {
    return null;
  }

  /**
   * Clears all entries from the token table
   * @throws DataAccessException if unable to access data
   */
  public void clear() throws DataAccessException {
    String sql = "DELETE FROM AuthToken";
    try (PreparedStatement statement = conn.prepareStatement(sql)) {
      statement.executeUpdate();
    }
    catch(SQLException e) {
      e.printStackTrace();
      throw new DataAccessException("Error encountered while clearing the tokens table");
    }
  }

}
