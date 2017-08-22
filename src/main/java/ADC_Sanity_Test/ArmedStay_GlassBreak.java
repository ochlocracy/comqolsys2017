package ADC_Sanity_Test;

import ADC.ADC;
import Panel.Setup;
import Sensors.Sensors;
import jxl.read.biff.BiffException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;


/**
 *
 */
public class ArmedStay_GlassBreak extends Setup {

    String page_name = "Arm Stay mode";
    Logger logger = Logger.getLogger(page_name);
    Sensors sensors = new Sensors();
    ADC adc = new ADC();
    String AccountID = adc.getAccountId();


    private int Normal_Exit_Delay = 35000;
    private String active = "02 01";
    private String tamper = "01 01";
    private String restore = "04 01";


    public ArmedStay_GlassBreak() throws IOException, BiffException {}

    public void add_primary_call(int zone, int group, int sensor_dec, int sensor_type) throws IOException {
        String add_primary = " shell service call qservice 50 i32 " + zone + " i32 " + group + " i32 " + sensor_dec + " i32 " + sensor_type;
        rt.exec(adbPath + add_primary);
        // shell service call qservice 50 i32 2 i32 10 i32 6619296 i32 1
    }

    public void Armstay_Trigger_Sensor_During_Exit_Delay_Alarm(int group, String DLID,String element_to_verify, String element_to_verify2) throws Exception {
        logger.info("ArmStay -Trip glass break " +group + " sensor during exit delay");
        ARM_STAY();
        Thread.sleep(3000);
        logger.info("Trip glass break sensor " + group);
        sensors.primary_call(DLID, active);
        Thread.sleep(2000);
        logger.info("Restore Glass Break " + group);
        sensors.primary_call(DLID, restore);
        Thread.sleep(2000);
        logger.info("Verify Alarm");
        verify_in_alarm();
        Thread.sleep(1000);
        logger.info("Disarm with 1234");
        enter_default_user_code();

        /*** ADC website verification ***/
        logger.info("ADC website verification");
        adc.New_ADC_session(AccountID);
        Thread.sleep(3000);
        adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("History"))).click();
        Thread.sleep(3000);
        adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_phBody_butSearch"))).click();


        Thread.sleep(10000);
        try {
            WebElement history_message = adc.driver1.findElement(By.xpath(element_to_verify));
            Assert.assertTrue(history_message.isDisplayed());{
                logger.info("Pass: message is displayed: " + history_message.getText());
            }
        }catch (Exception e){
            logger.info("***No such element found!***");
        }
        Thread.sleep(2000);
    }

    public void Armstay_Trigger_Sensor_During_Exit_Delay(int group, String DLID,String element_to_verify ) throws Exception {
        logger.info("ArmStay -Trip glass break " + group + " sensor during exit delay");
        ARM_STAY();
        Thread.sleep(5000);
        logger.info("Trip glass break sensor " +group);
        sensors.primary_call(DLID, active);
        Thread.sleep(2000);
        logger.info("Restore Glass Break " +group);
        sensors.primary_call(DLID, restore);
        Thread.sleep(Normal_Exit_Delay);
        logger.info("Disarm System");
        DISARM();
        logger.info("Verify Disarm");
        verify_disarm();

        /*** ADC website verification ***/
        logger.info("ADC website verification");
        adc.New_ADC_session(AccountID);
        adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("History"))).click();
        Thread.sleep(3000);
        adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_phBody_butSearch"))).click();

        Thread.sleep(10000);
        try {
            WebElement history_message = adc.driver1.findElement(By.xpath(element_to_verify));
            Assert.assertTrue(history_message.isDisplayed());
            {
                logger.info("Pass: message is displayed: " + history_message.getText());
            }
        } catch (Exception e) {
            logger.info("***No such element found!***");
        }

    }

    public void Armstay_tamper_Sensor_After_Exit_Delay_Alarm(int group, String DLID,String element_to_verify ) throws Exception {
        logger.info("ArmStay -Trip glass break " + group + " sensor during exit delay");
        ARM_STAY();
        Thread.sleep(Normal_Exit_Delay);
        logger.info("Tamper glass break sensor " + group);
        sensors.primary_call(DLID, tamper);
        Thread.sleep(2000);
        logger.info("Restore Glass Break " + group);
        sensors.primary_call(DLID, restore);
        Thread.sleep(3000);
        logger.info("Verify Alarm");
        verify_in_alarm();
        Thread.sleep(1000);
        logger.info("Disarm with 1234");
        enter_default_user_code();

        /*** ADC website verification ***/
        logger.info("ADC website verification");
        adc.New_ADC_session(AccountID);
        adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("History"))).click();
        Thread.sleep(3000);
        adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_phBody_butSearch"))).click();

        Thread.sleep(10000);
        try {
            WebElement history_message = adc.driver1.findElement(By.xpath(element_to_verify));
            Assert.assertTrue(history_message.isDisplayed());
            {
                logger.info("Pass: message is displayed: " + history_message.getText());
            }
        } catch (Exception e) {
            logger.info("***No such element found!***");
        }

    }
    public void Armstay_tamper_Sensor_After_Exit_Delay(int group, String DLID,String element_to_verify ) throws Exception {
        logger.info("ArmStay -Trip glass break " + group + " sensor during exit delay");
        ARM_STAY();
        Thread.sleep(Normal_Exit_Delay);
        logger.info("Tamper glass break sensor");
        sensors.primary_call(DLID, tamper);
        Thread.sleep(2000);
        logger.info("Restore Glass Break");
        sensors.primary_call(DLID, restore);
        Thread.sleep(3000);
        logger.info("Disarm System");
        DISARM();
        logger.info("Verify Disarm");
        verify_disarm();

        /*** ADC website verification ***/
        logger.info("ADC website verification");
        adc.New_ADC_session(AccountID);
        adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("History"))).click();
        Thread.sleep(3000);
        adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_phBody_butSearch"))).click();

        Thread.sleep(10000);
        try {
            WebElement history_message = adc.driver1.findElement(By.xpath(element_to_verify));
            Assert.assertTrue(history_message.isDisplayed());
            {
                logger.info("Pass: message is displayed: " + history_message.getText());
            }
        } catch (Exception e) {
            logger.info("***No such element found!***");
        }

    }


    public void delete_from_primary(int zone) throws IOException, InterruptedException {
        String deleteFromPrimary = " shell service call qservice 51 i32 " + zone;
        rt.exec(adbPath + deleteFromPrimary);
        System.out.println(deleteFromPrimary);
    }

    @BeforeTest
    public void capabilities_setup() throws Exception {
        setup_driver(get_UDID(),"http://127.0.1.1", "4723");
        setup_logger(page_name);
        setArmStay_NoDelay("Disable");
        setAutoStay("Disable");
    }

    @BeforeMethod
    public  void webDriver() {
        adc.webDriverSetUp();
    }

    @Test
    public void addGlassBreakSensor() throws IOException, InterruptedException{
        logger.info("Addig Sensors");
        Thread.sleep(2000);
        add_primary_call(1,13,6750361,19);
        add_primary_call(2,17,6750355,19);

        adc.New_ADC_session(adc.getAccountId());
        Thread.sleep(2000);
        adc.driver1.findElement(By.partialLinkText("Sensors")).click();
        Thread.sleep(2000);
        adc.Request_equipment_list();
    }

    @Test(dependsOnMethods = {"addGlassBreakSensor"})
    public void ArmstayExitDelay_13() throws Exception{
        Armstay_Trigger_Sensor_During_Exit_Delay_Alarm(13,"67 00 99","//*[contains(text(), 'Glass Break 1 (Sensor 1) Pending Alarm')]","//*[contains(text(), 'Glass Break 1 (Sensor 1) Alarm')]");
    }

    @Test(priority = 1,dependsOnMethods = {"addGlassBreakSensor"})
    public void ArmstayExitDelay_17() throws Exception{
        Armstay_Trigger_Sensor_During_Exit_Delay(17,"67 00 39","//*[contains(text(), 'Sensor 2 Tamper**')]");
    }

    @Test(priority = 2,dependsOnMethods = {"addGlassBreakSensor"})
    public void Armstay_tamper_13() throws Exception{
        Armstay_tamper_Sensor_After_Exit_Delay_Alarm(13,"67 00 99", "//*[contains(text(), 'Glass Break 1 (Sensor 1) Tamper')]");
    }

    @Test(priority = 3,dependsOnMethods = {"addGlassBreakSensor"})
    public void Armstay_tamper_17() throws Exception{
        Armstay_tamper_Sensor_After_Exit_Delay(17,"67 00 39", "//*[contains(text(), 'Sensor 2 Tamper**')]");
    }

    @AfterTest
    public void tearDown() throws IOException, InterruptedException {
        driver.quit();
        for (int i = 2; i > 0; i--) {
            delete_from_primary(i);
        }
    }

    @AfterMethod
    public void webDriverQuit() {
        adc.driver1.quit();
    }
}