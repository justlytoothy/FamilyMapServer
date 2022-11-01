package dao;

import model.Event;
import model.Person;
import model.User;

import java.sql.*;
import java.util.ArrayList;

/**
 * The data access object for the event table
 */
public class EventDAO {
    /**
     * The database connection
     */
    private final Connection conn;
    /**
     * Initialize database connection
     * @param conn a new connection
     */
    public EventDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * Takes in an object of event class and inserts all of the fields into a new row in the table
     * @param event the event to be added
     * @throws DataAccessException if unable to access data
     */
    public void insert(Event event) throws DataAccessException {
        String sql = "INSERT INTO Event (eventID, associatedUsername, personID, latitude, longitude, " +
                "country, city, eventType,year) VALUES(?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1,event.getEventID());
            statement.setString(2,event.getAssociatedUsername());
            statement.setString(3,event.getPersonID());
            statement.setFloat(4,event.getLatitude());
            statement.setFloat(5,event.getLongitude());
            statement.setString(6,event.getCountry());
            statement.setString(7,event.getCity());
            statement.setString(8,event.getEventType());
            statement.setInt(9,event.getYear());


            statement.executeUpdate();
        }
        catch(SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while inserting into the event table");
        }
    }

    /**
     * Takes in a event ID and returns the matching row from the database
     * @param eventID the ID of the event to be found
     * @return the found event
     * @throws DataAccessException if unable to access data
     */
    public Event find(String eventID) throws DataAccessException {
        Event event;
        ResultSet rs;
        String sql = "SELECT * FROM Event WHERE eventID = ?;";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1,eventID);
            rs = statement.executeQuery();
            if (rs.next()) {
                event = new Event(rs.getString("eventID"),rs.getString("associatedUsername"),
                        rs.getString("personID"),rs.getFloat("latitude"),
                        rs.getFloat("longitude"), rs.getString("country"),
                        rs.getString("city"), rs.getString("eventType"), rs.getInt("year"));
                return event;
            }
            else {
                return null;
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding event");
        }
    }

    /**
     * Clears all entries from the events table
     * @throws DataAccessException
     */
    public void clear() throws DataAccessException {
        String sql = "DELETE FROM Event";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.executeUpdate();
        }
        catch(SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while clearing the events table");
        }
    }

    public void deleteByUsername(String username) throws DataAccessException {
        PreparedStatement stmt = null;
        String delete = "DELETE FROM Event WHERE associatedUsername = ?";
        try {
            stmt = conn.prepareStatement(delete);
            stmt.setString(1, username);
            stmt.executeUpdate();
            stmt.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error while deleting events by username :(");
        }
    }
    public boolean doesEventIDExist(String eventID) {
        boolean exists = true;

        PreparedStatement stmt = null;
        ResultSet results = null;
        String query = "SELECT * FROM Event WHERE eventID = ?";
        try {
            stmt = conn.prepareStatement(query);
            stmt.setString(1, eventID);
            results = stmt.executeQuery();
            exists = results.next();
            stmt.close();
            results.close();
            return exists;
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Dang it we got problems");
            return false;
        }
    }

    /**
     * Finds all the family members of the provided person object
     * @param username the username string to find family
     * @return an array holding person objects of the family members or empty array
     * @throws DataAccessException if unable to access data
     */
    public ArrayList<Event> findFamilyEvents(String username) throws DataAccessException {
        ArrayList<Event> familyEvents = new ArrayList<>();
        ResultSet rs;
        String sql = "SELECT * FROM Event WHERE associatedUsername = ?;";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1,username);
            rs = statement.executeQuery();
            while (rs.next()) {
                Event event = new Event(rs.getString("eventID"),rs.getString("associatedUsername"),
                        rs.getString("personID"),rs.getFloat("latitude"),
                        rs.getFloat("longitude"), rs.getString("country"),
                        rs.getString("city"), rs.getString("eventType"), rs.getInt("year"));
                familyEvents.add(event);
            }
            statement.close();
            rs.close();
            return familyEvents;

        }
        catch(SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding family events");
        }

    }
}
