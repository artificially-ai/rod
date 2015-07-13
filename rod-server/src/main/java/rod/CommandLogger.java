package rod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rx.Subscriber;

final class CommandLogger extends Subscriber<Command> {
    private static final Logger logger = LoggerFactory.getLogger(CommandLogger.class);

    @Override
    public void onCompleted() {
        logger.info("Command stream complete.");
    }

    @Override
    public void onError(final Throwable e) {
        logger.error("Error processing stream: {}", e);
    }

    @Override
    public void onNext(final Command t) {
        logger.info("Processed command: {}", t);
    }
}