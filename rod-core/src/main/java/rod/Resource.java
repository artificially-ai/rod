package rod;

import rx.Observable;

public interface Resource {

    public String getName();

    public Observable<Observation> observe();

}
