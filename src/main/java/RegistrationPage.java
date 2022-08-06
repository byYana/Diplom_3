import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;

public class RegistrationPage {
    public static final String URL = "https://stellarburgers.nomoreparties.site/register";
    private String actualTextError = "Некорректный пароль";

    //локатор кнопки "Зарегистрироваться"
    @FindBy(how = How.XPATH, using = "//*[@id=\"root\"]/div/main/div/form/button")
    private SelenideElement buttonRegistration;

    // локатор поля ввода Имя
    @FindBy(how = How.XPATH, using = "//*[@id=\"root\"]/div/main/div/form/fieldset[1]/div/div/input")
    private SelenideElement inputName;

    // локатор поля ввода Почта
    @FindBy(how = How.XPATH, using = "//*[@id=\"root\"]/div/main/div/form/fieldset[2]/div/div/input")
    private SelenideElement inputEmail;

    // локатор поля ввода пароль
    @FindBy(how = How.XPATH, using = "//*[@id=\"root\"]/div/main/div/form/fieldset[3]/div/div/input")
    private SelenideElement inputPassword;

    // локатор текста об ошибке
    @FindBy(how = How.CSS, using = ".input__error.text_type_main-default")
    private SelenideElement textError;

    public SelenideElement getTextError() {
        return textError;
    }

    public void setInputName(String str) {
        inputName.setValue(str);
    }

    public void setInputEmail(String str) {
        inputEmail.setValue(str);
    }

    public void setInputPassword(String str) {
        inputPassword.setValue(str);
    }

    @Step("Клик по кнопке 'Зарегистрироваться'")
    public void clickButton() {
        buttonRegistration.shouldBe(visible).click();
    }
}
