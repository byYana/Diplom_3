package PageAndUser;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ForgotPasswordPage {
    public static final String URL = "https://stellarburgers.nomoreparties.site/forgot-password";

    //локатор кнопки "Войти в аккаунт"
    @FindBy(how = How.CLASS_NAME, using = "Auth_link__1fOlj")
    private SelenideElement buttonAuthorization;

    //локатор кнопки "Восстановить"
    @FindBy(how = How.XPATH, using = ".//button[contains(text(),'Восстановить')]")
    private SelenideElement buttonRestore;

    // локатор поля ввода Почта
    @FindBy(how = How.XPATH, using = ".//input[@name='name']")
    private SelenideElement inputEmail;

    @Step("Клик по кнопке 'Войти'")
    public void clickButtonAuthorization() {
        buttonAuthorization.click();
    }

    @Step("Клик по кнопке 'Восстановить'")
    public void clickButtonRestore() {
        buttonRestore.click();
    }

    @Step("Вводим почту")
    public void setInputEmail(String mail) {
        inputEmail.setValue(mail);
    }

    @Step("Восстановить пароль.")
    public void restore(String mail) {
        setInputEmail(mail);
        clickButtonRestore();
    }
}