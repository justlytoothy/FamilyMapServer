package result;
import model.Event;
/**
 * Contains the results of trying to find all of the events for all of the family members of the current user
 */
public class AllFamilyEventsResult {
    private Event[] data;
    private boolean success;
    private String message;

    /**
     * Initializes the array of the found family events and a success value of true
     * @param data the found family events
     * @param success true in this case
     */
    public AllFamilyEventsResult(Event[] data, boolean success) {
        this.data = data;
        this.success = success;
    }
    /**
     * Constructor used in case of failure, initializes message describing error and bool of false
     * @param success false in this case
     * @param message the error message describing the error
     */
    public AllFamilyEventsResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }


    public Event[] getData() {
        return data;
    }

    public void setData(Event[] data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
