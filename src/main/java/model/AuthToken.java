package model;

import java.util.Objects;

/**
 * The data access object for authorization token table
 */
public class AuthToken {
  private String authtoken;
  private String username;


  public AuthToken(String authtoken, String username) {
    this.authtoken = authtoken;
    this.username = username;

  }

  public String getAuthToken() {
    return authtoken;
  }

  public void setAuthToken(String authtoken) {
    this.authtoken = authtoken;
  }


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
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
    AuthToken token = (AuthToken) o;
    return Objects.equals(authtoken, token.authtoken) && Objects.equals(username, token.username);
  }


}
