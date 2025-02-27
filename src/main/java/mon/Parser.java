package mon;

import mon.exception.InvalidCommandException;

public class Parser {
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
