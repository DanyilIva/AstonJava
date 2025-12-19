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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Тестирование процесса оплаты услуг связи")
public class TestCheck {

    private WebDriver driver;
    private MtsPayPage payPage;
    private final String phoneNumber = "297777777";
    private final String expectedAmount = "100";
    private final String expectedAmountFormatted = "100.00";

    public static class MtsPayPage {
        private final WebDriver driver;
        private final WebDriverWait wait;

        private final By COOKIE_BUTTON = By.id("cookie-agree");
        private final By PHONE_INPUT = By.id("connection-phone");
        private final By AMOUNT_INPUT = By.id("connection-sum");
        private final By EMAIL_INPUT = By.id("connection-email");
        private final By CONTINUE_BUTTON = By.xpath("//form[@id='pay-connection']//button[@type='submit']");

        private final By PAYMENT_IFRAME = By.cssSelector("iframe.bepaid-iframe");
        private final By PAY_AMOUNT_HEADER = By.xpath("//div[contains(@class, 'pay-description')]//span");
        private final By PAY_BUTTON_SPAN_TEXT = By.xpath("//button[@class='colored disabled']//span");
        private final By PAY_PHONE_INFO = By.xpath("//div[@class='pay-description__text']//span");

        private final By LABEL_CARD_NUMBER = By.xpath("//div[@class='content ng-tns-c2312288139-2']//label");
        private final By LABEL_EXPIRY = By.xpath("//div[@class='content ng-tns-c2312288139-4']//label");
        private final By LABEL_CVC = By.xpath("//div[@class='content ng-tns-c2312288139-5']//label");
        private final By LABEL_NAME = By.xpath("//div[@class='content ng-tns-c2312288139-3']//label");

        private final By ICON_VISA = By.cssSelector("img[src*='visa-system.svg']");
        private final By ICON_MASTERCARD = By.cssSelector("img[src*='mastercard-system.svg']");
        private final By ICON_BELKART = By.cssSelector("img[src*='belkart-system.svg']");
        private final By ICON_MAESTRO = By.cssSelector("img[src*='maestro-system.svg']");
        private final By ICON_MIR = By.cssSelector("img[src*='mir-system-ru.svg']");

        public MtsPayPage(WebDriver driver) {
            this.driver = driver;
            this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        }
        @Step("Открыть главную страницу МТС")
        public void open() {
            driver.get("https://www.mts.by/");
        }

