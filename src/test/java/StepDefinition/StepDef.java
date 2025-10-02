package StepDefinition;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.junit.Assert;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import PageObject.AddNewCustomerPage;
import PageObject.LoginPage;
import PageObject.SearchCustomerPage;

import java.time.Duration;
import java.util.UUID;

public class StepDef extends BaseClass {
    
    @Given("User Launch Chrome browser")
    public void user_launch_chrome_browser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        loginpage = new LoginPage(driver);
        SearchCustPg = new SearchCustomerPage(driver);
        addnewCustp = new AddNewCustomerPage(driver);
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
        loginpage.clickLoginButton();
    }

    @Then("Page Title Should be {string}")
    public void page_title_should_be(String expectedTitle) {
        String actualTitle = driver.getTitle();
        Assert.assertEquals("Page title mismatch", expectedTitle, actualTitle);
    }

    @When("User Click on Log out Link")
    public void user_click_on_log_out_link() {
        loginpage.clickLogoutButton();
    }

    @Then("close browser")
    public void close_browser() {
        driver.quit();
    }
    
    ///////////////Add new Customer////////////////
    @Then("User can view Dashboard")
    public void user_can_view_dashboard() {
        String actualTitle = addnewCustp.getPageTitle();
        String expectedTitle = "Dashboard / nopCommerce administration";
        Assert.assertEquals("Dashboard title mismatch", expectedTitle, actualTitle);
    }

    @When("User click on customers Menu")
    public void user_click_on_customers_menu() {
        addnewCustp.clickOnCustomersMenu();
    }

    @When("click on customers Menu Item")
    public void click_on_customers_menu_item() {
        addnewCustp.clickOnCustomersMenuItem();
    }

    @When("click on Add new Button")
    public void click_on_add_new_button() {
        addnewCustp.clickOnAddnew();
    }

    @Then("User can view Add new customers page")
    public void user_can_view_add_new_customers_page() {
        String actualTitle = addnewCustp.getPageTitle();
        String expectedTitle = "Add a new customer / nopCommerce administration";
        Assert.assertEquals("Add new customer page title mismatch", expectedTitle, actualTitle);
    }

    @When("user enter customer info")
    public void user_enter_customer_info() {
        String uniqueId = UUID.randomUUID().toString().substring(0, 8); // Generate a unique ID
        generatedEmail = "customer_" + uniqueId + "@test.com"; // Store email
        String uniqueFirstName = "Ujjwal_" + uniqueId;
        String uniqueLastName = "Tyagi_" + uniqueId;
        generatedName = uniqueFirstName + " " + uniqueLastName; // Store name
        String uniqueCompanyName = "SeleniumJava_" + uniqueId;

        addnewCustp.setEmail(generatedEmail);
        addnewCustp.setPassword("test1");
        addnewCustp.setFirstName(uniqueFirstName);
        addnewCustp.setLastName(uniqueLastName);
        addnewCustp.setGender("Male");
        addnewCustp.setCompanyName(uniqueCompanyName);
    }

    @When("click on save button")
    public void click_on_save_button() {
        addnewCustp.clickOnSave();
    }

    @Then("User can view confirmation message {string}")
    public void user_can_view_confirmation_message(String expectedConfirmationMessage) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'alert-success')]")));
        String bodyTagText = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Confirmation message not found!", bodyTagText.contains(expectedConfirmationMessage));
    }

    /////Search Customer////
    @When("Enter customer EMail")
    public void enter_customer_e_mail() {
        SearchCustPg.enterEmailAdd(generatedEmail);
    }

    @When("Click on search button")
    public void click_on_search_button() {
        try {
            Thread.sleep(2000); // Wait for table to load
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SearchCustPg.clickOnSearchButton();
    }

    @Then("User should found Email in the Search table")
    public void user_should_found_email_in_the_search_table() {
        boolean status = SearchCustPg.searchCustomerByEmail(generatedEmail);
        Assert.assertTrue("Email not found in the search table!", status);
    }
    
    @When("Enter customer FirstName")
    public void enter_customer_first_name() {
        String[] nameParts = generatedName.split(" ");
        SearchCustPg.enterFirstName(nameParts[0]);
    }

    @When("Enter customer LastName")
    public void enter_customer_last_name() {
        String[] nameParts = generatedName.split(" ");
        SearchCustPg.enterLastName(nameParts[1]);
    }

    @Then("User should found Name in the Search table")
    public void user_should_found_name_in_the_search_table() {
        boolean status = SearchCustPg.searchCustomerByName(generatedName);
        Assert.assertTrue("Name not found in the search table!", status);
    }
}