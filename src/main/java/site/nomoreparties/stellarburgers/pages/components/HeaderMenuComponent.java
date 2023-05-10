package site.nomoreparties.stellarburgers.pages.components;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import site.nomoreparties.stellarburgers.pages.LoginPage;
import site.nomoreparties.stellarburgers.pages.Page;

import java.util.List;

public class HeaderMenuComponent extends Page {
    private final WebDriver driver;
    public HeaderMenuComponent(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".AppHeader_header__link__3D_hX.AppHeader_header__link_active__1IkJo")
    private WebElement navConstructorLink;
    @FindBy(css = ".AppHeader_header__logo__2D0X2")
    private WebElement navLogoLink;
    @FindBy(css = "a[href='/account']")
    private WebElement accountProfileLink;
    @FindBy(css = "a[href='/feed']")
    private WebElement ordersLink;

    public static HeaderMenuComponent open(WebDriver driver, String url) {
        driver.get(url);
        return new HeaderMenuComponent(driver);
    }

    public void clickOnAccountProfileLink() {
        accountProfileLink.click();
    }

    public void clickOnNavLogoLink() {
        navLogoLink.click();
    }

    public void clickOnNavConstructorLink() {
        navConstructorLink.click();
    }

    @Override
    public LoginPage goToLoginPage() {
        accountProfileLink.click();
        return new LoginPage(driver);
    }

    @Step("Checking visibility of elements on account page")
    public HeaderMenuComponent checkHeaderMenuComponentElementsAreVisible(){
        List<WebElement> accountElements = List.of(navConstructorLink, navLogoLink, ordersLink, accountProfileLink);
        checkElementsAreVisible(accountElements);
        return this;
    }
}