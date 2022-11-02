package service;

import dao.AuthTokenDAO;
import dao.DataAccessException;
import dao.Database;
import dao.PersonDAO;
import model.AuthToken;
import model.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import result.PersonResult;

import static org.junit.jupiter.api.Assertions.*;

class PersonServiceTest {
    private PersonResult incorrectAuth = new PersonResult("Error, Incorrect auth token",false);
    private Person personOne;
    private Person personTwo;
    private String auth;
    private PersonService personService;
    @BeforeEach
    void setUp() throws DataAccessException {
        personOne = new Person("name","idone","first","last","f",null,null,null);
        personTwo = new Person("username","1234","first","last","m",null,null,null);
        auth = "token";
        Database database = new Database();
        AuthTokenDAO authTokenDAO = database.getAuthTokenDAO();
        PersonDAO personDAO = database.getPersonDAO();
        AuthToken token = new AuthToken(auth,"username");
        authTokenDAO.insert(token);
        personDAO.insert(personOne);
        personDAO.insert(personTwo);
        personService = new PersonService();
        database.closeConnection(true);
    }
    @AfterEach
    void tearDown() throws DataAccessException {
        Database database = new Database();
        database.clearDatabase();
        database.closeConnection(true);
    }

    @Test
    @DisplayName("Get Person Positive")
    void getPersonPos() {
        String [] paramsSingle = new String[2];
        paramsSingle[0] = "hi";
        paramsSingle[1] = "1234";
        assertEquals(incorrectAuth.getMessage(),personService.getPerson("wrong",paramsSingle).getMessage());

    }
    @Test
    @DisplayName("Get Person Negative")
    void getPersonNeg() {
        String [] paramsMulti = new String[1];
        paramsMulti[0] = "hi";
        assertFalse(personService.getPerson("hello",paramsMulti).isSuccess());
    }

    @Test
    @DisplayName("Get One Person Positive")
    void getOnePersonPos() {
        String [] paramsSingle = new String[2];
        paramsSingle[0] = "hi";
        paramsSingle[1] = "1234";
        assertTrue(personService.getOnePerson(auth,paramsSingle).isSuccess());
    }
    @Test
    @DisplayName("Get One Person Negative")
    void getOnePersonNeg() {
        String [] paramsSingle = new String[2];
        paramsSingle[0] = "hi";
        paramsSingle[1] = "1234";
        assertFalse(personService.getOnePerson("2",paramsSingle).isSuccess());
    }

    @Test
    @DisplayName("Get Family Events Positive")
    void getFamilyPos() {
        assertEquals(1,personService.getFamily(auth).getData().size());

    }
    @Test
    @DisplayName("Get Family Events Negative")
    void getFamilyNeg() {
        assertNull(personService.getFamily("wrong").getData());

    }
}