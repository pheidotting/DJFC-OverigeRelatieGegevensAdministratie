package nl.lakedigital.djfc.logglytest;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;

public class LogglyAppender extends AppenderSkeleton {
    private String tag = "";
    private String token = "";
    private String interval = "1";

    public LogglyAppender() {
        super();
    }

    public LogglyAppender(boolean isActive) {
        super(isActive);
    }

    private LogglyEventsBuffer logglyEventsBuffer;

    @Override
    protected void append(LoggingEvent event) {
        if (logglyEventsBuffer == null) {
            ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
            logglyEventsBuffer = (LogglyEventsBuffer) appContext.getBean("logglyEventsBuffer");
        }

        assert this.layout != null : "Cannot log, there is no layout configured.";

        String output = this.layout.format(event);

        logglyEventsBuffer.add(output, event.getLevel(), token, tag, Integer.parseInt(interval));
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    @Override
    public void close() {

    }

    @Override
    public boolean requiresLayout() {
        return false;
    }
}
