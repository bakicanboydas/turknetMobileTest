package base;

import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.extentReport.ExtentTestManager;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static java.time.Duration.ofMillis;


public class BasePage {
    AppiumDriver driver;
    WebDriverWait wait;
    Actions action;

    public BasePage(AppiumDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
        this.action = new Actions(driver);

    }

    public WebElement findElement(By key){
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(key));
        return element;
    }


    public void clickToElement(By key){
        findElement(key).click();
        waitForSecond(2);
    }
    public void clickToElement(WebElement key){
        key.click();
    }

    public void sendKeysToElement(By key,String text){
        findElement(key).sendKeys(text);
    }

    public boolean checkElementIfVisibleTrue(By key){
        wait.until(ExpectedConditions.presenceOfElementLocated(key));
        return true;
    }

    public void checkElementIfExist(By key){
        if(findElement(key) != null){
            Assert.assertTrue(true,"Element is found");
        }
        else if(findElement(key) == null){
            Assert.assertFalse(true,"Element cannot find");
        }
    }

    public void scroolDownUntilFindTheElement(By key){

        int maxRetryCount = 10;
        while (maxRetryCount > 0) {
            try {
                if (checkElementIfVisibleTrue(key)) {
                    break;
                }
            }catch (Exception e){
                maxRetryCount--;
                new TouchAction(driver)
                    .press(PointOption.point(506, 1829))
                    .waitAction(waitOptions(ofMillis(1000)))
                    .moveTo(PointOption.point(506, 1285))
                    .release()
                    .perform();
                if (maxRetryCount == 0) {
                    Assert.assertFalse(false,"Element cannot find");
                    break;
                }
            }
        }

    }

    public void scroolDownUntilFindTheElement(WebElement key){

        int maxRetryCount = 10;
        while (maxRetryCount > 0) {
            try {
                if (key.isEnabled()) {
                    break;
                }
            }catch (Exception e){
                maxRetryCount--;
                new TouchAction(driver)
                    .press(PointOption.point(506, 1829))
                    .waitAction(waitOptions(ofMillis(1000)))
                    .moveTo(PointOption.point(506, 1285))
                    .release()
                    .perform();
                if (maxRetryCount == 0) {
                    Assert.assertFalse(false,"Element cannot find");
                    break;
                }
            }
        }

    }



    public void swipeToLeftNTime(Integer swipeCount){

        int maxRetryCount = swipeCount;
        while (maxRetryCount > 0) {
            maxRetryCount--;
            new TouchAction(driver)
                .press(PointOption.point(850, 1100))
                .waitAction(waitOptions(ofMillis(1000)))
                .moveTo(PointOption.point(330, 1100))
                .release()
                .perform();
            if (maxRetryCount == 0) {
                System.out.println("Swipe is over.");
                break;
            }

        }

    }

    public void waitForSecond(int seconds) {
        try {
            Thread.sleep(seconds*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clickElementIfExists(By key) {
        try{
            if(checkElementIfVisibleTrue(key)){
                clickToElement(key);
            }
        }catch (Exception e){
            System.out.println("Element bulunamadı");
        }
    }

    public void deviceGoBack(){
        driver.navigate().back();
    }

    public void emptyTab(){
        action.click();
    }

    protected String getInfoMessage(String message) {
        ExtentTestManager.getTest().log(Status.INFO, message);
        return message;
    }
}
