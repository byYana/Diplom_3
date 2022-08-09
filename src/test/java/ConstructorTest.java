import ForDeleteUser.API;
import ForDeleteUser.Login;
import PageAndUser.MainPage;
import PageAndUser.NewUser;
import com.codeborne.selenide.Selenide;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.open;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ConstructorTest {          //Проверь, что работают переходы к разделам:
    NewUser newUser;
    MainPage mainPage;
    String accessToken;
    WebDriver driver;

    @Before
    public void doBefore() {
        // Начало кода для запуска автотестов в Яндекс Браузере
       /* ChromeOptions options = new ChromeOptions();
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\yandexdriver.exe");
        options.setBinary("C:\\Users\\Yana\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");// указать путь до браузеру
        options.addArguments("test-type=browser");
        options.addArguments("chromeoptions.args", "--no-sandbox");
        driver = new ChromeDriver(options);
        WebDriverRunner.setWebDriver(driver);
     */   // Конец кода для запуска автотестов в Яндекс Браузере
        newUser = NewUser.getRandomUser();
        Response responseCreate = API.createUser(newUser);
        accessToken = responseCreate.then().statusCode(SC_OK).extract().body().as(Login.class).getAccessToken();
    }

    @After
    public void doAfter() {
        //driver.quit(); // код для запуска автотестов в Яндекс Браузере
        API.deleteUser(accessToken);
        Selenide.clearBrowserCookies();
    }

    @Test
    public void checkSauces() {                   // -«Соусы»,
        mainPage = open(MainPage.URL, MainPage.class);
        // Проверяем, что кнопка не активна и мы не находимся на данном разделе
        assertNotEquals(MainPage.ACTIVE_SAUCES, mainPage.getAttributeSauces());
        mainPage.clickButtonSauces();
        // Проверяем, что после клика, кнопка активна и мы находимся на данном разделе
        assertEquals(MainPage.ACTIVE_SAUCES, mainPage.getAttributeSauces());
    }

    @Test
    public void checkToppings() {                   // -«Начинки»,
        mainPage = open(MainPage.URL, MainPage.class);
        // Проверяем, что кнопка не активна и мы не находимся на данном разделе
        assertNotEquals(MainPage.ACTIVE_TOPPINGS, mainPage.getAttributeToppings());
        mainPage.clickButtonToppings();
        // Проверяем, что после клика, кнопка активна и мы находимся на данном разделе
        assertEquals(MainPage.ACTIVE_TOPPINGS, mainPage.getAttributeToppings());
    }

    @Test
    public void checkBuns() {                   // -«Булки>»,
        mainPage = open(MainPage.URL, MainPage.class);
        // Проверяем, что после клика, кнопка активна и мы находимся на данном разделе
        assertEquals(MainPage.ACTIVE_BUNS, mainPage.getAttributeBuns());
    }
}
