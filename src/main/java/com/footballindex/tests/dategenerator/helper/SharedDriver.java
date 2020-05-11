package com.footballindex.tests.dategenerator.helper;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class SharedDriver extends EventFiringWebDriver {
    static final Logger LOG = LoggerFactory.getLogger(SharedDriver.class);
    static RemoteWebDriver REAL_DRIVER = null;
    static String BROWSER ;
    static final String PLATFORM;
    static String DRIVER_PATH;
    static final String FILE_SEPARATOR ;
    private static final String DRIVER_ROOT = File.separator + "tools";

    static final Thread CLOSE_THREAD = new Thread() {
        @Override
        public void run() {
            REAL_DRIVER.quit();
        }
    };


    private SharedDriver() {
        super(REAL_DRIVER);
    }

    public static WebDriver getWebDriver() {
        return REAL_DRIVER;
    }

    static {
        try {
            Props.loadRunConfigProps();
        } catch (Exception e) {
            LOG.warn(String.valueOf(e));
        }
        LOG.info("Loading configuration properties file");

        FILE_SEPARATOR = System.getProperty("file.separator");
        PLATFORM = System.getProperty("os.name").toLowerCase().trim();
        //BROWSER = Props.getProp("browser");
        BROWSER =  System.getProperty("browser");

        try {
            initiateDriverForBrowser();
        } catch (Exception e) {

            LOG.warn(String.valueOf(e));
        }

        REAL_DRIVER.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        REAL_DRIVER.manage().window().maximize();
        Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);
    }

    public static void initiateDriverForBrowser() {
        if (BROWSER == null) {
            BROWSER = System.getenv("browser");
            if (BROWSER == null) {
                //default to chrome if no browser if supplied in the commandline
                BROWSER = "chrome";
            }
        }
        switch (BROWSER) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", getDriverPath());
                REAL_DRIVER = new ChromeDriver();
                break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver", getDriverPath() );
                FirefoxOptions options = new FirefoxOptions();
                REAL_DRIVER = new FirefoxDriver(options);
                break;
        }

        LOG.info("Opening Browser...." + BROWSER);
        REAL_DRIVER.manage().deleteAllCookies();
    }


    private static String getDriverPath() {
        String userDir;
        userDir = System.getProperty("user.dir");

        try {
            if (BROWSER.equals("chrome") && PLATFORM.contains("mac")) {
                DRIVER_PATH = userDir  + DRIVER_ROOT + FILE_SEPARATOR + "chromedriver"
                        + FILE_SEPARATOR + "mac" + FILE_SEPARATOR
                        + "chromedriver";
            } else if (BROWSER.equals("firefox") && PLATFORM.contains("mac")) {
                DRIVER_PATH = userDir + DRIVER_ROOT + FILE_SEPARATOR + "geckodriver"
                        + FILE_SEPARATOR + "mac" + FILE_SEPARATOR
                        + "geckodriver";
            } else if (BROWSER.equals("chrome") && PLATFORM.contains("windows")) {
                DRIVER_PATH = userDir + DRIVER_ROOT + FILE_SEPARATOR + "chromedriver"
                        + FILE_SEPARATOR + "win32" + FILE_SEPARATOR
                        + "chromedriver.exe";
            } else if (BROWSER.equals("firefox") && PLATFORM.contains("win")) {
                DRIVER_PATH = userDir + DRIVER_ROOT + FILE_SEPARATOR + "geckodriver"
                        + FILE_SEPARATOR + "win32" + FILE_SEPARATOR
                        + "geckodriver.exe";
            }
            } catch(Exception e){
                LOG.error(String.valueOf(e));
            }
            return DRIVER_PATH;

        }

        @Override
        public void close () {
            if (Thread.currentThread() != CLOSE_THREAD) {
                throw new UnsupportedOperationException(
                        "You shouldn't close this WebDriver. It's shared and will close when the JVM exits.");
            }
            super.close();
        }
    }
