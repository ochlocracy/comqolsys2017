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

public class Panic_Disable_Test_Grid {

    Setup1 s = new Setup1();
    String page_name = "Panic Disable testing";
    Logger logger = Logger.getLogger(page_name);

    public Panic_Disable_Test_Grid() throws IOException, BiffException {
    }
    @Parameters({"deviceName_", "applicationName_", "UDID_", "platformVersion_", "URL_", "PORT_" })
    @BeforeClass
    public void setUp(String deviceName_, String applicationName_, String UDID_, String platformVersion_, String URL_, String PORT_) throws Exception {
        s.setCapabilities(URL_);
        s.setup_logger(page_name);
    }
    @Parameters ({"UDID_"})
    @Test
    public void Verify_Keyfob_Alarm_Disarm_works() throws Exception {
        Siren_Alarms_Page siren = PageFactory.initElements(s.getDriver(), Siren_Alarms_Page.class);
        Settings_Page settings = PageFactory.initElements(s.getDriver(), Settings_Page.class);
        Advanced_Settings_Page adv = PageFactory.initElements(s.getDriver(), Advanced_Settings_Page.class);
        Installation_Page inst = PageFactory.initElements(s.getDriver(), Installation_Page.class);
        Emergency_Page emergency = PageFactory.initElements(s.getDriver(), Emergency_Page.class);
        Thread.sleep(1000);
        logger.info("Verify panic disappears from the Emergency page when disabled");
        s.navigate_to_Advanced_Settings_page();
        adv.INSTALLATION.click();
        inst.SIREN_AND_ALARMS.click();
        Thread.sleep(1000);
        s.swipe_vertical();
        Thread.sleep(1000);
        s.swipe_vertical();
        Thread.sleep(1000);
        siren.Police_Panic.click();
        Thread.sleep(1000);
        settings.Emergency_button.click();
        try {
            if (emergency.Police_icon.isDisplayed())
                s.take_screenshot();
            logger.info("Failed: Police Emergency is displayed");
        } catch (Exception e) {
            logger.info("Pass: Police Emergency is NOT displayed");
        } finally {
        }
        s.swipeFromLefttoRight();
        Thread.sleep(1000);
        s.navigate_to_Advanced_Settings_page();
        adv.INSTALLATION.click();
        inst.SIREN_AND_ALARMS.click();
        Thread.sleep(1000);
        s.swipe_vertical();
        Thread.sleep(1000);
        s.swipe_vertical();
        Thread.sleep(1000);
        siren.Fire_Panic.click();
        Thread.sleep(1000);
        settings.Emergency_button.click();
        try {
            if (emergency.Fire_icon.isDisplayed())
                s.take_screenshot();
            logger.info("Failed: Fire Emergency is displayed");
        } catch (Exception e) {
            logger.info("Pass: Fire Emergency is NOT displayed");
        } finally {
        }
        s.swipeFromLefttoRight();
        Thread.sleep(1000);
        s.navigate_to_Advanced_Settings_page();
        adv.INSTALLATION.click();
        inst.SIREN_AND_ALARMS.click();
        Thread.sleep(1000);
        s.swipe_vertical();
        Thread.sleep(1000);
        s.swipe_vertical();
        Thread.sleep(1000);
        siren.Auxiliary_Panic.click();
        Thread.sleep(1000);
        settings.Emergency_button.click();
        try {
            if (emergency.Auxiliary_icon.isDisplayed())
                s.take_screenshot();
            logger.info("Failed: Auxiliary Emergency is displayed");
        } catch (Exception e) {
            logger.info("Pass: Auxiliary Emergency is NOT displayed");
        } finally {
        }
        s.swipeFromLefttoRight();
        Thread.sleep(1000);
        s.navigate_to_Advanced_Settings_page();
        adv.INSTALLATION.click();
        inst.SIREN_AND_ALARMS.click();
        Thread.sleep(1000);
        s.swipe_vertical();
        Thread.sleep(1000);
        s.swipe_vertical();
        Thread.sleep(1000);
        siren.Police_Panic.click();
        siren.Fire_Panic.click();
        siren.Auxiliary_Panic.click();
        Thread.sleep(1000);
    }
    @AfterClass
    public void tearDown () throws IOException, InterruptedException {
        s.log.endTestCase(page_name);
        s.getDriver().quit();
    }
}
