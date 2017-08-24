package Settings_Grid;

import Panel.*;
import jxl.read.biff.BiffException;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;

public class Disarm_Photos_Test_Grid {
    Setup1 s = new Setup1();
    String page_name = "Disarm Photos testing";
    Logger logger = Logger.getLogger(page_name);

    public Disarm_Photos_Test_Grid() throws IOException, BiffException {}
    @Parameters({"deviceName_", "applicationName_", "UDID_", "platformVersion_", "URL_", "PORT_" })
    @BeforeClass
    public void setUp(String deviceName_, String applicationName_, String UDID_, String platformVersion_, String URL_, String PORT_) throws Exception {
        s.setCapabilities(URL_);
        s.setup_logger(page_name, UDID_);
    }
    @Parameters({"UDID_"})
    @Test
    public void Verify_Disarm_Photos_works(String UDID_) throws Exception {
        Home_Page home = PageFactory.initElements(s.getDriver(), Home_Page.class);
        Panel_Camera_Page camera = PageFactory.initElements(s.getDriver(), Panel_Camera_Page.class);
        Camera_Settings_Page set_cam = PageFactory.initElements(s.getDriver(), Camera_Settings_Page.class);
        Settings_Page settings = PageFactory.initElements(s.getDriver(), Settings_Page.class);
        Advanced_Settings_Page adv = PageFactory.initElements(s.getDriver(), Advanced_Settings_Page.class);
        Installation_Page inst = PageFactory.initElements(s.getDriver(), Installation_Page.class);
        logger.info("Verifying Disarm photo is taken when setting in enabled...");
        s.delete_all_camera_photos();
        Thread.sleep(1000);
        s.ARM_STAY();
        home.DISARM.click();
        s.enter_default_user_code();
        s.swipeFromLefttoRight();
        camera.Disarm_photos.click();
        if (camera.Photo_lable.isDisplayed()){
            logger.info(UDID_ + " Pass: Disarm photo is displayed");
        }else { s.take_screenshot();
            logger.info(UDID_ + " Failed: Disarm photo is NOT displayed");}
        camera.Camera_delete.click();
        camera.Camera_delete_yes.click();
        s.enter_default_user_code();
        Thread.sleep(1000);
        logger.info("Verifying Disarm photo is NOT taken when setting in disabled...");
        s.navigate_to_Advanced_Settings_page();
        adv.INSTALLATION.click();
        inst.CAMERA_SETTINGS.click();
        Thread.sleep(1000);
        set_cam.Disarm_Photos.click();
        Thread.sleep(1000);
        settings.Home_button.click();
        Thread.sleep(1000);
        s.ARM_STAY();
        home.DISARM.click();
        s.enter_default_user_code();
        s.swipeFromLefttoRight();
        camera.Disarm_photos.click();
        try {
            if (camera.Photo_lable.isDisplayed())
                s.take_screenshot();
            logger.info(UDID_ + " Failed: Disarm photo is displayed");
        } catch (Exception e) {
            logger.info(UDID_ + " Pass: Disarm photo is NOT displayed");
        } finally {
        }
        Thread.sleep(1000);
        s.navigate_to_Advanced_Settings_page();
        adv.INSTALLATION.click();
        Thread.sleep(1000);
        inst.CAMERA_SETTINGS.click();
        Thread.sleep(1000);
        set_cam.Disarm_Photos.click();
        Thread.sleep(1000);
        settings.Home_button.click();
    }
    @AfterClass
    public void tearDown () throws IOException, InterruptedException {
        s.log.endTestCase(page_name);
        s.getDriver().quit();
    }
}