package result;

import request.RegisterRequest;

/**
 * Returns the results of the register request to the user
 */
public class RegisterResult {
  /**
   * The generated authorization token after registering and automatic logging in
   */
  private String authtoken;
  /**
   * The username
   */
  private String username;
  /**
   * The person ID for the matching person created upon registration
   */
  private String personID;
  /**
   * A message describing any errors, if successful will be null
   */
  private String message;
  /**
   * The success of the request
   */
  private boolean success;

  /**
   * The constructor for use when the request has failed
   * @param message the error message generated
   * @param success the failure of the request as a boolean
   */
  public RegisterResult(String message, boolean success) {
    this.message = message;
    this.success = success;
  }

  /**
   * The constructor used for a successful response
   * @param authtoken the generated authorization token
   * @param username the username
   * @param personID person ID of the matching person created upon registration
   * @param success a boolean value representing success of request
   */
  public RegisterResult(String authtoken, String username, String personID, boolean success) {
    this.authtoken = authtoken;
    this.username = username;
    this.personID = personID;
    this.success = success;
    this.message = null;
  }
}
