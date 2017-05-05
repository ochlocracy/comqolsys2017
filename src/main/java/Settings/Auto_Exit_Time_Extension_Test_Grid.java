package Settings;

import Panel.*;
import Sensors.Sensors;
import jxl.read.biff.BiffException;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;

public class Auto_Exit_Time_Extension_Test_Grid {
    Setup1 s = new Setup1();
    String page_name = "Auto Exit Time Extension testing";
    Logger logger = Logger.getLogger(page_name);
    Sensors sensors = new Sensors();
    private String open = "06 00";
    private String close = "04 00";

    public Auto_Exit_Time_Extension_Test_Grid() throws IOException, BiffException {}
    @Parameters({"deviceName_", "applicationName_", "UDID_", "platformVersion_", "URL_", "PORT_" })
    @BeforeClass
    public void setUp(String deviceName_, String applicationName_, String UDID_, String platformVersion_, String URL_, String PORT_) throws Exception {
        s.setCapabilities(URL_);
        s.setup_logger(page_name, UDID_);
    }

    @Parameters ({"UDID_"})
    @Test
    public void Verify_Arm_Stay_No_Delay_works(String UDID_) throws Exception {
        Security_Arming_Page arming = PageFactory.initElements(s.getDriver(), Security_Arming_Page.class);
        Settings_Page settings = PageFactory.initElements(s.getDriver(), Settings_Page.class);
        Advanced_Settings_Page adv = PageFactory.initElements(s.getDriver(), Advanced_Settings_Page.class);
        Installation_Page inst = PageFactory.initElements(s.getDriver(), Installation_Page.class);
        Home_Page home = PageFactory.initElements(s.getDriver(), Home_Page.class);
        Thread.sleep(2000);
        logger.info("Verify that Auto Exit Time Extension works when enabled");
        logger.info("Adding sensors...");
        s.add_primary_call(1, 10, 6619296, 1, UDID_);
        Thread.sleep(2000);
        s.ARM_AWAY(3);
        s.primary_call(UDID_,"65 00 0A",open);
        Thread.sleep(2000);
        s.primary_call(UDID_,"65 00 0A",close);
        Thread.sleep(2000);
        s.primary_call(UDID_,"65 00 0A",open);
        Thread.sleep(2000);
        s.primary_call(UDID_,"65 00 0A",close);
        Thread.sleep(15000);
        try {
            if (home.ArwAway_State.isDisplayed())
                s.take_screenshot();
            logger.info(UDID_ + " Failed: System is ARMED AWAY");
        } catch (Exception e) {
            logger.info(UDID_ + " Pass: System is NOT ARMED AWAY");
        } finally {
        }
        Thread.sleep(60000);
        s.verify_armaway(UDID_);
        Thread.sleep(2000);
        home.ArwAway_State.click();
        s.enter_default_user_code();
        Thread.sleep(2000);
        logger.info("Verify that Auto Exit Time Extension does not works when disabled");
        s.navigate_to_Advanced_Settings_page();
        adv.INSTALLATION.click();
        inst.SECURITY_AND_ARMING.click();
        Thread.sleep(3000);
        s.swipe_vertical();
        Thread.sleep(3000);
        s.swipe_vertical();
        arming.Auto_Exit_Time_Extension.click();
        Thread.sleep(2000);
        settings.Home_button.click();
        Thread.sleep(2000);
        s.ARM_AWAY(3);
        s.primary_call(UDID_,"65 00 0A",open);
        Thread.sleep(2000);
        s.primary_call(UDID_,"65 00 0A",close);
        Thread.sleep(2000);
        s.primary_call(UDID_,"65 00 0A",open);
        Thread.sleep(2000);
        s.primary_call(UDID_,"65 00 0A",close);
        Thread.sleep(10000);
        s.verify_armaway(UDID_);
        Thread.sleep(2000);
        home.ArwAway_State.click();
        s.enter_default_user_code();
        Thread.sleep(2000);
        s.navigate_to_Advanced_Settings_page();
        adv.INSTALLATION.click();
        inst.SECURITY_AND_ARMING.click();
        Thread.sleep(3000);
        s.swipe_vertical();
        Thread.sleep(3000);
        s.swipe_vertical();
        arming.Auto_Exit_Time_Extension.click();
        Thread.sleep(2000);
        settings.Home_button.click();
        s.delete_from_primary(UDID_,1);
        Thread.sleep(2000);
    }
    @AfterClass
    public void tearDown () throws IOException, InterruptedException {
        s.log.endTestCase(page_name);
        s.getDriver().quit();
    }
}