package site.nomoreparties.stellarburgers;

import org.junit.Test;
import site.nomoreparties.stellarburgers.common.BaseTest;
import site.nomoreparties.stellarburgers.config.AppConfig;
import site.nomoreparties.stellarburgers.pages.HomePage;

public class NavigationTest extends BaseTest {
    @Test
    public void testLogoNavigation() {
        HomePage.open(driver, AppConfig.BASE_URL)
                .waitUntilPageIsLoaded(HomePage.class, AppConfig.BASE_URL)
                .clickOnLogoLinkInHeader()
                .checkHomePageElementsAreVisible();
    }

    @Test
    public void testConstructorNavigation() {
        HomePage.open(driver, AppConfig.BASE_URL)
                .waitUntilPageIsLoaded(HomePage.class, AppConfig.BASE_URL)
                .clickOnConstructorLinkInHeader()
                .checkHomePageElementsAreVisible();
    }
}