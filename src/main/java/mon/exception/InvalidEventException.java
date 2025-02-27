package mon.exception;

/**
 * Exception thrown when an invalid event format is provided.
 */
public class InvalidEventException extends Exception {

    /**
     * Constructs an {@code InvalidEventException} with a predefined error message.
     * The message provides guidance on the correct format for events.
     */
    public InvalidEventException() {
        super("    Invalid event format. Use: event <task> /from <start> /to <end>");
    }
}
