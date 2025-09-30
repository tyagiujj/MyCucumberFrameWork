package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    
    private WebDriver driver;

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // WebElements with @FindBy annotations
    @FindBy(id = "Email")
    private WebElement email;

    @FindBy(id = "Password")
    private WebElement passwordField; // Renamed for consistency

    @FindBy(xpath = "//button[@type='submit']") // More robust locator
	public WebElement loginButton;
    
    @FindBy(xpath = "//a[@href='/logout']") // More robust locator
	public WebElement logout;

    // Methods to interact with page elements
    public void enterEmail(String username) {
        email.clear();
        email.sendKeys(username);
    }

    public void enterPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public void clickLogoutButton() { // Renamed for consistency
        logout.click();
    }

    // Combined method for login action
    public void login(String username, String password) {
        enterEmail(username);
        enterPassword(password);
        clickLoginButton();
    }
}