package rod;

import java.util.ArrayList;
import java.util.List;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;

public class TestAppender extends AppenderBase<ILoggingEvent> {
    private final List<ILoggingEvent> events = new ArrayList<ILoggingEvent>();

    @Override
    protected void append(final ILoggingEvent event) {
        events.add(event);
    }

    public void clear() {
        events.clear();
    }

    public ILoggingEvent getLastEvent() {
        return events.get(events.size() - 1);
    }

    public List<ILoggingEvent> getAllEvents() {
        return events;
    }

}