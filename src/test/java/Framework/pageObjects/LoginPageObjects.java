package Framework.pageObjects;

import base.BasePage;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class LoginPageObjects extends BasePage {

    public LoginPageObjects(AppiumDriver driver) {
        super(driver);
    }

    By tcField = By.id("com.turknet.oim:id/et_login_tckn");
    By passwordField = By.id("com.turknet.oim:id/et_login_password");
    By loginButton = By.id("com.turknet.oim:id/btn_login");

    public LoginPageObjects checkTCField(){
        checkElementIfExist(tcField);
        return this;
    }

    public LoginPageObjects checkPasswordField(){
        checkElementIfExist(passwordField);
        return this;
    }

    public LoginPageObjects loginToAccount(String tcNo, String password){
        sendKeysToElement(tcField,tcNo);
        sendKeysToElement(passwordField,password);
        clickElementIfExists(loginButton);
        return this;

    }
}
