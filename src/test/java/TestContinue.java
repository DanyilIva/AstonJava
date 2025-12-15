import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestContinue {

    private WebDriver driver;
    private WebDriverWait wait;

    private final By ACCEPT_COOKIES_BUTTON_LOCATOR = By.id("cookie-agree");
    private final By PHONE_NUMBER_INPUT_LOCATOR = By.id("connection-phone");
    private final By AMOUNT_INPUT_LOCATOR = By.id("connection-sum");
    private final By EMAIL_INPUT_LOCATOR = By.id("connection-email");
    private final By CONTINUE_BUTTON_LOCATOR = By.xpath("//form[@id='pay-connection']//button[@type='submit']");

    @BeforeEach
    public void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void testContinue() {
        driver.get("https://www.mts.by/");
        wait.until(ExpectedConditions.elementToBeClickable(ACCEPT_COOKIES_BUTTON_LOCATOR)).click();
        wait.until(ExpectedConditions.elementToBeClickable(PHONE_NUMBER_INPUT_LOCATOR)).sendKeys("297777777");
        wait.until(ExpectedConditions.elementToBeClickable(AMOUNT_INPUT_LOCATOR)).sendKeys("100");
        wait.until(ExpectedConditions.elementToBeClickable(EMAIL_INPUT_LOCATOR)).sendKeys("danyil@test.com");
        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(CONTINUE_BUTTON_LOCATOR));
        assertTrue(continueButton.isEnabled(), "Кнопка 'Продолжить' должна быть активна после ввода данных.");
        continueButton.click();
        System.out.println("Тест успешно пройден: Нажатие кнопки 'Продолжить' открыло форму ввода данных карты.");
    }

    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}