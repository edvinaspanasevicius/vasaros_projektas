import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

    @FindBy(xpath = "//button[normalize-space()='Sign up']")
    private WebElement homeSignInButton;
    @FindBy (xpath = "//button[normalize-space()='Log in']")
    private WebElement homeLoginButton;

    public HomePage(WebDriver driver) {
        super(driver);
    }
    public void clickSignIn() {
        homeSignInButton.click();
    }
    public String checkLoginButton() {
        return homeLoginButton.getText();
    }
    public void clickHomeLogin() {
        homeLoginButton.click();
    }
}