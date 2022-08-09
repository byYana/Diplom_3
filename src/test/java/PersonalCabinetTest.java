import ForDeleteUser.API;
import ForDeleteUser.Login;
import PageAndUser.AuthorizationPage;
import PageAndUser.MainPage;
import PageAndUser.NewUser;
import PageAndUser.ProfilePage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static org.apache.http.HttpStatus.SC_OK;

public class PersonalCabinetTest {
    NewUser newUser;
    MainPage mainPage;
    AuthorizationPage authorizationPage;
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
    public void checkPersonalCabinetLogout() {                //Проверь переход по клику на «Личный кабинет». Неавторизованный пользователь
        mainPage = open(MainPage.URL, MainPage.class);
        mainPage.clickButtonPersonal();
        authorizationPage = page(AuthorizationPage.class);
        //проверяем, что кнопку "Войти" появилась на экране
        authorizationPage.getButtonAuthorization().shouldBe(Condition.visible);
    }

    @Test
    public void checkPersonalCabinetLogin() {                //Проверь переход по клику на «Личный кабинет». Авторизованный пользователь
        authorizationPage = open(AuthorizationPage.URL, AuthorizationPage.class);
        authorizationPage.login(newUser.getEmail(),newUser.getPassword());
        authorizationPage.clickButtonAuthorization();
        mainPage = page(MainPage.class);
        mainPage.clickButtonPersonal();
        ProfilePage profilePage = page(ProfilePage.class);
        //проверяем, что кнопку "Выйти" появилась на экране
        profilePage.getButtonExit().shouldBe(Condition.visible);
    }

    @Test
    public void checkGoToConstructor() {            //Проверь переход по клику на «Конструктор»
        authorizationPage = open(AuthorizationPage.URL, AuthorizationPage.class);
        authorizationPage.login(newUser.getEmail(),newUser.getPassword());
        authorizationPage.clickButtonAuthorization();
        mainPage = page(MainPage.class);
        mainPage.clickButtonPersonal();
        ProfilePage profilePage = page(ProfilePage.class);
        profilePage.clickConstructor();
        //проверяем, что кнопку "Оформить заказ" появилась на экране
        mainPage.getButtonMakeOrder().shouldBe(Condition.visible);
    }

    @Test
    public void checkGoToLogo() {            //Проверь переход по клику на логотип Stellar Burgers.
        authorizationPage = open(AuthorizationPage.URL, AuthorizationPage.class);
        authorizationPage.login(newUser.getEmail(),newUser.getPassword());
        authorizationPage.clickButtonAuthorization();
        mainPage = page(MainPage.class);
        mainPage.clickButtonPersonal();
        ProfilePage profilePage = page(ProfilePage.class);
        profilePage.clickLogo();
        //проверяем, что кнопку "Оформить заказ" появилась на экране
        mainPage.getButtonMakeOrder().shouldBe(Condition.visible);
    }

    @Test
    public void check() {           //Проверь выход по кнопке «Выйти» в личном кабинете.
        authorizationPage = open(AuthorizationPage.URL, AuthorizationPage.class);
        authorizationPage.login(newUser.getEmail(),newUser.getPassword());
        authorizationPage.clickButtonAuthorization();
        mainPage = page(MainPage.class);
        mainPage.clickButtonPersonal();
        ProfilePage profilePage = page(ProfilePage.class);
        profilePage.clickButtonExit();
        //проверяем, что кнопку "Войти" появилась на экране
        authorizationPage.getButtonAuthorization().shouldBe(Condition.visible);
    }
}

