package Framework.pageObjects;

import base.BasePage;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class HomePageObjects extends BasePage {

    public HomePageObjects(AppiumDriver driver) {super(driver);}

    By userIcon = By.id("com.turknet.oim:id/tv_user_short_cut");

    public HomePageObjects checkHomePage(){
        checkElementIfExist(userIcon);
        return this;
    }
}
