package ADC_Sanity_Test;

import ADC.ADC;
import Panel.Advanced_Settings_Page;
import Panel.Installation_Page;
import Panel.Security_Arming_Page;
import Panel.Setup;
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

 /*** PRECONDITIONS: Disable SIA limits, set Entry-Exit Delay time to 30, 31, 32, 33 sec; Disable ArmStay No-Delay setting ***/

public class ArmedStay extends Setup {

    String page_name = "Arm Stay mode";
    Logger logger = Logger.getLogger(page_name);
    Sensors sensors = new Sensors();
    ADC adc = new ADC();

    private int Normal_Exit_Delay = 30000;
    private String open = "06 00";
    private String close = "04 00";

    public ArmedStay() throws IOException, BiffException {}

    public void add_primary_call(int zone, int group, int sensor_dec, int sensor_type) throws IOException {
        String add_primary = " shell service call qservice 50 i32 " + zone + " i32 " + group + " i32 " + sensor_dec + " i32 " + sensor_type;
        rt.exec(adbPath + add_primary);
        // shell service call qservice 50 i32 2 i32 10 i32 6619296 i32 1
    }
     public void delete_from_primary(int zone) throws IOException, InterruptedException {
         String deleteFromPrimary = " shell service call qservice 51 i32 " + zone;
         rt.exec(adbPath + deleteFromPrimary);
         System.out.println(deleteFromPrimary);}

    @BeforeTest
    public void capabilities_setup() throws Exception {
        setup_driver(get_UDID(),"http://127.0.1.1", "4723");
        setup_logger(page_name);
    }
    @BeforeMethod
    public  void webDriver(){
        adc.webDriverSetUp();
    }
    public void ArmStay_NoDelay_Setting () throws InterruptedException {
        Security_Arming_Page arming = PageFactory.initElements(driver, Security_Arming_Page.class);
        Advanced_Settings_Page adv = PageFactory.initElements(driver, Advanced_Settings_Page.class);
        Installation_Page inst = PageFactory.initElements(driver, Installation_Page.class);
        adv.INSTALLATION.click();
        inst.SECURITY_AND_ARMING.click();
        Thread.sleep(3000);
        swipe_vertical();
        arming.Arm_Stay_No_Delay.click();
        Thread.sleep(2000);
    }

    @Test
    public void addSensors() throws IOException, InterruptedException {
        Thread.sleep(2000);
        add_primary_call(1, 10, 6619296, 1);
        add_primary_call(2, 12, 6619297, 1);
        add_primary_call(3, 13, 6619298, 1);
        add_primary_call(4, 14, 6619299, 1);
        add_primary_call(5, 16, 6619300, 1);
        add_primary_call(6, 8, 6619301, 1);
        add_primary_call(7, 9, 6619302, 1);
        add_primary_call(8, 25, 6619303, 1);

        adc.New_ADC_session(adc.getAccountId());
        Thread.sleep(2000);
        adc.driver1.findElement(By.partialLinkText("Sensors")).click();
        Thread.sleep(2000);
        adc.Request_equipment_list();
    }

