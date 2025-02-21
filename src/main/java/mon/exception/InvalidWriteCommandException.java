package mon.exception;

public class InvalidWriteCommandException extends Exception {
    public InvalidWriteCommandException(String commandData) {
        super("    Task could not be added from the data file. " +
                "Please check the following description for issues: " + commandData);
    }
}
