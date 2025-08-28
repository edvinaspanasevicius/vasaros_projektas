import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//input[@placeholder='Email']")
    private WebElement loginEmail;
    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement loginPassword;
    @FindBy(xpath = "//input[@value='Log in']")
    private WebElement submitLogin;
    @FindBy(xpath = "//h2[normalize-space()='Log in']")
    private WebElement logInTitle;
    @FindBy(xpath = "//button[normalize-space()='Register here']")
    private WebElement registrationButtonInLogIn;
    @FindBy(css = "pre[class='text-2xl text-[var(--error-text-color)] font-light max-xl:text-xl']")
    private WebElement wrongEmail;
    @FindBy(css = "p[class='text-[var(--error-text-color)] font-semibold text-2xl']")
    private WebElement emptyInsertion;

    public LoginPage(WebDriver driver) {
        super(driver);
    }
    public void enterLoginEmail(String email) {
        loginEmail.sendKeys(email);
    }
    public void enterLoginPassword(String password) {
        loginPassword.sendKeys(password);
    }
    public void pushSubmitLogin() {
        submitLogin.click();
    }
    public String visibleLogInTitle() {
        return logInTitle.getText();
    }
    public void registrationInLogInPush() {
        registrationButtonInLogIn.click();
    }
    public boolean checkErrorPromptsVisability() {
        JavascriptExecutor javascript = (JavascriptExecutor) driver;
        boolean isInsertionValid = (boolean) javascript.executeScript("return arguments[0].checkValidity()", loginEmail);
        return isInsertionValid;
    }
    public String visibleLoginError() {
        String error = "";
        try {
            error = emptyInsertion.getText();
        } catch (NoSuchElementException ignore) {
        }
        if (!error.isEmpty()) return error;
        {
            return "";
        }
    }
}