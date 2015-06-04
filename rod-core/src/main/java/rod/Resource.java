package rod;

import rx.Observable;

public interface Resource {

    public Observable<Observation> observe();

}
