package rod;

public class NopCommandFactory implements CommandFactory {

    @Override
    public Command build() {
        return new NopCommand();
    }

}
