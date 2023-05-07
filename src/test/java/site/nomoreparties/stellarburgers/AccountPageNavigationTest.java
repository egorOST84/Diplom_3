package site.nomoreparties.stellarburgers;

import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import site.nomoreparties.stellarburgers.common.BaseTest;
import site.nomoreparties.stellarburgers.config.AppConfig;
import site.nomoreparties.stellarburgers.pages.AccountPage;
import site.nomoreparties.stellarburgers.pages.HomePage;

public class AccountPageNavigationTest extends BaseTest {
    @Before
    public void setupTestUser() {
        createUser();
    }
    @Test
    @DisplayName("Check the correctness of navigating to the account profile page")
    public void checkNavigationToAccountProfileWorksCorrect() {
        HomePage.open(driver, AppConfig.BASE_URL)
                .signIn(email, password)
                // Переходим в личный кабинет
                .clickOnAccountProfileLink()
                // Ждем загрузку страницы профайла
                .waitUntilPageIsLoaded(AccountPage.class, AppConfig.ACCOUNT_PROFILE_URL)
                // Проверяем, что все элементы страницы загрузились и видны
                .checkAccountPageElementsAreVisible()
                // Проверяем текст на странице
                .checkPageContentText("В этом разделе вы можете изменить свои персональные данные");
    }
    @After
    public void deleteTestUser() {
        deleteUser();
    }
}