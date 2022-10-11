package service;

import result.FillResult;

/**
 * Performs fill operations and returns the results
 */
public class FillService {
  /**
   * Fills 4 generations of ancestor data for the currently logged-in user
   * @param username the username of the logged-in user
   * @return the result of filling in 4 generations of ancestor data
   */
  public FillResult fill (String username) {
    return null;
  }

  /**
   * Used when user provides the number of generations to fill,
   * fills that many generations of ancestor data for the logged-in user
   * @param username the logged-in users username
   * @param generations the number of generations to generate for the user
   * @return the results of filling in generations for the user
   */
  public FillResult fill (String username, int generations) {
    return null;
  }
}
