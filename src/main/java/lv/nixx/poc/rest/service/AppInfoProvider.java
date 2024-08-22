package lv.nixx.poc.rest.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.util.Optional;
import java.util.Properties;

@Service
public class AppInfoProvider {

    static final Logger log = LoggerFactory.getLogger(AppInfoProvider.class);

    public AppInfo getInfo() {
        return new AppInfo(getHost(), getVersion());
    }

    private String getHost() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (Exception ex) {
            log.error("Error retrieving host name", ex);
        }
        return "N/A";
    }

    public String getVersion() {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("META-INF/MANIFEST.MF");

        Properties prop = new Properties();
        try {
            prop.load(is);
            return Optional.ofNullable(prop.get("Version")).map(String::valueOf).orElse("N/A");
        } catch (IOException ex) {
            log.error("Error retrieving data from Manifest", ex);
        }
        return "N/A";
    }

    @AllArgsConstructor
    @Getter
    public static class AppInfo {
        private final String host;
        private final String version;
    }

}
