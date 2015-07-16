package rod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NopCommand implements Action {

    private static final Logger logger = LoggerFactory.getLogger(NopCommand.class);

    @Override
    public void execute() {
        logger.info("Executing NopCommand");
    }

    @Override
    public String toString() {
        return "nop-command";
    }

}
