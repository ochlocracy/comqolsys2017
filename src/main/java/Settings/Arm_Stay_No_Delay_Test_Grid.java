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

public class Arm_Stay_No_Delay_Test_Grid {

    Setup1 s = new Setup1();
    String page_name = "Arm Stay No Delay testing";
    Logger logger = Logger.getLogger(page_name);

    public Arm_Stay_No_Delay_Test_Grid() throws IOException, BiffException {
    }

    @Parameters({"deviceName_", "applicationName_", "UDID_", "platformVersion_", "URL_", "PORT_" })
    @BeforeClass
    public void setUp(String deviceName_, String applicationName_, String UDID_, String platformVersion_, String URL_, String PORT_) throws Exception {
        s.setCapabilities(URL_);
        s.setup_logger(page_name);
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
        logger.info("Verify that Arm Stay - No Delay works when enabled");
        s.ARM_STAY();
        s.verify_armstay();
        home.DISARM.click();
        s.enter_default_user_code();
        Thread.sleep(2000);
        logger.info("Verify that Arm Stay - No Delay does not work when disabled");
        s.navigate_to_Advanced_Settings_page();
        adv.INSTALLATION.click();
        inst.SECURITY_AND_ARMING.click();
        Thread.sleep(3000);
        s.swipe_vertical();
        arming.Arm_Stay_No_Delay.click();
        Thread.sleep(2000);
        settings.Home_button.click();
        s.ARM_STAY();
        try {
            if (home.Disarmed_text.getText().equals("ARMED STAY"))
                s.take_screenshot();
            logger.info("Failed: System is ARMED STAY");
        } catch (Exception e) {
            logger.info("Pass: System is NOT ARMED STAY");
        } finally {
        }
        Thread.sleep(15000);
        s.verify_armstay();
        home.DISARM.click();
        s.enter_default_user_code();
        Thread.sleep(2000);
        s.navigate_to_Advanced_Settings_page();
        adv.INSTALLATION.click();
        inst.SECURITY_AND_ARMING.click();
        Thread.sleep(3000);
        s.swipe_vertical();
        arming.Arm_Stay_No_Delay.click();
        Thread.sleep(2000);
        settings.Home_button.click();
        Thread.sleep(2000);
    }
    @AfterClass
    public void tearDown () throws IOException, InterruptedException {
        s.log.endTestCase(page_name);
        s.getDriver().quit();
    }
}
