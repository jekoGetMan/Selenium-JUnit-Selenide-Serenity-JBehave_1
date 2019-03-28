package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static java.lang.String.format;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class SignUpPage {
    private WebDriver driver;

    public SignUpPage(WebDriver driver) {
        this.driver = driver;
    }

    private By emailField = By.xpath("//*[@id=\"register-email\"]");
    private By confirmEmailField = By.xpath("//*[@id=\"register-confirm-email\"]");
    private By passwordField = By.xpath("//*[@id=\"register-password\"]");
    private By nameField = By.xpath("//*[@id=\"register-displayname\"]");
    private By monthDropDown = By.cssSelector("#register-dob-month");
    String monthDropDownOption = "//select[@id='register-dob-month']//option[text()='%s']";
    private By dayField = By.cssSelector("#register-dob-day");
    private By Year = By.cssSelector("#register-dob-year");
    String sexRadioButton = "//li[@id=\"li-gender\"]//label[normalize-space()=\"%s\"]//input";
    private By shareCheckBox = By.cssSelector("#li-thirdparty > label");
    private By registerButton = By.xpath("//*[@id=\"register-button-email-submit\"]");
    private By errorLabel = By.xpath("//label[@class='has-error' and string-length(text())>0]");
    String errorByText = "//label[@class=\"has-error\" and text()=\"%s\"]"; // локатор ошибки в зависимости от текста ошибки


    public SignUpPage typeEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
        return this;
    }

    public SignUpPage typeConfirmEmail(String email) {
        driver.findElement(confirmEmailField).sendKeys(email);
        return this;
    }

    public SignUpPage typePassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
        return this;
    }

    public SignUpPage typeName(String something) {
        driver.findElement(nameField).sendKeys(something);
        return this;
    }

    public SignUpPage setMonth(String month) {
        driver.findElement(monthDropDown).click();
        new WebDriverWait(driver, 5).until(visibilityOfElementLocated(By.xpath(format(monthDropDownOption, month)))).click();
        return this;
    }

    public SignUpPage typeDay(String day) {
        driver.findElement(dayField).sendKeys(day);
        return this;
    }

    public SignUpPage typeYear(String year) {
        driver.findElement(Year).sendKeys(year);
        return this;
    }

    public SignUpPage setSex(String value) {
        driver.findElement(By.xpath(String.format(sexRadioButton,value))).click();
        return this;
    }

    public SignUpPage setShare(boolean value) {
        WebElement checkbox = driver.findElement(shareCheckBox);
        if (!checkbox.isSelected() == value) {
            checkbox.click();
        }
        return this;
    }

    public SignUpPage clickSignUpButton() {
        driver.findElement(registerButton).click();
        return this;
    }

    public List<WebElement> getErrors() {
        return driver.findElements(errorLabel);
    }

    public String getErrorByNumber(int number) {
        return getErrors().get(number - 1).getText();
    }

    public boolean isErrorVisible(String message) {
        return driver.findElements(By.xpath(format(errorByText, message))).size() > 0
                && driver.findElements(By.xpath(String.format(errorByText, message))).get(0).isDisplayed();
    }
}