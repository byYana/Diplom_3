import ForDeleteUser.API;
import ForDeleteUser.Login;
import PageAndUser.AuthorizationPage;
import PageAndUser.NewUser;
import PageAndUser.RegistrationPage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static org.apache.http.HttpStatus.SC_OK;

public class RegistrationTest {         //**Регистрация**
    NewUser newUser;
    RegistrationPage registrationPage;
    WebDriver driver;

    @Before
    public void doBefore() {
        // Начало кода для запуска автотестов в Яндекс Браузере
      /*  ChromeOptions options = new ChromeOptions();
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\yandexdriver.exe");
        options.setBinary("C:\\Users\\Yana\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");// указать путь до браузеру
        options.addArguments("test-type=browser");
        options.addArguments("chromeoptions.args", "--no-sandbox");
        driver = new ChromeDriver(options);
        WebDriverRunner.setWebDriver(driver);*/
        // Конец кода для запуска автотестов в Яндекс Браузере
        newUser = NewUser.getRandomUser();
        registrationPage = open(RegistrationPage.URL, RegistrationPage.class);
    }

   /* @After
    public void doAfter(){
        driver.quit(); // код для запуска автотестов в Яндекс Браузере
    }*/

    @Test
    public void checkRegistration() {               //~~-Успешную регистрацию.~~
        registrationPage.setInputName(newUser.getName());
        registrationPage.setInputEmail(newUser.getEmail());
        registrationPage.setInputPassword(newUser.getPassword());
        registrationPage.clickButtonRegistration();
        AuthorizationPage authorizationPage = page(AuthorizationPage.class);
        //проверяем, что кнопку "Войти" появилась на экране
        authorizationPage.getButtonAuthorization().shouldBe(Condition.visible);
        //удаление пользователя
        NewUser oldUser = new NewUser(newUser.getEmail(), newUser.getPassword());
        Response response = API.loginUser(oldUser);
        String accessToken = response.then().statusCode(SC_OK).extract().body().as(Login.class).getAccessToken();
        API.deleteUser(accessToken);
    }

    @Test
    public void checkDefectRegistration() {        //-Ошибку для некорректного пароля. Минимальный пароль — шесть символов.
        registrationPage.setInputName(newUser.getName());
        registrationPage.setInputEmail(newUser.getEmail());
        registrationPage.setInputPassword(RandomStringUtils.randomAlphabetic(5));
        registrationPage.clickButtonRegistration();
        //проверяем что текст об ошибке появился
        registrationPage.getTextError().shouldBe(Condition.visible);
    }
}
