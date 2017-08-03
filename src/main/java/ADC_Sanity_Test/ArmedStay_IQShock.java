package ADC_Sanity_Test;

import ADC.ADC;
import Panel.Home_Page;
import jxl.read.biff.BiffException;
import org.apache.log4j.Logger;
import Panel.Setup;
import Sensors.Sensors;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.*;

import javax.swing.plaf.InternalFrameUI;
import java.io.IOException;

public class ArmedStay_IQShock extends Setup {

    String page_name = "ArmedStay_IQShock";
    Logger logger = Logger.getLogger(page_name);
    Sensors sensors = new Sensors();
    ADC adc = new ADC();
    String AccountID = adc.getAccountId();

    public ArmedStay_IQShock() throws IOException, BiffException {}

    private int Normal_Exit_Delay = 30000;
    private String open = "06 00";
    private String close = "04 01";
    private String activate = "02 01";
    private String tamper = "01 01";

    public void add_primary_call(int zone, int group, int sensor_dec, int sensor_type) throws IOException {
        String add_primary = " shell service call qservice 50 i32 " + zone + " i32 " + group + " i32 " + sensor_dec + " i32 " + sensor_type;
        rt.exec(adbPath + add_primary);
        // shell service call qservice 50 i32 2 i32 10 i32 6619296 i32 1
    }

    public void delete_from_primary(int zone) throws IOException, InterruptedException {
        String deleteFromPrimary = " shell service call qservice 51 i32 " + zone;
        rt.exec(adbPath + deleteFromPrimary);
        System.out.println(deleteFromPrimary);}

    public void ArmStay_Tamper_Sensor_during_Exit_Delay_Alarm(String DLID, String element_to_verify) throws Exception {
        ARM_STAY();
        Thread.sleep(3000);
        logger.info("Activate a sensor");
        sensors.primary_call(DLID, tamper);
        Thread.sleep(2000);
        verify_status_tampered();
        sensors.primary_call(DLID, close);
        Thread.sleep(2000);
        verify_in_alarm();
        enter_default_user_code();
        /*** ADC website verification ***/
        adc.New_ADC_session(AccountID);
        adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("History"))).click();
        Thread.sleep(10000);
        try {
            WebElement history_message = adc.driver1.findElement(By.xpath(element_to_verify));
            Assert.assertTrue(history_message.isDisplayed());
            {
                logger.info("Pass: message is displayed " + history_message.getText());
            }
        } catch (Exception e) {
            logger.info("***No such element found!***");
        }
        Thread.sleep(4000);
    }

    @BeforeTest
    public void capabilities_setup() throws Exception {
        setup_driver(get_UDID(), "http://127.0.1.1", "4723");
        setup_logger(page_name);
    }

    @BeforeMethod
    public void webDriver() { adc.webDriverSetUp(); }

    //Test Sensor Group 13

    @Test(groups = {"13"})
    public void addSensorsGroup13() throws IOException, InterruptedException {
        Thread.sleep(2000);
        add_primary_call(33, 13, 6684828, 107);
        Thread.sleep(1000);
        add_primary_call(34, 13, 6684829, 107);
    }

    @Test(priority = 2, dependsOnGroups = {"13"})
    public void TestSensor33G13() throws Exception {
        ArmStay_Tamper_Sensor_during_Exit_Delay_Alarm("66 00 C9", "//*[contains(text(), 'Sensor 33 Group 13 Tamper**')]");
    }

    @Test(priority = 3, dependsOnGroups = {"13"})
    public void TestSensor34G13() throws Exception {
        ArmStay_Tamper_Sensor_during_Exit_Delay_Alarm("66 00 D9", "//*[contains(text(), 'Sensor 34 Group 13 Tamper**')]");
    }

    @Test(dependsOnGroups = {"13"})
    public void deleteIQ_Shock_G_13 () throws IOException, InterruptedException {
        for (int i = 34; i >= 33; i--) {
            delete_from_primary(i);
        }
    }

    //Test Sensor Group 17

    @Test(priority = 4)
    public void addSensorsGroup17() throws IOException, InterruptedException {
        Thread.sleep(2000);
        add_primary_call(35, 17, 6684828, 107);
        Thread.sleep(1000);
        add_primary_call(36, 17, 6684829, 107);
    }

    @Test(priority = 5, dependsOnMethods = {"addSensorsGroup17"})
    public void TestSensor35G17() throws Exception {
        ArmStay_Tamper_Sensor_during_Exit_Delay_Alarm("66 00 C9", "//*[contains(text(), 'Sensor 35 Group 17 Tamper**')]");
    }

    @Test(priority =6, dependsOnMethods = {"addSensorsGroup17"})
    public void TestSensor36G17() throws Exception {
        ArmStay_Tamper_Sensor_during_Exit_Delay_Alarm("66 00 D9", "//*[contains(text(), 'Sensor 36 Group 17 Tamper**')]");
    }

    @Test
    public void deleteIQ_Shock_G_17 () throws IOException, InterruptedException {
        for (int i = 36; i >= 35; i--) {
            delete_from_primary(i);
        }
    }

    @AfterMethod
    public void webDriverQuit() {
        adc.driver1.quit();
    }
}