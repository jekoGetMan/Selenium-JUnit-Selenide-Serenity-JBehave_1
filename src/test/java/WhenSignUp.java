import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.SignUpPage;
import org.junit.Test;
import steps.SignUpSteps;


import java.util.concurrent.TimeUnit;

@RunWith(SerenityRunner.class)
public class WhenSignUp {


    @Steps
    SignUpSteps steps;

    @Managed
    WebDriver driver;


    @Test
    public void typeInvalidYear() {
        steps.open_signup_page();
        steps.set_month("December");
        steps.set_day("20");
        steps.set_year("85");
        steps.set_share(true);
        steps.should_see_error("Please enter a valid year.");
        steps.should_not_see_error("When were you born?");

    }

    @Test
    public void typeInvalidEmail() {
        steps.open_signup_page();
        steps.type_email("test@gmail.com");
        steps.type_confirmation_email("wrong@gmail.com");
        steps.type_name("Testname");
        steps.click_signUp();
        steps.should_see_error("Email address doesn't match.");
    }

    @Test
    public void signUpWithEmptyPassword() {
        steps.open_signup_page();
        steps.type_email("test@gmail.com");
        steps.type_confirmation_email("wrong@gmail.com");
        steps.type_name("Testname");
        steps.click_signUp();
        steps.should_see_error("Please choose a password.");
    }

    @Test
    public void typeInvalidValue() {
        steps.open_signup_page();
        steps.type_email("testmail");
        steps.type_confirmation_email("wrong@test.com");
        steps.type_password("qweqwe!123");
        steps.type_name("name");
        steps.select_sex("Male");
        steps.click_signUp();
        steps.should_see_errors_count(6);
        steps.should_see_error_by_number(3, "Please enter your birth month.");
    }


}