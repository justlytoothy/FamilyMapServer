package service;

import dao.DataAccessException;
import dao.Database;
import dao.UserDAO;
import model.Person;
import model.User;
import passoffrequest.FillRequest;
import request.LoginRequest;
import request.RegisterRequest;
import result.LoginResult;
import result.RegisterResult;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Performs register operations from requests and returns the results
 */
public class RegisterService {
  /**
   * This method takes in a request to register a new user,
   * generates 4 new generations of ancestor data,
   * logs the user in and then generates an authorization toke for them
   * @param request the request sent to register a new user
   * @return the result of the request to register a user
   */
  public RegisterResult register(RegisterRequest request) {
    RegisterResult result = new RegisterResult();
    Database database = new Database();
    UserDAO userDAO = database.getUserDAO();

    if (userDAO.userExists(request.getUsername())) {
      System.out.println("you already exist");
      result.setMessage("error: Username already in use, try again :(");
      database.closeConnection(false);
      return result;
    }

    String personID = database.genToken();

    User user = new User(request, personID);
    try {
      userDAO.insert(user);
    }
    catch (DataAccessException e) {
      result.setMessage("error: Invalid request. Try again next time :/");
      result.setSuccess(false);
      database.closeConnection(false);
      return result;
    }
    database.closeConnection(true);
    FillService fillService = new FillService();
    fillService.fillHelper(request.getUsername(), 4);

    LoginRequest loginRequest = new LoginRequest(request.getUsername(), request.getPassword());
    LoginResult loginResult = new LoginService().login(loginRequest);

    result = new RegisterResult(loginResult);
    result.setSuccess(true);
    return result;
  }
}
