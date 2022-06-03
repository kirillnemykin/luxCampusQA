package AutomationTests.scenarios;

import AutomationTests.pages.LoginPage;
import AutomationTests.testClients.Client;
import AutomationTests.testClients.Client.ClientBuilder;
import AutomationTests.testClients.ClientLombok;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.locators.RelativeLocator;

@Feature("Login functionality")
public class LoginTest {

    private static LoginPage loginPage;

    @BeforeAll
    @Step("Set up driver before tests")
    static void init() {
        BaseSetUp baseSetUp = new BaseSetUp();
        loginPage = PageFactory.initElements(BaseSetUp.driver, LoginPage.class);
    }

    @RegisterExtension
    ScreenshotOnFailure watcher = new ScreenshotOnFailure(BaseSetUp.driver, "target/surefire-reports");

    @Test
    @Story("Login successful")
    @Issue("issue-12345")
    @Description("Check login is successful after entering valid credentials")
    public void testLogin() {
        //GIVEN
        Long existingOperatorId = 123L;
        Client client = new ClientBuilder(existingOperatorId)
                .withName("testOperatorName")
                .withLastName("testLastName")
                .build();

        ClientLombok clientLombok = ClientLombok.builder()
                .id(123L)
                .name("testname")
                .lastName("lastName")
                .build();

        System.out.println(client);
        System.out.println("clientLombok " + clientLombok);

        String existingUserEmail = "test@test.com";
        String existingUserPassword = "test";
        //WHEN
        LoginTest.loginPage.openLoginPage();

        LoginTest.loginPage.setEmail(existingUserEmail)
                        .setPassword(existingUserPassword)
                        .submit();

        //THEN
        checkUserIsRedirectedToProducts();
    }

    private void checkUserIsRedirectedToProducts() {
        String currentUrl = BaseSetUp.driver.getCurrentUrl();
        Assertions.assertEquals("http://online-sh.herokuapp.com/products", currentUrl);
    }

    @AfterAll
    @Step("Quit browser")
    static void tearDown() {
        BaseSetUp.driver.quit();
    }
}
