import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountTest extends BaseTest{

    @Test
    void positiveLogout() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.clickSignIn();
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.signInEmail("dzinn@dzinn.com");
        registrationPage.signInFirstPassword("pirmas123@");
        registrationPage.setSignInSecondPassword("pirmas123@");
        registrationPage.submitSignIn();
        AccountPage accountPage = new AccountPage(driver);
        Thread.sleep(2000);
        accountPage.clickLogout();
        Thread.sleep(2000);
        assertEquals("Log in", homePage.checkLoginButton());
    }
}
