package QTMS_SRF;

import ADC.ADC;
import Panel.PanelInfo_ServiceCalls;
import Panel.Setup;
import Sensors.Sensors;
import jxl.read.biff.BiffException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.*;

import java.io.IOException;

public class ARM_AWAY extends Setup {

    String page_name = "QTMS: ARM AWAY";
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
    ADC adc = new ADC();
    String AccountID = adc.getAccountId();

    public ARM_AWAY() throws IOException, BiffException {}

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
    public void AW_02b() throws Exception {
        logger.info("Verify the panel can be disarmed from ADC");
       /* servcall.set_SIA_LIMITS_disable();
        Thread.sleep(1000);
        servcall.set_NORMAL_ENTRY_DELAY(Normal_Entry_Delay);
        Thread.sleep(1000);
        servcall.set_NORMAL_EXIT_DELAY(Normal_Exit_Delay);
        Thread.sleep(1000);
        servcall.set_LONG_ENTRY_DELAY(Long_Entry_Delay);
        Thread.sleep(1000);
        servcall.set_LONG_EXIT_DELAY(Long_Exit_Delay);
        Thread.sleep(1000);*/
        servcall.set_AUTO_STAY(0);
        Thread.sleep(1000);
        servcall.EVENT_ARM_AWAY();
        Thread.sleep(3000);
        verify_armaway();
        adc.New_ADC_session_User(login,password);
        Thread.sleep(3000);
        if (adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_phBody_ArmingStateWidget_imgState"))).isDisplayed()) {
            adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_phBody_ArmingStateWidget_btnDisarm"))).click();}
        Thread.sleep(4000);
        verify_disarm();
        System.out.println("Pass");
    }

    @AfterTest
    public void tearDown () throws IOException, InterruptedException {
        log.endTestCase(page_name);
            }

    @AfterMethod
    public void webDriverQuit() {
        adc.driver1.quit();
    }




}
