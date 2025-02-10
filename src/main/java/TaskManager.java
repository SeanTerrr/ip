public class TaskManager {
    private final Task[] taskList = new Task[100];
    private int numberOfTasks = 0;
    final String horizontalLine = "   _______________________________";

    public TaskManager() {
    }

    public int getNumberOfTasks() {
        return numberOfTasks;
    }

    public void printAllTasks() {
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < numberOfTasks; i++) {
            int numberCount = i + 1;
            System.out.println("    " + numberCount + "." + taskList[i]);
        }
    }

    public void markTaskAsDone(int taskId) throws MonException.InvalidTaskNumberException {
        if (taskId > numberOfTasks) {
            throw new MonException.InvalidTaskNumberException(numberOfTasks);
        } else {
            taskList[taskId-1].markAsDone();
            System.out.println(taskList[taskId-1]);
        }
    }

    public void unmarkTaskAsDone(int taskId) throws MonException.InvalidTaskNumberException {
        if (taskId > numberOfTasks) {
            throw new MonException.InvalidTaskNumberException(numberOfTasks);
        } else {
            taskList[taskId-1].unmarkAsDone();
            System.out.println(taskList[taskId-1]);
        }
    }

    public void decodeCommand(String command) {
        String[] commandArray = command.split(" ", 2);
        try {
            switch (commandArray[0]) {
            case "event":
                addEvent(commandArray[1]);
                break;
            case "deadline":
                addDeadline(commandArray[1]);
                break;
            case "todo":
                addTodo(commandArray[1]);
                break;
            case "unmark":
                unmarkTaskAsDone(Integer.parseInt(commandArray[1]));
                break;
            case "mark":
                markTaskAsDone(Integer.parseInt(commandArray[1]));
                break;
            default:
                throw new MonException.InvalidCommandException();
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void addTodo(String description) {
        taskList[numberOfTasks] = new ToDo(description);
        printAddedText(taskList[numberOfTasks++]);
    }

    public void addDeadline(String description) throws MonException.InvalidDeadlineException{
        String[] deadlineParts = description.split(" /by", 2);
        if (deadlineParts.length < 2) {
            throw new MonException.InvalidDeadlineException();
        }
        taskList[numberOfTasks] = new Deadline(deadlineParts[0],deadlineParts[1]);
        printAddedText(taskList[numberOfTasks++]);
    }

    public void addEvent(String description) throws MonException.InvalidEventException{
        String[] eventParts = description.split(" /from | /to", 3);
        if (eventParts.length < 3) {
            throw new MonException.InvalidEventException();
        }
        taskList[numberOfTasks] = new Event(eventParts[0],eventParts[1],eventParts[2]);
        printAddedText(taskList[numberOfTasks++]);
    }

    public void printAddedText(Task task){
        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + task);
        System.out.println("    Now you have " + numberOfTasks + " in the list.");
    }
}
