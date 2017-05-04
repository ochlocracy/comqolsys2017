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

public class Keyfob_Disarming_Test_Grid {
    Setup1 s = new Setup1();
    String page_name = "Keyfob Disarming testing";
    Logger logger = Logger.getLogger(page_name);
    Sensors sensors = new Sensors();
    private int delay = 15;
    private String disarm = "08 01";

    public Keyfob_Disarming_Test_Grid() throws IOException, BiffException {}
        @Parameters({"deviceName_", "applicationName_", "UDID_", "platformVersion_", "URL_", "PORT_" })
        @BeforeClass
        public void setUp(String deviceName_, String applicationName_, String UDID_, String platformVersion_, String URL_, String PORT_) throws Exception {
            s.setCapabilities(URL_);
            s.setup_logger(page_name, UDID_);
        }
        @Parameters ({"UDID_"})
        @Test
        public void Verify_Keyfob_Disarming_works(String UDID_) throws Exception {
            Security_Arming_Page arming = PageFactory.initElements(s.getDriver(), Security_Arming_Page.class);
            Settings_Page settings = PageFactory.initElements(s.getDriver(), Settings_Page.class);
            Advanced_Settings_Page adv = PageFactory.initElements(s.getDriver(), Advanced_Settings_Page.class);
            Installation_Page inst = PageFactory.initElements(s.getDriver(), Installation_Page.class);
            Home_Page home = PageFactory.initElements(s.getDriver(), Home_Page.class);
            logger.info("Adding sensors...");
            s.add_primary_call(1, 4, 6619386, 102,UDID_);
            Thread.sleep(2000);
            logger.info("Verify that Keyfob Disarming works when enabled");
            s.ARM_STAY();
            Thread.sleep(2000);
            s.primary_call(UDID_,"65 00 AF", disarm);
            Thread.sleep(2000);
            s.verify_disarm(UDID_);
            Thread.sleep(2000);
            s.ARM_AWAY(delay);
            Thread.sleep(2000);
            s.primary_call(UDID_,"65 00 AF", disarm);
            Thread.sleep(2000);
            s.verify_disarm(UDID_);
            Thread.sleep(2000);
            logger.info("Verify that Keyfob Disarming does not work when disabled");
            s.navigate_to_Advanced_Settings_page();
            adv.INSTALLATION.click();
            inst.SECURITY_AND_ARMING.click();
            Thread.sleep(2000);
            s.swipe_vertical();
            Thread.sleep(2000);
            s.swipe_vertical();
            arming.Keyfob_Disarming.click();
            Thread.sleep(2000);
            settings.Home_button.click();
            Thread.sleep(2000);
            s.ARM_STAY();
            Thread.sleep(2000);
            s.primary_call(UDID_,"65 00 AF", disarm);
            Thread.sleep(2000);
            s.verify_armstay(UDID_);
            home.DISARM.click();
            s.enter_default_user_code();
            Thread.sleep(2000);
            s.ARM_AWAY(delay);
            Thread.sleep(2000);
            s.primary_call(UDID_,"65 00 AF", disarm);
            Thread.sleep(2000);
            s.verify_armaway(UDID_);
            home.ArwAway_State.click();
            s.enter_default_user_code();
            Thread.sleep(2000);
            s.navigate_to_Advanced_Settings_page();
            adv.INSTALLATION.click();
            inst.SECURITY_AND_ARMING.click();
            Thread.sleep(2000);
            s.swipe_vertical();
            Thread.sleep(2000);
            s.swipe_vertical();
            arming.Keyfob_Disarming.click();
            Thread.sleep(2000);
            s.delete_from_primary(UDID_,1);
            settings.Home_button.click();
            Thread.sleep(2000);
        }
    @AfterClass
    public void tearDown () throws IOException, InterruptedException {
        s.log.endTestCase(page_name);
        s.getDriver().quit();
    }
    }