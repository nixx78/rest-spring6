package lv.nixx.poc.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class ControllerWithRedirect {

    @GetMapping("/old_url")
    public RedirectView processOld() {
        // In this case, redirect happens for the same root
        return new RedirectView("/new_url", true);
    }

    @GetMapping("/new_url")
    public String processNew() {
        return "Process by new method-" + System.currentTimeMillis();
    }

    @GetMapping("/v1/process")
    public String processV1() {
        return "Process by method-v1:" + System.currentTimeMillis();
    }

    @GetMapping("/v2/process")
    public String processV2() {
        return "Process by method-v2:" + System.currentTimeMillis();
    }

}
