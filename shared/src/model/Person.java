package model;

import java.util.Objects;
/**
 * The data access object for the person table
 */
public class Person {
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
   * Initializes the member variables for the person object
   * @param username associated username for the person
   * @param personID the ID
   * @param firstName first name of the person
   * @param lastName last name of the person
   * @param gender gender, either "m" or "f"
   * @param fatherID either null or the associated father person ID
   * @param motherID either null or the associated mother person ID
   * @param spouseID either null or the associated spouse person ID
   */
  public Person(String username, String personID, String firstName, String lastName,
               String gender, String fatherID, String motherID, String spouseID) {
    this.personID = personID;
    this.associatedUsername = username;
    this.firstName = firstName;
    this.lastName = lastName;
    this.gender = gender;
    this.fatherID = fatherID;
    this.motherID = motherID;
    this.spouseID = spouseID;
  }




  public String getAssociatedUsername() {
    return associatedUsername;
  }

  public void setAssociatedUsername(String associatedUsername) {
    this.associatedUsername=associatedUsername;
  }

  public String getPersonID() {
    return personID;
  }

  public void setPersonID(String personID) {
    this.personID=personID;
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

  public String getFatherID() {
    return fatherID;
  }

  public void setFatherID(String fatherID) {
    this.fatherID=fatherID;
  }

  public String getMotherID() {
    return motherID;
  }

  public void setMotherID(String motherID) {
    this.motherID=motherID;
  }

  public String getSpouseID() {
    return spouseID;
  }

  public void setSpouseID(String spouseID) {
    this.spouseID=spouseID;
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
    Person person = (Person) o;
    return Objects.equals(personID, person.personID) && Objects.equals(associatedUsername, person.associatedUsername) && Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && Objects.equals(gender, person.gender) && Objects.equals(fatherID, person.fatherID) && Objects.equals(motherID, person.motherID) && Objects.equals(spouseID, person.spouseID);
  }

}
