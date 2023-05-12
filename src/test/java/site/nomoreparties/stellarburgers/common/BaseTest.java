package site.nomoreparties.stellarburgers.common;

import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import site.nomoreparties.stellarburgers.api.UserApiHelper;
import site.nomoreparties.stellarburgers.config.RndConf;
import site.nomoreparties.stellarburgers.config.RndStr;
import site.nomoreparties.stellarburgers.ui.driver.WebDriverCreator;

public class BaseTest {
    protected String email;
    protected String name;
    protected String password;
    public static WebDriver driver;
    @Before
    public void setup() {
        driver = WebDriverCreator.createWebDriver();
    }
    @After
    public void teardown() {
        driver.quit();
    }

    @Step("Create random user")
    public void createUser(){
        // Генерируем случайные данные для регистрации пользователя
        name = new RndStr().get(RndConf.NAME, 15);
        email = new RndStr().get(RndConf.EMAIL, 15);
        password = new RndStr().get(RndConf.PASS, 15);
        // Создаем пользователя для тестов
        UserApiHelper.createUser(name, email, password);
    }
    @Step("Delete user")
    public void deleteUser() {
        String accessToken = UserApiHelper.loginUserAndGetToken(email, password);
        UserApiHelper.deleteUser(accessToken);
    }
}
