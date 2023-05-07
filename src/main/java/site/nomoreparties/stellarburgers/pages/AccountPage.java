package site.nomoreparties.stellarburgers.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AccountPage extends Page {
    private final WebDriver driver;
    @FindBy(css = "a[href='/account/profile']")
    private WebElement profileLink;
    @FindBy(css = "a[href='/account/order-history']")
    private WebElement orderHistoryLink;
    @FindBy(xpath = ".//label[text()='Имя']/../input")
    private WebElement usernameInput;
    @FindBy(xpath = ".//label[text()='Логин']/../input")
    private WebElement emailInput;
    @FindBy(xpath = ".//label[text()='Пароль']/../input")
    private WebElement passwordInput;
    @FindBy(xpath = ".//button[text()='Выход']")
    private WebElement logoutButton;
    @FindBy(xpath = ".//*[text()='Сохранить']")
    private WebElement submitButton;
    @FindBy(css = ".Account_text__fZAIn.text.text_type_main-default")
    private WebElement contentText;

    public AccountPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Override
    public LoginPage goToLoginPage() {
        return null;
    }

    public static AccountPage open(WebDriver driver, String url) {
        driver.get(url);
        return new AccountPage(driver);
    }

    public void verifyAccountProfileData(String name, String login){
        verifyInputValue(usernameInput, name);
        verifyInputValue(emailInput, login);
    }

    public AccountPage checkAccountPageElementsAreVisible(){
        List<WebElement> accountElements = List.of(profileLink, orderHistoryLink, usernameInput, emailInput, passwordInput, submitButton, logoutButton);
        checkElementsAreVisible(accountElements);
        return this;
    }

    public void checkPageContentText(String expectedText) {
        verifyText(contentText, expectedText);
    }

    public LoginPage goToLogout() {
        logoutButton.click();
        return new LoginPage(driver);
    }
}