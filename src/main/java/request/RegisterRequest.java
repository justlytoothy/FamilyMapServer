package request;

import model.User;

/**
 * The request to register a new user
 */
public class RegisterRequest {
  /**
   * The username for the user
   */
  private String username;
  /**
   * The password of the user
   */
  private String password;
  /**
   * The email of the user
   */
  private String email;
  /**
   * The first name of the user
   */
  private String firstName;
  /**
   * The last name of the user
   */
  private String lastName;
  /**
   * The gender of the user, "m" or "f"
   */
  private String gender;

  /**
   * Takes in all of the necessary parameters for the register result, then initializes all of the member variables
   * @param username the username
   * @param password the password
   * @param email the user email
   * @param firstName the user first name
   * @param lastName the user last name
   * @param gender the gender
   */
  public RegisterRequest(String username, String password, String email, String firstName, String lastName, String gender) {
    this.username=username;
    this.password=password;
    this.email=email;
    this.firstName=firstName;
    this.lastName=lastName;
    this.gender=gender;
  }
  public RegisterRequest(User user) {
    this.username=user.getUsername();
    this.password=user.getPassword();
    this.email=user.getEmail();
    this.firstName=user.getFirstName();
    this.lastName=user.getLastName();
    this.gender=user.getGender();
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public String getEmail() {
    return email;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getGender() {
    return gender;
  }
}
