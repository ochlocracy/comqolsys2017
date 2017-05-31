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

public class Keyfob_Alarm_Disarm_Test_Grid {
    Setup1 s = new Setup1();
    String page_name = "Auto Stay testing";
    Logger logger = Logger.getLogger(page_name);
    Sensors sensors = new Sensors();
    private String disarm = "08 01";

    public Keyfob_Alarm_Disarm_Test_Grid() throws IOException, BiffException {}
    @Parameters({"deviceName_", "applicationName_", "UDID_", "platformVersion_", "URL_", "PORT_" })
    @BeforeClass
    public void setUp(String deviceName_, String applicationName_, String UDID_, String platformVersion_, String URL_, String PORT_) throws Exception {
        s.setCapabilities(URL_);
        s.setup_logger(page_name, UDID_);
    }
    @Parameters ({"UDID_"})
    @Test
    public void Verify_Keyfob_Alarm_Disarm_works(String UDID_) throws Exception {
            Security_Arming_Page arming = PageFactory.initElements(s.getDriver(), Security_Arming_Page.class);
            Contact_Us contact = PageFactory.initElements(s.getDriver(), Contact_Us.class);
            Settings_Page settings = PageFactory.initElements(s.getDriver(), Settings_Page.class);
            Advanced_Settings_Page adv = PageFactory.initElements(s.getDriver(), Advanced_Settings_Page.class);
            Installation_Page inst = PageFactory.initElements(s.getDriver(), Installation_Page.class);
            Home_Page home = PageFactory.initElements(s.getDriver(), Home_Page.class);
            Emergency_Page emergency = PageFactory.initElements(s.getDriver(), Emergency_Page.class);
            logger.info("Adding sensors...");
            s.add_primary_call(1, 4, 6619386, 102, UDID_);
            Thread.sleep(2000);
            logger.info("Verify that Keyfod Alarm Disarm does not work when disabled");
            home.Emergency_Button.click();
            Thread.sleep(2000);
            emergency.Police_icon.click();
            Thread.sleep(4000);
            s.primary_call(UDID_,"65 00 AF", disarm);
            Thread.sleep(4000);
            if (emergency.Emergency_sent_text.isDisplayed()){
                logger.info(UDID_ +" Pass: Police Emergency is displayed");
            } else { s.take_screenshot();
                logger.info(UDID_ +" Failed: Police Emergency is NOT displayed");}
            emergency.Cancel_Emergency.click();
            s.enter_default_user_code();
            Thread.sleep(2000);
            logger.info("Verify that Keyfod Alarm Disarm  works when enabled");
            s.navigate_to_Advanced_Settings_page();
            adv.INSTALLATION.click();
            Thread.sleep(1000);
            inst.SECURITY_AND_ARMING.click();
            Thread.sleep(2000);
            s.swipe_vertical();
            Thread.sleep(2000);
            s.swipe_vertical();
            arming.Keyfob_Alarm_Disarm.click();
            Thread.sleep(2000);
            settings.Home_button.click();
            Thread.sleep(2000);
            home.Emergency_Button.click();
            emergency.Police_icon.click();
            Thread.sleep(2000);
            s.primary_call(UDID_,"65 00 AF", disarm);
            Thread.sleep(2000);
            s.verify_disarm(UDID_);
            Thread.sleep(2000);
            s.navigate_to_Advanced_Settings_page();
            adv.INSTALLATION.click();
            Thread.sleep(1000);
            inst.SECURITY_AND_ARMING.click();
            Thread.sleep(2000);
            s.swipe_vertical();
            Thread.sleep(2000);
            s.swipe_vertical();
            arming.Keyfob_Alarm_Disarm.click();
            Thread.sleep(2000);
            settings.Home_button.click();
            s.delete_from_primary(UDID_,1);
            Thread.sleep(2000);
            contact.acknowledge_all_alerts();
        }
    @AfterClass
    public void tearDown () throws IOException, InterruptedException {
        s.log.endTestCase(page_name);
        s.getDriver().quit();
    }
    }