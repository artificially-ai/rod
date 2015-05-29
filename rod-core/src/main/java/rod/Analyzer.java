package rod;

public interface Analyzer {

    public Command analyze(Observation observation) throws UnrecognizableObservationException;

}
