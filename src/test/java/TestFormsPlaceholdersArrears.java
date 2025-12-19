import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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

@DisplayName("Тестирование плейсхолдеров оплаты задолженности")
public class TestFormsPlaceholdersArrears {

    private WebDriver driver;
    private ArrearsPaymentPage paymentPage;

    public static class ArrearsPaymentPage {
        private final WebDriver driver;
        private final WebDriverWait wait;

        private final By COOKIE_BUTTON = By.id("cookie-agree");
        private final By HEADER_BUTTON = By.xpath("//div[@class='select__wrapper']//button[@class='select__header']");
        private final By SELECT_WRAPPER_OPENED = By.xpath("//div[@class='select__wrapper opened']");
        private final By TAB_ARREARS = By.xpath("//ul[@class='select__list']/li[4]//p");
        private final By ACTIVE_PAYMENT_FORM = By.id("pay-arrears");
        private final By INPUT_ACCOUNT_NUMBER = By.id("score-arrears");
        private final By INPUT_AMOUNT = By.id("arrears-sum");
        private final By INPUT_EMAIL = By.id("arrears-email");

        public ArrearsPaymentPage(WebDriver driver) {
            this.driver = driver;
            this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        }

        @Step("Открыть главную страницу МТС и принять куки")
        public void open() {
            driver.get("https://www.mts.by/");
            wait.until(ExpectedConditions.elementToBeClickable(COOKIE_BUTTON)).click();
        }

        @Step("Переключиться на вкладку 'Задолженность'")
        public void selectArrearsTab() {
            wait.until(ExpectedConditions.elementToBeClickable(HEADER_BUTTON)).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(SELECT_WRAPPER_OPENED));
            wait.until(ExpectedConditions.elementToBeClickable(TAB_ARREARS)).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(ACTIVE_PAYMENT_FORM));
        }

        @Step("Получить текст плейсхолдера для поля")
        private String getPlaceholderText(By inputLocator) {
            WebElement inputElement = wait.until(ExpectedConditions.elementToBeClickable(inputLocator));
            return inputElement.getAttribute("placeholder");
        }

        @Step("Проверка плейсхолдера номера счета")
        public String getAccountNumberPlaceholder() {
            return getPlaceholderText(INPUT_ACCOUNT_NUMBER);
        }

        @Step("Проверка плейсхолдера суммы")
        public String getAmountPlaceholder() {
            return getPlaceholderText(INPUT_AMOUNT);
        }

        @Step("Проверка плейсхолдера E-mail")
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
        paymentPage = new ArrearsPaymentPage(driver);
        paymentPage.open();
    }

    @Test
    @DisplayName("Проверка плейсхолдеров в форме оплаты задолженности")
    @Description("Тест проверяет корректность текста подсказок (placeholders) в полях ввода номера счета, суммы и e-mail.")
    public void testPlaceholdersForArrearsServices() {
        paymentPage.selectArrearsTab();

        assertEquals("Номер счета на 2073", paymentPage.getAccountNumberPlaceholder());
        assertEquals("Сумма", paymentPage.getAmountPlaceholder());
        assertEquals("E-mail для отправки чека", paymentPage.getEmailPlaceholder());
    }

    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
