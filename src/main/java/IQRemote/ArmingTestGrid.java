package IQRemote;

import Sensors.Sensors;
import io.appium.java_client.android.AndroidDriver;
import jxl.read.biff.BiffException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URL;

public class ArmingTestGrid {
    public AndroidDriver<WebElement> driver;
    DesiredCapabilities capabilities = new DesiredCapabilities();

    public ArmingTestGrid() throws IOException, BiffException {
    }

    public AndroidDriver<WebElement> getDriver() {
        return driver;
    }

    public DesiredCapabilities getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(String URL_) throws Exception {
        capabilities.setCapability("BROWSER_NAME", "Android");
        capabilities.setCapability("deviceName", "deviceName_");
        capabilities.setCapability("UDID", "UDID_");
        capabilities.setVersion("platformVersion_");
        capabilities.setCapability("applicationName", "applicationName_");
        capabilities.setCapability("appPackage", "com.qolsys");
        capabilities.setCapability("appActivity", "com.qolsys.activites.Theme3HomeActivity");
        capabilities.setCapability("PORT", "PORT_");
        capabilities.setCapability("newCommandTimeout", "10000");
        this.driver = new AndroidDriver<WebElement>(new URL(URL_), getCapabilities());
    }
    private String open = "06 00";
    private String close = "04 00";
    private String activate = "02 01";
    Sensors s = new Sensors();
    String adbPath = "/home/qolsys/android-sdk-linux/platform-tools/adb";
    public Runtime rt = Runtime.getRuntime();

    public void primary_call(String UDID_, String DLID, String State) throws IOException {
        String primary_send = " -s " + UDID_ +" shell rfinjector 02 " + DLID + " " + State;
        rt.exec(adbPath + primary_send);
        System.out.println(adbPath + " " + primary_send);
    }
    public void add_primary_call(String UDID_, int zone, int group, int sensor_dec, int sensor_type) throws IOException {
        String add_primary = " -s "+UDID_ +" shell service call qservice 50 i32 " + zone + " i32 " + group + " i32 " + sensor_dec + " i32 " + sensor_type;
        rt.exec(adbPath + add_primary);
    }
    public void logging(String UDID_) throws IOException {
        String log = " -s " +UDID_ + " logcat > /data/log.txt";
        rt.exec(adbPath + log);
    }

