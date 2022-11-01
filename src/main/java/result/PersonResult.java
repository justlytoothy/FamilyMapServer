package result;

import model.Person;

import java.util.ArrayList;

/**
 * Contains the results of the login request to the user
 */
public class PersonResult {
    /**
     * The associated username for the found person
     */
    private String associatedUsername;
    private ArrayList<Person> data;
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
     * A message describing any errors, if successful will be null
     */
    private String message;
    /**
     * The success of the request
     */
    private boolean success;
    /**
     * The constructor for use when the request has failed
     * @param message the error message generated
     * @param success the failure of the request as a boolean
     */
    public PersonResult(String message, boolean success) {
        this.message = message;
        this.success = success;
    }
    public PersonResult(){}

    /**
     * Constructor for successful response
     * @param person
     */
    public PersonResult(Person person) {
        fatherID = person.getFatherID();
        motherID = person.getMotherID();
        spouseID = person.getSpouseID();
        firstName = person.getFirstName();
        associatedUsername = person.getAssociatedUsername();
        lastName = person.getLastName();
        personID = person.getPersonID();
        gender = person.getGender();
        success = true;
        message = null;
    }


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

    public String getAssociatedUsername() {
        return associatedUsername;
    }

    public void setAssociatedUsername(String associatedUsername) {
        this.associatedUsername = associatedUsername;
    }

    public ArrayList<Person> getData() {
        return data;
    }

    public void setData(ArrayList<Person> data) {
        this.data = data;
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
}
