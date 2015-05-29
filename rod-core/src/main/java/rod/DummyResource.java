package rod;

public class DummyResource implements Resource {

    private final static DummyResource instance = new DummyResource();

    private DummyResource() {
    }

    public static DummyResource getSingleton() {
        return instance;
    }

}
