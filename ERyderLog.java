import java.time.LocalDateTime;

public class ERyderLog {
    private final String log;
    private final String event;
    private final LocalDateTime timeStamp;

    public ERyderLog(String log, String event, LocalDateTime timeStamp) {
        this.log = log;
        this.event = event;
        this.timeStamp = timeStamp;
    }

    public String getLog() {
        return log;
    }

    public String getEvent() {
        return event;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    @Override
    public String toString() {
        return log + " – " + event + "- " + timeStamp;
    }
}
