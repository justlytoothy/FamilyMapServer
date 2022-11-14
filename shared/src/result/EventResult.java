package result;

import model.Event;
import model.Event;

import java.util.ArrayList;

/**
 * Contains the results of the login request to the user
 */
public class EventResult {

    private ArrayList<Event> data;
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
    public EventResult(String message, boolean success) {
        this.message = message;
        this.success = success;
    }
    public EventResult(){}

    /**
     * Constructor for successful response
     * @param event
     */
    public EventResult(Event event) {
        eventID = event.getEventID();
        associatedUsername = event.getAssociatedUsername();
        personID = event.getPersonID();
        latitude = event.getLatitude();
        longitude = event.getLongitude();
        country = event.getCountry();
        city = event.getCity();
        eventType = event.getEventType();
        year = event.getYear();
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

    public ArrayList<Event> getData() {
        return data;
    }

    public void setData(ArrayList<Event> data) {
        this.data = data;
    }

}
