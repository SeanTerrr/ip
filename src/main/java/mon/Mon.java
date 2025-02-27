package mon;

import java.util.Scanner;

public class Mon {
    private final Storage storage;
    private final Ui ui;
    private final TaskManager taskManager;
    private final Parser parser;

    public Mon(){
        storage = new Storage();
        ui = new Ui();
        taskManager = new TaskManager();
        parser = new Parser();
    }

    public void run(){
        ui.printWelcomeText();
        Scanner in = new Scanner(System.in);
        Storage storage = new Storage();
        try {
            storage.addDataToTaskManager(taskManager);

            //main body loop
            while (true) {
                String userInput = in.nextLine();
                if (userInput.equals("bye")) {
                    taskManager.executeByeCommand(storage);
                    break;
                } else {
                    ui.printLine();
                    parser.decodeCommand(userInput,taskManager);
                    ui.printLine();
                }
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        ui.printByeStatement();
    }

    public static void main(String[] args) {
        new Mon().run();
    }
}
