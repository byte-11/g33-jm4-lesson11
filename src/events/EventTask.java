package events;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EventTask implements Runnable{

    private static final Logger logger = Logger.getLogger(EventTask.class.getName());
    static {
        try {
            FileHandler handler = new FileHandler("log/log_%g.txt",1024 * 1024, 4, true);
            handler.setLevel(Level.ALL);
            logger.addHandler(handler);
        } catch (IOException e) {
            logger.severe(e.getMessage());
        }
    }

    private final Event event;

    public EventTask(Event event) {
        this.event = event;
    }

    @Override
    public void run() {
        logger.info(event.getDescription() + " - started.");
    }
}
