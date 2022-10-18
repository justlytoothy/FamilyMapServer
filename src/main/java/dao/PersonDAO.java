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
  /**
   * Takes in an object of person class and inserts all of the fields into a new row in the table
   * @param person the person to be added
   * @throws DataAccessException if unable to access data
   */
  public void insert(Person person) throws DataAccessException {
    String sql = "INSERT INTO Person (personID, associatedUsername, firstName, lastName, fatherID, " +
            "motherID, spouseID, gender) VALUES(?,?,?,?,?,?,?,?)";
    try (PreparedStatement statement = conn.prepareStatement(sql)) {
      statement.setString(1,person.getPersonID());
      statement.setString(2,person.getAssociatedUsername());
      statement.setString(3,person.getFirstName());
      statement.setString(4,person.getLastName());
      statement.setString(5,person.getFatherID());
      statement.setString(6,person.getMotherID());
      statement.setString(7,person.getSpouseID());
      statement.setString(8,person.getGender());

      statement.executeUpdate();
    }
    catch(SQLException e) {
      e.printStackTrace();
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


}
