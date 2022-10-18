package dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import model.User;
import java.sql.Connection;


import javax.xml.crypto.Data;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest {
  private Database db;
  private User user;
  private UserDAO userDAO;

  @BeforeEach
  public void setUp() throws DataAccessException {
    db = new Database();
    user = new User("test","test","jake@notreal.com","Jake","Schilling","m","123456");
    Connection conn = db.getConnection();
    userDAO = new UserDAO(conn);
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
    db.openConnection();
  }

  @Test
  @DisplayName("Clear User")
  public void clear() throws DataAccessException {
    userDAO.insert(user);
    userDAO.clear();
    User check = userDAO.findUser(user.getUsername());
    assertNull(check);
  }
}