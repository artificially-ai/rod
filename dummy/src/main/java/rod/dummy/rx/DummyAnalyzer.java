package rod.dummy.rx;

import rod.Action;
import rod.Analyzer;
import rod.Observation;
import rod.ObservationToCommandMapping;
import rod.UnrecognizableObservationException;

public class DummyAnalyzer implements Analyzer {

    private ObservationToCommandMapping observationToCommand;

    public void setObservationToCommand(final ObservationToCommandMapping observationToCommand) {
        this.observationToCommand = observationToCommand;
    }

    @Override
    public Action analyze(final Observation observation) throws UnrecognizableObservationException {
        if (observationToCommand.containsKey(observation.getClass())) {
            return observationToCommand.get(observation.getClass()).build();
        } else {
            throw new UnrecognizableObservationException(observation);
        }
    }

}
