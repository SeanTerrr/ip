package mon;

public class MonException {
    public static class InvalidCommandException extends Exception {
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

    public static class InvalidEventException extends Exception {
        public InvalidEventException() {
            super("    Invalid event format. Use: event <task> /from <start> /to <end>");
        }
    }

    public static class InvalidDeadlineException extends Exception {
        public InvalidDeadlineException() {
            super("    Invalid deadline format. Use: deadline <task> /by <time>");
        }
    }

    public static class InvalidTaskNumberException extends Exception {
        public InvalidTaskNumberException(int numberOfTasks) {
            super("    Please enter a valid task ID. " +
                    "There are currently " + numberOfTasks + " items in the list.");
        }
    }

    public static class InvalidWriteCommandException extends Exception {
        public InvalidWriteCommandException(String commandData) {
            super("    Task could not be added from the data file. " +
                    "Please check the following description for issues: " + commandData);
        }
    }
}
