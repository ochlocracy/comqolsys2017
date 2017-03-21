package Settings;

import Panel.*;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.IOException;

public class Disarm_Photos_Test extends Setup {

    String page_name = "Secure Delete Images testing";
    Logger logger = Logger.getLogger(page_name);

    @BeforeMethod
    public void capabilities_setup() throws Exception {
        setup_driver("8ebdbc76", "http://127.0.1.1", "4723");
        setup_logger(page_name);
    }

    @Test
    public void Verify_Disarm_Photos_works() throws Exception {
        Home_Page home = PageFactory.initElements(driver, Home_Page.class);
        Panel_Camera_Page camera = PageFactory.initElements(driver, Panel_Camera_Page.class);
        Camera_Settings_Page set_cam = PageFactory.initElements(driver, Camera_Settings_Page.class);
        Settings_Page settings = PageFactory.initElements(driver, Settings_Page.class);
        Advanced_Settings_Page adv = PageFactory.initElements(driver, Advanced_Settings_Page.class);
        Installation_Page inst = PageFactory.initElements(driver, Installation_Page.class);
        ARM_STAY();
        home.DISARM.click();
        enter_default_user_code();
        swipeFromLefttoRight();

    }

    @AfterMethod
    public void tearDown () throws IOException, InterruptedException {
        log.endTestCase(page_name);
        driver.quit();
    }
}
