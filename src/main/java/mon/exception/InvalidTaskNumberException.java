package mon.exception;

/**
 * Exception thrown when an invalid task number is provided.
 */
public class InvalidTaskNumberException extends Exception {

  /**
   * Constructs an {@code InvalidTaskNumberException} with a message indicating the number of tasks.
   * The message helps the user to enter a valid task ID by informing them of the current number of tasks.
   *
   * @param numberOfTasks The current number of tasks in the list.
   */
  public InvalidTaskNumberException(int numberOfTasks) {
    super("    Please enter a valid task ID. " +
            "There are currently " + numberOfTasks + " items in the list.");
  }
}
