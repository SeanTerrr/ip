public class Deadline extends Task{
    private String deadline;
    public Deadline(String taskName, String deadline){
        super(taskName);
        this.deadline = deadline;
        this.isDone = false;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + deadline + ")";
    }
}
