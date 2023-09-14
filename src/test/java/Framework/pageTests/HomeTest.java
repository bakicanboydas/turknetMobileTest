package Framework.pageTests;

import base.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static utils.extentReport.ExtentTestManager.startTest;

import java.lang.reflect.Method;

public class HomeTest extends BaseTest {
    @BeforeMethod
    public void loginToAccount(Method method){
        startTest(method.getName(),"Ana sayfanın açılması ve kontrolü");
        loginPageObjects.loginToAccount(properties.getProperty("tcNo"),properties.getProperty("password"));


    }

    @Test(priority = 0, description = "Check home page")
    public void checkHomePage(){
        homePageObjects.checkHomePage();

    }


}
