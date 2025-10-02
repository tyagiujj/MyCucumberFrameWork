package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AddNewCustomerPage {

    public WebDriver driver;
    private WebDriverWait wait;

    public AddNewCustomerPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // Customers menu
    @FindBy(xpath = "//a[@href='#']//p[contains(text(),'Customers')]")
    @CacheLookup
    WebElement lnkCustomers_menu;

    // Customers menu item
    @FindBy(xpath = "//a[@href='/Admin/Customer/List']//p[contains(text(),'Customers')]")
    @CacheLookup
    WebElement lnkCustomers_menuitem;

    // Add new button
    @FindBy(xpath = "//a[@class='btn btn-primary']")
    @CacheLookup
    WebElement btnAddnew;

    // Email
    @FindBy(id = "Email")
    @CacheLookup
    WebElement txtEmail;

    // Password
    @FindBy(id = "Password")
    @CacheLookup
    WebElement txtPassword;

    // First name
    @FindBy(id = "FirstName")
    @CacheLookup
    WebElement txtFirstName;

    // Last name
    @FindBy(id = "LastName")
    @CacheLookup
    WebElement txtLastName;

    // Male gender
    @FindBy(id = "Gender_Male")
    @CacheLookup
    WebElement rdMaleGender;

    // Female gender
    @FindBy(id = "Gender_Female")
    @CacheLookup
    WebElement rdFemaleGender;

    // DOB
    @FindBy(id = "DateOfBirth")
    @CacheLookup
    WebElement txtDob;

    // Company name
    @FindBy(id = "Company")
    @CacheLookup
    WebElement txtCompanyName;

    // Admin comment
    @FindBy(id = "AdminComment")
    @CacheLookup
    WebElement txtAdminComment;

    // Save button
    @FindBy(name = "save")
    @CacheLookup
    WebElement btnSave;

    // Actions
    public void clickOnCustomersMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(lnkCustomers_menu));
        lnkCustomers_menu.click();
    }

    public void clickOnCustomersMenuItem() {
        wait.until(ExpectedConditions.elementToBeClickable(lnkCustomers_menuitem));
        lnkCustomers_menuitem.click();
    }

    public void clickOnAddnew() {
        wait.until(ExpectedConditions.elementToBeClickable(btnAddnew));
        btnAddnew.click();
    }

    public void setEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(txtEmail));
        txtEmail.clear();
        txtEmail.sendKeys(email);
    }

    public void setPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(txtPassword));
        txtPassword.clear();
        txtPassword.sendKeys(password);
    }

    public void setFirstName(String fname) {
        wait.until(ExpectedConditions.visibilityOf(txtFirstName));
        txtFirstName.clear();
        txtFirstName.sendKeys(fname);
    }

    public void setLastName(String lname) {
        wait.until(ExpectedConditions.visibilityOf(txtLastName));
        txtLastName.clear();
        txtLastName.sendKeys(lname);
    }

    public void setGender(String gender) {
        if (gender.equalsIgnoreCase("Male")) {
            wait.until(ExpectedConditions.elementToBeClickable(rdMaleGender));
            rdMaleGender.click();
        } else if (gender.equalsIgnoreCase("Female")) {
            wait.until(ExpectedConditions.elementToBeClickable(rdFemaleGender));
            rdFemaleGender.click();
        } else {
            wait.until(ExpectedConditions.elementToBeClickable(rdMaleGender));
            rdMaleGender.click(); // default
        }
    }

    public void setDob(String dob) {
        wait.until(ExpectedConditions.visibilityOf(txtDob));
        txtDob.clear();
        txtDob.sendKeys(dob);
    }

    public void setCompanyName(String comname) {
        wait.until(ExpectedConditions.visibilityOf(txtCompanyName));
        txtCompanyName.clear();
        txtCompanyName.sendKeys(comname);
    }

    public void setAdminComment(String comment) {
        wait.until(ExpectedConditions.visibilityOf(txtAdminComment));
        txtAdminComment.clear();
        txtAdminComment.sendKeys(comment);
    }

    public void clickOnSave() {
        wait.until(ExpectedConditions.elementToBeClickable(btnSave));
        btnSave.click();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}