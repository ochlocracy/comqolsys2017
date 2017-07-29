package Zwave;

import ADC.ADC;
import Panel.Advanced_Settings_Page;
import Panel.Devices_Page;
import Panel.Installation_Page;
import Panel.Setup;
import jxl.read.biff.BiffException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.List;

/**
 * At least one Zwave device must be paired, and it's name must be something other than "temp"
 */
public class Device_Renaming extends Setup{

    String page_name = "Device Renaming";
    Logger logger = Logger.getLogger(page_name);
    ADC adc = new ADC();
    String rename = "temp";

    //ADC Credentials
    String login = "Gen2-8334";
    String password = "qolsys1234";

    public Device_Renaming() throws IOException, BiffException {
    }


    @BeforeTest
    public void capabilities_setup() throws Exception {
        setup_driver( get_UDID(),"http://127.0.1.1", "4723");
        setup_logger(page_name);
    }

    @BeforeMethod
    public  void webDriver() {
        adc.webDriverSetUp();
    }

    @Test
    public void renameFromPanel() throws InterruptedException {
        int i;

        Advanced_Settings_Page adv = PageFactory.initElements(driver, Advanced_Settings_Page.class);
        Installation_Page inst = PageFactory.initElements(driver, Installation_Page.class);
        Devices_Page dev = PageFactory.initElements(driver, Devices_Page.class);
        navigate_to_Advanced_Settings_page();

        adv.INSTALLATION.click();
        inst.DEVICES.click();
        dev.Zwave_Devices.click();
        driver.findElement(By.xpath("//android.widget.TextView[@text='Edit Device']")).click();
        driver.findElement(By.id("com.qolsys:id/edit")).click();
        driver.findElement(By.id("android:id/text1")).click();

        List<WebElement> li = driver.findElements(By.xpath("//android.widget.CheckedTextView[@text='Upstairs Light']"));
        if(li.size() > 0){
            WebElement top = driver.findElement(By.xpath("//android.widget.CheckedTextView[@text='Bedroom Light']"));

            touchSwipe(top.getLocation().getX(), top.getLocation().getY(), li.get(0).getLocation().getX(),
                    li.get(0).getLocation().getY());
        }

        driver.findElement(By.xpath("//android.widget.CheckedTextView[@text='Custom Name']")).click();
        driver.findElement(By.id("com.qolsys:id/customDesc1")).sendKeys(rename);
        driver.findElement(By.id("com.qolsys:id/editButton")).click();
        Thread.sleep(5000);

        if(!driver.findElement(By.id("com.qolsys:id/nodeName")).getAttribute("text").equals(rename))
            logger.info("Fail: Name change not reflected in Panel UI");
        else
            logger.info("Pass: Name change successfully reflected in Panel UI");

        adc.navigate_to_user_site_lights(login, password);

        for(i = 0; i < 3; i++) {
            if (adc.getDriver1().findElement(By.id("ctl00_phBody_ucLightDeviceRepeaterControl_SwitchesAndDimmers" +
                    "_rptDevices_ctl0" + i + "_lnkDeviceName")).getText().equals(rename)){
                logger.info("Pass: Name change successfully reflected on user site");
                break;
            }
        }
        if(i == 3)
            logger.info("Fail: Name change not reflected on user site");
    }


    @AfterMethod
    public void webDriverQuit () {
        adc.getDriver1().quit();
    }

    @AfterTest
    public void tearDown() throws IOException, InterruptedException {
        log.endTestCase(page_name);
        driver.quit();
    }



}
