import ForDeleteUser.API;
import ForDeleteUser.Login;
import PageAndUser.*;
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

public class AuthorizationTest {                    //**Вход**
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
    public void checkLoginFromMainPage() {                      //-вход по кнопке «Войти в аккаунт» на главной,
        mainPage = open(MainPage.URL, MainPage.class);
        mainPage.clickButtonLogIntoAccount();
        authorizationPage = page(AuthorizationPage.class);
        authorizationPage.login(newUser.getEmail(),newUser.getPassword());
        authorizationPage.clickButtonAuthorization();
        //проверяем, что кнопку "Оформить заказ" появилась на экране
        mainPage.getButtonMakeOrder().shouldBe(Condition.visible);
    }

    @Test
    public void checkLoginPersonalCabinet() {                      //-вход через кнопку «Личный кабинет»,
        mainPage = open(MainPage.URL, MainPage.class);
        mainPage.clickButtonPersonal();
        authorizationPage = page(AuthorizationPage.class);
        authorizationPage.login(newUser.getEmail(),newUser.getPassword());
        authorizationPage.clickButtonAuthorization();
        //проверяем, что кнопку "Оформить заказ" появилась на экране
        mainPage.getButtonMakeOrder().shouldBe(Condition.visible);
    }

    @Test
    public void checkLoginRegistrationForm() {                     //-вход через кнопку в форме регистрации,
        RegistrationPage registrationPage = open(RegistrationPage.URL, RegistrationPage.class);
        registrationPage.clickButtonLogin();
        authorizationPage = page(AuthorizationPage.class);
        authorizationPage.login(newUser.getEmail(),newUser.getPassword());
        authorizationPage.clickButtonAuthorization();
        MainPage mainPage = page(MainPage.class);
        //проверяем, что кнопку "Оформить заказ" появилась на экране
        mainPage.getButtonMakeOrder().shouldBe(Condition.visible);
    }

    @Test
    public void checkLoginForgotPassword() {    //-вход через кнопку в форме восстановления пароля. Шаг 1.
        authorizationPage = open(AuthorizationPage.URL,AuthorizationPage.class);
        authorizationPage.clickButtonRestorePassword();
        ForgotPasswordPage forgotPasswordPage = page(ForgotPasswordPage.class);
        forgotPasswordPage.clickButtonAuthorization();
        authorizationPage.login(newUser.getEmail(),newUser.getPassword());
        authorizationPage.clickButtonAuthorization();
        MainPage mainPage = page(MainPage.class);
        //проверяем, что кнопку "Оформить заказ" появилась на экране
        mainPage.getButtonMakeOrder().shouldBe(Condition.visible);
    }

    @Test
    public void checkLoginResetPassword() {    //-вход через кнопку в форме восстановления пароля. Шаг 2.
        authorizationPage = open(AuthorizationPage.URL,AuthorizationPage.class);
        authorizationPage.clickButtonRestorePassword();
        ForgotPasswordPage forgotPasswordPage = page(ForgotPasswordPage.class);
        forgotPasswordPage.setInputEmail(newUser.getEmail());
        forgotPasswordPage.clickButtonRestore();
        ResetPasswordPage resetPasswordPage = page(ResetPasswordPage.class);
        resetPasswordPage.clickButtonAuthorization();
        authorizationPage.login(newUser.getEmail(),newUser.getPassword());
        authorizationPage.clickButtonAuthorization();
        MainPage mainPage = page(MainPage.class);
        //проверяем, что кнопку "Оформить заказ" появилась на экране
        mainPage.getButtonMakeOrder().shouldBe(Condition.visible);
    }
}