package site.nomoreparties.stellarburgers;

import org.junit.*;
import org.openqa.selenium.WebDriver;
import site.nomoreparties.stellarburgers.config.AppConfig;
import site.nomoreparties.stellarburgers.extensions.WebDriverFactory;
import site.nomoreparties.stellarburgers.pages.HomePage;

public class ConstructorPageNavigationTest {
    private static WebDriver driver;
    @Before
    public void setup() {
        driver = WebDriverFactory.get();
    }

    @After
    public void teardown() {
        driver.quit();
    }

    @Test
    public void checkBunsNavigation() {
        //Нажали на элемент "Булки" и проверили, что открылась соответствующая страница с булками.
        HomePage.open(driver, AppConfig.BASE_URL)
                .waitUntilPageIsLoaded(HomePage.class, AppConfig.BASE_URL)
                .goToBunsTab();
    }

    @Test
    public void checkSaucesNavigation() {
        //Нажали на элемент "Соусы" и проверили, что открылась соответствующая страница с соусами.
        HomePage.open(driver, AppConfig.BASE_URL)
                .waitUntilPageIsLoaded(HomePage.class, AppConfig.BASE_URL)
                .goToSaucesTab();
    }

    @Test
    public void checkFillingsNavigation() {
        //Нажали на элемент "Начинки" и проверили, что открылась соответствующая страница с начинками.
        HomePage.open(driver, AppConfig.BASE_URL)
                .waitUntilPageIsLoaded(HomePage.class, AppConfig.BASE_URL)
                .goToFillingsTab();
    }
}