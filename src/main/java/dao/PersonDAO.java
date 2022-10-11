package dao;

import model.Person;
import model.Event;

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
   * @throws DataAccessException
   */
  public void insert(Person person) throws DataAccessException {}

  /**
   * Takes in a person ID and returns the matching row from the database
   * @param personID the ID of the person to be found
   * @return the found person
   * @throws DataAccessException
   */
  public Person find(String personID) throws DataAccessException {return null;}

  /**
   * Clears all entries from the persons table
   * @throws DataAccessException
   */
  public void clear() throws DataAccessException {}

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
