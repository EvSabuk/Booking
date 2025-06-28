package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class SearchStep {

    WebDriver driver;

    @Before
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }


    @Given("booking search page is opened")
    public void bookingSearchPageIsOpened() {
        driver.get("https://www.booking.com/searchresults.en-gb.html");
    }

    @When("user searches {string}")
    public void userSearches(String hotel) throws InterruptedException {
        driver.findElement(By.name("ss")).sendKeys(hotel);
        Thread.sleep(5000);
        driver.findElement(By.xpath("//h3[text() = 'About']")).click();
        Thread.sleep(5000);
        driver.findElement(By.cssSelector("[type='submit']")).click();
        Thread.sleep(5000);
    }

    @Then("{string} hotel is shown")
    public void hotelIsShown(String expectedResult) {
        List<WebElement> titles = driver.findElements(By.cssSelector("[data-testid=title]"));
        boolean isHotelFound = false;
        for (WebElement title : titles) {
            if (title.getText().equals(expectedResult)) {
                isHotelFound = true;
                break;
            }
        }
        Assert.assertTrue(isHotelFound);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @And("hotel rate is {string}")
    public void hotelRateIs(String hotelRate) {
        String reviewScore = driver.findElement(By.xpath(
                "//div[text()='Akra Kemer - Ultra All Inclusive']" +
                        "/ancestor::div[@data-testid = 'property-card-container']" +
                        "/descendant::div[@data-testid = 'review-score']" +
                        "/child::div[@aria-hidden = 'true']"
        )).getText();
        Assert.assertEquals(hotelRate,reviewScore);
    }
}