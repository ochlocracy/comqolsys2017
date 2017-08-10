package ADC_Sanity_Test;


import ADC.ADC;
import Panel.*;
import Sensors.Sensors;
import jxl.read.biff.BiffException;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.*;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.ui.Select;

public class Misc extends Setup {

    public Misc() throws IOException, BiffException {
    }

    String page_name = " *** Miscellanies *** ";
    Logger logger = Logger.getLogger(page_name);
    Sensors sensors = new Sensors();
    ADC adc = new ADC();

    private String keyfobAway = "04 04";
    private String keyfobStay = "04 01";
    private String keyfobDisarm = "08 01";
    private String Motion_Activated = "02 01";
    private String Motion_Restore = "00 01";
    private String Motion_Tamper = "01 01";
    private String D_Open = "06 00";
    private String D_Restore = "04 00";
    private String D_Tamper = "01 00";

    public void add_primary_call(int zone, int group, int sensor_dec, int sensor_type) throws IOException {
        String add_primary = " shell service call qservice 50 i32 " + zone + " i32 " + group + " i32 " + sensor_dec + " i32 " + sensor_type;
        rt.exec(adbPath + add_primary);
        // shell service call qservice 50 i32 2 i32 10 i32 6619296 i32 1
    }

    public void delete_from_primary(int zone) throws IOException, InterruptedException {
        String deleteFromPrimary = " shell service call qservice 51 i32 " + zone;
        rt.exec(adbPath + deleteFromPrimary);
        System.out.println(deleteFromPrimary);
    }

    public void request_equipment_list() throws IOException, InterruptedException {
        adc.New_ADC_session(adc.getAccountId());
        Thread.sleep(2000);
        adc.driver1.findElement(By.partialLinkText("Sensors")).click();
        Thread.sleep(2000);
        adc.Request_equipment_list();
        //  Thread.sleep(60000);
    }

    @BeforeTest
    public void capabilities_setup() throws Exception {
        setup_driver(get_UDID(), "http://127.0.1.1", "4723");
        setup_logger(page_name);
    }

    @BeforeMethod
    public void webDriver() {
        adc.webDriverSetUp();
    }

    @Test
    public void addSensors() throws IOException, InterruptedException {
        add_primary_call(38, 1, 6619386, 102);
        add_primary_call(39, 6, 6619387, 102);
        add_primary_call(40, 4, 6619388, 102);
        Thread.sleep(2000);

        adc.New_ADC_session(adc.getAccountId());
        Thread.sleep(2000);
        adc.driver1.findElement(By.partialLinkText("Sensors")).click();
        Thread.sleep(2000);
        adc.Request_equipment_list();
    }

    /***** ARM AWAY BY KEY FOB *****/
    @Test(dependsOnMethods = {"addSensors"}, retryAnalyzer = RetryAnalizer.class)
    public void ArmAway_by_keyfob_group1() throws Exception {
        Home_Page home = PageFactory.initElements(driver, Home_Page.class);
        logger.info("****************************ARM AWAY BY KEY FOB****************************");
        logger.info("Keyfob group 1: can ArmStay-ArmAway-Disarm, panic = Police");
        logger.info("Arm Away by keyfob");
        Thread.sleep(10000);
        sensors.primary_call("65 00 AF", keyfobAway);
        Thread.sleep(3000);
        verify_armaway();
        Thread.sleep(3000);
        home.ArwAway_State.click();
        enter_default_user_code();
        adc.New_ADC_session(adc.getAccountId());
        Thread.sleep(3000);
        adc.driver1.findElement(By.partialLinkText("History")).click();
        Thread.sleep(10000);
        try {
            WebElement history_message_alarm = adc.driver1.findElement(By.xpath("//*[contains(text(), 'Keyfob 38 ')]"));
            Assert.assertTrue(history_message_alarm.isDisplayed());
            {
                System.out.println("Dealer website history: " + " " + history_message_alarm.getText());
            }
        } catch (Exception e) {
            System.out.println("No such element found!!!");
        }
        Thread.sleep(3000);
    }

