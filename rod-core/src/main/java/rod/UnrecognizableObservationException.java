package rod;

@SuppressWarnings("serial")
public class UnrecognizableObservationException extends Exception {

    public UnrecognizableObservationException(Observation observation) {
        super("Observation of ckass " + observation.getClass() + " is not recognizable by analyzer");
    }

}
