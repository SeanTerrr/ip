package mon.tasktype;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate deadline;

    /**
     * Creates a new Deadline task.
     *
     * @param taskName The name of the task.
     * @param deadline The due date or deadline for the task.
     * @param isDone   Whether the task is completed (true) or not (false).
     */
    public Deadline(String taskName, LocalDate deadline, Boolean isDone){
        super(taskName,"D");
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
        return super.toString() + " (by: " + deadline.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
    }

    /**
     * Converts the task into a format suitable for writing to a file.
     * Format: D | [status] | TaskName /by deadline
     *
     * @return The formatted string representation of the task for file storage.
     */
    @Override
    public String convertToWriteFormat() {
        return super.convertToWriteFormat() + " /by " + deadline + System.lineSeparator();
    }
}
