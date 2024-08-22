package lv.nixx.poc.rest.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.util.Properties;

@Service
public class AppInfoProvider {

    private static final Logger log = LoggerFactory.getLogger(AppInfoProvider.class);

    public String getVersion() {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("META-INF/MANIFEST.MF");

        Properties prop = new Properties();
        try {
            prop.load(is);
            return "Info about ServerAPI. version:" + prop.get("Version") + " host: " + InetAddress.getLocalHost().getHostName();
        } catch (IOException ex) {
            log.error("Error retrieving data from Manifest", ex);

        }
        return "UnknownVersion";
    }
}
