package dao;

import model.AuthToken;
import model.AuthToken;
import model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthTokenDAOTest {
    private Database database;
    private AuthTokenDAO authTokenDAO;
    private AuthToken token;
    @BeforeEach
    void setUp() throws DataAccessException {
        database = new Database();
        authTokenDAO = database.getAuthTokenDAO();
        token = new AuthToken("1234","hi");
        authTokenDAO.clear();
    }

    @AfterEach
    void tearDown() {
        database.closeConnection(true);
    }

    @Test
    @DisplayName("Insert AuthToken Success")
    public void insertPass() throws DataAccessException {
        authTokenDAO.insert(token);
        AuthToken check = authTokenDAO.find(token.getUsername());
        assertNotNull(check);
        assertEquals(token,check);
    }
    @Test
    @DisplayName("Insert AuthToken Failure")
    public void insertFail() throws DataAccessException {
        authTokenDAO.insert(token);
        assertThrows(DataAccessException.class, () -> authTokenDAO.insert(token));
    }
    @Test
    @DisplayName("Find AuthToken Success")
    public void findPass() throws DataAccessException {
        authTokenDAO.insert(token);
        AuthToken check = authTokenDAO.find(token.getUsername());
        assertNotNull(check);
        assertEquals(token,check);
    }
    @Test
    @DisplayName("Find AuthToken Failure")
    public void findFail() throws DataAccessException {
        authTokenDAO.insert(token);
        database.closeConnection(false);
        assertThrows(DataAccessException.class, () -> authTokenDAO.find(token.getUsername()));
        database = new Database();
    }

    @Test
    @DisplayName("Clear AuthToken")
    public void clear() throws DataAccessException {
        authTokenDAO.insert(token);
        authTokenDAO.clear();
        AuthToken check = authTokenDAO.find(token.getUsername());
        assertNull(check);
    }


    @Test
    @DisplayName("Auth Token Exists Neg")
    void realAuthTokenNeg() {
        assertFalse(authTokenDAO.realAuthToken("token"));
    }
    @Test
    @DisplayName("Auth Token Exists Pos")
    void realAuthTokenPos() throws DataAccessException {
        authTokenDAO.insert(token);
        assertTrue(authTokenDAO.realAuthToken("1234"));

    }
    @Test
    @DisplayName("Get Username Neg")
    void getUsernameNeg() throws DataAccessException {
        authTokenDAO.insert(token);
        assertEquals("hi",authTokenDAO.getUsername("1234"));
    }
    @Test
    @DisplayName("Get Username Pos")
    void getUsernamePos() throws DataAccessException {
        authTokenDAO.insert(token);
        assertNull(authTokenDAO.getUsername("5667"));
    }
}