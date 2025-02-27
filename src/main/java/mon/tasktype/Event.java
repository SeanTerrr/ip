package mon.tasktype;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a start and end time.
 * Inherits from the {@link Task} class.
 */
public class Event extends Task {
    private LocalDateTime eventStartTime;
    private LocalDateTime eventEndTime;

    /**
     * Creates a new Event task.
     *
     * @param eventName     The name of the event.
     * @param eventStartTime The start time of the event.
     * @param eventEndTime   The end time of the event.
     * @param isDone        Whether the event task is completed (true) or not (false).
     */
    public Event(String eventName, LocalDateTime eventStartTime, LocalDateTime eventEndTime, Boolean isDone) {
        super(eventName);
        this.eventStartTime = eventStartTime;
        this.eventEndTime = eventEndTime;
        this.isDone = isDone;
    }


    /**
     * Returns a string representation of the Event task.
     * Format: [E][status] EventName (from: startTime to: endTime)
     *
     * @return The formatted string representing the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + eventStartTime.format(DateTimeFormatter.ofPattern("d MMM yyyy h:mma")) + " to: "
                + eventEndTime.format(DateTimeFormatter.ofPattern("d MMM yyyy h:mma")) + ")";
    }

    /**
     * Converts the event into a format suitable for writing to a file.
     * Format: E | [status] | EventName /from startTime /to endTime
     *
     * @return The formatted string representation of the event for file storage.
     */
    @Override
    public String convertToWriteFormat() {
        return "E | " + super.convertToWriteFormat() + " /from " + eventStartTime + " /to " + eventEndTime + System.lineSeparator();
    }
}
