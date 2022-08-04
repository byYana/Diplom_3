import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;

public class RegistrationTest {
    NewUser newUser;
    String accessToken = null;
    NewUser oldUser;

    @Before
    public void doBefore(){
        newUser= NewUser.getRandomUser();
        oldUser= new NewUser(newUser.getEmail(), newUser.getPassword());
    }

    @After
    public void doAfter(){
        if (accessToken == null) {
         Response response=API.loginUser(oldUser);
         accessToken = response.jsonPath().getString("accessToken");
        }
        API.deleteUser(accessToken);
    }

    @Test
    public void pressF(){
        RegistrationPage registrationPage = open(RegistrationPage.URL, RegistrationPage.class);
        registrationPage.setInputName(newUser.getName());
        registrationPage.setInputEmail(newUser.getEmail());
        registrationPage.setInputPassword(newUser.getPassword());
        registrationPage.clickButton();
    }



}
