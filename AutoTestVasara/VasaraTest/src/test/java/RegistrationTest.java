import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class RegistrationTest extends BaseTest {

    String userEmail = "edvinas.edvinavicius"+ System.nanoTime() +"@gmail.com";
//    String userEmail = "vartotojas@gmail.com";
    String userPassword = "password123@";

    @Test
    void positiveSignIn() {
        HomePage homePage = new HomePage(driver);
        homePage.clickSignIn();
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.signInEmail(userEmail);
        registrationPage.signInFirstPassword(userPassword);
        registrationPage.setSignInSecondPassword(userPassword);
        registrationPage.submitSignIn();
        AccountPage accountPage = new AccountPage(driver);
        assertEquals("Logout", accountPage.checkPositive());
    }
//    negative registration tests (PROMPT errors)
    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/negativeRegistrationTests.csv", numLinesToSkip = 1)
    void assertsTests(String registrationEmail, String registrationPassword, String registrationConfirmPassword) throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.clickSignIn();
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.signInEmail(registrationEmail);
        registrationPage.signInFirstPassword(registrationPassword);
        registrationPage.setSignInSecondPassword(registrationConfirmPassword);
        registrationPage.submitSignIn();
        assertFalse(registrationPage.checkErrorPromptsVisability(), "Invalid input should fail validation");
    }

//    negative registration tests (DOM)
    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/negativeRegistrationTestsDOM.csv", numLinesToSkip = 1)
    void assertsTestsDom(String registrationEmail, String registrationPassword, String registrationConfirmPassword, String errorMessage) {
        HomePage homePage = new HomePage(driver);
        homePage.clickSignIn();
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.signInEmail(registrationEmail);
        registrationPage.signInFirstPassword(registrationPassword);
        registrationPage.setSignInSecondPassword(registrationConfirmPassword);
        registrationPage.submitSignIn();
        assertEquals(errorMessage, registrationPage.visibleRegistrationError());
    }


    @Test
    void navigationToLogIn() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.clickSignIn();
        Thread.sleep(2000);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.logInLinkInRegistration();
        Thread.sleep(2000);
        LoginPage loginPage = new LoginPage(driver);
        assertEquals("Log in", loginPage.visibleLogInTitle());
    }
}

