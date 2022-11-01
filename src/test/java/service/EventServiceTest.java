package service;

import dao.AuthTokenDAO;
import dao.DataAccessException;
import dao.Database;
import dao.EventDAO;
import model.AuthToken;
import model.Event;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import result.EventResult;

import static org.junit.jupiter.api.Assertions.*;

class EventServiceTest {
    private EventResult incorrectAuth = new EventResult("Error, Incorrect auth token",false);
    private Event eventOne;
    private Event eventTwo;
    private String auth;
    private EventService eventService;
    @BeforeEach
    void setUp() throws DataAccessException {
        eventOne = new Event("1234","username","person",Float.parseFloat("12.24"),Float.parseFloat("12.24"),"country","city","eventType",2022);
        eventTwo = new Event("5678","username","person",Float.parseFloat("12.24"),Float.parseFloat("12.24"),"country","city","eventType",2022);
        auth = "token";
        Database database = new Database();
        AuthTokenDAO authTokenDAO = database.getAuthTokenDAO();
        EventDAO eventDAO = database.getEventDAO();
        AuthToken token = new AuthToken(auth,"username");
        authTokenDAO.insert(token);
        eventDAO.insert(eventOne);
        eventDAO.insert(eventTwo);
        eventService = new EventService();
        database.closeConnection(true);
    }
    @AfterEach
    void tearDown() throws DataAccessException {
        Database database = new Database();
        database.clearDatabase();
        database.closeConnection(true);
    }

    @Test
    @DisplayName("Get Event Positive")
    void getEventPos() {
        String [] paramsSingle = new String[2];
        paramsSingle[0] = "hi";
        paramsSingle[1] = "1234";
        assertEquals(incorrectAuth.getMessage(),eventService.getEvent("wrong",paramsSingle).getMessage());

    }
    @Test
    @DisplayName("Get Event Negative")
    void getEventNeg() {
        String [] paramsMulti = new String[1];
        paramsMulti[0] = "hi";
        assertEquals(2,eventService.getEvent(auth,paramsMulti).getData().size());
    }

    @Test
    @DisplayName("Get One Event Positive")
    void getOneEventPos() {
        String [] paramsSingle = new String[2];
        paramsSingle[0] = "hi";
        paramsSingle[1] = "1234";
        assertTrue(eventService.getOneEvent(auth,paramsSingle).isSuccess());
    }
    @Test
    @DisplayName("Get One Event Negative")
    void getOneEventNeg() {
        String [] paramsSingle = new String[2];
        paramsSingle[0] = "hi";
        paramsSingle[1] = "1234";
        assertFalse(eventService.getOneEvent("2",paramsSingle).isSuccess());
    }

    @Test
    @DisplayName("Get Family Events Positive")
    void getFamilyEventsPos() {
        assertEquals(2,eventService.getFamilyEvents(auth).getData().size());

    }
    @Test
    @DisplayName("Get Family Events Negative")
    void getFamilyEventsNeg() {
        assertNull(eventService.getFamilyEvents("wrong").getData());

    }
}