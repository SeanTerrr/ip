package mon;

import mon.tasktype.Deadline;
import mon.tasktype.Event;
import mon.tasktype.ToDo;

import javax.imageio.IIOException;

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
            if (commandArray.length < 2){
                throw new MonException.InvalidCommandException();
            }
            switch (commandArray[0]) {
            case "event":
                addEvent(commandArray[1],false,true);
                break;
            case "deadline":
                addDeadline(commandArray[1],false,true);
                break;
            case "todo":
                addTodo(commandArray[1],false,true);
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

    public void addTodo(String description, Boolean isDone, Boolean printText) {
        taskList[numberOfTasks] = new ToDo(description, isDone);

        if (printText) {
            printAddedText(taskList[numberOfTasks]);
        }
        numberOfTasks++;
    }

    public void addDeadline(String description, Boolean isDone, Boolean printText)
            throws MonException.InvalidDeadlineException, MonException.InvalidWriteCommandException {
        String[] deadlineParts = description.split(" /by", 2);
        if (deadlineParts.length < 2) {
            if (printText) {
                throw new MonException.InvalidDeadlineException();
            }
            else {
                throw new MonException.InvalidWriteCommandException(description);
            }
        }

        //trim leading spaces in the array
        for (int i = 0; i < deadlineParts.length; i++) {
            deadlineParts[i] = deadlineParts[i].trim();
        }
        taskList[numberOfTasks] = new Deadline(deadlineParts[0],deadlineParts[1], isDone);

        if (printText){
            printAddedText(taskList[numberOfTasks]);
        }
        numberOfTasks++;
    }

    public void addEvent(String description, Boolean isDone, Boolean printText)
            throws MonException.InvalidEventException, MonException.InvalidWriteCommandException{

        String[] eventParts = description.split(" /from | /to", 3);
        if (eventParts.length < 3) {
            if (printText){
                throw new MonException.InvalidEventException();
            }
            else {
                throw new MonException.InvalidWriteCommandException(description);
            }
        }
        for (int i = 0; i < eventParts.length; i++) {
            eventParts[i] = eventParts[i].trim();
        }
        taskList[numberOfTasks] = new Event(eventParts[0],eventParts[1],eventParts[2], isDone);

        if (printText){
            printAddedText(taskList[numberOfTasks]);
        }
        numberOfTasks++;
    }

    public void printAddedText(Task task){
        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + task);
        System.out.println("    Now you have " + numberOfTasks + " in the list.");
    }

    public void executeByeCommand(MonFile monFile) {
        try {
            monFile.clearFileContent();
            for (int i = 0; i < numberOfTasks; i++){
                monFile.writeToFile(taskList[i]);
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
