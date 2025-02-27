package mon;

import mon.tasktype.Task;

import java.util.ArrayList;

/**
 * User interface class that handles the printing of various messages and task-related information to the console.
 */
public class Ui {
    final String botName = "Mon";
    final String logo = "\n" +
            "   .___  ___.   ______   .__   __. \n" +
            "   |   \\/   |  /  __  \\  |  \\ |  | \n" +
            "   |  \\  /  | |  |  |  | |   \\|  | \n" +
            "   |  |\\/|  | |  |  |  | |  . `  | \n" +
            "   |  |  |  | |  `--'  | |  |\\   | \n" +
            "   |__|  |__|  \\______/  |__| \\__| \n" +
            "                                \n";
    final String horizontalLine = "   _______________________________";

    /**
     * Prints a horizontal line to the console.
     */
    public void printLine(){
        System.out.println(horizontalLine);
    }

    /**
     * Prints a message indicating that a task has been added to the task list.
     * Displays the added task and the total number of tasks in the list.
     *
     * @param task The task that was added.
     * @param taskList The list of all tasks.
     */
    public void printAddedText(Task task, ArrayList<Task> taskList){
        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + task);
        System.out.println("    Now you have " + taskList.size() + " tasks in the list.");
    }

    /**
     * Prints the current number of tasks in the task list.
     *
     * @param taskList The list of all tasks.
     */
    public void printCurrentTaskSize(ArrayList<Task> taskList){
        System.out.println("    Now you have " + taskList.size() + " tasks in the list.");
    }

    /**
     * Prints a message indicating that a task has been deleted from the task list.
     * Displays the deleted task.
     *
     * @param task The task that was deleted.
     */
    public void printDeletedText(Task task){
        System.out.println("    Got it. I've deleted this task:");
        System.out.println("      " + task);
    }

    /**
     * Prints the welcome message with the bot's name and logo.
     */
    public void printWelcomeText(){
        printLine();
        System.out.println(logo + "    Hello! I'm " + botName + "\n" + "    What can I do for you?");
        printLine();
    }

    /**
     * Prints a goodbye message when the program ends.
     */
    public void printByeStatement(){
        printLine();
        System.out.println("    Bye! See you again!");
        printLine();
    }
}
