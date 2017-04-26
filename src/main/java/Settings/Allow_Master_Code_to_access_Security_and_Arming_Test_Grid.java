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

public class Allow_Master_Code_to_access_Security_and_Arming_Test_Grid {

    Setup1 s = new Setup1();
    String page_name = "Allow Master Code to access Security and Arming testing";
    Logger logger = Logger.getLogger(page_name);

    public Allow_Master_Code_to_access_Security_and_Arming_Test_Grid() throws IOException, BiffException {
    }

    @Parameters({"deviceName_", "applicationName_", "UDID_", "platformVersion_", "URL_", "PORT_" })
    @BeforeClass
    public void setUp(String deviceName_, String applicationName_, String UDID_, String platformVersion_, String URL_, String PORT_) throws Exception {
        s.setCapabilities(URL_);
        s.setup_logger(page_name);
    }

    @Test
    public void Verify_Master_Code_gets_access_to_Security_and_Arming_page() throws Exception {
        Security_Arming_Page arming = PageFactory.initElements(s.getDriver(), Security_Arming_Page.class);
        Settings_Page settings = PageFactory.initElements(s.getDriver(), Settings_Page.class);
        Advanced_Settings_Page adv = PageFactory.initElements(s.getDriver(), Advanced_Settings_Page.class);
        Installation_Page inst = PageFactory.initElements(s.getDriver(), Installation_Page.class);
        Thread.sleep(3000);
        logger.info("Navigate to the setting page to enable the access to the Security and Arming page using Master Code");
        s.navigate_to_Advanced_Settings_page();
        adv.INSTALLATION.click();
        inst.SECURITY_AND_ARMING.click();
        Thread.sleep(2000);
        s.swipe_vertical();
        Thread.sleep(1000);
        s.swipe_vertical();
        Thread.sleep(1000);
        s.swipe_vertical();
        arming.Allow_Master_Code_To_Access_Security_and_Arming.click();
        Thread.sleep(2000);
        settings.Home_button.click();
        Thread.sleep(2000);
        logger.info("Navigate to the Advanced setting page to check Security and Arming icon");
        s.navigate_to_Settings_page();
        settings.ADVANCED_SETTINGS.click();
        s.enter_default_user_code();
        Thread.sleep(2000);
        if (inst.SECURITY_AND_ARMING.isDisplayed()){
            logger.info("Pass: Security and Arming icon is present");
        }else { s.take_screenshot();
            logger.info("Failed: Security and Arming icon is NOT present");}
        Thread.sleep(2000);
        settings.Home_button.click();
        logger.info("Verify Security and Arming icon disappears after disabling the setting");
        s.navigate_to_Advanced_Settings_page();
        adv.INSTALLATION.click();
        inst.SECURITY_AND_ARMING.click();
        Thread.sleep(2000);
        s.swipe_vertical();
        Thread.sleep(2000);
        s.swipe_vertical();
        Thread.sleep(2000);
        s.swipe_vertical();
        arming.Allow_Master_Code_To_Access_Security_and_Arming.click();
        Thread.sleep(2000);
        settings.Home_button.click();
        logger.info("Navigate to the Advanced setting page to check Security and Arming icon");
        s.navigate_to_Settings_page();
        settings.ADVANCED_SETTINGS.click();
        s.enter_default_user_code();
        Thread.sleep(2000);
        try {
            if (inst.SECURITY_AND_ARMING.isDisplayed())
                s.take_screenshot();
            logger.info("Failed: Security and Arming icon is present");
        } catch(Exception e){
            logger.info("Pass: Security and Arming icon is NOT present");
        } finally{
        }
    }
    @AfterClass
    public void tearDown () throws IOException, InterruptedException {
        s.log.endTestCase(page_name);
        s.getDriver().quit();
    }
}
