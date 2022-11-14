package result;

/**
 * Contains the response to the corresponding load request
 */
public class LoadResult {
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
  public LoadResult(String message, boolean success) {
    this.message = message;
    this.success = success;
  }

  public LoadResult() {}

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }
}
