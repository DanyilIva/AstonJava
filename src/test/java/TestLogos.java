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
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestLogos {

    private WebDriver driver;
    private WebDriverWait wait;

    private final By VISA_LOGO_LOCATOR = By.cssSelector("img[alt='Visa']");
    private final By VISA_VERIFIED_LOCATOR = By.cssSelector("img[alt='Verified By Visa']");
    private final By MASTERCARD_LOGO_LOCATOR = By.cssSelector("img[alt='MasterCard']");
    private final By MASTERCARD_SECURE_LOCATOR = By.cssSelector("img[alt='MasterCard Secure Code']");
    private final By BELKART_LOGO_LOCATOR = By.cssSelector("img[alt='Белкарт']");
    private final By PAYMENT_SECTION_LOCATOR = By.cssSelector("#pay-section");

    @BeforeEach
    public void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.manage().window().maximize();
    }

    @Test
    public void testLogos() {
        driver.get("https://www.mts.by/");
        wait.until(ExpectedConditions.visibilityOfElementLocated(PAYMENT_SECTION_LOCATOR));
        checkLogoPresence(VISA_LOGO_LOCATOR, "Visa");
        checkLogoPresence(VISA_VERIFIED_LOCATOR, "Verified by Visa");
        checkLogoPresence(MASTERCARD_LOGO_LOCATOR, "Mastercard");
        checkLogoPresence(MASTERCARD_SECURE_LOCATOR, "Mastercard Secure Code");
        checkLogoPresence(BELKART_LOGO_LOCATOR, "Белкарт");
        System.out.println("Тест успешно пройден: Все 5 логотипов платежных систем присутствуют и видимы.");
    }

    private void checkLogoPresence(By locator, String logoName) {
        List<WebElement> logos = driver.findElements(locator);
        assertFalse(logos.isEmpty(), "Логотип платежной системы " + logoName + " должен присутствовать на странице.");
        assertTrue(logos.get(0).isDisplayed(), "Логотип платежной системы " + logoName + " должен быть видимым.");
    }

    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
