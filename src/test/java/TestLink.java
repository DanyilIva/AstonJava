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

public class TestLink {

    private WebDriver driver;
    private WebDriverWait wait;;

    private final By DETAILS_LINK_LOCATOR = By.cssSelector("a[href*='/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/']");
    private final By ACCEPT_COOKIES_BUTTON_LOCATOR = By.id("cookie-agree");
    private final String EXPECTED_DETAILS_URL = "https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/";

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
    public void testLink() {
        driver.get("https://www.mts.by/");
        wait.until(ExpectedConditions.elementToBeClickable(ACCEPT_COOKIES_BUTTON_LOCATOR)).click();
        WebElement detailsLink = wait.until(ExpectedConditions.elementToBeClickable(DETAILS_LINK_LOCATOR));
        assertTrue(detailsLink.isDisplayed(), "Ссылка 'Подробнее о сервисе' должна быть видимой.");
        detailsLink.click();
        Boolean urlChanged = wait.until(ExpectedConditions.urlToBe(EXPECTED_DETAILS_URL));
        assertTrue(urlChanged, "Переход по ссылке 'Подробнее о сервисе' не привел на ожидаемую страницу.");
        System.out.println("Тест успешно пройден: Ссылка ведет на корректную страницу помощи.");
    }

    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
