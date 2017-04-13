package GridTesting;

import Panel.*;
import Sensors.Sensors;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import jxl.read.biff.BiffException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Test2 {

    AppiumDriver driver = null;
    public String adbPath = "/home/qolsys/android-sdk-linux/platform-tools/adb";
    Sensors MySensors = new Sensors();
    String page_name = "Smoke Test Door-Window Sensors";
    Logger logger = Logger.getLogger(page_name);


    private int Open = 1;
    private int Close = 0;

    private int Normal_Entry_Delay = 13;

    public Test2() throws IOException, BiffException {
    }

    @Parameters({"deviceName_", "applicationName_", "UDID_", "platformVersion_", "URL_", "PORT_" })
    @BeforeTest(alwaysRun=true)

    public void setup(String deviceName_, String applicationName_, String UDID_, String platformVersion_, String URL_, String PORT_ ) throws MalformedURLException {

        DesiredCapabilities caps = new DesiredCapabilities();

        caps.setCapability("BROWSER_NAME", "Android");
        caps.setCapability("deviceName", deviceName_ );
        caps.setCapability("UDID", UDID_);
        caps.setVersion(platformVersion_);
        caps.setCapability("applicationName", applicationName_);
        caps.setCapability("appPackage", "com.qolsys");
        caps.setCapability("appActivity", "com.qolsys.activites.Theme3HomeActivity");
        caps.setCapability("PORT", PORT_);
        driver = new AndroidDriver<WebElement>(new URL(URL_), caps);
    }

    @Parameters({"UDID_"})
    @Test(threadPoolSize = 3)
    public void Test2(String UDID_) throws Exception {

        logger.info("Current software version: " + Software_Version());
        TimeUnit.SECONDS.sleep(1);
        driver.findElement(By.id("com.qolsys:id/t3_home_tab2")).click();
        TimeUnit.SECONDS.sleep(1);
        MySensors.read_sensors_from_csv();
        logger.info("Adding sensors...");
        MySensors.addAllSensors1(UDID_);
        TimeUnit.SECONDS.sleep(1);
        driver.findElement(By.id("com.qolsys:id/t3_home_tab1")).click();
        TimeUnit.SECONDS.sleep(1);

        logger.info("Disarm mode tripping sensors group 10, 12, 13, 14, 16, 25 -> Expected result= system stays in Disarm mode");
        MySensors.sendPacket_allSensors_selectedGroup(MySensors.door_window_zones, 10, Open);
        MySensors.sendPacket_allSensors_selectedGroup(MySensors.door_window_zones, 12, Open);
        MySensors.sendPacket_allSensors_selectedGroup(MySensors.door_window_zones, 13, Open);
        MySensors.sendPacket_allSensors_selectedGroup(MySensors.door_window_zones, 14, Open);
        MySensors.sendPacket_allSensors_selectedGroup(MySensors.door_window_zones, 16, Open);
        MySensors.sendPacket_allSensors_selectedGroup(MySensors.door_window_zones, 25, Open);
        TimeUnit.SECONDS.sleep(5);
        WebElement Door4 = driver.findElementByXPath("//android.widget.TextView[@text='Door/Window 4']");
        verify_sensor_is_displayed(Door4);
        WebElement Door5 = driver.findElementByXPath("//android.widget.TextView[@text='Door/Window 5']");
        verify_sensor_is_displayed(Door5);
        WebElement Door6 = driver.findElementByXPath("//android.widget.TextView[@text='Door/Window 6']");
        verify_sensor_is_displayed(Door6);
        WebElement Door7 = driver.findElementByXPath("//android.widget.TextView[@text='Door/Window 7']");
        verify_sensor_is_displayed(Door7);
        WebElement Door8 = driver.findElementByXPath("//android.widget.TextView[@text='Door/Window 8']");
        verify_sensor_is_displayed(Door8);
        WebElement Door9 = driver.findElementByXPath("//android.widget.TextView[@text='Door/Window 9']");
        verify_sensor_is_displayed(Door9);
        verify_disarm();
        MySensors.sendPacket_allSensors_selectedGroup(MySensors.door_window_zones, 10, Close);
        MySensors.sendPacket_allSensors_selectedGroup(MySensors.door_window_zones, 12, Close);
        MySensors.sendPacket_allSensors_selectedGroup(MySensors.door_window_zones, 13, Close);
        MySensors.sendPacket_allSensors_selectedGroup(MySensors.door_window_zones, 14, Close);
        MySensors.sendPacket_allSensors_selectedGroup(MySensors.door_window_zones, 16, Close);
        MySensors.sendPacket_allSensors_selectedGroup(MySensors.door_window_zones, 25, Close);
        TimeUnit.SECONDS.sleep(5);

        logger.info("********************************************************");
        logger.info("Disarm mode tripping sensors group 8 -> Expected result= Instant Alarm");
        MySensors.sendPacket_allSensors_selectedGroup(MySensors.door_window_zones, 8, Open);
        TimeUnit.SECONDS.sleep(3);
        WebElement Door2 = driver.findElementByXPath("//android.widget.TextView[@text='Door/Window 2']");
        verify_sensor_is_displayed(Door2);
        verify_status_open();
        verify_in_alarm();
        MySensors.sendPacket_allSensors_selectedGroup(MySensors.door_window_zones, 8, Close);
        enter_default_user_code();
        TimeUnit.SECONDS.sleep(5);

        logger.info("********************************************************");
        logger.info("Disarm mode tripping sensors group 9 -> Expected result= 30 sec delay -> Alarm");
        MySensors.sendPacket_allSensors_selectedGroup(MySensors.door_window_zones, 9, Open);
        TimeUnit.SECONDS.sleep(Normal_Entry_Delay);
        WebElement Door3 = driver.findElementByXPath("//android.widget.TextView[@text='Door/Window 3']");
        verify_sensor_is_displayed(Door3);
        verify_status_open();
        verify_in_alarm();
        MySensors.sendPacket_allSensors_selectedGroup(MySensors.door_window_zones, 9, Close);
        enter_default_user_code();
        TimeUnit.SECONDS.sleep(5);

        logger.info("**********************TAMPER**********************");
        logger.info("Disarm mode tamper sensors group 10, 12, 13, 14, 16, 25 -> Expected result = Disarm");
        MySensors.sendTamper_allSensors_selectedGroup(MySensors.door_window_zones, 10);
        MySensors.sendTamper_allSensors_selectedGroup(MySensors.door_window_zones, 12);
        MySensors.sendTamper_allSensors_selectedGroup(MySensors.door_window_zones, 13);
        MySensors.sendTamper_allSensors_selectedGroup(MySensors.door_window_zones, 14);
        MySensors.sendTamper_allSensors_selectedGroup(MySensors.door_window_zones, 16);
        MySensors.sendTamper_allSensors_selectedGroup(MySensors.door_window_zones, 25);
        TimeUnit.SECONDS.sleep(3);
        verify_sensor_is_tampered(Door4);
        verify_sensor_is_tampered(Door5);
        verify_sensor_is_tampered(Door6);
        verify_sensor_is_tampered(Door7);
        verify_sensor_is_tampered(Door8);
        verify_sensor_is_tampered(Door9);
        verify_disarm();
        MySensors.sendPacket_allSensors_selectedGroup(MySensors.door_window_zones, 10, Close);
        MySensors.sendPacket_allSensors_selectedGroup(MySensors.door_window_zones, 12, Close);
        MySensors.sendPacket_allSensors_selectedGroup(MySensors.door_window_zones, 13, Close);
        MySensors.sendPacket_allSensors_selectedGroup(MySensors.door_window_zones, 14, Close);
        MySensors.sendPacket_allSensors_selectedGroup(MySensors.door_window_zones, 16, Close);
        MySensors.sendPacket_allSensors_selectedGroup(MySensors.door_window_zones, 25, Close);

        logger.info("********************************************************");
        logger.info("Disarm mode tamper sensors group 8 -> Expected result -> Instant Alarm");
        MySensors.sendTamper_allSensors_selectedGroup(MySensors.door_window_zones, 8);
        TimeUnit.SECONDS.sleep(3);
        verify_sensor_is_tampered(Door2);
        verify_status_tampered();
        verify_in_alarm();
        MySensors.sendPacket_allSensors_selectedGroup(MySensors.door_window_zones, 8, Close);
        enter_default_user_code();
        TimeUnit.SECONDS.sleep(5);

        logger.info("********************************************************");
        logger.info("Disarm mode tamper sensors group 9 -> Expected result -> Instant Alarm");
        MySensors.sendTamper_allSensors_selectedGroup(MySensors.door_window_zones, 9);
        TimeUnit.SECONDS.sleep(3);
        verify_sensor_is_tampered(Door3);
        verify_status_tampered();
        verify_in_alarm();
        MySensors.sendPacket_allSensors_selectedGroup(MySensors.door_window_zones, 9, Close);
        enter_default_user_code();
        TimeUnit.SECONDS.sleep(3);
        driver.findElement(By.id("com.qolsys:id/t3_home_tab2")).click();
        MySensors.deleteAllSensors1(UDID_);

    }
    @AfterMethod
    public void tearDown () throws IOException, InterruptedException {
        driver.quit();
    }
    public String Software_Version () throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        Slide_Menu menu = PageFactory.initElements(driver, Slide_Menu.class);
        Settings_Page settings = PageFactory.initElements(driver, Settings_Page.class);
        Advanced_Settings_Page adv = PageFactory.initElements(driver, Advanced_Settings_Page.class);
        About_page about = PageFactory.initElements(driver, About_page.class);
        menu.Slide_menu_open.click();
        menu.Settings.click();
        settings.ADVANCED_SETTINGS.click();
        settings.Two.click();
        settings.Two.click();
        settings.Two.click();
        settings.Two.click();
        adv.ABOUT.click();
        about.Software.click();
        WebElement soft_version = driver.findElement(By.id("com.qolsys:id/summary"));
        String current_version = soft_version.getText();
        driver.findElementById("com.qolsys:id/ft_home_button").click();
        return current_version;
    }

    public void verify_disarm() throws Exception {
        Home_Page home_page = PageFactory.initElements(driver, Home_Page.class);
        if (home_page.Disarmed_text.getText().equals("DISARMED")) {
            logger.info("Pass: System is DISARMED");
        } else {
   //         take_screenshot();
            logger.info("Failed: System is not DISARMED " + home_page.Disarmed_text.getText());
        }
    }
    public void verify_sensor_is_displayed(WebElement sensor_name) throws Exception {
        if (sensor_name.isDisplayed()) {
            logger.info(sensor_name.getText() +" is successfully opened/activated");
        } else {
   //         take_screenshot();
            logger.info(sensor_name +" is NOT opened/activated");}
    }
    public void verify_status_open() throws Exception {
        Home_Page home_page = PageFactory.initElements(driver, Home_Page.class);
        if (home_page.Red_banner_sensor_status.getText().equals("Open")) {
            logger.info("Pass: Correct status is Open");
        }else {
     //       take_screenshot();
            logger.info("Failed: Incorrect status: " + home_page.Red_banner_sensor_status.getText());}
    }
    public void verify_in_alarm() throws Exception {
        Home_Page home_page = PageFactory.initElements(driver, Home_Page.class);
        if (home_page.ALARM.isDisplayed()) {
            logger.info("Pass: System is in ALARM");
        } else {
     //       take_screenshot();
            logger.info("Failed: System is NOT in ALARM");}
    }
    public void enter_default_user_code (){
        Home_Page home_page = PageFactory.initElements(driver, Home_Page.class);
        home_page.One.click();
        home_page.Two.click();
        home_page.Three.click();
        home_page.Four.click();
    }
    public void verify_sensor_is_tampered(WebElement sensor_name) throws Exception {
        if (sensor_name.isDisplayed()) {
            logger.info(sensor_name.getText() + " is successfully tampered");
        } else {
    //        take_screenshot();
            logger.info(sensor_name +" is NOT tampered");}
    }
    public void verify_status_tampered() throws Exception {
        Home_Page home_page = PageFactory.initElements(driver, Home_Page.class);
        if (home_page.Red_banner_sensor_status.getText().equals("Tampered")) {
            logger.info("Pass: Correct status is Tampered");
        }else {
      //      take_screenshot();
            logger.info("Failed: Incorrect status: " + home_page.Red_banner_sensor_status.getText());}
    }

}
