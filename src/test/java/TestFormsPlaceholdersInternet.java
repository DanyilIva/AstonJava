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

public class TestFormsPlaceholdersInternet {

    private WebDriver driver;
    private InternetPaymentPage paymentPage;

    public static class InternetPaymentPage {
        private final WebDriver driver;
        private final WebDriverWait wait;

        private final By COOKIE_BUTTON = By.id("cookie-agree");
        private final By HEADER_BUTTON = By.xpath("//div[@class='select__wrapper']//button[@class='select__header']");
        private final By SELECT_WRAPPER_OPENED = By.xpath("//div[@class='select__wrapper opened']");
        private final By TAB_INTERNET = By.xpath("//ul[@class='select__list']/li[2]//p");
        private final By ACTIVE_PAYMENT_FORM = By.id("pay-internet");
        private final By INPUT_ACCOUNT_NUMBER = By.id("internet-phone");
        private final By INPUT_AMOUNT = By.id("internet-sum");
        private final By INPUT_EMAIL = By.id("internet-email");

        public InternetPaymentPage(WebDriver driver) {
            this.driver = driver;
            this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        }

        public void open() {
            driver.get("https://www.mts.by/");
            wait.until(ExpectedConditions.elementToBeClickable(COOKIE_BUTTON)).click();
        }

        public void selectInternetTab() {
            wait.until(ExpectedConditions.elementToBeClickable(HEADER_BUTTON)).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(SELECT_WRAPPER_OPENED));
            wait.until(ExpectedConditions.elementToBeClickable(TAB_INTERNET)).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(ACTIVE_PAYMENT_FORM));
        }

        private String getPlaceholderText(By inputLocator) {
            WebElement inputElement = wait.until(ExpectedConditions.elementToBeClickable(inputLocator));
            return inputElement.getAttribute("placeholder");
        }

        public String getAccountNumberPlaceholder() {
            return getPlaceholderText(INPUT_ACCOUNT_NUMBER);
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
        paymentPage = new InternetPaymentPage(driver);
        paymentPage.open();
    }

    @Test
    public void testPlaceholdersForInternetServices() {
        paymentPage.selectInternetTab();
        assertEquals("Номер абонента", paymentPage.getAccountNumberPlaceholder());
        assertEquals("Сумма", paymentPage.getAmountPlaceholder());
        assertEquals("E-mail для отправки чека", paymentPage.getEmailPlaceholder());
        System.out.println("Тест успешно пройден: Все плейсхолдеры для 'Домашний интернет' верны.");
    }

    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}