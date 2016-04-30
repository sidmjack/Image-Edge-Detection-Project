/**
 * Name: Sidney Jackson & Lawrence Wolf-Sonkin
 * Blackboard Login: sjacks85 & lwolfso1 & eheredi1
 * Course: Data Structures 600.226.02
 **/
/** Exception class for empty Queues.
 */
public class QueueEmptyException extends RuntimeException {

    /** Create a default exception object.
     */
    public QueueEmptyException() {
        super("ERROR: queue is empty, invalid operation");
    }

    /** Create a specific exception object.
     *  @param err the error message
     */
    public QueueEmptyException(String err) {
        super(err);
    }
}
