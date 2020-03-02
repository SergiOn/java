package academy.learnprogramming.bbbms.service;

import org.springframework.stereotype.Service;

@Service
public class DemoServiceImpl implements DemoService {

    @Override
    public String getMessage() {
        return "Hello from the core module";
    }
}
