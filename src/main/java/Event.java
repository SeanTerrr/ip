public class Event extends Task {
    private String eventStartTime;
    private String eventEndTime;
    public Event(String eventName, String eventStartTime, String eventEndTime) {
        super(eventName);
        this.eventStartTime = eventStartTime;
        this.eventEndTime = eventEndTime;
        this.isDone = false;
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
        return "[E]" + super.toString() + "(from: " + eventStartTime + " to:" + eventEndTime + ")";
    }
}
