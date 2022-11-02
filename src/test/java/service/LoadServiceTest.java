package service;

import dao.DataAccessException;
import dao.Database;
import model.Event;
import model.Person;
import model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import request.LoadRequest;
import result.LoadResult;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class LoadServiceTest {

    @Test
    @DisplayName("Load Negative")
    void loadNeg() throws DataAccessException {
        Database database = new Database();
        database.clearDatabase();
        database.closeConnection(true);
        ArrayList<User> users = new ArrayList<>();
        ArrayList<Person> persons = new ArrayList<>();
        ArrayList<Event> events = new ArrayList<>();

        LoadRequest req = new LoadRequest(users, persons, events);

        LoadResult loadResult = new LoadService().load(req);

        assertEquals("I can't load data without data bro :/",loadResult.getMessage());

    }
    @Test
    @DisplayName("Load Positive")
    void loadPos() throws DataAccessException {
        Database database = new Database();
        database.clearDatabase();
        database.closeConnection(true);
        ArrayList<User> users = new ArrayList<>();
        ArrayList<Person> persons = new ArrayList<>();
        ArrayList<Event> events = new ArrayList<>();

        User user = new User("username", "password", "email", "first", "last", "m", "999");
        User userTwo = new User("user", "password", "email", "last", "first", "f", "9999");

        users.add(user);
        users.add(userTwo);

        Person p1 = new Person("myPerson", "bro", "first", "last", "gender", "f", "m", "s");
        Person p2 = new Person("notMyPerson", "names", "hello", "notMe", "female", "father", "mother", "spouse");

        persons.add(p1);
        persons.add(p2);

        Event eventOne = new Event("e1", "user", "1", Float.parseFloat("25.3"), Float.parseFloat("25.3"), "country", "city", "birth", 2022);
        Event eventTwo = new Event("e2", "user", "2", Float.parseFloat("25.3"), Float.parseFloat("25.3"), "country", "city", "birth", 2022);
        Event eventThree = new Event("e3", "user", "3", Float.parseFloat("25.3"), Float.parseFloat("25.3"), "country", "city", "birth", 2022);

        events.add(eventOne);
        events.add(eventTwo);
        events.add(eventThree);

        LoadRequest req = new LoadRequest(users, persons, events);

        LoadResult loadResult = new LoadService().load(req);

        String msg = "Successfully added 2 users, 2 persons, and 3 events to the database!";
        assertEquals(msg,loadResult.getMessage());
    }
}