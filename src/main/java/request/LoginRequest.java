package request;

/**
 * The request to login a user
 */
public class LoginRequest {
  /**
   * the username used to login
   */
  private String username;
  /**
   * The password used to login
   */
  private String password;

  /**
   * Takes in username and password and initializes fields in the request
   * @param username the username entered to login
   * @param password the password entered to login
   */
  public LoginRequest(String username, String password) {
    this.username=username;
    this.password=password;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }
}
