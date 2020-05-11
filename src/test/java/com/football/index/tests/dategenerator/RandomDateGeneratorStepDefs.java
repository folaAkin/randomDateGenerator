package com.football.index.tests.dategenerator;

import com.footballindex.tests.dategenerator.helper.Props;
import com.footballindex.tests.dategenerator.helper.RandomDateGeneratorPage;
import com.footballindex.tests.dategenerator.helper.Util;
import cucumber.api.java.en.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@FieldDefaults(level = AccessLevel.PRIVATE)
public class RandomDateGeneratorStepDefs {
    static final Logger LOG = LoggerFactory.getLogger(RandomDateGeneratorStepDefs.class);
    final RandomDateGeneratorPage randomDateGeneratorPage;
    List<String> randomNumbersGeneratedInTextArea;
    int countOfDatesEntered;

    public RandomDateGeneratorStepDefs(RandomDateGeneratorPage randomDateGeneratorPage) {
        this.randomDateGeneratorPage = randomDateGeneratorPage;
    }

    @Given("^I am on code beautify random date generator page$")
    public void iAmOnCodeCodeBeautifyRandomDateGeneratorPage() {
        randomDateGeneratorPage.openRandomDateGeneratorPage();
        LOG.info("Navigating to random dates generator page");
    }

    @When("^I enter (.*) random dates to generate$")
    public void iEnterRandomDatesToGenerate(String numberOfDates) throws InterruptedException {
        randomDateGeneratorPage.enterTheNumberOfDatesToGenerate(numberOfDates);

    }

    @And("^I select a date format(.*)$")
    public void iSelectADate(String dateFormat) {
        randomDateGeneratorPage.selectDateFormat(dateFormat.trim());
    }

    @Then("^the dates (.*) generated should be the same format selected$")
    public void theDatesYYYYMMDDHhMmSsGeneratedShouldBeTheSameFormatSelected(String dateFormat) {
        randomNumbersGeneratedInTextArea = randomDateGeneratorPage.getRandomNumberGenerated();
        randomNumbersGeneratedInTextArea.forEach(randomNumberGeneratedInTextArea -> {
            LOG.info("Random date is " + randomNumberGeneratedInTextArea);
            assertThat(Util.isDateFormatValid(randomNumberGeneratedInTextArea, dateFormat)).as("Date format is not valid").isTrue();
        });
    }

    @And("^I enter (.*) and (.*)$")
    public void iEnterAnd(String startDate, String endDate) {
        randomDateGeneratorPage.enterStartDate(Props.getProp(startDate));
        randomDateGeneratorPage.enterEndDate(Props.getProp(endDate));
    }

    @And("^I generate random dates$")
    public void iGenerateRandomNumbers() {
        randomDateGeneratorPage.clickOnRandomDatesButton();
    }

    @And("^I enter custom date: (.*)$")
    public void iSelectCumstomDateFormat(String customDateFormat) {
        randomDateGeneratorPage.enterCustomDateFormat(customDateFormat.trim());
    }

    @Then("^the random dates generated should be equal to the number of dates to generate$")
    public void theRandomDatesGeneratedShouldBeEqualToTheNumberOfDatesToGenerate() {
        randomNumbersGeneratedInTextArea = randomDateGeneratorPage.getRandomNumberGenerated();
        assertThat(countOfDatesEntered).as("Count of dates to generate differs from dates generated").isEqualTo(randomNumbersGeneratedInTextArea.size());
    }

    @And("^I get the count of dates to generate$")
    public void iGetTheCountOfDatesToGenerate() {
        countOfDatesEntered = Integer.parseInt(randomDateGeneratorPage.getCountOfDatesToGenerate());
    }


}
