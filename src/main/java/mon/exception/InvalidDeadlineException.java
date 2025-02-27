package mon.exception;

/**
 * Exception thrown when an invalid deadline format is provided.
 */
public class InvalidDeadlineException extends Exception {

    /**
     * Constructs an {@code InvalidDeadlineException} with a predefined error message.
     * The message provides guidance on the correct format for deadlines.
     */
    public InvalidDeadlineException() {
        super("    Invalid deadline format. Use: deadline <task> /by <time>");
    }
}
