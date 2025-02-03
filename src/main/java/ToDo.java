public class ToDo extends Task{
    public ToDo(String taskName){
        super(taskName);
        isDone = false;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() + " ";
    }
}
