import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.visible;

public class RegistrationPage {
    public static final String URL= "https://stellarburgers.nomoreparties.site/register";

    //локатор кнопки "Зарегистрироваться"
    @FindBy(how = How.XPATH,using = "//*[@id=\"root\"]/div/main/div/form/button")
    private SelenideElement buttonRegistration;

    // локатор поля ввода Имя
    @FindBy(how = How.XPATH,using = "//*[@id=\"root\"]/div/main/div/form/fieldset[1]/div/div/input")
    private SelenideElement inputName;

    // локатор поля ввода Почта
    @FindBy(how = How.XPATH,using = "//*[@id=\"root\"]/div/main/div/form/fieldset[2]/div/div/input")
    private SelenideElement inputEmail;

    // локатор поля ввода пароль
    @FindBy(how = How.XPATH,using = "//*[@id=\"root\"]/div/main/div/form/fieldset[3]/div/div/input")
    private SelenideElement inputPassword;




// @FindBy(how = How.XPATH,using = ".//div[@class='Home_Column__xlKDK']/button[text()='Проедет без поздарядки']")


    public void setInputName(String str) {
        inputName.setValue(str);
    }
    public void setInputEmail(String str){
        inputEmail.setValue(str);
    }
    public void setInputPassword(String str){
        inputPassword.setValue(str);
    }
    public void clickButton(){
        buttonRegistration.shouldBe(visible).click();
    }
}
