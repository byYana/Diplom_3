package PageAndUser;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class YandexBrowser {
    static WebDriver driver;

    public static void doBefore() {
        ChromeOptions options = new ChromeOptions();
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\yandexdriver.exe");
        options.setBinary("C:\\Users\\Yana\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");// указать путь до браузеру
        options.addArguments("test-type=browser");
        options.addArguments("chromeoptions.args", "--no-sandbox");
        driver = new ChromeDriver(options);
        WebDriverRunner.setWebDriver(driver);
    }

    public static void doAfter() {
        driver.quit();
    }
}