        @Step("Принять куки (если присутствуют)")
        public void acceptCookies() {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(COOKIE_BUTTON)).click();
            } catch (Exception ignored) {

            }
        }

        @Step("Заполнить форму оплаты: телефон {phone}, сумма {amount}, email {email}")
        public void fillConnectionForm(String phone, String amount, String email) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(PHONE_INPUT)).sendKeys(phone);
            wait.until(ExpectedConditions.visibilityOfElementLocated(AMOUNT_INPUT)).sendKeys(amount);
            wait.until(ExpectedConditions.visibilityOfElementLocated(EMAIL_INPUT)).sendKeys(email);
        }

        @Step("Нажать кнопку 'Продолжить' и проверить ее активность")
        public void clickContinue() {
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(CONTINUE_BUTTON));
            assertTrue(button.isEnabled(), "Кнопка 'Продолжить' должна быть активна.");
            button.click();
        }

        @Step("Переключиться в iframe платежной системы bePaid")
        public void switchToPaymentFrame() {
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(PAYMENT_IFRAME));
        }

        @Step("Получить сумму из заголовка iframe")
        public String getHeaderAmount() {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(PAY_AMOUNT_HEADER)).getText();
        }

        @Step("Получить текст с кнопки оплаты в iframe")
        public String getButtonAmountText() {
            return wait.until(ExpectedConditions.elementToBeClickable(PAY_BUTTON_SPAN_TEXT)).getText();
        }

        @Step("Получить информацию о номере телефона в iframe")
        public String getPhoneInfo() {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(PAY_PHONE_INFO)).getText();
        }

        @Step("Проверить надпись 'Номер карты'")
        public String getCardLabel() {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(LABEL_CARD_NUMBER)).getText();
        }

        @Step("Проверить надпись 'Срок действия'")
        public String getExpiryLabel() {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(LABEL_EXPIRY)).getText();
        }

        @Step("Проверить надпись 'CVC'")
        public String getCvcLabel() {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(LABEL_CVC)).getText();
        }

        @Step("Проверить надпись 'Имя и фамилия на карте'")
        public String getNameLabel() {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(LABEL_NAME)).getText();
        }

        public WebElement getVisaIcon() {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(ICON_VISA));
        }

        public WebElement getMastercardIcon() {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(ICON_MASTERCARD));
        }

        public WebElement getBelkartIcon() {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(ICON_BELKART));
        }

        public WebElement getMaestroIcon() {
            return wait.until(ExpectedConditions.presenceOfElementLocated(ICON_MAESTRO));
        }

        public WebElement getMirIcon() {
            return wait.until(ExpectedConditions.presenceOfElementLocated(ICON_MIR));
        }
    }

    @BeforeEach
    @Step("Настройка WebDriver и инициализация страницы")
    public void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        payPage = new MtsPayPage(driver);
        payPage.open();
        payPage.acceptCookies();
        payPage.fillConnectionForm(phoneNumber, expectedAmount, "danyil@test.com");
        payPage.clickContinue();
        payPage.switchToPaymentFrame();
    }

    @Test
    @DisplayName("Проверка передачи суммы и номера телефона в iframe")
    @Description("Проверяет, что сумма и номер телефона, введенные на главной странице, корректно отображаются в заголовке и на кнопке оплаты в iframe.")
    public void testDataTransferToIframe() {
        String headerAmt = payPage.getHeaderAmount();
        assertTrue(headerAmt.contains(expectedAmountFormatted), "Сумма в заголовке неверна.");
        String btnText = payPage.getButtonAmountText();
        assertEquals("Оплатить 100.00 BYN", btnText.trim(), "На кнопке оплаты неверный текст.");
        String phoneInfoText = payPage.getPhoneInfo();
        assertTrue(phoneInfoText.contains(phoneNumber), "В информации о номере телефона отсутствует ожидаемый номер.");
    }

    @Test
    @DisplayName("Проверка надписей полей ввода в iframe")
    @Description("Проверяет корректность текстовых надписей (лейблов) для полей ввода номера карты, срока действия, CVC и имени в iframe платежной формы.")
    public void testIframeInputLabels() {
        assertEquals("Номер карты", payPage.getCardLabel(), "Надпись 'Номер карты' неверна.");
        assertEquals("Срок действия", payPage.getExpiryLabel(), "Надпись'Срок действия' неверна.");
        assertEquals("CVC", payPage.getCvcLabel(), "Надпись 'CVC' неверна.");
        assertEquals("Имя и фамилия на карте", payPage.getNameLabel(), "Надпись 'Имя и фамилия на карте' неверна.");
    }

    @Test
    @DisplayName("Проверка наличия иконок платежных систем в iframe")
    @Description("Проверяет, что все ожидаемые иконки платежных систем (Visa, Mastercard, Belkart, Maestro, Mir) отображаются в платежной форме.")
    public void testPaymentSystemIconsPresence() {
        assertNotNull(payPage.getVisaIcon(), "Иконка Visa не найдена.");
        assertNotNull(payPage.getMastercardIcon(), "Иконка Mastercard не найдена.");
        assertNotNull(payPage.getBelkartIcon(), "Иконка Belkart не найдена.");
        assertNotNull(payPage.getMaestroIcon(), "Иконка Maestro не найдена.");
        assertNotNull(payPage.getMirIcon(), "Иконка Мир не найдена.");
    }

    @AfterEach
    @Step("Закрытие браузера")
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
