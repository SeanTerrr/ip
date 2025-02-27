package mon.tasktype;
/**
 * Represents a task with a deadline.
 * Inherits from the {@link Task} class.
 */
public class Deadline extends Task {
    private String deadline;

    /**
     * Creates a new Deadline task.
     *
     * @param taskName The name of the task.
     * @param deadline The due date or deadline for the task.
     * @param isDone   Whether the task is completed (true) or not (false).
     */
    public Deadline(String taskName, String deadline, Boolean isDone){
        super(taskName);
        this.deadline = deadline;
        this.isDone = isDone;
    }

    /**
     * Returns a string representation of the Deadline task.
     * Format: [D][status] TaskName (by: deadline)
     *
     * @return The formatted string representing the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }

    /**
     * Converts the task into a format suitable for writing to a file.
     * Format: D | [status] | TaskName /by deadline
     *
     * @return The formatted string representation of the task for file storage.
     */
    @Override
    public String convertToWriteFormat() {
        return "D | " + super.convertToWriteFormat() + " /by " + deadline + System.lineSeparator();
    }
}
