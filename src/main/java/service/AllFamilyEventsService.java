package service;

import result.AllFamilyEventsResult;

/**
 * Performs finding of all family events operations and returns the results
 */
public class AllFamilyEventsService {
    /**
     * First finds the family members of the logged-in user from auth token
     * Then gets all the events for all of those family members
     * @param authtoken the auth token of the logged-in user
     * @return the result of trying to get all events from all family members
     */
    public AllFamilyEventsResult find (String authtoken) {
        return null;
    }

}
