package StepDefinition;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import PageObject.AddNewCustomerPage;
import PageObject.LoginPage;
import PageObject.SearchCustomerPage;

public class BaseClass {
    public static WebDriver driver;
    public LoginPage loginpage;
    public SearchCustomerPage SearchCustPg;
    public AddNewCustomerPage addnewCustp;
    public static String generatedEmail; // Store email for search
    public static String generatedName; // Store name for search
    public Properties configProp;
    private static final Logger logger = LogManager.getLogger(BaseClass.class);

    // Load config properties
    public void loadConfig() {
        configProp = new Properties();
        try {
            FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
            configProp.load(fis);
            logger.info("Configuration properties loaded successfully");
        } catch (IOException e) {
            logger.error("Error loading config.properties", e);
            throw new RuntimeException("Config file not found", e);
        }
    }

    // Generate unique email id
    public String generateEmailId() {
        String uniqueId = UUID.randomUUID().toString().substring(0, 8);
        logger.info("Generated unique email ID: test_{}@test.com", uniqueId);
        return "test_" + uniqueId + "@test.com";
    }
}