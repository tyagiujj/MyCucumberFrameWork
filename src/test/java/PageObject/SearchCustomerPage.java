package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class SearchCustomerPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public SearchCustomerPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    // Email field
    @FindBy(id = "SearchEmail")
    @CacheLookup
    WebElement txtEmail;

    // First name field
    @FindBy(id = "SearchFirstName")
    @CacheLookup
    WebElement txtFirstName;

    // Last name field
    @FindBy(id = "SearchLastName")
    @CacheLookup
    WebElement txtLastName;

    // Search button
    @FindBy(id = "search-customers")
    @CacheLookup
    WebElement btnSearch;

    // Methods
    public void enterEmailAdd(String email) {
        wait.until(ExpectedConditions.visibilityOf(txtEmail));
        txtEmail.clear();
        txtEmail.sendKeys(email);
    }

    public void enterFirstName(String firstName) {
        wait.until(ExpectedConditions.visibilityOf(txtFirstName));
        txtFirstName.clear();
        txtFirstName.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        wait.until(ExpectedConditions.visibilityOf(txtLastName));
        txtLastName.clear();
        txtLastName.sendKeys(lastName);
    }

    public void clickOnSearchButton() {
        wait.until(ExpectedConditions.elementToBeClickable(btnSearch));
        btnSearch.click();
    }

    public boolean searchCustomerByEmail(String email) {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//table[@id='customers-grid']//tr")));
        WebElement table = driver.findElement(By.xpath("//table[@id='customers-grid']"));
        return table.getText().toLowerCase().contains(email.toLowerCase());
    }

    public boolean searchCustomerByName(String name) {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//table[@id='customers-grid']//tr")));
        WebElement table = driver.findElement(By.xpath("//table[@id='customers-grid']"));
        return table.getText().toLowerCase().contains(name.toLowerCase());
    }
}