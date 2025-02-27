package mon;

import mon.exception.InvalidDateTimeFormat;
import mon.exception.InvalidDeadlineException;
import mon.exception.InvalidEventException;
import mon.exception.InvalidWriteCommandException;
import mon.tasktype.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The Storage class handles all file-related operations for saving and loading task data.
 * It ensures the data is stored in a file and can be loaded back into the task manager upon application startup.
 */
public class Storage {
    private File monFile;
    private final String dataPath = "./data/MonData.txt";

    /**
     * Constructs a Storage object, initializing the data directory and file.
     * If the directory or file doesn't exist, it creates them.
     */
    public Storage() {
        File dir = new File("./data"); // Separate directory creation
        if (!dir.exists()) {
            if (dir.mkdirs()) {
                System.out.println("Directory created: " + dir.getAbsolutePath());
            } else {
                System.out.println("Failed to create directory!");
            }
        }

        monFile = new File(dataPath);

        if (!monFile.exists()) {
            try {
                if (monFile.createNewFile()) {
                    System.out.println("    File created: " + monFile.getAbsolutePath());
                } else {
                    System.out.println("    File already exists at: " + monFile.getAbsolutePath());
                }
            } catch (IOException e) {
                System.out.println("    An error occurred while creating the file.");
                e.printStackTrace();
            }
        }
    }

    /**
     * Writes the task data to the file in a specific format.
     *
     * @param task The task to be written to the file.
     * @throws IOException If an error occurs while writing to the file.
     */
    public void writeToFile(Task task)  throws IOException {
        FileWriter fw = new FileWriter(dataPath,true);
        fw.write(task.convertToWriteFormat());
        fw.close();
    }

    /**
     * Loads data from the file and adds the tasks to the TaskManager.
     * It parses the file content and creates the corresponding tasks based on the file format.
     *
     * @param taskManager The TaskManager instance to load the tasks into.
     * @throws InvalidWriteCommandException If the file format is invalid.
     * @throws FileNotFoundException If the file is not found.
     * @throws InvalidEventException If an event is malformed in the file.
     * @throws InvalidDeadlineException If a deadline is malformed in the file.
     * @throws InvalidDateTimeFormat If the date time format is invalid
     */
    public void addDataToTaskManager(TaskManager taskManager)
            throws InvalidWriteCommandException, FileNotFoundException,
            InvalidEventException, InvalidDeadlineException, InvalidDateTimeFormat {
        Scanner s = new Scanner(monFile);
        while (s.hasNextLine()) {
            String currentLine = s.nextLine();
            if (currentLine.isEmpty()) {
                continue;
            }
            String[] taskParts = currentLine.split(" \\| ");
            if (taskParts.length != 3 || (!taskParts[1].equals("X") && !taskParts[1].equals("O"))) {
                throw new InvalidWriteCommandException(currentLine);
            }
            Boolean isDone = taskParts[1].equals("X");
            switch (taskParts[0]) {
            case "T":
                taskManager.addTodo(taskParts[2], isDone, false);
                break;
            case "D":
                taskManager.addDeadline(taskParts[2], isDone, false);
                break;
            case "E":
                taskManager.addEvent(taskParts[2], isDone, false);
                break;
            default:
                throw new InvalidWriteCommandException(currentLine);
            }
        }
    }

    /**
     * Clears the content of the task data file.
     */
    public void clearFileContent(){
        try {
            FileWriter fw = new FileWriter(dataPath);
            fw.write("");
            fw.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
