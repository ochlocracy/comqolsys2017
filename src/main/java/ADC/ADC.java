package ADC;

import Panel.Setup;
import Sensors.Sensors;
import jxl.read.biff.BiffException;
import net.sf.cglib.core.internal.Function;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ADC extends Setup {

    Sensors mySensors = new Sensors();

    String door_window_DLID = "65 00 9A";
    String motion_DLID = "55 00 44";
    String smoke_DLID = "67 00 22";
    String co_detector_DLID = "75 00 AA";
    String glassbreak_DLID = "67 00 99";
    String tilt_DLID = "63 00 EA";
    String shock_other_DLID = "66 00 C9";
    String freeze_DLID = "73 00 1A";
    String heat_DLID = "75 00 26";
    String water_flood_DLID = "75 11 0A";
    String doorbell_DLID = "61 BD AA";
    String occupancy_DLID = "85 00 1A";

    private int door_window = 1;
    private int motion = 2;
    private int smoke_detector = 5;
    private int co_detector = 6;
    private int glassbreak = 19;
    private int tilt = 16;
    private int shock_IQ = 107;
    private int shock_other = 113;
    private int freeze = 17;
    private int heat = 111;
    private int water_flood = 22;
    private int keyfob = 102;
    private int keypad = 104;
    private int med_pendant = 21;
    private int doorbell = 109;
    private int occupancy = 114;

    public int getDoor_window() {
        return door_window;
    }

    public int getMotion() {
        return motion;
    }

    public int getSmoke_detector() {
        return smoke_detector;
    }

    public int getCo_detector() {
        return co_detector;
    }

    public int getGlassbreak() {
        return glassbreak;
    }

    public int getTilt() {
        return tilt;
    }

    public int getShock_IQ() {
        return shock_IQ;
    }

    public int getShock_other() {
        return shock_other;
    }

    public int getFreeze() {
        return freeze;
    }

    public int getHeat() {
        return heat;
    }

    public int getWater_flood() {
        return water_flood;
    }

    public int getKeyfob() {
        return keyfob;
    }

    public int getKeypad() {
        return keypad;
    }

    public int getMed_pendant() {
        return med_pendant;
    }

    public int getDoorbell() {
        return doorbell;
    }

    public int getOccupancy() {
        return occupancy;
    }

    public String new_dw_name = "NewDoor1";
    public String new_motion_name = "NewMotion1";
    public String new_smoke_name = "NewSmoke1";
    public String new_co_name = "NewCO1";
    public String new_glassbreak_name = "NewGlassBreak1";
    public String new_tilt_name = "NewTilt1";
    public String new_shock_other_name = "NewShockOther1";
    public String new_freeze_name = "NewFreeze1";
    public String new_heat_name = "NewHeat1";
    public String new_water_flood_name = "NewWaterFlood1";
    public String new_keyfob_name = "NewKeyFob1";
    public String new_keypad_name = "NewKeyPad1";
    public String new_med_pendant_name = "NewAuxiliaryPendant1";
    public String new_doorbell_name = "NewDoorBell1";
    public String new_occupancy_name = "NewOccupancy1";
    public String new_iq_shock_name = "NewIQShock1";

    public ADC() throws IOException, BiffException {}

    public String getAccountId () throws IOException {
        String accountId = null;
        if (get_UDID().equals("8ebdbc76")) {      //Anya
            accountId = "4679473";
            return accountId;
        } else if (get_UDID().equals("8ebdbcf6")) {    //Olga
            accountId = "5432189";
            return accountId;
        } else if (get_UDID().equals("ac82129c")){    //Sergio
            accountId = "5434757";
            return accountId;
        } else if (get_UDID().equals("8ebdbc27")){    //Nathan
            accountId = "5347653";
            return accountId;
        }else if (get_UDID().equals("8ebdbcf1")) {    //Zach
            accountId = "5434890";
            return accountId;
        }else if (get_UDID().equals("8ebdbcb3")) {    //Jeff
        accountId = "4283420";
        return accountId;
        }else  if (get_UDID().equals("62e9f0df")) {
            accountId = "5222397";
        }else  if (get_UDID().equals("62964b68")) {
        accountId = "5389996";
        }else  if (get_UDID().equals("62c74a45")) { //Olga new verizon
            accountId = "5222509";
        }else  if (get_UDID().equals("62964b68")) { //Olga AT&T
            accountId = "5389996";
        }else  if (get_UDID().equals("62964b68")) { //Olga AT&T
         accountId = " 5389996";
        }
        return  accountId;
    }

    public void add_sensor(int zone, int group, int DLID, int sensor_type) throws IOException {
        String add_sensor = " shell service call qservice 50 i32 " + zone + " i32 " + group + " i32 " + DLID + " i32 " + sensor_type;
        mySensors.rt.exec(adbPath + " -s " + mySensors.primary + add_sensor);
        System.out.println(adbPath + " -s " + mySensors.primary + add_sensor);
    }
    public void add_sensor_grid(int zone, int group, int DLID, int sensor_type, String UDID_) throws IOException {
        String add_sensor = " shell service call qservice 50 i32 " + zone + " i32 " + group + " i32 " + DLID + " i32 " + sensor_type;
        mySensors.rt.exec(adbPath + " -s " + UDID_ + add_sensor);
        System.out.println(add_sensor);
    }
    public void add_all_sensor_types() throws IOException {
        add_sensor(1, 10, 6619305, door_window); //default name Door/Window 1
        add_sensor(2, 17, 5570628, motion); //default name Motion 2
        add_sensor(3, 26, 6750242, smoke_detector); //default name Smoke Detector 3
        add_sensor(4, 34, 7667882, co_detector); //default name CO Detector 4
        add_sensor(5, 13, 6750361, glassbreak); //default name Glass Break 5
        add_sensor(6, 12, 6488238, tilt); //default name Tilt 6
        add_sensor(7, 13, 6684828, shock_other); //default name Other Shock 7
        add_sensor(8, 52, 7536801, freeze); //default name Freeze 8
        add_sensor(9, 26, 7667810, heat); //default name Smoke-M 9
        add_sensor(10, 38, 7672224, water_flood); //default name Multi-Function-1 10
        add_sensor(11, 4, 6619386, keyfob); //default name Key Fob 11
        add_sensor(12, 2, 8716538, keypad); //default name Keypad 12
        add_sensor(13, 6, 6361649, med_pendant); //default name Auxiliary Pendant 13
        add_sensor(14, 25, 6405546, doorbell); //default name Door Bell 14
        add_sensor(15, 25, 8716449, occupancy); //default name Occupancy Sensor 15
        add_sensor(16, 13, 6684829, shock_IQ); //default name Shock-IQ 16
    }
    public void add_all_sensor_types_grid(String UDID_) throws IOException {
        add_sensor_grid(1, 10, 6619305, door_window, UDID_); //default name Door/Window 1
        add_sensor_grid(2, 17, 5570628, motion, UDID_); //default name Motion 2
        add_sensor_grid(3, 26, 6750242, smoke_detector, UDID_); //default name Smoke Detector 3
        add_sensor_grid(4, 34, 7667882, co_detector, UDID_); //default name CO Detector 4
        add_sensor_grid(5, 13, 6750361, glassbreak, UDID_); //default name Glass Break 5
        add_sensor_grid(6, 12, 6488238, tilt, UDID_); //default name Tilt 6
        add_sensor_grid(7, 13, 6684828, shock_other, UDID_); //default name Other Shock 7
        add_sensor_grid(8, 52, 7536801, freeze, UDID_); //default name Freeze 8
        add_sensor_grid(9, 26, 7667810, heat, UDID_); //default name Smoke-M 9
        add_sensor_grid(10, 38, 7672224, water_flood, UDID_); //default name Multi-Function-1 10
        add_sensor_grid(11, 4, 6619386, keyfob, UDID_); //default name Key Fob 11
        add_sensor_grid(12, 2, 8716538, keypad, UDID_); //default name Keypad 12
        add_sensor_grid(13, 6, 6361649, med_pendant, UDID_); //default name Auxiliary Pendant 13
        add_sensor_grid(14, 25, 6405546, doorbell, UDID_); //default name Door Bell 14
        add_sensor_grid(15, 25, 8716449, occupancy, UDID_); //default name Occupancy Sensor 15
        add_sensor_grid(16, 13, 6684829, shock_IQ, UDID_); //default name Shock-IQ 16
    }
    public void delete_sensor(int zone) throws IOException {
        String delete = "shell service call qservice 51 i32 " + zone;
        mySensors.rt.exec(adbPath + " " + delete);
    }
    public void delete_sensor_grid(String UDID_, int zone) throws IOException {
        String delete = " shell service call qservice 51 i32 " + zone;
        mySensors.rt.exec(adbPath + " -s " +UDID_ + delete);
    }
    public void delete_all() throws IOException {
        for (int i = 1; i < 17; i++) {
            delete_sensor(i);}
    }
    public void delete_all_grid(String UDID_) throws IOException {
        for (int i = 1; i < 17; i++) {
            delete_sensor_grid(UDID_, i);
        }
    }
    public void javascript_disable() throws InterruptedException {
        driver1.get("about:config");
        Actions act = new Actions(driver1);
        act.sendKeys("javascript.enabled").perform();
        TimeUnit.SECONDS.sleep(2);
        act.sendKeys(Keys.TAB).perform();
        TimeUnit.SECONDS.sleep(2);
        act.sendKeys(Keys.ENTER).perform();
    }
    public void New_ADC_session(String accountID) throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        getDriver1().manage().window().maximize();
        String ADC_URL = "https://alarmadmin.alarm.com/Support/CustomerInfo.aspx?customer_Id="+accountID;
        getDriver1().get(ADC_URL);
        String login = "qautomation";
        String password = "Qolsys123";
        Thread.sleep(2000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtUsername")));
        getDriver1().findElement(By.id("txtUsername")).sendKeys(login);
        getDriver1().findElement(By.id("txtPassword")).sendKeys(password);
        getDriver1().findElement(By.id("butLogin")).click();
        getDriver1().findElement(By.partialLinkText("Equipment")).click();
        TimeUnit.SECONDS.sleep(2);
    }
    public void New_ADC_session_User(String User, String Password) throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        getDriver1().manage().window().maximize();
        String ADC_URL = "https://www.alarm.com/login.aspx";
        getDriver1().get(ADC_URL);
        driver1.findElement(By.id("ctl00_ContentPlaceHolder1_loginform_txtUserName")).sendKeys(User);
        driver1.findElement(By.className("password")).sendKeys(Password);
        Thread.sleep(1000);
        driver1.findElement(By.id("ctl00_ContentPlaceHolder1_loginform_signInButton")).click();
        Thread.sleep(1000);
        try {
            if (driver1.findElement(By.id("ctl00_responsiveBody_pageInfoActions_buttonSave")).isDisplayed()) {
                driver1.findElement(By.id("ctl00_responsiveBody_pageInfoActions_buttonSave")).click();
            }
        } catch (NoSuchElementException e) {
        }
    }

    public void New_ADC_session_emPower_Page(String accountID) throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        getDriver1().manage().window().maximize();
        String ADC_URL = "https://alarmadmin.alarm.com/Support/DeviceAutomation.aspx" + accountID;
        getDriver1().get(ADC_URL);
        String login = "qautomation";
        String password = "Qolsys123";
        Thread.sleep(2000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtUsername")));
        getDriver1().findElement(By.id("txtUsername")).sendKeys(login);
        getDriver1().findElement(By.id("txtPassword")).sendKeys(password);
        getDriver1().findElement(By.id("butLogin")).click();
        TimeUnit.SECONDS.sleep(2);
    }

    //must be on the Equipment page
    public void get_image_sensors() throws InterruptedException {
        getDriver1().findElement(By.xpath("/html/body/form/table/tbody/tr/td[2]/div/div[2]/div[3]/div/div/ul/li[4]/a")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_phBody_btnRequestInfo"))).click();
        TimeUnit.MINUTES.sleep(2);
        getDriver1().findElement(By.id("ctl00_phBody_btnRefreshPage")).click();
        TimeUnit.SECONDS.sleep(2);
    }
    public void navigate_to_user_site(String login, String password) {
        getDriver1().manage().window().maximize();
        String ADC_URL = "https://alarm.com";
        getDriver1().get(ADC_URL);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("LOGIN")));
        getDriver1().findElement(By.partialLinkText("LOGIN")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_ContentPlaceHolder1_loginform_" +
                "txtUserName")));
        getDriver1().findElement(By.id("ctl00_ContentPlaceHolder1_loginform_txtUserName")).sendKeys(login);
        getDriver1().findElement(By.className("password")).sendKeys(password);
        getDriver1().findElement(By.id("ctl00_ContentPlaceHolder1_loginform_signInButton")).click();
    }
    public void navigate_to_user_site_lights(String login, String password) {
        navigate_to_user_site(login, password);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("emPower")));
        getDriver1().findElement(By.partialLinkText("emPower")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Lights")));
        getDriver1().findElement(By.id("Lights")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("id('ctl00_phBody_ucLightDeviceRepeater" +
                "Control_SwitchesAndDimmers_rptDevices_ctl00_btnDevicesViewDeviceOn')")));
    }
    public void Request_equipment_list() throws InterruptedException {
        logger.info("Request sensor list and Sensor names");
        getDriver1().findElement(By.id("ctl00_phBody_sensorList_butRequest")).click();
        TimeUnit.SECONDS.sleep(10);
        logger.info("Request equipment list");
        getDriver1().findElement(By.id("ctl00_refresh_sensors_button_btnRefreshPage")).click();
        TimeUnit.SECONDS.sleep(3);
        getDriver1().findElement(By.xpath("//input[@value='Request Sensor Names']")).click();
        TimeUnit.SECONDS.sleep(2);
        try {
        Alert alert = getDriver1().switchTo().alert();
        getDriver1().switchTo().alert().accept();
        alert.accept();
        } catch (Exception e) {}
        TimeUnit.SECONDS.sleep(2);
        getDriver1().findElement(By.id("ctl00_refresh_sensors_button_btnRefreshPage")).click();
        TimeUnit.SECONDS.sleep(5);
        getDriver1().findElement(By.xpath("//input[@value='Request Sensor Names']")).click();
        TimeUnit.SECONDS.sleep(2);
        try {
            Alert alert =  getDriver1().switchTo().alert();
            getDriver1().switchTo().alert();
            alert.accept();
        } catch (Exception e){}
        TimeUnit.SECONDS.sleep(2);
        getDriver1().findElement(By.id("ctl00_refresh_sensors_button_btnRefreshPage")).click();
        TimeUnit.SECONDS.sleep(2);
    }
    public void Sensor_verification(String name, String group, String sensor_type, int number) {
        //number = number of the table row
        String sName = driver1.findElement(By.xpath(".//*[@id='ctl00_phBody_sensorList_AlarmDataGridSensor']/tbody/tr[" + number + "]/td[2]")).getText();
        boolean sensor_name = sName.contains(name);
        if (sensor_name == true){
            System.out.println("Sensor name is " + name + ": " + sensor_name);}
        else {System.out.println("Sensor name is " + name + ": " + sensor_name +" *** "+sName+" *** ");}
        String sGroup = driver1.findElement(By.xpath(".//*[@id='ctl00_phBody_sensorList_AlarmDataGridSensor']/tbody/tr[" + number + "]/td[3]")).getText();
        boolean sensor_group = sGroup.contains(group);
        if (sensor_group == true){
            System.out.println("Sensor group is " + group + ": " + sensor_group);}
        else {System.out.println("Sensor group is " + group + ": " + sensor_group +" *** "+sGroup+" *** ");}
        String sType = driver1.findElement(By.xpath(".//*[@id='ctl00_phBody_sensorList_AlarmDataGridSensor']/tbody/tr[" + number + "]/td[4]")).getText();
        boolean sens_type = sType.contains(sensor_type);
        if (sens_type == true){
            System.out.println("Sensor type is " + sensor_type + ": " + sens_type);}
        else {System.out.println("Sensor type is " + sensor_type + ": " + sens_type +" *** "+sType+" *** ");}
    }
    public void Sensor_verification_full(String name, String group, String sensor_type, String rep_type, String input_status, int number) {
        //number = number of the table row
        String sName = driver1.findElement(By.xpath(".//*[@id='ctl00_phBody_sensorList_AlarmDataGridSensor']/tbody/tr[" + number + "]/td[2]")).getText();
        boolean sensor_name = sName.contains(name);
        if (sensor_name == true) {
            System.out.println("Sensor name is " + name + ": " + sensor_name);}
        else { System.out.println("Sensor name is " + name + ": " + sensor_name +" *** "+sName+" *** ");}
        String sGroup = driver1.findElement(By.xpath(".//*[@id='ctl00_phBody_sensorList_AlarmDataGridSensor']/tbody/tr[" + number + "]/td[3]")).getText();
        boolean sensor_group = sGroup.contains(group);
        if (sensor_group == true) {
            System.out.println("Sensor group is " + group + ": " + sensor_group);}
        else { System.out.println("Sensor group is " + group + ": " + sensor_group +" *** "+sGroup+" *** ");}
        String sType = driver1.findElement(By.xpath(".//*[@id='ctl00_phBody_sensorList_AlarmDataGridSensor']/tbody/tr[" + number + "]/td[4]")).getText();
        boolean sens_type = sType.contains(sensor_type);
        if (sens_type == true) {
            System.out.println("Sensor type is " + sensor_type + ": " + sens_type);}
        else { System.out.println("Sensor type is " + sensor_type + ": " + sens_type +" *** "+sType+" *** ");}
        String reporting_type =  driver1.findElement(By.xpath(".//*[@id='ctl00_phBody_sensorList_AlarmDataGridSensor']/tbody/tr[" + number + "]/td[5]")).getText();
        boolean sensor_reporting_type = reporting_type.contains(rep_type);
        if (sensor_reporting_type == true) {
            System.out.println("Sensor reporting type is " + rep_type + ": " + sensor_reporting_type);}
        else { System.out.println("Sensor reporting type is " + rep_type + ": " + sensor_reporting_type +" *** "+reporting_type+" *** ");}
        String status =  driver1.findElement(By.xpath(".//*[@id='ctl00_phBody_sensorList_AlarmDataGridSensor']/tbody/tr[" + number + "]/td[6]")).getText();
        boolean current_status = status.contains(input_status);
        if (current_status == true) {
            System.out.println("Sensor status is " + input_status + ": " + current_status);}
        else { System.out.println("Sensor status is " + input_status + ": " + current_status +" *** "+status+" *** ");}
    }
    public void send_event_to_sensor(String DLID, String status) throws IOException {
        String event = "shell rfinjector 02 "+DLID+" "+status+" 00";
        mySensors.rt.exec(adbPath + " " + event);
        System.out.println(event);
    }
    public void send_event_to_sensor_grid(String UDID_, String DLID, String status) throws IOException {
        String event = " shell rfinjector 02 "+DLID+" "+status+" 00";
        mySensors.rt.exec(adbPath + " -s " +UDID_ + event);
        System.out.println(event);
    }
    public WebElement fluentWait(String identifier) throws TimeoutException {
        FluentWait<WebDriver> fwait = new FluentWait<>(driver1)
                .withTimeout(30, TimeUnit.SECONDS)
                .pollingEvery(2, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);

        WebElement ele = null;
        // start waiting for given element
        ele = fwait.until(ExpectedConditions.visibilityOfElementLocated(By.id(identifier)));

        return ele;
    }
}