package dao;

import model.Event;
import model.Person;
import model.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PersonDAOTest {

    private Database database;
    private Person person;
    private PersonDAO personDAO;

    @BeforeEach
    public void setUp() throws DataAccessException {
        database = new Database();
        person = new Person("test","12345","Jake","Schilling","m",null,"123456",null);
        personDAO = database.getPersonDAO();
        personDAO.clear();
    }

    @AfterEach
    public void tearDown() {
        database.closeConnection(false);
    }

    @Test
    @DisplayName("Insert Person Success")
    public void insertPass() throws DataAccessException {
        personDAO.insert(person);

        Person check = personDAO.find(person.getPersonID());
        assertNotNull(check);

        assertEquals(person,check);
    }
    @Test
    @DisplayName("Insert Person Failure")
    public void insertFail() throws DataAccessException {
        personDAO.insert(person);
        assertThrows(DataAccessException.class, () -> personDAO.insert(person));
    }
    @Test
    @DisplayName("Find Person Success")
    public void findPass() throws DataAccessException {
        personDAO.insert(person);
        Person check = personDAO.find(person.getPersonID());
        assertNotNull(check);
        assertEquals(person,check);
    }
    @Test
    @DisplayName("Find Person Failure")
    public void findFail() throws DataAccessException {
        personDAO.insert(person);
        database.closeConnection(false);
        assertThrows(DataAccessException.class, () -> personDAO.find(person.getPersonID()));
        database = new Database();
    }

    @Test
    @DisplayName("Clear Person")
    public void clear() throws DataAccessException {
        personDAO.insert(person);
        personDAO.clear();
        Person check = personDAO.find(person.getPersonID());
        assertNull(check);
    }
    @Test
    @DisplayName("Person ID Exists Neg")
    void doesPersonIDExistNeg() {
        assertFalse(personDAO.doesPersonIDExist("token"));
    }
    @Test
    @DisplayName("Person ID Exists Pos")
    void doesPersonIDExistPos() throws DataAccessException {
        personDAO.insert(person);
        assertTrue(personDAO.doesPersonIDExist("12345"));

    }
    @Test
    @DisplayName("Find Family Persons Pos")
    void findFamilyPos() throws DataAccessException {
        personDAO.insert(person);
        Person personTwo = new Person("test","6789","Bob","Schilling","m",null,"123456",null);
        personDAO.insert(personTwo);
        ArrayList<Person> familyPersons = personDAO.findFamily("test");
        assert(familyPersons.size() == 2);
    }
    @Test
    @DisplayName("Find Family Persons Neg")
    void findFamilyNeg() throws DataAccessException {
        personDAO.insert(person);
        Person personTwo = new Person("test","6789","Bob","Schilling","m",null,"123456",null);
        personDAO.insert(personTwo);
        database.closeConnection(true);
        assertThrows(DataAccessException.class, () -> personDAO.findFamily("test"));
        database = new Database();
    }

    @Test
    @DisplayName("Delete by Username Pos")
    void deleteByUsernamePos() throws DataAccessException {
        Person personTwo = new Person("test","6789","Bob","Schilling","m",null,"123456",null);
        personDAO.insert(person);
        personDAO.insert(personTwo);
        personDAO.deleteByUsername("test");
        assertFalse(personDAO.doesPersonIDExist("12345"));
    }
    @Test
    @DisplayName("Delete by Username Neg")
    void deleteByUsernameNeg() throws DataAccessException {
        database.closeConnection(true);
        assertThrows(DataAccessException.class, () -> personDAO.deleteByUsername("fake"));
        database = new Database();
    }
}