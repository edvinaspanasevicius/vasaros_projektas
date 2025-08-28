import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HomeTest extends BaseTest{

    @Test
    void positiveSignIn () {
        HomePage homePage = new HomePage(driver);
        homePage.clickSignIn();
    }
}
