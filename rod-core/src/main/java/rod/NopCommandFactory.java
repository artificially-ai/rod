package rod;

public class NopCommandFactory implements ActionFactory {

    @Override
    public Action build() {
        return new NopCommand();
    }

}
