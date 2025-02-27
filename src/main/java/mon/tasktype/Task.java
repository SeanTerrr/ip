package mon.tasktype;

/**
 * Represents a general task with a name and completion status.
 */
public class Task {
    protected String taskName;
    protected Boolean isDone;
    protected String identifier;
    /**
     * Creates a new Task.
     *
     * @param taskName The name of the task.
     * @param identifier The type of task
     */
    public Task(String taskName, String identifier) {
        this.taskName = taskName;
        this.isDone = false;
        this.identifier = identifier;
    }

    public String getTaskName() {
        return taskName;
    }

    /**
     * Marks the task as done and prints a confirmation message.
     */
    public void markAsDone() {
        this.isDone = true;
        System.out.println("    Nice! Marked as Done:");
        System.out.print("      ");
    }

    /**
     * Unmarks the task as done and prints a confirmation message.
     */
    public void unmarkAsDone() {
        this.isDone = false;
        System.out.println("    Oh no! Unmarked:");
        System.out.print("      ");
    }

    /**
     * Gets the status icon for the task.
     *
     * @return "X" if the task is done, otherwise " ".
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Gets the identifier for the task.
     *
     * @return "T", "D", "E" depending on the type of task
     */
    public String getIdentifier() {
        return identifier;
    }
    /**
     * Returns a string representation of the Task.
     * Format: [status] TaskName
     *
     * @return The formatted string representing the task.
     */
    @Override
    public String toString() {
        return "[" + identifier + "][" + getStatusIcon() + "] " + taskName;
    }

    /**
     * Converts the task into a format suitable for writing to a file.
     * Format: Status | TaskName
     *
     * @return The formatted string representation of the task for file storage.
     */
    public String convertToWriteFormat() {
        String statusIcon = isDone ? "X" : "O";
        return identifier + " | " + statusIcon + " | " + taskName;
    }
}
