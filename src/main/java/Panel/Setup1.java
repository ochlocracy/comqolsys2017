package Panel;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import jxl.read.biff.BiffException;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Setup1 {

    Configuration c = new Configuration();
    public String adbPath = c.getAdbPath();
    public String appDir = "src";

    public AndroidDriver<WebElement> driver;
    DesiredCapabilities capabilities = new DesiredCapabilities();


    public AndroidDriver<WebElement> getDriver() {return driver;}

    public Setup1() throws IOException, BiffException { this.driver = driver;}

    public DesiredCapabilities getCapabilities() {
        return capabilities;
    }

    public Log log = new Log();
    public Logger logger = Logger.getLogger("String");
    public Runtime rt = Runtime.getRuntime();

   private static final SimpleDateFormat sdf = new SimpleDateFormat("MM.dd_HH.mm.ss");

    protected WebDriver driver1;
    protected WebDriverWait wait;

    public WebDriver getDriver1() {
        return driver1;
    }

    public void webDriverSetUp () {
        driver1 = new FirefoxDriver();
        wait = new WebDriverWait(driver1, 40);
    }

    @Parameters({"deviceName_", "applicationName_", "UDID_", "platformVersion_", "URL_", "PORT_" })
    @BeforeClass(alwaysRun=true)
    public void setCapabilities(String URL_) throws MalformedURLException {
        capabilities.setCapability("BROWSER_NAME", "Android");
        capabilities.setCapability("deviceName", "deviceName_");
        capabilities.setCapability("UDID", "UDID_");
        capabilities.setVersion("platformVersion_");
        capabilities.setCapability("applicationName", "applicationName_");
        capabilities.setCapability("appPackage", "com.qolsys");
        capabilities.setCapability("appActivity", "com.qolsys.activites.Theme3HomeActivity");
        capabilities.setCapability("PORT", "PORT_");
        this.driver = new AndroidDriver<WebElement>(new URL(URL_), getCapabilities());
    }

    public void setup_logger(String test_case_name, String UDID_) throws Exception {
        PropertyConfigurator.configure(new File(appDir, "log4j.properties").getAbsolutePath());
        log.startTestCase(" " +test_case_name+ " " + UDID_);
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

    public WebElement element_verification(String UDID_, WebElement element, String element_name) throws  Exception{
        try {
            if (element.isDisplayed()) {
                logger.info("Pass: "+ UDID_ +" "+ element_name + " is present, value = " + element.getText());
            }
        } catch (Exception e){
            take_screenshot();
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
    public void swipe_vertical_up() throws InterruptedException {
        int starty = 260;
        int endy = 660;
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
    public void DISARM (){
        Home_Page home_page = PageFactory.initElements(driver, Home_Page.class);
        home_page.DISARM.click();
        enter_default_user_code();
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

    public void verify_disarm(String UDID_) throws Exception {
        Home_Page home_page = PageFactory.initElements(driver, Home_Page.class);
        if (home_page.Disarmed_text.getText().equals("DISARMED")) {
            logger.info("Pass: " + UDID_ + " System is DISARMED");
        } else {
            take_screenshot();
            logger.info("Failed: System is not DISARMED " + home_page.Disarmed_text.getText());
        }
    }

    public void verify_armstay(String UDID_) throws Exception {
        Home_Page home_page = PageFactory.initElements(driver, Home_Page.class);
        if (home_page.Disarmed_text.getText().equals("ARMED STAY")) {
            logger.info("Pass: "+ UDID_ +" System is ARMED STAY");
        } else {
            take_screenshot();
            logger.info("Failed: System is NOT ARMED STAY");}
    }

    public void verify_armaway(String UDID_) throws Exception {
        Home_Page home_page = PageFactory.initElements(driver, Home_Page.class);
        if (home_page.ArwAway_State.isDisplayed()) {
            logger.info("Pass: " + UDID_ +" Panel is in Arm Away mode");
        } else {
            take_screenshot();
            logger.info("Failed: Panel is not in Arm Away mode");}
    }
    public void verify_in_alarm() throws Exception {
        Home_Page home_page = PageFactory.initElements(driver, Home_Page.class);
        if (home_page.ALARM.isDisplayed()) {
            logger.info("Pass: System is in ALARM");
        } else {
            take_screenshot();
            logger.info("Failed: System is NOT in ALARM");}
    }
    public void verify_sensor_is_displayed(String UDID_, WebElement sensor_name) throws Exception {
        if (sensor_name.isDisplayed()) {
            logger.info(UDID_ + " " +sensor_name.getText() +" is successfully opened/activated");
        } else {
            take_screenshot();
            logger.info(UDID_ + " " + sensor_name +" is NOT opened/activated");}
    }
    public void verify_sensor_is_tampered(WebElement sensor_name) throws Exception {
        if (sensor_name.isDisplayed()) {
            logger.info(sensor_name.getText() + " is successfully tampered");
        } else {
            take_screenshot();
            logger.info(sensor_name +" is NOT tampered");}
    }

    public void verify_status_open() throws Exception {
        Home_Page home_page = PageFactory.initElements(driver, Home_Page.class);
        if (home_page.Red_banner_sensor_status.getText().equals("Open")) {
            logger.info("Pass: Correct status is Open");
        }else {
            take_screenshot();
            logger.info("Failed: Incorrect status: " + home_page.Red_banner_sensor_status.getText());}
    }

    public void verify_status_activated() throws Exception {
        Home_Page home_page = PageFactory.initElements(driver, Home_Page.class);
        if (home_page.Red_banner_sensor_status.getText().equals("Activated")) {
            logger.info("Pass: Correct status is Activated");
        }else {
            take_screenshot();
            logger.info("Failed: Incorrect status: " + home_page.Red_banner_sensor_status.getText());}
    }
    public void verify_status_tampered() throws Exception {
        Home_Page home_page = PageFactory.initElements(driver, Home_Page.class);
        if (home_page.Red_banner_sensor_status.getText().equals("Tampered")) {
            logger.info("Pass: Correct status is Tampered");
        }else { take_screenshot();
            logger.info("Failed: Incorrect status: " + home_page.Red_banner_sensor_status.getText());}
    }
    public void verify_status_alarmed() throws Exception {
        Home_Page home_page = PageFactory.initElements(driver, Home_Page.class);
        if (home_page.Red_banner_sensor_status.getText().equals("Alarmed")) {
            logger.info("Pass: Correct status is Alarmed");
        }else { take_screenshot();
            logger.info("Failed: Incorrect status: " + home_page.Red_banner_sensor_status.getText());}
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
        Thread.sleep(3000);
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
    public void take_screenshot() throws Exception {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        try {
            // now copy the  screenshot to desired location using copyFile //method
            FileUtils.copyFile(src, new File("/home/qolsys/Desktop/comqolsysPOM/screenshots/"+sdf.format(timestamp)+".png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void eventLogsGenerating(String fileName,String[] findEvent, int length) throws Exception{
        List<LogEntry> logEntries = driver.manage().logs().get("logcat").getAll();
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
        for(int i=0; i<logEntries.size(); i++) {
            String log = logEntries.get(i).getMessage();
            bw.write(log);
            bw.newLine();
            displayingEvent(log,findEvent,length);
        }
        bw.close();
    }

    private void displayingEvent(String log, String[] findEvent, int length){
        for(int j=0;j<length;j++){
            if (log.contains(findEvent[j])) {
                System.out.println(findEvent[j]+" RECEIVED");
            }
        }
    }
    protected void deleteLogFile(String fileName) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(fileName);
        writer.print("");
        writer.close();
    }

    public void LogcatClear() throws Exception{
        rt.exec(adbPath+" logcat -c &");
    }

    public void killLogcat() throws Exception{
        rt.exec(adbPath+" shell busybox pkill logcat");
    }
    public void add_primary_call(int zone, int group, int sencor_dec, int sensor_type, String UDID_) throws IOException {
        String add_primary = " shell service call qservice 50 i32 " + zone + " i32 " + group + " i32 " + sencor_dec + " i32 " + sensor_type;
        rt.exec(adbPath + " -s " + UDID_ + add_primary);
        // shell service call qservice 50 i32 2 i32 10 i32 6619296 i32 1
    }

    public void primary_call(String UDID_, String DLID, String State) throws IOException {
        String primary_send =" shell rfinjector 02 "+DLID+" "+State;
        rt.exec(adbPath + " -s " +UDID_+ primary_send);
        // shell service call qservice 50 i32 2 i32 10 i32 6619296 i32 1
        System.out.println(adbPath + " -s " +UDID_+ primary_send); }

    public void delete_from_primary(String UDID_, int zone) throws IOException, InterruptedException {
        logger.info("Deleting sensor/sensors from a panel");
        String deleteFromPrimary = " shell service call qservice 51 i32 " + zone;
        rt.exec(adbPath + " -s " +UDID_+ deleteFromPrimary);
        System.out.println(deleteFromPrimary);
    }
    public void autoStaySetting () throws InterruptedException {
        Home_Page home = PageFactory.initElements(driver, Home_Page.class);
        Advanced_Settings_Page adv = PageFactory.initElements(driver, Advanced_Settings_Page.class);
        Installation_Page inst = PageFactory.initElements(driver, Installation_Page.class);
        Security_Arming_Page arming = PageFactory.initElements(driver, Security_Arming_Page.class);
        navigate_to_Advanced_Settings_page();
        adv.INSTALLATION.click();
        inst.SECURITY_AND_ARMING.click();
        Thread.sleep(1000);
        swipe_vertical();
        Thread.sleep(1000);
        arming.Auto_Stay.click();
        Thread.sleep(1000);
        home.Home_button.click();
    }
}