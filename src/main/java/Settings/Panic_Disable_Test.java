package Settings;

import Panel.*;
import jxl.read.biff.BiffException;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.IOException;

public class Panic_Disable_Test extends Setup {

    String page_name = "Panic Disable testing";
    Logger logger = Logger.getLogger(page_name);

    public Panic_Disable_Test() throws IOException, BiffException {
    }

    @BeforeMethod
    public void capabilities_setup() throws Exception {
        setup_driver(udid_, "http://127.0.1.1", "4723");
        setup_logger(page_name);
    }
    @Test
    public void Verify_Keyfob_Alarm_Disarm_works() throws Exception {
        Siren_Alarms_Page siren = PageFactory.initElements(driver, Siren_Alarms_Page.class);
        Settings_Page settings = PageFactory.initElements(driver, Settings_Page.class);
        Advanced_Settings_Page adv = PageFactory.initElements(driver, Advanced_Settings_Page.class);
        Installation_Page inst = PageFactory.initElements(driver, Installation_Page.class);
        Emergency_Page emergency = PageFactory.initElements(driver, Emergency_Page.class);
        Thread.sleep(1000);
        logger.info("Verify panic disappears from the Emergency page when disabled");
        navigate_to_Advanced_Settings_page();
        adv.INSTALLATION.click();
        inst.SIREN_AND_ALARMS.click();
        Thread.sleep(1000);
        swipe_vertical();
        Thread.sleep(1000);
        swipe_vertical();
        Thread.sleep(1000);
        siren.Police_Panic.click();
        Thread.sleep(1000);
        settings.Emergency_button.click();
        try {
            if (emergency.Police_icon.isDisplayed())
                take_screenshot();
                logger.info("Failed: Police Emergency is displayed");
        } catch (Exception e) {
            logger.info("Pass: Police Emergency is NOT displayed");
        } finally {
        }
        swipeFromLefttoRight();
        Thread.sleep(1000);
        navigate_to_Advanced_Settings_page();
        adv.INSTALLATION.click();
        inst.SIREN_AND_ALARMS.click();
        Thread.sleep(1000);
        swipe_vertical();
        Thread.sleep(1000);
        swipe_vertical();
        Thread.sleep(1000);
        siren.Fire_Panic.click();
        Thread.sleep(1000);
        settings.Emergency_button.click();
        try {
            if (emergency.Fire_icon.isDisplayed())
                take_screenshot();
                logger.info("Failed: Fire Emergency is displayed");
        } catch (Exception e) {
            logger.info("Pass: Fire Emergency is NOT displayed");
        } finally {
        }
        swipeFromLefttoRight();
        Thread.sleep(1000);
        navigate_to_Advanced_Settings_page();
        adv.INSTALLATION.click();
        inst.SIREN_AND_ALARMS.click();
        Thread.sleep(1000);
        swipe_vertical();
        Thread.sleep(1000);
        swipe_vertical();
        Thread.sleep(1000);
        siren.Auxiliary_Panic.click();
        Thread.sleep(1000);
        settings.Emergency_button.click();
        try {
            if (emergency.Auxiliary_icon.isDisplayed())
                take_screenshot();
                logger.info("Failed: Auxiliary Emergency is displayed");
        } catch (Exception e) {
            logger.info("Pass: Auxiliary Emergency is NOT displayed");
        } finally {
        }
        swipeFromLefttoRight();
        Thread.sleep(1000);
        navigate_to_Advanced_Settings_page();
        adv.INSTALLATION.click();
        inst.SIREN_AND_ALARMS.click();
        Thread.sleep(1000);
        swipe_vertical();
        Thread.sleep(1000);
        swipe_vertical();
        Thread.sleep(1000);
        siren.Police_Panic.click();
        siren.Fire_Panic.click();
        siren.Auxiliary_Panic.click();
        Thread.sleep(1000);
    }
    @AfterMethod
    public void tearDown () throws IOException, InterruptedException {
        log.endTestCase(page_name);
        driver.quit();
    }
}
