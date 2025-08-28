import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountPage extends BasePage {

    @FindBy(xpath = "//button[normalize-space()='Logout']")
    private WebElement accountLogoutButton;
    @FindBy(xpath = "//a[normalize-space()='Admin Panel']")
    private WebElement adminPanelButton;

    public AccountPage(WebDriver driver) {
        super(driver);
    }
    public void clickLogout() {
        accountLogoutButton.click();
    }
    public String checkPositive() {
        return accountLogoutButton.getText();
    }
    public String visibleAdminPanelButton() {
        return adminPanelButton.getText();
    }
}
