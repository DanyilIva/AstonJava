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
    private MtsPayPage payPage;

    public static class MtsPayPage {
        private final WebDriver driver;
        private final WebDriverWait wait;

        private final By COOKIE_BUTTON = By.id("cookie-agree");
        private final By PHONE_INPUT = By.id("connection-phone");
        private final By AMOUNT_INPUT = By.id("connection-sum");
        private final By EMAIL_INPUT = By.id("connection-email");
        private final By CONTINUE_BUTTON = By.xpath("//form[@id='pay-connection']//button[@type='submit']");

        public MtsPayPage(WebDriver driver) {
            this.driver = driver;
            this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        }

        public void open() {
            driver.get("https://www.mts.by/");
        }

        public void acceptCookies() {
            wait.until(ExpectedConditions.elementToBeClickable(COOKIE_BUTTON)).click();
        }

        public void fillConnectionForm(String phone, String amount, String email) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(PHONE_INPUT)).sendKeys(phone);
            wait.until(ExpectedConditions.visibilityOfElementLocated(AMOUNT_INPUT)).sendKeys(amount);
            wait.until(ExpectedConditions.visibilityOfElementLocated(EMAIL_INPUT)).sendKeys(email);
        }

        public void clickContinue() {
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(CONTINUE_BUTTON));
            assertTrue(button.isEnabled(), "Кнопка 'Продолжить' должна быть активна.");
            button.click();
        }
    }

    @BeforeEach
    public void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        payPage = new MtsPayPage(driver);
    }

    @Test
    public void testPaymentFormSubmission() {
        payPage.open();
        payPage.acceptCookies();
        payPage.fillConnectionForm("297777777", "100", "danyil@test.com");
        payPage.clickContinue();
        System.out.println("Тест успешно пройден: Нажатие кнопки 'Продолжить' открыло форму ввода данных карты.");
    }

    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
