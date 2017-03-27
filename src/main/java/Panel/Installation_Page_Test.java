package Panel;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.IOException;

public class Installation_Page_Test extends Setup {

    String page_name = "Installation page";
    Logger logger = Logger.getLogger(page_name);

    @BeforeMethod
    public void capabilities_setup() throws Exception {
        setup_driver(udid_, "http://127.0.1.1", "4723");
        setup_logger(page_name);
    }

    @Test
    public void Check_all_elements_on_Installation_page() throws Exception {
        Advanced_Settings_Page adv = PageFactory.initElements(driver, Advanced_Settings_Page.class);
        Installation_Page instal = PageFactory.initElements(driver, Installation_Page.class);
        navigate_to_Advanced_Settings_page();
        adv.INSTALLATION.click();
        logger.info("Verifying elements on the page...");
        Thread.sleep(1000);

        element_verification(instal.DEVICES, "Devices");
        element_verification(instal.DEALER_SETTINGS, "Dealer Settings");
        element_verification(instal.SYSTEM_LOGS, "System Logs");
        element_verification(instal.SIREN_AND_ALARMS, "Siren and Alarms");
        element_verification(instal.SECURITY_AND_ARMING, "Security and Arming");
        element_verification(instal.CAMERA_SETTINGS, "Camera Settings");
        element_verification(instal.LOAD_HELP_VIDEOS, "Load Help Videos");
    }

    @AfterMethod
    public void tearDown () throws IOException, InterruptedException {
        log.endTestCase(page_name);
        driver.quit();}
}
