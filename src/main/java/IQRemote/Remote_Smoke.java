package IQRemote;

import Panel.Emergency_Page;
import Panel.Home_Page;
import Panel.Setup;
import io.appium.java_client.TouchAction;
import jxl.read.biff.BiffException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.text.SimpleDateFormat;


public class Remote_Smoke extends Setup {

    public Remote_Smoke() throws IOException, BiffException {}

    public Runtime rt = Runtime.getRuntime();

    SimpleDateFormat time_formatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
    String currentTime = time_formatter.format(System.currentTimeMillis());

    public void AppiumStart() throws IOException, InterruptedException {
     rt.exec(adbPath + " /usr/local/bin/appium");
        System.out.println("Appium session is started successfully");
        Thread.sleep(4000);
    }

    public void setSliderValue ( WebElement elem, int intToSlideValue) {
        WebElement slider = elem;
        int startX = slider.getLocation().getX();
        int yAxis = slider.getLocation().getY();
        int moveToDirectionAt = intToSlideValue + startX;
        TouchAction act = new TouchAction(driver);
        act.longPress(startX, yAxis).moveTo(moveToDirectionAt, yAxis).release().perform();
    }

    @BeforeClass
    public void capabilities_setup() throws Exception {

        setup_driver(get_UDID(),"http://127.0.1.1", "4723");}

    @Test
    public void Test1 () throws Exception {

        swipeFromRighttoLeft();
        Thread.sleep(3000);
        WebElement slider = driver.findElement(By.id("com.qolsys:id/dimmer_seek_bar"));
        int sliderSize = slider.getSize().getHeight();
        setSliderValue(slider,(int)(sliderSize *0.5));
        Thread.sleep(3000);
    }

    @Test
    public void TestMain() throws Exception {
        /*** PANEL ARMING ***/

        Home_Page home_page = PageFactory.initElements(driver, Home_Page.class);

        rt.exec(adbPath + " shell logcat -T" + currentTime + " -v time -s QolsysProvider > /data/log.txt");

        for (int i =1000; i>0; i--) {
            ARM_STAY();
            Thread.sleep(5000);
            DISARM();
            Thread.sleep(5000);
            ARM_AWAY(15);
            Thread.sleep(5000);
            home_page.ArwAway_State.click();
            enter_default_user_code();
            Thread.sleep(5000);

            /*** EMERGENCY ***/

            Emergency_Page emergency_page = PageFactory.initElements(driver, Emergency_Page.class);

            emergency_page.Emergency_Button.click();
            Thread.sleep(2000);
            emergency_page.Police_icon.click();
            Thread.sleep(5000);
            emergency_page.Cancel_Emergency.click();
            enter_default_user_code();
            Thread.sleep(5000);

            emergency_page.Emergency_Button.click();
            Thread.sleep(2000);
            emergency_page.Fire_icon.click();
            Thread.sleep(5000);
            emergency_page.Cancel_Emergency.click();
            enter_default_user_code();
            Thread.sleep(5000);

            emergency_page.Emergency_Button.click();
            Thread.sleep(2000);
            emergency_page.Auxiliary_icon.click();
            Thread.sleep(5000);
            emergency_page.Cancel_Emergency.click();
            enter_default_user_code();
            Thread.sleep(5000);
        }

    }

    @AfterClass
    public void tearDown () throws IOException, InterruptedException {
        driver.quit();
    }
}


