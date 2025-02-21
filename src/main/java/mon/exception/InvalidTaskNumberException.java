package mon.exception;

public class InvalidTaskNumberException extends Exception {
  public InvalidTaskNumberException(int numberOfTasks) {
    super("    Please enter a valid task ID. " +
            "There are currently " + numberOfTasks + " items in the list.");
  }
}
