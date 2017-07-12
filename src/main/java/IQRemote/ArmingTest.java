package IQRemote;

import Panel.Setup;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import jxl.read.biff.BiffException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import Sensors.Sensors;

public class ArmingTest {
    private String open = "06 00";
    private String close = "04 00";
    private String activate = "02 01";

    Sensors s = new Sensors();
    Setup set = new Setup();

    public AndroidDriver<WebElement> driver_remote;
    public AndroidDriver<WebElement> driver_primary;

    String timeStamp = new SimpleDateFormat("yyyy_MM_dd_HH:mm:ss").format(Calendar.getInstance().getTime());

    public ArmingTest() throws IOException, BiffException {}

    public void setup_driver(String url_, String port_) throws Exception {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("deviceName", "IQPanel2");
        cap.setCapability("BROWSER_NAME", "Android");
        cap.setCapability("udid", "6NJUM1CCZG" );
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
        cap.setCapability("newCommandTimeout", "1000");
        driver_primary = new AndroidDriver<WebElement>(new URL(url_+":"+port_+"/wd/hub"), cap);
    }
    public void swipeFromLefttoRight_primary() throws Exception {
        Thread.sleep(2000);
        int sx = (int) (driver_primary.manage().window().getSize().width * 0.90);
        int ex = (int) (driver_primary.manage().window().getSize().width * 0.10);
        int sy = driver_primary.manage().window().getSize().height / 2;
        driver_primary.swipe(ex, sy, sx, sy, 3000);
        Thread.sleep(2000);
    }
    public void swipeFromLefttoRight_remote() throws Exception {
        Thread.sleep(2000);
        int sx = (int) (driver_remote.manage().window().getSize().width * 0.90);
        int ex = (int) (driver_remote.manage().window().getSize().width * 0.10);
        int sy = driver_remote.manage().window().getSize().height / 2;
        driver_remote.swipe(ex, sy, sx, sy, 3000);
        Thread.sleep(2000);
    }
    public Runtime rt = Runtime.getRuntime();

    String adbPath = "/home/nchortek/Android/Sdk/platform-tools/adb";

    public void add_primary_call(int zone, int group, int sensor_dec, int sensor_type) throws IOException {
        String add_primary = " -s 8ebdbc76 shell service call qservice 50 i32 " + zone + " i32 " + group + " i32 " + sensor_dec + " i32 " + sensor_type;
        rt.exec(adbPath + add_primary);
    }
    public void primary_call(String DLID, String State) throws IOException {
        String primary_send =" -s 8ebdbc76 shell rfinjector 02 "+DLID+" "+State;
        rt.exec(adbPath + primary_send);
        System.out.println(adbPath + " " + primary_send);
       }
    public void tap (int x, int y){
        TouchAction touch = new TouchAction (driver_primary);
        touch.tap(x,y).perform();
    }
    public void tap_remote (int x, int y){
        TouchAction touch = new TouchAction (driver_remote);
        touch.tap(x,y).perform();
    }
    @BeforeMethod
    public void capabilities_setup() throws Exception {
        setup_driver("http://127.0.1.1", "4723");
  //      setup_driver1("http://127.0.1.1", "4725");
    }

