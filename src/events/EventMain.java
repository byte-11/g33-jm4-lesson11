package events;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class EventMain {
    public static void main(String[] args) {
        EventManager eventManager = new EventManager();
        List<Event> events = eventManager.getEventsFromFile("resources/events.txt");
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(4);
        for (Event event : events) {
            pool.scheduleAtFixedRate(
                    new EventTask(event),
                    Duration.between(LocalDateTime.now(), event.getStartTime()).toSeconds(),
                    60 * 5,
                    TimeUnit.SECONDS
            );
        }
    }
}
