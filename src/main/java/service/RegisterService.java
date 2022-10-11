package service;

import request.RegisterRequest;
import result.RegisterResult;

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
    return null;
  }
}
