import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.SignUpPage;

import java.util.concurrent.TimeUnit;

public class SignUpTest {
    private WebDriver driver;
    private SignUpPage page;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\someFiles\\Prg\\tst1\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.get("https://www.spotify.com/us/signup/?forward_url  =https%3A%2F%2Fwww.spotify.com%2Fus%2Faccount%2Foverview%2F%3F_ga%3D2.68218707.314742187.1552594654-368280494.1552594654");
    }

    @Test
    public void typeInvalidYear() {
        page = new SignUpPage(driver);
        page.setMonth("December")
                .typeDay("20")
                .typeYear("85")
                .setShare(true);
        Assert.assertTrue(page.isErrorVisible("Please enter a valid year."));
        Assert.assertFalse(page.isErrorVisible("When were you born?"));
    }

    @Test
    public void typeInvalidEmail() {
        page = new SignUpPage(driver);
        page.typeName("test@gmail.com")
                .typeConfirmEmail("wrong@gmail.com")
                .typeName("Testname")
                .clickSignUpButton();
        Assert.assertTrue(page.isErrorVisible("Email address doesn't match."));
    }

    @Test
    public void signUpWithEmptyPassword() {
        page = new SignUpPage(driver);
        page.typeEmail("test@gmail.com")
                .typeConfirmEmail("test@gmail.com")
                .typeName("Testname")
                .clickSignUpButton();
        Assert.assertTrue(page.isErrorVisible("Please choose a password."));
    }

    @Test
    public void typeInvalidValue() {
        page = new SignUpPage(driver);
        page.typeEmail("testmail")
                .typeConfirmEmail("wrong@test.com")
                .typePassword("qweqwe!123")
                .typeName("name")
                .setSex("Male")
                .setShare(false)
                .clickSignUpButton();
        Assert.assertEquals(6, page.getErrors().size());
        Assert.assertEquals("Please enter your birth month.", page.getErrorByNumber(3));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}