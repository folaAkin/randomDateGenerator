@randomDateGen
Feature: Random Dates Generation

  As a user
  I want to generate random dates
  In order to use different dates format

  Background:
    Given I am on code beautify random date generator page


  Scenario Outline: Generate random dates
    When I enter <number_of_dates_to_generate> random dates to generate
    And I select a date format <dateFormat>
    And I enter <startDate> and <endDate>
    And I generate random dates
    Then the dates <format> generated should be the same format selected

    Examples:
      | number_of_dates_to_generate | dateFormat          | startDate | endDate   | format              |
      | 5                           | YYYY-MM-DD hh:mm:ss | startDate | endDate   | yyyy-MM-dd HH:mm:ss |
      | 15                          | MM-DD-YYYY hh:mm:ss | startDate | startDate | MM-dd-yyyy HH:mm:ss |

  Scenario Outline: Generate random dates with custom dates
    When I enter <number_of_dates_to_generate> random dates to generate
    And I select a date format <dateFormat>
    And I enter custom date: <customDate>
    And I enter <startDate> and <endDate>
    And I generate random dates
    Then the dates <format> generated should be the same format selected


    Examples:
      | number_of_dates_to_generate | dateFormat         | customDate | startDate | endDate | format     |
      | 10                          | Custom date format | YYYY-MM-DD | startDate | endDate | yyyy-MM-dd |
      | 5                           | Custom date format | MM/DD/YYYY | startDate | endDate | MM/dd/yyyy |

  @randomDateGen
  Scenario: Verify the number of random number generated
    When I enter 20 random dates to generate
    And I get the count of dates to generate
    And I select a date format YYYY-MM-DD hh:mm:ss
    And I enter startDate and endDate
    And I generate random dates
    Then the random dates generated should be equal to the number of dates to generate


