package QTMS_SRF;


import ADC.ADC;
import Panel.Home_Page;
import Panel.PanelInfo_ServiceCalls;
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

public class Arm_Stay extends Setup{
    public Arm_Stay() throws IOException, BiffException {}
    String page_name = "QTMS: Auto Bypass";
    Logger logger = Logger.getLogger(page_name);
    Sensors sensors = new Sensors();
    private int Normal_Exit_Delay = 10;
    private int Normal_Entry_Delay = 11;
    private int Long_Exit_Delay = 12;
    private int Long_Entry_Delay = 13;
    PanelInfo_ServiceCalls servcall = new PanelInfo_ServiceCalls();
    String open = "06 00";
    String close = "04 00";
    String tamper = "01 01";
    private String restore = "04 01";
    private String active = "02 01";
    ADC adc = new ADC();
    String AccountID = adc.getAccountId();
    public void add_primary_call(int zone, int group, int sensor_dec, int sensor_type) throws IOException {
        String add_primary = " shell service call qservice 50 i32 " + zone + " i32 " + group + " i32 " + sensor_dec + " i32 " + sensor_type;
        rt.exec(adbPath + add_primary);
    }

    public void delete_from_primary(int zone) throws IOException, InterruptedException {
        String deleteFromPrimary = " shell service call qservice 51 i32 " + zone;
        rt.exec(adbPath + deleteFromPrimary);
        System.out.println(deleteFromPrimary);
    }
    @BeforeTest
    public void capabilities_setup() throws Exception {

        setup_driver(get_UDID(), "http://127.0.1.1", "4723");
        setup_logger(page_name);
        Thread.sleep(1000);

    }
    @BeforeMethod
    public  void webDriver() {
        adc.webDriverSetUp();
    }


