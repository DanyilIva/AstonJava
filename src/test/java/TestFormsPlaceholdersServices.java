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
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestFormsPlaceholdersServices {

    private WebDriver driver;
    private PhonePaymentPage paymentPage;

    public static class PhonePaymentPage {
        private final WebDriver driver;
        private final WebDriverWait wait;

        private final By COOKIE_BUTTON = By.id("cookie-agree");
        private final By INPUT_PHONE = By.id("connection-phone");
        private final By INPUT_AMOUNT = By.id("connection-sum");
        private final By INPUT_EMAIL = By.id("connection-email");

        public PhonePaymentPage(WebDriver driver) {
            this.driver = driver;
            this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        }

        public void open() {
            driver.get("https://www.mts.by/");
            wait.until(ExpectedConditions.elementToBeClickable(COOKIE_BUTTON)).click();
        }

        private String getPlaceholderText(By inputLocator) {
            WebElement inputElement = wait.until(ExpectedConditions.elementToBeClickable(inputLocator));
            return inputElement.getAttribute("placeholder");
        }

        public String getPhonePlaceholder() {
            return getPlaceholderText(INPUT_PHONE);
        }

        public String getAmountPlaceholder() {
            return getPlaceholderText(INPUT_AMOUNT);
        }

        public String getEmailPlaceholder() {
            return getPlaceholderText(INPUT_EMAIL);
        }
    }

    @BeforeEach
    public void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        paymentPage = new PhonePaymentPage(driver);
        paymentPage.open();
    }

    @Test
    public void testPlaceholdersForPhoneServices() {
        assertEquals("Номер телефона", paymentPage.getPhonePlaceholder());
        assertEquals("Сумма", paymentPage.getAmountPlaceholder());
        assertEquals("E-mail для отправки чека", paymentPage.getEmailPlaceholder());
        System.out.println("Тест успешно пройден: Все плейсхолдеры для 'Услуги связи' верны.");
    }

    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
