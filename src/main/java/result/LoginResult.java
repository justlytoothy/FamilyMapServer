package result;

/**
 * Returns the results of the login request to the user
 */
public class LoginResult {
  /**
   * The generated authorization created after logging in
   */
  private String authtoken;
  /**
   * The username
   */
  private String username;
  /**
   * The person ID for the matching person for this user
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
  public LoginResult(String message, boolean success) {
    this.message = message;
    this.success = success;
  }

  /**
   * The constructor used for a successful response
   * @param authtoken the generated authorization token
   * @param username the username
   * @param personID person ID of the matching person
   * @param success a boolean value representing success of request
   */
  public LoginResult(String authtoken, String username, String personID, boolean success) {
    this.authtoken = authtoken;
    this.username = username;
    this.personID = personID;
    this.success = success;
    this.message = null;
  }
}
