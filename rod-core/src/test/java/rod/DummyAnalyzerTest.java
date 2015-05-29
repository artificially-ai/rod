package rod;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/analyzers.xml"})
public class DummyAnalyzerTest {

    @Autowired
    private DummyAnalyzer dummyAnalyzer;

    @Test
    public void testAnalyzeDummyObervation() throws Exception {
        Command command = dummyAnalyzer.analyze(new DummyObservation());

        assertThat(command, instanceOf(NopCommand.class));
    }

}