    @Test
    public void Test1 () throws Exception {
        for (int i =10; i>0; i--) {
            Thread.sleep(10000);
            swipeFromLefttoRight_primary();
            Thread.sleep(2000);
            swipeFromLefttoRight_primary();
            Thread.sleep(4000);
            System.out.println("Click Mode button");
            tap (1055, 275);
 //           driver_primary.findElement(By.id("com.qolsys:id/btThermoMode")).click();
            Thread.sleep(4000);
            System.out.println("Select Heat mode, OFF mode");
            driver_primary.findElement(By.id("com.qolsys:id/heat")).click();
            Thread.sleep(10000);
            tap (1055, 275);
//            driver_primary.findElement(By.id("com.qolsys:id/btThermoMode")).click();
            Thread.sleep(3000);
            driver_primary.findElement(By.id("com.qolsys:id/off")).click();
            Thread.sleep(4000);
//        swipeFromLefttoRight_remote();
//        Thread.sleep(3000);
            //       driver_remote.findElement(By.id("com.qolsys:id/btThermoMode")).click();
            //       Thread.sleep(2000);
//        driver_remote.findElement(By.id("com.qolsys:id/off")).click();
//        Thread.sleep(5000);
            System.out.println("Open/close the door");
            swipeFromLefttoRight_primary();
            Thread.sleep(3000);
            driver_primary.findElement(By.id("com.qolsys:id/doorStatusbutton")).click();
            Thread.sleep(10000);
//        swipeFromLefttoRight_remote();
//        Thread.sleep(3000);
//        driver_remote.findElement(By.id("com.qolsys:id/doorStatusbutton")).click();
//        Thread.sleep(10000);
            swipeFromLefttoRight_primary();
            Thread.sleep(2000);
            driver_primary.findElement(By.id("com.qolsys:id/statusButton")).click();
            Thread.sleep(3000);
//        swipeFromLefttoRight_remote();
//        Thread.sleep(3000);
//        driver_remote.findElement(By.id("com.qolsys:id/statusButton")).click();
//        Thread.sleep(3000);
            swipeFromLefttoRight_primary();
            Thread.sleep(2000);
            driver_primary.findElement(By.id("com.qolsys:id/t3_img_disarm")).click();
            driver_primary.findElement(By.id("com.qolsys:id/img_arm_stay")).click();
            Thread.sleep(10000);
            driver_primary.findElement(By.id("com.qolsys:id/t3_img_disarm")).click();
            Thread.sleep(3000);
            driver_primary.findElement(By.id("com.qolsys:id/tv_keyOne")).click();
            driver_primary.findElement(By.id("com.qolsys:id/tv_keyTwo")).click();
            driver_primary.findElement(By.id("com.qolsys:id/tv_keyThree")).click();
            driver_primary.findElement(By.id("com.qolsys:id/tv_keyFour")).click();
            Thread.sleep(5000);
            System.out.println(i);
        }
//        driver_remote.findElement(By.id("com.qolsys:id/t3_img_disarm")).click();
//        Thread.sleep(3000);
//
//        driver_remote.findElement(By.id("com.qolsys:id/tv_keyOne")).click();
//        driver_remote.findElement(By.id("com.qolsys:id/tv_keyTwo")).click();
//        driver_remote.findElement(By.id("com.qolsys:id/tv_keyThree")).click();
//        driver_remote.findElement(By.id("com.qolsys:id/tv_keyFour")).click();
//        Thread.sleep(5000);
//        driver_remote.findElement(By.id("com.qolsys:id/t3_img_disarm")).click();
//        driver_remote.findElement(By.id("com.qolsys:id/img_arm_away")).click();
//        Thread.sleep(15000);
//        driver_primary.findElement(By.id("com.qolsys:id/main")).click();
//        driver_primary.findElement(By.id("com.qolsys:id/tv_keyOne")).click();
//        driver_primary.findElement(By.id("com.qolsys:id/tv_keyTwo")).click();
//        driver_primary.findElement(By.id("com.qolsys:id/tv_keyThree")).click();
//        driver_primary.findElement(By.id("com.qolsys:id/tv_keyFour")).click();
//        Thread.sleep(5000);}
    }
    @Test
    public void Test1_Remote () throws Exception {
       for (int i = 20; i > 0; i--) {
            Thread.sleep(35000);
            swipeFromLefttoRight_remote();
            Thread.sleep(4000);
            System.out.println("Click Mode button");
            tap_remote(860, 207 );
            Thread.sleep(4000);
            System.out.println("Select Heat mode, OFF mode");
            driver_remote.findElement(By.id("com.qolsys:id/heat")).click();
            Thread.sleep(10000);
        System.out.println(driver_remote.findElement(By.id("com.qolsys:id/uiTargTemp")).getText());
        driver_remote.findElement(By.id("com.qolsys:id/btTempUp")).click();
        Thread.sleep(2000);
        driver_remote.findElement(By.id("com.qolsys:id/btTempUp")).click();
        Thread.sleep(10000);
        driver_remote.findElement(By.id("com.qolsys:id/btTempDown")).click();
        Thread.sleep(2000);
        driver_remote.findElement(By.id("com.qolsys:id/btTempDown")).click();
        Thread.sleep(10000);
        System.out.println(driver_remote.findElement(By.id("com.qolsys:id/uiTargTemp")).getText());
        Thread.sleep(4000);
            tap_remote(860, 207 );
            Thread.sleep(3000);
            driver_remote.findElement(By.id("com.qolsys:id/off")).click();
            Thread.sleep(4000);
            System.out.println("Open/close the door");
            swipeFromLefttoRight_remote();
            Thread.sleep(3000);
            driver_remote.findElement(By.id("com.qolsys:id/doorStatusbutton")).click();
            Thread.sleep(10000);
            System.out.println("Turn On/Off the light");
            swipeFromLefttoRight_remote();
            Thread.sleep(3000);
            driver_remote.findElement(By.id("com.qolsys:id/statusButton")).click();
            Thread.sleep(3000);
//        swipeFromLefttoRight_remote();
//        Thread.sleep(3000);
//        driver_remote.findElement(By.id("com.qolsys:id/statusButton")).click();
//        Thread.sleep(3000);
            swipeFromLefttoRight_remote();
            Thread.sleep(2000);
            System.out.println("Arm Stay");
            driver_remote.findElement(By.id("com.qolsys:id/t3_img_disarm")).click();
            driver_remote.findElement(By.id("com.qolsys:id/img_arm_stay")).click();
            Thread.sleep(10000);
            System.out.println("Disarm");
            driver_remote.findElement(By.id("com.qolsys:id/t3_img_disarm")).click();
            Thread.sleep(3000);
            driver_remote.findElement(By.id("com.qolsys:id/tv_keyOne")).click();
            driver_remote.findElement(By.id("com.qolsys:id/tv_keyTwo")).click();
            driver_remote.findElement(By.id("com.qolsys:id/tv_keyThree")).click();
            driver_remote.findElement(By.id("com.qolsys:id/tv_keyFour")).click();
            Thread.sleep(5000);
           System.out.println(i);
        }
    }
    @Test
    public void Test2 () throws InterruptedException, IOException {
        Thread.sleep(5000);
        System.out.println(timeStamp);
       s.add_primary_call(1,10, 123411, 1);
       s.add_primary_call(2,10, 123412, 1);
       s.add_primary_call(3,10, 123413, 1);
       s.add_primary_call(4,10, 123414, 1);
       s.add_primary_call(5,12, 123415, 1);
       s.add_primary_call(6,12, 123416, 1);
       s.add_primary_call(7,12, 123417, 1);
       s.add_primary_call(8,12, 123418, 1);
       s.add_primary_call(9,25, 123419, 1);
       s.add_primary_call(10,25, 123420, 1);
       s.add_primary_call(11,15, 333441, 2);
       s.add_primary_call(12,15, 333442, 2);
       s.add_primary_call(13,17, 333443, 2);
       s.add_primary_call(14,17, 333444, 2);
       s.add_primary_call(15,26, 787871, 5);
       s.add_primary_call(16,26, 787872, 5);
       s.add_primary_call(17,26, 787873, 5);
       s.add_primary_call(18,26, 787874, 5);
       s.add_primary_call(19,34, 555544, 6);
       s.add_primary_call(20,34, 555545, 6);

        for (int i= 500; i>0; i--){
        Thread.sleep(5000);
        System.out.println("ArmStay");
        driver_primary.findElement(By.id("com.qolsys:id/t3_img_disarm")).click();
        driver_primary.findElement(By.id("com.qolsys:id/img_arm_stay")).click();
        Thread.sleep(5000);
        primary_call("01 E2 31", open);
            Thread.sleep(2000);
 //       driver_primary.findElement(By.id("com.qolsys:id/t3_img_disarm")).click();
 //       Thread.sleep(3000);
        driver_primary.findElement(By.id("com.qolsys:id/tv_keyOne")).click();
        driver_primary.findElement(By.id("com.qolsys:id/tv_keyTwo")).click();
        driver_primary.findElement(By.id("com.qolsys:id/tv_keyThree")).click();
        driver_primary.findElement(By.id("com.qolsys:id/tv_keyFour")).click();
            primary_call("01 E2 31", close);
        Thread.sleep(5000);
            System.out.println("ArmAway");
        driver_primary.findElement(By.id("com.qolsys:id/t3_img_disarm")).click();
        driver_primary.findElement(By.id("com.qolsys:id/img_arm_away")).click();
        Thread.sleep(20000);
        primary_call("01 E2 71", open);
        Thread.sleep(2000);
 ///       driver_primary.findElement(By.id("com.qolsys:id/main")).click();
        driver_primary.findElement(By.id("com.qolsys:id/tv_keyOne")).click();
        driver_primary.findElement(By.id("com.qolsys:id/tv_keyTwo")).click();
        driver_primary.findElement(By.id("com.qolsys:id/tv_keyThree")).click();
        driver_primary.findElement(By.id("com.qolsys:id/tv_keyFour")).click();
        primary_call("01 E2 71", close);
        System.out.println("Fire Emergency");
        driver_primary.findElement(By.id("com.qolsys:id/t3_emergency_icon")).click();
        Thread.sleep(3000);
        driver_primary.findElement(By.id("com.qolsys:id/tv_fire")).click();
        driver_primary.findElement(By.id("com.qolsys:id/tv_emg_cancel")).click();
        driver_primary.findElement(By.id("com.qolsys:id/tv_keyOne")).click();
        driver_primary.findElement(By.id("com.qolsys:id/tv_keyTwo")).click();
        driver_primary.findElement(By.id("com.qolsys:id/tv_keyThree")).click();
        driver_primary.findElement(By.id("com.qolsys:id/tv_keyFour")).click();
        Thread.sleep(300000);
        System.out.println("Open/close door window sensors");
        primary_call("01 E2 31", open);
        primary_call("01 E2 41", open);
        primary_call("01 E2 51", open);
        primary_call("01 E2 61", open);
        primary_call("01 E2 71", open);
        primary_call("01 E2 81", open);
        primary_call("01 E2 91", open);
        primary_call("01 E2 A1", open);
        primary_call("01 E2 B1", open);
        primary_call("01 E2 C1", open);
        Thread.sleep(10000);
        primary_call("01 E2 31", close);
        primary_call("01 E2 41", close);
        primary_call("01 E2 51", close);
        primary_call("01 E2 61", close);
        primary_call("01 E2 71", close);
        primary_call("01 E2 81", close);
        primary_call("01 E2 91", close);
        primary_call("01 E2 A1", close);
        primary_call("01 E2 B1", close);
        primary_call("01 E2 C1", close);
        Thread.sleep(150000);
        System.out.println("Arm Stay");
        driver_primary.findElement(By.id("com.qolsys:id/t3_img_disarm")).click();
        driver_primary.findElement(By.id("com.qolsys:id/img_arm_stay")).click();
        Thread.sleep(5000);
        driver_primary.findElement(By.id("com.qolsys:id/t3_img_disarm")).click();
        Thread.sleep(3000);
        driver_primary.findElement(By.id("com.qolsys:id/tv_keyOne")).click();
        driver_primary.findElement(By.id("com.qolsys:id/tv_keyTwo")).click();
        driver_primary.findElement(By.id("com.qolsys:id/tv_keyThree")).click();
        driver_primary.findElement(By.id("com.qolsys:id/tv_keyFour")).click();
        Thread.sleep(10000);
        System.out.println("Activate motion sensors ");
        primary_call("05 16 18", activate);
        primary_call("05 16 28", activate);
        primary_call("05 16 38", activate);
        primary_call("05 16 48", activate);
        Thread.sleep(5000);
        System.out.println(i);
        }
    }

    @Test
    public  void test3 () throws Exception {
        swipeFromLefttoRight_primary();
        swipeFromLefttoRight_primary();
        swipeFromLefttoRight_primary();
        swipeFromLefttoRight_primary();
        List<WebElement> li = driver_primary.findElements(By.id("com.qolsys:id/lightSelect"));
        li.get(0).click();
        li.get(1).click();
 //       driver_primary.findElement(By.id("com.qolsys:id/uiTvchkbox")).click();

    }

    @AfterMethod
    public void tearDown () throws IOException, InterruptedException {
        driver_remote.quit();                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   driver_remote.quit();
  //      driver_primary.quit();
    }
}