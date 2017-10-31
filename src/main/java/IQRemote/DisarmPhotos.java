package IQRemote;

import Panel.*;
import jxl.read.biff.BiffException;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class DisarmPhotos extends  Setup_Remote{

    final String iqRemoteUDID = "6NJUMAGLVK";

    public DisarmPhotos() throws IOException, BiffException {}

    @BeforeMethod
    public void Setup () throws Exception {
        setup_driver(iqRemoteUDID, "http://127.0.1.1", "4723");
        }

    @Test
    public void disarmPhotos() throws Exception {
        Home_Page home = PageFactory.initElements(driver, Home_Page.class);
        Panel_Camera_Page camera = PageFactory.initElements(driver, Panel_Camera_Page.class);
        System.out.println("Verifying Disarm photo is taken when setting in enabled...");
        delete_all_camera_photos();
        Thread.sleep(1000);
        ARM_STAY();
        home.DISARM.click();
        enter_default_user_code();
        swipeFromLefttoRight();
        camera.Disarm_photos.click();
        if (camera.Photo_lable.isDisplayed()){
            System.out.println("Pass: Disarm photo is displayed");
        }else {
            System.out.println("Failed: Disarm photo is NOT displayed");}
        camera.Camera_delete.click();
        camera.Camera_delete_yes.click();
        enter_default_user_code();
        Thread.sleep(1000);
        }

    @AfterMethod
    public void TearDown(){
        driver.quit();
    }
}
