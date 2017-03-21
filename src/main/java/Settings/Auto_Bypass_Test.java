package Settings;

import Panel.*;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.IOException;
import Sensors.Sensors;

public class Auto_Bypass_Test extends Setup {

    String page_name = "Auto Bypass testing";
    Logger logger = Logger.getLogger(page_name);
    Sensors sensors = new Sensors();
    private String open = "06 00";
    private String close = "04 00";

    @BeforeMethod
    public void capabilities_setup() throws Exception {
        setup_driver("8ebdbc76", "http://127.0.1.1", "4723");
        setup_logger(page_name);
    }
    @Test
    public void Verify_Auto_Bypass_works() throws Exception {
        Security_Arming_Page arming = PageFactory.initElements(driver, Security_Arming_Page.class);
        Settings_Page settings = PageFactory.initElements(driver, Settings_Page.class);
        Advanced_Settings_Page adv = PageFactory.initElements(driver, Advanced_Settings_Page.class);
        Installation_Page inst = PageFactory.initElements(driver, Installation_Page.class);
        Home_Page home = PageFactory.initElements(driver, Home_Page.class);
        logger.info("Adding sensors...");
        sensors.add_primary_call(1,10,6619296,1);
        Thread.sleep(2000);
        logger.info("Verify that Auto Bypass works when enabled");
        sensors.primary_call("65 00 0A",open);
        Thread.sleep(3000);
        home.DISARM.click();
        home.ARM_STAY.click();
        Thread.sleep(3000);
        sensors.primary_call("65 00 0A",close);
        Thread.sleep(1000);
        sensors.primary_call("65 00 0A",open);
        Thread.sleep(1000);
        sensors.primary_call("65 00 0A",close);
        Thread.sleep(1000);
        sensors.primary_call("65 00 0A",open);
        Thread.sleep(1000);
        sensors.primary_call("65 00 0A",close);
        Thread.sleep(1000);
        verify_armstay();
        home.DISARM.click();
        enter_default_user_code();
        Thread.sleep(3000);
        navigate_to_Advanced_Settings_page();
        adv.INSTALLATION.click();
        inst.SECURITY_AND_ARMING.click();
        swipe_vertical();
        arming.Auto_Bypass.click();
        Thread.sleep(3000);
        settings.Home_button.click();
        Thread.sleep(3000);
        logger.info("Verify that Auto Bypass does not work when disabled");
        sensors.primary_call("65 00 0A",open);
        home.DISARM.click();
        home.ARM_STAY.click();
        Thread.sleep(2000);
        //Poo-up message suppose to be here

        sensors.delete_from_primary(1);

    }

    @AfterMethod
    public void tearDown() throws IOException, InterruptedException {
        log.endTestCase(page_name);
        driver.quit();
    }
}
