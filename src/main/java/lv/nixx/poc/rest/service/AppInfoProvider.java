package lv.nixx.poc.rest.service;

import jakarta.servlet.ServletContext;
import lombok.Builder;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

@Service
public class AppInfoProvider {

    static final Logger log = LoggerFactory.getLogger(AppInfoProvider.class);

    private final ServletContext context;
    private final Environment environment;

    public AppInfoProvider(ServletContext context, Environment environment) {
        this.context = context;
        this.environment = environment;
    }

    public AppInfo getInfo() {
        AppInfo.AppInfoBuilder builder =
                AppInfo.builder()
                        .host(getHost());

        setDataFromManifest(builder);

        return builder.build();
    }

    private String getHost() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (Exception ex) {
            log.error("Error retrieving host name", ex);
        }
        return "N/A";
    }

    private void setDataFromManifest(AppInfo.AppInfoBuilder builder) {
        String version = "N/A";
        String timestmap = "N/A";
        try {
            InputStream manifestStream = context.getResourceAsStream("/META-INF/MANIFEST.MF");
            if (manifestStream != null) {
                Manifest manifest = new Manifest(manifestStream);
                Attributes attributes = manifest.getMainAttributes();

                version = attributes.getValue("Version");
                timestmap = attributes.getValue("Implementation-Build");
            }
        } catch (IOException e) {
            log.error("Can't retrieve data from MANIFEST file");
        }

        builder.version(version)
                .timestamp(timestmap)
                .profile(environment.getActiveProfiles());
    }

    @Builder
    @Getter
    public static class AppInfo {
        private final String host;
        private final String version;
        private final String timestamp;
        private final String[] profile;
    }

}
