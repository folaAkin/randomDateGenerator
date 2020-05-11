package com.footballindex.tests.dategenerator.helper;


import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.footballindex.tests.dategenerator.helper.SharedDriver.getWebDriver;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

/**
 * Based on shared webdriver implementation in cucumber-jvm examples
 * A new instance of SharedDriver is created for each Scenario and passed to  Stepdef classes via Dependency Injection
 */

public class BasePage {
    private static final long DRIVER_WAIT_TIME = 10;
    private static final Logger LOG = LoggerFactory.getLogger(BasePage.class);
    protected WebDriverWait wait;
    protected WebDriver webDriver;

    protected BasePage() {
        this.webDriver = getWebDriver();
        if (webDriver == null) {
            LOG.debug("driver is null");
        } else {
            LOG.debug("the driver is not null");
        }
        this.wait = new WebDriverWait(webDriver, DRIVER_WAIT_TIME);
    }

    /*

    **
            * Find the dynamic element wait until its visible for a specified time
     *
             * @param by                Element location found by css, xpath, id etc...
            * @param waitTimeInSeconds max time to wait until element is visible
     **/
    public WebElement waitForExpectedElement(final By by, long waitTimeInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(getWebDriver(), waitTimeInSeconds);
            return wait.until(visibilityOfElementLocated(by));
        } catch (NoSuchElementException | TimeoutException e) {
            LOG.info(e.getMessage());
            return null;
        }
    }


}
