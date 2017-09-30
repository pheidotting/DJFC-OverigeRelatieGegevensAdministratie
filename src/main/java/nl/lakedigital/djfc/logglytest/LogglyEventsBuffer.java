package nl.lakedigital.djfc.logglytest;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.log4j.Level;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class LogglyEventsBuffer {
    private List<LogglyEvent> events;
    private Timer timer;


    public void add(String event, Level level, String token, String tag, int interval) {
        if (events == null) {
            events = new ArrayList<>();
        }
        events.add(new LogglyEvent(event, level));

        if (events.size() >= 500 || level == Level.ERROR) {
            flush(token, tag);
        }
        if (timer == null) {
            int delay = interval * 1000;
            int period = 1000;
            this.timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {

                public void run() {
                    flush(token, tag);

                }
            }, delay, period);
            timer = null;
        }
    }

    public void flush(String token, String tag) {
        if (events != null && !events.isEmpty()) {
            List<LogglyEvent> eventsToFlush = new ArrayList<>(events);
            events = null;

            for (LogglyEvent event : eventsToFlush) {

                try {
                    Unirest.post("http://logs-01.loggly.com/inputs/" + token + "/tag/" + tag + "," + event.getLevel().toString()).header("accept", "application/json").body(event.getEvent()).asString();
                } catch (UnirestException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private class LogglyEvent {
        private String event;
        private Level level;

        public LogglyEvent(String event, Level level) {
            this.event = event;
            this.level = level;
        }

        public String getEvent() {
            return event;
        }

        public void setEvent(String event) {
            this.event = event;
        }

        public Level getLevel() {
            return level;
        }

        public void setLevel(Level level) {
            this.level = level;
        }
    }
}
