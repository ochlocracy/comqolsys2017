package Settings;

import Panel.*;
import jxl.read.biff.BiffException;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;

public class Allow_Master_Code_to_access_Camera_Settings_Page_Test_Grid {

    Setup1 s = new Setup1();
    String page_name = "Allow Master Code to access Camera Settings testing";
    Logger logger = Logger.getLogger(page_name);

    public Allow_Master_Code_to_access_Camera_Settings_Page_Test_Grid() throws IOException, BiffException{}

        @Parameters({"deviceName_", "applicationName_", "UDID_", "platformVersion_", "URL_", "PORT_" })
        @BeforeClass
        public void setUp(String deviceName_, String applicationName_, String UDID_, String platformVersion_, String URL_, String PORT_) throws Exception {
            s.setCapabilities(URL_);
            s.setup_logger(page_name, UDID_);
        }

        @Parameters ({"UDID_"})
        @Test
        public void Verify_Master_Code_gets_access_to_Camera_Settings_page(String UDID_) throws Exception {
            Camera_Settings_Page set_cam =  PageFactory.initElements(s.getDriver(), Camera_Settings_Page.class);
            Settings_Page settings = PageFactory.initElements(s.getDriver(), Settings_Page.class);
            Advanced_Settings_Page adv = PageFactory.initElements(s.getDriver(), Advanced_Settings_Page.class);
            Installation_Page inst = PageFactory.initElements(s.getDriver(), Installation_Page.class);
            Thread.sleep(3000);
            logger.info("Navigate to the setting page to enable the access to the Camera Settings page using Master Code");
            s.navigate_to_Advanced_Settings_page();
            adv.INSTALLATION.click();
            Thread.sleep(2000);
            inst.CAMERA_SETTINGS.click();
            Thread.sleep(2000);
            s.swipe_vertical();
            Thread.sleep(1000);
            set_cam.Allow_Master_Code_to_access_Camera_Settings.click();
            Thread.sleep(1000);
            settings.Home_button.click();
            Thread.sleep(1000);
            logger.info("Navigate to the Advanced setting page to check Camera Settings icon");
            s.navigate_to_Settings_page();
            settings.ADVANCED_SETTINGS.click();
            s.enter_default_user_code();
            Thread.sleep(2000);
            try {
                if (inst.CAMERA_SETTINGS.isDisplayed())
                logger.info(UDID_ + " Pass: Camera Settings icon is present");
            } catch(Exception e){
                s.take_screenshot();
                logger.info(UDID_ + " Failed: Camera Settings icon is NOT present");
            }
            Thread.sleep(2000);
            settings.Home_button.click();
            logger.info("Verify Camera Settings icon disappears after disabling the setting");
            s.navigate_to_Advanced_Settings_page();
            Thread.sleep(2000);
            adv.INSTALLATION.click();
            Thread.sleep(2000);
            inst.CAMERA_SETTINGS.click();
            Thread.sleep(2000);
            s.swipe_vertical();
            Thread.sleep(2000);
            set_cam.Allow_Master_Code_to_access_Camera_Settings.click();
            Thread.sleep(2000);
            settings.Home_button.click();
            logger.info("Navigate to the Advanced setting page to check Camera Settings icon");
            s.navigate_to_Settings_page();
            settings.ADVANCED_SETTINGS.click();
            s.enter_default_user_code();
            Thread.sleep(2000);
            try {
                if (inst.CAMERA_SETTINGS.isDisplayed())
                    s.take_screenshot();
                logger.info(UDID_ + " Failed: Camera Settings icon is present");
            } catch(Exception e){
                logger.info(UDID_ + " Pass: Camera Settings icon is NOT present");
            } finally{}
            Thread.sleep(2000);
        }
    @AfterClass
    public void tearDown () throws IOException, InterruptedException {
        s.log.endTestCase(page_name);
        s.getDriver().quit();
    }
}