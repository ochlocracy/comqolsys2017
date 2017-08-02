package Zwave;

import ADC.ADC;
import Panel.Setup;
import jxl.read.biff.BiffException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.List;

/**
 * At least one Zwave device must be paired, no devices can be named "Family Room Light" or "ADC"
 */
public class Device_Renaming extends Setup{

    String page_name = "Device Renaming";
    Logger logger = Logger.getLogger(page_name);
    ADC adc = new ADC();
    String rename = "";
    String rename1 = "Family Room Light";
    String rename2 = "ADC";

    //ADC Credentials
    String login = "Gen2-8334";
    String password = "qolsys1234";

    public Device_Renaming() throws IOException, BiffException {
    }

    public void navigate_to_ZWave_Page(){
        navigate_to_Advanced_Settings_page();
        driver.findElement(By.xpath("//android.widget.TextView[@text='Installation']")).click();
        driver.findElement(By.xpath("//android.widget.TextView[@text='Devices']")).click();
        driver.findElement(By.xpath("//android.widget.TextView[@text='Z-wave Devices']")).click();
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
    public void renameFromPanel() throws Exception {
        int i;

        navigate_to_Advanced_Settings_page();

        navigate_to_ZWave_Page();
        driver.findElement(By.xpath("//android.widget.TextView[@text='Edit Device']")).click();
        driver.findElement(By.id("com.qolsys:id/edit")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("android:id/text1")).click();
        driver.findElement(By.xpath("//android.widget.CheckedTextView[@text='Family Room Light']")).click();
        driver.findElement(By.id("com.qolsys:id/editButton")).click();
        Thread.sleep(5000);

        if(!driver.findElement(By.id("com.qolsys:id/nodeName")).getAttribute("text").equals(rename1))
            logger.info("Fail: Name change not reflected in Panel UI (Edit Z-Wave Devices Page)");
        else
            logger.info("Pass: Name change successfully reflected in Panel UI (Edit Z-Wave Devices Page)");

        driver.findElement(By.id("com.qolsys:id/ft_home_button")).click();
        swipe_left();

        if(!driver.findElement(By.id("com.qolsys:id/uiName")).getAttribute("text").equalsIgnoreCase(rename1))
            logger.info("Fail: Name change not reflected in Panel UI (Lights Page)");
        else
            logger.info("Pass: Name change successfully reflected in Panel UI (Lights Page)");

        adc.navigate_to_user_site_lights(login, password);

        for(i = 0; i < 3; i++) {
            if (adc.getDriver1().findElement(By.id("ctl00_phBody_ucLightDeviceRepeaterControl_SwitchesAndDimmers" +
                    "_rptDevices_ctl0" + i + "_lnkDeviceName")).getText().equals(rename1)){
                logger.info("Pass: Name change successfully reflected on user site");
                break;
            }
        }
        if(i == 3)
            logger.info("Fail: Name change not reflected on user site");

        Thread.sleep(2000);
    }

    @Test (priority = 1)
    public void renameFromUserSite() throws Exception {
        int i, j, size;

        adc.navigate_to_user_site_lights(login, password);
        adc.getDriver1().findElement(By.id("ctl00_phBody_ucLightDeviceRepeaterControl_SwitchesAndDimmers_rptDevices_" +
                "ctl00_lnkDeviceName")).click();
        Thread.sleep(5000);
        WebElement text = adc.getDriver1().findElement(By.id("ctl00_phBody_txtEditName"));
        text.clear();
        text.sendKeys(rename2);
        adc.getDriver1().findElement(By.id("ctl00_phBody_btnSaveEdit")).click();
        Thread.sleep(5000);

        swipe_left();
        List<WebElement> li = driver.findElements(By.id("com.qolsys:id/uiName"));

        size = li.size();
        for(i = 0; i < size; i++){
            if(li.get(i).getText().equals(rename2)) {
                logger.info("Pass: Name change reflected in Panel UI (Lights Page)");
                break;
            }
        }

        if(i == size)
            logger.info("Fail: Name change not reflected in Panel UI (Lights Page)");

        li.clear();

        swipeFromLefttoRight();
        navigate_to_ZWave_Page();
        driver.findElement(By.xpath("//android.widget.TextView[@text='Edit Device']")).click();

        li = driver.findElements(By.id("com.qolsys:id/nodeName"));
        size = li.size();

        for(j = 0; j < size; j++){
            if(li.get(j).getText().equals(rename2)) {
                logger.info("Pass: Name change reflected in Panel UI (Edit Z-Wave Devices Page)");
                break;
            }
        }

        if(i == size)
            logger.info("Fail: Name change not reflected in Panel UI (Edit Z-Wave Devices Page)");
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
