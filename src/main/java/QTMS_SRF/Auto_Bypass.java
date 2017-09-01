package QTMS_SRF;

import ADC.ADC;
import Panel.Home_Page;
import Panel.PanelInfo_ServiceCalls;
import Panel.Setup;
import Sensors.Sensors;
import jxl.read.biff.BiffException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by nchortek on 8/25/17.
 */
public class Auto_Bypass extends Setup{

    public Auto_Bypass() throws IOException, BiffException {
    }


    String page_name = "Auto Bypass";
    Logger logger = Logger.getLogger(page_name);
    Sensors sensors = new Sensors();
    ADC adc = new ADC();
    PanelInfo_ServiceCalls servcall = new PanelInfo_ServiceCalls();

    String open = "06 00";
    String close = "04 00";
    private int Normal_Exit_Delay = 10;
    private int Normal_Entry_Delay = 11;
    private int Long_Exit_Delay = 12;
    private int Long_Entry_Delay = 13;
    int two_sec = 2000;

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
        //setup_driver(get_UDID(), "http://127.0.1.1", "4723");
        setup_driver(udid_, "http://127.0.1.1", "4723");
        setup_logger(page_name);
        Thread.sleep(1000);
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
    public void AB_01() throws IOException, InterruptedException {
        logger.info("Enabling auto bypass and adding sensor");

        adc.webDriverSetUp();
        servcall.set_AUTO_BYPASS(1);
        Thread.sleep(two_sec);
        add_primary_call(1, 10, 6619296, 1);
        Thread.sleep(two_sec);
        adc.New_ADC_session(adc.getAccountId());
        Thread.sleep(two_sec);
        adc.driver1.findElement(By.partialLinkText("Sensors")).click();
        Thread.sleep(two_sec);
        adc.Request_equipment_list();
        Thread.sleep(two_sec);

       adc.driver1.quit();
    }


    @Test (priority = 1)
    public void AB_02() throws Exception {
        logger.info("Verify that open sensor will be selected for bypass and at top of sensor list when pushing arm button.");
        Home_Page home_page = PageFactory.initElements(driver, Home_Page.class);

        sensors.primary_call("65 00 0A", open);
        Thread.sleep(two_sec);
        home_page.DISARM.click();
        Thread.sleep(two_sec);
        driver.findElement(By.id("com.qolsys:id/img_expand")).click();
        Thread.sleep(two_sec);
        if(driver.findElement(By.id("com.qolsys:id/uiTVName")).getText().equals("Door/Window 1"))
            logger.info("Pass: Sensor is selected for bypass");
        else
            logger.info("Fail: Sensor is not selected for bypass");
        //check that it's selected for bypass
        Thread.sleep(two_sec);
        sensors.primary_call("65 00 0A", close);
        home_page.ARM_STAY.click();
        DISARM();
    }

    @Test (priority = 2)
    public void AB_03_AB_04() throws Exception {
        logger.info("Verify that open sensors are bypassed after arming & that TTS will announce bypassed sensors");
        Home_Page home = PageFactory.initElements(driver, Home_Page.class);

        logger.info("open before arm away");
        sensors.primary_call("65 00 0A", open);
        Thread.sleep(two_sec);
        ARM_AWAY(Long_Exit_Delay);
        Thread.sleep(two_sec);
        deleteLogFile("/home/nchortek/comqolsys2017/log/test.txt");
        Thread.sleep(two_sec);
        eventLogsGenerating("/home/nchortek/comqolsys2017/log/test.txt",new String[]{
                "TtsUtil:: TTS service received Door/Window 1  ByPassed"},1);
        Thread.sleep(two_sec);
        sensors.primary_call("65 00 0A", close);
        Thread.sleep(two_sec);

        logger.info("open after arm away");
        sensors.primary_call("65 00 0A", open);
        Thread.sleep(two_sec);
        sensors.primary_call("65 00 0A", close);
        Thread.sleep(two_sec);
        deleteLogFile("/home/nchortek/comqolsys2017/log/test.txt");
        Thread.sleep(two_sec);
        eventLogsGenerating("/home/nchortek/comqolsys2017/log/test.txt",new String[]{
                "TtsUtil:: TTS service received Door/Window 1  ByPassed"},1);
        Thread.sleep(two_sec);
        home.ArwAway_State.click();
        enter_default_user_code();

        logger.info("open before arm stay");
        Thread.sleep(two_sec);
        sensors.primary_call("65 00 0A", open);
        Thread.sleep(two_sec);
        ARM_STAY();
        Thread.sleep(two_sec);
        deleteLogFile("/home/nchortek/comqolsys2017/log/test.txt");
        Thread.sleep(two_sec);
        eventLogsGenerating("/home/nchortek/comqolsys2017/log/test.txt",new String[]{
                "TtsUtil:: TTS service received Door/Window 1  ByPassed"},1);
        Thread.sleep(two_sec);
        sensors.primary_call("65 00 0A", close);
        Thread.sleep(two_sec);

        logger.info("open after arm stay");
        sensors.primary_call("65 00 0A", open);
        Thread.sleep(two_sec);
        sensors.primary_call("65 00 0A", close);
        Thread.sleep(two_sec);
        deleteLogFile("/home/nchortek/comqolsys2017/log/test.txt");
        Thread.sleep(two_sec);
        eventLogsGenerating("/home/nchortek/comqolsys2017/log/test.txt",new String[]{
                "TtsUtil:: TTS service received Door/Window 1  ByPassed"},1);
        Thread.sleep(two_sec);
        DISARM();
        Thread.sleep(two_sec);
    }

    @Test (priority = 3)
    public void AB_05() throws Exception {
        logger.info("Verify that Open Sensor Protest will appear if bypass is unselected and system is armed");
        servcall.set_AUTO_BYPASS(0);
        Thread.sleep(10000);

        Home_Page home_page = PageFactory.initElements(driver, Home_Page.class);
        sensors.primary_call("65 00 0A", open);
        Thread.sleep(two_sec);
        home_page.DISARM.click();
        Thread.sleep(two_sec);
        home_page.ARM_AWAY.click();
        Thread.sleep(two_sec);
        if(driver.findElements(By.id("com.qolsys:id/message")).size() == 1){
            logger.info("Pass: Open Sensor Pop-up Message Received");
            driver.findElement(By.id("com.qolsys:id/ok")).click();
        }
        else{
            logger.info("Fail: Open Sensor Pop-up Message Not Received");
            Thread.sleep(two_sec);
            home_page.ARM_AWAY.click();
        }
        Thread.sleep(11000);
        verify_armaway();
        Thread.sleep(two_sec);
        servcall.set_AUTO_BYPASS(1);
        Thread.sleep(two_sec);
        home_page.ArwAway_State.click();
        enter_default_user_code();
    }

    @Test (priority = 4)
    public void AB_06() throws Exception {
        logger.info("Verify that panel will arm once sensor is closed from step AB_05");
        Thread.sleep(two_sec);
        sensors.primary_call("65 00 0A", close);
        Thread.sleep(two_sec);
        ARM_STAY();
        verify_armstay();
        DISARM();
    }


    @AfterTest
    public void tearDown () throws IOException, InterruptedException {
        delete_from_primary(1);
        log.endTestCase(page_name);
        driver.quit();
    }


}
