package rod;

import rx.Subscriber;

final class CommandLogger extends Subscriber<Command> {
    @Override
    public void onCompleted() {
        ResourceController.logger.info("Command stream complete.");
    }

    @Override
    public void onError(final Throwable e) {
        ResourceController.logger.error("Error processing stream: {}", e);
    }

    @Override
    public void onNext(final Command t) {
        ResourceController.logger.info("Processed command: {}", t);
    }
}