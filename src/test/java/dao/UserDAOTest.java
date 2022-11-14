package dao;

import model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest {
    private Database db;
    private User user;
    private UserDAO userDAO;

    @BeforeEach
    public void setUp() throws DataAccessException {
        db = new Database();
        user = new User("test","test","jake@notreal.com","Jake","Schilling","m","123456");
        userDAO = db.getUserDAO();
        userDAO.clear();
    }

    @AfterEach
    public void tearDown() {
        db.closeConnection(false);
    }

    @Test
    @DisplayName("Insert User Success")
    public void insertPass() throws DataAccessException {
        userDAO.insert(user);
        User check = userDAO.findUser(user.getUsername());
        assertNotNull(check);
        assertEquals(user,check);
    }
    @Test
    @DisplayName("Insert User Failure")
    public void insertFail() throws DataAccessException {
        userDAO.insert(user);
        assertThrows(DataAccessException.class, () -> userDAO.insert(user));
    }
    @Test
    @DisplayName("Find User Success")
    public void findPass() throws DataAccessException {
        userDAO.insert(user);
        User check = userDAO.findUser(user.getUsername());
        assertNotNull(check);
        assertEquals(user,check);
    }
    @Test
    @DisplayName("Find User Failure")
    public void findFail() throws DataAccessException {
        userDAO.insert(user);
        db.closeConnection(false);
        assertThrows(DataAccessException.class, () -> userDAO.findUser(user.getUsername()));
        db = new Database();
    }

    @Test
    @DisplayName("Clear User")
    public void clear() throws DataAccessException {
        userDAO.insert(user);
        userDAO.clear();
        User check = userDAO.findUser(user.getUsername());
        assertNull(check);
    }

    @Test
    @DisplayName("Get Password Pos")
    void getPasswordPos() throws DataAccessException {
        userDAO.insert(user);
        assertEquals("test",userDAO.getPassword("test"));
    }
    @Test
    @DisplayName("Get Password Neg")
    void getPasswordNeg() throws DataAccessException {
        assertThrows(DataAccessException.class,()->userDAO.getPassword("test"));
    }

    @Test
    @DisplayName("Get PersonID Pos")
    void getPersonIDPos() throws DataAccessException {
        userDAO.insert(user);
        assertEquals("123456",userDAO.getPersonID("test"));
    }
    @Test
    @DisplayName("Get PersonID Neg")
    void getPersonIDNeg() throws DataAccessException {
        assertThrows(DataAccessException.class,()->userDAO.getPersonID("test"));
    }

    @Test
    @DisplayName("User Exists Neg")
    void userExistsNeg() {
        assertFalse(userDAO.userExists("token"));
    }
    @Test
    @DisplayName("User Exists Pos")
    void userExistsPos() throws DataAccessException {
        userDAO.insert(user);
        assertTrue(userDAO.userExists("test"));

    }
}