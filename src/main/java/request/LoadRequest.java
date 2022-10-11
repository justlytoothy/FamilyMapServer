package request;
import model.User;
import model.Event;
import model.Person;

/**
 * The request sent to clear and then load the new user person and event data into the database
 */
public class LoadRequest {
  /**
   * The array of users to be added
   */
  private User[] users;
  /**
   * The array of people to be added
   */
  private Person[] persons;
  /**
   * The array of events to be added
   */
  private Event[] events;

  /**
   * Initializes all member variables for the request
   * @param users the array of users to be added
   * @param persons the array of person objects to be added
   * @param events the array of events to be added
   */
  public LoadRequest(User[] users, Person[] persons, Event[] events) {
    this.users=users;
    this.persons=persons;
    this.events=events;
  }
}
