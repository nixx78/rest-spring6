package lv.nixx.poc.rest.controller;

import lv.nixx.poc.rest.service.AppInfoProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppInfoController {

    private final AppInfoProvider appInfoProvider;

    public AppInfoController(AppInfoProvider appInfoProvider) {
        this.appInfoProvider = appInfoProvider;
    }

    @GetMapping("/appInfo")
    public AppInfoProvider.AppInfo getVersion() {
        return appInfoProvider.getInfo();
    }

}
