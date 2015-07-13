package rod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rx.Observable;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

@Service
public class DummyRodService implements RodService {

    private static final Logger logger = LoggerFactory.getLogger(DummyRodService.class);

    private final PublishSubject<Command> commands = PublishSubject.create();

    @Autowired
    private DummyAnalyzer dummyAnalyzer;

    @Override
    public void register(final Resource resource) {
        resource.observe().observeOn(Schedulers.computation()).subscribe(this);
    }

    @Override
    public void onCompleted() {
        logger.info("No more observations to analyze");
    }

    @Override
    public void onError(final Throwable e) {
        logger.error("Caught error while observing: {}", e);
        commands.onError(e);
    }

    @Override
    public void onNext(final Observation observation) {
        try {
            final Command command = dummyAnalyzer.analyze(observation);
            logger.info("Dummy analysis produced command: {}", command);
            commands.onNext(command);
        } catch (final UnrecognizableObservationException e) {
            logger.warn("Dummy analyzer cannot recognize commands of class {}: {}", observation.getClass(), e);
        }
    }

    @Override
    public Observable<Command> commands() {
        return commands.asObservable();
    }

}
