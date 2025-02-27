package mon.exception;

/**
 * Exception thrown when an invalid command is entered.
 */
public class InvalidCommandException extends Exception {

    /**
     * Constructs an {@code InvalidCommandException} with a predefined error message.
     * The message provides guidance on correct command formats.
     */
    public InvalidCommandException() {
        super("    Invalid command. Please use the correct format:\n" +
                "\n    - mark <number> – Mark an item as completed" +
                "\n    - unmark <number> – Unmark a completed item" +
                "\n    - list – Show all items" +
                "\n    - todo <item> – Add a to-do item" +
                "\n    - deadline <task> /by <time> – Add a task with a deadline" +
                "\n    - event <task> /from <start> /to <end> – Add an event with a start and end time" +
                "\n    - schedule <date> – Displays all tasks scheduled for the specified date (format: YYYY-MM-DD)" +
                "\n    - find <keyword> - Findl all matching tasks" +
                "\n\n    Check your input and try again!");
    }
}
