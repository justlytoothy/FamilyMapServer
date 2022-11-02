package service;

import dao.DataAccessException;
import dao.Database;
import dao.PersonDAO;
import dao.UserDAO;
import generator.Generator;
import model.Person;
import model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import result.FillResult;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FillServiceTest {
    private FillService fillService;
    private Database database;
    private Generator generator;
    private Person person;
    private User user = new User("username","password","email","Jake","Schilling","m","12345");

    @BeforeEach
    void setUp() throws DataAccessException {
        fillService = new FillService();
        database = new Database();
        person = new Person("username","12345","Jake","Schilling","m",null,"123456",null);
        database.clearDatabase();
        UserDAO userDAO = database.getUserDAO();
        userDAO.insert(user);
        database.closeConnection(true);
        database = new Database();
        generator = new Generator();
    }

    @AfterEach
    void tearDown() throws DataAccessException {
        database.clearDatabase();
        database.closeConnection(true);
    }

    @Test
    @DisplayName("Fill Positive")
    void fillPos() {
        String [] params = new String [2];
        params[1] = "username";
        fillService.fill(params);
        int people = fillService.getPeopleAdded();
        System.out.println(people);
        assertEquals(31,people);

    }
    @Test
    @DisplayName("Fill Negative")
    void fillNeg() {
        String [] params = new String [3];
        params[1] = "username";
        params[2] = "wrong";
        FillResult fillResult = fillService.fill(params);
        assert(fillResult.getMessage().equals("error:Invalid int param. Try again :("));
    }

    @Test
    @DisplayName("Fill Helper Positive")
    void fillHelperPos() throws DataAccessException {
        database.closeConnection(true);
        fillService.fillHelper("username",2);
        database = new Database();
        PersonDAO personDAO = database.getPersonDAO();
        assertEquals(7,personDAO.findFamily("username").size());
    }
    @Test
    @DisplayName("Fill Helper Negative")
    void fillHelperNeg() throws DataAccessException {
        database.closeConnection(true);
        fillService.fillHelper("username",5);
        database = new Database();
        //old data deleted
        PersonDAO personDAO = database.getPersonDAO();
        assertEquals(63,personDAO.findFamily("username").size());

    }

    @Test
    @DisplayName("Make Parents Positive")
    void makeParentsPos() throws SQLException,DataAccessException {
        fillService.makeParents(database,person,generator.getRandomLocation(),1);
        PersonDAO personDAO = database.getPersonDAO();
        assertEquals(3,personDAO.findFamily("username").size());
    }
    @Test
    @DisplayName("Make Parents Negative")
    void makeParentsNeg() throws SQLException,DataAccessException {
        person.setAssociatedUsername(null);
        assertThrows(SQLException.class, () -> fillService.makeParents(database,person,generator.getRandomLocation(),2));
    }
//negative case doesn't really exist for this???
    @Test
    @DisplayName("Create Mother Positive")
    void createMotherPos() {
        Person mother = fillService.createMother(database,"username");
        assertEquals("username",mother.getAssociatedUsername());
    }

//negative case doesn't really exist for this???
    @Test
    @DisplayName("Create Father Positive")
    void createFatherPos() {
        Person father = fillService.createFather(database,"username");
        assertEquals("username",father.getAssociatedUsername());
    }

    @Test
    @DisplayName("Create Events Positive")
    void createEventsPos() throws SQLException {
        fillService.setEventsAdded(0);
        Generator.Location marriage = generator.getRandomLocation();
        fillService.createEvents(database, person, "username", 1993, marriage);
        assertEquals(3,fillService.getEventsAdded());

    }
    @Test
    @DisplayName("Create Events Negative")
    void createEventsNeg() throws SQLException {
        Person personTwo = new Person("second", "username", "bob", "youruncle", "m", "f", "m", "s");
        fillService.setEventsAdded(0);
        Generator.Location marriage = generator.getRandomLocation();
        fillService.createEvents(database, personTwo, "username", 1700, marriage);
        assertEquals(4,fillService.getEventsAdded());
    }

    @Test
    @DisplayName("Is Integer Positive")
    void isIntegerPos() {
        assertTrue(fillService.isInteger("2"));
    }
    @Test
    @DisplayName("Is Integer Negative")
    void isIntegerNeg() {
        assertFalse(fillService.isInteger("hello"));
    }
}