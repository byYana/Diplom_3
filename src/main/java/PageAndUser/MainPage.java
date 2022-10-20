package PageAndUser;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class MainPage {
    public static final String URL = "https://stellarburgers.nomoreparties.site/";
    public static final String ACTIVE_BUNS = "tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect";
    public static final String ACTIVE_SAUCES = "tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect";
    public static final String ACTIVE_TOPPINGS = "tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect";

    //локатор кнопки "Войти в аккаунт"
    @FindBy(how = How.XPATH, using = ".//section[2]/div/button")
    private SelenideElement buttonLogIntoAccount;

    //локатор кнопки "Оформить заказ"
    @FindBy(how = How.XPATH, using = ".//section[2]/div/button")
    private SelenideElement buttonMakeOrder;

    //локатор кнопки "Личный Кабинет"
    @FindBy(how = How.XPATH, using = ".//div/header/nav/a")
    private SelenideElement buttonPersonal;

    //локатор вкладки "Булки"
    @FindBy(how = How.XPATH, using = ".//span[contains(text(),'Булки')]")
    private SelenideElement buttonBuns;

    //локатор вкладки "Булки"
    @FindBy(how = How.XPATH, using = ".//section[1]/div[1]/div[1]")
    private SelenideElement buttonShellBuns;

    //локатор вкладки "Соусы"
    @FindBy(how = How.XPATH, using = ".//span[contains(text(),'Соусы')]")
    private SelenideElement buttonSauces;

    //локатор вкладки "Соусы"
    @FindBy(how = How.XPATH, using = ".//section[1]/div[1]/div[2]")
    private SelenideElement buttonShellSauces;

    //локатор вкладки "Начинки"
    @FindBy(how = How.XPATH, using = ".//span[contains(text(),'Начинки')]")
    private SelenideElement buttonToppings;

    //локатор вкладки "Начинки"
    @FindBy(how = How.XPATH, using = ".//section[1]/div[1]/div[3]")
    private SelenideElement buttonShellToppings;

    @Step("Клик по кнопке 'Войти'")
    public void clickButtonLogIntoAccount() {
        buttonLogIntoAccount.click();
    }

    public String getAttributeBuns() {
        return buttonShellBuns.getAttribute("class");
    }

    @Step("Клик по вкладке 'Соусы'")
    public void clickButtonSauces() {
        buttonSauces.click();
    }

    public String getAttributeSauces() {
        return buttonShellSauces.getAttribute("class");
    }

    @Step("Клик по вкладке 'Начинки'")
    public void clickButtonToppings() {
        buttonToppings.click();
    }

    public String getAttributeToppings() {
        return buttonShellToppings.getAttribute("class");
    }

    public SelenideElement getButtonMakeOrder() {
        return buttonMakeOrder;
    }

    @Step("Клик по вкладке 'Булки'")
    public void clickButtonPersonal() {
        buttonPersonal.click();
    }
}