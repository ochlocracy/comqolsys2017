package ADC_Sanity_Test;

import ADC.ADC;
import Panel.Home_Page;
import Panel.PanelInfo_ServiceCalls;
import Panel.Setup;
import Sensors.Sensors;
import jxl.read.biff.BiffException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.*;

import java.io.IOException;

/** Estimate execution time: 31 min, just panel: 22 min  ***/

public class ArmedAway_Contact extends Setup{

    String page_name = "Arm Away mode contact sensor testing";
    Logger logger = Logger.getLogger(page_name);
    Sensors sensors = new Sensors();
    ADC adc = new ADC();
    PanelInfo_ServiceCalls servcall = new PanelInfo_ServiceCalls();

    /*** If you want to run tests only on the panel, please set ADCexecute value to false ***/
    String ADCexecute = "false";

    public ArmedAway_Contact() throws IOException, BiffException {}

    private int Normal_Exit_Delay = 10;
    private int Normal_Entry_Delay = 11;
    private int Long_Exit_Delay = 12;
    private int Long_Entry_Delay = 13;
    private String open = "06 00";
    private String close = "04 00";
    private String activate = "02 01";
    private String restore = "00 01";
    private String tamper = "01 00";
    String AccountID = adc.getAccountId();

    public void add_primary_call(int zone, int group, int sensor_dec, int sensor_type) throws IOException {
        String add_primary = " shell service call qservice 50 i32 " + zone + " i32 " + group + " i32 " + sensor_dec + " i32 " + sensor_type;
        rt.exec(adbPath + add_primary);
        // shell service call qservice 50 i32 2 i32 10 i32 6619296 i32 1
    }
    public void delete_from_primary(int zone) throws IOException, InterruptedException {
        String deleteFromPrimary = " shell service call qservice 51 i32 " + zone;
        rt.exec(adbPath + deleteFromPrimary);
        System.out.println(deleteFromPrimary);}
    public void ADC_verification (String string, String string1) throws IOException, InterruptedException {
        String[] message = {string, string1};

        if (ADCexecute.equals("true")) {
            adc.New_ADC_session(AccountID);
            adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("History"))).click();
            Thread.sleep(10000);
            for (int i =0; i< message.length; i++) {
                try {
                    WebElement history_message = adc.driver1.findElement(By.xpath(message[i]));
                    Assert.assertTrue(history_message.isDisplayed());
                    {
                        System.out.println("Pass: message is displayed " + history_message.getText());
                    }
                } catch (Exception e) {
                    System.out.println("***No such element found!***");
                }
            }
        }else{
            System.out.println("Set execute to TRUE to run ADC verification part");
        }
        Thread.sleep(2000);
    }

    @BeforeTest
    public void capabilities_setup() throws Exception {
        setup_driver(get_UDID(),"http://127.0.1.1", "4723");
        setup_logger(page_name);
        servcall.set_NORMAL_ENTRY_DELAY(Normal_Exit_Delay);
        Thread.sleep(1000);
        servcall.set_NORMAL_EXIT_DELAY(Normal_Entry_Delay);
        Thread.sleep(1000);
        servcall.set_LONG_ENTRY_DELAY(Long_Exit_Delay);
        Thread.sleep(1000);
        servcall.set_LONG_EXIT_DELAY(Long_Entry_Delay);

        servcall.set_AUTO_STAY(0);
        servcall.set_ARM_STAY_NO_DELAY_disable();
    }
    @BeforeMethod
    public  void webDriver(){
        adc.webDriverSetUp();
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
        add_primary_call(9, 17, 5570628, 2);
        add_primary_call(10, 20, 5570629, 2);


        adc.New_ADC_session(adc.getAccountId());
        Thread.sleep(2000);
        adc.driver1.findElement(By.partialLinkText("Sensors")).click();
        Thread.sleep(2000);
        adc.Request_equipment_list();
    }

    public void ArmAway_Open_Close_during_Exit_Delay(int group, String DLID, String element_to_verify, String element_to_verify2 ) throws Exception {
        logger.info("ArmAway -Open/Close Group " + group + " contact sensor during exit delay");
        Home_Page home = PageFactory.initElements(driver, Home_Page.class);
        ARM_AWAY(Long_Exit_Delay/2);
        logger.info("Open/Close a sensor");
        sensors.primary_call(DLID, open);
        Thread.sleep(2000);
        sensors.primary_call(DLID, close);
        Thread.sleep(17000);
        verify_armaway();
        Thread.sleep(5000);
        home.ArwAway_State.click();
        enter_default_user_code();
        Thread.sleep(2000);

        ADC_verification(element_to_verify, element_to_verify2);

    }
    public void ArmAway_Open_Close_during_Exit_Delay_Alarm(int group, String DLID, String element_to_verify, String element_to_verify2) throws Exception {
        logger.info("ArmAway - Open/Close Group " + group + " contact sensor during exit delay");
        ARM_AWAY(Long_Exit_Delay / 2);
        logger.info("Open/Close a sensor");
        sensors.primary_call(DLID, open);
        Thread.sleep(2000);
        sensors.primary_call(DLID, close);
        Thread.sleep(17000);
        verify_in_alarm();
        Thread.sleep(2000);
        enter_default_user_code();
        Thread.sleep(2000);

        ADC_verification(element_to_verify, element_to_verify2);
    }

    @Test (dependsOnMethods = {"addSensors"}, retryAnalyzer = RetryAnalizer.class)
    public void ArmAwayExitDelay_10() throws Exception {
        ArmAway_Open_Close_during_Exit_Delay(10, "65 00 0A", "//*[contains(text(), 'Door/Window 1  (Sensor 1) Opened/Closed')]", "//*[contains(text(), 'Armed Away')]");
    }
    @Test (priority = 1, retryAnalyzer = RetryAnalizer.class)
    public void ArmAwayExitDelay_12() throws Exception {
        ArmAway_Open_Close_during_Exit_Delay(12, "65 00 1A", "//*[contains(text(), 'Door/Window 2  (Sensor 2) Opened/Closed')]", "//*[contains(text(), 'Armed Away')]");
    }
    @Test(priority = 2, retryAnalyzer = RetryAnalizer.class)
    public void ArmAwayExitDelay_13 () throws Exception {
        ArmAway_Open_Close_during_Exit_Delay_Alarm(13, "65 00 2A","//*[contains(text(), 'Door/Window 3  (Sensor 3) Opened/Closed')]","//*[contains(text(), 'Armed Away')]");
    }
    @Test(priority = 3, retryAnalyzer = RetryAnalizer.class)
    public void  ArmAwayExitDelay_14() throws Exception {
        ArmAway_Open_Close_during_Exit_Delay(14, "65 00 3A", "//*[contains(text(), 'Door/Window 4  (Sensor 4) Opened/Closed')]", "//*[contains(text(), 'Armed Away')]");
    }
    @Test(priority = 4, retryAnalyzer = RetryAnalizer.class)
    public void  ArmAwayExitDelay_16() throws Exception {
        ArmAway_Open_Close_during_Exit_Delay(16, "65 00 4A", "//*[contains(text(), 'Door/Window 5  (Sensor 5) Opened/Closed')]", "//*[contains(text(), 'Armed Away')]");
    }
    @Test(priority = 5, retryAnalyzer = RetryAnalizer.class)
    public void  ArmAwayExitDelay_8() throws Exception {
        ArmAway_Open_Close_during_Exit_Delay_Alarm(8, "65 00 5A","//*[contains(text(), 'Door/Window 6 (Sensor 6) Alarm')]", "//*[contains(text(), 'Armed Away')]");
    }
    @Test(priority = 6, retryAnalyzer = RetryAnalizer.class)
    public void  ArmAwayExitDelay_9() throws Exception {
        ArmAway_Open_Close_during_Exit_Delay_Alarm(9, "65 00 6A","//*[contains(text(), 'Delayed alarm on sensor 7 in partition 1')]", "//*[contains(text(), 'Armed Away')]");
    }
    @Test(priority = 7, retryAnalyzer = RetryAnalizer.class)
    public void  ArmAwayExitDelay_25() throws Exception {
        ArmAway_Open_Close_during_Exit_Delay(25,"65 00 7A",  "//*[contains(text(), 'Door/Window 8  (Sensor 8) Opened/Closed')]","//*[contains(text(), 'Armed Away')]");
    }

    /* ARM AWAY Open-Close sensors*/

    public void ArmAway_Open_Close(int group, String DLID, String element_to_verify, String element_to_verify2 ) throws Exception {
        logger.info("ArmAway -Open/Close Group " + group + " contact sensor during exit delay");
        Home_Page home = PageFactory.initElements(driver, Home_Page.class);
        ARM_AWAY(Long_Exit_Delay);
        Thread.sleep(2000);
        logger.info("Open/Close a sensor");
        sensors.primary_call(DLID, open);
        Thread.sleep(2000);
        sensors.primary_call(DLID, close);
        Thread.sleep(17000);
        verify_in_alarm();
        Thread.sleep(2000);
        enter_default_user_code();
        Thread.sleep(2000);

        ADC_verification(element_to_verify, element_to_verify2);
    }
    public void ArmAway_Open_Close_ArmAway(int group, String DLID, String element_to_verify, String element_to_verify2 ) throws Exception {
        logger.info("ArmAway -Open/Close Group " + group + " contact sensor during exit delay");
        Home_Page home = PageFactory.initElements(driver, Home_Page.class);
        ARM_AWAY(Long_Exit_Delay);
        logger.info("Open/Close a sensor");
        sensors.primary_call(DLID, open);
        Thread.sleep(2000);
        sensors.primary_call(DLID, close);
        Thread.sleep(17000);
        verify_armaway();
        Thread.sleep(2000);
        home.ArwAway_State.click();
        enter_default_user_code();
        Thread.sleep(2000);

        ADC_verification(element_to_verify, element_to_verify2);
    }

    @Test (priority = 8, retryAnalyzer = RetryAnalizer.class)
    public void ArmAway_10() throws Exception {
        ArmAway_Open_Close(10, "65 00 0A", "//*[contains(text(), 'Door/Window 1  (Sensor 1) Opened/Closed')]", "//*[contains(text(), 'Delayed alarm on sensor 1 in partition 1')]");
    }
    @Test (priority = 9, retryAnalyzer = RetryAnalizer.class)
    public void ArmAway_12() throws Exception {
        ArmAway_Open_Close(12, "65 00 1A", "//*[contains(text(), 'Door/Window 2  (Sensor 2) Opened/Closed')]", "//*[contains(text(), 'Delayed alarm on sensor 2 in partition 2')]");
    }
    @Test (priority = 10, retryAnalyzer = RetryAnalizer.class)
    public void ArmAway_13() throws Exception {
        ArmAway_Open_Close(13, "65 00 2A", "//*[contains(text(), 'Door/Window 3  (Sensor 3) Opened/Closed')]", "//*[contains(text(), 'Pending Alarm')]");
    }
    @Test (priority = 11, retryAnalyzer = RetryAnalizer.class)
    public void ArmAway_14() throws Exception {
        ArmAway_Open_Close(14, "65 00 3A", "//*[contains(text(), 'Door/Window 4  (Sensor 4) Opened/Closed')]", "//*[contains(text(), 'Pending Alarm')]");
    }
    @Test (priority = 12, retryAnalyzer = RetryAnalizer.class)
    public void ArmAway_16() throws Exception {
        ArmAway_Open_Close(16, "65 00 4A", "//*[contains(text(), 'Door/Window 5  (Sensor 5) Opened/Closed')]", "//*[contains(text(), 'Pending Alarm')]");
    }
    @Test (priority = 13, retryAnalyzer = RetryAnalizer.class)
    public void ArmAway_8() throws Exception {
        ArmAway_Open_Close(8, "65 00 5A", "//*[contains(text(), 'Delayed alarm')]", "//*[contains(text(), 'Pending Alarm')]");
    }
    @Test (priority = 14, retryAnalyzer = RetryAnalizer.class)
    public void ArmAway_9() throws Exception {
        ArmAway_Open_Close(9, "65 00 6A", "//*[contains(text(), 'Entry delay on sensor 7')]", "//*[contains(text(), 'Pending Alarm')]");
    }
    @Test (priority = 15, retryAnalyzer = RetryAnalizer.class)
    public void ArmAway_25() throws Exception {
        ArmAway_Open_Close_ArmAway(25, "65 00 7A", "//*[contains(text(), 'Door/Window 8  (Sensor 8) Opened/Closed')]", "//*[contains(text(), 'Armed Away')]");
    }

    /*** Contact + follower ***/

    public void ArmAway_Open_Close_Follower_Motion(int group, int group2, String DLID, String DLID2, String element_to_verify, String element_to_verify2 ) throws Exception {
        logger.info("ArmAway -Open/Close Group " + group + " contact sensor followed by motion " + group2);
        ARM_AWAY(Long_Exit_Delay);
        Thread.sleep(2000);
        logger.info("Open/Close a sensor");
        sensors.primary_call(DLID, open);
        Thread.sleep(1000);
        sensors.primary_call(DLID, close);
        Thread.sleep(4000);
        sensors.primary_call(DLID2, activate);
        Thread.sleep(2000);
        sensors.primary_call(DLID2, restore);
        Thread.sleep(5000);
        verify_in_alarm();
        Thread.sleep(2000);
        enter_default_user_code();
        Thread.sleep(2000);

        ADC_verification(element_to_verify, element_to_verify2);
    }
    public void ArmAway_Open_Close_Follower_Contact(int group, int group2, String DLID, String DLID2, String element_to_verify, String element_to_verify2 ) throws Exception {
        logger.info("ArmAway -Open/Close Group " + group + " contact sensor followed by contact " + group2);
        ARM_AWAY(Long_Exit_Delay);
        Thread.sleep(2000);
        logger.info("Open/Close a sensor");
        sensors.primary_call(DLID, open);
        Thread.sleep(1000);
        sensors.primary_call(DLID, close);
        Thread.sleep(4000);
        sensors.primary_call(DLID2, open);
        Thread.sleep(2000);
        sensors.primary_call(DLID2, close);
        Thread.sleep(5000);
        verify_in_alarm();
        Thread.sleep(2000);
        enter_default_user_code();
        Thread.sleep(2000);

        ADC_verification(element_to_verify, element_to_verify2);
    }

    @Test (priority = 16, retryAnalyzer = RetryAnalizer.class)
    public void ArmAway_10_follower_motion17() throws Exception {
        ArmAway_Open_Close_Follower_Motion(10, 17,"65 00 0A", "55 00 44", "//*[contains(text(), 'Door/Window 1  (Sensor 1) Opened/Closed')]","//*[contains(text(), 'Pending Alarm')]" );
    }
    @Test (priority = 17, retryAnalyzer = RetryAnalizer.class)
    public void ArmAway_10_follower_motion20() throws Exception {
        ArmAway_Open_Close_Follower_Motion(10, 20, "65 00 0A", "55 00 54", "//*[contains(text(), 'Door/Window 1  (Sensor 1) Opened/Closed')]","//*[contains(text(), 'Pending Alarm')]" );
    }
    @Test (priority = 18, retryAnalyzer = RetryAnalizer.class)
    public void ArmAway_10_follower_contact12() throws Exception {
        ArmAway_Open_Close_Follower_Contact(10, 12, "65 00 0A", "65 00 1A", "//*[contains(text(), 'Door/Window 1  (Sensor 1) Opened/Closed')]","//*[contains(text(), 'Delayed alarm on sensor 2 in partition 1')]" );
    }
    @Test (priority = 19, retryAnalyzer = RetryAnalizer.class)
    public void ArmAway_10_follower_contact14() throws Exception {
        ArmAway_Open_Close_Follower_Contact(10, 14, "65 00 0A", "65 00 3A", "//*[contains(text(), 'Door/Window 1  (Sensor 1) Opened/Closed')]","//*[contains(text(), 'Delayed alarm on sensor 4 in partition 1')]" );
    }

    /*** ARM AWAY Tamper sensors ***/

    public void ArmAway_Tamper(int group, String DLID, String element_to_verify, String element_to_verify1 ) throws Exception {
        logger.info("ArmAway Tamper Group " + group + " contact sensor");
        ARM_AWAY(Long_Exit_Delay);
        Thread.sleep(2000);
        logger.info("Open/Close a sensor");
        sensors.primary_call(DLID, tamper);
        Thread.sleep(2000);
        sensors.primary_call(DLID, close);
        Thread.sleep(17000);
        verify_in_alarm();
        Thread.sleep(2000);
        enter_default_user_code();
        Thread.sleep(2000);

        ADC_verification(element_to_verify, element_to_verify1);

    }
    public void ArmAway_Tamper_ArmAway(int group, String DLID, String element_to_verify, String element_to_verify1 ) throws Exception {
        Home_Page home = PageFactory.initElements(driver, Home_Page.class);
        logger.info("ArmAway Tamper Group " + group + " contact sensor");
        ARM_AWAY(Long_Exit_Delay);
        Thread.sleep(2000);
        logger.info("Open/Close a sensor");
        sensors.primary_call(DLID, tamper);
        Thread.sleep(2000);
        sensors.primary_call(DLID, close);
        Thread.sleep(17000);
        verify_armaway();
        Thread.sleep(2000);
        home.ArwAway_State.click();
        enter_default_user_code();
        Thread.sleep(2000);

        ADC_verification(element_to_verify, element_to_verify1);

    }

    @Test (priority = 20, retryAnalyzer = RetryAnalizer.class)
    public void ArmAway_Tamper_10() throws Exception {
        ArmAway_Tamper(10, "65 00 0A", "//*[contains(text(), 'Sensor 1 Tamper**')]", "//*[contains(text(), 'Sensor 1 End of tamper')]");
    }
    @Test (priority = 21, retryAnalyzer = RetryAnalizer.class)
    public void ArmAway_Tamper_12() throws Exception {
        ArmAway_Tamper(12, "65 00 1A", "//*[contains(text(), 'Sensor 2 Tamper**')]", "//*[contains(text(), 'Sensor 2 End of tamper')]");
    }
    @Test (priority = 22, retryAnalyzer = RetryAnalizer.class)
    public void ArmAway_Tamper_13() throws Exception {
        ArmAway_Tamper(13, "65 00 2A", "//*[contains(text(), 'Sensor 3 Tamper**')]", "//*[contains(text(), 'Sensor 3 End of tamper')]");
    }
    @Test (priority = 23, retryAnalyzer = RetryAnalizer.class)
    public void ArmAway_Tamper_14() throws Exception {
        ArmAway_Tamper(14, "65 00 3A", "//*[contains(text(), 'Sensor 4 Tamper**')]", "//*[contains(text(), 'Sensor 4 End of tamper')]");
    }
    @Test (priority = 24, retryAnalyzer = RetryAnalizer.class)
    public void ArmAway_Tamper_16() throws Exception {
        ArmAway_Tamper(16, "65 00 4A", "//*[contains(text(), 'Sensor 5 Tamper**')]", "//*[contains(text(), 'Sensor 5 End of tamper')]");
    }
    @Test (priority = 25, retryAnalyzer = RetryAnalizer.class)
    public void ArmAway_Tamper_8() throws Exception {
        ArmAway_Tamper(8, "65 00 5A", "//*[contains(text(), 'Sensor 6 Tamper**')]", "//*[contains(text(), 'Sensor 6 End of tamper')]");
    }
    @Test (priority = 26, retryAnalyzer = RetryAnalizer.class)
    public void ArmAway_Tamper_9() throws Exception {
        ArmAway_Tamper(9, "65 00 6A", "//*[contains(text(), 'Sensor 7 Tamper**')]", "//*[contains(text(), 'Sensor 7 End of tamper')]");
    }
    @Test (priority = 27, retryAnalyzer = RetryAnalizer.class)
    public void ArmAway_Tamper_25() throws Exception {
        ArmAway_Tamper_ArmAway(25, "65 00 7A", "//*[contains(text(), 'Sensor 8 Tamper**')]", "//*[contains(text(), 'Sensor 8 End of tamper')]");
    }

    @AfterTest
    public void tearDown() throws IOException, InterruptedException {
        driver.quit();
        for (int i= 10; i>0; i--) {
            delete_from_primary(i);
        }
    }
    @AfterMethod
    public void webDriverQuit(){
        adc.driver1.quit();
    }
}