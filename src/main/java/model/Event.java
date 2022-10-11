package model;

import java.util.Objects;
/**
 * The data access object for the event table
 */
public class Event {
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

    public Event(String eventID, String username, String personID, Float latitude, Float longitude,
                 String country, String city, String eventType, int year) {
        this.eventID = eventID;
        this.associatedUsername = username;
        this.personID = personID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.city = city;
        this.eventType = eventType;
        this.year = year;
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
    /**
     * Overriding equals function to ensure proper comparison
     * @param o, the object to be compared
     * @return bool representing if the objects were equal or not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(eventID, event.eventID) && Objects.equals(associatedUsername, event.associatedUsername) && Objects.equals(personID, event.personID) && Objects.equals(latitude, event.latitude) && Objects.equals(longitude, event.longitude) && Objects.equals(country, event.country) && Objects.equals(city, event.city) && Objects.equals(eventType, event.eventType) && Objects.equals(year, event.year);
    }

}
