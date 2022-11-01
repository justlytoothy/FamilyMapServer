package service;

import dao.DataAccessException;
import dao.Database;
import handler.ClearHandler;
import result.ClearResult;

import javax.xml.crypto.Data;

/**
 * Performs clear operations and returns the results
 */
public class ClearService {
  /**
   * This method deletes all data from the database, including user, authtoken, person, and event data
   * @return a response describing the success or failure of clearing the database
   */
  public ClearResult clear () {
    Database database = new Database();
    ClearResult clearResult;
    try {
      database.clearDatabase();
      database.closeConnection(true);
      clearResult = new ClearResult("Clear succeeded.",true);
      return clearResult;
    }
    catch(DataAccessException e) {
      clearResult = new ClearResult("Error while clearing the database :(",false);
      return clearResult;
    }
  }
}
