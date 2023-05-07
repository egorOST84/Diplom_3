package site.nomoreparties.stellarburgers.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import site.nomoreparties.stellarburgers.config.AppConfig;
import site.nomoreparties.stellarburgers.pages.components.HeaderMenuComponent;

import static org.junit.Assert.assertTrue;

public class HomePage extends Page {
    private final WebDriver driver;
    private final HeaderMenuComponent headerMenu;
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

    // Элементы меню "Соберите бургер"
    @FindBy(xpath = ".//span[text()='Булки']")
    private WebElement bunsTab;
    @FindBy(xpath = ".//span[text()='Соусы']")
    private WebElement saucesTab;
    @FindBy(xpath = ".//span[text()='Начинки']")
    private WebElement fillingsTab;

    // Секции меню "Соберите бургер"
    @FindBy(xpath = ".//div[@Class='BurgerIngredients_ingredients__menuContainer__Xu3Mo']/h2[1]")
    private WebElement bunsSection;
    @FindBy(xpath = ".//div[@Class='BurgerIngredients_ingredients__menuContainer__Xu3Mo']/h2[2]")
    private WebElement saucesSection;
    @FindBy(xpath = ".//div[@Class='BurgerIngredients_ingredients__menuContainer__Xu3Mo']/h2[3]")
    private WebElement fillingsSection;

    public static HomePage open(WebDriver driver, String url) {
        driver.get(url);
        return new HomePage(driver);
    }

    @Override
    public LoginPage goToLoginPage() {
        loginButton.click();
        return new LoginPage(driver);
    }

    public Page clickOnAccountProfileLink() {
        headerMenu.clickOnAccountProfileLink();
        if (driver.getCurrentUrl().equals(AppConfig.LOGIN_URL)){
            return new LoginPage(driver);
        }
        return new AccountPage(driver);
    }

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

    public void goToSaucesTab() {
        // Нажимаем на таб "Соусы"
        if(isElementClickable(saucesTab)) {
            saucesTab.click();
        }
        // Проверяем, что видна секция с соусами
        assertTrue(isElementVisible(saucesSection));

    }

    public void goToFillingsTab() {
        // Нажимаем на таб "Начинки"
        if(isElementClickable(fillingsTab)) {
            fillingsTab.click();
        }
        // Проверяем, что видна секция с начинками
        assertTrue(isElementVisible(fillingsSection));
    }
}