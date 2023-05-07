package site.nomoreparties.stellarburgers.ui.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class WebDriverCreator {
    public static WebDriver createWebDriver() {
        // Определяем параметр browser для определения движка в WebDriver через конфигурационный файл application.properties
        Properties properties = new Properties();
        try (InputStream fileBrowseConfig = WebDriverCreator.class.getResourceAsStream("/application.properties")) {
            properties.load(fileBrowseConfig);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String browser = properties.getProperty("browser");
        String version = properties.getProperty("version");

        if (browser == null) {
            return createChromeDriver();
        }

        switch (browser) {
            case "yandex":
                return createYandexDriver(version);
            case "chrome":
            default:
                return createChromeDriver();
        }
    }

    private static WebDriver createChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        return new ChromeDriver(options);
    }

    private static WebDriver createYandexDriver(String chromeDriverVersion) {
        System.setProperty("webdriver.chrome.driver",
                String.format("src/test/resources/drivers/chromedriver/chromedriver_%s/chromedriver", chromeDriverVersion));
        ChromeOptions options = new ChromeOptions();
        options.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex");
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        return new ChromeDriver(options);
    }
}