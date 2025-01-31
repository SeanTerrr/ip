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
            String checkBox;
            if (taskList[i].getIsDone()){
                checkBox = "[X]";
            }
            else {
                checkBox = "[ ]";
            }
            System.out.println("    " + numberCount + "." + checkBox + " " + taskList[i].getTaskName());
        }
    }

    public void markTaskAsDone(int taskId) {
        if (taskId > numberOfTasks) {
            System.out.println("    Please enter a valid task ID");
        }
        else {
            taskList[taskId-1].setIsDone(true);
            System.out.println("    Nice! Marked as Done:\n      [X] " + taskList[taskId-1].getTaskName());
        }
    }

    public void unmarkTaskAsDone(int taskId) {
        if (taskId > numberOfTasks) {
            System.out.println("    Please enter a valid task ID");
        }
        else {
            taskList[taskId-1].setIsDone(false);
            System.out.println("    Oh no! Unmarked:\n      [ ] " +  taskList[taskId-1].getTaskName());
        }
    }
}
