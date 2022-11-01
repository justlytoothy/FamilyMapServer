package service;
import dao.*;
import model.Event;
import model.Person;
import model.User;
import request.LoadRequest;
import result.ClearResult;
import result.LoadResult;
import java.util.ArrayList;

/**
 * A service that performs load operations on the database
 */
public class LoadService {
    /**
     * Loads all the events, people, and users from the request into their respective tables in the database
     * @param request a request containing the people, events, and users to be added
     * @return the results of trying to load all of this information into the database
     */
    public LoadResult load(LoadRequest request) {
        //first clear the database
        LoadResult result;
        ClearResult clearResult = new ClearService().clear();
        if (!clearResult.isSuccess()) {
            result = new LoadResult("Unable to clear database to perform load operation :(",false);
            return result;
        }
        Database database = new Database();
        UserDAO userDAO = database.getUserDAO();
        PersonDAO personDAO = database.getPersonDAO();
        EventDAO eventDAO = database.getEventDAO();
        ArrayList<User> users = request.getUsers();
        ArrayList<Person> people = request.getPersons();
        ArrayList<Event> events = request.getEvents();
        if (users.isEmpty() && people.isEmpty() && events.isEmpty()) {
            result = new LoadResult("I can't load data without data bro :/",false);
            database.closeConnection(false);
            return result;
        }
        for (User user : users) {
            try {
                userDAO.insert(user);
            }
            catch(DataAccessException e) {
                e.printStackTrace();
                result = new LoadResult("Error while loading users :(",false);
                database.closeConnection(false);
                return result;
            }
        }
        for (Person person : people) {
            try {
                personDAO.insert(person);
            }
            catch(DataAccessException e) {
                e.printStackTrace();
                result = new LoadResult("Error while loading people :(",false);
                database.closeConnection(false);
                return result;
            }
        }
        for (Event event : events) {
            try {
                eventDAO.insert(event);
            }
            catch(DataAccessException e) {
                e.printStackTrace();
                result = new LoadResult("Error while loading events :(",false);
                database.closeConnection(false);
                return result;
            }
        }
        result = new LoadResult("Successfully added " + users.size() + " users, " + people.size() + " persons, and " + events.size() + " events to the database!",true);
        database.closeConnection(true);
        return result;
    }
}
