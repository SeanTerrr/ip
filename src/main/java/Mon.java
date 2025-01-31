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
            }
            else if (userInput.equals("list")){
                System.out.println(horizontalLine);
                taskManager.printTasks();
                System.out.println(horizontalLine);
            }
            else {
                System.out.println(horizontalLine);
                if (userInput.startsWith("mark")) {
                    int taskId = Integer.parseInt(userInput.substring(5));
                    taskManager.markTaskAsDone(taskId);
                }
                else if (userInput.startsWith("unmark")) {
                    int taskId = Integer.parseInt(userInput.substring(7));
                    taskManager.unmarkTaskAsDone(taskId);
                }
                else {
                    Task newTask = new Task(userInput);
                    taskManager.addTask(newTask);
                }
                System.out.println(horizontalLine);
            }
        }
        System.out.println(horizontalLine);
        System.out.println("    Bye! See you again!");
        System.out.println(horizontalLine);
    }
}
