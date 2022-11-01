package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import static java.util.UUID.randomUUID;

/**
 * The database connection class
 */
public class Database {
    private Connection conn;
    private UserDAO userDAO;
    private EventDAO eventDAO;
    private PersonDAO personDAO;
    private AuthTokenDAO authTokenDAO;
    public Database() {
        try {
            if (conn == null) {
                openConnection();
            }
            userDAO = new UserDAO(conn);
            eventDAO = new EventDAO(conn);
            personDAO = new PersonDAO(conn);
            authTokenDAO = new AuthTokenDAO(conn);
        }
        catch(DataAccessException e) {
            e.printStackTrace();
        }
    }

    // Whenever we want to make a change to our database we will have to open a connection and use
    // Statements created by that connection to initiate transactions

    /**
     * Opens the connection to the database
     * @return the new connection
     * @throws DataAccessException if unable to connect
     */
    public void openConnection() throws DataAccessException {
        try {
            // The Structure for this Connection is driver:language:path
            // The path assumes you start in the root of your project unless given a full file path
            final String CONNECTION_URL = "jdbc:sqlite:familymap.sqlite";

            // Open a database connection to the file given in the path
            conn = DriverManager.getConnection(CONNECTION_URL);

            // Start a transaction
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Unable to open connection to database");
        }
    }

    /**
     * Either returns the current connection or opens new based on whether connection is open or closed
     * @return current connection or new one
     * @throws DataAccessException if unable to open connection
     */
    public Connection getConnection() throws DataAccessException {
        if (conn == null) {
            openConnection();
        }
        return conn;
    }

    // When we are done manipulating the database it is important to close the connection. This will
    // end the transaction and allow us to either commit our changes to the database (if true is passed in)
    // or rollback any changes that were made before we encountered a potential error (if false is passed in).

    // IMPORTANT: IF YOU FAIL TO CLOSE A CONNECTION AND TRY TO REOPEN THE DATABASE THIS WILL CAUSE THE
    // DATABASE TO LOCK. YOUR CODE MUST ALWAYS CLOSE THE DATABASE NO MATTER WHAT ERRORS
    // OR PROBLEMS ARE ENCOUNTERED

    /**
     * Closes the current connection
     * @param commit whether we need to commit or rollback current changes
     */
    public void closeConnection(boolean commit) {
        try {
            if (commit) {
                // This will commit the changes to the database
                conn.commit();
            } else {
                // If we find out something went wrong, pass a false into closeConnection and this
                // will rollback any changes we made during this connection
                conn.rollback();
            }
            conn.close();
            conn = null;
        } catch (SQLException e) {
            // If you get here there are probably issues with your code and/or a connection is being left open
            e.printStackTrace();
        }
    }
    public String genToken() {
        String token = randomUUID().toString();
        Date date = new Date();
        token = token.substring(0,8) + date.getTime();
        return token;
    }


    public UserDAO getUserDAO() {
        return userDAO;
    }

    public EventDAO getEventDAO() {
        return eventDAO;
    }

    public PersonDAO getPersonDAO() {
        return personDAO;
    }

    public AuthTokenDAO getAuthTokenDAO() {
        return authTokenDAO;
    }
}

