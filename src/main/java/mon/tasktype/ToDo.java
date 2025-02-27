package mon.tasktype;

/**
 * Represents a simple to-do task without a specific deadline or event time.
 * Inherits from the {@link Task} class.
 */
public class ToDo extends Task {

    /**
     * Creates a new ToDo task.
     *
     * @param taskName The name of the to-do task.
     * @param isDone   Whether the task is completed (true) or not (false).
     */
    public ToDo(String taskName, Boolean isDone) {
        super(taskName,"T");
        this.isDone = isDone;
    }

    /**
     * Converts the ToDo task into a format suitable for writing to a file.
     * Format: T | [status] | TaskName
     *
     * @return The formatted string representation of the task for file storage.
     */
    @Override
    public String convertToWriteFormat() {
        return super.convertToWriteFormat() + System.lineSeparator();
    }
}
