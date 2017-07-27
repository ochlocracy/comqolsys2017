package ADC_Sanity_Test;


import ADC.ADC;
import Panel.*;
import Sensors.Sensors;
import jxl.read.biff.BiffException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.*;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

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

    public void add_primary_call(int zone, int group, int sensor_dec, int sensor_type) throws IOException {
        String add_primary = " shell service call qservice 50 i32 " + zone + " i32 " + group + " i32 " + sensor_dec + " i32 " + sensor_type;
        rt.exec(adbPath + add_primary);
        // shell service call qservice 50 i32 2 i32 10 i32 6619296 i32 1
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

    ////////***** ARM AWAY BY KEY FOB *****/////////
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

        /*** ADC website verification ***/

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

        /*** ADC website verification ***/

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

        /*** ADC website verification ***/

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

    //////////***** ARM STAY BY KEY FOB *****/////////
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

        /*** ADC website verification ***/

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

        /*** ADC website verification ***/

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

        /*** ADC website verification ***/

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
    ////////***** DISARM BY KEY FOB *****/////////
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
/*** ADC website verification ***/

        adc.New_ADC_session(adc.getAccountId());
        Thread.sleep(2000);
        adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("History"))).click();
        Thread.sleep(10000);
        try {
            WebElement history_message = adc.driver1.findElement(By.xpath("//*[contains(text(), ' Keyfob 38 ')]"));
            Assert.assertTrue(history_message.isDisplayed());
            logger.info("Dealer website history: " + history_message.getText());
        } catch (Exception e){
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
/*** ADC website verification ***/

        adc.New_ADC_session(adc.getAccountId());
        Thread.sleep(2000);
        adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("History"))).click();
        Thread.sleep(10000);
        try {
            WebElement history_message = adc.driver1.findElement(By.xpath("//*[contains(text(), ' Keyfob 39 ')]"));
            Assert.assertTrue(history_message.isDisplayed());
            logger.info("Dealer website history: " + history_message.getText());
        } catch (Exception e){
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
/*** ADC website verification ***/

        adc.New_ADC_session(adc.getAccountId());
        Thread.sleep(2000);
        adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("History"))).click();
        Thread.sleep(10000);
        try {
            WebElement history_message = adc.driver1.findElement(By.xpath("//*[contains(text(), ' Keyfob 40 ')]"));
            Assert.assertTrue(history_message.isDisplayed());
            logger.info("Dealer website history: " + history_message.getText());
        } catch (Exception e){
            logger.info("***No such element found!***");
        }
        Thread.sleep(2000);
    }


//////////////////// AirFX - Sensor Addition //////////////////////
   @Test
   public void AirFX_sensor_adding() throws IOException, InterruptedException {
       TimeUnit.SECONDS.sleep(2);
       adc.getDriver1().manage().window().maximize();
       String ADC_URL = "https://alarmadmin.alarm.com/Support/CustomerInfo.aspx?customer_Id="+adc.getAccountId();
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
       Thread.sleep(10000);
       WebElement id = adc.driver1.findElement(By.id("#ctl00_phBody_ucsAddSensor_txtID"));

      // adc.driver1.findElement(By.partialLinkText("Sensors")).click();
       //Thread.sleep(2000);
       //adc.Request_equipment_list();

   }
    @AfterTest
    public void tearDown() throws IOException, InterruptedException {
        driver.quit();

    }

    @AfterMethod
    public void webDriverQuit() {
        adc.driver1.quit();
    }
}
