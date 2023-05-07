package site.nomoreparties.stellarburgers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import io.qameta.allure.junit4.DisplayName;
import site.nomoreparties.stellarburgers.common.BaseTest;
import site.nomoreparties.stellarburgers.config.AppConfig;
import site.nomoreparties.stellarburgers.config.RndConf;
import site.nomoreparties.stellarburgers.config.RndStr;
import site.nomoreparties.stellarburgers.constants.Colour;
import site.nomoreparties.stellarburgers.pages.SignupPage;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(Parameterized.class)
public class SignUpInvalidPasswordTest extends BaseTest {
    @Before
    public void setupTestUser() {
        createUser();
    }

    private final String password;

    public SignUpInvalidPasswordTest(String description, String password) {
        super();
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

    @Test
    //@DisplayName("Check sign up with invalid password length")
    public void checkSignUpWithInvalidPasswordLength() {
        SignupPage.open(driver, AppConfig.SIGNUP_URL)
                .signUp(name, email, password)
                .checkInputError("Некорректный пароль", Colour.ERROR_MESSAGE_COLOR);
    }
}