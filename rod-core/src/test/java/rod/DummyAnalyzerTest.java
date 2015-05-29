package rod;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;

import org.junit.Test;

public class DummyAnalyzerTest {

    @Test
    public void testAnalyzeDummyObervation() throws Exception {
        Command command = new DummyAnalyzer().analyze(new DummyObservation());

        assertThat(command, instanceOf(NopCommand.class));
    }

}
