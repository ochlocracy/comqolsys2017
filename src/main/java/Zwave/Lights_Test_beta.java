package Zwave;

import jxl.read.biff.BiffException;
import org.apache.commons.io.FileUtils;
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
 * PRECONDITION: 3 lights must be paired and turned off before
 * executing this test.
 */
public class Lights_Test_beta extends Setup{
    String page_name = "Lights_Page_beta";
    Logger logger = Logger.getLogger(page_name);


    public Lights_Test_beta() throws IOException, BiffException {
    }

    public void clickAll(List<WebElement> ele){
        int i;
        int size = ele.size();
        for(i = 0; i < size; i++)
            ele.get(i).click();
    }

    public void checkAllStatus(File status, List<WebElement> ele) throws Exception{
        int i;
        int size = ele.size();
        for(i = 0; i < size; i++)
            checkStatus(status, ele.get(i));
    }

    @BeforeMethod
    public void capabilities_setup() throws Exception {
        setup_driver(udid_, "http://127.0.1.1", "4723");
        setup_logger(page_name);
    }

    @Test
    public void Check_all_elements_on_Lights_Page() throws Exception {
        Lights_Page_beta lights = PageFactory.initElements(driver, Lights_Page_beta.class);
        swipe_left();

        element_verification(lights.On_Button, "On Button");
        element_verification(lights.Off_Button, "Off Button");
        element_verification(lights.Get_Status_Button, "Get Status Button");


        List<WebElement> li1 = driver.findElements(By.id("com.qolsys:id/lightSelect"));
        List<WebElement> li2 = driver.findElements(By.id("com.qolsys:id/uiName"));
        List<WebElement> li3 = driver.findElements(By.id("com.qolsys:id/statusButton"));

        element_verification(li1.get(0), "Light1 Select Button");
        element_verification(li1.get(1), "Light2 Select Button");
        element_verification(li1.get(2), "Light3 Select Button");
        element_verification(li2.get(0), "Light1 Name");
        element_verification(li2.get(1), "Light2 Name");
        element_verification(li2.get(2), "Light3 Name");
        element_verification(li3.get(0), "Light1 Status");
        element_verification(li3.get(1), "Light2 Status");
        element_verification(li3.get(2), "Light3 Status");

        li1.clear();
        li2.clear();
        li3.clear();
    }

    @Test (priority = 1)
    public void Test_Lights_Page() throws Exception {


        // navigate to and initialize lights page
        Lights_Page_beta lights = PageFactory.initElements(driver, Lights_Page_beta.class);
        File light_on = new File(projectPath + "/scr/light_on");
        File light_off = new File(projectPath + "/scr/light_off");
        swipe_left();
        List<WebElement> li = driver.findElements(By.id("com.qolsys:id/lightSelect"));
        List<WebElement> status = driver.findElements(By.id("com.qolsys:id/statusButton"));

        //File screenshot = driver.getScreenshotAs(OutputType.FILE);
        //File screenshotLocation = new File(projectPath+"/scr/test");
        //FileUtils.copyFile(screenshot, screenshotLocation);
        //return;

        // check if light can be selected
        li.get(0).click();
        if(!checkAttribute(li.get(0), "checked", "true"))
            return;


        // check if light can be turned on
        lights.On_Button.click();
        Thread.sleep(10000);
        if(!checkAttribute(li.get(0), "checked", "false"))
            return;

        // check if light icon turns yellow
        if(!checkStatus(light_on, status.get(0)))
            return;

        // ensure light can be selected
        li.get(0).click();
        if(!checkAttribute(li.get(0), "checked", "true"))
            return;

        // check if light is deselected upon turn-off
        lights.Off_Button.click();
        Thread.sleep(10000);
        if(!checkAttribute(li.get(0), "checked", "false")){
            return;
        }

        // check if light icon turns grey
        if(!checkStatus(light_off, status.get(0)))
            return;

        // repeat above process but for multiple lights
        clickAll(li);
        //li.get(0).click();
        //li.get(1).click();
        //li.get(2).click();

        lights.On_Button.click();
        Thread.sleep(10000);

        //check that they're deselected
        if(!checkAttribute(li.get(0), "checked","false"))
            return;

        if(!checkAttribute(li.get(1), "checked","false"))
            return;

        if(!checkAttribute(li.get(2), "checked","false"))
            return;

        // check that lights turn yellow
        //checkAllStatus(light_on, status);
        checkStatus(light_on, status.get(0));
        checkStatus(light_on, status.get(1));
        checkStatus(light_on, status.get(2));

        clickAll(li);
        //li.get(0).click();
        //li.get(1).click();
        //li.get(2).click();
        lights.Off_Button.click();
        Thread.sleep(10000);

        //check that they're deselected
        if(!checkAttribute(li.get(0), "checked","false"))
            return;

        if(!checkAttribute(li.get(1), "checked","false"))
            return;

        if(!checkAttribute(li.get(2), "checked","false"))

        // check that lights turn grey
        //checkAllStatus(light_off, status);
        logger.info("testing first element");
        checkStatus(light_off, status.get(0));
        logger.info("testing second element");
        checkStatus(light_off, status.get(1));
        logger.info("testing last element");
        checkStatus(light_off, status.get(2));
        logger.info("done testing");

        Thread.sleep(6000);
    }

    @AfterMethod
    public void tearDown () throws IOException, InterruptedException {
        log.endTestCase(page_name);
        driver.quit();
    }
}