    @Test (dependsOnMethods = {"addSensors"}, retryAnalyzer = RetryAnalizer.class)
    public void ArmStay_Open_Close_group_10_during_Exit_Delay() throws Exception {
        logger.info("ArmStay -Open/Close Group 10 contact sensor during exit delay");
        ARM_STAY();
        Thread.sleep(10000);
        logger.info("Open/Close a sensor");
        sensors.primary_call("65 00 0A", open);
        Thread.sleep(2000);
        sensors.primary_call("65 00 0A", close);
        Thread.sleep(Normal_Exit_Delay);
        verify_armstay();
        Thread.sleep(10000);
        DISARM();

        /*** ADC website verification ***/

        adc.New_ADC_session(adc.getAccountId());
        adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("History"))).click();
        Thread.sleep(10000);
        try {
            WebElement history_message = adc.driver1.findElement(By.xpath("//*[contains(text(), 'Door/Window 1  (Sensor 1) Opened/Closed')]"));
            Assert.assertTrue(history_message.isDisplayed());{
                logger.info("Pass: message is displayed " + history_message.getText());
            }
        }catch (Exception e){
            logger.info("***No such element found!***");
        }
        Thread.sleep(5000);
    }

    @Test(priority = 1, retryAnalyzer = RetryAnalizer.class)
    public void ArmStay_Open_Close_group_12_during_Exit_Delay() throws Exception {
        logger.info("ArmStay -Open/Close Group 12 contact sensor during exit delay");
        ARM_STAY();
        Thread.sleep(10000);
        logger.info("Open/Close a sensor");
        sensors.primary_call("65 00 1A", open);
        Thread.sleep(2000);
        sensors.primary_call("65 00 1A", close);
        Thread.sleep(Normal_Exit_Delay);
        verify_armstay();
        Thread.sleep(3000);
        DISARM();

        adc.New_ADC_session(adc.getAccountId());
        Thread.sleep(2000);
        adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("History"))).click();
        Thread.sleep(10000);
        try{
            WebElement history_message = adc.driver1.findElement(By.xpath("//*[contains(text(), 'Door/Window 2  (Sensor 2) Opened/Closed')]"));
            Assert.assertTrue(history_message.isDisplayed());
            logger.info("Pass: message is displayed "+history_message.getText());
        } catch (Exception e){
            logger.info("***No such element found!***");
        }
        Thread.sleep(2000);
    }
    @Test(priority = 2, retryAnalyzer = RetryAnalizer.class)
    public void ArmStay_Open_Close_group_13_during_Exit_Delay() throws Exception {
        logger.info("ArmStay -Open/Close Group 13 contact sensor during exit delay");
        ARM_STAY();
        Thread.sleep(10000);
        logger.info("Open/Close a sensor");
        sensors.primary_call("65 00 2A", open);
        Thread.sleep(2000);
        sensors.primary_call("65 00 2A", close);
        Thread.sleep(Normal_Exit_Delay);
        verify_in_alarm();
        Thread.sleep(2000);
        enter_default_user_code();

        /*** ADC website verification ***/

        adc.New_ADC_session(adc.getAccountId());
        Thread.sleep(2000);
        adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("History"))).click();
        adc.driver1.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        try {
            WebElement history_message = adc.driver1.findElement(By.xpath("//*[contains(text(), 'Door/Window 3  (Sensor 3) Opened/Closed')]"));
            WebElement history_message_alarm = adc.driver1.findElement(By.xpath("//*[contains(text(), 'Door/Window 3 (Sensor 3) Alarm')]"));
            Assert.assertTrue(history_message.isDisplayed() && history_message_alarm.isDisplayed()); {
               logger.info("Pass: message is displayed " + history_message.getText()+" " + history_message_alarm.getText());
            }
            }catch (Exception e){
            logger.info("***No such element found!***");
        }
            Thread.sleep(2000);
        }

    @Test(priority = 3, retryAnalyzer = RetryAnalizer.class)
    public void  ArmStay_Open_Close_group_14_during_Exit_Delay() throws Exception {
        logger.info("ArmStay -Open/Close Group 14 contact sensor during exit delay");
        ARM_STAY();
        Thread.sleep(10000);
        logger.info("Open/Close a sensor");
        sensors.primary_call("65 00 3A", open);
        Thread.sleep(2000);
        sensors.primary_call("65 00 3A", close);
        Thread.sleep(Normal_Exit_Delay);
        verify_armstay();
        Thread.sleep(3000);
        DISARM();

        /*** ADC website verification ***/

        adc.New_ADC_session(adc.getAccountId());
        Thread.sleep(2000);
        adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("History"))).click();
        Thread.sleep(10000);
        try {
            WebElement history_message = adc.driver1.findElement(By.xpath("//*[contains(text(), 'Door/Window 4  (Sensor 4) Opened/Closed')]"));
            Assert.assertTrue(history_message.isDisplayed());
               logger.info("Pass: message is displayed " + history_message.getText());
            } catch (Exception e){
            logger.info("***No such element found!***");
        }
        Thread.sleep(2000);
    }
    @Test(priority = 4, retryAnalyzer = RetryAnalizer.class)
    public void  ArmStay_Open_Close_group_16_during_Exit_Delay() throws Exception {
        logger.info("ArmStay -Open/Close Group 16 contact sensor during exit delay");
        ARM_STAY();
        Thread.sleep(10000);
        logger.info("Open/Close a sensor");
        sensors.primary_call("65 00 4A", open);
        Thread.sleep(2000);
        sensors.primary_call("65 00 4A", close);
        Thread.sleep(Normal_Exit_Delay);
        verify_armstay();
        Thread.sleep(3000);
        DISARM();

        /*** ADC website verification ***/

        adc.New_ADC_session(adc.getAccountId());
        Thread.sleep(2000);
        adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("History"))).click();
        Thread.sleep(10000);
        try {
            WebElement history_message = adc.driver1.findElement(By.xpath("//*[contains(text(), 'Door/Window 5  (Sensor 5) Opened/Closed')]"));
            Assert.assertTrue(history_message.isDisplayed());
            {
                logger.info("Pass: message is displayed " + history_message.getText());
            }
        }catch (Exception e){
            logger.info("***No such element found!***");
        }
        Thread.sleep(2000);
    }
    @Test(priority = 5, retryAnalyzer = RetryAnalizer.class)
    public void  ArmStay_Open_Close_group_8_during_Exit_Delay() throws Exception {
        logger.info("ArmStay -Open/Close Group 8 contact sensor during exit delay");
        ARM_STAY();
        Thread.sleep(10000);
        logger.info("Open/Close a sensor");
        sensors.primary_call("65 00 5A", open);
        Thread.sleep(2000);
        sensors.primary_call("65 00 5A", close);
        Thread.sleep(2000);
        verify_in_alarm();
        Thread.sleep(30000);
        enter_default_user_code();

        /*** ADC website verification ***/

        adc.New_ADC_session(adc.getAccountId());
        Thread.sleep(2000);
        adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("History"))).click();
        Thread.sleep(10000);
        try {
            WebElement history_message_alarm = adc.driver1.findElement(By.xpath("//*[contains(text(), 'Door/Window 6 (Sensor 6) Alarm')]"));
            Assert.assertTrue( history_message_alarm.isDisplayed());{
                logger.info("Pass: message is displayed " + " " +history_message_alarm.getText());
            }
        }catch (Exception e){
            logger.info("***No such element found!***");
        }
        Thread.sleep(2000);
    }
    @Test(priority = 6, retryAnalyzer = RetryAnalizer.class)
    public void  ArmStay_Open_Close_group_9_during_Exit_Delay() throws Exception {
        logger.info("ArmStay -Open/Close Group 9 contact sensor during exit delay");
        ARM_STAY();
        Thread.sleep(10000);
        logger.info("Open/Close a sensor");
        sensors.primary_call("65 00 6A", open);
        Thread.sleep(2000);
        sensors.primary_call("65 00 6A", close);
        Thread.sleep(30000);
        verify_in_alarm();
        Thread.sleep(5000);
        enter_default_user_code();

        /*** ADC website verification ***/

        adc.New_ADC_session(adc.getAccountId());
        Thread.sleep(3000);
        adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("History"))).click();
        Thread.sleep(10000);
        try {
            WebElement history_message_alarm = adc.driver1.findElement(By.xpath("//*[contains(text(), 'Delayed alarm on sensor 7 in partition 1')]"));
            Assert.assertTrue( history_message_alarm.isDisplayed());{
               logger.info("Pass: message is displayed " + " " +history_message_alarm.getText());
            }
        }catch (Exception e){
            logger.info("***No such element found!***");
        }
        Thread.sleep(2000);
    }
    @Test(priority = 7, retryAnalyzer = RetryAnalizer.class)
    public void  ArmStay_Open_Close_group_25_during_Exit_Delay() throws Exception {
        logger.info("ArmStay -Open/Close Group 25 contact sensor during exit delay");
        ARM_STAY();
        Thread.sleep(10000);
        logger.info("Open/Close a sensor");
        sensors.primary_call("65 00 7A", open);
        Thread.sleep(2000);
        sensors.primary_call("65 00 7A", close);
        Thread.sleep(30000);
        verify_armstay();
        Thread.sleep(5000);
        DISARM();

        /*** ADC website verification ***/

        adc.New_ADC_session(adc.getAccountId());
        Thread.sleep(3000);
        adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("History"))).click();
        Thread.sleep(10000);
        try {
            WebElement history_message_alarm = adc.driver1.findElement(By.xpath("//*[contains(text(), 'Door/Window 8  (Sensor 8) Opened/Closed')]"));
            Assert.assertTrue( history_message_alarm.isDisplayed());{
                logger.info("Pass: message is displayed " + " " +history_message_alarm.getText());
            }
        }catch (Exception e){
            logger.info("***No such element found!***");
        }
    }
    @AfterTest
    public void tearDown() throws IOException, InterruptedException {
        driver.quit();
        for (int i= 8; i>0; i--) {
            delete_from_primary(i);
        }
    }
    @AfterMethod
    public void webDriverQuit(){
        adc.driver1.quit();
    }
}