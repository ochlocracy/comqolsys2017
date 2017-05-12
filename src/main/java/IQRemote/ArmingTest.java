package IQRemote;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URL;

public class ArmingTest {

    public AndroidDriver<WebElement> driver_remote;
    public AndroidDriver<WebElement> driver_primary;

    public void setup_driver(String url_, String port_) throws Exception {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("deviceName", "IQPanel2");
        cap.setCapability("BROWSER_NAME", "Android");
        cap.setCapability("udid", "6NJUMAGLVK" );
        cap.setCapability("appPackage", "com.qolsys");
        cap.setCapability("appActivity", "com.qolsys.activites.MainActivity");
        driver_remote = new AndroidDriver<WebElement>(new URL(url_+":"+port_+"/wd/hub"), cap);
    }
    public void setup_driver1(String url_, String port_) throws Exception {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("deviceName", "IQPanel");
        cap.setCapability("BROWSER_NAME", "Android1");
        cap.setCapability("udid", "8ebdbc76" );
        cap.setCapability("appPackage", "com.qolsys");
        cap.setCapability("appActivity", "com.qolsys.activites.Theme3HomeActivity");
        driver_primary = new AndroidDriver<WebElement>(new URL(url_+":"+port_+"/wd/hub"), cap);
    }
    public void swipeFromLefttoRight() throws Exception {
        Thread.sleep(2000);
        int sx = (int) (driver_primary.manage().window().getSize().width * 0.90);
        int ex = (int) (driver_primary.manage().window().getSize().width * 0.10);
        int sy = driver_primary.manage().window().getSize().height / 2;
        driver_primary.swipe(ex, sy, sx, sy, 3000);
        Thread.sleep(2000);
    }

    @BeforeMethod
    public void capabilities_setup() throws Exception {
      //  setup_driver("http://127.0.1.1", "4723");
        setup_driver1("http://127.0.1.1", "4725");
    }

    @Test
    public void Test1 () throws InterruptedException {
        for (int i =10; i>0; i--){
        Thread.sleep(35000);
        driver_primary.findElement(By.id("com.qolsys:id/t3_img_disarm")).click();
        driver_primary.findElement(By.id("com.qolsys:id/img_arm_stay")).click();
        Thread.sleep(5000);
        driver_remote.findElement(By.id("com.qolsys:id/t3_img_disarm")).click();
        Thread.sleep(3000);
        driver_remote.findElement(By.id("com.qolsys:id/tv_keyOne")).click();
        driver_remote.findElement(By.id("com.qolsys:id/tv_keyTwo")).click();
        driver_remote.findElement(By.id("com.qolsys:id/tv_keyThree")).click();
        driver_remote.findElement(By.id("com.qolsys:id/tv_keyFour")).click();
        Thread.sleep(5000);
        driver_remote.findElement(By.id("com.qolsys:id/t3_img_disarm")).click();
        driver_remote.findElement(By.id("com.qolsys:id/img_arm_away")).click();
        Thread.sleep(15000);
        driver_primary.findElement(By.id("com.qolsys:id/main")).click();
        driver_primary.findElement(By.id("com.qolsys:id/tv_keyOne")).click();
        driver_primary.findElement(By.id("com.qolsys:id/tv_keyTwo")).click();
        driver_primary.findElement(By.id("com.qolsys:id/tv_keyThree")).click();
        driver_primary.findElement(By.id("com.qolsys:id/tv_keyFour")).click();
        Thread.sleep(5000);}
    }
    @Test
    public void Test2 () throws InterruptedException {
        for (int i= 10; i>0; i--){
        Thread.sleep(5000);
            System.out.println("ArmStay");
        driver_primary.findElement(By.id("com.qolsys:id/t3_img_disarm")).click();
        driver_primary.findElement(By.id("com.qolsys:id/img_arm_stay")).click();
        Thread.sleep(5000);
        driver_primary.findElement(By.id("com.qolsys:id/t3_img_disarm")).click();
        Thread.sleep(3000);
        driver_primary.findElement(By.id("com.qolsys:id/tv_keyOne")).click();
        driver_primary.findElement(By.id("com.qolsys:id/tv_keyTwo")).click();
        driver_primary.findElement(By.id("com.qolsys:id/tv_keyThree")).click();
        driver_primary.findElement(By.id("com.qolsys:id/tv_keyFour")).click();
        Thread.sleep(5000);
            System.out.println("ArmAway");
        driver_primary.findElement(By.id("com.qolsys:id/t3_img_disarm")).click();
        driver_primary.findElement(By.id("com.qolsys:id/img_arm_away")).click();
        Thread.sleep(20000);
        driver_primary.findElement(By.id("com.qolsys:id/main")).click();
        driver_primary.findElement(By.id("com.qolsys:id/tv_keyOne")).click();
        driver_primary.findElement(By.id("com.qolsys:id/tv_keyTwo")).click();
        driver_primary.findElement(By.id("com.qolsys:id/tv_keyThree")).click();
        driver_primary.findElement(By.id("com.qolsys:id/tv_keyFour")).click();
        Thread.sleep(5000);
            System.out.println("Fire Emergency");
        driver_primary.findElement(By.id("com.qolsys:id/t3_emergency_icon")).click();
        Thread.sleep(3000);
        driver_primary.findElement(By.id("com.qolsys:id/tv_fire")).click();
        driver_primary.findElement(By.id("com.qolsys:id/tv_emg_cancel")).click();
        driver_primary.findElement(By.id("com.qolsys:id/tv_keyOne")).click();
        driver_primary.findElement(By.id("com.qolsys:id/tv_keyTwo")).click();
        driver_primary.findElement(By.id("com.qolsys:id/tv_keyThree")).click();
        driver_primary.findElement(By.id("com.qolsys:id/tv_keyFour")).click();
        Thread.sleep(5000);
            System.out.println(i);}
    }

    @AfterMethod
    public void tearDown () throws IOException, InterruptedException {
   //     driver_remote.quit();
        driver_primary.quit();
    }
}
