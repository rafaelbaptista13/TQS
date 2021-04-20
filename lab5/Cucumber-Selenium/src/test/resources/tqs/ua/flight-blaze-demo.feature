Feature: Buy a flight in Blaze Demo
 
  Scenario: Seek for Selenium-Jupiter documentation
    When I navigate to "https://blazedemo.com/"
    And I choose "Boston" as a departure city
    And I choose "London" as a destination city
    And I press button "Find Flights"
    And I press button "Choose This Flight"
    And I type "Joao Ferreira" in "inputName"
    And I type "Boston" in "address"
    And I type "Boston" in "city"
    And I type "Boston" in "state"
    And I type "12345" in "zipCode"
    And I type "8127182771821728" in "creditCardNumber"
    And I type "2021" in "creditCardYear"
    And I type "Joao Ferreira" in "nameOnCard"
    And I press button "Purchase Flight"
    Then I should be shown results including "Thank you for your purchase today!"