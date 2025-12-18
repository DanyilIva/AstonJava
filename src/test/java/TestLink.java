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
    private MtsMainPage mainPage;

    public static class MtsMainPage {
        private final WebDriver driver;
        private final WebDriverWait wait;

        private final By DETAILS_LINK = By.cssSelector("a[href*='/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/']");
        private final By COOKIE_BUTTON = By.id("cookie-agree");

        public MtsMainPage(WebDriver driver) {
            this.driver = driver;
            this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        }

        public void open() {
            driver.get("https://www.mts.by/");
        }

        public void acceptCookiesIfPresent() {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(COOKIE_BUTTON)).click();
            } catch (Exception e) {
                System.out.println("Кнопка куки не появилась или не кликабельна");
            }
        }

        public void clickDetailsLink() {
            WebElement link = wait.until(ExpectedConditions.elementToBeClickable(DETAILS_LINK));
            assertTrue(link.isDisplayed(), "Ссылка 'Подробнее о сервисе' должна быть видимой.");
            link.click();
        }

        public boolean waitForUrl(String expectedUrl) {
            return wait.until(ExpectedConditions.urlToBe(expectedUrl));
        }
    }

    @BeforeEach
    public void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        mainPage = new MtsMainPage(driver);
    }

    @Test
    public void testDetailsLinkNavigation() {
        String expectedUrl = "https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/";
        mainPage.open();
        mainPage.acceptCookiesIfPresent();
        mainPage.clickDetailsLink();
        boolean isCorrectUrl = mainPage.waitForUrl(expectedUrl);
        assertTrue(isCorrectUrl, "Переход по ссылке 'Подробнее о сервисе' не привел на ожидаемую страницу.");
        System.out.println("Тест успешно пройден: Ссылка ведет на корректную страницу помощи.");
    }

    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
