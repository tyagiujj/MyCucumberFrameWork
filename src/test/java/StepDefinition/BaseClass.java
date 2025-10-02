package StepDefinition;

import java.util.Properties;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import PageObject.AddNewCustomerPage;
import PageObject.LoginPage;
import PageObject.SearchCustomerPage;

/*Parent Class*/
public class BaseClass {
    public static WebDriver driver;
    public LoginPage loginpage;
    public SearchCustomerPage SearchCustPg;
    public AddNewCustomerPage addnewCustp;
    public static String generatedEmail; // Store email for search
    public static String generatedName; // Store name for search

    // Generate unique email id
    public String generateEmailId() {
        return RandomStringUtils.randomAlphabetic(5) + "_" + System.currentTimeMillis() + "@test.com";
    }
}