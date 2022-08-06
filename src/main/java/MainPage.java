import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.visible;

public class MainPage {
    public static final String URL = "https://stellarburgers.nomoreparties.site/";

    //локатор кнопки "Войти в аккаунт"
    @FindBy(how = How.XPATH, using = "//*[@id=\"root\"]/div/main/section[2]/div/button")
    private SelenideElement buttonLogIntoAccount;

    //локатор кнопки "Оформить заказ"
    @FindBy(how = How.XPATH, using = "//*[@id=\"root\"]/div/main/section[2]/div/button")
    private SelenideElement buttonMakeOrder;

    //локатор кнопки "Личный Кабинет"
    @FindBy(how = How.XPATH, using = "//*[@id=\"root\"]/div/header/nav/a")
    private SelenideElement buttonPersonal;

    @Step("Клик по кнопке 'Войти в аккаунт'")
    public void clickButtonLogIntoAccount() {
        buttonLogIntoAccount.click();
    }

    public SelenideElement getButtonMakeOrder() {
        return buttonMakeOrder;
    }
    public void clickButtonPersonal() {
        buttonPersonal.click();
    }
}
