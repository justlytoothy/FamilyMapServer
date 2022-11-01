package dao;

import model.Event;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class EventDAOTest {
    private Database database;
    private EventDAO eventDAO;
    private Event event;
    @BeforeEach
    void setUp() throws DataAccessException {
        database = new Database();
        eventDAO = database.getEventDAO();
        event = new Event("1234","username","person",Float.parseFloat("12.24"),Float.parseFloat("12.24"),"country","city","eventType",2022);
        eventDAO.clear();
    }

    @AfterEach
    void tearDown() {
        database.closeConnection(true);
    }

    @Test
    @DisplayName("Insert Event Success")
    public void insertPass() throws DataAccessException {
        eventDAO.insert(event);
        Event check = eventDAO.find(event.getEventID());
        assertNotNull(check);
        assertEquals(event,check);
    }
    @Test
    @DisplayName("Insert Event Failure")
    public void insertFail() throws DataAccessException {
        eventDAO.insert(event);
        assertThrows(DataAccessException.class, () -> eventDAO.insert(event));
    }
    @Test
    @DisplayName("Find Event Success")
    public void findPass() throws DataAccessException {
        eventDAO.insert(event);
        Event check = eventDAO.find(event.getEventID());
        assertNotNull(check);
        assertEquals(event,check);
    }
    @Test
    @DisplayName("Find Event Failure")
    public void findFail() throws DataAccessException {
        eventDAO.insert(event);
        database.closeConnection(false);
        assertThrows(DataAccessException.class, () -> eventDAO.find(event.getEventID()));
        database = new Database();
    }

    @Test
    @DisplayName("Clear Event")
    public void clear() throws DataAccessException {
        eventDAO.insert(event);
        eventDAO.clear();
        Event check = eventDAO.find(event.getEventID());
        assertNull(check);
    }

    @Test
    @DisplayName("Event ID Exists Neg")
    void doesEventIDExistNeg() {
        assertFalse(eventDAO.doesEventIDExist("token"));
    }
    @Test
    @DisplayName("Event ID Exists Pos")
    void doesEventIDExistPos() throws DataAccessException {
        eventDAO.insert(event);
        assertTrue(eventDAO.doesEventIDExist("1234"));

    }

    @Test
    @DisplayName("Find Family Events Pos")
    void findFamilyEventsPos() throws DataAccessException {
        eventDAO.insert(event);
        Event eventTwo = new Event("5678","username","person",Float.parseFloat("12.24"),Float.parseFloat("12.24"),"country","city","eventType",2022);
        eventDAO.insert(eventTwo);
        ArrayList<Event> familyEvents = eventDAO.findFamilyEvents("username");
        assert(familyEvents.size() == 2);
    }
    @Test
    @DisplayName("Find Family Events Neg")
    void findFamilyEventsNeg() throws DataAccessException {
        eventDAO.insert(event);
        Event eventTwo = new Event("5678","username","person",Float.parseFloat("12.24"),Float.parseFloat("12.24"),"country","city","eventType",2022);
        eventDAO.insert(eventTwo);
        database.closeConnection(true);
        assertThrows(DataAccessException.class, () -> eventDAO.findFamilyEvents("username"));
        database = new Database();
    }
    @Test
    @DisplayName("Delete by Username Pos")
    void deleteByUsernamePos() throws DataAccessException {
        Event eventTwo = new Event("5678","username","person",Float.parseFloat("12.24"),Float.parseFloat("12.24"),"country","city","eventType",2022);
        eventDAO.insert(event);
        eventDAO.insert(eventTwo);
        eventDAO.deleteByUsername("username");
        assertFalse(eventDAO.doesEventIDExist("1234"));
    }
    @Test
    @DisplayName("Delete by Username Neg")
    void deleteByUsernameNeg() throws DataAccessException {
        database.closeConnection(true);
        assertThrows(DataAccessException.class, () -> eventDAO.deleteByUsername("fake"));
        database = new Database();
    }
}