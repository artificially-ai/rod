package rod;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.not;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RodServer.class)
public class ResourceControllerTest {

    private TestAppender testAppender;

    @Autowired
    private ResourceController resourceController;

    @Autowired
    private ResourceRepository resourceRepository;

    @Before
    public void setupLogsForTesting() {
        final Logger root = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        testAppender = (TestAppender) root.getAppender("TEST");
        if (testAppender != null) {
            testAppender.clear();
        }
    }

    @Test
    public void testRegisterResource() throws Exception {
        final int countBeforeTest = resourceRepository.resourceCount();
        final boolean response = resourceController.registerResource();

        assertThat(response, equalTo(true));
        assertThat(resourceRepository.resourceCount(), equalTo(countBeforeTest + 1));
    }

    @Test
    public void testObserveResource() throws Exception {
        resourceController.registerResource();
        resourceController.observe();
        Thread.sleep(1000);
        resourceController.stopObserving();

        final List<ILoggingEvent> allEvents = testAppender.getAllEvents();
        assertThat(allEvents, hasSize(greaterThan(0)));
        assertThat(allEvents.get(0).getMessage(), not(isEmptyString()));
    }
}
