import java.util.Scanner;

public class Mon {
    public static void main(String[] args) {
        String botName = "Mon";
        String horizontalLine = "   _______________________________";
        System.out.println(horizontalLine);
        System.out.println("    Hello! I'm " + botName + "\n" + "    What can I do for you?");
        System.out.println(horizontalLine);
        Scanner in = new Scanner(System.in);
        while (true) {
            String userInput = in.nextLine();
            if (userInput.equals("bye")) {
                break;
            } else {
                System.out.println(horizontalLine);
                System.out.println("    " + userInput);
                System.out.println(horizontalLine);
            }
        }
        System.out.println(horizontalLine);
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println(horizontalLine);
    }
}
