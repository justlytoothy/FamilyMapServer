package model;

import java.util.Objects;

/**
 * The data access object for the user table
 */
public class User {
  /**
   * The username
   */
  private String username;
  /**
   * The password
   */
  private String password;
  /**
   * The users email
   */
  private String email;
  /**
   * The users first name
   */
  private String firstName;
  /**
   * The users last name
   */
  private String lastName;
  /**
   * The users gender, either "m" or "f"
   */
  private String gender;
  /**
   * The person ID of the person associated with this user
   */
  private String personID;

  /**
   * Initializes member variables for the user model object
   * @param username username for the user
   * @param password password for the user
   * @param email users email
   * @param firstName first name of the user
   * @param lastName last name of the user
   * @param gender gender of user, either "m" or "f"
   * @param personID the person ID for associated person object
   */
  public User(String username, String password, String email, String firstName, String lastName, String gender, String personID) {
    this.username=username;
    this.password=password;
    this.email=email;
    this.firstName = firstName;
    this.lastName = lastName;
    this.gender = gender;
    this.personID = personID;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username=username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password=password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email=email;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName=firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName=lastName;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender=gender;
  }

  public String getPersonID() {
    return personID;
  }

  public void setPersonID(String personID) {
    this.personID=personID;
  }

  /**
   * Overriding equals function to ensure proper comparison
   * @param o, the object to be compared
   * @return bool representing if the objects were equal or not
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    User user = (User) o;
    return Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(gender, user.gender) && Objects.equals(email, user.email);
  }
}
