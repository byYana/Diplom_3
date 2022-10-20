package PageAndUser;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ResetPasswordPage {
    public static final String URL = "https://stellarburgers.nomoreparties.site/reset-password";

    //локатор кнопки "Войти"
    @FindBy(how = How.CSS, using = ".Auth_link__1fOlj")
    private SelenideElement buttonAuthorization;

    @Step("Клик по кнопке 'Войти'")
    public void clickButtonAuthorization() {
        buttonAuthorization.click();
    }
}