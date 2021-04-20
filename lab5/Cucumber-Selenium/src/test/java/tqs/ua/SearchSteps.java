package tqs.ua;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SearchSteps {

    private WebDriver webDriver;

    @When("I navigate to {string}")
    public void iNavigateTo(String url) {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.get(url);
    }

    @And("I choose {string} as a departure city")
    public void iChooseDepartureCity(String city) {
        WebElement dropdown = webDriver.findElement(By.name("fromPort"));
        dropdown.findElement(By.xpath("//option[. = '" + city + "']")).click();
    }

    @And("I choose {string} as a destination city")
    public void iChoosedestinationCity(String city) {
        WebElement dropdown = webDriver.findElement(By.name("toPort"));
        dropdown.findElement(By.xpath("//option[. = '" + city + "']")).click();
    }

    @And("I press button {string}")
    public void iPressButton(String buttonName) {
        webDriver.findElement(By.xpath("//input[@value=\'" + buttonName + "\']")).click();
        
    }

    @And("I type {string} in {string}")
    public void iTypeForm(String value, String location) {
        webDriver.findElement(By.id(location)).sendKeys(value);
    }

    @And("I type {string}")
    public void iType(String searchQuery) {
        webDriver.findElement(By.name("q")).sendKeys(searchQuery);
    }

    @And("I press Enter")
    public void iPressEnter() {
        webDriver.findElement(By.name("q")).sendKeys(Keys.ENTER);
    }

    @Then("I should be shown results including {string}")
    public void iShouldBeShownResultsIncluding(String result) {
        try {
            webDriver.findElement(
                    By.xpath("//*[contains(text(), '" + result + "')]"));
        } catch (NoSuchElementException e) {
            throw new AssertionError(
                    "\"" + result + "\" not available in results");
        } finally {
            webDriver.quit();
        }
    }

}