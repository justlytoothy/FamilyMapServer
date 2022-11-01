package service;

import dao.AuthTokenDAO;
import dao.DataAccessException;
import dao.Database;
import dao.EventDAO;
import model.Event;
import result.EventResult;

import java.util.ArrayList;

public class EventService {
    public EventResult getEvent(String auth, String params []) {
        if (params.length == 2) {
            return getOneEvent(auth,params);
        }
        else if (params.length == 1) {
            return getFamilyEvents(auth);
        }
        else {
            EventResult eventResult = new EventResult("Incorrect parameters provided",false);
            return eventResult;
        }
    }
    public EventResult getOneEvent(String auth, String params []) {
        EventResult eventResult;
        String ID = params[1];
        Database database = new Database();
        EventDAO eventDAO = database.getEventDAO();
        AuthTokenDAO authTokenDAO = database.getAuthTokenDAO();
        if (!authTokenDAO.realAuthToken(auth)) {
            eventResult = new EventResult("Error, Incorrect auth token",false);
            database.closeConnection(false);
            return eventResult;
        }
        if (!eventDAO.doesEventIDExist(ID)) {
            eventResult = new EventResult("Error, event does not exist",false);
            database.closeConnection(false);
            return eventResult;
        }
        try {
            Event event = eventDAO.find(ID);
            String uName = event.getAssociatedUsername();
            if (!uName.equals(authTokenDAO.getUsername(auth))) {
                eventResult = new EventResult("Error, event is not associated with current user",false);
                database.closeConnection(false);
                return eventResult;
            }
            eventResult = new EventResult(event);
            database.closeConnection(true);
            return eventResult;
        }
        catch (DataAccessException e) {
            e.printStackTrace();
            eventResult = new EventResult("Error, event does not exist",false);
            database.closeConnection(false);
            return eventResult;

        }

    }
    public EventResult getFamilyEvents(String auth) {
        EventResult eventResult;
        Database database = new Database();
        EventDAO eventDAO = database.getEventDAO();
        AuthTokenDAO authTokenDAO = database.getAuthTokenDAO();
        if (!authTokenDAO.realAuthToken(auth)) {
            eventResult = new EventResult("Error, Incorrect auth token",false);
            database.closeConnection(false);
            return eventResult;
        }
        try {
            ArrayList<Event> familyEvents = eventDAO.findFamilyEvents(authTokenDAO.getUsername(auth));
            eventResult = new EventResult();
            eventResult.setData(familyEvents);
            eventResult.setSuccess(true);
            database.closeConnection(true);
            return eventResult;
        }
        catch (DataAccessException e) {
            e.printStackTrace();
            eventResult = new EventResult("Error, event does not exist",false);
            database.closeConnection(false);
            return eventResult;
        }

    }
}
