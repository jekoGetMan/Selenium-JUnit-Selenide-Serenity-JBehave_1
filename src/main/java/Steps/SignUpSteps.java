package Steps;

import net.thucydides.core.annotations.Step;
import pages.SignUpPage;

public class SignUpSteps {
    SignUpPage page;

    @Step("User types email {0}")
    public void type_email(String mail) {
        page.typeEmail(mail);
    }

    @Step
    public void type_confirmation_email(String mail) {
        page.typeConfirmEmail(mail);
    }

    @Step
    public void type_password(String password) {
        page.typePassword(password);
    }

    @Step
    public void type_name(String name) {
        page.typeName(name);
    }

    @Step
    public void set_month(String month) {
        page.setMonth(month);
    }

    @Step
    public void set_day(String day) {
        page.typeDay(day);
    }

    @Step
    public void set_year(String year) {
        page.typeYear(year);
    }

    @Step
    public void select_sex(String sex) {
        page.setSex(sex);
    }

    @Step
    public void set_share(boolean value) {
        page.setShare(value);
    }

    @Step
    public void click_signUp() {
        page.clickSignUpButton();
    }


}
