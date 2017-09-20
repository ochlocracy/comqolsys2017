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
    String page_name = "QTMS: ARM STAY";
    Logger logger = Logger.getLogger(page_name);
    Sensors sensors = new Sensors();
    String login = "panAut";
    String password = "qolsys123";
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
    private String log_path = "/home/olgak/IdeaProjects/comqolsys2017/log/test.txt";
    ADC adc = new ADC();
    private String keyfobAway = "04 04";
    private String keyfobStay = "04 01";
    private String keyfobDisarm = "08 01";
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


    @Test
    public void AS_02() throws Exception {
        logger.info("Verify the panel can be disarmed from ADC");
        servcall.set_ARM_STAY_NO_DELAY_enable();
        Thread.sleep(1000);
        servcall.EVENT_ARM_STAY();
        Thread.sleep(3000);
        verify_armstay();
        adc.New_ADC_session_User(login,password);
        Thread.sleep(3000);
        if (adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_phBody_ArmingStateWidget_imgState"))).isDisplayed()) {
            adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_phBody_ArmingStateWidget_btnDisarm"))).click();}
        Thread.sleep(4000);
        verify_disarm();
        System.out.println("Pass");
    }
    @Test(priority = 1)
    public void AS_04() throws Exception {
        logger.info("Verify the panel can be disarmed using a keyfob: If KeyFob Instant Arming is disabled, panel should arm away after exit delay.\n" +
                " If KeyFob Instant Arming is enabled, panel should arm away immediately.");
        Home_Page home = PageFactory.initElements(driver, Home_Page.class);
        servcall.set_KEYFOB_NO_DELAY_disable();
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
        Thread.sleep(1000);
        add_primary_call(38, 1, 6619386, 102);
        Thread.sleep(1000);
        sensors.primary_call("65 00 AF", keyfobStay);
        Thread.sleep(15000);
        verify_armstay();
        sensors.primary_call("65 00 AF", keyfobAway);
        Thread.sleep(2000);
        deleteLogFile(log_path);
        Thread.sleep(2000);
        eventLogsGenerating(log_path,new String[]{
                " PanelAPI:: armAwayThePanelAfterExitDelay called"},1);
        //Thread.sleep(4000);

        Thread.sleep(15000);
        verify_armaway();}
     /*   home.ArwAway_State.click();
        enter_default_user_code();
        verify_disarm();
        System.out.println("Pass:If KeyFob Instant Arming is disabled, panel armed away after exit delay.");
        servcall.set_KEYFOB_NO_DELAY_enable();
        Thread.sleep(1000);
        sensors.primary_call("65 00 AF", keyfobStay);
        Thread.sleep(1000);
        verify_armstay();
        sensors.primary_call("65 00 AF", keyfobAway);
        Thread.sleep(1000);
        verify_armaway();
        home.ArwAway_State.click();
        enter_default_user_code();
        verify_disarm();
        System.out.println("Pass: If KeyFob Instant Arming is enabled, panel should arm away immediately.");*/


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