package site.nomoreparties.stellarburgers.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import site.nomoreparties.stellarburgers.pages.components.HeaderMenuComponent;

public class PasswordRecoveryPage extends Page {
    private final WebDriver driver;
    private final HeaderMenuComponent headerMenu;
    @FindBy(xpath = ".//label[text()='Email']/../input")
    private WebElement emailInput;

    @FindBy(css = "a[href='/login']")
    private WebElement loginLink;

    public PasswordRecoveryPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        headerMenu = new HeaderMenuComponent(driver);
        PageFactory.initElements(driver, this);
    }

    public static PasswordRecoveryPage open(WebDriver driver, String url) {
        driver.get(url);
        return new PasswordRecoveryPage(driver);
    }

    @Override
    public LoginPage goToLoginPage() {
        loginLink.click();
        return new LoginPage(driver);
    }
}
