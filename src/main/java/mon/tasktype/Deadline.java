package mon.tasktype;

import mon.Task;

public class Deadline extends Task {
    private String deadline;
    public Deadline(String taskName, String deadline, Boolean isDone){
        super(taskName);
        this.deadline = deadline;
        this.isDone = isDone;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }

    @Override
    public String convertToWriteFormat() {
        return "D | " + super.convertToWriteFormat() + " /by " + deadline + System.lineSeparator();
    }
}
