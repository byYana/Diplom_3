package PageAndUser;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.visible;

public class RegistrationPage {
    public static final String URL = "https://stellarburgers.nomoreparties.site/register";
    private final String actualTextError = "Некорректный пароль";

    //локатор кнопки "Войти"
    @FindBy(how = How.XPATH, using = ".//button[contains(text(),'Зарегистрироваться')]")
    private SelenideElement buttonLogin;

    // локатор кнопки "Зарегистрироваться"
    @FindBy(how = How.CLASS_NAME, using = "Auth_link__1fOlj")
    private SelenideElement buttonRegistration;

    // локатор поля ввода Имя
    @FindBy(how = How.XPATH, using = ".//fieldset[1]/div/div/input")
    private SelenideElement inputName;

    // локатор поля ввода Почта
    @FindBy(how = How.XPATH, using = ".//fieldset[2]/div/div/input")
    private SelenideElement inputEmail;

    // локатор поля ввода пароль
    @FindBy(how = How.XPATH, using = ".//fieldset[3]/div/div/input")
    private SelenideElement inputPassword;

    // локатор текста об ошибке
    @FindBy(how = How.CSS, using = ".input__error.text_type_main-default")
    private SelenideElement textError;

    public SelenideElement getTextError() {
        return textError;
    }

    @Step("Вводим имя")
    public void setInputName(String name) {
        inputName.setValue(name);
    }

    @Step("Вводим почту")
    public void setInputEmail(String email) {
        inputEmail.setValue(email);
    }

    @Step("Вводим пароль")
    public void setInputPassword(String password) {
        inputPassword.setValue(password);
    }

    @Step("Клик по кнопке 'Зарегистрироваться'")
    public void clickButtonRegistration() {
        buttonLogin.shouldBe(visible).click();
    }

    @Step("Клик по кнопке 'Войти'")
    public void clickButtonLogin() {
        buttonRegistration.click();
    }

    @Step("Регистрация")
    public void registration(String name, String mail, String password) {
        setInputName(name);
        setInputEmail(mail);
        setInputPassword(password);
        clickButtonRegistration();
    }

}