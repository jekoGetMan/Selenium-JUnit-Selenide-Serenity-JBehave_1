package pages;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static java.lang.String.format;
import static org.openqa.selenium.By.xpath;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

@DefaultUrl("https://www.spotify.com/us/signup/?forward_url=https%3A%2F%2Fwww.spotify.com%2Fus%2Faccount%2Foverview%2F%3F_ga%3D2.68218707.314742187.1552594654-368280494.1552594654")
public class SignUpPage extends PageObject {


    private By emailField = xpath("//*[@id=\"register-email\"]");
    private By confirmEmailField = xpath("//*[@id=\"register-confirm-email\"]");
    private By passwordField = xpath("//*[@id=\"register-password\"]");
    private By nameField = xpath("//*[@id=\"register-displayname\"]");
    private By monthDropDown = By.cssSelector("#register-dob-month");
    String monthDropDownOption = "//select[@id='register-dob-month']//option[text()='%s']";
    private By dayField = By.cssSelector("#register-dob-day");
    private By Year = By.cssSelector("#register-dob-year");
    String sexRadioButton = "//li[@id=\"li-gender\"]//label[normalize-space()=\"%s\"]//input";
    private By shareCheckBox = By.cssSelector("#li-thirdparty > label");
    private By registerButton = xpath("//*[@id=\"register-button-email-submit\"]");
    private By errorLabel = xpath("//label[@class='has-error' and string-length(text())>0]");
    String errorByText = "//label[@class=\"has-error\" and text()=\"%s\"]"; // локатор ошибки в зависимости от текста ошибки


    public SignUpPage typeEmail(String email) {
        find(emailField).sendKeys(email);
        return this;
    }

    public SignUpPage typeConfirmEmail(String email) {
        find(confirmEmailField).sendKeys(email);
        return this;
    }

    public SignUpPage typePassword(String password) {
        find(passwordField).sendKeys(password);
        return this;
    }

    public SignUpPage typeName(String something) {
        find(nameField).sendKeys(something);
        return this;
    }

    public SignUpPage setMonth(String month) {
        find(monthDropDown).click();
        find(xpath(format(monthDropDownOption, month))).waitUntilVisible().click();
        return this;
    }

    public SignUpPage typeDay(String day) {
        find(dayField).sendKeys(day);
        return this;
    }

    public SignUpPage typeYear(String year) {
        find(Year).sendKeys(year);
        return this;
    }

    public SignUpPage setSex(String value) {
        find(xpath(String.format(sexRadioButton,value))).click();
        return this;
    }

    public SignUpPage setShare(boolean value) {
        WebElement checkbox = find(shareCheckBox);
        if (!checkbox.isSelected() == value) {
            checkbox.click();
        }
        return this;
    }

    public SignUpPage clickSignUpButton() {
        find(registerButton).click();
        return this;
    }

    public List<WebElementFacade> getErrors() {
        return findAll(errorLabel);
    }

    public String getErrorByNumber(int number) {
        return getErrors().get(number - 1).getText();
    }

    public boolean isErrorVisible(String message) {
        return findAll(xpath(format(errorByText, message))).size() > 0
                && findAll(xpath(String.format(errorByText, message))).get(0).isDisplayed();
    }
}