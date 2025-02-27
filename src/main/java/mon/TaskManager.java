package mon;

import mon.exception.*;
import mon.tasktype.Deadline;
import mon.tasktype.Event;
import mon.tasktype.Task;
import mon.tasktype.ToDo;
import java.util.ArrayList;

public class TaskManager {
    private Ui ui;
    private ArrayList<Task> taskList;

    public TaskManager() {
        taskList = new ArrayList<>();
        ui = new Ui();
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
            ui.printAddedText(taskList.get(taskList.size()-1),taskList);
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
            ui.printAddedText(taskList.get(taskList.size()-1),taskList);
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
            ui.printAddedText(taskList.get(taskList.size() - 1),taskList);
        }
    }

    public void deleteTask(int taskId) throws InvalidTaskNumberException {
        if (taskId > taskList.size()) {
            throw new InvalidTaskNumberException(taskList.size());
        }
        ui.printDeletedText(taskList.get(taskId-1));
        taskList.remove(taskId-1);
        ui.printCurrentTaskSize(taskList);
    }
    public void executeByeCommand(Storage monFile) {
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

    public void executeFindCommand(String keyword){
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : taskList) {
            if (task.getTaskName().toLowerCase().contains(keyword.toLowerCase())) {
                foundTasks.add(task);
            }
        }

        if (foundTasks.isEmpty()){
            System.out.println("    No matching tasks found!");
            return;
        }

        System.out.println("    Here are the matching tasks in your list:");
        for (int i = 0; i < foundTasks.size(); i++) {
            int numberCount = i + 1;
            System.out.println("    " + numberCount + "." + foundTasks.get(i));
        }
    }
}
