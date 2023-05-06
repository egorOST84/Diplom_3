package site.nomoreparties.stellarburgers;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import site.nomoreparties.stellarburgers.config.AppConfig;
import site.nomoreparties.stellarburgers.config.RndConf;
import site.nomoreparties.stellarburgers.config.RndStr;
import site.nomoreparties.stellarburgers.constants.Colour;
import site.nomoreparties.stellarburgers.extensions.WebDriverFactory;
import site.nomoreparties.stellarburgers.pages.SignupPage;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(Parameterized.class)
public class SignUpInvalidPasswordTest {
    private static WebDriver driver;
    private final String password;

    public SignUpInvalidPasswordTest(String description, String password) {
        this.password = password;
    }

    @Parameterized.Parameters(name = "{index}: {0}")
    public static Collection<Object[]> getData() {
        return Stream.of(
                new Object[]{"empty password", new RndStr().get(RndConf.PASS, 0)},
                new Object[]{"1 char", new RndStr().get(RndConf.PASS, 1)},
                new Object[]{"5 chars", new RndStr().get(RndConf.PASS, 5)}

        ).collect(Collectors.toList());
    }

    @Before
    public void setup() {
        driver = WebDriverFactory.get();
    }

    @After
    public void teardown() {
        driver.quit();
    }

    @Test
    public void checkSignUpWithInvalidPasswordLength() {
        final String name = new RndStr().get(RndConf.NAME, 15);
        final String email = new RndStr().get(RndConf.EMAIL, 15);

        SignupPage.open(driver, AppConfig.SIGNUP_URL)
                .signUp(name, email, password)
                .checkInputError("Некорректный пароль", Colour.ERROR_MESSAGE_COLOR);
    }
}
