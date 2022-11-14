package request;
import model.User;
import model.Event;
import model.Person;

import java.util.ArrayList;

/**
 * The request sent to clear and then load the new user person and event data into the database
 */
public class LoadRequest {
  /**
   * The array of users to be added
   */
  private ArrayList<User> users;
  /**
   * The array of people to be added
   */
  private ArrayList<Person> persons;
  /**
   * The array of events to be added
   */
  private ArrayList<Event> events;

  /**
   * Initializes all member variables for the request
   * @param users the array of users to be added
   * @param persons the array of person objects to be added
   * @param events the array of events to be added
   */
  public LoadRequest(ArrayList<User> users, ArrayList<Person> persons, ArrayList<Event> events) {
    this.users = users;
    this.persons = persons;
    this.events = events;
  }


  public ArrayList<User> getUsers() {
    return users;
  }

  public ArrayList<Person> getPersons() {
    return persons;
  }

  public ArrayList<Event> getEvents() {
    return events;
  }
}