    @Parameters({"deviceName_", "applicationName_", "UDID_", "platformVersion_", "URL_", "PORT_"})
    @BeforeClass
    public void setUp(String deviceName_, String applicationName_, String UDID_, String platformVersion_, String URL_, String PORT_) throws Exception {
        setCapabilities(URL_);
    }
    @Test
    @Parameters({"UDID_"})
    public void Test2(String UDID_) throws InterruptedException, IOException {
        Thread.sleep(5000);

        add_primary_call(UDID_, 1, 10, 123411, 1);
        add_primary_call(UDID_, 2, 10, 123412, 1);
        add_primary_call(UDID_, 3, 10, 123413, 1);
        add_primary_call(UDID_, 4, 10, 123414, 1);
        add_primary_call(UDID_, 5, 12, 123415, 1);
        add_primary_call(UDID_, 6, 12, 123416, 1);
        add_primary_call(UDID_, 7, 12, 123417, 1);
        add_primary_call(UDID_, 8, 12, 123418, 1);
        add_primary_call(UDID_, 9, 25, 123419, 1);
        add_primary_call(UDID_, 10, 25, 123420, 1);
        add_primary_call(UDID_, 11, 15, 333441, 2);
        add_primary_call(UDID_, 12, 15, 333442, 2);
        add_primary_call(UDID_, 13, 17, 333443, 2);
        add_primary_call(UDID_, 14, 17, 333444, 2);

        for (int i = 500; i > 0; i--) {
            Thread.sleep(5000);
            System.out.println("ArmStay");
            getDriver().findElement(By.id("com.qolsys:id/t3_img_disarm")).click();
            getDriver().findElement(By.id("com.qolsys:id/img_arm_stay")).click();
            Thread.sleep(5000);
            primary_call(UDID_, "01 E2 31", open);
            Thread.sleep(2000);
            //       driver_primary.findElement(By.id("com.qolsys:id/t3_img_disarm")).click();
            //       Thread.sleep(3000);
            getDriver().findElement(By.id("com.qolsys:id/tv_keyOne")).click();
            getDriver().findElement(By.id("com.qolsys:id/tv_keyTwo")).click();
            getDriver().findElement(By.id("com.qolsys:id/tv_keyThree")).click();
            getDriver().findElement(By.id("com.qolsys:id/tv_keyFour")).click();
            primary_call(UDID_, "01 E2 31", close);
            Thread.sleep(5000);
            System.out.println("ArmAway");
            getDriver().findElement(By.id("com.qolsys:id/t3_img_disarm")).click();
            getDriver().findElement(By.id("com.qolsys:id/img_arm_away")).click();
            Thread.sleep(20000);
            primary_call(UDID_, "01 E2 71", open);
            Thread.sleep(2000);
            ///       driver_primary.findElement(By.id("com.qolsys:id/main")).click();
            getDriver().findElement(By.id("com.qolsys:id/tv_keyOne")).click();
            getDriver().findElement(By.id("com.qolsys:id/tv_keyTwo")).click();
            getDriver().findElement(By.id("com.qolsys:id/tv_keyThree")).click();
            getDriver().findElement(By.id("com.qolsys:id/tv_keyFour")).click();
            primary_call(UDID_, "01 E2 71", close);
            System.out.println("Fire Emergency");
            getDriver().findElement(By.id("com.qolsys:id/t3_emergency_icon")).click();
            Thread.sleep(3000);
            getDriver().findElement(By.id("com.qolsys:id/tv_fire")).click();
            getDriver().findElement(By.id("com.qolsys:id/tv_emg_cancel")).click();
            getDriver().findElement(By.id("com.qolsys:id/tv_keyOne")).click();
            getDriver().findElement(By.id("com.qolsys:id/tv_keyTwo")).click();
            getDriver().findElement(By.id("com.qolsys:id/tv_keyThree")).click();
            getDriver().findElement(By.id("com.qolsys:id/tv_keyFour")).click();
            Thread.sleep(300000);
            System.out.println("Open/close door window sensors");
            primary_call(UDID_, "01 E2 31", open);
            primary_call(UDID_, "01 E2 41", open);
            primary_call(UDID_, "01 E2 51", open);
            primary_call(UDID_, "01 E2 61", open);
            primary_call(UDID_, "01 E2 71", open);
            primary_call(UDID_, "01 E2 81", open);
            primary_call(UDID_, "01 E2 91", open);
            primary_call(UDID_, "01 E2 A1", open);
            primary_call(UDID_, "01 E2 B1", open);
            primary_call(UDID_, "01 E2 C1", open);
            Thread.sleep(10000);
            primary_call(UDID_, "01 E2 31", close);
            primary_call(UDID_, "01 E2 41", close);
            primary_call(UDID_, "01 E2 51", close);
            primary_call(UDID_, "01 E2 61", close);
            primary_call(UDID_, "01 E2 71", close);
            primary_call(UDID_, "01 E2 81", close);
            primary_call(UDID_, "01 E2 91", close);
            primary_call(UDID_, "01 E2 A1", close);
            primary_call(UDID_, "01 E2 B1", close);
            primary_call(UDID_, "01 E2 C1", close);
            Thread.sleep(150000);
            System.out.println("Arm Stay");
            getDriver().findElement(By.id("com.qolsys:id/t3_img_disarm")).click();
            getDriver().findElement(By.id("com.qolsys:id/img_arm_stay")).click();
            Thread.sleep(5000);
            getDriver().findElement(By.id("com.qolsys:id/t3_img_disarm")).click();
            Thread.sleep(3000);
            getDriver().findElement(By.id("com.qolsys:id/tv_keyOne")).click();
            getDriver().findElement(By.id("com.qolsys:id/tv_keyTwo")).click();
            getDriver().findElement(By.id("com.qolsys:id/tv_keyThree")).click();
            getDriver().findElement(By.id("com.qolsys:id/tv_keyFour")).click();
            Thread.sleep(10000);
            System.out.println("Activate motion sensors ");
            primary_call(UDID_, "05 16 18", activate);
            primary_call(UDID_, "05 16 28", activate);
            primary_call(UDID_, "05 16 38", activate);
            primary_call(UDID_, "05 16 48", activate);
            Thread.sleep(5000);
            System.out.println(i);
        }
    }
//        @Test
//        public void WiFi_Toggle() throws InterruptedException {
//        for (int i = 20; i>0; i--) {
//            System.out.println("Arming the system");
//            getDriver().findElement(By.id("com.qolsys:id/t3_img_disarm")).click();
//            Thread.sleep(1000);
//            getDriver().findElement(By.id("com.qolsys:id/img_arm_stay")).click();
//            Thread.sleep(60000);
//            getDriver().findElement(By.id("com.qolsys:id/t3_img_disarm")).click();
//            Thread.sleep(2000);
//            getDriver().findElement(By.id("com.qolsys:id/tv_keyOne")).click();
//            getDriver().findElement(By.id("com.qolsys:id/tv_keyTwo")).click();
//            getDriver().findElement(By.id("com.qolsys:id/tv_keyThree")).click();
//            getDriver().findElement(By.id("com.qolsys:id/tv_keyFour")).click();
//            Thread.sleep(60000);
//
//            System.out.println("Toggling the WiFi from UI");
//            getDriver().findElement(By.id("com.qolsys:id/btn_drop")).click();
//            Thread.sleep(1000);
//            getDriver().findElement(By.id("com.qolsys:id/tv_tray_settings")).click();
//            Thread.sleep(1000);
//            getDriver().findElement(By.xpath("//android.widget.TextView[@text='ADVANCED SETTINGS']")).click();
//            Thread.sleep(1000);
//            getDriver().findElement(By.id("com.qolsys:id/tv_keyTwo")).click();
//            getDriver().findElement(By.id("com.qolsys:id/tv_keyTwo")).click();
//            getDriver().findElement(By.id("com.qolsys:id/tv_keyTwo")).click();
//            getDriver().findElement(By.id("com.qolsys:id/tv_keyTwo")).click();
//            Thread.sleep(1000);
//            getDriver().findElement(By.xpath("//android.widget.TextView[@text='WI-FI']")).click();
//            Thread.sleep(3000);
//            WebElement sandbox = getDriver().findElement(By.xpath("//android.widget.TextView[@text='The_Sandbox']"));
//            WebElement connected = getDriver().findElement(By.xpath("//android.widget.TextView[@text='Connected']"));
//            try {
//                if (sandbox.isDisplayed() && connected.isDisplayed())
//                    System.out.println("SSID Sandbox is present and connected");
//            } catch (Exception e) {
//                System.out.println("SSID Sandbox is NOT present or not connected");
//            } finally {
//            }
//            getDriver().findElement(By.id("com.qolsys:id/wire_less_toggle")).click();
//            Thread.sleep(15000);
//            getDriver().findElement(By.id("com.qolsys:id/wire_less_toggle")).click();
//            Thread.sleep(15000);
//            try {
//                if (sandbox.isDisplayed() && connected.isDisplayed())
//                    System.out.println("SSID Sandbox is present and connected");
//            } catch (Exception e) {
//                System.out.println("SSID Sandbox is NOT present or not connected");
//            } finally {
//            }
//            getDriver().findElement(By.id("com.qolsys:id/ft_home_button")).click();
//            System.out.println(i);
//            Thread.sleep(300000);}
//        }

    @AfterClass
    public void tearDown() throws IOException, InterruptedException {
            getDriver().quit();
    }
}