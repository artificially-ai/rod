package rod;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.instanceOf;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/configuration.xml", "/analyzers.xml" })
public class DummyRodServiceTest {

    @Autowired
    private DummyRodService service;

    @SuppressWarnings("unchecked")
    @Test
    public void testService() throws Exception {
        service.register(DummyResource.getSingleton());
        final List<Action> commands = service.commands().take(2).toList().toBlocking().single();

        assertThat(commands, contains(instanceOf(NopCommand.class), instanceOf(NopCommand.class)));
    }
}
