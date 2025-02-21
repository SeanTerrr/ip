package mon.exception;

public class InvalidCommandException extends Exception {
    public InvalidCommandException() {
        super("    Invalid command. Please use the correct format:\n" +
                "\n    - mark <number> – Mark an item as completed" +
                "\n    - unmark <number> – Unmark a completed item" +
                "\n    - list – Show all items" +
                "\n    - todo <item> – Add a to-do item" +
                "\n    - deadline <task> /by <time> – Add a task with a deadline" +
                "\n    - event <task> /from <start> /to <end> – Add an event with a start and end time" +
                "\n\n    Check your input and try again!");
    }
}
