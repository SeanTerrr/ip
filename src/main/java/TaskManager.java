public class TaskManager {
    private final Task[] taskList = new Task[100];
    private int numberOfTasks = 0;

    public TaskManager() {
    }

    public int getNumberOfTasks() {
        return numberOfTasks;
    }

    public void incrementNumberOfTasks() {
        numberOfTasks++;
    }

    public void addTask(Task task) {
        taskList[getNumberOfTasks()] = task;
        incrementNumberOfTasks();
        System.out.println("    added: " + task.getTaskName());
    }

    public void printTasks() {
        for (int i = 0; i < numberOfTasks; i++) {
            int numberCount = i + 1;
            System.out.println("    " + numberCount + ". " + taskList[i].getTaskName());
        }
    }
}
