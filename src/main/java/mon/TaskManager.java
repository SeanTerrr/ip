package mon;

import mon.tasktype.Deadline;
import mon.tasktype.Event;
import mon.tasktype.ToDo;
import java.util.ArrayList;

public class TaskManager {
    private final ArrayList<Task> taskList = new ArrayList<Task>();
    final String horizontalLine = "   _______________________________";

    public TaskManager() {
    }

    public int getNumberOfTasks() {
        return taskList.size();
    }

    public void decodeCommand(String command) {
        String[] commandArray = command.split(" ", 2);
        try {
            if (commandArray.length < 2){
                throw new MonException.InvalidCommandException();
            }

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
            case "delete":
                deleteTask(Integer.parseInt(commandArray[1]));
                break;
            default:
                throw new MonException.InvalidCommandException();
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

    public void markTaskAsDone(int taskId) throws MonException.InvalidTaskNumberException {
        if (taskId > taskList.size()) {
            throw new MonException.InvalidTaskNumberException(taskList.size());
        } else {
            taskList.get(taskId-1).markAsDone();
            System.out.println(taskList.get(taskId-1));
        }
    }

    public void unmarkTaskAsDone(int taskId) throws MonException.InvalidTaskNumberException {
        if (taskId > taskList.size()) {
            throw new MonException.InvalidTaskNumberException(taskList.size());
        } else {
            taskList.get(taskId-1).unmarkAsDone();
            System.out.println(taskList.get(taskId-1));
        }
    }

    public void addTodo(String description) {
        taskList.add(new ToDo(description));
        printAddedText(taskList.get(taskList.size()-1));
    }

    public void addDeadline(String description) throws MonException.InvalidDeadlineException{
        String[] deadlineParts = description.split(" /by", 2);
        if (deadlineParts.length < 2) {
            throw new MonException.InvalidDeadlineException();
        }
        taskList.add(new Deadline(deadlineParts[0],deadlineParts[1]));
        printAddedText(taskList.get(taskList.size()-1));
    }

    public void addEvent(String description) throws MonException.InvalidEventException{
        String[] eventParts = description.split(" /from | /to", 3);
        if (eventParts.length < 3) {
            throw new MonException.InvalidEventException();
        }
        taskList.add(new Event(eventParts[0],eventParts[1],eventParts[2]));
        printAddedText(taskList.get(taskList.size()-1));
    }

    public void printAddedText(Task task){
        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + task);
        System.out.println("    Now you have " + taskList.size() + " tasks in the list.");
    }

    public void deleteTask(int taskId) throws MonException.InvalidTaskNumberException {
        if (taskId > taskList.size()) {
            throw new MonException.InvalidTaskNumberException(taskList.size());
        }
        printDeletedText(taskList.get(taskId-1));
        taskList.remove(taskId-1);
        System.out.println("    Now you have " + taskList.size() + " tasks in the list.");
    }

    public void printDeletedText(Task task){
        System.out.println("    Got it. I've deleted this task:");
        System.out.println("      " + task);
    }
}
