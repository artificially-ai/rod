package rod;

import java.util.concurrent.TimeUnit;

import rx.Observable;

public class DummyResource implements Resource {

    private final static DummyResource instance = new DummyResource();

    private DummyResource() {
    }

    public static DummyResource getSingleton() {
        return instance;
    }

    @Override
    public Observable<Observation> observe() {
        return Observable.interval(1, TimeUnit.SECONDS).map(i -> new DummyObservation());
    }

    @Override
    public String getName() {
        return "dummy";
    }

}
