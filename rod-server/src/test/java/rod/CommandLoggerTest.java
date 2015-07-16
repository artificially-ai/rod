package rod;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import rx.Observable;
import rx.schedulers.Schedulers;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;

public class CommandLoggerTest {

    private TestAppender testAppender;

    @Before
    public void setupLogsForTesting() {
        final Logger root = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        testAppender = (TestAppender) root.getAppender("TEST");
        if (testAppender != null) {
            testAppender.clear();
        }
    }

    @Test
    public void testOnComplete() throws Exception {
        Observable.just(new NopCommand()).subscribeOn(Schedulers.immediate()).subscribe(new CommandLogger());

        final ILoggingEvent lastEvent = testAppender.getLastEvent();
        assertThat(lastEvent.getMessage(), equalTo("Command stream complete."));
        assertThat(lastEvent.getLevel(), equalTo(Level.INFO));
    }

    @Test
    public void testOnError() throws Exception {
        final Observable<Action> observable = Observable.error(new RuntimeException("Simulating an error."));
        observable.subscribeOn(Schedulers.immediate()).subscribe(new CommandLogger());

        final ILoggingEvent lastEvent = testAppender.getLastEvent();
        assertThat(lastEvent.getMessage(), equalTo("Error processing stream: {}"));
        assertThat(lastEvent.getLevel(), equalTo(Level.ERROR));
    }

    @Test
    public void testOnNext() throws Exception {
        Observable.from(Arrays.asList(new NopCommand(), new NopCommand(), new NopCommand())).subscribeOn(Schedulers.immediate()).subscribe(new CommandLogger());

        final List<ILoggingEvent> allEvents = testAppender.getAllEvents();
        assertThat(allEvents, hasSize(4));
        assertThat(allEvents.get(0).getMessage(), equalTo("Processed command: {}"));
        assertThat(allEvents.get(0).getLevel(), equalTo(Level.INFO));
        assertThat(allEvents.get(1).getMessage(), equalTo("Processed command: {}"));
        assertThat(allEvents.get(1).getLevel(), equalTo(Level.INFO));
        assertThat(allEvents.get(2).getMessage(), equalTo("Processed command: {}"));
        assertThat(allEvents.get(2).getLevel(), equalTo(Level.INFO));
        assertThat(allEvents.get(3).getMessage(), equalTo("Command stream complete."));
        assertThat(allEvents.get(3).getLevel(), equalTo(Level.INFO));
    }
}
