import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class AuthorizationPage {
    public static final String URL = "https://stellarburgers.nomoreparties.site/login";

    //локатор кнопки "Войти"
    @FindBy(how = How.XPATH, using = "//*[@id=\"root\"]/div/main/div/form/button")
    private SelenideElement buttonAuthorization;

    //локатор кнопки "Личный Кабинет"
    @FindBy(how = How.XPATH, using = "//*[@id=\"root\"]/div/header/nav/a")
    private SelenideElement buttonPersonal;

    // локатор поля ввода Почта
    @FindBy(how = How.XPATH, using = ".//input[@name='name']")
    private SelenideElement inputEmail;

    // локатор поля ввода пароль
    @FindBy(how = How.XPATH, using = ".//input[@name='Пароль']")
    private SelenideElement inputPassword;


    public SelenideElement getButtonAuthorization() {
        return buttonAuthorization;
    }

    public void clickButtonAuthorization() {
        buttonAuthorization.click();
    }

    public void clickButtonPersonal() {
        buttonPersonal.click();
    }

    public void setInputEmail(String str) {
        inputEmail.setValue(str);
    }

    public void setInputPassword(String str) {
        inputPassword.setValue(str);
    }
}
