package dao;

import model.Person;

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


}
