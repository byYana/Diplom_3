import ForDeleteUser.API;
import ForDeleteUser.Login;
import PageAndUser.AuthorizationPage;
import PageAndUser.NewUser;
import PageAndUser.RegistrationPage;
import PageAndUser.YandexBrowser;
import com.codeborne.selenide.Condition;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static org.apache.http.HttpStatus.SC_OK;

public class RegistrationTest {
    NewUser newUser;
    RegistrationPage registrationPage;
    boolean yandex = false; // Если надо тесты запустить в Яндекс браузере, то переменная - true, для Хрома - false

    @Before
    public void doBefore() {
        if (yandex) {
            YandexBrowser.doBefore();
        }
        newUser = NewUser.getRandomUser();
        registrationPage = open(RegistrationPage.URL, RegistrationPage.class);
    }

    @After
    public void doAfter() {
        if (yandex) {
            YandexBrowser.doAfter();
        }
    }

    @Test
    @DisplayName("Проверка успешной регистрации")
    public void checkRegistration() {
        registrationPage.registration(newUser.getName(), newUser.getEmail(), newUser.getPassword());
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
    @DisplayName("Проверка ошибки для некорректного пароля. Минимальный пароль — шесть символов. ")
    public void checkDefectRegistration() {
        registrationPage.registration(newUser.getName(), newUser.getEmail(), RandomStringUtils.randomAlphabetic(5));
        //проверяем что текст об ошибке появился
        registrationPage.getTextError().shouldBe(Condition.visible);
    }
}
