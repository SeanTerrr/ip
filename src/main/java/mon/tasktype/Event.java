package mon.tasktype;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime eventStartTime;
    private LocalDateTime eventEndTime;
    public Event(String eventName, LocalDateTime eventStartTime, LocalDateTime eventEndTime, Boolean isDone) {
        super(eventName);
        this.eventStartTime = eventStartTime;
        this.eventEndTime = eventEndTime;
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + eventStartTime.format(DateTimeFormatter.ofPattern("d MMM yyyy h:mma")) + " to: "
                + eventEndTime.format(DateTimeFormatter.ofPattern("d MMM yyyy h:mma")) + ")";
    }

    @Override
    public String convertToWriteFormat() {
        return "E | " + super.convertToWriteFormat() + " /from " + eventStartTime + " /to " + eventEndTime + System.lineSeparator();
    }
}
