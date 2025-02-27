package mon;

import java.util.Scanner;

/**
 * Main class for running the Mon application.
 * It initializes and coordinates the UI, task management, and command parsing.
 */
public class Mon {
    private final Storage storage;
    private final Ui ui;
    private final TaskManager taskManager;
    private final Parser parser;

    /**
     * Constructs the Mon application with its components: Storage, Ui, TaskManager, and Parser.
     */
    public Mon(){
        storage = new Storage();
        ui = new Ui();
        taskManager = new TaskManager();
        parser = new Parser();
    }

    /**
     * Runs the main application loop.
     * Prints the welcome text, processes user input, and handles commands.
     */
    public void run(){
        ui.printWelcomeText();
        Scanner in = new Scanner(System.in);
        Storage storage = new Storage();
        try {
            storage.addDataToTaskManager(taskManager);

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
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        ui.printByeStatement();
    }

    /**
     * Main method to start the Mon application.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        new Mon().run();
    }
}
