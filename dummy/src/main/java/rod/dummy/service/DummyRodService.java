package rod.dummy.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rod.Action;
import rod.Observation;
import rod.Resource;
import rod.RodService;
import rod.UnrecognizableObservationException;
import rod.dummy.rx.DummyAnalyzer;
import rx.Observable;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

@Service("DummyRodService")
public class DummyRodService implements RodService {

    private static final Logger logger = LoggerFactory.getLogger(DummyRodService.class);

    private final PublishSubject<Action> commands = PublishSubject.create();

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
            final Action command = dummyAnalyzer.analyze(observation);
            logger.info("Dummy analysis produced command: {}", command);
            commands.onNext(command);
        } catch (final UnrecognizableObservationException e) {
            logger.warn("Dummy analyzer cannot recognize commands of class {}: {}", observation.getClass(), e);
        }
    }

    @Override
    public Observable<Action> commands() {
        return commands.asObservable();
    }

}
