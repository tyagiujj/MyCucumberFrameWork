package StepDefinition;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.junit.Assert;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import PageObject.AddNewCustomerPage;
import PageObject.LoginPage;
import PageObject.SearchCustomerPage;

import java.time.Duration;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import io.cucumber.java.Scenario;

public class StepDef extends BaseClass {
	
	private static final Logger logger = LogManager.getLogger(StepDef.class);
	
	@Before
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		loadConfig(); // Load properties
		logger.info("Setup Method Executed - WebDriver initialized");
	}
    
	@Given("User Launch Chrome browser")
	public void user_launch_chrome_browser() {
		loginpage = new LoginPage(driver);
		SearchCustPg = new SearchCustomerPage(driver);
		addnewCustp = new AddNewCustomerPage(driver);
		logger.info("Page objects initialized");
	}

	@When("User open URL {string}")
	public void user_open_url(String url) {
		// Use from config if needed, but since passed, use passed or override
		String configUrl = configProp.getProperty("url");
		driver.get(configUrl); // Prefer config for robustness
		logger.info("Opened URL from config: {}", configUrl);
	}

	@When("User enters Email as {string} and password as {string}")
	public void user_enters_email_as_and_password_as(String email, String password) {
		// Use from config for admin, but since DDT, use passed
		if ("admin@yourstore.com".equals(email)) {
			email = configProp.getProperty("adminEmail");
			password = configProp.getProperty("adminPassword");
		}
		loginpage.enterEmail(email);
		loginpage.enterPassword(password);
		logger.info("Entered credentials for user: {}", email);
	}

	@When("click on login button")
	public void click_on_login_button() {
		loginpage.clickLoginButton();
		logger.info("Clicked login button");
	}

	@Then("Page Title Should be {string}")
	public void page_title_should_be(String expectedTitle) {
		String actualTitle = driver.getTitle();
		Assert.assertEquals("Page title mismatch", expectedTitle, actualTitle);
		logger.info("Verified page title: {}", actualTitle);
	}

	@When("User Click on Log out Link")
	public void user_click_on_log_out_link() {
		loginpage.clickLogoutButton();
		logger.info("Clicked logout button");
	}

	@Then("close browser")
	public void close_browser() {
		logger.info("Browser close step executed (actual close in @After)");
	}
    
	///////////////Add new Customer////////////////
	@Then("User can view Dashboard")
	public void user_can_view_dashboard() {
		String actualTitle = addnewCustp.getPageTitle();
		String expectedTitle = "Dashboard / nopCommerce administration";
		Assert.assertEquals("Dashboard title mismatch", expectedTitle, actualTitle);
		logger.info("Verified dashboard title");
	}

	@When("User click on customers Menu")
	public void user_click_on_customers_menu() {
		addnewCustp.clickOnCustomersMenu();
		logger.info("Clicked customers menu");
	}

	@When("click on customers Menu Item")
	public void click_on_customers_menu_item() {
		addnewCustp.clickOnCustomersMenuItem();
		logger.info("Clicked customers menu item");
	}

	@When("click on Add new Button")
	public void click_on_add_new_button() {
		addnewCustp.clickOnAddnew();
		logger.info("Clicked add new button");
	}

	@Then("User can view Add new customers page")
	public void user_can_view_add_new_customers_page() {
		String actualTitle = addnewCustp.getPageTitle();
		String expectedTitle = "Add a new customer / nopCommerce administration";
		Assert.assertEquals("Add new customer page title mismatch", expectedTitle, actualTitle);
		logger.info("Verified add new customer page title");
	}

	@When("user enter customer info")
	public void user_enter_customer_info() {
		String uniqueId = UUID.randomUUID().toString().substring(0, 8); // Generate a unique ID
		generatedEmail = "customer_" + uniqueId + "@test.com"; // Or use generateEmailId()
		// generatedEmail = generateEmailId(); // You can switch to this
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
		logger.info("Entered customer info for email: {}", generatedEmail);
	}

	@When("click on save button")
	public void click_on_save_button() {
		addnewCustp.clickOnSave();
		logger.info("Clicked save button");
	}

	@Then("User can view confirmation message {string}")
	public void user_can_view_confirmation_message(String expectedConfirmationMessage) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'alert-success')]")));
		String bodyTagText = driver.findElement(By.tagName("body")).getText();
		Assert.assertTrue("Confirmation message not found!", bodyTagText.contains(expectedConfirmationMessage));
		logger.info("Verified confirmation message: {}", expectedConfirmationMessage);
	}

	/////Search Customer////
	@When("Enter customer EMail")
	public void enter_customer_e_mail() {
		SearchCustPg.enterEmailAdd(generatedEmail);
		logger.info("Entered email for search: {}", generatedEmail);
	}

	@When("Click on search button")
	public void click_on_search_button() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//table[@id='customers-grid']//tr")));
		SearchCustPg.clickOnSearchButton();
		logger.info("Clicked search button");
	}

	@Then("User should found Email in the Search table")
	public void user_should_found_email_in_the_search_table() {
		boolean status = SearchCustPg.searchCustomerByEmail(generatedEmail);
		Assert.assertTrue("Email not found in the search table!", status);
		logger.info("Verified email in search table");
	}
    
	@When("Enter customer FirstName")
	public void enter_customer_first_name() {
		String[] nameParts = generatedName.split(" ");
		SearchCustPg.enterFirstName(nameParts[0]);
		logger.info("Entered first name for search: {}", nameParts[0]);
	}

	@When("Enter customer LastName")
	public void enter_customer_last_name() {
		String[] nameParts = generatedName.split(" ");
		SearchCustPg.enterLastName(nameParts[1]);
		logger.info("Entered last name for search: {}", nameParts[1]);
	}

	@Then("User should found Name in the Search table")
	public void user_should_found_name_in_the_search_table() {
		boolean status = SearchCustPg.searchCustomerByName(generatedName);
		Assert.assertTrue("Name not found in the search table!", status);
		logger.info("Verified name in search table");
	}

	@After
	public void TearDown(Scenario scenario) {
		logger.info("Tear Down Method Executed for scenario: {}", scenario.getName());
		if (scenario.isFailed()) {
			byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot, "image/png", "Failure Screenshot - " + scenario.getName());
			logger.error("Scenario failed: {} - Screenshot attached", scenario.getName());
		}
		if (driver != null) {
			driver.quit();
			logger.info("WebDriver quit");
		}
	}
}