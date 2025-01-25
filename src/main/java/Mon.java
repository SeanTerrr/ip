import java.util.Scanner;

public class Mon {
    public static void main(String[] args) {
        String botName = "Mon";
        String horizontalLine = "   _______________________________";
        System.out.println(horizontalLine);
        System.out.println("    Hello! I'm " + botName + "\n" + "    What can I do for you?");
        System.out.println(horizontalLine);
        TaskManager taskManager = new TaskManager();
        Scanner in = new Scanner(System.in);
        while (true) {
            String userInput = in.nextLine();
            if (userInput.equals("bye")) {
                break;
            } else {
                System.out.println(horizontalLine);
                if (userInput.equals("list")){
                    taskManager.printTasks();
                }
                else {
                    Task newTask = new Task(userInput);
                    taskManager.addTask(newTask);
                }
                System.out.println(horizontalLine);
            }
        }
        System.out.println(horizontalLine);
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println(horizontalLine);
    }
}
