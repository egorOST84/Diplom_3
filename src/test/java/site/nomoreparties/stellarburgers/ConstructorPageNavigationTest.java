package site.nomoreparties.stellarburgers;

import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import site.nomoreparties.stellarburgers.common.BaseTest;
import site.nomoreparties.stellarburgers.config.AppConfig;
import site.nomoreparties.stellarburgers.pages.HomePage;

public class ConstructorPageNavigationTest extends BaseTest {
    @Test
    @DisplayName("Check navigation to Buns page")
    public void checkBunsNavigation() {
        //Нажали на элемент "Булки" и проверили, что открылась соответствующая страница с булками.
        HomePage.open(driver, AppConfig.BASE_URL)
                .waitUntilPageIsLoaded(HomePage.class, AppConfig.BASE_URL)
                .goToBunsTab();
    }

    @Test
    @DisplayName("Check navigation to Sauces page")
    public void checkSaucesNavigation() {
        //Нажали на элемент "Соусы" и проверили, что открылась соответствующая страница с соусами.
        HomePage.open(driver, AppConfig.BASE_URL)
                .waitUntilPageIsLoaded(HomePage.class, AppConfig.BASE_URL)
                .goToSaucesTab();
    }

    @Test
    @DisplayName("Check navigation to Fillings page")
    public void checkFillingsNavigation() {
        //Нажали на элемент "Начинки" и проверили, что открылась соответствующая страница с начинками.
        HomePage.open(driver, AppConfig.BASE_URL)
                .waitUntilPageIsLoaded(HomePage.class, AppConfig.BASE_URL)
                .goToFillingsTab();
    }
}