package service;

import dao.AuthTokenDAO;
import dao.DataAccessException;
import dao.Database;
import dao.UserDAO;
import model.AuthToken;
import request.LoginRequest;
import result.LoginResult;

import javax.xml.crypto.Data;

/**
 * Performs login operations from requests and returns the results
 */
public class LoginService {
  /**
   * This method logs in a user and generates an authorization token for them
   * @param request the request sent at login, containing the username and password
   * @return the result of the login request
   */
  public LoginResult login(LoginRequest request) {
    Database database = new Database();
    LoginResult result = new LoginResult();
    UserDAO userDAO = database.getUserDAO();
    AuthTokenDAO authTokenDAO = database.getAuthTokenDAO();
    if (!userDAO.userExists(request.getUsername())) {
      result.setMessage("Error: User does not exist, please register first");
      result.setSuccess(false);
      database.closeConnection(false);
      return result;
    }
    try {
      if (!request.getPassword().equals(userDAO.getPassword(request.getUsername()))) {
        result.setMessage("Error: Incorrect Password");
        result.setSuccess(false);
        database.closeConnection(false);
        return result;
      }
      String authtoken = database.genToken();
      AuthToken authToken = new AuthToken(authtoken,request.getUsername());
      authTokenDAO.insert(authToken);
      result = new LoginResult(authtoken,request.getUsername(),userDAO.getPersonID(request.getUsername()),true);
      database.closeConnection(true);
      return result;
    }
    catch(DataAccessException e) {
      result.setMessage("Error: Accessing database :(");
      result.setSuccess(false);
      database.closeConnection(false);
      return result;
    }

  }
}
