import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class LoginTest extends BaseTest {

    String userEmail = "vartotojas@vartotojas.com";
    String userPassword = "password123@";
    String adminEmail = "admin@admin.com";
    String adminPassword = "password123@";


    @Test
    void positiveUserLogIn() {
        HomePage homePage = new HomePage(driver);
        homePage.clickHomeLogin();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterLoginEmail(userEmail);
        loginPage.enterLoginPassword(userPassword);
        loginPage.pushSubmitLogin();
        AccountPage accountPage = new AccountPage(driver);
        assertEquals("Logout", accountPage.checkPositive());
    }
    @Test
    void positiveAdminLogIn() {
        HomePage homePage = new HomePage(driver);
        homePage.clickHomeLogin();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterLoginEmail(adminEmail);
        loginPage.enterLoginPassword(adminPassword);
        loginPage.pushSubmitLogin();
        AccountPage accountPage = new AccountPage(driver);
        assertEquals("Admin Panel", accountPage.visibleAdminPanelButton());
    }
// neigiami login testai reikia klaidu patvirtinimo
    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/negativeLoginTests.csv", numLinesToSkip = 1)
    void assertsTests(String logInEmail, String logInPassword) throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.clickHomeLogin();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterLoginEmail(logInEmail);
        loginPage.enterLoginPassword(logInPassword);
        loginPage.pushSubmitLogin();

        assertFalse(loginPage.checkErrorPromptsVisability(), "Invalid input should fail validation");
    }
    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/negativeLoginTestsDOM.csv", numLinesToSkip = 1)
    void assertsTestsDom(String logInEmail, String logInPassword,String errorMessage) {
        HomePage homePage = new HomePage(driver);
        homePage.clickHomeLogin();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterLoginEmail(logInEmail);
        loginPage.enterLoginPassword(logInPassword);
        loginPage.pushSubmitLogin();
        assertEquals(errorMessage, loginPage.visibleLoginError());
    }
    @Test
    void navigationToRegistration() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.clickHomeLogin();
        Thread.sleep(2000);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.registrationInLogInPush();
        Thread.sleep(2000);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        assertEquals("Registration",registrationPage.visibleRegistrationTitle());
    }
}
