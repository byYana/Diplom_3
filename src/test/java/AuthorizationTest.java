import com.codeborne.selenide.Condition;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

public class AuthorizationTest {                    //**Вход**
    NewUser newUser;
    RegistrationPage registrationPage;
    MainPage mainPage;

    //-вход через кнопку в форме регистрации,
    //-вход через кнопку в форме восстановления пароля.
    @Before
    public void doBefore() {
        // Начало кода для запуска автотестов в Яндекс Браузере
       /* ChromeOptions options = new ChromeOptions();
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Yana\\courses\\Diplom_3\\src\\main\\resources\\yandexdriver.exe");
        options.setBinary("C:\\Users\\Yana\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
        options.addArguments("test-type=browser");
        options.addArguments("chromeoptions.args", "--no-sandbox");
        driver = new ChromeDriver(options);
        WebDriverRunner.setWebDriver(driver);
     */   // Конец кода для запуска автотестов в Яндекс Браузере
        newUser = NewUser.getRandomUser();
        API.createUser(newUser);
    }

    @After
    public void doAfter(){
        //driver.quit(); // код для запуска автотестов в Яндекс Браузере
        NewUser oldUser = new NewUser(newUser.getEmail(), newUser.getPassword());
        Response response = API.loginUser(oldUser);
        String accessToken = response.jsonPath().getString("accessToken");
        API.deleteUser(accessToken);
    }

    @Test
    public void checkLoginFromMainPage() {  //-вход по кнопке «Войти в аккаунт» на главной,
        mainPage = open(MainPage.URL,MainPage.class);
        mainPage.clickButtonLogIntoAccount();
        AuthorizationPage authorizationPage = page(AuthorizationPage.class);
        authorizationPage.setInputEmail(newUser.getEmail());
        authorizationPage.setInputPassword(newUser.getPassword());
        authorizationPage.clickButtonAuthorization();
        //проверяем, что кнопку "Оформить заказ" появилась на экране
        mainPage.getButtonMakeOrder().shouldBe(Condition.visible);
    }

    @Test
    public void check(){                      //-вход через кнопку «Личный кабинет»,
        mainPage = open(MainPage.URL,MainPage.class);
        mainPage.clickButtonPersonal();
        AuthorizationPage authorizationPage = page(AuthorizationPage.class);
        authorizationPage.setInputEmail(newUser.getEmail());
        authorizationPage.setInputPassword(newUser.getPassword());
        authorizationPage.clickButtonAuthorization();
        //проверяем, что кнопку "Оформить заказ" появилась на экране
        mainPage.getButtonMakeOrder().shouldBe(Condition.visible);
    }

}
