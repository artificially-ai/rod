package rod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NopCommand implements Command {

    private static final Logger logger = LogManager.getLogger(NopCommand.class);

    @Override
    public void execute() {
        logger.info("Executing NopCommand");
    }

}
