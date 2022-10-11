package result;

/**
 * Returns the results of the fill request to the user
 */
public class FillResult {
  /**
   * The message, either that it was successful or the error message that was generated,
   * if successful will include the number of events and people added
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
  public FillResult(String message, boolean success) {
    this.message = message;
    this.success = success;
  }
}
