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
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.swing.plaf.InternalFrameUI;
import java.io.IOException;

public class ArmedStay_IQShock extends Setup {

    String page_name = "ArmedStay_IQShock";
    Logger logger = Logger.getLogger(page_name);
    Sensors sensors = new Sensors();
    ADC adc = new ADC();
    String AccountID = adc.getAccountId();
    private int Normal_Exit_Delay = 30000;
    private String open = "06 00";
    private String close = "04 01";
    private String activate = "02 01";
    private String tamper = "01 01";

    public ArmedStay_IQShock() throws IOException, BiffException {
    }

    public void add_primary_call(int zone, int group, int sensor_dec, int sensor_type) throws IOException {
        String add_primary = " shell service call qservice 50 i32 " + zone + " i32 " + group + " i32 " + sensor_dec + " i32 " + sensor_type;
        rt.exec(adbPath + add_primary);
        // shell service call qservice 50 i32 2 i32 10 i32 6619296 i32 1
    }

    @BeforeClass
    public void capabilities_setup() throws Exception {
        setup_driver(get_UDID(), "http://127.0.1.1", "4723");
        setup_logger(page_name);
    }

    @BeforeMethod
    public void webDriver() {
        adc.webDriverSetUp();
    }

    @Test
    public void addSensorsGroup13() throws IOException, InterruptedException {
        Thread.sleep(2000);
        add_primary_call(33, 13, 6684828, 107);
        Thread.sleep(1000);
        add_primary_call(34, 13, 6684829, 107);
    }

    @Test
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

    //Test Sensor Group 13

    @Test (dependsOnMethods = {"addSensorsGroup13"})
    public void TestSensor33G13 () throws Exception {
        ArmStay_Tamper_Sensor_during_Exit_Delay_Alarm("66 00 C9","//*[contains(text(), 'Sensor 33 Tamper**')]" );
    }

    @Test (dependsOnMethods = {"addSensorsGroup13"})
    public void TestSensor34G13() throws Exception {
        ArmStay_Tamper_Sensor_during_Exit_Delay_Alarm("66 00 D9","//*[contains(text(), 'Sensor 34 Tamper**')]" );
    }

    //Test Sensor Group 17

    @Test
    public void addSensorsGroup17() throws IOException, InterruptedException {
        Thread.sleep(2000);
        add_primary_call(33, 17, 6684828, 107);
        Thread.sleep(1000);
        add_primary_call(34, 17, 6684829, 107);
    }

    @Test (dependsOnMethods = {"addSensorsGroup17"})
    public void TestSensor33G17() throws Exception {
        ArmStay_Tamper_Sensor_during_Exit_Delay_Alarm("66 00 C9","//*[contains(text(), 'Sensor 33 Tamper**')]" );
    }

    @Test (dependsOnMethods = {"addSensorsGroup17"})
    public void TestSensor34G17 () throws Exception {
        ArmStay_Tamper_Sensor_during_Exit_Delay_Alarm("66 00 D9","//*[contains(text(), 'Sensor 34 Tamper**')]" );
    }

    @AfterClass
        public void tearDown () throws IOException, InterruptedException {
        log.endTestCase(page_name);
        driver.quit();
        }
    }
