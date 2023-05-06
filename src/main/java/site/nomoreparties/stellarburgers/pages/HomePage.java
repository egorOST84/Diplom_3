package site.nomoreparties.stellarburgers.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import site.nomoreparties.stellarburgers.config.AppConfig;
import site.nomoreparties.stellarburgers.pages.components.HeaderMenuComponent;

import java.util.List;

public class HomePage extends Page {
    private final WebDriver driver;
    private HeaderMenuComponent headerMenu;
    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        headerMenu = new HeaderMenuComponent(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = ".//button[text()='Войти в аккаунт']")
    private WebElement loginButton;
    @FindBy(xpath = ".//button[text()='Оформить заказ']")
    private WebElement orderButton;
    @FindBy(xpath = ".//span[text()='Булки']")
    private WebElement bunsTab;

    public static HomePage open(WebDriver driver, String url) {
        driver.get(url);
        return new HomePage(driver);
    }

    @Override
    public LoginPage goToLoginPage() {
        loginButton.click();
        return new LoginPage(driver);
    }

    public Page clickAccountProfileLinkToHeader() {
        headerMenu.clickOnAccountProfileLink();
        return waitUntilPageIsLoaded(AccountPage.class, AppConfig.ACCOUNT_PROFILE_URL);
    }

    public void waitUntilHomePageElementsAreVisible() {
        waitUntilElementsAreVisible(List.of(orderButton));
    }
}

