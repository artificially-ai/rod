package rod.dummy.rx;

import rod.Observation;
import rod.Resource;

public class DummyObservation implements Observation {

    @Override
    public Resource origin() {
        return DummyResource.getSingleton();
    }

}
