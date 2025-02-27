package mon.tasktype;

public class Task {
    protected String taskName;
    protected Boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
    public String getTaskName() {
        return taskName;
    }

    public void markAsDone() {
        this.isDone = true;
        System.out.println("    Nice! Marked as Done:");
        System.out.print("      ");
    }

    public void unmarkAsDone() {
        this.isDone = false;
        System.out.println("    Oh no! Unmarked:");
        System.out.print("      ");
    }

    public Boolean getIsDone() {
        return isDone;
    }

    public String getStatusIcon(){
        return isDone ? "X" : " ";
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + taskName;
    }

    public String convertToWriteFormat() {
        String statusIcon = isDone ? "X" : "O";
        return statusIcon + " | " + taskName;
    }

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
}
