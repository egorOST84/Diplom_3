package site.nomoreparties.stellarburgers.common;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import site.nomoreparties.stellarburgers.api.UserApiHelper;
import site.nomoreparties.stellarburgers.config.RndConf;
import site.nomoreparties.stellarburgers.config.RndStr;
import site.nomoreparties.stellarburgers.extensions.WebDriverFactory;

public class BaseTest {
    protected String email;
    protected String name;
    protected String password;
    public static WebDriver driver;
    @Before
    public void setup() {
        driver = WebDriverFactory.get();
    }
    @After
    public void teardown() {
        driver.quit();
    }

    public void createUser(){
        // Генерируем случайные данные для регистрации пользователя
        name = new RndStr().get(RndConf.NAME, 15);
        email = new RndStr().get(RndConf.EMAIL, 15);
        password = new RndStr().get(RndConf.PASS, 15);
        // Создаем пользователя для тестов
        UserApiHelper.createUser(name, email, password);
    }
    public void deleteUser() {
        String accessToken = UserApiHelper.loginUserAndGetToken(email, password);
        UserApiHelper.deleteUser(accessToken);
    }
}
