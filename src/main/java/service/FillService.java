package service;

import dao.*;
import generator.Generator;
import model.Event;
import model.Person;
import model.User;
import result.FillResult;

import javax.xml.crypto.Data;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Random;


/**
 * Performs fill operations and returns the results
 */
public class FillService {
  Generator generator;
  private int DEFAULT_GENERATIONS = 4;
  private int CURRENT_YEAR = 2022;
  private int peopleAdded = 0;
  private int eventsAdded = 0;
  private int totalGenerations = 0;
  private Random rand = new Random();

  public FillService() {
      generator = new Generator();
  }



  /**
   * Used to fill user,
   * fills that many generations of ancestor data for the logged-in user
   * @param params the parsed params array from handler
   * @return the results of filling in generations for the user
   */
  public FillResult fill (String params []) {
    FillResult result = new FillResult();
    int generations = DEFAULT_GENERATIONS;
    if (params.length == 3) {
      if (!isInteger(params[2])) {
        result.setMessage("error:Invalid int param. Try again :(");
        result.setSuccess(false);
        return result;
      }
      else if (Integer.parseInt(params[2]) < 0) {
        result.setMessage("error:Number of generations cannot be less than 0. :(");
        result.setSuccess(false);
        return result;
      }
      else {
        generations = Integer.parseInt(params[2]);
      }
    }
    else if (params.length != 2) {
      result.setMessage("error: Invalid Parameters passed. Please try again :(");
      result.setSuccess(false);
      return result;
    }
    Database database = new Database();
    UserDAO userDAO =database.getUserDAO();

    String username = params[1];
    if (!userDAO.userExists(username)) {
      result.setMessage("error: User does not exist :(");
      result.setSuccess(false);
      database.closeConnection(false);
      return result;
    }
    database.closeConnection(true);

    return fillHelper(username,generations);
  }
  public FillResult fillHelper(String username, int generations) {
    peopleAdded = 0;
    totalGenerations = generations;
    eventsAdded = 0;
    FillResult result = new FillResult();
    Database database = new Database();
    PersonDAO personDAO = database.getPersonDAO();
    EventDAO eventDAO = database.getEventDAO();
    UserDAO userDAO = database.getUserDAO();
    try {
      User currentUser = userDAO.findUser(username);
      Person currentPerson = new Person(username,currentUser.getPersonID(), currentUser.getFirstName(), currentUser.getLastName(), currentUser.getGender(), null, null, null);
      personDAO.deleteByUsername(username);
      eventDAO.deleteByUsername(username);
      Generator.Location marriageLocation = generator.getRandomLocation();
      makeParents(database,currentPerson,marriageLocation,generations);
      result.setSuccess(true);
      result.setMessage("Successfully added " + peopleAdded + " persons and " + eventsAdded + " events to the database!");
      database.closeConnection(true);
    }
    catch(DataAccessException| SQLException e) {
      e.printStackTrace();
      database.closeConnection(false);
      result.setSuccess(false);
      result.setMessage("Error when trying to access database. :(");
    }
    return result;
  }
  public void makeParents(Database database, Person currentPerson, Generator.Location marriageLocation, int generationsLeft) throws SQLException {
    int birthYear = CURRENT_YEAR - (30 * (totalGenerations + 1 - generationsLeft));

    createEvents(database, currentPerson, currentPerson.getAssociatedUsername(), birthYear, marriageLocation);
    PersonDAO personDAO = database.getPersonDAO();
    try {
      if (generationsLeft > 0) {
        Person father=createFather(database, currentPerson.getAssociatedUsername());
        Person mother=createMother(database, currentPerson.getAssociatedUsername());

        mother.setSpouseID(father.getPersonID());
        father.setSpouseID(mother.getPersonID());

        marriageLocation=generator.getRandomLocation();
        makeParents(database, mother, marriageLocation, generationsLeft - 1);
        makeParents(database, father, marriageLocation, generationsLeft - 1);

        currentPerson.setMotherID(mother.getPersonID());
        currentPerson.setFatherID(father.getPersonID());
      }

      personDAO.insert(currentPerson);
      peopleAdded++;

      return;
    }
    catch(DataAccessException e) {
      e.printStackTrace();
    }
  }

  public Person createMother(Database database, String associatedUsername) {
    String personID = database.genToken();
    String firstName = generator.getRandomFName();
    String lastName = generator.getRandomSName();
    String gender = "f";

    return new Person(associatedUsername,personID, firstName, lastName, gender, null, null, null);
  }

  public Person createFather(Database database, String associatedUsername) {
    String personID = database.genToken();
    String firstName = generator.getRandomMName();
    String lastName = generator.getRandomSName();
    String gender = "m";

    return new Person(associatedUsername, personID, firstName, lastName, gender, null, null, null);
  }
  public void createEvents(Database database, Person currentPerson, String associatedUsername, int birthYear, Generator.Location marriageLocation) throws SQLException {
    String personID = currentPerson.getPersonID();
    EventDAO eventDAO =database.getEventDAO();

    String birthID = database.genToken();
    Generator.Location birthLocation = generator.getRandomLocation();
    try {
      Event birth=new Event(birthID, associatedUsername, personID, birthLocation.getLatitude(), birthLocation.getLongitude(),
              birthLocation.getCountry(), birthLocation.getCity(), "Birth", birthYear);
      eventDAO.insert(birth);
      ++eventsAdded;


      String baptismID=database.genToken();
      Generator.Location baptismLocation=generator.getRandomLocation();
      Event baptism=new Event(baptismID, associatedUsername, personID, baptismLocation.getLatitude(), baptismLocation.getLongitude(),
              baptismLocation.getCountry(), baptismLocation.getCity(), "Baptism", birthYear + 8);
      eventDAO.insert(baptism);
      ++eventsAdded;

      String marriageID=database.genToken();
      Event marriage = new Event(marriageID, associatedUsername, personID, marriageLocation.getLatitude(), marriageLocation.getLongitude(),
              marriageLocation.getCountry(), marriageLocation.getCity(), "Marriage", birthYear + 24);
      eventDAO.insert(marriage);
      ++eventsAdded;

      if ((birthYear + 50) < CURRENT_YEAR) { // realistic death of user's mother required in tests so have deaeth sometime over 50 instead of 72...
        String deathID=database.genToken();
        Generator.Location deathLocation=generator.getRandomLocation();
        Event death=new Event(deathID, associatedUsername, personID, deathLocation.getLatitude(), deathLocation.getLongitude(),
                deathLocation.getCountry(), deathLocation.getCity(), "Death", birthYear + 50);
        eventDAO.insert(death);
        ++eventsAdded;
      }
    }
    catch(DataAccessException e) {
      e.printStackTrace();
    }
  }


  public int getPeopleAdded() {
    return peopleAdded;
  }

  public void setPeopleAdded(int peopleAdded) {
    this.peopleAdded=peopleAdded;
  }

  public int getEventsAdded() {
    return eventsAdded;
  }

  public void setEventsAdded(int eventsAdded) {
    this.eventsAdded=eventsAdded;
  }
  public boolean isInteger(String str) {
    try {
      int x = Integer.parseInt(str);
    }
    catch (NumberFormatException | NullPointerException nfe) {
      return false;
    }
    return true;
  }
}
