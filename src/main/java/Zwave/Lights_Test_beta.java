package Zwave;

import jxl.read.biff.BiffException;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import Panel.Setup;
import java.util.List;



/**
 * Created by nchortek on 6/22/17.
 * PRECONDITION: 5 lights must be paired and turned off before
 * executing this test.
 */
public class Lights_Test_beta extends Setup{
    String page_name = "Lights_Page_beta";
    Logger logger = Logger.getLogger(page_name);


    public Lights_Test_beta() throws IOException, BiffException {
    }

    @BeforeMethod
    public void capabilities_setup() throws Exception {
        setup_driver(udid_, "http://127.0.1.1", "4723");
        setup_logger(page_name);
    }


    @Test
    public void Test_Lights_Page() throws Exception {


        // navigate to and initialize lights page
        Lights_Page_beta lights = PageFactory.initElements(driver, Lights_Page_beta.class);
        swipe_left();

        // check if light can be selected
        lights.Light_Select.click();
        if(!checkAttribute(lights.Light_Select, "checked", "true"))
            return;

        // check if light can be turned on
        lights.On_Button.click();
        Thread.sleep(2000);
        if(!checkAttribute(lights.Light_Select, "checked", "false"))
            return;

        // check if light icon turns yellow
        File light_on = new File("/home/nchortek/comqolsys2017/scr/light_on");
        File tmp = takeScreenshot(lights.Light_Icon, "/home/nchortek/comqolsys2017/scr/tmp");
        if(compareImage(tmp, light_on)){
            logger.info("Pass: light icon turns yellow upon turn-on");
        }
        else{
            logger.info("Fail: light icon does not turn yellow upon turn-on");
        }
        java.lang.Runtime.getRuntime().exec("rm -f " + tmp.getAbsolutePath());

        // ensure light can be selected
        lights.Light_Select.click();
        if(!checkAttribute(lights.Light_Select, "checked", "true"))
            return;

        // check if light is deselected upon turn-off
        lights.Off_Button.click();
        Thread.sleep(2000);
        if(!checkAttribute(lights.Light_Select, "checked", "false")){
            return;
        }

        List<WebElement> li = driver.findElements(By.id("com.qolsys:id/lightSelect"));
        li.get(0).click();
        li.get(1).click();
        li.get(2).click();
        li.clear();
        swipe_up();
        li = driver.findElements(By.id("com.qolsys:id/lightSelect"));
        li.get(1).click();
        li.get(2).click();
        lights.On_Button.click();
        Thread.sleep(6000);

        //check that they're deselected
        if(!checkAttribute(li.get(0), "checked","false"))
            return;

        if(!checkAttribute(li.get(1), "checked","false"))
            return;

        if(!checkAttribute(li.get(2), "checked","false"))
            return;

        li.clear();
        swipe_down();

        li = driver.findElements(By.id("com.qolsys:id/lightSelect"));

        if(!checkAttribute(li.get(0), "checked","false"))
            return;

        if(!checkAttribute(li.get(1), "checked","false"))
            return;

        li.get(0).click();
        li.get(1).click();
        li.get(2).click();
        li.clear();
        swipe_up();
        li = driver.findElements(By.id("com.qolsys:id/lightSelect"));
        li.get(1).click();
        li.get(2).click();
        lights.Off_Button.click();
        Thread.sleep(6000);

        //check that they're deselected
        if(!checkAttribute(li.get(0), "checked","false"))
            return;

        if(!checkAttribute(li.get(1), "checked","false"))
            return;

        if(!checkAttribute(li.get(2), "checked","false"))
            return;

        li.clear();
        swipe_down();

        li = driver.findElements(By.id("com.qolsys:id/lightSelect"));

        if(!checkAttribute(li.get(0), "checked","false"))
            return;

        if(!checkAttribute(li.get(1), "checked","false"))
            return;

        li.clear();
        Thread.sleep(6000);



    }

    @AfterMethod
    public void tearDown () throws IOException, InterruptedException {
        log.endTestCase(page_name);
        driver.quit();
    }
}
