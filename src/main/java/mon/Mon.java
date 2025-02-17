package mon;

import java.io.File;
import java.util.Scanner;

public class Mon {
    public static void main(String[] args) {
        final String botName = "Mon";
        final String horizontalLine = "   _______________________________";
        System.out.println(horizontalLine);
        System.out.println("    Hello! I'm " + botName + "\n" + "    What can I do for you?");
        System.out.println(horizontalLine);
        TaskManager taskManager = new TaskManager();
        Scanner in = new Scanner(System.in);
        MonFile monFile = new MonFile();
        try {
            monFile.addDataToTaskManager(taskManager);

            //main body loop
            while (true) {
                String userInput = in.nextLine();
                if (userInput.equals("bye")) {
                    taskManager.executeByeCommand(monFile);
                    break;
                } else if (userInput.equals("list")) {
                    System.out.println(horizontalLine);
                    taskManager.printAllTasks();
                    System.out.println(horizontalLine);
                } else {
                    System.out.println(horizontalLine);
                    taskManager.decodeCommand(userInput);
                    System.out.println(horizontalLine);
                }
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        printByeStatement();
    }

    public static void printByeStatement(){
        final String horizontalLine = "   _______________________________";
        System.out.println(horizontalLine);
        System.out.println("    Bye! See you again!");
        System.out.println(horizontalLine);
    }
}
