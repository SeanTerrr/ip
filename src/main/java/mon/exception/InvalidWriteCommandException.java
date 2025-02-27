package mon.exception;

/**
 * Exception thrown when a task could not be added from the data file due to an invalid command format.
 */
public class InvalidWriteCommandException extends Exception {

    /**
     * Constructs an {@code InvalidWriteCommandException} with a message indicating the problematic command data.
     * The message helps the user identify issues with the command data that caused the task addition to fail.
     *
     * @param commandData The data from the file that caused the issue.
     */
    public InvalidWriteCommandException(String commandData) {
        super("    Task could not be added from the data file. " +
                "Please check the following description for issues: " + commandData);
    }
}
