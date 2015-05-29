package rod;

public class DummyObservation implements Observation {

    @Override
    public Resource origin() {
        return DummyResource.getSingleton();
    }

}
