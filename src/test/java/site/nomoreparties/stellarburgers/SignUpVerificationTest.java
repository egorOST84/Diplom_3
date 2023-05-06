package site.nomoreparties.stellarburgers;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import site.nomoreparties.stellarburgers.config.AppConfig;
import site.nomoreparties.stellarburgers.config.RndConf;
import site.nomoreparties.stellarburgers.config.RndStr;
import site.nomoreparties.stellarburgers.extensions.WebDriverFactory;
import site.nomoreparties.stellarburgers.pages.AccountPage;
import site.nomoreparties.stellarburgers.pages.LoginPage;
import site.nomoreparties.stellarburgers.pages.SignupPage;

public class SignUpVerificationTest {
    private static WebDriver driver;

    @Before
    public void setup() {
        driver = WebDriverFactory.get();
    }

    @After
    public void teardown() {
        driver.quit();
    }

    @Test
    public void checkSignUpAndVerifyAccountData() {
        // Генерируем случайные данные для регистрации пользователя
        String name = new RndStr().get(RndConf.NAME, 15);
        String email = new RndStr().get(RndConf.EMAIL, 15);
        String password = new RndStr().get(RndConf.PASS, 15);
        // Открываем страницу регистрации и регистрируем пользователя
        SignupPage.open(driver, AppConfig.SIGNUP_URL)
                .signUp(name, email, password)
                // Ждем пока страница загрузится и переходим на страницу логина
                .waitUntilPageIsLoaded(LoginPage.class, AppConfig.LOGIN_URL)
                // входим в систему
                .signIn(email, password)
                // переходим в личный кабинет и проверяем верность данных
                .clickOnAccountProfileLink()
                // Ждем загрузку страницы профайла
                .waitUntilPageIsLoaded(AccountPage.class, AppConfig.ACCOUNT_PROFILE_URL)
                // Проверяем, что все элементы страницы загрузились и видны
                .checkAccountPageElementsAreVisible()
                // Проверяем имя и логин
                .verifyAccountProfileData(name, email);
    }
}