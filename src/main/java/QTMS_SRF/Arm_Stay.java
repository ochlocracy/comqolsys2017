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
import java.util.List;

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
    private String activate = "02 01";
    private String idle = "00 01";
    private String restore = "04 01";
    private String active = "02 01";
    private String log_path = "/home/olgak/IdeaProjects/comqolsys2017/log/test.txt";
    ADC adc = new ADC();
    private String keyfobAway = "04 04";
    private String keyfobStay = "04 01";
    private String keyfobDisarm = "08 01";
    String AccountID = adc.getAccountId();
    String door_window10 = "65 00 0A";
    String door_window12 = "65 00 1A";
    String door_window13 = "65 00 2A";
    String door_window14 = "65 00 3A";
    String door_window16 = "65 00 4A";
    String door_window25 = "65 00 5A";
    String door_window8 = "65 00 6A";
    String door_window9 = "65 00 7A";
    String motion15 = "55 00 44";
    String motion17 = "55 00 54";
    String motion20 = "55 00 64";
    String motion35 = "55 00 74";
    String keyfob1 = "65 00 AF";
    String keyfob4 = "65 00 BF";
    String keyfob6 = "65 00 CF";
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
        deleteLogFile(log_path);
         Thread.sleep(1000);
        eventLogsGenerating(log_path,new String[]{
                "SYSTEM_STATUS_CHANGED_TIME value : ARM-STAY-EXIT-DELAY"},1);
        Thread.sleep(15000);
        verify_armstay();
        sensors.primary_call("65 00 AF", keyfobAway);
        Thread.sleep(1000);
       // deleteLogFile(log_path);
       // Thread.sleep(2000);
        eventLogsGenerating(log_path,new String[]{
                "SYSTEM_STATUS_CHANGED_TIME value : ARM-AWAY-EXIT-DELAY"},1);
             Thread.sleep(15000);
        verify_armaway();
       // sensors.primary_call("65 00 AF", keyfobDisarm);
        home.ArwAway_State.click();
        enter_default_user_code();
        verify_disarm();
        System.out.println("*** Pass:If KeyFob Instant Arming is disabled, panel armed away after exit delay. ***");
        servcall.set_KEYFOB_NO_DELAY_enable();
        Thread.sleep(2000);
        sensors.primary_call("65 00 AF", keyfobStay);
        Thread.sleep(1000);
        deleteLogFile(log_path);
        Thread.sleep(1000);
        eventLogsGenerating(log_path,new String[]{
                "SYSTEM_STATUS_CHANGED_TIME value : ARM-STAY-EXIT-DELAY"},1);
        Thread.sleep(1000);
        verify_armstay();
        sensors.primary_call("65 00 AF", keyfobAway);
        Thread.sleep(1000);
        eventLogsGenerating(log_path,new String[]{
                "SYSTEM_STATUS_CHANGED_TIME value : ARM-AWAY-EXIT-DELAY"},1);
        Thread.sleep(1000);
        verify_armaway();
        home.ArwAway_State.click();
        enter_default_user_code();
        verify_disarm();
        System.out.println("*** Pass: If KeyFob Instant Arming is enabled, panel should arm away immediately. ***");}

        @Test(priority = 2)
    public void AS_05() throws Exception {
        logger.info("Verify the panel returns to Arm Stay at the end of the entry delay if the user does not enter a valid user code");
        ARM_STAY();
        Home_Page home = PageFactory.initElements(driver, Home_Page.class);
        home.DISARM.click();
        Thread.sleep(13000);
        verify_armstay();
        sensors.primary_call("65 00 AF", keyfobDisarm);
    }

    public void arm_stay_sensor_event(int zone,int group,String DLID) throws Exception {
        servcall.set_ARM_STAY_NO_DELAY_enable();
        Thread.sleep(2000);
        ARM_STAY();
        verify_armstay();
        sensors.primary_call(DLID, open);
        Thread.sleep(1000);
        sensors.primary_call(DLID, close);
        }

    public void user_history_arm_stay_sensor_event_verification(int zone) throws Exception {
        adc.New_ADC_session_User(login,password);
        Thread.sleep(60000);
        adc.driver1.findElement(By.id("ctl00_HeaderLinks1_imgReload")).click();
        try {
            WebElement history_message_alarm = adc.driver1.findElement(By.xpath("//*[contains(text(), ' Pending Alarm ')]"));
            Assert.assertTrue(history_message_alarm.isDisplayed());
            {
                System.out.println("User website history -> " + " Sensor " + zone + " event: " + history_message_alarm.getText());
            }
        } catch (Exception e) {
            System.out.println("No such element found!!!");
        }
        Thread.sleep(3000);
        delete_from_primary(zone);
           }
    @Test(priority = 3)
    public void AS_06() throws Exception {
        logger.info("Verify the system will go into alarm at the end of the entry delay if a sensor in group 10 is opened in Arm Stay");
        int zone = 10;
        int group = 10;
        delete_from_primary(zone);
        add_primary_call(zone, group,6619296, 1);
        Thread.sleep(1000);
        arm_stay_sensor_event(zone, group, door_window10);
        Thread.sleep(15000);
        verify_in_alarm();
        enter_default_user_code();
        Thread.sleep(2000);
        user_history_arm_stay_sensor_event_verification(zone);}

    @Test(priority = 4)
    public void AS_07() throws Exception {
        logger.info("Verify the system will go into alarm at the end of the entry delay if a sensor in group 12 is opened in Arm Stay");
        int zone = 12;
        int group = 12;
       // delete_from_primary(zone);
        add_primary_call(zone, group,6619297, 1);
        Thread.sleep(1000);
        arm_stay_sensor_event(zone, group, door_window12);
        Thread.sleep(15000);
        verify_in_alarm();
        enter_default_user_code();
        Thread.sleep(2000);
        user_history_arm_stay_sensor_event_verification(zone);}

    @Test(priority = 5)
    public void AS_08() throws Exception {
        logger.info("Verify the system will go into immediate alarm if a sensor in group 14 is opened in Arm Stay");
        int zone = 14;
        int group = 14;
        //delete_from_primary(zone);
        add_primary_call(zone, group,6619299, 1);
        Thread.sleep(1000);
        arm_stay_sensor_event(zone, group, door_window14);
        Thread.sleep(1000);
        verify_in_alarm();
        enter_default_user_code();
        Thread.sleep(2000);
        user_history_arm_stay_sensor_event_verification(zone);}

    @Test(priority = 6)
    public void AS_09() throws Exception {
        logger.info("Verify the system will go into immediate alarm if a sensor in group 13 is opened in Arm Stay");
        int zone = 13;
        int group = 13;
        //delete_from_primary(zone);
        add_primary_call(zone, group,6619298, 1);
        Thread.sleep(1000);
        arm_stay_sensor_event(zone, group, door_window13);
        Thread.sleep(1000);
        verify_in_alarm();
        enter_default_user_code();
        Thread.sleep(2000);
        user_history_arm_stay_sensor_event_verification(zone);}

    @Test(priority = 7)
    public void AS_10() throws Exception {
        logger.info("Verify the system will NOT go into  alarm if a sensor in group 16 is opened in Arm Stay");
        int zone = 16;
        int group = 16;
        //delete_from_primary(zone);
        add_primary_call(zone, group, 6619300, 1);
        Thread.sleep(1000);
        arm_stay_sensor_event(zone,group, door_window16);
        Thread.sleep(1000);
        verify_armstay();
        DISARM();
        delete_from_primary(zone);
        // write mthod whhich check sensor status
    }
    @Test(priority = 8)
    public void AS_12() throws Exception {
        logger.info("Verify the system will NOT go into  alarm if a sensor in group 17 is activated in Arm Stay");
        int zone = 17;
        int group = 17;
        //delete_from_primary(zone);
        add_primary_call(zone, group, 5570629, 2);
        Thread.sleep(1000);
        servcall.set_ARM_STAY_NO_DELAY_enable();
        Thread.sleep(2000);
        ARM_STAY();
        verify_armstay();
        sensors.primary_call(motion17, active);
        sensors.primary_call(motion17, idle);
        Thread.sleep(1000);
        verify_armstay();
        DISARM();
        delete_from_primary(zone);
            // write mthod whhich check sensor status
    }
    @Test(priority = 9)
    public void AS_11() throws Exception {
        logger.info("Verify the system will go into immediate alarm if a sensor in group 15 is activated in Arm Stay");
        int zone = 15;
        int group = 15;
        //delete_from_primary(zone);
        add_primary_call(zone, group, 5570628, 2);
        Thread.sleep(1000);
        servcall.set_ARM_STAY_NO_DELAY_enable();
        Thread.sleep(2000);
        ARM_STAY();
        verify_armstay();
        sensors.primary_call(motion15, active);
        sensors.primary_call(motion15, idle);
        Thread.sleep(1000);
        verify_in_alarm();
        enter_default_user_code();
        user_history_arm_stay_sensor_event_verification(zone);

        // write mthod whhich check sensor status
    }
    @Test(priority = 10)
    public void AS_13() throws Exception {
        logger.info("Verify the system will NOT go into  alarm if a sensor in group 20 is activated in Arm Stay");
        int zone = 20;
        int group = 20;
        ///delete_from_primary(zone);
        add_primary_call(zone, group, 5570630, 2);
        Thread.sleep(1000);
        servcall.set_ARM_STAY_NO_DELAY_enable();
        Thread.sleep(2000);
        ARM_STAY();
        verify_armstay();
        sensors.primary_call(motion20, active);
        sensors.primary_call(motion20, idle);
        Thread.sleep(1000);
        verify_armstay();
        DISARM();
        delete_from_primary(zone);
        // write mthod whhich check sensor status
    }

    @Test(priority = 11)
    public void AS_14() throws Exception {
        logger.info("Verify the system will go into alarm at the end of the entry delay if a sensor in group 35 is Activated in Arm Stay");
        add_primary_call(35, 35, 5570631, 2);
        Thread.sleep(1000);
        servcall.set_ARM_STAY_NO_DELAY_enable();
        Thread.sleep(2000);
        ARM_STAY();
        verify_armstay();
        sensors.primary_call(motion35, active);
        sensors.primary_call(motion35, idle);
        Thread.sleep(13000);
        verify_in_alarm();
        enter_default_user_code();
        user_history_arm_stay_sensor_event_verification(35);

        // write mthod whhich check sensor status
    }
    /*** NOT VALID ***/
   /* @Test(priority = 12)
    public void AS_15() throws Exception {
        logger.info("Verify the system will go into immediate alarm if a sensor in group 15 is activate and that the system can be disarmed before the dialer delay.");
        int zone = 15;
        int group = 15;
        //delete_from_primary(zone);
        add_primary_call(zone, group, 5570628, 2);
        Thread.sleep(1000);
        servcall.set_ARM_STAY_NO_DELAY_enable();
        Thread.sleep(2000);
        ARM_STAY();
        verify_armstay();
        sensors.primary_call(motion15, active);
        sensors.primary_call(motion15, idle);
        Thread.sleep(3000);
        enter_default_user_code();
        verify_armstay();
        DISARM();
        delete_from_primary(zone);
        //user_history_arm_stay_sensor_event_verification(zone);

        // write mthod whhich check sensor status
    }*/

    public void arm_stay_2sensors_event(int zone1, int zone2, String DLID1,String DLID2,String Status1, String Status2) throws Exception {
        servcall.set_ARM_STAY_NO_DELAY_enable();
        Thread.sleep(2000);
        ARM_STAY();
        verify_armstay();
        sensors.primary_call(DLID1, open);
        Thread.sleep(1000);
        sensors.primary_call(DLID1, close);
        Thread.sleep(3000);
        sensors.primary_call(DLID2, open);
        Thread.sleep(15000);
          Home_Page home_page = PageFactory.initElements(driver, Home_Page.class);
        List<WebElement> events = driver.findElements(By.id("com.qolsys:id/tv_status"));
        for (int j = 0; j < events.size(); j++)
            if (events.get(j).getText().equals(Status1)) {
                logger.info("Pass: Correct status is " + Status1);
            }else { take_screenshot();
                logger.info("Failed: Incorrect status: " + home_page.Red_banner_sensor_status.getText());}
        verify_in_alarm();
         /* enter_default_user_code();
        delete_from_primary(zone1);
        delete_from_primary(zone2);

        adc.New_ADC_session_User(login,password);
        Thread.sleep(60000);
        adc.driver1.findElement(By.id("ctl00_HeaderLinks1_imgReload")).click();
        try {
            WebElement history_message_alarm = adc.driver1.findElement(By.xpath("//*[contains(text(), 'Sensor 10 ')]"));
            Assert.assertTrue(history_message_alarm.isDisplayed());
            {
                System.out.println("User website history -> " + " Sensor " + zone1 + " event: " + history_message_alarm.getText());
            }
        } catch (Exception e) {
            System.out.println("No such element found!!!");
        }
        try {
            WebElement history_message_alarm = adc.driver1.findElement(By.xpath("//*[contains(text(), ' Pending Alarm ')]"));
            Assert.assertTrue(history_message_alarm.isDisplayed());
            {
                System.out.println("User website history -> " + " Sensor " + zone2 + " event: " + history_message_alarm.getText());
            }
        } catch (Exception e) {
            System.out.println("No such element found!!!");
        }
        Thread.sleep(3000);*/
            }
    @Test(priority = 13)
    public void AS_16() throws Exception {
        logger.info("Verify the system reports alarm on both sensors (10 and 12 groups) at the end of the entry delay ");
        add_primary_call(10, 10,6619296, 1);
        add_primary_call(12, 12,6619297, 1);
        arm_stay_2sensors_event(10,12,door_window10,door_window12, "Open","Open");
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
        driver.quit();

    }

    @AfterMethod
    public void webDriverQuit() {
        adc.driver1.quit();
    }





}