package mon.tasktype;

public class Event extends Task {
    private String eventStartTime;
    private String eventEndTime;
    public Event(String eventName, String eventStartTime, String eventEndTime, Boolean isDone) {
        super(eventName);
        this.eventStartTime = eventStartTime;
        this.eventEndTime = eventEndTime;
        this.isDone = isDone;
    }

    public String getEventStartTime() {
        return eventStartTime;
    }

    public void setEventStartTime(String eventStartTime) {
        this.eventStartTime = eventStartTime;
    }

    public String getEventEndTime() {
        return eventEndTime;
    }

    public void setEventEndTime(String eventEndTime) {
        this.eventEndTime = eventEndTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + eventStartTime + " to: " + eventEndTime + ")";
    }

    @Override
    public String convertToWriteFormat() {
        return "E | " + super.convertToWriteFormat() + " /from " + eventEndTime + " /to " + eventStartTime + System.lineSeparator();
    }
}
