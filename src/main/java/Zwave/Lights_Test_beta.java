package Zwave;

import jxl.read.biff.BiffException;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.IOException;
import Panel.Setup;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by nchortek on 6/22/17.
 * PRECONDITION: A light must be paired and turned off before
 * executing this test.
 */
public class Lights_Test_beta extends Setup {
    String page_name = "Lights_Page_beta";
    Logger logger = Logger.getLogger(page_name);



    public Lights_Test_beta() throws IOException, BiffException {
    }

    @BeforeMethod
    public void capabilities_setup() throws Exception {
        setup_driver(udid_, "http://127.0.1.1", "4723");
        setup_logger(page_name);
    }

    public void swipe_left() throws InterruptedException {
        int starty = 400;
        int endx = 360;
        int startx = 660;
        driver.swipe(startx, starty, endx, starty, 500);
        Thread.sleep(2000);
    }

    @Test
    public void Check_all_elements_on_Lights_page() throws Exception {
        // navigate to and initialize lights page
        Lights_Page_beta lights = PageFactory.initElements(driver, Lights_Page_beta.class);
        //int dimness;
        String current_activity;
        //SeekBar Dimmer_Bar;
        //Dimmer_Bar = (SeekBar)findViewById();
        swipe_left();

        //driver.currentActivity();

        // get initial dimness
        //dimness = lights.Dimmer_Bar.getProgress();
        //logger.info("current dimness:"+dimness);

        // check if light can be selected
        lights.Light_Select.click();
        if(lights.Light_Select.getAttribute("checked").equals("true")) {
            logger.info("Pass: successful selection of light");
        }
        else{
            logger.info("Fail: unsuccessful selection of light, remaining tests skipped");
            return;
        }

        // check if light can be turned on
        lights.On_Button.click();
        Thread.sleep(2000);
        if (lights.Light_Select.getAttribute("checked").equals("false")) {
            logger.info("Pass: successful deselection of light upon turn-on");
        }
        else{
            logger.info("Fail: unsuccessful deselection of light upon turn-on, remaining tests skipped");
            return;
        }

        // check if dimness remains constant from Off to On
        //if (dimness == lights.Dimmer_Bar.getProgress()) {
        //   logger.info("Pass: Dimness level remains constant when moving from Off to On");
        //}
        //else{
        //    logger.info("Fail: Dimness level changes when moving from Off to On");
        //}

        // get new dimness
        //dimness = lights.Dimmer_Bar.getProgress();
        //logger.info("current dimness:"+dimness);

        // ensure light is selected
        lights.Light_Select.click();
        if (lights.Light_Select.getAttribute("checked").equals("true")) {
            logger.info("Pass: successful selection of light");
        }
        else{
            logger.info("Fail: unsuccessful seselection of light, remaining tests skipped");
            return;
        }

        // check if light is deselected upon turn-off
        lights.Off_Button.click();
        Thread.sleep(2000);
        if (lights.Light_Select.getAttribute("checked").equals("false")) {
            logger.info("Pass: successful deselection of light upon turn-off");
        }
        else{
            logger.info("Fail: unsuccessful deselection of light upon turn-off, remaining tests skipped");
            return;
        }

        // check if dimness remain constant from On to Off
        //if (dimness == lights.Dimmer_Bar.getProgress()) {
        //    logger.info("Pass: Dimness level remains constant when moving from On to Off");
        //}
        //else{
        //    logger.info("Fail: Dimness level changes when moving from On to Off");
        //}

    }

    @AfterMethod
    public void tearDown () throws IOException, InterruptedException {
        log.endTestCase(page_name);
        driver.quit();
    }
}
