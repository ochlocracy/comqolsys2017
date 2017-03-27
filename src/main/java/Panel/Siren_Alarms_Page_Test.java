package Panel;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.IOException;

public class Siren_Alarms_Page_Test extends Setup{

    String page_name = "Siren and Alarms page testing";
    Logger logger = Logger.getLogger(page_name);

    @BeforeMethod
    public void capabilities_setup() throws Exception {
        setup_driver(udid_, "http://127.0.1.1", "4723");
        setup_logger(page_name);}

    @Test
    public void Check_all_elements_on_Siren_Alarms_page() throws Exception {
        Siren_Alarms_Page siren = PageFactory.initElements(driver, Siren_Alarms_Page.class);
        Advanced_Settings_Page adv = PageFactory.initElements(driver, Advanced_Settings_Page.class);
        Installation_Page inst = PageFactory.initElements(driver, Installation_Page.class);
        navigate_to_Advanced_Settings_page();
        adv.INSTALLATION.click();
        inst.SIREN_AND_ALARMS.click();
        logger.info("Verifying elements on the page...");
        Thread.sleep(1000);
        if (siren.Disable_Siren_summery.isDisplayed()) {
            logger.info("Pass: Correct Disable Siren summery");
        }
        siren.Disable_Siren.click();
        Thread.sleep(1000);
        if (siren.Disable_Siren_summery_enabled.isDisplayed()) {
            logger.info("Pass: Correct Disable Siren summery when enabled");
        }
        siren.Disable_Siren.click();
        Thread.sleep(1000);
        if (siren.Siren_Annunciation_summery.isDisplayed()) {
            logger.info("Pass: Correct Siren Annunciation summery");
        }
        siren.Siren_Annunciation.click();
        Thread.sleep(1000);
        if (siren.Siren_Annunciation_summery_enabled.isDisplayed()) {
            logger.info("Pass: Correct Siren Annunciation summery when enabled");
        }
        siren.Siren_Annunciation.click();
        Thread.sleep(2000);

        if (siren.Fire_Verification_summery.isDisplayed()) {
            logger.info("Pass: Correct Fire Verification summery");
        }
        siren.Fire_Verification.click();
        Thread.sleep(1000);
        if (siren.Fire_Verification_summery_enabled.isDisplayed()) {
            logger.info("Pass: Correct Fire Verification summery when enabled");
        }
        siren.Fire_Verification.click();
        Thread.sleep(2000);
        if (siren.Severe_Weather_Siren_Warning_summery.isDisplayed()) {
            logger.info("Pass: Correct Severe Weather Siren Warning summery");
        }
        siren.Severe_Weather_Siren_Warning.click();
        Thread.sleep(1000);
        if (siren.Severe_Weather_Siren_Warning_summery_disabled.isDisplayed()) {
            logger.info("Pass: Correct Severe Weather Siren Warning summery when disabled");
        }
        siren.Severe_Weather_Siren_Warning.click();
        Thread.sleep(1000);
        if (siren.Dialer_Delay_summery.isDisplayed()) {
            logger.info("Pass: Correct Dialer Delay summery");
        }
        Thread.sleep(2000);
        siren.Dialer_Delay.click();
        Thread.sleep(2000);
        if (siren.Set_value_title.isDisplayed()) {
            logger.info("Pass: Set Value in sec window is present ");
        }
        siren.Cancel.click();
        Thread.sleep(1000);
        swipe_vertical();
        Thread.sleep(2000);
        if (siren.Siren_Timeout_summery.isDisplayed()) {
            logger.info("Pass: Correct Siren Timeout summery");
        }
        siren.Siren_Timeout.click();
        Thread.sleep(1000);
        if (siren.Siren_Timeout_title.isDisplayed()) {
            logger.info("Pass: Siren Timeout options are displayed");
        }
        siren.Siren_Timeout_Cancel.click();
        Thread.sleep(1000);
        if (siren.Water_Freeze_Siren_summery.isDisplayed()) {
            logger.info("Pass: Correct Water/Freeze Siren summery");
        }
        siren.Water_Freeze_Siren.click();
        Thread.sleep(1000);
        if (siren.Water_Freeze_Siren_summery_enabled.isDisplayed()) {
            logger.info("Pass: Correct Water/Freeze Siren summery when enabled");
        }
        siren.Water_Freeze_Siren.click();
        Thread.sleep(1000);
        if (siren.Police_Panic_summery.isDisplayed()) {
            logger.info("Pass: Correct Police Panic summery");
        }
        siren.Police_Panic.click();
        Thread.sleep(1000);
        if (siren.Police_Panic_summery_disabled.isDisplayed()) {
            logger.info("Pass: Correct Police Panic summery when disabled");
        }
        siren.Police_Panic.click();
        Thread.sleep(1000);
        swipe_vertical();
        Thread.sleep(2000);
        if (siren.Fire_Panic_summery.isDisplayed()) {
            logger.info("Pass: Correct Fire Panic summery");
        }
        siren.Fire_Panic.click();
        Thread.sleep(1000);
        if (siren.Fire_Panic_summery_disabled.isDisplayed()) {
            logger.info("Pass: Correct Fire Panic summery when disabled");
        }
        siren.Fire_Panic.click();
        Thread.sleep(1000);
        if (siren.Auxiliary_Panic_summery.isDisplayed()) {
            logger.info("Pass: Correct Auxiliary Panic summery");
        }
        siren.Auxiliary_Panic.click();
        Thread.sleep(1000);
        if (siren.Auxiliary_Panic_summery_disabled.isDisplayed()) {
            logger.info("Pass: Correct Auxiliary Panic summery when disabled");
        }
        siren.Auxiliary_Panic.click();
        Thread.sleep(1000);
        if (siren.Allow_Master_Code_To_Access_Siren_and_Alarms_summery.isDisplayed()) {
            logger.info("Pass: Correct Allow Master Code To Access Siren and Alarms summery");
        }
        siren.Allow_Master_Code_To_Access_Siren_and_Alarms.click();
        Thread.sleep(1000);
        if (siren.Allow_Master_Code_To_Access_Siren_and_Alarms_summery_enabled.isDisplayed()) {
            logger.info("Pass: Correct Allow Master Code To Access Siren and Alarms summery when enabled");
        }
        siren.Allow_Master_Code_To_Access_Siren_and_Alarms.click();
        Thread.sleep(2000);
    }

    @AfterMethod
    public void tearDown () throws IOException, InterruptedException {
        log.endTestCase(page_name);
        driver.quit();
    }
}
