package ADC_Sanity_Test;

import ADC.ADC;
import Panel.Emergency_Page;
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

/**
 * Created by qolsysauto on 8/7/17.
 */
public class ArmedStay_Auxiliary extends Setup{
    // PRECONDITIONS: Disable SIA limits, set Entry-Exit Delay time to 30, 31, 32, 33 sec; Disable ArmStay No-Delay setting
    /*** If you want to run tests only on the panel, please set ADCexecute value to false ***/
    String ADCexecute = "true";
    public ArmedStay_Auxiliary() throws IOException, BiffException {}
    String page_name = "ADC Smoke Test: Auxiliary Arm Stay";
    Logger logger = Logger.getLogger(page_name);
    Sensors sensors = new Sensors();
    ADC adc = new ADC();
    String AccountID = adc.getAccountId();

    private String activate = "03 01";

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
        setArmStay_NoDelay("Disable");
        setAutoStay("Disable");
    }
    @BeforeMethod
    public  void webDriver(){
        adc.webDriverSetUp();
    }

    @Test
    public void addSensors() throws IOException, InterruptedException {
        Thread.sleep(2000);
        add_primary_call(43, 6, 6361649, 21);
        add_primary_call(44, 0, 6361650, 21);
        add_primary_call(48, 1, 6361652, 21);
        add_primary_call(49, 2, 6361653, 21);
        add_primary_call(50, 4, 6361654, 21);

        adc.New_ADC_session(adc.getAccountId());
        Thread.sleep(10000);
        adc.driver1.findElement(By.partialLinkText("Sensors")).click();
        Thread.sleep(10000);
        adc.Request_equipment_list();

    }

    public void ArmStay_Activate_Silent_Sensor(int group, String DLID, String element_to_verify1, String element_to_verify2) throws Exception {
        logger.info("ArmStay -Activate Group " + group + " Silent Auxiliary Police Pendant during Arm Stay");
        ARM_STAY();
        Thread.sleep(33000);
        logger.info("Activate a sensor");
        sensors.primary_call(DLID, activate);
        Thread.sleep(2000);
        verify_armstay();
        DISARM();
        Thread.sleep(15000);
        // ADC website verification
        ADC_verification(element_to_verify1, element_to_verify2);
    }

    public void ArmStay_Activate_Medical_Sensor(int group, String DLID, String element_to_verify1, String element_to_verify2) throws Exception {
        logger.info("ArmStay -Activate Group " + group + " Auxiliary Medical Pendant during Arm Stay");
        Emergency_Page emg = PageFactory.initElements(driver, Emergency_Page.class);
        ARM_STAY();
        Thread.sleep(33000);
        logger.info("Activate a sensor");
        sensors.primary_call(DLID, activate);
        Thread.sleep(2000);
        element_verification(emg.Auxiliary_title, "Auxiliary Alarmed");
        logger.info("Cancel Emergency Alarm");
        emg.Cancel_Emergency.click();
        enter_default_user_code();
        Thread.sleep(25000);
        // ADC website verification
        ADC_verification(element_to_verify1, element_to_verify2);

    }

    public void ArmStay_Activate_Police_Sensor(int group, String DLID, String element_to_verify1, String element_to_verify2) throws Exception {
        logger.info("ArmStay -Activate Group " + group + " Auxiliary Police Pendant during Arm Stay");
        Emergency_Page emg = PageFactory.initElements(driver, Emergency_Page.class);
        ARM_STAY();
        Thread.sleep(33000);
        logger.info("Activate a sensor");
        sensors.primary_call(DLID, activate);
        Thread.sleep(2000);
        element_verification(emg.Police_title, "Auxiliary Alarmed");
        logger.info("Cancel Emergency Alarm");
        emg.Cancel_Emergency.click();
        enter_default_user_code();
        Thread.sleep(25000);
        // ADC website verification
        ADC_verification(element_to_verify1, element_to_verify2);
    }


    @Test (dependsOnMethods = {"addSensors"}, retryAnalyzer = RetryAnalizer.class)
    public void ArmStayActivateSensor_2() throws Exception {
        ArmStay_Activate_Silent_Sensor(2, "61 12 35", "//*[contains(text(), 'Auxiliary Pendant (Sensor 1) Pending Alarm')]", "//*[contains(text(), 'Sensor 1 Alarm')]");
    }
    @Test  (priority = 1, retryAnalyzer = RetryAnalizer.class)
    public  void ArmStayActivateSensor_4() throws Exception {
        ArmStay_Activate_Medical_Sensor(4,"61 12 36", "//*[contains(text(), 'Auxiliary Pendant (Sensor 1) Pending Alarm')]", "//*[contains(text(), 'Sensor 1 Alarm')]");
    }
    @Test (priority = 2, retryAnalyzer = RetryAnalizer.class)
    public void ArmStayActivateSensor_6() throws Exception {
        ArmStay_Activate_Medical_Sensor(6,"61 12 31", "//*[contains(text(), 'Auxiliary Pendant (Sensor 1) Pending Alarm')]", "//*[contains(text(), 'Sensor 1 Alarm')]");
    }
    @Test (priority = 3, retryAnalyzer = RetryAnalizer.class)
    public void ArmStayActivateSensor_1() throws Exception {
        ArmStay_Activate_Police_Sensor(1,"61 12 34", "//*[contains(text(), 'Auxiliary Pendant (Sensor 1) Pending Alarm')]", "//*[contains(text(), 'Auxiliary Pendant (Sensor 1) Police Panic')]");
    }
    @Test  (priority = 4, retryAnalyzer = RetryAnalizer.class)
    public void ArmStayActivateSensor_0() throws Exception {
        ArmStay_Activate_Police_Sensor(0,"61 12 32", "//*[contains(text(), 'Auxiliary Pendant (Sensor 1) Pending Alarm')]", "//*[contains(text(), 'Auxiliary Pendant (Sensor 1) Police Panic')]");
    }



    @AfterTest
    public void tearDown() throws IOException, InterruptedException {
        driver.quit();
        for (int i= 50; i>0; i--) {
            delete_from_primary(i);
        }
    }
    @AfterMethod
    public void webDriverQuit(){
        adc.driver1.quit();
    }

}
