import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTitle {

    private WebDriver driver;
    private MtsPage mtsPage;
    private final String EXPECTED_TITLE = "Онлайн пополнение без комиссии";

    public static class MtsPage {
        private final WebDriver driver;
        private final WebDriverWait wait;

        private final By paySectionTitle = By.cssSelector("#pay-section h2");

        public MtsPage(WebDriver driver) {
            this.driver = driver;
            this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        }

        public void open() {
            driver.get("https://www.mts.by/");
        }

        public String getHeaderText() {
            String rawText = wait.until(ExpectedConditions.visibilityOfElementLocated(paySectionTitle)).getText();
            return rawText.replace("\n", " ").trim();
        }
    }

    @BeforeEach
    public void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        mtsPage = new MtsPage(driver);
    }

    @Test
    public void testTitleWithPO() {
        mtsPage.open();
        String actualTitle = mtsPage.getHeaderText();

        assertEquals(EXPECTED_TITLE, actualTitle, "Заголовок блока не совпадает!");
        System.out.println("Тест успешно пройден: Название блока верное - " + actualTitle);
    }

    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
