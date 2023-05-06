package site.nomoreparties.stellarburgers;

import org.junit.*;
import org.openqa.selenium.WebDriver;
import site.nomoreparties.stellarburgers.api.UserApiHelper;
import site.nomoreparties.stellarburgers.config.AppConfig;
import site.nomoreparties.stellarburgers.config.RndConf;
import site.nomoreparties.stellarburgers.config.RndStr;
import site.nomoreparties.stellarburgers.extensions.WebDriverFactory;
import site.nomoreparties.stellarburgers.pages.AccountPage;
import site.nomoreparties.stellarburgers.pages.HomePage;
import site.nomoreparties.stellarburgers.pages.LoginPage;

public class LogoutTest {
    private static WebDriver driver;
    private static String email;
    private static String name;
    private static String password;

    @BeforeClass
    public static void createUser(){
        // Генерируем случайные данные для регистрации пользователя
        name = new RndStr().get(RndConf.NAME, 15);
        email = new RndStr().get(RndConf.EMAIL, 15);
        password = new RndStr().get(RndConf.PASS, 15);
        // Создаем пользователя для тестов
        UserApiHelper.createUser(name, email, password);
    }
    @Before
    public void setup() {
        driver = WebDriverFactory.get();
    }

    @After
    public void teardown() {
        driver.quit();
    }

    @AfterClass
    public static void deleteUser() {
        String accessToken = UserApiHelper.loginUserAndGetToken(email, password);
        UserApiHelper.deleteUser(accessToken);
    }

    @Test
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
}
