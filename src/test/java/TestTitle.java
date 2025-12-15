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

public class TestTitle {

    private WebDriver driver;
    private WebDriverWait wait;
    private final String EXPECTED_TITLE = "Онлайн пополнение без комиссии";

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
    public void testTitle() {
        driver.get("https://www.mts.by/");
        By titleLocator = By.cssSelector("#pay-section h2");
        WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(titleLocator));
        String actualTitleText_raw = titleElement.getText();
        String actualTitleText_processed = actualTitleText_raw.replace("\n", " ").trim();
        assertEquals(EXPECTED_TITLE, actualTitleText_processed, "Название блока 'Онлайн пополнение без комиссии' не соответствует ожидаемому.");
        System.out.println("Тест успешно пройден: Название блока верное - " + actualTitleText_processed);
    }

    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