    @Test(dependsOnMethods = {"addSensors"}, retryAnalyzer = RetryAnalizer.class)
    public void ArmAway_by_keyfob_group6() throws Exception {
        Home_Page home = PageFactory.initElements(driver, Home_Page.class);
        logger.info("****************************ARM AWAY BY KEY FOB****************************");
        logger.info("Keyfob group 6: can ArmStay-ArmAway-Disarm, panic = Mobile Auxiliary");
        logger.info("Arm Away by keyfob");
        Thread.sleep(10000);
        sensors.primary_call("65 00 BF", keyfobAway);
        Thread.sleep(3000);
        verify_armaway();
        Thread.sleep(3000);
        home.ArwAway_State.click();
        enter_default_user_code();
        adc.New_ADC_session(adc.getAccountId());
        Thread.sleep(3000);
        adc.driver1.findElement(By.partialLinkText("History")).click();
        Thread.sleep(10000);
        try {
            WebElement history_message_alarm = adc.driver1.findElement(By.xpath("//*[contains(text(), 'Keyfob 39 ')]"));
            Assert.assertTrue(history_message_alarm.isDisplayed());
            {
                System.out.println("Dealer website history: " + " " + history_message_alarm.getText());
            }
        } catch (Exception e) {
            System.out.println("No such element found!!!");
        }
        Thread.sleep(3000);
    }

    @Test(dependsOnMethods = {"addSensors"}, retryAnalyzer = RetryAnalizer.class)
    public void ArmAway_by_keyfob_group4() throws Exception {
        Home_Page home = PageFactory.initElements(driver, Home_Page.class);
        logger.info("****************************ARM AWAY BY KEY FOB****************************");
        logger.info("Keyfob group 4: can ArmStay-ArmAway-Disarm, panic = Fixed Auxiliary");
        logger.info("Arm Away by keyfob");
        Thread.sleep(10000);
        sensors.primary_call("65 00 CF", keyfobAway);
        Thread.sleep(3000);
        verify_armaway();
        Thread.sleep(3000);
        home.ArwAway_State.click();
        enter_default_user_code();
        adc.New_ADC_session(adc.getAccountId());
        Thread.sleep(3000);
        adc.driver1.findElement(By.partialLinkText("History")).click();
        Thread.sleep(10000);
        try {
            WebElement history_message_alarm = adc.driver1.findElement(By.xpath("//*[contains(text(), 'Keyfob 40 ')]"));
            Assert.assertTrue(history_message_alarm.isDisplayed());
            {
                System.out.println("Dealer website history: " + " " + history_message_alarm.getText());
            }
        } catch (Exception e) {
            System.out.println("No such element found!!!");
        }
        Thread.sleep(3000);
    }

    /***** ARM STAY BY KEY FOB *****/
    @Test(dependsOnMethods = {"addSensors"}, retryAnalyzer = RetryAnalizer.class)
    public void ArmStay_by_keyfob_group1() throws Exception {
        Home_Page home = PageFactory.initElements(driver, Home_Page.class);
        logger.info("****************************ARM STAY BY KEY FOB****************************");
        logger.info("Keyfob group 1: can ArmStay-ArmAway-Disarm, panic = Police");
        logger.info("Arm Stay by keyfob");
        Thread.sleep(10000);
        sensors.primary_call("65 00 AF", keyfobStay);
        Thread.sleep(3000);
        verify_armstay();
        Thread.sleep(3000);
        home.DISARM.click();
        enter_default_user_code();
        adc.New_ADC_session(adc.getAccountId());
        Thread.sleep(3000);
        adc.driver1.findElement(By.partialLinkText("History")).click();
        Thread.sleep(10000);
        try {
            WebElement history_message_alarm = adc.driver1.findElement(By.xpath("//*[contains(text(), 'Keyfob 38 ')]"));
            Assert.assertTrue(history_message_alarm.isDisplayed());
            {
                System.out.println("Dealer website history: " + " " + history_message_alarm.getText());
            }
        } catch (Exception e) {
            System.out.println("No such element found!!!");
        }
        Thread.sleep(3000);
    }

