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
  public void insert(User user) throws DataAccessException {
    String sql = "INSERT INTO User (username, password, email, firstName, lastName, " +
            "gender, personID) VALUES(?,?,?,?,?,?,?)";
    try (PreparedStatement statement = conn.prepareStatement(sql)) {
      statement.setString(1,user.getUsername());
      statement.setString(2,user.getPassword());
      statement.setString(3,user.getEmail());
      statement.setString(4,user.getFirstName());
      statement.setString(5,user.getLastName());
      statement.setString(6,user.getGender());
      statement.setString(7,user.getPersonID());
      statement.executeUpdate();
    }
    catch(SQLException e) {
      e.printStackTrace();
      throw new DataAccessException("Error encountered while inserting into the user table");
    }
  }
  /**
   * Takes in a username and returns the matching user from the database
   * @param username the username of the desired user
   * @return the found user
   * @throws DataAccessException if unable to access data
   */
  public User findUser(String username) throws DataAccessException {
    User user;
    ResultSet rs;
    String sql = "SELECT * FROM User WHERE username = ?;";
    try (PreparedStatement statement = conn.prepareStatement(sql)) {
      statement.setString(1,username);
      rs = statement.executeQuery();
      if (rs.next()) {
        user = new User(rs.getString("username"),rs.getString("password"),
                rs.getString("email"),rs.getString("firstName"),
                rs.getString("lastName"),rs.getString("gender"),
                rs.getString("personID"));
        return user;
      }
      else {
        return null;
      }
    }
    catch(SQLException e) {
      e.printStackTrace();
      throw new DataAccessException("Error encountered while finding user");
    }
  }

  /**
   * Clears all entries from the users table
   * @throws DataAccessException if unable to access data
   */
  public void clear() throws DataAccessException {
    String sql = "DELETE FROM User";
    try (PreparedStatement statement = conn.prepareStatement(sql)) {
      statement.executeUpdate();
    }
    catch(SQLException e) {
      e.printStackTrace();
      throw new DataAccessException("Error encountered while clearing the user table");
    }
  }

  /**
   * Gets password based on username to login
   * @param username
   * @return
   */
  public String getPassword(String username) throws DataAccessException {
    String password;

    ResultSet results = null;
    String sql = "SELECT * FROM User WHERE username = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setString(1, username);
      results = stmt.executeQuery();
      password = results.getString("password");
      stmt.close();
      results.close();
      return password;
    }
    catch (SQLException e) {
      e.printStackTrace();
      throw new DataAccessException("Error while looking for password :(");
    }
  }
  public String getPersonID(String username) throws DataAccessException {
    String personID;

    ResultSet results = null;
    String sql = "SELECT * FROM User WHERE username = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setString(1, username);
      results = stmt.executeQuery();
      personID = results.getString("personID");
      stmt.close();
      results.close();
      return personID;
    }
    catch (SQLException e) {
      e.printStackTrace();
      throw new DataAccessException("Error while getting PersonID");
    }
  }
  /**
   * Checks to see if a certain username already exists in the database
   * @param username username to check in the table
   * @return true or false
   */
  public boolean userExists(String username) {
    boolean exists = true;

    ResultSet results = null;
    String query = "SELECT * FROM User WHERE username = ?";
    try (PreparedStatement stmt = conn.prepareStatement(query)) {
      stmt.setString(1, username);
      results = stmt.executeQuery();
      exists = results.next();
      stmt.close();
      results.close();
      return exists;
    }
    catch (SQLException e) {
      e.printStackTrace();
      System.out.println("Whoops, errors bro");
      return false;
    }
  }



}
