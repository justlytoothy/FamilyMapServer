package result;

/**
 * Contains the result of trying to find a specified person if they are associated with the current user
 */
public class FindPersonResult {
  /**
   * The associated username for the found person
   */
  private String associatedUsername;
  /**
   * The person ID of the person
   */
  private String personID;
  /**
   * The persons first name
   */
  private String firstName;
  /**
   * The persons last name
   */
  private String lastName;
  /**
   * The persons gender, either "m" or "f"
   */
  private String gender;
  /**
   * The optional person id of the persons father
   */
  private String fatherID;
  /**
   * The optional person id of the persons mother
   */
  private String motherID;
  /**
   * The optional person id of the persons spouse
   */
  private String spouseID;
  /**
   * Whether the operation was successful or not
   */
  private boolean success;
  /**
   * The optional error message, only included if operation failed
   */
  private String message;

  /**
   * Initializes the error message and success to false
   * @param success the success, will be false in this constructor
   * @param message the error message generated
   */
  public FindPersonResult(boolean success, String message) {
    this.success=success;
    this.message=message;
  }

  /**
   * Initializes all member variables in the response
   * @param associatedUsername the associated username for the person
   * @param personID the id of the person found
   * @param firstName the first name of the person
   * @param lastName the last name of the person
   * @param gender the gender of the person, either "m" or "f"
   * @param fatherID the ID of the persons father, may be null
   * @param motherID the ID of the persons mother, may be null
   * @param spouseID, the ID of the persons spouse, may be null
   * @param success the success, will be true in this constructor
   */
  public FindPersonResult(String associatedUsername, String personID, String firstName, String lastName, String gender, String fatherID, String motherID, String spouseID, boolean success) {
    this.associatedUsername=associatedUsername;
    this.personID=personID;
    this.firstName=firstName;
    this.lastName=lastName;
    this.gender=gender;
    this.fatherID=fatherID;
    this.motherID=motherID;
    this.spouseID=spouseID;
    this.success=success;
  }

  public String getAssociatedUsername() {
    return associatedUsername;
  }

  public void setAssociatedUsername(String associatedUsername) {
    this.associatedUsername = associatedUsername;
  }

  public String getPersonID() {
    return personID;
  }

  public void setPersonID(String personID) {
    this.personID = personID;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getFatherID() {
    return fatherID;
  }

  public void setFatherID(String fatherID) {
    this.fatherID = fatherID;
  }

  public String getMotherID() {
    return motherID;
  }

  public void setMotherID(String motherID) {
    this.motherID = motherID;
  }

  public String getSpouseID() {
    return spouseID;
  }

  public void setSpouseID(String spouseID) {
    this.spouseID = spouseID;
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
