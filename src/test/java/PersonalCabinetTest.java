import ForDeleteUser.API;
import ForDeleteUser.Login;
import PageAndUser.*;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static org.apache.http.HttpStatus.SC_OK;

public class PersonalCabinetTest {
    NewUser newUser;
    MainPage mainPage;
    AuthorizationPage authorizationPage;
    String accessToken;
    boolean yandex = false; // Если надо тесты запустить в Яндекс браузере, то переменная - true, для Хрома - false

    @Before
    public void doBefore() {
        if (yandex) {
            YandexBrowser.doBefore();
        }
        newUser = NewUser.getRandomUser();
        Response responseCreate = API.createUser(newUser);
        accessToken = responseCreate.then().statusCode(SC_OK).extract().body().as(Login.class).getAccessToken();
    }

    @After
    public void doAfter() {
        API.deleteUser(accessToken);
        if (yandex) {
            YandexBrowser.doAfter();
        } else {
            Selenide.clearBrowserCookies();
        }
    }

    @Test
    @DisplayName("Проверка перехода по клику на «Личный кабинет». Неавторизованный пользователь.")
    public void checkPersonalCabinetLogout() {
        mainPage = open(MainPage.URL, MainPage.class);
        mainPage.clickButtonPersonal();
        authorizationPage = page(AuthorizationPage.class);
        //проверяем, что кнопку "Войти" появилась на экране
        authorizationPage.getButtonAuthorization().shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("Проверка перехода по клику на «Личный кабинет». Авторизованный пользователь.")
    public void checkPersonalCabinetLogin() {
        authorizationPage = open(AuthorizationPage.URL, AuthorizationPage.class);
        authorizationPage.login(newUser.getEmail(), newUser.getPassword());
        mainPage = page(MainPage.class);
        mainPage.clickButtonPersonal();
        ProfilePage profilePage = page(ProfilePage.class);
        //проверяем, что кнопку "Выйти" появилась на экране
        profilePage.getButtonExit().shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("Проверка перехода по клику на «Конструктор»")
    public void checkGoToConstructor() {
        authorizationPage = open(AuthorizationPage.URL, AuthorizationPage.class);
        authorizationPage.login(newUser.getEmail(), newUser.getPassword());
        mainPage = page(MainPage.class);
        mainPage.clickButtonPersonal();
        ProfilePage profilePage = page(ProfilePage.class);
        profilePage.clickConstructor();
        //проверяем, что кнопку "Оформить заказ" появилась на экране
        mainPage.getButtonMakeOrder().shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("Проверка перехода по клику на логотип Stellar Burgers.")
    public void checkGoToLogo() {
        authorizationPage = open(AuthorizationPage.URL, AuthorizationPage.class);
        authorizationPage.login(newUser.getEmail(), newUser.getPassword());
        mainPage = page(MainPage.class);
        mainPage.clickButtonPersonal();
        ProfilePage profilePage = page(ProfilePage.class);
        profilePage.clickLogo();
        //проверяем, что кнопку "Оформить заказ" появилась на экране
        mainPage.getButtonMakeOrder().shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("Проверка выхода по кнопке «Выйти» в личном кабинете.")
    public void check() {
        authorizationPage = open(AuthorizationPage.URL, AuthorizationPage.class);
        authorizationPage.login(newUser.getEmail(), newUser.getPassword());
        mainPage = page(MainPage.class);
        mainPage.clickButtonPersonal();
        ProfilePage profilePage = page(ProfilePage.class);
        profilePage.clickButtonExit();
        //проверяем, что кнопку "Войти" появилась на экране
        authorizationPage.getButtonAuthorization().shouldBe(Condition.visible);
    }
}

