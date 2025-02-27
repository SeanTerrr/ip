package mon;

import mon.exception.*;
import mon.tasktype.Deadline;
import mon.tasktype.Event;
import mon.tasktype.Task;
import mon.tasktype.ToDo;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

/**
 * The TaskManager class manages the list of tasks, providing methods to add, delete, mark, unmark, and search for tasks.
 * It interacts with the UI to print updates and task statuses.
 */
public class TaskManager {
    private Ui ui;
    private ArrayList<Task> taskList;
    private HashMap<LocalDate, ArrayList<Task>> taskMap = new HashMap<>();
    private HashMap<Task, LocalDate> dateMap = new HashMap<>();
  
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
     * @throws InvalidTaskNumberException If the task ID is invalid (greater than the size of the task list or less than 0).
     */
    public void markTaskAsDone(int taskId) throws InvalidTaskNumberException {
        if (taskId < 0 || taskId > taskList.size() || taskList.isEmpty()) {
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
     * @throws InvalidTaskNumberException If the task ID is invalid (greater than the size of the task list or less than 0).
     */
    public void unmarkTaskAsDone(int taskId) throws InvalidTaskNumberException {
        if (taskId < 0 || taskId > taskList.size() || taskList.isEmpty()) {
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
     * @throws InvalidDateTimeFormat If the date time format is invalid
     */
    public void addDeadline(String description, Boolean isDone, Boolean printText)
            throws InvalidDeadlineException, InvalidWriteCommandException, InvalidDateTimeFormat {
        try {
            String[] deadlineParts = description.split(" /by", 2);
            if (deadlineParts.length < 2) {
                if (printText) {
                    throw new InvalidDeadlineException();
                } else {
                    throw new InvalidWriteCommandException(description);
                }
            }

            //trim leading spaces in the array
            for (int i = 0; i < deadlineParts.length; i++) {
                deadlineParts[i] = deadlineParts[i].trim();
            }
            LocalDate deadline = LocalDate.parse(deadlineParts[1]);
            Deadline task = new Deadline(deadlineParts[0], deadline, isDone);
            taskList.add(task);
            taskMap.computeIfAbsent(deadline, k -> new ArrayList<>()).add(task);
            dateMap.computeIfAbsent(task, k -> deadline);
            if (printText) {
                ui.printAddedText(taskList.get(taskList.size() - 1), taskList);
            }
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeFormat();
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
     * @throws InvalidDateTimeFormat If the date time format is invalid
     */
    public void addEvent(String description, Boolean isDone, Boolean printText)
            throws InvalidEventException, InvalidWriteCommandException, InvalidDateTimeFormat{
        try {
            String[] eventParts = description.split(" /from | /to", 3);
            if (eventParts.length < 3) {
                if (printText) {
                    throw new InvalidEventException();
                } else {
                    throw new InvalidWriteCommandException(description);
                }
            }
            for (int i = 0; i < eventParts.length; i++) {
                eventParts[i] = eventParts[i].trim();
            }
            LocalDateTime eventStartTime = LocalDateTime.parse(eventParts[1]);
            LocalDateTime eventEndTime = LocalDateTime.parse(eventParts[2]);
            Event task = new Event(eventParts[0], eventStartTime, eventEndTime, isDone);
            taskList.add(task);
            taskMap.computeIfAbsent(eventStartTime.toLocalDate(), k -> new ArrayList<>()).add(task);
            dateMap.computeIfAbsent(task, k -> eventStartTime.toLocalDate());

            if (printText) {
                ui.printAddedText(taskList.get(taskList.size() - 1), taskList);
            }
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeFormat();
        }
    }

    /**
     * Deletes a specific task from the task list based on its task ID.
     *
     * @param taskId The ID of the task to delete.
     * @throws InvalidTaskNumberException If the task ID is invalid (greater than the size of the task list or less than 0).
     */
    public void deleteTask(int taskId) throws InvalidTaskNumberException {
        if (taskId < 0 || taskId > taskList.size() || taskList.isEmpty()) {
            throw new InvalidTaskNumberException(taskList.size());
        }
        Task taskToDelete = taskList.get(taskId-1);
        LocalDate date = dateMap.get(taskToDelete);
        dateMap.remove(taskToDelete);
        taskMap.get(date).remove(taskToDelete);
        if (taskMap.get(date).isEmpty()) {
            taskMap.remove(date);
        }

        ui.printDeletedText(taskList.get(taskId-1));
        taskList.remove(taskToDelete);
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
     * Executes the schedule command by parsing the given date string and retrieving tasks on that date.
     * If the date format is invalid, it throws an {@link InvalidDateTimeFormat} exception.
     *
     * @param dateString The date string in the format "YYYY-MM-DD" to search for tasks.
     * @throws InvalidDateTimeFormat if the provided date string is not in the valid format.
     */
    public void executeScheduleCommand(String dateString) throws InvalidDateTimeFormat {
        try {
            LocalDate date = LocalDate.parse(dateString);
            getAllTaskOnDate(date);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeFormat();
        }
    }

    /**
     * Retrieves and displays all tasks scheduled for a specific date.
     *
     * @param date The {@link LocalDate} representing the date for which tasks are retrieved.
     */
    public void getAllTaskOnDate(LocalDate date) {
        ArrayList<Task> taskListAtDate = taskMap.get(date);
        System.out.println("    Here are the tasks in your list for "
                + date.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ":");
        if (taskListAtDate == null) {
            System.out.println("    No tasks in your list!");
            return;
        }
        for (int i = 0; i < taskListAtDate.size(); i++) {
            int numberCount = i + 1;
            System.out.println("    " + numberCount + "." + taskListAtDate.get(i));
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
