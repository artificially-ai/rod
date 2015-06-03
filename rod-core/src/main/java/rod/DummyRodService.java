package rod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rx.Observable;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

@Service
public class DummyRodService implements RodService {

    private static final Logger logger = LogManager.getLogger(DummyRodService.class);

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
    }

    @Override
    public void onNext(final Observation observation) {
        try {
            commands.onNext(dummyAnalyzer.analyze(observation));
        } catch (final UnrecognizableObservationException e) {
            logger.warn("Dummy analyzer cannot recognize commands of class {}: {}", observation.getClass(), e);
        }
    }

    @Override
    public Observable<Command> observeCommands() {
        return commands.asObservable();
    }

}
