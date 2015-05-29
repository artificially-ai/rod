package rod;

import java.util.HashMap;
import java.util.Map;

public class DummyAnalyzer implements Analyzer {

    private static Map<Class<? extends Observation>, CommandFactory> observationToCommand = new HashMap<>();
    static {
        observationToCommand.put(DummyObservation.class, new NopCommandFactory());
    }

    @Override
    public Command analyze(Observation observation) throws UnrecognizableObservationException {
        if (observationToCommand.containsKey(observation.getClass())) {
            return observationToCommand.get(observation.getClass()).build();
        } else {
            throw new UnrecognizableObservationException(observation);
        }
    }

}
