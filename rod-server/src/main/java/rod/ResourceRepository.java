package rod;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class ResourceRepository {

    private final Map<String, Resource> resources = new HashMap<>();

    public void save(final Resource resource) {
        resources.put(resource.getName(), resource);
    }

    public int resourceCount() {
        return resources.size();
    }
}
