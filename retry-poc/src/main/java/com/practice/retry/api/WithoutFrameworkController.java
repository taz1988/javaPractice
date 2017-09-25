package com.practice.retry.api;

import com.practice.retry.withoutframework.service.SimpleRetryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/withoutframework/")
public class WithoutFrameworkController {

    private final SimpleRetryService simpleRetryService;
    private final SimpleEntityGenerator simpleEntityGenerator;

    @Autowired
    public WithoutFrameworkController(SimpleRetryService simpleRetryService, SimpleEntityGenerator simpleEntityGenerator) {
        this.simpleRetryService = simpleRetryService;
        this.simpleEntityGenerator = simpleEntityGenerator;
    }

    @RequestMapping("/call")
    public String callNonStableService() {
        return simpleRetryService.getResponseWithRetry(simpleEntityGenerator.generate(10)).toString();
    }

}
