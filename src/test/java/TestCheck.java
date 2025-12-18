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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestCheck {

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

        public void open() {
            driver.get("https://www.mts.by/");
        }

        public void acceptCookies() {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(COOKIE_BUTTON)).click();
            } catch (Exception ignored) {}
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

        public void switchToPaymentFrame() {
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(PAYMENT_IFRAME));
        }

        public String getHeaderAmount() {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(PAY_AMOUNT_HEADER)).getText();
        }

        public String getButtonAmountText() {
            return wait.until(ExpectedConditions.elementToBeClickable(PAY_BUTTON_SPAN_TEXT)).getText();
        }

        public String getPhoneInfo() {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(PAY_PHONE_INFO)).getText();
        }

        public String getCardLabel() { return wait.until(ExpectedConditions.visibilityOfElementLocated(LABEL_CARD_NUMBER)).getText(); }
        public String getExpiryLabel() { return wait.until(ExpectedConditions.visibilityOfElementLocated(LABEL_EXPIRY)).getText(); }
        public String getCvcLabel() { return wait.until(ExpectedConditions.visibilityOfElementLocated(LABEL_CVC)).getText(); }
        public String getNameLabel() { return wait.until(ExpectedConditions.visibilityOfElementLocated(LABEL_NAME)).getText(); }
        public WebElement getVisaIcon() { return wait.until(ExpectedConditions.visibilityOfElementLocated(ICON_VISA)); }
        public WebElement getMastercardIcon() { return wait.until(ExpectedConditions.visibilityOfElementLocated(ICON_MASTERCARD)); }
        public WebElement getBelkartIcon() { return wait.until(ExpectedConditions.visibilityOfElementLocated(ICON_BELKART)); }
        public WebElement getMaestroIcon() { return wait.until(ExpectedConditions.presenceOfElementLocated(ICON_MAESTRO)); }
        public WebElement getMirIcon() { return wait.until(ExpectedConditions.presenceOfElementLocated(ICON_MIR)); }
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
        String phoneNumber = "297777777";
        String expectedAmount = "100";
        String expectedAmountFormatted = "100.00";
        payPage.open();
        payPage.acceptCookies();
        payPage.fillConnectionForm(phoneNumber, expectedAmount, "danyil@test.com");
        payPage.clickContinue();
        payPage.switchToPaymentFrame();

        String headerAmt = payPage.getHeaderAmount();
        assertTrue(headerAmt.contains(expectedAmountFormatted),
                "Сумма в заголовке (" + headerAmt + ") не содержит ожидаемую сумму " + expectedAmountFormatted + " BYN");
        System.out.println("Проверка суммы пройдена успешно.");

        String btnText = payPage.getButtonAmountText();
        assertEquals("Оплатить 100.00 BYN", btnText.trim(), "На кнопке оплаты неверный текст.");
        System.out.println("Проверка текста на кнопке пройдена успешно.");

        String phoneInfoText = payPage.getPhoneInfo();
        assertTrue(phoneInfoText.contains(phoneNumber), "В информации о номере телефона (" + phoneInfoText + ") отсутствует ожидаемый номер " + phoneNumber);
        System.out.println("Проверка номера телефона пройдена успешно.");

        assertEquals("Номер карты", payPage.getCardLabel(), "Надпись 'Номер карты' неверна.");
        System.out.println("Проверка надписи 'Номер карты' пройдена успешно.");

        assertEquals("Срок действия", payPage.getExpiryLabel(), "Надпись'Срок действия' неверна.");
        System.out.println("Проверка надписи 'Срок действия' пройдена успешно.");

        assertEquals("CVC", payPage.getCvcLabel(), "Надпись 'CVC' неверна.");
        System.out.println("Проверка надписи 'CVC' пройдена успешно.");

        assertEquals("Имя и фамилия на карте", payPage.getNameLabel(), "Надпись 'Имя и фамилия на карте' неверна.");
        System.out.println("Проверка надписи 'Имя и фамилия на карте' пройдена успешно.");

        assertNotNull(payPage.getVisaIcon(), "Иконка Visa не найдена.");
        System.out.println("Иконка Visa найдена.");
        assertNotNull(payPage.getMastercardIcon(), "Иконка Mastercard не найдена.");
        System.out.println("Иконка Mastercard найдена.");
        assertNotNull(payPage.getBelkartIcon(), "Иконка Belkart не найдена.");
        System.out.println("Иконка Belkart найдена.");
        assertNotNull(payPage.getMaestroIcon(), "Иконка Maestro не найдена.");
        System.out.println("Иконка Maestro найдена.");
        assertNotNull(payPage.getMirIcon(), "Иконка Mir не найдена.");
        System.out.println("Иконка Mir найдена.");

        System.out.println("Тест успешно пройден: Все проверки в окне оплаты отображаются корректно.");
    }

    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}