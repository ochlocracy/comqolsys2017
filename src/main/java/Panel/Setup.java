package Panel;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;

import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.*;
import org.openqa.selenium.logging.LogEntry;

import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import jxl.read.biff.BiffException;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.imageio.ImageIO;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Setup {

    Configuration c = new Configuration();
    public String adbPath = c.getAdbPath();
    public File appDir = new File("src");
    public String udid_ =  c.getudid_(); //"8ebdbc27";
    public String projectPath = c.getProjectPath(); //

    public AndroidDriver<WebElement> driver;


    public Log log = new Log();
    public Logger logger = Logger.getLogger(this.getClass().getName());
    public Runtime rt = Runtime.getRuntime();

   private static final SimpleDateFormat sdf = new SimpleDateFormat("MM.dd_HH.mm.ss");

    public Setup() throws IOException, BiffException {}

    public WebDriver driver1;
    public WebDriverWait wait;

    public void webDriverSetUp () {
        driver1 = new FirefoxDriver();
        wait = new WebDriverWait(driver1, 40);
    }
    public WebDriver getDriver1() {
        return driver1;
    }

    public static String execCmd(String cmd) throws java.io.IOException {
        Process proc = Runtime.getRuntime().exec(cmd);
        java.io.InputStream is = proc.getInputStream();
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        String val = "";
        if (s.hasNext()) {
            val = s.next();
        }
        else {
            val = "";
        }
   //     System.out.println(val);
        return val;
    }
    public String split_method (  String str) {
        String a = str.split("\\n")[1];
        return a.split("\\s")[0];
    }

    public String get_UDID() throws IOException {
        String command = adbPath + " devices";
        rt.exec(command);
        String panel_UDID = split_method(execCmd(command));
        return panel_UDID;
    }

    public void setup_driver(String getUdid, String url_, String port_) throws Exception {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("deviceName", "IQPanel2");
        cap.setCapability("BROWSER_NAME", "Android");
        cap.setCapability("udid", getUdid);
        cap.setCapability("appPackage", "com.qolsys");
        cap.setCapability("appActivity", "com.qolsys.activites.Theme3HomeActivity");
        cap.setCapability("newCommandTimeout", "1000");
        cap.setCapability("clearSystemFiles", true);
        driver = new AndroidDriver<WebElement>(new URL(url_+":"+port_+"/wd/hub"), cap);
    }

    public void setup_logger(String test_case_name) throws Exception {
        PropertyConfigurator.configure(new File(appDir, "log4j.properties").getAbsolutePath());
      //  log.clearLog();
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

    public WebElement element_verification(WebElement element, String element_name ) throws  Exception{
        try {
            if (element.isDisplayed()) {
                logger.info("Pass: " + element_name + " is present, value = " + element.getText());
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

    public void navigate_to_Advanced_Settings_page () throws InterruptedException {
        Slide_Menu menu = PageFactory.initElements(driver, Slide_Menu.class);
        Settings_Page settings = PageFactory.initElements(driver, Settings_Page.class);
        menu.Slide_menu_open.click();
        menu.Settings.click();
        Thread.sleep(1000);
        settings.ADVANCED_SETTINGS.click();
        Thread.sleep(2000);
        settings.Two.click();
        settings.Two.click();
        settings.Two.click();
        settings.Two.click();
    }
    public void DISARM (){
        Home_Page home_page = PageFactory.initElements(driver, Home_Page.class);
        logger.info("Disarm");
        home_page.DISARM.click();
        enter_default_user_code();
    }

    public void ARM_STAY (){
        Home_Page home_page = PageFactory.initElements(driver, Home_Page.class);
        logger.info("Arm Stay");
        home_page.DISARM.click();
        home_page.ARM_STAY.click();
    }

    public void ARM_AWAY(int delay) throws Exception {
        Home_Page home_page = PageFactory.initElements(driver, Home_Page.class);
        logger.info("Arm Away");
        home_page.DISARM.click();
        home_page.ARM_AWAY.click();
        TimeUnit.SECONDS.sleep(delay);
    }
    public void enter_default_DURESS_code (){
        Home_Page home_page = PageFactory.initElements(driver, Home_Page.class);
        home_page.Nine.click();
        home_page.Nine.click();
        home_page.Nine.click();
        home_page.Eight.click();}
    public void enter_default_user_code (){
        Home_Page home_page = PageFactory.initElements(driver, Home_Page.class);
        home_page.One.click();
        home_page.Two.click();
        home_page.Three.click();
        home_page.Four.click();
    }
    public void enter_guest_code (){
        Home_Page home_page = PageFactory.initElements(driver, Home_Page.class);
        home_page.One.click();
        home_page.Two.click();
        home_page.Three.click();
        home_page.Three.click();
    }
    public void enter_default_dealer_code (){
        Home_Page home_page = PageFactory.initElements(driver, Home_Page.class);
        home_page.Two.click();
        home_page.Two.click();
        home_page.Two.click();
        home_page.Two.click();
    }
    public void verify_disarm() throws Exception {
        Home_Page home_page = PageFactory.initElements(driver, Home_Page.class);
        if (home_page.Disarmed_text.getText().equals("DISARMED")) {
            logger.info("Pass: System is DISARMED");
        } else {
            take_screenshot();
            logger.info("Failed: System is not DISARMED " + home_page.Disarmed_text.getText());
        }
    }

    public void verify_armstay() throws Exception {
        Home_Page home_page = PageFactory.initElements(driver, Home_Page.class);
        if (home_page.Disarmed_text.getText().equals("ARMED STAY")) {
            logger.info("Pass: System is ARMED STAY");
        } else {
            take_screenshot();
            logger.info("Failed: System is NOT ARMED STAY");}
    }
    public boolean verify_armstay_l() throws Exception {
        Home_Page home_page = PageFactory.initElements(driver, Home_Page.class);
        if (home_page.Disarmed_text.getText().equals("ARMED STAY")) {
            logger.info("Pass: System is ARMED STAY");
            return true;
        } else {
            take_screenshot();
            logger.info("Failed: System is NOT ARMED STAY");}
        return false;
    }

    public void verify_armaway() throws Exception {
        Home_Page home_page = PageFactory.initElements(driver, Home_Page.class);
        if (home_page.ArwAway_State.isDisplayed()) {
            logger.info("Pass: Panel is in Arm Away mode");
        } else {
            take_screenshot();
            logger.info("Failed: Panel is not in Arm Away mode");}
    }


    public void verify_photoframe_mode() throws Exception {
        Home_Page home_page = PageFactory.initElements(driver, Home_Page.class);
        if (home_page.PhotoFrame_Mode.isDisplayed()) {
            logger.info("Pass: Panel is in Photo Frame mode");
        } else {
            take_screenshot();
            logger.info("Failed: Panel is not in Photo Frame mode");}}
    public void verify_in_alarm() throws Exception {
        Home_Page home_page = PageFactory.initElements(driver, Home_Page.class);
        if (home_page.ALARM.isDisplayed()) {
            logger.info("Pass: System is in ALARM");
        } else {
            take_screenshot();
            logger.info("Failed: System is NOT in ALARM");}
    }
    public void verify_sensor_is_displayed(WebElement sensor_name) throws Exception {
        if (sensor_name.isDisplayed()) {
            logger.info(sensor_name.getText() +" is successfully opened/activated");
        } else {
            take_screenshot();
            logger.info(sensor_name +" is NOT opened/activated");}
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

    public void verify_sensorstatus_inAlarm(String Al_status) throws Exception {
        Home_Page home_page = PageFactory.initElements(driver, Home_Page.class);
        if (home_page.Red_banner_sensor_status.getText().equals(Al_status)) {
            logger.info("Pass: Correct status is " + Al_status);
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
        swipeFromRighttoLeft();
    }
    public void take_screenshot() throws Exception {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        try {
            // now copy the  screenshot to desired location using copyFile //method
            FileUtils.copyFile(src, new File("src/screenshots/"+sdf.format(timestamp)+".png"));
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
    public static void SendReport(String email) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("adyshleva@gmail.com", "Deutschland1983!");
                    }
                });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("adyshleva@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(email));
            message.setSubject("Test Automation Report");
            BodyPart messageBodyPart1 = new MimeBodyPart();
            messageBodyPart1.setText("Hello, \nplease find in the attachment test automation results for the current build");
            MimeBodyPart messageBodyPart2 = new MimeBodyPart();
            String filename = "/home/qolsys/Desktop/comqolsysPOM/log/testlog1.log";
            DataSource source = new FileDataSource(filename);
            messageBodyPart2.setDataHandler(new DataHandler(source));
            messageBodyPart2.setFileName(filename);
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart2);
            multipart.addBodyPart(messageBodyPart1);
            message.setContent(multipart);
            Transport.send(message);
            System.out.println("=====Email Sent=====");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void setDelay() throws InterruptedException {
        Home_Page home = PageFactory.initElements(driver, Home_Page.class);
        Dealer_Settings_Page dealer = PageFactory.initElements(driver, Dealer_Settings_Page.class);
        Advanced_Settings_Page adv = PageFactory.initElements(driver, Advanced_Settings_Page.class);
        Installation_Page inst = PageFactory.initElements(driver, Installation_Page.class);
        Security_Arming_Page arming = PageFactory.initElements(driver, Security_Arming_Page.class);
        navigate_to_Advanced_Settings_page();
        adv.INSTALLATION.click();
        inst.DEALER_SETTINGS.click();
        Thread.sleep(1000);
        swipe_vertical();
        Thread.sleep(1000);
        swipe_vertical();
        Thread.sleep(1000);
        dealer.SIA_Limits.click();
        Thread.sleep(1000);
        home.Back_button.click();
        Thread.sleep(1000);
        inst.SECURITY_AND_ARMING.click();
        Thread.sleep(1000);
        swipe_vertical();
        Thread.sleep(1000);
        swipe_vertical();
        Thread.sleep(1000);
        swipe_vertical();
        Thread.sleep(1000);
        swipe_vertical();
        Thread.sleep(1000);
        arming.Normal_Entry_Delay.click();
        Thread.sleep(1000);
        tap(1081,504);
        Thread.sleep(1000);
        tap(540,504);
        Thread.sleep(1000);
        tap(540,504);
        arming.Delay_Set.click();
        Thread.sleep(1000);
        arming.Normal_Exit_Delay.click();
        Thread.sleep(1000);
        tap(1081,504);
        Thread.sleep(1000);
        tap(540,504);
        Thread.sleep(1000);
        tap(725,504);
        arming.Delay_Set.click();
        Thread.sleep(1000);
        arming.Long_Entry_Delay.click();
        Thread.sleep(1000);
        tap(1081,504);
        Thread.sleep(1000);
        tap(540,504);
        Thread.sleep(1000);
        tap(908,504);
        arming.Delay_Set.click();
        Thread.sleep(1000);
        arming.Long_Exit_Delay.click();
        Thread.sleep(1000);
        tap(1081,504);
        Thread.sleep(1000);
        tap(540,504);
        Thread.sleep(1000);
        tap(908,504);
        arming.Delay_Set.click();
        Thread.sleep(1000);
        home.Home_button.click();
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
    public void enableWiFi () throws InterruptedException {
        Advanced_Settings_Page adv = PageFactory.initElements(driver, Advanced_Settings_Page.class);
        navigate_to_Advanced_Settings_page();
        adv.WI_FI.click();
        driver.findElement(By.id("com.qolsys:id/wire_less_toggle")).click();
        Thread.sleep(3000);
    }
    public void swipe_right() throws InterruptedException {
        int starty = 400;
        int endx = 660;
        int startx = 360;
        driver.swipe(startx, starty, endx, starty, 500);
        Thread.sleep(2000);
    }

    public void swipe_left() throws InterruptedException {
        int starty = 400;
        int endx = 360;
        int startx = 660;
        driver.swipe(startx, starty, endx, starty, 500);
        Thread.sleep(2000);
    }

    public void swipe_up() throws InterruptedException {
        int starty = 616;
        int endy = 227;
        int startx = 314;
        driver.swipe(startx, starty, startx, endy, 3000);
        Thread.sleep(2000);
    }

    public void swipe_down() throws InterruptedException {
        int starty = 227;
        int endy = 616;
        int startx = 314;
        driver.swipe(startx, starty, startx, endy, 3000);
        Thread.sleep(2000);
    }


    public boolean checkAttribute(WebElement ele, String attribute, String state){
        if(ele.getAttribute(attribute).equals(state)){
            logger.info("Pass: the element's attribute [" + attribute + "] is " + state);
            return true;
        }
        else{
            logger.info("Fail: the element's attribute [" + attribute + "] is " + ele.getAttribute(attribute));
            return false;
        }
    }


    //compares two images and returns whether or not they're identical
    public boolean compareImage(File fileA, File fileB){
        try {
            // take buffer data from both image files //
            BufferedImage biA = ImageIO.read(fileA);
            DataBuffer dbA = biA.getData().getDataBuffer();
            int sizeA = dbA.getSize();
            BufferedImage biB = ImageIO.read(fileB);
            DataBuffer dbB = biB.getData().getDataBuffer();
            int sizeB = dbB.getSize();
            // compare data-buffer objects //
            if(sizeA == sizeB) {
                for(int i=0 ; i< sizeA ; i++) {
                    if(dbA.getElem(i) != dbB.getElem(i)) {
                        return false;
                    }
                }
                return true;
            }
            else {
                return false;
            }
        }
        catch (Exception e) {
            logger.info("Failed to compare image files ..." + e);
            return false;
        }
    }


    /*public void imageCaptureRotateCropById(String img, String element) throws Exception{
        logger.info("Image Capturing is in progress .......");
        WebElement ele = driver.findElement(By.id(element));
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        File screenshotLocation = new File(projectPath+img);
        FileUtils.copyFile(screenshot, screenshotLocation);
        //rotateImage(screenshotLocation);
        BufferedImage  fullImg = ImageIO.read(new File(projectPath+img));
        Point point = ele.getLocation();
        int eleWidth = ele.getSize().getWidth();
        int eleHeight = ele.getSize().getHeight();
        BufferedImage eleScreenshot= fullImg.getSubimage(point.getX(), point.getY(), eleWidth, eleHeight);
        ImageIO.write(eleScreenshot, "png", screenshot);
        File screenshotLocation1 = new File(projectPath+img);
        FileUtils.copyFile(screenshot, screenshotLocation1);
    }*/

    //takes a screenshot of the given element and saves it to the given destination
    public File takeScreenshot(WebElement ele, String dst) throws Exception{
        // Get entire page screenshot
        File screenshot = driver.getScreenshotAs(OutputType.FILE);
        BufferedImage  fullImg = ImageIO.read(screenshot);

        // Get the location of element on the page
        org.openqa.selenium.Point point = ele.getLocation();

        // Get width and height of the element
        int eleWidth = ele.getSize().getWidth();
        int eleHeight = ele.getSize().getHeight();

        // Crop the entire page screenshot to get only element screenshot
        BufferedImage eleScreenshot= fullImg.getSubimage(point.getX(), point.getY(), eleWidth, eleHeight);
        ImageIO.write(eleScreenshot, "png", screenshot);

        // Copy the element screenshot to disk
        File screenshotLocation = new File(dst);
        FileUtils.copyFile(screenshot, screenshotLocation);

        return screenshotLocation;
    }

    //compares a given element against a provided screenshot
    public boolean checkStatus(File cmp, WebElement ele) throws Exception {
        File tmp = takeScreenshot(ele, projectPath + "/scr/tmp");
        Thread.sleep(2000);
        if(compareImage(tmp, cmp)){
            logger.info("Pass: light icon is the expected color");
            java.lang.Runtime.getRuntime().exec("rm -f " + tmp.getAbsolutePath());
            return true;
        }
        else{
            logger.info("Fail: light icon is not the expected color");
            java.lang.Runtime.getRuntime().exec("rm -f " + tmp.getAbsolutePath());
            return false;
        }
    }

    //calls checkStatus on every element in a given list
    public void checkAllStatus(File status, List<WebElement> ele) throws Exception{
        int i;
        int size = ele.size();
        for(i = 0; i < size; i++)
            checkStatus(status, ele.get(i));
    }

    //clicks all of the elements contained in a given list
    public void clickAll(List<WebElement> ele){
        int i;
        int size = ele.size();
        for(i = 0; i < size; i++)
            ele.get(i).click();
    }

    //performs a swipe using a touch action
    public void touchSwipe(int startx, int starty, int endx, int endy){
        TouchAction a = new TouchAction(driver);
        a.longPress(startx, starty).moveTo(endx, endy).release().perform();
    }

    //State is "Enable" or "Disable"
    public void setArmStay_NoDelay(String state) throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 37 i32 0 i32 0 i32 21 i32 0 i32 0";
        rt.exec(command);
        String value = (execCmd(command)).toString();
        System.out.println(value);

        if (state.equals("Enable")) {
            if (value.contains("00000001")) {
                System.out.println("Setting is already Enabled");
            } else if (value.contains("00000000")) {
                System.out.println("Setting is Disabled, Enabling the setting");
                rt.exec(adbPath + " shell service call qservice 40 i32 0 i32 0 i32 21 i32 1 i32 0 i32 0");}
        }

        else if (state.equals("Disable")) {
            if (value.contains("00000001")) {
                System.out.println("Setting is Enabled, Disabling the setting");
                rt.exec(adbPath + " shell service call qservice 40 i32 0 i32 0 i32 21 i32 0 i32 0 i32 0");
            } else if (value.contains("00000000")) {
                System.out.println("Setting is already Disabled");
            }
        }
        Thread.sleep(1000);
    }
    //State is "Enable" or "Disable"
    public void setAutoStay(String state) throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 37 i32 0 i32 0 i32 20 i32 0 i32 0";
        rt.exec(command);
        String value = (execCmd(command)).toString();
        System.out.println(value);

        if (state.equals("Enable")) {
            if (value.contains("00000001")) {
                System.out.println("Setting is already Enabled");
            } else if (value.contains("00000000")) {
                System.out.println("Setting is Disabled, Enabling the setting");
                rt.exec(adbPath + " shell service call qservice 40 i32 0 i32 0 i32 20 i32 1 i32 0 i32 0");}
        }

        else if (state.equals("Disable")) {
            if (value.contains("00000001")) {
                System.out.println("Setting is Enabled, Disabling the setting");
                rt.exec(adbPath + " shell service call qservice 40 i32 0 i32 0 i32 20 i32 0 i32 0 i32 0");
            } else if (value.contains("00000000")) {
                System.out.println("Setting is already Disabled");
            }
        }
        Thread.sleep(1000);
    }
    public static String captureScreenshot (AndroidDriver driver, String screenshotName) throws IOException {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs((OutputType.FILE));
            String dest = "/home/qolsys/IdeaProjects/comqolsys2017/Report/" + screenshotName + ".png";
            File destination = new File(dest);
            FileUtils.copyFile(source, destination);
            System.out.println("Screenshot taken");
            return dest;
        } catch (Exception e) {
            System.out.println("Exception while taking screenshot " + e.getMessage());
            return e.getMessage();
        }
    }
    public void verify_setting(String setting, String call, String expected) throws IOException {
        String result = execCmd(adbPath + " shell service call qservice " + call).split(" ")[2];
        if(result.equals(expected))
            logger.info("[Pass] " + setting + " has value: " + expected);
        else
            logger.info("[Fail] " + setting + " has value: " + result + ". Expected:" + expected);

    }

    }