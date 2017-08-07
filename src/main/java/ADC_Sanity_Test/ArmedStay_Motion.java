package ADC_Sanity_Test;

import ADC.ADC;
import Panel.PanelInfo_ServiceCalls;
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
import java.util.concurrent.TimeUnit;

/**
 * Created by qolsys on 7/27/17.
 */
public class ArmedStay_Motion extends Setup{

    String page_name = "Arm Stay mode motion sensor testing";
    Logger logger = Logger.getLogger(page_name);
    Sensors sensors = new Sensors();
    ADC adc = new ADC();
    PanelInfo_ServiceCalls servcall = new PanelInfo_ServiceCalls();

    /*** If you want to run tests only on the panel, please set ADCexecute value to false ***/
    String ADCexecute = "true";

    public ArmedStay_Motion() throws IOException, BiffException {}

    private int Normal_Exit_Delay = 10;
    private int Normal_Entry_Delay = 11;
    private int Long_Exit_Delay = 12;
    private int Long_Entry_Delay = 13;
    private String activate = "02 01";
    private String close = "00 01";
    private String tamper = "01 01";
    String AccountID = adc.getAccountId();

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
    public void ADC_verification (String string, String string1) throws IOException, InterruptedException {
        String[] message = {string, string1};

        if (ADCexecute.equals("true")) {
            adc.New_ADC_session(AccountID);
            adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("History"))).click();
            Thread.sleep(3000);
            adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_phBody_butSearch"))).click();
            Thread.sleep(5000);
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

    public void ArmStay_Activate_During_Delay(int group, String DLID, String element_to_verify, String element_to_verify2 ) throws Exception {
        logger.info("ArmStay During Delay Activate Group " + group + " motion sensor");
        ARM_STAY();
        logger.info("Activate a sensor");
        TimeUnit.SECONDS.sleep(Long_Exit_Delay/2);
        sensors.primary_call(DLID, activate);
        TimeUnit.SECONDS.sleep(Long_Exit_Delay);
        verify_armstay();
        DISARM();
        Thread.sleep(2000);

        ADC_verification(element_to_verify, element_to_verify2);
        //sensors.primary_call(DLID, close);
        //Thread.sleep(2000);
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

        //servcall.set_AUTO_STAY(0);
        servcall.set_ARM_STAY_NO_DELAY_disable();
    }
    @BeforeMethod
    public  void webDriver(){
        adc.webDriverSetUp();
    }

    @Test
    public void addSensors() throws IOException, InterruptedException {
        Thread.sleep(2000);
        add_primary_call(1, 15, 5570628, 2);
        add_primary_call(2, 17, 5570629, 2);
        add_primary_call(3, 20, 5570630, 2);
        add_primary_call(4, 25, 5570631, 2);
        add_primary_call(5, 35, 5570632, 2);


        adc.New_ADC_session(adc.getAccountId());
        Thread.sleep(2000);
        adc.driver1.findElement(By.partialLinkText("Sensors")).click();
        Thread.sleep(2000);
        adc.Request_equipment_list();
    }

    @Test (dependsOnMethods = {"addSensors"}, retryAnalyzer = RetryAnalizer.class)
    public void ArmStayDelay_15() throws Exception {
        ArmStay_Activate_During_Delay(15, "55 00 44", "//*[contains(text(), '(Sensor 1) Activated')]", "//*[contains(text(), 'Armed Stay')]");
    }
    @Test (priority = 1, retryAnalyzer = RetryAnalizer.class)
    public void ArmStayDelay_17() throws Exception {
        ArmStay_Activate_During_Delay(17, "55 00 54", "//*[contains(text(), '(Sensor 2) Activated')]", "//*[contains(text(), 'Armed Stay')]");
    }




    @AfterTest
    public void tearDown() throws IOException, InterruptedException {
        driver.quit();
//        for (int i= 5; i>0; i--) {
//            delete_from_primary(i);
//        }
    }

    @AfterMethod
    public void webDriverQuit(){
        adc.driver1.quit();
    }







}