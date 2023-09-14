package base;

import Framework.pageObjects.HomePageObjects;
import Framework.pageObjects.LoginPageObjects;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import utils.logs.Log;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    public AppiumDriver driver;

    //PAGES
    public HomePageObjects homePageObjects;
    public LoginPageObjects loginPageObjects;


    public Properties properties;
    FileInputStream fileInputStream;
    public DesiredCapabilities capabilities;


    public AppiumDriver getDriver(){return driver;}
    @Parameters({"platformName","udid","appPackage","appActivity","implicityWait"})
    @BeforeClass(alwaysRun = true)
    public void setup(String platformName,String udid, String appPackage, String appActivity, String implicityWait) throws Exception{
        capabilities = new DesiredCapabilities();

        if(platformName.equals("Android")){
            capabilities.setCapability("platformName","Android");
            capabilities.setCapability("udid",udid);
            capabilities.setCapability("appPackage",appPackage);
            capabilities.setCapability("appActivity",appActivity);
        }
        else if(platformName.equals("IOS")){
            capabilities.setCapability("platformName","IOS");
            capabilities.setCapability("udid",udid);
            capabilities.setCapability("appPackage",appPackage);
            capabilities.setCapability("appActivity",appActivity);
        }
        try {
            URL url = new URL("http://0.0.0.0:4723/wd/hub");
            Log.info("Test başlıyor!");
            driver = new AppiumDriver<MobileElement>(url,capabilities);
            //driver = new AndroidDriver<MobileElement>(url,capabilities);
            //driver = new IOSDriver<MobileElement>(url,capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        int impWait = Integer.parseInt(implicityWait);
        driver.manage().timeouts().implicitlyWait(impWait, TimeUnit.SECONDS);
        //return getDriver();
    }

    @BeforeMethod(alwaysRun = true)
    public void methodSetup() throws IOException {
        getReadPropFile();
        homePageObjects = new HomePageObjects(driver);
        loginPageObjects = new LoginPageObjects(driver);
    }

    public void getReadPropFile() throws IOException {
        properties = new Properties();
        fileInputStream = new FileInputStream("src/test/resources/config.properties");
        properties.load(fileInputStream);
    }

    @AfterClass(alwaysRun = true)
    public void teardown() {
        Log.info("Test bitti.");
        driver.quit();}
}
