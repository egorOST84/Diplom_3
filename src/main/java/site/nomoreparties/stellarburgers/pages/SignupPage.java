package site.nomoreparties.stellarburgers.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import site.nomoreparties.stellarburgers.pages.components.HeaderMenuComponent;

import java.util.List;

public class SignupPage extends Page {
    private final WebDriver driver;
    private final HeaderMenuComponent headerMenu;
    @FindBy(xpath = ".//label[text()='Имя']/../input")
    private WebElement usernameInput;

    @FindBy(xpath = ".//label[text()='Email']/../input")
    private WebElement emailInput;

    @FindBy(xpath = ".//label[text()='Пароль']/../input")
    private WebElement passwordInput;

    @FindBy(xpath = ".//button[text()='Зарегистрироваться']")
    private WebElement submitButton;

    @FindBy(css = "a[href='/login']")
    private WebElement loginLink;

    @FindBy(css = ".input__icon.input__icon-action")
    private WebElement togglePasswordButton;

    @FindBy(css = ".input__error.text_type_main-default")
    private WebElement inputPasswordError;

    public SignupPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        headerMenu = new HeaderMenuComponent(driver);
        PageFactory.initElements(driver, this);
    }

    public static SignupPage open(WebDriver driver, String url) {
        driver.get(url);
        return new SignupPage(driver);
    }

    @Override
    public LoginPage goToLoginPage() {
        loginLink.click();
        return new LoginPage(driver);
    }

    public void enterUsername(String username) {
        usernameInput.sendKeys(username);
    }

    public void enterEmail(String email) {
        emailInput.sendKeys(email);
    }

    public void enterPassword(String password) {
        passwordInput.sendKeys(password);
    }

    public void clickSubmitButton() {
        submitButton.click();
    }

    public void clickTogglePasswordButton() {
        togglePasswordButton.click();
    }

    public SignupPage signUp(String username, String email,String password){
        enterUsername(username);
        enterEmail(email);
        enterPassword(password);
        clickSubmitButton();
        return this;
    }

    public void checkInputError(String expectedMessage, String expectedColour) {
        assertText(inputPasswordError, expectedMessage);
        assertColour(inputPasswordError, expectedColour);
    }

    public void waitUntilSignPagIsLoaded() {
        List<WebElement> pageElements = List.of(usernameInput, emailInput, passwordInput, submitButton);

        checkElementsAreVisible(pageElements);
    }
}
