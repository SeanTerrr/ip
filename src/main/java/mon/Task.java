package mon;

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
}
