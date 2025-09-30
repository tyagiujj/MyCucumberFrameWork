package StepDefinition;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.junit.Assert;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import PageObject.LoginPage;

import java.time.Duration;

public class StepDef {
    
    public WebDriver driver;
    public LoginPage loginpage;
    
    @Given("User Launch Chrome browser")
    public void user_launch_chrome_browser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Add implicit wait
        loginpage = new LoginPage(driver);
    }

    @When("User open URL {string}")
    public void user_open_url(String url) {
        driver.get(url);
    }

    @When("User enters Email as {string} and password as {string}")
    public void user_enters_email_as_and_password_as(String email, String password) {
        loginpage.enterEmail(email);
        loginpage.enterPassword(password);
    }

    @When("click on login button")
    public void click_on_login_button() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(loginpage.loginButton)); // Wait for button
        loginpage.clickLoginButton();
    }

    @Then("Page Title Should be {string}")
    public void page_title_should_be(String expectedTitle) {
        String actualTitle = driver.getTitle();
        Assert.assertEquals("Page title mismatch", expectedTitle, actualTitle);
    }

    @When("User Click on Log out Link")
    public void user_click_on_log_out_link() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(loginpage.logout)); // Wait for logout link
        loginpage.clickLogoutButton();
    }

    @Then("close Browser")
    public void close_browser() {
        driver.quit();
    }
}