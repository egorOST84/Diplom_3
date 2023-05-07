package site.nomoreparties.stellarburgers;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import site.nomoreparties.stellarburgers.common.BaseTest;
import site.nomoreparties.stellarburgers.config.AppConfig;
import site.nomoreparties.stellarburgers.pages.*;

public class LogInVerificationTest extends BaseTest {

    @Before
    public void setupTestUser() {
        createUser();
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
    @After
    public void deleteTestUser() {
        deleteUser();
    }

}