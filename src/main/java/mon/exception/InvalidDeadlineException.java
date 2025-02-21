package mon.exception;

public class InvalidDeadlineException extends Exception {
    public InvalidDeadlineException() {
        super("    Invalid deadline format. Use: deadline <task> /by <time>");
    }
}
