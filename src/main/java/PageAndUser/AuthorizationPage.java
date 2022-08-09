package PageAndUser;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class AuthorizationPage {
    public static final String URL = "https://stellarburgers.nomoreparties.site/login";

    //локатор кнопки "Войти"
    @FindBy(how = How.XPATH, using = ".//button[contains(text(),'Войти')]")
    private SelenideElement buttonAuthorization;

    //локатор кнопки "Восстановить пароль"
    @FindBy(how = How.XPATH, using = ".//a[contains(text(),'Восстановить пароль')]")
    private SelenideElement buttonRestorePassword;

    // локатор поля ввода Почта
    @FindBy(how = How.XPATH, using = ".//input[@name='name']")
    private SelenideElement inputEmail;

    // локатор поля ввода пароль
    @FindBy(how = How.XPATH, using = ".//input[@name='Пароль']")
    private SelenideElement inputPassword;

    public SelenideElement getButtonAuthorization() {
        return buttonAuthorization;
    }

    @Step("Клик по кнопке 'Войти'")
    public void clickButtonAuthorization() {
        buttonAuthorization.click();
    }

    @Step("Клик по кнопке 'Восстановить пароль'")
    public void clickButtonRestorePassword() {
        buttonRestorePassword.click();
    }

    @Step("Вводим почту")
    public void setInputEmail(String mail) {
        inputEmail.setValue(mail);
    }

    @Step("Вводим пароль")
    public void setInputPassword(String password) {
        inputPassword.setValue(password);
    }

    @Step("Авторизация")
    public void login(String mail,String password){
        setInputEmail(mail);
        setInputPassword(password);
    }
}
