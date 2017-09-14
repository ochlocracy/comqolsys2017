package QTMS_SRF;

import ADC.ADC;
import ADC_Sanity_Test.RetryAnalizer;
import Cellular.Cellular_test_page_elements;
import Cellular.System_Tests_page;
import Cellular.WiFi_setting_page_elements;
import Panel.*;
import Sensors.Sensors;
import jxl.read.biff.BiffException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class Auto_Bypass extends Setup{

    public Auto_Bypass() throws IOException, BiffException {
    }
    String page_name = "QTMS: Auto Bypass";
    Logger logger = Logger.getLogger(page_name);
    Sensors sensors = new Sensors();
    ADC adc = new ADC();
    PanelInfo_ServiceCalls servcall = new PanelInfo_ServiceCalls();

    String open = "06 00";
    String close = "04 00";
    String tamper = "01 01";
    String activate = "02 01";
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
    public void swipe_bypass_page() throws InterruptedException {
        int starty = 507;
        int endy = 177;
        int startx = 907;
        driver.swipe(startx, starty, startx, endy, 3000);
        Thread.sleep(2000);
    }


    @BeforeTest
    public void capabilities_setup() throws Exception {
        //setup_driver(get_UDID(), "http://127.0.1.1", "4723");
        setup_driver(get_UDID(), "http://127.0.1.1", "4723");
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

    /* @Test
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
    }*/
    @Test  /*** Disarm mode/1)a sensor must be paired 2)Auto Bypass Enabled ***/
    public void addSensors() throws Exception {
        add_primary_call(1, 10, 6619296, 1);
        add_primary_call(2, 12, 6619297, 1);
        add_primary_call(3, 13, 6619298, 1);
        add_primary_call(4, 14, 6619299, 1);
        add_primary_call(5, 16, 6619300, 1);
        add_primary_call(20, 15, 5570628, 2);
        add_primary_call(22, 17, 5570629, 2);
        add_primary_call(23, 20, 5570630, 2);
        Thread.sleep(2000);
            }

    @Test  (dependsOnMethods = {"addSensors"}, priority = 2)/*** Disarm mode/1)a sensor must be paired 2)Auto Bypass Enabled ***/
    public void AB319_02_AB319_04() throws Exception {
        Thread.sleep(2000);
       // servcall.set_AUTO_BYPASS(1);
        logger.info("Verify that open sensor will be selected for bypass and at top of sensor list when pushing arm button.");
        Home_Page home_page = PageFactory.initElements(driver, Home_Page.class);
        sensors.primary_call("65 00 0A", open);
       // servcall.get_AUTO_BYPASS();
        servcall.set_AUTO_STAY(0);
        home_page.DISARM.click();
        driver.findElement(By.id("com.qolsys:id/img_expand")).click();
        Thread.sleep(4000);
        driver.findElement(By.id("com.qolsys:id/t3_open_tv_active")).click();
        if(driver.findElement(By.id("com.qolsys:id/uiTVName")).getText().equals("Door/Window 1"))
        logger.info("AB319_2 Pass: Sensor is bypassed");
          else
         logger.info("Fail: Sensor is not selected for bypass");
           Thread.sleep(two_sec);
        driver.findElement(By.id("com.qolsys:id/img_arm_away")).click();
        Thread.sleep(13000);
        logger.info("Verify that bypassed sensors are really bypassed after panel is armed");
        sensors.primary_call("65 00 0A", close);
        sensors.primary_call("65 00 0A", open);
        sensors.primary_call("65 00 0A", close);
        Thread.sleep(two_sec);
        verify_armaway();
        Thread.sleep(two_sec);
        logger.info("AB319_4 Pass: Sensor is bypassed");
        home_page.ArwAway_State.click();
        enter_default_user_code();
    }

   @Test (priority = 3)
        public void AB319_03_AB319_09() throws Exception {
        logger.info("Verify that TTS will announce bypassed sensors during arming & ");
        logger.info("Verify that TTS will not announce opening and closing of bypassed sensor while armed");
        add_primary_call(1, 10, 6619296, 1);
        Home_Page home = PageFactory.initElements(driver, Home_Page.class);
        logger.info("open before ARM AWAY");
        sensors.primary_call("65 00 0A", open);
        //servcall.set_AUTO_STAY(0);
       // servcall.set_AUTO_BYPASS(1);
        Thread.sleep(4000);
        ARM_AWAY(Long_Exit_Delay);
        Thread.sleep(two_sec);
        deleteLogFile("/home/olgak/IdeaProjects/comqolsys2017/log/test.txt");
        Thread.sleep(two_sec);
        eventLogsGenerating("/home/olgak/IdeaProjects/comqolsys2017/log/test.txt",new String[]{
                "TtsUtil:: TTS processing:Door/Window 1, ByPassed"},1);
        Thread.sleep(4000);
        sensors.primary_call("65 00 0A", close);
        Thread.sleep(two_sec);
        //home.ArwAway_State.click();
      //  enter_default_user_code();
       logger.info("Verify that TTS will not announce opening and closing of bypassed sensor while armed away");
       sensors.primary_call("65 00 0A", open);
       Thread.sleep(two_sec);
       sensors.primary_call("65 00 0A", close);
       Thread.sleep(two_sec);
       deleteLogFile("/home/olgak/IdeaProjects/comqolsys2017/log/test.txt");
       Thread.sleep(two_sec);
       eventLogsGenerating("/home/olgak/IdeaProjects/comqolsys2017/log/test.txt",new String[]{
               "TtsUtil:: TTS service received Door/Window 1,  ByPassed"},1);
       logger.info("No TTS message is into logs");
       Thread.sleep(2000);
       home.ArwAway_State.click();
       enter_default_user_code();
       Thread.sleep(two_sec);

       logger.info("open before ARM STAY");
        Thread.sleep(two_sec);
        sensors.primary_call("65 00 0A", open);
        Thread.sleep(two_sec);
        ARM_STAY();
        Thread.sleep(two_sec);
        deleteLogFile("/home/olgak/IdeaProjects/comqolsys2017/log/test.txt");
        Thread.sleep(two_sec);
        eventLogsGenerating("/home/olgak/IdeaProjects/comqolsys2017/log/test.txt",new String[]{
                "TtsUtil:: TTS processing:Door/Window 1  ByPassed"},1);
        Thread.sleep(two_sec);
        sensors.primary_call("65 00 0A", close);
        Thread.sleep(two_sec);

       logger.info("Verify that TTS will not announce opening and closing of bypassed sensor while armed stay");
        sensors.primary_call("65 00 0A", open);
        Thread.sleep(two_sec);
        sensors.primary_call("65 00 0A", close);
        Thread.sleep(two_sec);
        deleteLogFile("/home/olgak/IdeaProjects/comqolsys2017/log/test.txt");
        Thread.sleep(two_sec);
        eventLogsGenerating("/home/olgak/IdeaProjects/comqolsys2017/log/test.txt",new String[]{
                "TtsUtil:: TTS service received Door/Window 1,  ByPassed"},1);
        logger.info("No TTS message is into logs");
        Thread.sleep(two_sec);
        DISARM();
        Thread.sleep(two_sec);
    }

    @Test (dependsOnMethods = {"addSensors"}, priority = 8)
    public void AB319_05_AB319_10_AB319_11() throws Exception {
        logger.info("Verify that Open Sensor Protest will appear if bypass is unselected and system is armed");
        logger.info("Verify that Open Sensor Protest appears when Auto Bypass is dissabled, sensor is opened, and arm is attempted.");
        logger.info("Verify that panel will arm once 'Arming protest' message appears and selected 'ok' ");
        Thread.sleep(2000);
        servcall.set_AUTO_BYPASS(0);
        servcall.set_AUTO_STAY(0);
        Thread.sleep(4000);
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
        Thread.sleep(13000);
        verify_armaway();
        Thread.sleep(two_sec);
        home_page.ArwAway_State.click();
        enter_default_user_code();
    }

    @Test (dependsOnMethods = {"addSensors"}, priority = 9)
    public void AB319_06() throws Exception {
        logger.info("Verify that panel will arm once sensor is closed from step AB319_05");
        Thread.sleep(2000);
        //servcall.set_AUTO_BYPASS(0);
      //  servcall.set_AUTO_STAY(0);
        Home_Page home_page = PageFactory.initElements(driver, Home_Page.class);
        sensors.primary_call("65 00 0A", open);
        Thread.sleep(4000);
        home_page.DISARM.click();
        Thread.sleep(two_sec);
        home_page.ARM_AWAY.click();
        Thread.sleep(two_sec);
        if(driver.findElements(By.id("com.qolsys:id/message")).size() == 1){
            logger.info("Pass: Open Sensor Pop-up Message Received");
            sensors.primary_call("65 00 0A", close);
            Thread.sleep(5000);
            driver.findElement(By.id("com.qolsys:id/cancel")).click();
            home_page.ARM_AWAY.click();
        }
        else{
            logger.info("Fail: Open Sensor Pop-up Message Not Received");
            Thread.sleep(two_sec);
            home_page.ARM_AWAY.click();
        }
        Thread.sleep(14000);
        verify_armaway();
        Thread.sleep(two_sec);
        logger.info("AB319_06 Pass: Verified that panel will arm once sensor is closed from step AB319_05");
        home_page.ArwAway_State.click();
        enter_default_user_code();
    }
    @Test (dependsOnMethods = {"addSensors"}, priority = 4)
    public void AB319_07() throws Exception {
        logger.info("Verify that sensor will not Auto Bypass if sensor is opened after selecting arm button.");
        Thread.sleep(2000);
        //servcall.set_AUTO_BYPASS(1);
       // servcall.set_AUTO_STAY(0);
        Home_Page home_page = PageFactory.initElements(driver, Home_Page.class);
        Thread.sleep(4000);
        home_page.DISARM.click();
        Thread.sleep(two_sec);
        home_page.ARM_AWAY.click();
        Thread.sleep(two_sec);
        sensors.primary_call("65 00 0A", open);
        Thread.sleep(11000);
        verify_status_alarmed();
        Thread.sleep(500);
        logger.info("AB319_07 Pass: Verified that sensor will not Auto Bypass if sensor is opened after selecting arm button.");
        enter_default_user_code();
        }

    @Test (dependsOnMethods = {"addSensors"}, priority = 5)
        public void AB319_08() throws Exception {
        logger.info("Verify that sensor can be unslected from bypass and system can be armed as normal");
        Thread.sleep(2000);
        Home_Page home_page = PageFactory.initElements(driver, Home_Page.class);
       // servcall.set_AUTO_BYPASS(1);
       // servcall.set_AUTO_STAY(0);
      //  Thread.sleep(2000);
        sensors.primary_call("65 00 0A", open);
        Thread.sleep(4000);
        driver.findElement(By.id("com.qolsys:id/t3_img_disarm")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("com.qolsys:id/img_expand")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("com.qolsys:id/t3_open_tv_active")).click();
        if(driver.findElement(By.id("com.qolsys:id/uiTVName")).getText().equals("Door/Window 1"))
             tap(764,192);
        else
            logger.info("Fail: Sensor is not selected for bypass");
        Thread.sleep(two_sec);
        sensors.primary_call("65 00 0A", close);
        Thread.sleep(two_sec);
        driver.findElement(By.id("com.qolsys:id/img_arm_away")).click();
        Thread.sleep(13000);
        verify_armaway();
        Thread.sleep(two_sec);
        logger.info("AB319_08 Pass: System armed.");
        home_page.ArwAway_State.click();
        enter_default_user_code();
    }
    @Test (dependsOnMethods = {"addSensors"}, priority = 10)
    public void AB319_12() throws Exception {
        logger.info("AB319_12: Verify that panel user can manually bypass opened sensors and any sensor.");
        Thread.sleep(2000);
       // servcall.set_AUTO_BYPASS(0);
       // servcall.get_AUTO_BYPASS();
        Home_Page home_page = PageFactory.initElements(driver, Home_Page.class);
       // servcall.set_AUTO_STAY(0);
        Thread.sleep(2000);
        sensors.primary_call("65 00 0A", open);
        sensors.primary_call("65 00 1A",tamper);
        sensors.primary_call("65 00 2A",open);
        sensors.primary_call("55 00 44",tamper);
        sensors.primary_call("55 00 54",tamper);
        sensors.primary_call("55 00 64",tamper);
        Thread.sleep(4000);
        driver.findElement(By.id("com.qolsys:id/t3_img_disarm")).click();
//        home_page.DISARM.click();
        Thread.sleep(2000);
        driver.findElement(By.id("com.qolsys:id/img_expand")).click();
        Thread.sleep(4000);
        driver.findElement(By.id("com.qolsys:id/t3_open_tv_active")).click();
        List<WebElement> li = driver.findElements(By.id("com.qolsys:id/uiTVName"));
        li.size();
        if(li.get(0).getText().equals("Door/Window 1"))
        { tap(764,192);
        logger.info("Pass: Door/Window 1 is selected for bypass");}
        else
            logger.info("Fail: Door/Window 1 is not selected for bypass");
        if(li.get(1).getText().equals("Door/Window 3"))
        { tap(764,271);
            logger.info("Pass: Door/Window 3 is selected for bypass");}
        else
            logger.info("Fail: Door/Window 3 is not selected for bypass");
        if(li.get(2).getText().equals("Door/Window 2"))
        { tap(764,358);
            logger.info("Pass: Door/Window 2 is selected for bypass");}
        else
            logger.info("Fail: Door/Window 2 is not selected for bypass");
        if(li.get(3).getText().equals("Motion 20")) {
            tap(764, 442);
            logger.info("Pass: Motion 20 is selected for bypass");}
          else
            logger.info("Fail: Motion 20 is not selected for bypass");
        Thread.sleep(two_sec);
        swipe_bypass_page();
        List<WebElement> lis = driver.findElements(By.id("com.qolsys:id/uiTVName"));
        lis.size();
        if(lis.get(3).getText().equals("Motion 23"))
        { tap(764,407);
            logger.info("Pass: Motion 23 is selected for bypass");}
        else
            logger.info("Fail: Motion 23 is not selected for bypass");
        if(lis.get(2).getText().equals("Motion 22"))
        { tap(764,317);
            logger.info("Pass: Motion 22 is selected for bypass");}
        else
            logger.info("Fail: Motion 22 is not selected for bypass");
        Thread.sleep(two_sec);
        driver.findElement(By.id("com.qolsys:id/img_arm_away")).click();
        Thread.sleep(13000);
        verify_armaway();
        Thread.sleep(two_sec);
        logger.info("AB319_12 Pass: System armed.");
        home_page.ArwAway_State.click();
        enter_default_user_code(); }

    @Test (dependsOnMethods = {"addSensors"},priority = 6)
    public void AB319_13() throws Exception {
        logger.info("AB319_13: Verify that panel user can manually bypass any sensors");
        Thread.sleep(2000);
       // servcall.set_AUTO_BYPASS(1);
       // servcall.get_AUTO_BYPASS();
      //  servcall.set_AUTO_STAY(0);
        Home_Page home_page = PageFactory.initElements(driver, Home_Page.class);
       // Thread.sleep(4000);
        driver.findElement(By.id("com.qolsys:id/t3_img_disarm")).click();
       // home_page.DISARM.click();
        Thread.sleep(2000);
        driver.findElement(By.id("com.qolsys:id/img_expand")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("com.qolsys:id/t3_open_tv_all")).click();
        List<WebElement> li = driver.findElements(By.id("com.qolsys:id/uiTVName"));
        li.size();
        if(li.get(0).getText().equals("Door/Window 1"))
        { tap(764,192);
            logger.info("Pass: Door/Window 1 is selected for bypass");}
        else
            logger.info("Fail: Door/Window 1 is not selected for bypass");
        Thread.sleep(4000);
        swipe_bypass_page();
        List<WebElement> lis = driver.findElements(By.id("com.qolsys:id/uiTVName"));
        lis.size();
        if(lis.get(3).getText().equals("Motion 23"))
        { tap(764,462);
            logger.info("Pass: Motion 23 is selected for bypass");}
        else
            logger.info("Fail: Motion 23 is not selected for bypass");
        Thread.sleep(4000);
        driver.findElement(By.id("com.qolsys:id/img_arm_away")).click();
        Thread.sleep(13000);
        verify_armaway();
        Thread.sleep(two_sec);
        logger.info("AB319_13 Pass: System armed.");
        home_page.ArwAway_State.click();
        enter_default_user_code();
    }
    @Test (dependsOnMethods = {"addSensors"}, priority = 7)
    public void AB319_14() throws Exception {
        logger.info("Verify that panel can arm away when a entry delay(group10,12) sensor  is unselected from bypassed sensor list");
        Thread.sleep(2000);
       // servcall.set_AUTO_BYPASS(1);
        servcall.set_AUTO_STAY(1);
        Home_Page home_page = PageFactory.initElements(driver, Home_Page.class);
        sensors.primary_call("65 00 1A", open);
        Thread.sleep(4000);
        driver.findElement(By.id("com.qolsys:id/t3_img_disarm")).click();
       // home_page.DISARM.click();
        Thread.sleep(2000);
        driver.findElement(By.id("com.qolsys:id/img_expand")).click();
        Thread.sleep(4000);
        driver.findElement(By.id("com.qolsys:id/t3_open_tv_active")).click();
        List<WebElement> lis = driver.findElements(By.id("com.qolsys:id/uiTVName"));
        lis.size();
        if(lis.get(0).getText().equals("Door/Window 2"))
        { tap(764,182);
        logger.info("Pass: Door/Window 2 is disselected for bypass");}
        else
            logger.info("Fail: Sensor is selected for bypass");
        Thread.sleep(4000);
        home_page.ARM_AWAY.click();
        Thread.sleep(two_sec);
        sensors.primary_call("65 00 1A", close);
        Thread.sleep(15000);
        verify_armaway();
        Thread.sleep(two_sec);
        logger.info("AB319_06 Pass: Verified that panel will arm once sensor is closed from step AB319_05");
        home_page.ArwAway_State.click();
        enter_default_user_code();}

    @Test /*** Disarm mode/Enabled by Default ***/(priority = 1)
    public void AB319_01() throws Exception {
        logger.info("AB319_01: Verify that Auto Bypass is enabled");
        servcall.get_AUTO_BYPASS();
       }

    @AfterTest
    public void tearDown () throws IOException, InterruptedException {
        log.endTestCase(page_name);
        driver.quit();
        for (int i= 24; i>0; i--) {
            delete_from_primary(i);
        }
    }


}
