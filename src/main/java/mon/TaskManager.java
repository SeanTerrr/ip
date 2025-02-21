package mon;

import mon.exception.*;
import mon.tasktype.Deadline;
import mon.tasktype.Event;
import mon.tasktype.ToDo;
import java.util.ArrayList;

public class TaskManager {
    private final ArrayList<Task> taskList = new ArrayList<>();

    public TaskManager() {}
    public void decodeCommand(String command) {
        String[] commandArray = command.split(" ", 2);
        try {
            if (commandArray.length < 2){
                throw new InvalidCommandException();
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
            case "delete":
                deleteTask(Integer.parseInt(commandArray[1]));
                break;
            default:
                throw new InvalidCommandException();
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void printAllTasks() {
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            int numberCount = i + 1;
            System.out.println("    " + numberCount + "." + taskList.get(i));
        }
    }

    public void markTaskAsDone(int taskId) throws InvalidTaskNumberException {
        if (taskId > taskList.size()) {
            throw new InvalidTaskNumberException(taskList.size());
        } else {
            taskList.get(taskId-1).markAsDone();
            System.out.println(taskList.get(taskId-1));
        }
    }

    public void unmarkTaskAsDone(int taskId) throws InvalidTaskNumberException {
        if (taskId > taskList.size()) {
            throw new InvalidTaskNumberException(taskList.size());
        } else {
            taskList.get(taskId-1).unmarkAsDone();
            System.out.println(taskList.get(taskId-1));
        }
    }

    public void addTodo(String description, Boolean isDone, Boolean printText) {
        taskList.add(new ToDo(description, isDone));
        if (printText){
            printAddedText(taskList.get(taskList.size()-1));
        }
    }

    public void addDeadline(String description, Boolean isDone, Boolean printText)
            throws InvalidDeadlineException, InvalidWriteCommandException {
        String[] deadlineParts = description.split(" /by", 2);
        if (deadlineParts.length < 2) {
            if (printText) {
                throw new InvalidDeadlineException();
            }
            else {
                throw new InvalidWriteCommandException(description);
            }
        }

        //trim leading spaces in the array
        for (int i = 0; i < deadlineParts.length; i++) {
            deadlineParts[i] = deadlineParts[i].trim();
        }
        taskList.add(new Deadline(deadlineParts[0],deadlineParts[1], isDone));

        if (printText){
            printAddedText(taskList.get(taskList.size()-1));
        }
    }

    public void addEvent(String description, Boolean isDone, Boolean printText)
            throws InvalidEventException, InvalidWriteCommandException{

        String[] eventParts = description.split(" /from | /to", 3);
        if (eventParts.length < 3) {
            if (printText){
                throw new InvalidEventException();
            }
            else {
                throw new InvalidWriteCommandException(description);
            }
        }
        for (int i = 0; i < eventParts.length; i++) {
            eventParts[i] = eventParts[i].trim();
        }
        taskList.add(new Event(eventParts[0],eventParts[1],eventParts[2],isDone));

        if (printText) {
            printAddedText(taskList.get(taskList.size() - 1));
        }
    }

    public void printAddedText(Task task){
        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + task);
        System.out.println("    Now you have " + taskList.size() + " tasks in the list.");
    }

    public void deleteTask(int taskId) throws InvalidTaskNumberException {
        if (taskId > taskList.size()) {
            throw new InvalidTaskNumberException(taskList.size());
        }
        printDeletedText(taskList.get(taskId-1));
        taskList.remove(taskId-1);
        System.out.println("    Now you have " + taskList.size() + " tasks in the list.");
    }

    public void printDeletedText(Task task){
        System.out.println("    Got it. I've deleted this task:");
        System.out.println("      " + task);
    }

    public void executeByeCommand(MonFile monFile) {
        try {
            monFile.clearFileContent();
            for (Task task : taskList) {
                monFile.writeToFile(task);
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
