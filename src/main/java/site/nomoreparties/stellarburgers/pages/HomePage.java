package site.nomoreparties.stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import site.nomoreparties.stellarburgers.config.AppConfig;
import site.nomoreparties.stellarburgers.pages.components.HeaderMenuComponent;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class HomePage extends Page {
    private final WebDriver driver;
    private final HeaderMenuComponent headerMenu;
    @FindBy(xpath = ".//button[text()='Войти в аккаунт']")
    private WebElement loginButton;
    @FindBy(xpath = ".//button[text()='Оформить заказ']")
    private WebElement orderButton;
    @FindBy(xpath = ".//h1[text()='Соберите бургер']")
    private WebElement assembleBurgerText;


    // Элементы меню "Соберите бургер"
    @FindBy(xpath = ".//span[text()='Булки']")
    private WebElement bunsTab;
    @FindBy(xpath = ".//span[text()='Соусы']")
    private WebElement saucesTab;
    @FindBy(xpath = ".//span[text()='Начинки']")
    private WebElement fillingsTab;

    // Секции меню "Соберите бургер"
    @FindBy(xpath = ".//div[@Class='BurgerIngredients_ingredients__menuContainer__Xu3Mo']/h2[text()='Булки']")
    private WebElement bunsSection;
    @FindBy(xpath = ".//div[@Class='BurgerIngredients_ingredients__menuContainer__Xu3Mo']/h2[text()='Соусы']")
    private WebElement saucesSection;
    @FindBy(xpath = ".//div[@Class='BurgerIngredients_ingredients__menuContainer__Xu3Mo']/h2[text()='Начинки']")
    private WebElement fillingsSection;

    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        headerMenu = new HeaderMenuComponent(driver);
        PageFactory.initElements(driver, this);
    }

    @Step("Open Home page")
    public static HomePage open(WebDriver driver, String url) {
        driver.get(url);
        return new HomePage(driver);
    }

    @Override
    @Step("Open login page")
    public LoginPage goToLoginPage() {
        loginButton.click();
        return new LoginPage(driver);
    }

    @Step("Clicking on account profile link in header")
    public Page clickOnAccountProfileLink() {
        headerMenu.clickOnAccountProfileLink();
        if (driver.getCurrentUrl().equals(AppConfig.LOGIN_URL)){
            return new LoginPage(driver);
        }
        return new AccountPage(driver);
    }

    @Step("Clicking on logo link in header")
    public HomePage clickOnLogoLinkInHeader() {
        headerMenu.clickOnNavLogoLink();
        return new HomePage(driver);
    }

    @Step("Clicking on constructor link in header")
    public HomePage clickOnConstructorLinkInHeader() {
        headerMenu.clickOnNavConstructorLink();
        return new HomePage(driver);
    }

    @Step("Click on bans tab and verify buns section is opened")
    public void goToBunsTab() {
        // Переход к разделу "Соусы" так как таю "Булки" некликабельный по умолчанию
        goToSaucesTab();
        // Нажимаем на таб "Булки"
        if(isElementClickable(bunsTab)) {
            bunsTab.click();
        }
        // Проверяем, что видна секция с булками
        assertTrue(isElementVisible(bunsSection));
    }

    @Step("Click on sauces tab and verify sauces section is opened")
    public void goToSaucesTab() {
        // Нажимаем на таб "Соусы"
        if(isElementClickable(saucesTab)) {
            saucesTab.click();
        }
        // Проверяем, что видна секция с соусами
        assertTrue(isElementVisible(saucesSection));

    }

    @Step("Click on fillings tab and verify fillings section is opened")
    public void goToFillingsTab() {
        // Нажимаем на таб "Начинки"
        if(isElementClickable(fillingsTab)) {
            fillingsTab.click();
        }
        // Проверяем, что видна секция с начинками
        assertTrue(isElementVisible(fillingsSection));
    }

    @Step("Verifying text to be visible on page")
    public void checkPageContentText(String expectedText) {
        verifyText(assembleBurgerText, expectedText);
    }

    @Step("Checking visibility of elements on account page")
    public HomePage checkHomePageElementsAreVisible(){
        List<WebElement> accountElements = List.of(loginButton, assembleBurgerText, bunsTab, saucesTab, fillingsTab);
        checkElementsAreVisible(accountElements);
        headerMenu.checkHeaderMenuComponentElementsAreVisible();
        return this;
    }
}