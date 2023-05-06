package site.nomoreparties.stellarburgers.extensions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import site.nomoreparties.stellarburgers.config.AppConfig;
import site.nomoreparties.stellarburgers.config.WebDriverConfig;

import java.time.Duration;

public class WebDriverFactory {
    public static WebDriver get() {
        //String browserName = System.getenv().get("browser"); // Закомментировано, т.к. по заданию "Нужно отправить тесты на ревью с подключённым Google Chrome."
        String browserName = "chrome";

        WebDriver driver;
        switch (browserName) {
            case "chrome":
//                ChromeOptions options = new ChromeOptions();
//                options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
//                driver = new ChromeDriver(options);
                driver = new ChromeDriver();

                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            default:
                throw new RuntimeException("Browser " + browserName + " not exist");
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(WebDriverConfig.WAIT_OF_SECONDS_TIMEOUT));
        driver.manage().window().fullscreen();
        driver.navigate().to(AppConfig.LOGIN_URL);
        return driver;
    }
}
