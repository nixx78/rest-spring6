package lv.nixx.poc.rest.controller;

import lv.nixx.poc.rest.service.AppInfoProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

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

    @GetMapping("/variables")
    public Map<String, String> getVariables() {
        return Map.of(
                "VARIABLE_FROM_DOCKERFILE", String.valueOf(System.getenv("VARIABLE_FROM_DOCKERFILE")),
                "SPRING_PROFILES_ACTIVE", String.valueOf(System.getenv("SPRING_PROFILES_ACTIVE"))
        );
    }

}
