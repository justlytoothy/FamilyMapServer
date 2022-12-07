package service;

import dao.*;
import model.Person;
import model.User;
import result.PersonResult;

import java.util.ArrayList;

public class PersonService {
    public PersonResult getPerson(String auth, String params []) {
        if (params.length == 2) {
            return getOnePerson(auth,params);
        }
        else if (params.length == 1) {
            return getFamily(auth);
        }
        else {
            PersonResult personResult = new PersonResult("Incorrect parameters provided",false);
            return personResult;
        }
    }

    public PersonResult getOnePerson(String auth, String params []) {
        PersonResult personResult;
        String ID = params[1];
        Database database = new Database();
        PersonDAO personDAO = database.getPersonDAO();
        AuthTokenDAO authTokenDAO = database.getAuthTokenDAO();
        if (!authTokenDAO.realAuthToken(auth)) {
            personResult = new PersonResult("Error, Incorrect auth token",false);
            database.closeConnection(false);
            return personResult;
        }
        if (!personDAO.doesPersonIDExist(ID)) {
            personResult = new PersonResult("Error, person does not exist",false);
            database.closeConnection(false);
            return personResult;
        }
        try {
            Person person = personDAO.find(ID);
            String uName = person.getAssociatedUsername();
            if (!uName.equals(authTokenDAO.getUsername(auth))) {
                personResult = new PersonResult("Error, person is not associated with current user",false);
                database.closeConnection(false);
                return personResult;
            }
            personResult = new PersonResult(person);
            database.closeConnection(true);
            return personResult;
        }
        catch (DataAccessException e) {
            e.printStackTrace();
            personResult = new PersonResult("Error, person does not exist",false);
            database.closeConnection(false);
            return personResult;

        }

    }
    public PersonResult getFamily(String auth) {
        PersonResult personResult;
        Database database = new Database();
        PersonDAO personDAO = database.getPersonDAO();
        UserDAO userDAO = database.getUserDAO();
        AuthTokenDAO authTokenDAO = database.getAuthTokenDAO();
        if (!authTokenDAO.realAuthToken(auth)) {
            personResult = new PersonResult("Error, Incorrect auth token",false);
            database.closeConnection(false);
            return personResult;
        }
        try {
            personResult = new PersonResult();
            String username = authTokenDAO.getUsername(auth);
            ArrayList<Person> family = personDAO.findFamily(username);
            User user = userDAO.findUser(username);
            Person actualPerson = personDAO.find(user.getPersonID());
            personResult = new PersonResult(actualPerson);
            personResult.setData(family);
            personResult.setSuccess(true);
            database.closeConnection(true);
            return personResult;
        }
        catch (DataAccessException e) {
            e.printStackTrace();
            personResult = new PersonResult("Error, person does not exist",false);
            database.closeConnection(false);
            return personResult;
        }

    }
}

