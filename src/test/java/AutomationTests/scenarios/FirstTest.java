package AutomationTests.scenarios;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.Logs;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.commons.io.FileUtils;

import javax.swing.*;

public class FirstTest {
    private static WebDriver driver;

    public FirstTest() {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\WebDriver\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.setHeadless(false);
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);

        driver = new ChromeDriver(options);
        //driver.manage().timeouts().implicitlyWait(Duration.ofMillis(100L));
        
    }

    @Test
    public void testSearchResultPresent_seleniumInput() throws InterruptedException, IOException {
        //GIVEN
        driver.get("https://www.google.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(100L));
        FluentWait<WebDriver> driverFluentWait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofMillis(3000L))
                .pollingEvery(Duration.ofMillis(300L));
        WebElement input = driver.findElement(By.xpath(".//input[@name='q']"));
        WebElement gmailLink = driver.findElement(By.linkText("Почта"));
        Actions actions = new Actions(driver);
        //WHEN
        actions.moveToElement(gmailLink).perform();
        Thread.sleep(100L);

        //WHEN
        //input.sendKeys("QA automation");
        //input.sendKeys(Keys.ENTER);
        //actions.sendKeys(input, "QA Automation").sendKeys(Keys.ENTER).perform();
        //THEN
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//ul")));
        driverFluentWait.until(e -> e.findElement(By.xpath(".//ul")));
        TakesScreenshot screenDriver = (TakesScreenshot) driver;
        File screenshotAs = screenDriver.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshotAs, new File("C:\\Users\\kirill.nemykin\\Desktop\\Screenshots\\Testscreen.png"));
    }

    @Test
    public void testSearchResultPresent_JsInput() {
        //GIVEN
        driver.get("https://www.google.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(500L));
        WebElement input = driver.findElement(By.xpath(".//input[@name='q']"));
        //WHEN
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        // set the text
        jsExecutor.executeScript("arguments[0].value='QA'", input);
        input.sendKeys(Keys.ENTER);

        //THEN
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//ul")));
        driver.close();
        driver.quit();
    }

    @Test
    public void shouldNavigate() throws InterruptedException {
        //GIVEN
        String testUrl = "https://www.google.com/";
        String expectedPageTitle = "Google";
        //WHEN
        driver.navigate().to(testUrl);
        //driver.navigate().refresh();
        //driver.switchTo().newWindow(WindowType.TAB);
        Thread.sleep(3000L);
        String actualPageTitle = driver.getTitle();
        //THEN
        Assertions.assertEquals(expectedPageTitle, actualPageTitle);
    }

    @Test
    public void shouldSearch_byId() {
        //GIVEN
        String expectedHeaderText = "Available relative locators";
        //WHEN
        driver.get("https://www.selenium.dev/documentation/webdriver/elements/locators/");
        WebElement headerElement = driver.findElement(By.id("available-relative-locators"));
        //THEN
        Assertions.assertEquals(expectedHeaderText, headerElement.getText());
    }
    @Test
    public void shouldSearch_byName() {
        //GIVEN
        //WHEN
        driver.get("http://online-sh.herokuapp.com/login");
        WebElement emailInputEmail = driver.findElement(By.name("email"));
        //THEN
        Assertions.assertTrue(emailInputEmail.isEnabled());
    }
    @Test
    public void shouldSearch_byClassName() {
        //GIVEN
        //WHEN
        driver.get("http://online-sh.herokuapp.com/login");
        WebElement submitButtonElement = driver.findElement(By.className("btn-primary"));
        //THEN
        Assertions.assertTrue(submitButtonElement.isEnabled());
    }
    @Test
    public void shouldSearch_byClassName_multipleElements() throws InterruptedException {
        //GIVEN
        //WHEN
        driver.get("http://online-sh.herokuapp.com/login");
        List<WebElement> inputElements = driver.findElements(By.className("form-control"));
        //THEN
        Assertions.assertEquals(2, inputElements.size());
    }

    @Test
    public void shouldSearch_byTagName() {
        //GIVEN
        int expectedUlsCount = 35;
        //WHEN
        driver.get("https://www.selenium.dev/documentation/webdriver/elements/locators/");
        List<WebElement> headerElements = driver.findElements(By.tagName("ul"));
        //THEN
        Assertions.assertEquals(expectedUlsCount, headerElements.size());
    }
    @Test
    public void shouldSearch_byLink() {
        //GIVEN
        String expectedUrl = "https://www.selenium.dev/documentation/about/contributing/";
        //WHEN
        driver.get("https://www.selenium.dev/documentation/webdriver/elements/locators/");
        WebElement linkElement = driver.findElement(By.partialLinkText("contribution"));
        linkElement.click();
        String actualCurrentUrl = driver.getCurrentUrl();
        //THEN
        Assertions.assertEquals(expectedUrl, actualCurrentUrl);
    }
    @Test
    public void shouldSearch_byCssSelector_parentChild() {
        //GIVEN
        //WHEN
        driver.get("https://www.selenium.dev/documentation/webdriver/elements/locators/");
        List<WebElement> elements = driver.findElements(By.cssSelector("ul > li"));
        //THEN
        Assertions.assertEquals(177, elements.size());
    }
    @Test
    public void shouldSearch_byCssSelector_complexStructure() {
        //GIVEN
        //WHEN
        driver.get("https://www.selenium.dev/documentation/webdriver/elements/locators/");
        List<WebElement> elements = driver.findElements(By.cssSelector("#tabs-10 > li:nth-child(1) > a"));
        //THEN
        Assertions.assertEquals(1, elements.size());

        WebElement firstElement =  elements.get(0);
        String classAttributeValue = firstElement.getAttribute("class");
        boolean isActive = classAttributeValue.contains("active");

        boolean isElementActive = elements.get(0).getAttribute("class").contains("active");

        Assertions.assertTrue(isActive);

    }
    @Test
    public void byXpath() {
        //GIVEN
        //WHEN
        driver.get("https://www.selenium.dev/documentation/webdriver/");
        WebElement headerElement = driver.findElement(By.xpath("(//div[@class='entry'])[3]//a"));
        headerElement.click();
        String currentUrl = driver.getCurrentUrl();
        //THEN
        org.assertj.core.api.Assertions.assertThat(currentUrl).endsWith("browser/");
    }
    @Test
    public void byRelativeLocator() throws InterruptedException {
        //GIVEN
        //WHEN
        driver.get("http://online-sh.herokuapp.com/login");
        By emailLocator = RelativeLocator.with(By.tagName("input")).above(By.id("exampleInputPassword1"));
        WebElement emailInput = driver.findElement(emailLocator);
        emailInput.sendKeys("emailTest@test.com");
        Duration scriptTimeout = driver.manage().timeouts().getScriptTimeout();
        Duration pageLoadTimeout = driver.manage().timeouts().getPageLoadTimeout();
        Duration implicitWaitTimeout = driver.manage().timeouts().getImplicitWaitTimeout();
        String currentTab = driver.getWindowHandle();
        driver.switchTo().newWindow(WindowType.TAB);
        Thread.sleep(3000L);
        driver.switchTo().window(currentTab);

        //THEN
        System.out.println("ScriptTimeout " + scriptTimeout);
        System.out.println("pageLoadTimeout " + pageLoadTimeout);
        System.out.println("implicitWaitTimeout " + implicitWaitTimeout);
        Assertions.assertTrue(driver.getCurrentUrl().contains("/login"));
    }
    @Test
    public void testClick() {
        //GIVEN
        //WHEN
        driver.get("http://online-sh.herokuapp.com/login");
        driver.manage().window().maximize();
        By emailLocator = RelativeLocator.with(By.tagName("input")).above(By.id("exampleInputPassword1"));
        WebElement emailInput = driver.findElement(emailLocator);
        emailInput.sendKeys("emailTest@test.com");

        By passwordLocator = RelativeLocator.with(By.id("exampleInputPassword1"));
        WebElement passwordInput = driver.findElement(passwordLocator);
        passwordInput.sendKeys("test");
        String classAttributeValue = passwordInput.getAttribute("class");
        String text = passwordInput.getText();
        
        //THEN
        boolean isEnabled = passwordInput.isEnabled();
        Assertions.assertTrue(isEnabled);
        boolean isDisplayed = passwordInput.isDisplayed();
        Assertions.assertTrue(isDisplayed);


        //WebElement submitButton = driver.findElement(By.xpath("//button[contains(@class,'btn btn-primary')]"));
        //submitButton.click();
        passwordInput.submit();

        //THEN
        String currentUrl = driver.getCurrentUrl();
        Assertions.assertEquals("http://online-sh.herokuapp.com/registration", currentUrl);

    }

    @Test
    public void clickCheckbox() throws InterruptedException {
        //GIVEN
        //WHEN
        driver.manage().window().maximize();
        driver.get("https://mdbootstrap.com/docs/standard/forms/checkbox/");
        WebElement checkboxElementInput = driver.findElement(By.xpath("//label[contains(., ' Default checkbox ')]//preceding-sibling::input"));

        //THEN
        Assertions.assertFalse(checkboxElementInput.isSelected());

        //WHEN
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()", checkboxElementInput);
        Thread.sleep(3000L);

        //THEN
        Assertions.assertTrue(checkboxElementInput.isSelected());
    }

    @Test
    public void clickRadiobutton() {
        //GIVEN
        //WHEN
        driver.manage().window().maximize();
        driver.get("https://www.javascripttutorial.net/javascript-dom/javascript-radio-button/");
        driver.switchTo().frame(0);
        WebElement radiobuttonElementInput = driver.findElement(By.xpath("//input[@id='xs']"));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", radiobuttonElementInput);

        //THEN
        Assertions.assertFalse(radiobuttonElementInput.isSelected());

        //WHEN
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()", radiobuttonElementInput);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000L));

        //THEN
        Assertions.assertTrue(radiobuttonElementInput.isSelected());
        driver.switchTo().defaultContent();
    }

    @Test
    public void testSelect() {
        //GIVEN
        //WHEN
        driver.get("https://getbootstrap.com/docs/5.0/forms/select/");
        WebElement selectElement = driver.findElement(By.xpath("(//select[@class='form-select'])[1]"));
        Select select = new Select(selectElement);
        select.selectByVisibleText("Two");
        //THEN
        WebElement firstSelectedOption = select.getFirstSelectedOption();
        Assertions.assertEquals("Two", firstSelectedOption.getText());
        Assertions.assertTrue(firstSelectedOption.isSelected());
        Assertions.assertFalse(select.isMultiple());

        //WHEN
        List<WebElement> options = select.getOptions();

        //THEN
        Assertions.assertEquals(4, options.size());
    }

    @AfterEach
    public void cleanUp() {
        driver.close();
        driver.quit();
    }
}
