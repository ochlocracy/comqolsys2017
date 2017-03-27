package Panel;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import java.net.URL;
import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Setup {

    public String adbPath = "/home/qolsys/android-sdk-linux/platform-tools/adb";
    public File appDir = new File("/home/qolsys/Desktop/comqolsysPOM/src/main/java");
    public String udid_ = "8ebdbc76";

    public AndroidDriver<WebElement> driver;

   public Log log = new Log();
   public Logger logger = Logger.getLogger("String");

    public void setup_driver(String udid_, String url_, String port_) throws Exception {

        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("deviceName", "IQPanel2");
        cap.setCapability("BROWSER_NAME", "Android");
        cap.setCapability("udid", udid_);
        cap.setCapability("appPackage", "com.qolsys");
        cap.setCapability("appActivity", "com.qolsys.activites.Theme3HomeActivity");
        driver = new AndroidDriver<WebElement>(new URL(url_+":"+port_+"/wd/hub"), cap);
    }

    public void setup_logger(String test_case_name) throws Exception {
        PropertyConfigurator.configure(new File(appDir, "log4j.properties").getAbsolutePath());
        log.clearLog();
        log.startTestCase(" " +test_case_name+ " ");
    }

    public void swipeFromLefttoRight() throws Exception {
        Thread.sleep(2000);
        int sx = (int) (driver.manage().window().getSize().width * 0.90);
        int ex = (int) (driver.manage().window().getSize().width * 0.10);
        int sy = driver.manage().window().getSize().height / 2;
        driver.swipe(ex, sy, sx, sy, 3000);
        Thread.sleep(2000);
    }
    public void swipeFromRighttoLeft() throws Exception {
        Thread.sleep(2000);
        int sx = (int) (driver.manage().window().getSize().width * 0.90);
        int ex = (int) (driver.manage().window().getSize().width * 0.10);
        int sy = driver.manage().window().getSize().height / 2;
        driver.swipe(sx, sy, ex, sy, 3000);
        Thread.sleep(2000);
    }

    public WebElement element_verification(WebElement element, String element_name) throws  Exception{
        try {
            if (element.isDisplayed()) {
                logger.info("Pass: " + element_name + " is present, value = " + element.getText());
            }
        } catch (Exception e){
            Log.error("*" +element_name+"* - Element is not found!");
            throw(e);
        } finally {
            return element;
        }
    }

    public void swipe_vertical() throws InterruptedException {
        int starty = 660;
        int endy = 260;
        int startx = 502;
        driver.swipe(startx, starty, startx, endy, 3000);
        Thread.sleep(2000);
    }

    public void tap (int x, int y){
        TouchAction touch = new TouchAction (driver);
        touch.tap(x,y).perform();
    }

    /* Navigation to the panel settings pages */

    public void navigate_to_Settings_page () {
        Slide_Menu menu = PageFactory.initElements(driver, Slide_Menu.class);
        menu.Slide_menu_open.click();
        menu.Settings.click();
    }

    public void navigate_to_Advanced_Settings_page () {
        Slide_Menu menu = PageFactory.initElements(driver, Slide_Menu.class);
        Settings_Page settings = PageFactory.initElements(driver, Settings_Page.class);
        menu.Slide_menu_open.click();
        menu.Settings.click();
        settings.ADVANCED_SETTINGS.click();
        settings.Two.click();
        settings.Two.click();
        settings.Two.click();
        settings.Two.click();
    }

    public void ARM_STAY (){
        Home_Page home_page = PageFactory.initElements(driver, Home_Page.class);
        home_page.DISARM.click();
        home_page.ARM_STAY.click();
    }

    public void ARM_AWAY(int delay) throws Exception {
        Home_Page home_page = PageFactory.initElements(driver, Home_Page.class);
        home_page.DISARM.click();
        home_page.ARM_AWAY.click();
        TimeUnit.SECONDS.sleep(delay);
    }

    public void enter_default_user_code (){
        Home_Page home_page = PageFactory.initElements(driver, Home_Page.class);
        home_page.One.click();
        home_page.Two.click();
        home_page.Three.click();
        home_page.Four.click();
    }

    public void verify_disarm() {
        Home_Page home_page = PageFactory.initElements(driver, Home_Page.class);
        if (home_page.Disarmed_text.getText().equals("DISARMED")) {
            logger.info("Pass: System is DISARMED");
        } else {
            logger.info("Failed: System is not DISARMED " + home_page.Disarmed_text.getText());
        }
    }

    public void verify_armstay(){
        Home_Page home_page = PageFactory.initElements(driver, Home_Page.class);
        if (home_page.Disarmed_text.getText().equals("ARMED STAY")) {
            logger.info("Pass: System is ARMED STAY");
        } else {
            logger.info("Failed: System is NOT ARMED STAY");}
    }

    public void verify_armaway(){
        Home_Page home_page = PageFactory.initElements(driver, Home_Page.class);
        if (home_page.ArwAway_State.isDisplayed()) {
            logger.info("Pass: Panel is in Arm Away mode");
        } else {
            logger.info("Failed: Panel is not in Arm Away mode");}
    }
    public void verify_in_alarm() {
        Home_Page home_page = PageFactory.initElements(driver, Home_Page.class);
        if (home_page.ALARM.isDisplayed()) {
            logger.info("Pass: System is in ALARM");
        } else {
            logger.info("Failed: System is NOT in ALARM");}
    }
    public void verify_sensor_is_displayed(WebElement sensor_name){
        if (sensor_name.isDisplayed()) {
            logger.info(sensor_name.getText() +" is successfully opened/activated");
        } else {
            logger.info(sensor_name +" is NOT opened/activated");}
    }
    public void verify_sensor_is_tampered(WebElement sensor_name){
        if (sensor_name.isDisplayed()) {
            logger.info(sensor_name.getText() + " is successfully tampered");
        } else {
            logger.info(sensor_name +" is NOT tampered");}
    }

    public void verify_status_open(){
        Home_Page home_page = PageFactory.initElements(driver, Home_Page.class);
        if (home_page.Red_banner_sensor_status.getText().equals("Open")) {
            logger.info("Pass: Correct status is Open");
        }else { logger.info("Failed: Incorrect status: " + home_page.Red_banner_sensor_status.getText());}
    }

    public void verify_status_activated(){
        Home_Page home_page = PageFactory.initElements(driver, Home_Page.class);
        if (home_page.Red_banner_sensor_status.getText().equals("Activated")) {
            logger.info("Pass: Correct status is Activated");
        }else { logger.info("Failed: Incorrect status: " + home_page.Red_banner_sensor_status.getText());}
    }
    public void verify_status_tampered(){
        Home_Page home_page = PageFactory.initElements(driver, Home_Page.class);
        if (home_page.Red_banner_sensor_status.getText().equals("Tampered")) {
            logger.info("Pass: Correct status is Tampered");
        }else { logger.info("Failed: Incorrect status: " + home_page.Red_banner_sensor_status.getText());}
    }
    public void verify_status_alarmed(){
        Home_Page home_page = PageFactory.initElements(driver, Home_Page.class);
        if (home_page.Red_banner_sensor_status.getText().equals("Alarmed")) {
            logger.info("Pass: Correct status is Alarmed");
        }else { logger.info("Failed: Incorrect status: " + home_page.Red_banner_sensor_status.getText());}
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
    public void Rename_Sensor(int number, String new_name) {
        WebElement b = driver.findElement(By.className("android.widget.LinearLayout"));
        List<WebElement> li1 = b.findElements(By.id("com.qolsys:id/imageView1"));
        li1.get(number).click();
        driver.findElement(By.id("com.qolsys:id/sensorDescText")).clear();
        driver.findElement(By.id("com.qolsys:id/sensorDescText")).sendKeys(new_name);
        driver.hideKeyboard();
        driver.findElement(By.id("com.qolsys:id/addsensor")).click();
    }
    public void delete_all_camera_photos() throws Exception {
        Panel_Camera_Page camera = PageFactory.initElements(driver, Panel_Camera_Page.class);
        swipeFromLefttoRight();
        Thread.sleep(2000);
        try {
            while (camera.Photo_lable.isDisplayed()){
                camera.Camera_delete.click();
                camera.Camera_delete_yes.click();
                enter_default_user_code();}
        }catch (Exception e) {
            System.out.println("No photos to delete...");
        } finally {
        }
        swipeFromLefttoRight();
    }
}
