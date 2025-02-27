package mon.tasktype;

public class ToDo extends Task {
    public ToDo(String taskName, Boolean isDone){
        super(taskName);
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String convertToWriteFormat() {
        return "T | " + super.convertToWriteFormat() + System.lineSeparator();
    }
}
