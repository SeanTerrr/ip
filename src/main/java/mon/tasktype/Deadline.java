package mon.tasktype;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate deadline;
    public Deadline(String taskName, LocalDate deadline, Boolean isDone){
        super(taskName);
        this.deadline = deadline;
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
    }

    @Override
    public String convertToWriteFormat() {
        return "D | " + super.convertToWriteFormat() + " /by " + deadline + System.lineSeparator();
    }
}
