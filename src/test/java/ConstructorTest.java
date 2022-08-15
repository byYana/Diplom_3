import ForDeleteUser.API;
import ForDeleteUser.Login;
import PageAndUser.MainPage;
import PageAndUser.NewUser;
import PageAndUser.YandexBrowser;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ConstructorTest {
    NewUser newUser;
    MainPage mainPage;
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
    @DisplayName("Проверка перехода к разделу «Соусы».")
    public void checkSauces() {
        mainPage = open(MainPage.URL, MainPage.class);
        // Проверяем, что кнопка не активна и мы не находимся на данном разделе
        assertNotEquals(MainPage.ACTIVE_SAUCES, mainPage.getAttributeSauces());
        mainPage.clickButtonSauces();
        // Проверяем, что после клика, кнопка активна и мы находимся на данном разделе
        assertEquals(MainPage.ACTIVE_SAUCES, mainPage.getAttributeSauces());
    }

    @Test
    @DisplayName("Проверка перехода к разделу «Начинки».")
    public void checkToppings() {
        mainPage = open(MainPage.URL, MainPage.class);
        // Проверяем, что кнопка не активна и мы не находимся на данном разделе
        assertNotEquals(MainPage.ACTIVE_TOPPINGS, mainPage.getAttributeToppings());
        mainPage.clickButtonToppings();
        // Проверяем, что после клика, кнопка активна и мы находимся на данном разделе
        assertEquals(MainPage.ACTIVE_TOPPINGS, mainPage.getAttributeToppings());
    }

    @Test
    @DisplayName("Проверка перехода к разделу «Булки».")
    public void checkBuns() {
        mainPage = open(MainPage.URL, MainPage.class);
        // Проверяем, что после клика, кнопка активна и мы находимся на данном разделе
        assertEquals(MainPage.ACTIVE_BUNS, mainPage.getAttributeBuns());
    }
}
