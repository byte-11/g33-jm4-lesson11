package events;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EventManager {

    private static final Logger logger = Logger.getLogger(EventManager.class.getName());

    private static final Pattern PATTERN_DATE_TIME = Pattern.compile("^(\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2})");
    private static final Pattern PATTERN_DESC = Pattern.compile("Event:\\s.+");
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public List<Event> getEventsFromFile(String filePath) {
        final List<Event> events = new ArrayList<>();
        try {
            final BufferedReader bf = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = bf.readLine()) != null) {
                events.add(createEventFromContext(line));
            }
        } catch (IOException e) {
            logger.severe(e.getMessage());
        }
        return events;
    }

    private Event createEventFromContext(String line) {
        return new Event(
                parseLocalDateTime(line),
                parseDescription(line)
        );
    }

    private LocalDateTime parseLocalDateTime(String time) {
        Matcher matcher = PATTERN_DATE_TIME.matcher(time);
        if (matcher.find()) {
            //2023-11-03 10:00:00 Event: Meeting with Team
            return LocalDateTime.from(FORMATTER.parse(matcher.group()));
        }
        return null;
    }

    private String parseDescription(String line) {
        final Matcher matcher = PATTERN_DESC.matcher(line);
        if (matcher.find()){
            return matcher.group();
        }
        return null;
    }
}
