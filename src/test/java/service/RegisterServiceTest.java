package service;

import dao.DataAccessException;
import dao.Database;
import dao.UserDAO;
import model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import request.RegisterRequest;
import result.RegisterResult;

import static org.junit.jupiter.api.Assertions.*;

class RegisterServiceTest {



    @Test
    @DisplayName("Register Positive")
    void registerPos() throws DataAccessException {
        Database database = new Database();
        database.clearDatabase();
        UserDAO userDAO = database.getUserDAO();
        User user = new User("username", "password", "email", "first", "last", "m", "999");
        User userTwo = new User("user", "password", "email", "last", "first", "f", "9999");
        userDAO.insert(userTwo);
        database.closeConnection(true);


        RegisterRequest registerRequest = new RegisterRequest(user);
        RegisterResult registerResult = new RegisterService().register(registerRequest);
        assertTrue(registerResult.isSuccess());
    }
    @Test
    @DisplayName("Register Negative")
    void registerNeg() throws DataAccessException {
        Database database = new Database();
        database.clearDatabase();
        UserDAO userDAO = database.getUserDAO();
        User user = new User("username", "password", "email", "first", "last", "m", "999");
        User userTwo = new User("user", "password", "email", "last", "first", "f", "9999");
        userDAO.insert(userTwo);
        database.closeConnection(true);
        RegisterRequest rRequest = new RegisterRequest(userTwo);
        RegisterResult registerResult = new RegisterService().register(rRequest);
        assertFalse(registerResult.isSuccess());
    }
}