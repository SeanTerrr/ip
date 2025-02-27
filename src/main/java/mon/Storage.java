package mon;
import mon.exception.InvalidDeadlineException;
import mon.exception.InvalidEventException;
import mon.exception.InvalidWriteCommandException;
import mon.tasktype.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    private File monFile;
    private final String dataPath = "./data/MonData.txt";

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

    public void writeToFile(Task task)  throws IOException {
        FileWriter fw = new FileWriter(dataPath,true);
        fw.write(task.convertToWriteFormat());
        fw.close();
    }

    public void addDataToTaskManager(TaskManager taskManager)
            throws InvalidWriteCommandException, FileNotFoundException, InvalidEventException, InvalidDeadlineException {
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
                taskManager.addTodo(taskParts[2], isDone,false);
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
