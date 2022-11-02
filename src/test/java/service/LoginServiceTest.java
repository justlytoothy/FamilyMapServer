package service;

import dao.DataAccessException;
import dao.Database;
import dao.UserDAO;
import model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import request.LoginRequest;
import result.LoginResult;

import javax.xml.crypto.Data;

import static org.junit.jupiter.api.Assertions.*;

class LoginServiceTest {

    @Test
    @DisplayName("Login Positive")
    void loginPos() throws DataAccessException {
        Database database = new Database();
        database.clearDatabase();
        UserDAO userDao = database.getUserDAO();
        User user = new User("username", "pass", "email", "first", "last", "f", "123");

        userDao.insert(user);

        database.closeConnection(true);
        LoginRequest loginRequest = new LoginRequest("username", "pass");
        LoginResult loginResult = new LoginService().login(loginRequest);
        assertTrue(loginResult.isSuccess());
    }
    @Test
    @DisplayName("Login Negative")
    void loginNeg() throws DataAccessException {
        Database database = new Database();
        database.clearDatabase();
        UserDAO userDao = database.getUserDAO();
        User user = new User("username", "pass", "email", "first", "last", "f", "123");

        userDao.insert(user);

        database.closeConnection(true);
        LoginRequest loginRequest = new LoginRequest("username", "false");
        LoginResult loginResult = new LoginService().login(loginRequest);
        assertEquals("Error: Incorrect Password",loginResult.getMessage());
    }

}