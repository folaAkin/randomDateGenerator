package com.footballindex.tests.dategenerator.helper;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class Props {
    static final Log LOG = LogFactory.getLog(Props.class);
    static Properties properties;

    public static Properties loadRunConfigProps() {
        properties = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/config/config.properties");
            properties.load(input);

        } catch (IOException e) {
            LOG.warn(e);

        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    LOG.warn(e);
                }
            }
        }
        return properties;


    }

    public static String getProp(String key) {
        if ((key == null) || key.isEmpty()) {
            LOG.info("No key in config file");
            return "";
        } else {
            return properties.getProperty(key);
        }
    }


}
