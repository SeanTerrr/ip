package mon;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class MonFile {
    private File monFile;
    private final String dataPath = "src/main/java/mon/MonData.txt";

    public MonFile() {
        monFile = new File(dataPath);
    }

    public void writeToFile(Task task)  throws IOException {
        FileWriter fw = new FileWriter(dataPath,true);
        fw.write(task.convertToWriteFormat());
        fw.close();
    }

    public void addDataToTaskManager(TaskManager taskManager)
            throws MonException.InvalidWriteCommandException, FileNotFoundException, MonException.InvalidEventException, MonException.InvalidDeadlineException {
        Scanner s = new Scanner(monFile);
        while (s.hasNextLine()) {
            String currentLine = s.nextLine();
            if (currentLine.isEmpty()) {
                continue;
            }
            String[] taskParts = currentLine.split(" \\| ");
            if (taskParts.length != 3 || (!taskParts[1].equals("X") && !taskParts[1].equals("O"))) {
                throw new MonException.InvalidWriteCommandException(currentLine);
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
                throw new MonException.InvalidWriteCommandException(currentLine);
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
