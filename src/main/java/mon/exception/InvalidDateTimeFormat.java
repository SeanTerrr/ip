package mon.exception;

public class InvalidDateTimeFormat extends Exception {
    public InvalidDateTimeFormat() {
        super("    ERROR: Invalid date format.\n" +
                "    Expected formats:\n" +
                "      - YYYY-MM-DD for deadlines and schedule command (e.g., 2025-03-15)\n" +
                "      - YYYY-MM-DD'T'HH:mm:ss for events (e.g., 2025-03-15T14:30:00)\n" +
                "    Please check your input and try again.");
    }
}
