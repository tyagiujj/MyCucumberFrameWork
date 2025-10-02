package PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class SearchCustomerPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Constructor
    public SearchCustomerPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "SearchEmail")
    WebElement emailAdd;

    @FindBy(id = "search-customers")
    WebElement searchBtn;

    @FindBy(xpath = "//table[@class='table table-bordered table-hover table-striped dataTable']")
    WebElement searchResult;

    @FindBy(xpath = "//table[@id='customers-grid']//tbody/tr")
    List<WebElement> tableRows;

    @FindBy(id = "SearchFirstName")
    WebElement firstName;

    @FindBy(id = "SearchLastName")
    WebElement lastName;

    // Action method to enter email address
    public void enterEmailAdd(String email) {
        wait.until(ExpectedConditions.visibilityOf(emailAdd));
        emailAdd.sendKeys(email);
    }

    // Action method to perform click action on search button
    public void clickOnSearchButton() {
        wait.until(ExpectedConditions.elementToBeClickable(searchBtn));
        searchBtn.click();
    }

    public boolean searchCustomerByEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(searchResult));
        boolean found = false;
        int ttlRows = tableRows.size();

        for (int i = 1; i <= ttlRows; i++) {
            System.out.println("Searching row: " + i);
            WebElement webElementEmail = driver.findElement(By.xpath("//table[@id='customers-grid']//tbody/tr[" + i + "]/td[2]"));
            String actualEmailAdd = webElementEmail.getText();
            System.out.println(actualEmailAdd);

            if (actualEmailAdd.equals(email)) {
                found = true;
                break;
            }
        }

        return found;
    }

    // Action method to enter first name
    public void enterFirstName(String firstNameText) {
        wait.until(ExpectedConditions.visibilityOf(firstName));
        firstName.sendKeys(firstNameText);
    }

    // Action method to enter last name
    public void enterLastName(String lastNameText) {
        wait.until(ExpectedConditions.visibilityOf(lastName));
        lastName.sendKeys(lastNameText);
    }

    public boolean searchCustomerByName(String name) {
        wait.until(ExpectedConditions.visibilityOf(searchResult));
        boolean found = false;
        int ttlRows = tableRows.size();

        for (int i = 1; i <= ttlRows; i++) {
            WebElement webElementName = driver.findElement(By.xpath("//table[@id='customers-grid']//tbody/tr[" + i + "]/td[3]"));
            String actualName = webElementName.getText();

            if (actualName.equals(name)) {
                found = true;
                break;
            }
        }

        return found;
    }
}