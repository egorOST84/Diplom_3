package site.nomoreparties.stellarburgers.pages.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class OrderPageComponent {

    // TODO Этот POM класс зарезервирован под оформление заказа
    private final WebDriver driver;
    public OrderPageComponent(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