    @Test (priority = 1)
    public void AB319_01() throws Exception {
        logger.info("AB319_01: Verify that Auto Bypass is enabled");
        servcall.get_AUTO_BYPASS();
        Thread.sleep(2000);
        servcall.set_SIA_LIMITS_disable();
        Thread.sleep(1000);
        servcall.set_NORMAL_ENTRY_DELAY(Normal_Entry_Delay);
        Thread.sleep(1000);
        servcall.set_NORMAL_EXIT_DELAY(Normal_Exit_Delay);
        Thread.sleep(1000);
        servcall.set_LONG_ENTRY_DELAY(Long_Entry_Delay);
        Thread.sleep(1000);
        servcall.set_LONG_EXIT_DELAY(Long_Exit_Delay);

    }
    @Test
    public void Armstay_activate_Sensor() throws Exception {
        logger.info("ArmStay -Trip glass break sensor during exit delay");
       // add_primary_call(2,17,6750355,19);
        servcall.set_ARM_STAY_NO_DELAY_enable();
        ARM_STAY();
        Thread.sleep(2000);
        sensors.primary_call("67 00 39", active);
        Thread.sleep(2000);
        logger.info("Restore Glass Break");
        sensors.primary_call("67 00 39", restore);
        Thread.sleep(3000);
        verify_armstay();
        logger.info("Disarm System");
        DISARM();

       /*** ADC website verification ***/
        logger.info("ADC website verification");
        adc.New_ADC_session(AccountID);
        adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("History"))).click();
        Thread.sleep(3000);
        adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_phBody_butSearch"))).click();
        String   element_to_verify = "//*[contains(text(), ' Keyfob 39 Delayed Aux')]";
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
    delete_from_primary(2);
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
    @Test(priority = 3)//dependsOnMethods = {"addGlassBreakSensor"})
    public void Armstay_tamper_17() throws Exception{
      //Armstay_tamper_Sensor_After_Exit_Delay(17,"67 00 39", "//*[contains(text(), 'Sensor 2 Tamper**')]");
    }

    @Test(priority = 92)
    public void AS_92() throws Exception {
        logger.info("verify the panel will not go into immediate alarm when group 13 d/w sensor is tripped during 'Quick Exit' ");
        servcall.set_ARM_STAY_NO_DELAY_enable();

        add_primary_call(2,17,6750355,19);

        Thread.sleep(2000);
        ARM_STAY();
        driver.findElement(By.id("com.qolsys:id/img_quick_exit")).click();
        Thread.sleep(2000);
        sensors.primary_call("65 00 2A", open);
        Thread.sleep(2000);
        sensors.primary_call("65 00 2A", close);
        Thread.sleep(20000);
        verify_armstay();
        DISARM();
        Thread.sleep(2000);
        delete_from_primary(2);
    }
    @Test(priority = 93)
    public void AS_93() throws Exception {
        logger.info("verify the panel will not go into immediate alarm when group 13 d/w sensor is tripped during 'Quick Exit' ");
        servcall.set_ARM_STAY_NO_DELAY_enable();
        add_primary_call(3, 13, 6619298, 1);
        Thread.sleep(2000);
        ARM_STAY();
        driver.findElement(By.id("com.qolsys:id/img_quick_exit")).click();
        Thread.sleep(2000);
        sensors.primary_call("65 00 2A", open);
        Thread.sleep(2000);
        sensors.primary_call("65 00 2A", close);
        Thread.sleep(20000);
        verify_armstay();
        DISARM();
        Thread.sleep(2000);
        delete_from_primary(3);
    }
    @Test(priority = 94)
    public void AS_94() throws Exception {
        logger.info("verify the panel will not go into immediate alarm when group 14 d/w sensor is tripped during 'Quick Exit'   ");
        servcall.set_ARM_STAY_NO_DELAY_enable();
        add_primary_call(4, 14, 6619299, 1);
        Thread.sleep(2000);
        ARM_STAY();
        driver.findElement(By.id("com.qolsys:id/img_quick_exit")).click();
        Thread.sleep(2000);
        sensors.primary_call("65 00 3A", open);
        Thread.sleep(2000);
        sensors.primary_call("65 00 3A", close);
        Thread.sleep(20000);
        verify_armstay();
        DISARM();
        Thread.sleep(2000);
        delete_from_primary(4);
    }

    @Test(priority = 95)
    public void AS_95() throws Exception {
        logger.info("verify the panel will go into immediate alarm when group 8 d/w sensor is tripped during 'Quick Exit'  ");
        servcall.set_ARM_STAY_NO_DELAY_enable();
        add_primary_call(6, 8, 6619301, 1);
        Thread.sleep(2000);
        ARM_STAY();
        driver.findElement(By.id("com.qolsys:id/img_quick_exit")).click();
        Thread.sleep(2000);
        sensors.primary_call("65 00 5A", open);
        Thread.sleep(2000);
        verify_in_alarm();
        enter_default_user_code();
        Thread.sleep(2000);
        delete_from_primary(6);
    }

    @Test(priority = 96)
    public void AS_96() throws Exception {
        logger.info("verify the panel will go into  alarm  when group 9 d/w sensor is tripped during 'Quick Exit' ");
        servcall.set_ARM_STAY_NO_DELAY_enable();
        add_primary_call(7, 9, 6619302, 1);
        Thread.sleep(2000);
        ARM_STAY();
        driver.findElement(By.id("com.qolsys:id/img_quick_exit")).click();
        Thread.sleep(2000);
        sensors.primary_call("65 00 6A", open);
        Thread.sleep(16000);
        verify_in_alarm();
        enter_default_user_code();
        Thread.sleep(2000);
        delete_from_primary(7);
        }

    @AfterTest
    public void tearDown () throws IOException, InterruptedException {
        log.endTestCase(page_name);
        // driver.quit();
        //   for (int i= 24; i>0; i--) {
        //      delete_from_primary(i);
        //  }
    }

    @AfterMethod
    public void webDriverQuit() {
        adc.driver1.quit();
    }





}