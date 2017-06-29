package Zwave;

import jxl.read.biff.BiffException;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.File;
import java.io.IOException;
import Panel.Setup;
import java.util.List;

import javax.imageio.ImageIO;


/**
 * Created by nchortek on 6/22/17.
 * PRECONDITION: 5 lights must be paired and turned off before
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

    public void swipe_up() throws InterruptedException {
        int starty = 616;
        int endy = 227;
        int startx = 314;
        driver.swipe(startx, starty, startx, endy, 3000);
        Thread.sleep(2000);
    }

    public void swipe_down() throws InterruptedException {
        int starty = 227;
        int endy = 616;
        int startx = 314;
        driver.swipe(startx, starty, startx, endy, 3000);
        Thread.sleep(2000);
    }

    //compares two images and returns whether or not they're identical
    public boolean compareImage(File fileA, File fileB) {
        try {
            // take buffer data from both image files //
            BufferedImage biA = ImageIO.read(fileA);
            DataBuffer dbA = biA.getData().getDataBuffer();
            int sizeA = dbA.getSize();
            BufferedImage biB = ImageIO.read(fileB);
            DataBuffer dbB = biB.getData().getDataBuffer();
            int sizeB = dbB.getSize();
            // compare data-buffer objects //
            if(sizeA == sizeB) {
                for(int i=0 ; i< sizeA ; i++) {
                    if(dbA.getElem(i) != dbB.getElem(i)) {
                        return false;
                    }
                }
                return true;
            }
            else {
                return false;
            }
        }
        catch (Exception e) {
            logger.info("Failed to compare image files ..." + e);
            return false;
        }
    }

    //takes a screenshot of the given element and saves it to the given destination
    public File takeScreenshot(WebElement ele, String dst) throws Exception{
        // Get entire page screenshot
        File screenshot = driver.getScreenshotAs(OutputType.FILE);
        BufferedImage  fullImg = ImageIO.read(screenshot);

        // Get the location of element on the page
        org.openqa.selenium.Point point = ele.getLocation();

        // Get width and height of the element
        int eleWidth = ele.getSize().getWidth();
        int eleHeight = ele.getSize().getHeight();

        // Crop the entire page screenshot to get only element screenshot
        BufferedImage eleScreenshot= fullImg.getSubimage(point.getX(), point.getY(),
                eleWidth, eleHeight);
        ImageIO.write(eleScreenshot, "png", screenshot);

        // Copy the element screenshot to disk
        File screenshotLocation = new File(dst);
        FileUtils.copyFile(screenshot, screenshotLocation);

        return screenshotLocation;
    }


    @Test
    public void Check_all_elements_on_Lights_page() throws Exception {
        // navigate to and initialize lights page
        Lights_Page_beta lights = PageFactory.initElements(driver, Lights_Page_beta.class);
        swipe_left();

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

        // check if light icon turns yellow
        File light_on = new File("/home/nchortek/light_on.png");
        File tmp = takeScreenshot(lights.Light_Icon, "/home/nchortek/Pictures/tmp");
        if(compareImage(tmp, light_on)){
            logger.info("Pass: light icon turns yellow upon turn-on");
        }
        else{
            logger.info("Fail: light icon does not turn yellow upon turn-on");
        }

        // ensure light can be selected
        lights.Light_Select.click();
        if (lights.Light_Select.getAttribute("checked").equals("true")) {
            logger.info("Pass: successful selection of light");
        }
        else{
            logger.info("Fail: unsuccessful selection of light, remaining tests skipped");
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
        if(li.get(0).getAttribute("checked").equals("false")){
            logger.info("Pass: successful deselection of light 3 upon turn-on");
        }
        else{
            logger.info("Fail: unsuccessful deselection of light 3 upon turn-on remaining tests skipped");
            return;
        }

        if(li.get(1).getAttribute("checked").equals("false")){
            logger.info("Pass: successful deselection of light 4 upon turn-on");
        }
        else{
            logger.info("Fail: unsuccessful deselection of light 4 upon turn-on remaining tests skipped");
            return;
        }

        if(li.get(2).getAttribute("checked").equals("false")){
            logger.info("Pass: successful deselection of light 5 upon turn-on");
        }
        else{
            logger.info("Fail: unsuccessful deselection of light 5 upon turn-on remaining tests skipped");
            return;
        }

        li.clear();
        swipe_down();

        li = driver.findElements(By.id("com.qolsys:id/lightSelect"));

        if(li.get(0).getAttribute("checked").equals("false")){
            logger.info("Pass: successful deselection of light 1 upon turn-on");
        }
        else{
            logger.info("Fail: unsuccessful deselection of light 1 upon turn-on remaining tests skipped");
            return;
        }

        if(li.get(1).getAttribute("checked").equals("false")){
            logger.info("Pass: successful deselection of light 2 upon turn-on");
        }
        else{
            logger.info("Fail: unsuccessful deselection of light 2 upon turn-on remaining tests skipped");
            return;
        }

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

        if(li.get(0).getAttribute("checked").equals("false")){
            logger.info("Pass: successful deselection of light 3 upon turn-off");
        }
        else{
            logger.info("Fail: unsuccessful deselection of light 3 upon turn-off");
        }

        if(li.get(1).getAttribute("checked").equals("false")){
            logger.info("Pass: successful deselection of light 4 upon turn-off");
        }
        else{
            logger.info("Fail: unsuccessful deselection of light 4 upon turn-off");
        }

        if(li.get(2).getAttribute("checked").equals("false")){
            logger.info("Pass: successful deselection of light 5 upon turn-off");
        }
        else{
            logger.info("Fail: unsuccessful deselection of light 5 upon turn-off");
        }

        li.clear();
        swipe_down();

        li = driver.findElements(By.id("com.qolsys:id/lightSelect"));

        if(li.get(0).getAttribute("checked").equals("false")){
            logger.info("Pass: successful deselection of light 1 upon turn-off");
        }
        else{
            logger.info("Fail: unsuccessful deselection of light 1 upon turn-off");
        }

        if(li.get(1).getAttribute("checked").equals("false")){
            logger.info("Pass: successful deselection of light 2 upon turn-off");
        }
        else{
            logger.info("Fail: unsuccessful deselection of light 2 upon turn-off");
        }



        li.clear();
        Thread.sleep(6000);



    }

    @AfterMethod
    public void tearDown () throws IOException, InterruptedException {
        log.endTestCase(page_name);
        driver.quit();
    }
}
