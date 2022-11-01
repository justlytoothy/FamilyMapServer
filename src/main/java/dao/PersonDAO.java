package dao;

import model.Person;
import model.Event;
import model.User;

import java.sql.*;

/**
 * The data access object for the person table
 */
public class PersonDAO {
  /**
   * The database connection
   */
  private final Connection conn;
  /**
   * Initialize database connection
   * @param conn a new connection
   */
  public PersonDAO(Connection conn) {
    this.conn = conn;
  }
  public boolean doesPersonIDExist(String personID) {
    boolean exists = true;

    PreparedStatement stmt = null;
    ResultSet results = null;
    String query = "SELECT * FROM Person WHERE PersonID = ?";
    try {
      stmt = conn.prepareStatement(query);
      stmt.setString(1, personID);
      results = stmt.executeQuery();
      exists = results.next();
      stmt.close();
      results.close();
      return exists;
    }
    catch (SQLException e) {
      e.printStackTrace();
      System.out.println("Dang it we got problems");
      return false;
    }
  }
  /**
   * Takes in an object of person class and inserts all of the fields into a new row in the table
   * @param person the person to be added
   * @throws DataAccessException if unable to access data
   */
  public void insert(Person person) throws DataAccessException {
    String sql = "INSERT INTO Person VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setString(1, person.getPersonID());
      stmt.setString(2, person.getAssociatedUsername());
      stmt.setString(3, person.getFirstName());
      stmt.setString(4, person.getLastName());
      stmt.setString(5, person.getFatherID());
      stmt.setString(6, person.getMotherID());
      stmt.setString(7, person.getSpouseID());
      stmt.setString(8, person.getGender());

      stmt.executeUpdate();
      stmt.close();
    }
    catch(SQLException e) {
      e.printStackTrace();
      System.out.println(person.getFirstName());
      System.out.println(person.getLastName());
      System.out.println(person.getPersonID());
      System.out.println("Next Person");
      throw new DataAccessException("Error encountered while inserting into the person table");
    }
  }

  /**
   * Takes in a person ID and returns the matching row from the database
   * @param personID the ID of the person to be found
   * @return the found person
   * @throws DataAccessException if unable to access data
   */
  public Person find(String personID) throws DataAccessException {
    Person person;
    ResultSet rs;
    String sql = "SELECT * FROM Person WHERE personID = ?;";
    try (PreparedStatement statement = conn.prepareStatement(sql)) {
      statement.setString(1,personID);
      rs = statement.executeQuery();
      if (rs.next()) {
        person = new Person(rs.getString("associatedUsername"),rs.getString("personID"),
                rs.getString("firstName"),rs.getString("lastName"),
                rs.getString("gender"), rs.getString("fatherID"),
                rs.getString("motherID"), rs.getString("spouseID"));
        return person;
      }
      else {
        return null;
      }
    }
    catch(SQLException e) {
      e.printStackTrace();
      throw new DataAccessException("Error encountered while finding person");
    }
  }

  /**
   * Clears all entries from the persons table
   * @throws DataAccessException
   */
  public void clear() throws DataAccessException {
    String sql = "DELETE FROM Person";
    try (PreparedStatement statement = conn.prepareStatement(sql)) {
      statement.executeUpdate();
    }
    catch(SQLException e) {
      e.printStackTrace();
      throw new DataAccessException("Error encountered while clearing the person table");
    }
  }

  /**
   * Finds all the family members of the provided person object
   * @param person the person object on which to find family
   * @return an array holding person objects of the family members or empty array
   * @throws DataAccessException if unable to access data
   */
  public Person[] findFamily(Person person) throws DataAccessException {
    return null;
  }
  /**
   * Finds all the events associated with the provided person object
   * @param person the person object on which to find events
   * @return an array holding event objects or empty array
   * @throws DataAccessException if unable to access data
   */
  public Event[] findEvents(Person person) throws DataAccessException {
    return null;
  }
  public void deleteByUsername(String username) {
    PreparedStatement stmt = null;
    String delete = "DELETE FROM Person WHERE AssociatedUsername = ?";
    try {
      stmt = conn.prepareStatement(delete);
      stmt.setString(1, username);
      stmt.executeUpdate();
      stmt.close();
    }
    catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }


}
