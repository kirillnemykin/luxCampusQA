package OnlineShop;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class onlineShopTests {
    private static WebDriver driver;

    public onlineShopTests() {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\WebDriver\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.setHeadless(false);
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);

        driver = new ChromeDriver(options);
        //driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000L));
    }

    @Test
    public void shouldNavigate() throws InterruptedException {
        //GIVEN
        String testUrl = "http://online-sh.herokuapp.com/";
        String expectedPageTitle = "Online Shop";
        //WHEN
        driver.navigate().to(testUrl);
        Thread.sleep(3000L);
        String actualPageTitle = driver.getTitle();
        //THEN
        Assertions.assertEquals(expectedPageTitle, actualPageTitle);
    }

    @AfterEach
    public void cleanUp() {
        driver.close();
        driver.quit();
    }

}
