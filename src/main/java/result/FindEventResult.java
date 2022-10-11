package result;

/**
 * Contains the response of the user trying to find a specific event by its ID
 */
public class FindEventResult {
    /**
     * The specific ID tied to the event
     */
    private String eventID;

    /**
     * The username of the user that the event is associated with
     */
    private String associatedUsername;
    /**
     * The ID of the person that the event is associated with
     */
    private String personID;
    /**
     * A float representing the latitude of the event
     */
    private Float latitude;
    /**
     * A float representing the longitude of the event
     */
    private Float longitude;
    /**
     * A string representing the country where the event occurred
     */
    private String country;
    /**
     * A string representing the city where the event occurred
     */
    private String city;
    /**
     * A string representing the type of the event
     */
    private String eventType;
    /**
     * An integer representing the country where the event occurred
     */
    private int year;
    /**
     * The success of the operation
     */
    private boolean success;
    /**
     * The optional error message
     */
    private String message;

    /**
     * The constructor used when operation is successful, initializes all variables and success to true
     * @param eventID the ID associated with found event
     * @param associatedUsername the username associating the user with this event
     * @param personID the ID associating a person with this event
     * @param latitude the latitude of the event
     * @param longitude the longitude of the event
     * @param country the country where the event occurred
     * @param city the city where the event occurred
     * @param eventType the type of the event
     * @param year the year the event occurred
     * @param success true in this case
     */
    public FindEventResult(String eventID, String associatedUsername, String personID, Float latitude, Float longitude, String country, String city, String eventType, int year, boolean success) {
        this.eventID = eventID;
        this.associatedUsername = associatedUsername;
        this.personID = personID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.city = city;
        this.eventType = eventType;
        this.year = year;
        this.success = success;
    }

    /**
     * Constructor used in case of failure, initializes message describing error and bool of false
     * @param success false in this case
     * @param message the error message describing the error
     */
    public FindEventResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
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

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
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
