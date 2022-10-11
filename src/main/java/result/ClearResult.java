package result;

/**
 * Returns the results of the clear request to the user
 */
public class ClearResult {
  /**
   * The message, either that it was successful or the error message that was generated
   */
  private String message;
  /**
   * boolean describing the success of the request, whether it succeeded or failed
   */
  private boolean success;
  /**
   * Initializes the member variables of the response
   * @param message the message, either success or the error message
   * @param success the boolean representing success of request
   */
  public ClearResult(String message, boolean success) {
    this.message = message;
    this.success = success;
  }
}
