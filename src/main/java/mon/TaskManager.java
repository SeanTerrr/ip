package mon;

import mon.exception.*;
import mon.tasktype.Deadline;
import mon.tasktype.Event;
import mon.tasktype.Task;
import mon.tasktype.ToDo;

import java.util.ArrayList;

/**
 * The TaskManager class manages the list of tasks, providing methods to add, delete, mark, unmark, and search for tasks.
 * It interacts with the UI to print updates and task statuses.
 */
public class TaskManager {
    private Ui ui;
    private ArrayList<Task> taskList;

    /**
     * Constructs a TaskManager object, initializing the task list and UI.
     */
    public TaskManager() {
        taskList = new ArrayList<>();
        ui = new Ui();
    }

    /**
     * Prints all the tasks currently in the task list.
     */
    public void printAllTasks() {
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            int numberCount = i + 1;
            System.out.println("    " + numberCount + "." + taskList.get(i));
        }
    }

    /**
     * Marks a specific task as done based on its task ID.
     *
     * @param taskId The ID of the task to mark as done.
     * @throws InvalidTaskNumberException If the task ID is invalid (greater than the size of the task list).
     */
    public void markTaskAsDone(int taskId) throws InvalidTaskNumberException {
        if (taskId > taskList.size()) {
            throw new InvalidTaskNumberException(taskList.size());
        } else {
            taskList.get(taskId-1).markAsDone();
            System.out.println(taskList.get(taskId-1));
        }
    }

    /**
     * Unmarks a specific task as done based on its task ID.
     *
     * @param taskId The ID of the task to unmark as done.
     * @throws InvalidTaskNumberException If the task ID is invalid (greater than the size of the task list).
     */
    public void unmarkTaskAsDone(int taskId) throws InvalidTaskNumberException {
        if (taskId > taskList.size()) {
            throw new InvalidTaskNumberException(taskList.size());
        } else {
            taskList.get(taskId-1).unmarkAsDone();
            System.out.println(taskList.get(taskId-1));
        }
    }

    /**
     * Adds a new ToDo task to the task list.
     *
     * @param description The description of the ToDo task.
     * @param isDone The status of the task (done or not).
     * @param printText Whether to print a confirmation message after adding the task.
     */
    public void addTodo(String description, Boolean isDone, Boolean printText) {
        taskList.add(new ToDo(description, isDone));
        if (printText){
            ui.printAddedText(taskList.get(taskList.size()-1),taskList);
        }
    }

    /**
     * Adds a new Deadline task to the task list.
     *
     * @param description The description of the Deadline task.
     * @param isDone The status of the task (done or not).
     * @param printText Whether to print a confirmation message after adding the task.
     * @throws InvalidDeadlineException If the deadline format is invalid.
     * @throws InvalidWriteCommandException If the write command is invalid.
     */
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

        // Trim leading spaces in the array
        for (int i = 0; i < deadlineParts.length; i++) {
            deadlineParts[i] = deadlineParts[i].trim();
        }
        taskList.add(new Deadline(deadlineParts[0],deadlineParts[1], isDone));

        if (printText){
            ui.printAddedText(taskList.get(taskList.size()-1),taskList);
        }
    }

    /**
     * Adds a new Event task to the task list.
     *
     * @param description The description of the Event task.
     * @param isDone The status of the task (done or not).
     * @param printText Whether to print a confirmation message after adding the task.
     * @throws InvalidEventException If the event format is invalid.
     * @throws InvalidWriteCommandException If the write command is invalid.
     */
    public void addEvent(String description, Boolean isDone, Boolean printText)
            throws InvalidEventException, InvalidWriteCommandException {

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

    /**
     * Deletes a specific task from the task list based on its task ID.
     *
     * @param taskId The ID of the task to delete.
     * @throws InvalidTaskNumberException If the task ID is invalid (greater than the size of the task list).
     */
    public void deleteTask(int taskId) throws InvalidTaskNumberException {
        if (taskId > taskList.size()) {
            throw new InvalidTaskNumberException(taskList.size());
        }
        ui.printDeletedText(taskList.get(taskId-1));
        taskList.remove(taskId-1);
        ui.printCurrentTaskSize(taskList);
    }

    /**
     * Executes the "bye" command to save all tasks to the file and clear the file content.
     *
     * @param monFile The Storage instance used to handle file operations.
     */
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

    /**
     * Executes the "find" command to search for tasks containing a specific keyword.
     *
     * @param keyword The keyword to search for in the task descriptions.
     */
    public void executeFindCommand(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : taskList) {
            if (task.getTaskName().toLowerCase().contains(keyword.toLowerCase())) {
                foundTasks.add(task);
            }
        }

        if (foundTasks.isEmpty()) {
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
