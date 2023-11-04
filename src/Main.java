import javax.management.remote.rmi.RMIJRMPServerImpl;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static final Logger log = Logger.getLogger(Main.class.getName());

    static {
        ConsoleHandler handler = new ConsoleHandler();
        handler.setLevel(Level.WARNING);
        handler.setFilter(new MyFilter());
        log.addHandler(handler);
        log.setUseParentHandlers(false);


    }



    public static void main(String[] args) throws FileNotFoundException {
        log.severe("server msg");
        log.warning("warning msg");
        log.info("info msg");
        log.config("config msg");
        log.fine("fine msg");
        log.finer("finer msg");
        log.finest("finest msg");
        Pattern pattern = Pattern.compile("^(\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2})");
        Matcher matcher = pattern.matcher("2023-11-03 13:53:00 Event: Meeting with Team");
//        File file = new File("resources/events.txt");
//        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        long seconds = 0;
        if (matcher.find()){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            TemporalAccessor accessor = formatter.parse(matcher.group());
            LocalDateTime from = LocalDateTime.from(accessor);
            Duration between = Duration.between(LocalDateTime.now(), from);
            seconds = between.toSeconds();
        }
        System.out.println(seconds);
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(3);
        executor.scheduleAtFixedRate(()->{
            System.out.println("Welcome to PARTY😎");
        },-1,15, TimeUnit.SECONDS);
    }
}