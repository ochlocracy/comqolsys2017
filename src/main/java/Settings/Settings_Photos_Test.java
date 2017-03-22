package Settings;

import Panel.*;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.IOException;

public class Settings_Photos_Test extends Setup {

    String page_name = "Settings Photos testing";
    Logger logger = Logger.getLogger(page_name);

    @BeforeMethod
    public void capabilities_setup() throws Exception {
        setup_driver("8ebdbc76", "http://127.0.1.1", "4723");
        setup_logger(page_name);
    }
    @Test
    public void Verify_Alarm_Photos_works() throws Exception {
        Home_Page home = PageFactory.initElements(driver, Home_Page.class);
        Emergency_Page emergency = PageFactory.initElements(driver, Emergency_Page.class);
        Panel_Camera_Page camera = PageFactory.initElements(driver, Panel_Camera_Page.class);
        Camera_Settings_Page set_cam = PageFactory.initElements(driver, Camera_Settings_Page.class);
        Settings_Page settings = PageFactory.initElements(driver, Settings_Page.class);
        Advanced_Settings_Page adv = PageFactory.initElements(driver, Advanced_Settings_Page.class);
        Installation_Page inst = PageFactory.initElements(driver, Installation_Page.class);
        logger.info("Verifying Settings photo is NOT taken when setting in disabled...");
        navigate_to_Settings_page();
        settings.ADVANCED_SETTINGS.click();
        enter_default_user_code();
        Thread.sleep(1000);
        settings.Home_button.click();
        swipeFromLefttoRight();
        camera.Settings_photos.click();
        try {
            if (camera.Photo_lable.isDisplayed())
                logger.info("Failed: Disarm photo is displayed");
        } catch (Exception e) {
            logger.info("Pass: Disarm photo is NOT displayed");
        } finally {
        }
        logger.info("Verifying Settings photo is taken when setting in enabled...");
        navigate_to_Advanced_Settings_page();
        adv.INSTALLATION.click();
        inst.CAMERA_SETTINGS.click();
        set_cam.Setting_Photos.click();
        Thread.sleep(1000);
        settings.Home_button.click();
        navigate_to_Settings_page();
        settings.ADVANCED_SETTINGS.click();
        enter_default_user_code();
        Thread.sleep(1000);
        settings.Home_button.click();
        swipeFromLefttoRight();
        camera.Settings_photos.click();
        if (camera.Photo_lable.isDisplayed()){
            logger.info("Pass: Settings photo is displayed");
        }else { logger.info("Failed: Settings photo is NOT displayed");}
        camera.Camera_delete.click();
        camera.Camera_delete_yes.click();
        enter_default_user_code();
        navigate_to_Advanced_Settings_page();
        adv.INSTALLATION.click();
        inst.CAMERA_SETTINGS.click();
        set_cam.Setting_Photos.click();
        Thread.sleep(1000);
        settings.Home_button.click();
    }
    @AfterMethod
    public void tearDown () throws IOException, InterruptedException {
        log.endTestCase(page_name);
        driver.quit();
    }

}
