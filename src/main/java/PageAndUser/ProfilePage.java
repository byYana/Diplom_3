package PageAndUser;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ProfilePage {
    public static final String URL = "https://stellarburgers.nomoreparties.site/account/profile";

    //локатор кнопки "Выход"
    @FindBy(how = How.XPATH, using = ".//button[contains(text(),'Выход')]")
    private SelenideElement buttonExit;

    //локатор кнопки "Конструктор"
    @FindBy(how = How.XPATH, using = ".//p[contains(text(),'Конструктор')]")
    private SelenideElement buttonConstructor;

    //локатор кнопки логотипа
    @FindBy(how = How.CLASS_NAME, using = "AppHeader_header__logo__2D0X2")
    private SelenideElement buttonLogo;

    public SelenideElement getButtonExit() {
        return buttonExit;
    }

    @Step("Клик по кнопке 'Выход'")
    public void clickButtonExit() {
        buttonExit.click();
    }

    @Step("Клик по кнопке 'Конструктор'")
    public void clickConstructor() {
        buttonConstructor.click();
    }

    @Step("Клик по логотипу")
    public void clickLogo() {
        buttonLogo.click();
    }
}