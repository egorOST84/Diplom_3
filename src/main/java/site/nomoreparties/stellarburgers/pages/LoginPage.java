package site.nomoreparties.stellarburgers.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import site.nomoreparties.stellarburgers.config.AppConfig;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class LoginPage extends Page {
    private final WebDriver driver;
    @FindBy(xpath = ".//label[text()='Email']/../input")
    private WebElement emailInput;
    @FindBy(xpath = ".//label[text()='Пароль']/../input")
    private WebElement passwordInput;
    @FindBy(xpath = ".//button[text()='Войти']")
    private WebElement submitButton;
    @FindBy(css = "a[href='/register']")
    private WebElement signupLink;

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public static LoginPage open(WebDriver driver, String url) {
        driver.get(url);
        return new LoginPage(driver);
    }

    public void enterEmail(String email) {
        emailInput.sendKeys(email);
    }

    public void enterPassword(String password) {
        passwordInput.sendKeys(password);
    }

    public LoginPage clickSubmitButton() {
        submitButton.click();
        return this;
    }

    @Override
    public HomePage signIn(String email, String password){
        enterEmail(email);
        enterPassword(password);
        clickSubmitButton()
        // Ждем пока страница загрузится и переходим на главную страницу
        .waitUntilPageIsLoaded(HomePage.class, AppConfig.BASE_URL)
        .verifyUrl(HomePage.class, AppConfig.BASE_URL)
        // проверяем видимость кнопки заказа на главной странице
        .waitUntilHomePageElementsAreVisible();

        return new HomePage(driver);
    }

    @Override
    public LoginPage goToLoginPage() {
        return this;
    }

    public SignupPage goToSignup() {
        signupLink.click();
        return new SignupPage(driver);
    }

    public void checkLoginPageElementsAreVisible(){
        List<WebElement> loginPageElements = List.of(emailInput, passwordInput, submitButton, signupLink);
        waitUntilElementsAreVisible(loginPageElements);
    }
}

