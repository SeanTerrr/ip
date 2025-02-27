package mon;

import mon.tasktype.Task;

import java.util.ArrayList;

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

    public void printLine(){
        System.out.println(horizontalLine);
    }
    public void printAddedText(Task task, ArrayList<Task> taskList){
        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + task);
        System.out.println("    Now you have " + taskList.size() + " tasks in the list.");
    }

    public void printCurrentTaskSize(ArrayList<Task> taskList){
        System.out.println("    Now you have " + taskList.size() + " tasks in the list.");
    }

    public void printDeletedText(Task task){
        System.out.println("    Got it. I've deleted this task:");
        System.out.println("      " + task);
    }

    public void printWelcomeText(){
        printLine();
        System.out.println(logo + "    Hello! I'm " + botName + "\n" + "    What can I do for you?");
        printLine();
    }

    public void printByeStatement(){
        printLine();
        System.out.println("    Bye! See you again!");
        printLine();
    }
}
