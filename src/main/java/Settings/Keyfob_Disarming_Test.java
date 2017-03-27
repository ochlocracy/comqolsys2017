package Settings;

import Panel.*;
import Sensors.Sensors;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.IOException;

public class Keyfob_Disarming_Test extends Setup{

    String page_name = "Keyfob Disarming testing";
    Logger logger = Logger.getLogger(page_name);
    Sensors sensors = new Sensors();
    private int delay = 15;
    private String disarm = "08 01";

    @BeforeMethod
    public void capabilities_setup() throws Exception {
        setup_driver(udid_, "http://127.0.1.1", "4723");
        setup_logger(page_name);
    }

    @Test
    public void Verify_Keyfob_Disarming_works() throws Exception {
        Security_Arming_Page arming = PageFactory.initElements(driver, Security_Arming_Page.class);
        Settings_Page settings = PageFactory.initElements(driver, Settings_Page.class);
        Advanced_Settings_Page adv = PageFactory.initElements(driver, Advanced_Settings_Page.class);
        Installation_Page inst = PageFactory.initElements(driver, Installation_Page.class);
        Home_Page home = PageFactory.initElements(driver, Home_Page.class);
        logger.info("Adding sensors...");
        sensors.add_primary_call(1, 4, 6619386, 102);
        Thread.sleep(2000);
        logger.info("Verify that Keyfob Disarming works when enabled");
        ARM_STAY();
        Thread.sleep(2000);
        sensors.primary_call("65 00 AF", disarm);
        Thread.sleep(2000);
        verify_disarm();
        Thread.sleep(2000);
        ARM_AWAY(delay);
        Thread.sleep(2000);
        sensors.primary_call("65 00 AF", disarm);
        Thread.sleep(2000);
        verify_disarm();
        Thread.sleep(2000);
        logger.info("Verify that Keyfob Disarming does not work when disabled");
        navigate_to_Advanced_Settings_page();
        adv.INSTALLATION.click();
        inst.SECURITY_AND_ARMING.click();
        Thread.sleep(2000);
        swipe_vertical();
        Thread.sleep(2000);
        swipe_vertical();
        arming.Keyfob_Disarming.click();
        Thread.sleep(2000);
        settings.Home_button.click();
        Thread.sleep(2000);
        ARM_STAY();
        Thread.sleep(2000);
        sensors.primary_call("65 00 AF", disarm);
        Thread.sleep(2000);
        verify_armstay();
        home.DISARM.click();
        enter_default_user_code();
        Thread.sleep(2000);
        ARM_AWAY(delay);
        Thread.sleep(2000);
        sensors.primary_call("65 00 AF", disarm);
        Thread.sleep(2000);
        verify_armaway();
        home.ArwAway_State.click();
        enter_default_user_code();
        Thread.sleep(2000);
        navigate_to_Advanced_Settings_page();
        adv.INSTALLATION.click();
        inst.SECURITY_AND_ARMING.click();
        Thread.sleep(2000);
        swipe_vertical();
        Thread.sleep(2000);
        swipe_vertical();
        arming.Keyfob_Disarming.click();
        Thread.sleep(2000);
        sensors.delete_from_primary(1);
        settings.Home_button.click();
        Thread.sleep(2000);
    }
    @AfterMethod
    public void tearDown() throws IOException, InterruptedException {
        log.endTestCase(page_name);
        driver.quit();
    }
}
