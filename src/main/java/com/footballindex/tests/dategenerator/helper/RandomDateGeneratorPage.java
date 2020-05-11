package com.footballindex.tests.dategenerator.helper;


import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import java.util.Arrays;

import java.util.List;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.xpath;


@FieldDefaults(level = AccessLevel.PRIVATE)
public class RandomDateGeneratorPage extends BasePage{
    static final long DRIVER_WAIT_TIME = 30;
     final By randomDateButton = xpath("//button[text() = 'Generate random Dates']");
     final By howDateTextField = cssSelector("#option-count-147fe348");
     final By dateOutputFormatTextField = cssSelector("#option-format-147fe348");
     final By customDateFormatTextField = cssSelector("#option-custom-format-147fe348");
     final By randomNumberTextArea = cssSelector("[class='data randomjson']");
     final By startDateTextField = cssSelector("#option-start-147fe348");
     final By endDateTextField= cssSelector("#option-end-147fe348");




    public void openRandomDateGeneratorPage(){
        SharedDriver.getWebDriver().get(Props.getProp("url"));
    }


    public void enterTheNumberOfDatesToGenerate(String numberOfDays) throws InterruptedException {
        waitForExpectedElement(howDateTextField,DRIVER_WAIT_TIME).clear();
        Thread.sleep(2000);
        waitForExpectedElement(howDateTextField,DRIVER_WAIT_TIME).sendKeys(numberOfDays);
    }
    public String getCountOfDatesToGenerate(){
        return waitForExpectedElement(howDateTextField,DRIVER_WAIT_TIME).getAttribute("value");
    }

    public void selectDateFormat(String dateFormat)  {
        Select select = new Select(waitForExpectedElement(dateOutputFormatTextField,DRIVER_WAIT_TIME));
        select.selectByVisibleText(dateFormat);
    }

   public List<String> getRandomNumberGenerated(){
     return Arrays.asList(waitForExpectedElement(randomNumberTextArea,DRIVER_WAIT_TIME).getAttribute("value").trim().split("\n"));
    }

    public void enterStartDate(String startDate){
        waitForExpectedElement(startDateTextField,DRIVER_WAIT_TIME).clear();
        waitForExpectedElement(startDateTextField,DRIVER_WAIT_TIME).sendKeys(startDate);
    }


    public void enterEndDate(String startDate){
        waitForExpectedElement(endDateTextField,DRIVER_WAIT_TIME).clear();
        waitForExpectedElement(endDateTextField,DRIVER_WAIT_TIME).sendKeys(startDate);
    }

    public void clickOnRandomDatesButton(){
        waitForExpectedElement(randomDateButton,DRIVER_WAIT_TIME).click();
    }

  public void enterCustomDateFormat(String customDate){
      waitForExpectedElement(customDateFormatTextField,DRIVER_WAIT_TIME).clear();
      waitForExpectedElement(customDateFormatTextField,DRIVER_WAIT_TIME).sendKeys(customDate);
  }
}
