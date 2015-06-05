package rod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import rx.schedulers.Schedulers;

@Controller
public class ResourceController {

    static final Logger logger = LoggerFactory.getLogger(ResourceController.class);

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private RodService service;

    @RequestMapping(value = "/resource/register", method = RequestMethod.GET)
    public @ResponseBody boolean registerResource() {
        final DummyResource resource = DummyResource.getSingleton();
        resourceRepository.save(resource);
        service.register(resource);
        return true;
    }

    @RequestMapping(value = "/resource/observe", method = RequestMethod.GET)
    public @ResponseBody boolean observe() {
        service.commands().subscribeOn(Schedulers.io()).subscribe(new CommandLogger());
        return true;
    }

    @RequestMapping(value = "/resource/stop-observing", method = RequestMethod.GET)
    public @ResponseBody boolean stopObserving() {
        service.commands().unsubscribeOn(Schedulers.io());
        return true;
    }
}
