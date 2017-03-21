package Settings;

import Panel.*;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.IOException;

public class Secure_Arming_Test extends Setup {

    String page_name = "Secure Arming testing";
    Logger logger = Logger.getLogger(page_name);

    @BeforeMethod
    public void capabilities_setup() throws Exception {
        setup_driver("8ebdbc76", "http://127.0.1.1", "4723");
        setup_logger(page_name);
    }

    @Test
    public void Verify_Secure_Arming_works() throws Exception {
        Settings_Page settings = PageFactory.initElements(driver, Settings_Page.class);
        Security_Arming_Page arming = PageFactory.initElements(driver, Security_Arming_Page.class);
        Advanced_Settings_Page adv = PageFactory.initElements(driver, Advanced_Settings_Page.class);
        Installation_Page inst = PageFactory.initElements(driver, Installation_Page.class);
        Home_Page home = PageFactory.initElements(driver, Home_Page.class);
        Thread.sleep(2000);
        logger.info("Verify no code is required to Arm the system when setting is disabled");
        home.DISARM.click();
        home.ARM_STAY.click();
        Thread.sleep(2000);
        verify_armstay();
        home.DISARM.click();
        enter_default_user_code();
        Thread.sleep(2000);
        navigate_to_Advanced_Settings_page();
        adv.INSTALLATION.click();
        inst.SECURITY_AND_ARMING.click();
        arming.Secure_Arming.click();
        Thread.sleep(2000);
        settings.Home_button.click();
        Thread.sleep(2000);
        logger.info("Verify code is required to Arm the system when setting is enabled");
        home.DISARM.click();
        home.ARM_STAY.click();
        if(home.Enter_Code_to_Access_the_Area.isDisplayed()){
            logger.info("Pass: code is requires to Arm the system");
        }
        enter_default_user_code();
        Thread.sleep(2000);
        home.DISARM.click();
        enter_default_user_code();
        Thread.sleep(2000);
        navigate_to_Advanced_Settings_page();
        adv.INSTALLATION.click();
        inst.SECURITY_AND_ARMING.click();
        arming.Secure_Arming.click();
        Thread.sleep(2000);
    }

    @AfterMethod
    public void tearDown() throws IOException, InterruptedException {
        log.endTestCase(page_name);
        driver.quit();
    }
}
