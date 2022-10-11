package result;
import model.Person;

/**
 * Contains the response of finding all associated family members of the current user
 */
public class AllFamilyResult {
  /**
   * The array of the found family members, objects of type person
   */
  private Person[] data;
  /**
   * The success of the operation
   */
  private boolean success;
  /**
   * The optional error message
   */
  private String message;

  /**
   * Constructor for when operation is successful, initializes array of data found and bool of true
   * @param data the array of the person objects for the users family members
   * @param success true in this case
   */
  public AllFamilyResult(Person[] data, boolean success) {
    this.data = data;
    this.success = success;
  }

  /**
   * Initializes a message with error that caused failure and success value of false
   * @param success false in this case
   * @param message the error message generated
   */
  public AllFamilyResult(boolean success, String message) {
    this.success = success;
    this.message = message;
  }

  public Person[] getData() {
    return data;
  }

  public void setData(Person[] data) {
    this.data = data;
  }

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
