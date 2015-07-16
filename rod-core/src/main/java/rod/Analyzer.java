package rod;

public interface Analyzer {

    public Action analyze(Observation observation) throws UnrecognizableObservationException;

}