    @Test(dependsOnMethods = {"addSensors"}, retryAnalyzer = RetryAnalizer.class)
    public void ArmStay_by_keyfob_group6() throws Exception {
        Home_Page home = PageFactory.initElements(driver, Home_Page.class);
        logger.info("****************************ARM STAY BY KEY FOB****************************");
        logger.info("Keyfob group 6: can ArmStay-ArmAway-Disarm, panic = Mobile Auxiliary");
        logger.info("Arm Stay by keyfob");
        Thread.sleep(10000);
        sensors.primary_call("65 00 BF", keyfobStay);
        Thread.sleep(3000);
        verify_armstay();
        Thread.sleep(3000);
        home.DISARM.click();
        enter_default_user_code();
        adc.New_ADC_session(adc.getAccountId());
        Thread.sleep(3000);
        adc.driver1.findElement(By.partialLinkText("History")).click();
        Thread.sleep(10000);
        try {
            WebElement history_message_alarm = adc.driver1.findElement(By.xpath("//*[contains(text(), 'Keyfob 39 ')]"));
            Assert.assertTrue(history_message_alarm.isDisplayed());
            {
                System.out.println("Dealer website history: " + " " + history_message_alarm.getText());
            }
        } catch (Exception e) {
            System.out.println("No such element found!!!");
        }
        Thread.sleep(3000);
    }

    @Test(dependsOnMethods = {"addSensors"}, retryAnalyzer = RetryAnalizer.class)
    public void ArmStay_by_keyfob_group4() throws Exception {
        Home_Page home = PageFactory.initElements(driver, Home_Page.class);
        logger.info("****************************ARM STAY BY KEY FOB****************************");
        logger.info("Keyfob group 6: can ArmStay-ArmAway-Disarm, panic = Fixed Auxiliary");
        logger.info("Arm Stay by keyfob");
        Thread.sleep(10000);
        sensors.primary_call("65 00 CF", keyfobStay);
        Thread.sleep(3000);
        verify_armstay();
        Thread.sleep(3000);
        home.DISARM.click();
        enter_default_user_code();
        adc.New_ADC_session(adc.getAccountId());
        Thread.sleep(3000);
        adc.driver1.findElement(By.partialLinkText("History")).click();
        Thread.sleep(10000);
        try {
            WebElement history_message_alarm = adc.driver1.findElement(By.xpath("//*[contains(text(), 'Keyfob 40 ')]"));
            Assert.assertTrue(history_message_alarm.isDisplayed());
            {
                System.out.println("Dealer website history: " + " " + history_message_alarm.getText());
            }
        } catch (Exception e) {
            System.out.println("No such element found!!!");
        }
        Thread.sleep(3000);
    }

