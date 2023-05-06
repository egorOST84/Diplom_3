package site.nomoreparties.stellarburgers.pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import site.nomoreparties.stellarburgers.config.WebDriverConfig;

import java.lang.reflect.Constructor;
import java.time.Duration;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public abstract class Page {
    private final WebDriver driver;
    public Page(WebDriver driver) {
        this.driver = driver;
    }

    private WebElement waitForElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WebDriverConfig.WAIT_OF_SECONDS_TIMEOUT));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public HomePage signIn(String email, String password) {
        goToLoginPage().signIn(email, password);
        return new HomePage(driver);
    }
    public abstract LoginPage goToLoginPage();

    public void assertText(WebElement element, String expectedText) {
        try {
            WebElement webElement = waitForElement(element);
            String actualText = webElement.getText();
            assertEquals(expectedText, actualText);
        } catch (Exception e) {
            Assert.fail("Element not found: " + element);
        }
    }

    public void assertColour(WebElement element, String expectedColour) {
        try {
            WebElement errorElement = waitForElement(element);
            String actualColour = errorElement.getCssValue("color");
            assertEquals(expectedColour, Color.fromString(actualColour).asHex());
        } catch (Exception e) {
            Assert.fail("Element not found: " + element);
        }
    }

    public <T> T waitUntilPageIsLoaded(Class<T> pageClass, String expectedUrl) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WebDriverConfig.WAIT_OF_SECONDS_TIMEOUT));
        wait.until(ExpectedConditions.urlToBe(expectedUrl));
        try {
            Constructor<T> constructor = pageClass.getConstructor(WebDriver.class);
            return constructor.newInstance(driver);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create instance of page class", e);
        }
    }

    public void checkElementsAreVisible(List<WebElement> elements) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WebDriverConfig.WAIT_OF_SECONDS_TIMEOUT));

        for (WebElement element : elements) {
                assertTrue(element.isDisplayed());
        }
    }

    public void verifyInputValue(WebElement inputElement, String value){
        try {
            assertTrue(inputElement.getAttribute("value").equalsIgnoreCase(value));
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid web element: " + inputElement);
        }
    }
}
