package Settings;


import Panel.*;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class Allow_Master_Code_to_access_Camera_Settings_Test extends Setup {

    String page_name = "Allow Master Code to access Camera Settings testing";
    Logger logger = Logger.getLogger(page_name);

    @BeforeMethod
    public void capabilities_setup() throws Exception {
        setup_driver("8ebdbc76", "http://127.0.1.1", "4723");
        setup_logger(page_name);}

    @Test
    public void Verify_Master_Code_gets_access_to_Camera_Settings_page() throws Exception {
        Camera_Settings_Page set_cam =  PageFactory.initElements(driver, Camera_Settings_Page.class);
        Settings_Page settings = PageFactory.initElements(driver, Settings_Page.class);
        Advanced_Settings_Page adv = PageFactory.initElements(driver, Advanced_Settings_Page.class);
        Installation_Page inst = PageFactory.initElements(driver, Installation_Page.class);
        Thread.sleep(3000);
        logger.info("Navigate to the setting page to enable the access to the Camera Settings page using Master Code");
        navigate_to_Advanced_Settings_page();
        adv.INSTALLATION.click();
        inst.CAMERA_SETTINGS.click();
        Thread.sleep(2000);
        swipe_vertical();
        Thread.sleep(1000);
        set_cam.Allow_Master_Code_to_access_Camera_Settings.click();
        Thread.sleep(1000);
        settings.Home_button.click();
        Thread.sleep(1000);
        navigate_to_Settings_page();
        settings.ADVANCED_SETTINGS.click();
        enter_default_user_code();
        Thread.sleep(2000);
        if (inst.CAMERA_SETTINGS.isDisplayed()){
            logger.info("Pass: Camera Settings icon is present");
        }else {  logger.info("Failed: Camera Settings icon is NOT present");}
        Thread.sleep(2000);
        settings.Home_button.click();
        navigate_to_Advanced_Settings_page();
        adv.INSTALLATION.click();
        inst.CAMERA_SETTINGS.click();
        Thread.sleep(2000);
        swipe_vertical();
        Thread.sleep(2000);
        set_cam.Allow_Master_Code_to_access_Camera_Settings.click();
        Thread.sleep(2000);
        settings.Home_button.click();
        logger.info("Verify Camera Settings icon disappears after disabling the setting");
        navigate_to_Settings_page();
        settings.ADVANCED_SETTINGS.click();
        enter_default_user_code();
        Thread.sleep(2000);
        try {
            if (inst.CAMERA_SETTINGS.isDisplayed())
                logger.info("Failed: Camera Settings icon is present");
        } catch(Exception e){
            logger.info("Pass: Camera Settings icon is NOT present");
        } finally{
        }
    }

    @AfterMethod
    public void tearDown() throws IOException, InterruptedException {
        log.endTestCase(page_name);
        driver.quit();
    }
}
