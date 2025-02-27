package mon;

import mon.exception.InvalidCommandException;

/**
 * The Parser class is responsible for decoding user input commands and directing them to the appropriate methods in the TaskManager.
 */
public class Parser {

    /**
     * Decodes a user command and executes the corresponding task management action.
     * The method splits the input command into the command type and its associated data, then processes it accordingly.
     *
     * @param command The user input command as a string.
     * @param taskManager The task manager responsible for managing tasks.
     * @throws InvalidCommandException If the command is invalid or in an incorrect format.
     */
    public void decodeCommand(String command, TaskManager taskManager) throws InvalidCommandException {
        String[] commandArray = command.split(" ", 2);
        try {
            if (commandArray.length == 1 && commandArray[0].equals("list")) {
                taskManager.printAllTasks();
                return;
            }
            if (commandArray.length < 2) {
                throw new InvalidCommandException();
            }
            switch (commandArray[0]) {
            case "event":
                taskManager.addEvent(commandArray[1], false, true);
                break;
            case "deadline":
                taskManager.addDeadline(commandArray[1], false, true);
                break;
            case "todo":
                taskManager.addTodo(commandArray[1], false, true);
                break;
            case "unmark":
                taskManager.unmarkTaskAsDone(Integer.parseInt(commandArray[1]));
                break;
            case "mark":
                taskManager.markTaskAsDone(Integer.parseInt(commandArray[1]));
                break;
            case "delete":
                taskManager.deleteTask(Integer.parseInt(commandArray[1]));
                break;
            case "find":
                taskManager.executeFindCommand(commandArray[1]);
                break;
            default:
                throw new InvalidCommandException();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
