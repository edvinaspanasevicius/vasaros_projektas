import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage extends BasePage {

    @FindBy(xpath = "//input[@placeholder='Email']")
    private WebElement signInEmail;
    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement signInPassword;
    @FindBy(xpath = "//input[@placeholder='Confirm Password']")
    private WebElement signInSecondPassword;
    @FindBy(xpath = "//input[@value='Register']")
    private WebElement pushSignInSubmit;
    @FindBy(xpath = "//button[normalize-space()='Login here']")
    private WebElement logInLink;
    @FindBy(xpath = "//h2[normalize-space()='Registration']")
    private WebElement registrationTitle;
    @FindBy(xpath = "//p[normalize-space()='Email is allowed up to 50 characters']")
    private WebElement emailExceededError;
    @FindBy(css = "pre[class='text-[1.15rem] text-[var(--error-text-color)] text-center max-md:text-[0.95rem]']")
    private WebElement withoutSimbolError;
    @FindBy (css = "form > p")
    private WebElement withoutEmail;



    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    public void signInEmail(String email) {
        signInEmail.sendKeys(email);
    }

    public boolean checkErrorPromptsVisability() {
        JavascriptExecutor javascript = (JavascriptExecutor) driver;
        boolean isInsertionValid = (boolean) javascript.executeScript("return arguments[0].checkValidity()", signInEmail);
        return isInsertionValid;
    }

    public void signInFirstPassword(String firstPassword) {
        signInPassword.sendKeys(firstPassword);
    }

    public void setSignInSecondPassword(String secondPassword) {
        signInSecondPassword.sendKeys(secondPassword);
    }

    public void submitSignIn() {
        pushSignInSubmit.click();
    }

    public void logInLinkInRegistration() {
        logInLink.click();
    }

    public String visibleRegistrationTitle() {
        return registrationTitle.getText();
    }
    public String visibleRegistrationError() {
        String error = "";
        try {
            error = emailExceededError.getText();
        } catch (NoSuchElementException ignore){
        }
        if (!error.isEmpty())return error;
        try {
            error = withoutSimbolError.getText();
        } catch (NoSuchElementException ignore){
        }
        if (!error.isEmpty())return error;
        try {
            return withoutEmail.getText();
        } catch (NoSuchElementException ignore){
            System.out.println(withoutEmail.isDisplayed());
        }
        return "";
    }
}