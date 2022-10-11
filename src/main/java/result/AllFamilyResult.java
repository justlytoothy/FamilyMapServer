package result;
import model.Person;

/**
 * Returns the response of finding all associated family members of the current user
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
}
