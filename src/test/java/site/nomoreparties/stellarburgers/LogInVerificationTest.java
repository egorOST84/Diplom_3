package site.nomoreparties.stellarburgers;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import site.nomoreparties.stellarburgers.api.UserApiHelper;
import site.nomoreparties.stellarburgers.config.AppConfig;
import site.nomoreparties.stellarburgers.config.RndConf;
import site.nomoreparties.stellarburgers.config.RndStr;
import site.nomoreparties.stellarburgers.extensions.WebDriverFactory;
import site.nomoreparties.stellarburgers.pages.*;

public class LogInVerificationTest {
    private static WebDriver driver;
    private static String name;
    private static String email;
    private static String password;

    @Before
    public void setup() {
        driver = WebDriverFactory.get();

        // Генерируем случайные данные для регистрации пользователя
        name = new RndStr().get(RndConf.NAME, 15);
        email = new RndStr().get(RndConf.EMAIL, 15);
        password = new RndStr().get(RndConf.PASS, 15);
        // Создаем пользователя для тестов
        UserApiHelper.createUser(name, email, password);
    }

    @After
    public void teardown() {
        driver.quit();

        String accessToken = UserApiHelper.loginUserAndGetToken(email, password);
        UserApiHelper.deleteUser(accessToken);
    }

    @Test
    public void checkLoginByLoginButtonFromHomePage() {
        // Тест на вход по кнопке "Войти в аккаунт" на главной странице
        HomePage.open(driver, AppConfig.BASE_URL)
                .signIn(email, password);
    }

    @Test
    public void checkLoginByAccountLinkInHeaderMenuFromHomePage() {
        // Тест на вход через кнопку "Личный кабинет"
        HomePage.open(driver, AppConfig.BASE_URL)
                .clickOnAccountProfileLink()
                .signIn(email, password);
    }

    @Test
    public void loginFromRegistrationForm() {
        // Тест на вход через кнопку в форме регистрации
        SignupPage.open(driver, AppConfig.SIGNUP_URL)
                .signIn(email, password);
    }

    @Test
    public void loginFromPasswordRecoveryForm() {
        // Тест на вход через кнопку в форме восстановления пароля
        PasswordRecoveryPage.open(driver, AppConfig.RECOVERY_PASSWORD_URL)
                .signIn(email, password);
    }
}
