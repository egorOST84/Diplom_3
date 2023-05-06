package site.nomoreparties.stellarburgers.pages.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class MenuItemComponent {

    // TODO Этот POM класс зарезервирован под элемент в заказе
    private final WebDriver driver;
    public MenuItemComponent(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
