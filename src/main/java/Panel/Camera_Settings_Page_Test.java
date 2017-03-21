package Panel;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.IOException;

public class Camera_Settings_Page_Test extends  Setup{

    String page_name = "Camera Settings testing";
    Logger logger = Logger.getLogger(page_name);

    @BeforeMethod
    public void capabilities_setup() throws Exception {
        setup_driver("8ebdbc76", "http://127.0.1.1", "4723");
        setup_logger(page_name);}

    @Test
    public void Check_all_elements_on_Siren_Alarms_page() throws Exception {
        Camera_Settings_Page camera = PageFactory.initElements(driver, Camera_Settings_Page.class);
        Advanced_Settings_Page adv = PageFactory.initElements(driver, Advanced_Settings_Page.class);
        Installation_Page inst = PageFactory.initElements(driver, Installation_Page.class);
        navigate_to_Advanced_Settings_page();
        adv.INSTALLATION.click();
        inst.CAMERA_SETTINGS.click();
        logger.info("Verifying elements on the page...");
        Thread.sleep(1000);
        if (camera.Secure_Delete_Images_summery.isDisplayed()) {
            logger.info("Pass: Correct Secure Delete Images summery");
        }
        camera.Secure_Delete_Images.click();
        Thread.sleep(1000);
        if (camera.Secure_Delete_Images_summery_disabled.isDisplayed()) {
            logger.info("Pass: Correct Secure Delete Images summery when disabled");
        }
        camera.Secure_Delete_Images.click();
        Thread.sleep(1000);
        if (camera.Disarm_Photos_summery.isDisplayed()) {
            logger.info("Pass: Correct Disarm Photos summery");
        }
        camera.Disarm_Photos.click();
        Thread.sleep(1000);
        if (camera.Disarm_Photos_summery_disabled.isDisplayed()) {
            logger.info("Pass: Correct Disarm Photos summery when disabled");
        }
        camera.Disarm_Photos.click();
        Thread.sleep(1000);
        if (camera.Alarm_Photos_summery.isDisplayed()) {
            logger.info("Pass: Correct Alarm Photos summery");
        }
        camera.Alarm_Photos.click();
        Thread.sleep(1000);
        if (camera.Alarm_Photos_summery_disabled.isDisplayed()) {
            logger.info("Pass: Correct Alarm Photos summery when disabled");
        }
        camera.Alarm_Photos.click();
        Thread.sleep(1000);
        if (camera.Setting_Photos_summery.isDisplayed()) {
            logger.info("Pass: Correct Setting Photos summery");
        }
        camera.Setting_Photos.click();
        Thread.sleep(1000);
        if (camera.Setting_Photos_summery_enabled.isDisplayed()) {
            logger.info("Pass: Correct Setting Photos summery when enabled");
        }
        camera.Setting_Photos.click();
        Thread.sleep(1000);
        swipe_vertical();
        Thread.sleep(1000);
        if (camera.Allow_Master_Code_to_access_Camera_Settings_summery.isDisplayed()) {
            logger.info("Pass: Correct Allow Master Code to access Camera Settings summery");
        }
        camera.Allow_Master_Code_to_access_Camera_Settings.click();
        Thread.sleep(1000);
        if (camera.Allow_Master_Code_to_access_Camera_Settings_summery_enabled.isDisplayed()) {
            logger.info("Pass: Correct Allow Master Code to access Camera Settings summery when enabled");
        }
        camera.Allow_Master_Code_to_access_Camera_Settings.click();
        Thread.sleep(1000);
    }
    
    @AfterMethod
    public void tearDown () throws IOException, InterruptedException {
        log.endTestCase(page_name);
        driver.quit();
    }
}
