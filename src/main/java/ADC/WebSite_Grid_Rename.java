package ADC;

import Panel.*;
import jxl.read.biff.BiffException;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class WebSite_Grid_Rename {
    Setup1 s = new Setup1();
    ADC myADC = new ADC();
    String page_name = "ADC sensors name change";
    Logger logger = Logger.getLogger(page_name);
    public WebDriver driver1;

    public WebSite_Grid_Rename() throws IOException, BiffException {
    }
    public void swipingVertical() throws InterruptedException {
        int starty = 660;
        int endy = 260;
        int startx = 502;
        s.getDriver().swipe(startx, starty, startx, endy, 3000);
        Thread.sleep(2000);
    }
    @Parameters({"deviceName_", "applicationName_", "UDID_", "platformVersion_", "URL_", "PORT_" })
    @BeforeClass
    public void setUp(String deviceName_, String applicationName_, String UDID_, String platformVersion_, String URL_, String PORT_) throws Exception {
        s.setCapabilities(URL_);
        s.setup_logger(page_name, UDID_);
    }

    @Test
    public void Test3() throws Exception {
        Installation_Page inst = PageFactory.initElements(s.getDriver(), Installation_Page.class);
        Advanced_Settings_Page adv = PageFactory.initElements(s.getDriver(), Advanced_Settings_Page.class);
        Devices_Page devices = PageFactory.initElements(s.getDriver(), Devices_Page.class);
        Security_Sensors_Page sec_sensors = PageFactory.initElements(s.getDriver(), Security_Sensors_Page.class);
        logger.info("********************************************************");
        logger.info("Rename sensors from Panel");
        s.navigate_to_Advanced_Settings_page();
        adv.INSTALLATION.click();
        inst.DEVICES.click();
        devices.Security_Sensors.click();
        sec_sensors.Edit_Sensor.click();
        TimeUnit.SECONDS.sleep(3);
        logger.info("Renaming door_window sensor, new name: "+myADC.new_dw_name);
        s.Rename_Sensor(0, myADC.new_dw_name);
        logger.info("Renaming motion sensor, new name: "+ myADC.new_motion_name);
        s.Rename_Sensor(1, myADC.new_motion_name);
        logger.info("Renaming smoke detector, new name: "+ myADC.new_smoke_name);
        s.Rename_Sensor(2, myADC.new_smoke_name);
        logger.info("Renaming co_detector, new name: "+ myADC.new_co_name);
        s.Rename_Sensor(3, myADC.new_co_name);
        swipingVertical();
        TimeUnit.SECONDS.sleep(10);
        logger.info("Renaming glassbreak sensor, new name: "+ myADC.new_glassbreak_name);
        s.Rename_Sensor(0, myADC.new_glassbreak_name);
        logger.info("Renaming tilt sensor, new name: "+ myADC.new_tilt_name);
        s.Rename_Sensor(1, myADC.new_tilt_name);
        logger.info("Renaming shock_other sensor, new name: "+ myADC.new_shock_other_name);
        s.Rename_Sensor(2, myADC.new_shock_other_name);
        logger.info("Renaming freeze sensor, new name: "+ myADC.new_freeze_name);
        s.Rename_Sensor(3, myADC.new_freeze_name);
        swipingVertical();
        TimeUnit.SECONDS.sleep(10);
        logger.info("Renaming heat sensor, new name: "+ myADC.new_heat_name);
        s.Rename_Sensor(0, myADC.new_heat_name);
        logger.info("Renaming water_flood sensor, new name: "+ myADC.new_water_flood_name);
        s.Rename_Sensor(1, myADC.new_water_flood_name);
        logger.info("Renaming keyfob, new name: "+ myADC.new_keyfob_name);
        s.Rename_Sensor(2, myADC.new_keyfob_name);
        logger.info("Renaming keypad, new name: "+ myADC.new_keypad_name);
        s.Rename_Sensor(3, myADC.new_keypad_name);
        swipingVertical();
        TimeUnit.SECONDS.sleep(10);
        logger.info("Renaming auxiliary pendant, new name: "+ myADC.new_med_pendant_name);
        s.Rename_Sensor(1, myADC.new_med_pendant_name);
        logger.info("Renaming doorbell sensor, new name: "+ myADC.new_doorbell_name);
        s.Rename_Sensor(2, myADC.new_doorbell_name);
        logger.info("Renaming occupancy sensor, new name: "+ myADC.new_occupancy_name);
        s.Rename_Sensor(3, myADC.new_occupancy_name);
        logger.info("Renaming iq shock sensor, new name: "+ myADC.new_iq_shock_name);
        s.Rename_Sensor(4, myADC.new_iq_shock_name);
        logger.info("Sensors renamed successfully");
    }
    @Test
    public void Test4() throws InterruptedException {
        logger.info("********************************************************");
        logger.info("Verify new sensor name, group, type are displayed correctly on the dealer website");
        myADC.New_ADC_session();
        TimeUnit.SECONDS.sleep(5);
        s.getDriver1().findElement(By.partialLinkText("Sensors")).click();
        TimeUnit.SECONDS.sleep(3);
        s.getDriver1().findElement(By.xpath("//input[@value='Request Sensor Names']")).click();
        TimeUnit.SECONDS.sleep(2);
        Alert alert = s.getDriver1().switchTo().alert();
        s.getDriver1().switchTo().alert();
        alert.accept();
        TimeUnit.SECONDS.sleep(5);
        s.getDriver1().findElement(By.id("ctl00_refresh_sensors_button_btnRefreshPage")).click();
        TimeUnit.SECONDS.sleep(10);
        myADC.Request_equipment_list();
        myADC.Sensor_verification(myADC.new_dw_name, "10", "Door/Window", 2);
        myADC.Sensor_verification(myADC.new_motion_name, "17", "Motion", 3);
        myADC.Sensor_verification(myADC.new_smoke_name, "26", "Smoke Detector", 4);
        myADC.Sensor_verification(myADC.new_co_name, "34", "CO Detector", 5);
        myADC.Sensor_verification(myADC.new_glassbreak_name, "13", "Glass Break", 6);
        myADC.Sensor_verification(myADC.new_tilt_name, "12", "Tilt", 7);
        myADC.Sensor_verification(myADC.new_shock_other_name, "13", "Shock: Others", 8);
        myADC.Sensor_verification(myADC.new_freeze_name, "52", "Freeze", 9);
        myADC.Sensor_verification(myADC.new_heat_name, "26", "IQ Smoke: Multi-function", 10);
        myADC.Sensor_verification(myADC.new_water_flood_name, "38", "Water: Multi-Function", 11);
        myADC.Sensor_verification(myADC.new_med_pendant_name, "6", "Medical Pendant", 12);
        myADC.Sensor_verification(myADC.new_doorbell_name, "25", "Door Bell", 13);
        myADC.Sensor_verification(myADC.new_occupancy_name, "25", "Occupancy", 14);
        myADC.Sensor_verification(myADC.new_iq_shock_name, "13", "IQ Shock", 15);
    }
    @AfterClass
    public void tearDown() {
        s.getDriver1().quit();
    }
}
