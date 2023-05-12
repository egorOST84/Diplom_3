package site.nomoreparties.stellarburgers;

import org.junit.*;
import io.qameta.allure.junit4.DisplayName;
import site.nomoreparties.stellarburgers.common.BaseTest;
import site.nomoreparties.stellarburgers.config.AppConfig;
import site.nomoreparties.stellarburgers.pages.AccountPage;
import site.nomoreparties.stellarburgers.pages.HomePage;
import site.nomoreparties.stellarburgers.pages.LoginPage;

public class LogoutTest extends BaseTest {
    @Before
    public void setupTestUser() {
        createUser();
    }

    @Test
    @DisplayName("Check navigation to Account Profile page works correctly")
    public void checkNavigationToAccountProfileWorksCorrect() {
        HomePage.open(driver, AppConfig.BASE_URL)
                .signIn(email, password)
                // Переходим в личный кабинет
                .clickOnAccountProfileLink()
                // Ждем загрузку страницы профайла
                .waitUntilPageIsLoaded(AccountPage.class, AppConfig.ACCOUNT_PROFILE_URL)
                // Проверяем, что все элементы страницы загрузились и видны
                .checkAccountPageElementsAreVisible()
                // Выполняем выходи из аккаунта
                .goToLogout()
                // Ждем загрузку страницы логина
                .waitUntilPageIsLoaded(LoginPage.class, AppConfig.LOGIN_URL)
                // Проверяем, что все элементы страницы загрузились и видны
                .checkLoginPageElementsAreVisible();
    }

    @After
    public void deleteTestUser() {
        deleteUser();
    }
}