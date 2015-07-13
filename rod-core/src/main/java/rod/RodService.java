package rod;

import rx.Observable;
import rx.Observer;

public interface RodService extends Observer<Observation> {

    public void register(Resource resource);

    public Observable<Command> commands();

}
