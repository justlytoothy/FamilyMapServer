package service;
import request.LoadRequest;
import result.LoadResult;

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
        return null;
    }
}
