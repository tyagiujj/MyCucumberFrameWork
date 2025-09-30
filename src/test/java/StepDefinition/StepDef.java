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

import java.time.Duration;

public class StepDef {
    
    public WebDriver driver;
    public LoginPage loginpage;
    public  AddNewCustomerPage addnewCustp;
    
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
       // Wait for button
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
      // Wait for logout link
        loginpage.clickLoginButton();
    }

    @Then("close Browser")
    public void close_browser() {
        driver.quit();
    }
    
    ///////////////Add new Customer////////////////
    @Then("User can view Dashboard")
    public void user_can_view_dashboard() {
    	 addnewCustp = new AddNewCustomerPage(driver);
    	String ActualTitle=addnewCustp.getPageTitle();
    	String expectedTitle="Dashboard / nopCommerce administration";
    	if(ActualTitle.equalsIgnoreCase(expectedTitle)) {
    		Assert.assertTrue(true);
    	}
    	else {
    		Assert.assertTrue(false);
    	}
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
    	String ActualTitle=addnewCustp.getPageTitle();
    	String expectedTitle="Add a new customer / nopCommerce administration";
    	if(ActualTitle.equalsIgnoreCase(expectedTitle)) {
    		Assert.assertTrue(true);
    	}
    	else {
    		Assert.assertTrue(false);
    	}
    }

    @When("user enter customer info")
    public void user_enter_customer_info() {
    	addnewCustp.enterEmail("cs129@gmail.com");
    	//addnewCustp.enterEmail(generateEmailId() + "@gmail.com");
    	addnewCustp.enterPassword("test1");
    	addnewCustp.enterFirstName("Ujjwal");
    	addnewCustp.enterLastName("Tyagi");
    	addnewCustp.enterGender("Female");
    	//addnewCustp.enterDob("6/13/1988");
    	addnewCustp.enterCompanyName("Selenium with java");
    	addnewCustp.enterAdminContent("Admin content");
    	addnewCustp.enterManagerOfVendor("Vendor 1");

    
    }

    @When("click on save button")
    public void click_on_save_button() {
    	addnewCustp.clickOnSave();
       
    }

    @Then("User can view confirmation message {string}")
    public void user_can_view_confirmation_message(String expectedConfirmationmessage) {
    	String bodytagText= driver.findElement(By.tagName("Body")).getText();
    	if(bodytagText.contains(expectedConfirmationmessage)) {
    		Assert.assertTrue(true);
    	}
    	else {
    		Assert.assertTrue(false);
    	}
    	
        
    }

  

}