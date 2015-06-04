package rod;

@SuppressWarnings("serial")
public class UnrecognizableObservationException extends Exception {

    public UnrecognizableObservationException(Observation observation) {
        super("Observation of class " + observation.getClass() + " is not recognizable by analyzer");
    }

}
