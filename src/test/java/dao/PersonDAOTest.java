package dao;

import model.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

class PersonDAOTest {

  private Database db;
  private Person person;
  private PersonDAO personDAO;

  @BeforeEach
  public void setUp() throws DataAccessException {
    db = new Database();
    person = new Person("test","12345","Jake","Schilling","m",null,"123456",null);
    Connection conn = db.getConnection();
    personDAO = new PersonDAO(conn);
    personDAO.clear();
  }

  @AfterEach
  public void tearDown() {
    db.closeConnection(false);
  }

  @Test
  @DisplayName("Insert Person Success")
  public void insertPass() throws DataAccessException {
    personDAO.insert(person);
//    String sql = "SELECT * FROM Person;";
//    Connection conn = db.getConnection();
//    try(PreparedStatement stmt = conn.prepareStatement(sql)) {
//      ResultSet rs = stmt.executeQuery();
//      ResultSetMetaData rsmd = rs.getMetaData();
//      System.out.println("querying SELECT * FROM XXX");
//      int columnsNumber = rsmd.getColumnCount();
//      while (rs.next()) {
//        for (int i = 1; i <= columnsNumber; i++) {
//          if (i > 1) System.out.print(",  ");
//          String columnValue = rs.getString(i);
//          System.out.print(columnValue + " " + rsmd.getColumnName(i));
//        }
//        System.out.println("");
//      }
//    }
//    catch(SQLException e) {
//      e.printStackTrace();
//      throw new DataAccessException("bruh");
//    }
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
    db.closeConnection(false);
    assertThrows(DataAccessException.class, () -> personDAO.find(person.getPersonID()));
    db.openConnection();
  }

  @Test
  @DisplayName("Clear Person")
  public void clear() throws DataAccessException {
    personDAO.insert(person);
    personDAO.clear();
    Person check = personDAO.find(person.getPersonID());
    assertNull(check);
  }
}