    /**** DISARM BY KEY FOB *****/
    //normal delay 30, 31; long delay 32, 33
    @Test(dependsOnMethods = {"addSensors"}, retryAnalyzer = RetryAnalizer.class)
    public void Disarm_by_keyfob_group1() throws Exception {
        Home_Page home = PageFactory.initElements(driver, Home_Page.class);
        logger.info("****************************DISARM BY KEY FOB****************************");
        Thread.sleep(10000);
        logger.info("**** DISARM BY 1-group KEY FOB ****");
        Thread.sleep(2000);
        home.DISARM.click();
        Thread.sleep(2000);
        home.ARM_AWAY.click();
        Thread.sleep(33000);
        verify_armaway();
        sensors.primary_call("65 00 AF", keyfobDisarm);
        Thread.sleep(5000);
        verify_disarm();
        adc.New_ADC_session(adc.getAccountId());
        Thread.sleep(2000);
        adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("History"))).click();
        Thread.sleep(10000);
        try {
            WebElement history_message = adc.driver1.findElement(By.xpath("//*[contains(text(), ' Keyfob 38 ')]"));
            Assert.assertTrue(history_message.isDisplayed());
            logger.info("Dealer website history: " + history_message.getText());
        } catch (Exception e) {
            logger.info("***No such element found!***");
        }
        Thread.sleep(2000);
    }

    @Test(dependsOnMethods = {"addSensors"}, retryAnalyzer = RetryAnalizer.class)
    public void Disarm_by_keyfob_group6() throws Exception {
        Home_Page home = PageFactory.initElements(driver, Home_Page.class);
        logger.info("****************************DISARM BY KEY FOB****************************");
        Thread.sleep(10000);
        logger.info("**** DISARM BY 6-group KEY FOB ****");
        Thread.sleep(2000);
        home.DISARM.click();
        Thread.sleep(2000);
        home.ARM_AWAY.click();
        Thread.sleep(33000);
        verify_armaway();
        sensors.primary_call("65 00 BF", keyfobDisarm);
        Thread.sleep(5000);
        verify_disarm();
        adc.New_ADC_session(adc.getAccountId());
        Thread.sleep(2000);
        adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("History"))).click();
        Thread.sleep(10000);
        try {
            WebElement history_message = adc.driver1.findElement(By.xpath("//*[contains(text(), ' Keyfob 39 ')]"));
            Assert.assertTrue(history_message.isDisplayed());
            logger.info("Dealer website history: " + history_message.getText());
        } catch (Exception e) {
            logger.info("***No such element found!***");
        }
        Thread.sleep(2000);
    }

    @Test(dependsOnMethods = {"addSensors"}, retryAnalyzer = RetryAnalizer.class)
    public void Disarm_by_keyfob_group4() throws Exception {
        Home_Page home = PageFactory.initElements(driver, Home_Page.class);
        logger.info("****************************DISARM BY KEY FOB****************************");
        Thread.sleep(10000);
        logger.info("**** DISARM BY 4-group KEY FOB ****");
        Thread.sleep(2000);
        home.DISARM.click();
        Thread.sleep(2000);
        home.ARM_AWAY.click();
        Thread.sleep(33000);
        verify_armaway();
        sensors.primary_call("65 00 CF", keyfobDisarm);
        Thread.sleep(5000);
        verify_disarm();
        adc.New_ADC_session(adc.getAccountId());
        Thread.sleep(2000);
        adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("History"))).click();
        Thread.sleep(10000);
        try {
            WebElement history_message = adc.driver1.findElement(By.xpath("//*[contains(text(), ' Keyfob 40 ')]"));
            Assert.assertTrue(history_message.isDisplayed());
            logger.info("Dealer website history: " + history_message.getText());
        } catch (Exception e) {
            logger.info("***No such element found!***");
        }
        Thread.sleep(2000);
    }


    /**** AirFX - Sensor Addition ****/
    @Test (priority = 1)
    public void AirFX_sensor_addition() throws IOException, InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        String ID = "1";
        String DL = "6500A0";
        String sensor1 = "sensor1";
        adc.getDriver1().manage().window().maximize();
        String ADC_URL = "https://alarmadmin.alarm.com/Support/CustomerInfo.aspx?customer_Id=" + adc.getAccountId();
        adc.getDriver1().get(ADC_URL);
        String login = "qapple";
        String password = "qolsys123";
        Thread.sleep(2000);
        adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtUsername")));
        adc.getDriver1().findElement(By.id("txtUsername")).sendKeys(login);
        adc.getDriver1().findElement(By.id("txtPassword")).sendKeys(password);
        adc.getDriver1().findElement(By.id("butLogin")).click();
        Thread.sleep(2000);
        adc.getDriver1().get("https://alarmadmin.alarm.com/Support/AirFx/rt_AddSensor.aspx");
        Thread.sleep(2000);
        adc.getDriver1().findElement(By.id("ctl00_phBody_ucsAddSensor_txtID")).sendKeys(ID); //ID
        Select type_menu = new Select(adc.getDriver1().findElement(By.id("ctl00_phBody_ucsAddSensor_ddlSensorType")));
        type_menu.selectByVisibleText("Door/Window");
        Select group_menu = new Select(adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_phBody_ucsAddSensor_ddlSensorGroup_IQpanel"))));
        group_menu.selectByVisibleText("12");
        Thread.sleep(1000);
        adc.getDriver1().findElement(By.id("ctl00_phBody_ucsAddSensor_UcEligibleSensorName1_txtSN")).sendKeys(sensor1);
        adc.getDriver1().findElement(By.id("ctl00_phBody_ucsAddSensor_txtDL")).sendKeys(DL);
        Select RF_type_menu = new Select(adc.getDriver1().findElement(By.id("ctl00_phBody_ucsAddSensor_ddlRFType")));
        RF_type_menu.selectByVisibleText("GE 319 RF");
        Thread.sleep(1000);
        adc.getDriver1().findElement(By.id("ctl00_phBody_ucsAddSensor_btnAdd")).click();
        logger.info("Sensor1 added successfully");
        Thread.sleep(1000);
        adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("History"))).click();
        logger.info("Checking for the notification in the Event History of Dealer Site ");
        Thread.sleep(2000);
        try {
            WebElement history_message = adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), ' WirelessSensor #1 ')]")));
            Assert.assertTrue(history_message.isDisplayed());
            logger.info("Dealer website history: " + history_message.getText());
        } catch (Exception e) {
            logger.info("***No such element found!***");
        }
        Thread.sleep(2000);
        sensors.primary_call("65 00 0A", D_Tamper);
        logger.info("sensor1 Tampered");
        Thread.sleep(2000);
        sensors.primary_call("65 00 0A", D_Restore);
        logger.info("sensor1 Restored");
        Thread.sleep(2000);
        adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_phBody_dgEvents_ctl103_refreshButton"))).click();
        Thread.sleep(3000);
        try {
            WebElement history_message = adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'Sensor 1 Tamper**')]")));
            Assert.assertTrue(history_message.isDisplayed());
            logger.info("Dealer website history: " + history_message.getText());
        } catch (Exception e) {
            logger.info("***No such element found!***");
        }
        try {
            WebElement history_message1 = adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'Sensor 1 End of tamper')]")));
            Assert.assertTrue(history_message1.isDisplayed());
            logger.info("Dealer website history: " + history_message1.getText());
        } catch (Exception e) {
            logger.info("***No such element found!***");
        }
        Thread.sleep(4000);
        Slide_Menu menu = PageFactory.initElements(driver, Slide_Menu.class);
        Settings_Page settings = PageFactory.initElements(driver, Settings_Page.class);
        Status_page_elements tabs = PageFactory.initElements(driver, Status_page_elements.class);
        logger.info("Checking for the notification in the Event History of Panel ");
        Thread.sleep(2000);
        menu.Slide_menu_open.click();
        Thread.sleep(2000);
        menu.Settings.click();
        settings.STATUS.click();
        Thread.sleep(2000);
        tabs.HISTORY_tab.click();
        Thread.sleep(4000);
        List<WebElement> events = driver.findElements(By.id("com.qolsys:id/textView3"));
        String event;
        String str;
        str = events.get(3).getText();
        event = "Active";
        if (str.equals(event)) {
            logger.info("sensor1 was added and the sensor1 status is " + str);
        }
        else if (!event.equals(str)) {
            logger.info("***No such element found!***");}
        str = events.get(2).getText();
        event = "Tampered";
        if (str.equals(event)) {
            logger.info("The sensor1 status is " + str);
        }
        else if (!event.equals(str)) {
            logger.info("***No such element found!***");}
        str = events.get(1).getText();
        event = "Closed";
        if (str.equals(event)) {
            logger.info("The sensor1 status is " + str);
        }
        else if (!event.equals(str)) {
            logger.info("***No such element found!***");}
    }

    /**** AirFX - Change Sensor Type and Sensor Group ****/
    @Test (priority = 2)
    public void AirFX_change_sensor_group() throws InterruptedException, IOException {
        adc.getDriver1().manage().window().maximize();
        String ADC_URL = "https://alarmadmin.alarm.com/Support/CustomerInfo.aspx?customer_Id=" + adc.getAccountId();
        adc.getDriver1().get(ADC_URL);
        String login = "qapple";
        String password = "qolsys123";
        Thread.sleep(2000);
        adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtUsername")));
        adc.getDriver1().findElement(By.id("txtUsername")).sendKeys(login);
        adc.getDriver1().findElement(By.id("txtPassword")).sendKeys(password);
        adc.getDriver1().findElement(By.id("butLogin")).click();
        Thread.sleep(2000);
        adc.getDriver1().get("https://alarmadmin.alarm.com/Support/AirFx/rt_ChangeSensorGroup.aspx");
        Thread.sleep(2000);
        Select type_menu = new Select(adc.getDriver1().findElement(By.id("ctl00_phBody_dgDevices_ctl02_ddlDeviceType")));
        type_menu.selectByVisibleText("Smoke Detector");
        Thread.sleep(2000);
        Select group_menu = new Select(adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_phBody_dgDevices_ctl02_DrpNewSensorGroup_IQpanel"))));
        group_menu.selectByVisibleText("26");
        Thread.sleep(2000);
        adc.getDriver1().findElement(By.id("ctl00_phBody_btnSubmit")).click();
        Thread.sleep(4000);
        try {
            Alert alert = adc.driver1.switchTo().alert();
            adc.driver1.switchTo().alert().accept();
            alert.accept();
        } catch (NoAlertPresentException Ex) {
        }
        logger.info("Sensor group and type were changed successfully");
        Thread.sleep(2000);
        adc.driver1.get("https://alarmadmin.alarm.com/Support/Commands/GetSensorNames.aspx");
        adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_phBody_sensorList_butRequest"))).click();
        Thread.sleep(3000);
        adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("History"))).click();
        logger.info("Checking for the notification in the Event History of Dealer Site ");
        Thread.sleep(2000);
        adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_phBody_dgEvents_ctl103_refreshButton"))).click();
        Thread.sleep(3000);
        try {
            WebElement history_message = adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'Change Sensor Group ')]")));
            /* 	Change Sensor Group (Rep: qapple) [(Acknowledged at 8/3/2017 4:43:10 pm) ACK Token: 49] */
            Assert.assertTrue(history_message.isDisplayed());
            logger.info("Dealer website history: " + history_message.getText());
        } catch (Exception e) {
            logger.info("***No such element found!***");
        }
        Thread.sleep(4000);
        Slide_Menu menu = PageFactory.initElements(driver, Slide_Menu.class);
        Settings_Page settings = PageFactory.initElements(driver, Settings_Page.class);
        Status_page_elements tabs = PageFactory.initElements(driver, Status_page_elements.class);
        logger.info("Checking for the notification in the Event History of Panel ");
        Thread.sleep(2000);
        menu.Slide_menu_open.click();
        Thread.sleep(2000);
        menu.Settings.click();
        settings.STATUS.click();
        Thread.sleep(2000);
        tabs.HISTORY_tab.click();
        Thread.sleep(4000);
        List<WebElement> events = driver.findElements(By.id("com.qolsys:id/textView3"));
        String event;
        String str;
        str = events.get(1).getText();
        event = "Group Changed (Door/Window-12-Entry-Exit-Long Delay)";
        if (str.equals(event)) {
            logger.info("sensor1 : " + str);
        }
        else if (!event.equals(str)) {
            logger.info("***No such element found!***");}
    }


    /**** AirFX - Change Sensor Name ****/
    @Test (priority = 3)
    public void AirFX_change_sensor_name() throws InterruptedException, IOException {
        adc.getDriver1().manage().window().maximize();
        String ADC_URL = "https://alarmadmin.alarm.com/Support/CustomerInfo.aspx?customer_Id=" + adc.getAccountId();
        String new_name = "New custom name";
        adc.getDriver1().get(ADC_URL);
        String login = "qapple";
        String password = "qolsys123";
        Thread.sleep(2000);
        adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtUsername")));
        adc.getDriver1().findElement(By.id("txtUsername")).sendKeys(login);
        adc.getDriver1().findElement(By.id("txtPassword")).sendKeys(password);
        adc.getDriver1().findElement(By.id("butLogin")).click();
        Thread.sleep(2000);
        adc.getDriver1().get("https://alarmadmin.alarm.com/Support/AirFx/rt_ChangeSensorName.aspx");
        Thread.sleep(2000);
        adc.getDriver1().findElement(By.id("ctl00_phBody_dgDevices_ctl02_UcEligibleSensorName1_txtSN")).sendKeys(new_name);
        Thread.sleep(1000);
        adc.getDriver1().findElement(By.id("GVrow0")).click();
        Thread.sleep(500);
        adc.getDriver1().findElement(By.id("ctl00_phBody_btnSubmit")).click();
        Thread.sleep(2000);
        try {
            Alert alert = adc.driver1.switchTo().alert();
            adc.driver1.switchTo().alert().accept();
            alert.accept();
        } catch (NoAlertPresentException Ex) {
        }
        logger.info("Sensor name was changed successfully");
        Thread.sleep(2000);
        adc.driver1.get("https://alarmadmin.alarm.com/Support/Commands/GetSensorNames.aspx");
        adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_phBody_sensorList_butRequest"))).click();
        Thread.sleep(3000);
        adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("History"))).click();
        logger.info("Checking for the notification in the Event History of Dealer Site ");
        Thread.sleep(2000);
        adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_phBody_dgEvents_ctl103_refreshButton"))).click();
        Thread.sleep(3000);
        try {
            WebElement history_message = adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'Set Sensor Names(s)')]")));
            Assert.assertTrue(history_message.isDisplayed());
            logger.info("Dealer website history: " + history_message.getText());
        } catch (Exception e) {
            logger.info("***No such element found!***");
        }
        Thread.sleep(4000);
        Slide_Menu menu = PageFactory.initElements(driver, Slide_Menu.class);
        Settings_Page settings = PageFactory.initElements(driver, Settings_Page.class);
        Status_page_elements tabs = PageFactory.initElements(driver, Status_page_elements.class);
        logger.info("Checking for the notification in the Event History of Panel ");
        Thread.sleep(4000);
        menu.Slide_menu_open.click();
        Thread.sleep(2000);
        menu.Settings.click();
        settings.STATUS.click();
        Thread.sleep(2000);
        tabs.HISTORY_tab.click();
        Thread.sleep(4000);
        List<WebElement> events = driver.findElements(By.id("com.qolsys:id/textView3"));
        String event;
        String str;
        str = events.get(1).getText();
        event = "Name Changed (New custom name)";
        if (str.equals(event)) {
            System.out.println("sensor1 : " + str);
        }
        else if (!event.equals(str)) {
            System.out.println("***No such element found!***");}
         }

    /**** AirFX - Sensor Deletion ****/
    @Test (priority = 4)
    public void AirFX_sensor_deletion() throws InterruptedException, IOException {
        adc.getDriver1().manage().window().maximize();
        String ADC_URL = "https://alarmadmin.alarm.com/Support/CustomerInfo.aspx?customer_Id=" + adc.getAccountId();
        String new_name = "New custom name";
        adc.getDriver1().get(ADC_URL);
        String login = "qapple";
        String password = "qolsys123";
        Thread.sleep(2000);
        adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtUsername")));
        adc.getDriver1().findElement(By.id("txtUsername")).sendKeys(login);
        adc.getDriver1().findElement(By.id("txtPassword")).sendKeys(password);
        adc.getDriver1().findElement(By.id("butLogin")).click();
        Thread.sleep(2000);
        adc.getDriver1().get("https://alarmadmin.alarm.com/Support/AirFx/rt_DeleteSensor.aspx");
        Thread.sleep(2000);
        adc.getDriver1().findElement(By.id("ctl00_phBody_ucDelete_dgDevices_ctl02_chkDelete")).click();
        Thread.sleep(2000);
        adc.getDriver1().findElement(By.id("ctl00_phBody_ucDelete_btnSubmit")).click();
        Thread.sleep(4000);
        try {
            Alert alert = adc.driver1.switchTo().alert();
            adc.driver1.switchTo().alert().accept();
            alert.accept();
        } catch (NoAlertPresentException Ex) {
        }
        logger.info("Sensor was deleted successfully");
        Thread.sleep(2000);
        adc.driver1.get("https://alarmadmin.alarm.com/Support/Commands/GetSensorNames.aspx");
        adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_phBody_sensorList_butRequest"))).click();
        Thread.sleep(3000);
        adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("History"))).click();
        logger.info("Checking for the notification in the Event History of Dealer Site ");
        Thread.sleep(30000);
        adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_phBody_dgEvents_ctl103_refreshButton"))).click();
        Thread.sleep(3000);
        try {
            WebElement history_message = adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'Delete WirelessSensor #1 ')]")));
            Assert.assertTrue(history_message.isDisplayed());
            logger.info("Dealer website history: " + history_message.getText());
        } catch (Exception e) {
            logger.info("***No such element found!***");
        }
        Thread.sleep(4000);
        Slide_Menu menu = PageFactory.initElements(driver, Slide_Menu.class);
        Settings_Page settings = PageFactory.initElements(driver, Settings_Page.class);
        Status_page_elements tabs = PageFactory.initElements(driver, Status_page_elements.class);
        logger.info("Checking for the notification in the Event History of Panel ");
        Thread.sleep(2000);
        menu.Slide_menu_open.click();
        Thread.sleep(2000);
        menu.Settings.click();
        settings.STATUS.click();
        Thread.sleep(2000);
        tabs.HISTORY_tab.click();
        Thread.sleep(2000);
        List<WebElement> events = driver.findElements(By.id("com.qolsys:id/textView3"));
        String event;
        String str;
        str = events.get(1).getText();
        event = "Sensor Deleted";
        if (str.equals(event)) {
            System.out.println("sensor1 : " + str);
        }
        else if (!event.equals(str)) {
            System.out.println("***No such element found!***");}
    }



    /**** Panel History ****/
    @Test
    public void Panel_history() throws IOException, InterruptedException {

       /* Thread.sleep(2000);
        sensors.primary_call("65 00 0A", D_Tamper);
        logger.info("sensor1 Tampered");
        Thread.sleep(2000);
        sensors.primary_call("65 00 0A", D_Restore);
        logger.info("sensor1 Restored");
        sensors.primary_call("65 00 0A", D_Open);
        logger.info("sensor1 Opened");
        sensors.primary_call("65 00 0A", D_Restore);
        logger.info("sensor1 Closed");
        Thread.sleep(2000);*/
        Slide_Menu menu = PageFactory.initElements(driver, Slide_Menu.class);
        Settings_Page settings = PageFactory.initElements(driver, Settings_Page.class);
        Status_page_elements tabs = PageFactory.initElements(driver, Status_page_elements.class);
        logger.info("Checking for the notification in the Event History of Panel ");
        Thread.sleep(2000);
        menu.Slide_menu_open.click();
        Thread.sleep(2000);
        menu.Settings.click();
        settings.STATUS.click();
        Thread.sleep(1000);

        tabs.HISTORY_tab.click();
        Thread.sleep(1000);
        //  List<WebElement> devices_name = driver.findElements(By.id("com.qolsys:id/textView2"));
        //  for (int j = 1; j < devices_name.size(); j++) {
        //     logger.info("Sensor name: " + devices_name.get(j).getText());
        //  }
        List<WebElement> events = driver.findElements(By.id("com.qolsys:id/textView3"));
        String event;
        String str;
        //for (int i = 1; i < events.size(); i++) {
            //logger.info("History message: " + events.get(i).getText());
            //

            str = events.get(1).getText();
            event = "Openrf";
            if (str.equals(event)) {
                System.out.println("sensor1 " + str);
                        }
            else if (!event.equals(str)) {
           System.out.println("***No such element found!***");
        }

    }




   @AfterTest
       public void tearDown () throws IOException, InterruptedException {
           log.endTestCase(page_name);
           driver.quit();
           for (int i= 40; i>37; i--) {
           delete_from_primary(i);
           }
       }

    @AfterMethod
    public void webDriverQuit(){
        adc.driver1.quit();
    }
       }