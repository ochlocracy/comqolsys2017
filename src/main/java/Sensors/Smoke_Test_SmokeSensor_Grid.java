package Sensors;

import Panel.Contact_Us;
import Panel.Emergency_Page;
import Panel.Home_Page;
import Panel.Setup1;
import jxl.read.biff.BiffException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Smoke_Test_SmokeSensor_Grid {
    Setup1 s = new Setup1();
    String page_name = "Smoke Test Glassbreak";
    Logger logger = Logger.getLogger(page_name);
    Sensors MySensors = new Sensors();
    private int Long_Exit_Delay =17;
    int Idle = 0;
    int Alarm = 1;

    public Smoke_Test_SmokeSensor_Grid() throws IOException, BiffException {}
    @Parameters({"deviceName_", "applicationName_", "UDID_", "platformVersion_", "URL_", "PORT_" })
    @BeforeClass
    public void setUp(String deviceName_, String applicationName_, String UDID_, String platformVersion_, String URL_, String PORT_) throws Exception {
        s.setCapabilities(URL_);
        s.setup_logger(page_name, UDID_);
    }
    @Parameters ({"UDID_"})
    @Test
    public void Test1(String UDID_) throws Exception {
        Home_Page home_page = PageFactory.initElements(s.getDriver(), Home_Page.class);
        Contact_Us contact_us = PageFactory.initElements(s.getDriver(), Contact_Us.class);
        Emergency_Page emergency = PageFactory.initElements(s.getDriver(), Emergency_Page.class);
        SensorsList list = PageFactory.initElements(s.getDriver(), SensorsList.class);

        logger.info("Current software version: " + s.Software_Version());
        MySensors.read_sensors_from_csv();
        logger.info("Adding sensors...");
        MySensors.addAllSensors1(UDID_);
        TimeUnit.SECONDS.sleep(5);
        s.autoStaySetting();
        TimeUnit.SECONDS.sleep(2);

        logger.info("Disarm activate Smoke Sensor -> Expected result = Instant Alarm");
        MySensors.sendPacket_allSensors_selectedGroup(MySensors.smoke_detector_zones, 26,Alarm);
        TimeUnit.SECONDS.sleep(5);
        MySensors.sendPacket_allSensors_selectedGroup(MySensors.smoke_detector_zones, 26,Idle);
        if (emergency.Emergency_sent_text.getText().equals("Fire Emergency Sent")){
            logger.info("Pass: Fire emergency is sent in Disarm mode");
        }
        else { s.take_screenshot();
            logger.info("Failed: Fire emergency is NOT sent in Disarm mode");
        }
        TimeUnit.SECONDS.sleep(2);
        emergency.Cancel_Emergency.click();
        s.enter_default_user_code();
        TimeUnit.SECONDS.sleep(5);

        logger.info("********************************************************");
        logger.info("ArmStay activate Smoke Sensor -> Expected result = Instant Alarm");
        s.ARM_STAY();
        TimeUnit.SECONDS.sleep(5);
        MySensors.sendPacket_allSensors_selectedGroup(MySensors.smoke_detector_zones, 26,Alarm);
        TimeUnit.SECONDS.sleep(5);
        MySensors.sendPacket_allSensors_selectedGroup(MySensors.smoke_detector_zones, 26,Idle);
        if (emergency.Emergency_sent_text.getText().equals("Fire Emergency Sent")){
            logger.info("Pass: Fire emergency is sent in ArmStay mode");
        }
        else { s.take_screenshot();
            logger.info("Failed: Fire emergency is NOT sent in ArmStay mode");
        }
        TimeUnit.SECONDS.sleep(2);
        emergency.Cancel_Emergency.click();
        s.enter_default_user_code();
        TimeUnit.SECONDS.sleep(5);

        logger.info("********************************************************");
        logger.info("ArmAway activate Smoke Sensor -> Expected result = Instant Alarm");
        s.ARM_AWAY(Long_Exit_Delay);
        TimeUnit.SECONDS.sleep(2);
        MySensors.sendPacket_allSensors_selectedGroup(MySensors.smoke_detector_zones, 26,Alarm);
        TimeUnit.SECONDS.sleep(5);
        MySensors.sendPacket_allSensors_selectedGroup(MySensors.smoke_detector_zones, 26,Idle);
        if (emergency.Emergency_sent_text.getText().equals("Fire Emergency Sent")){
            logger.info("Pass: Fire emergency is sent in ArmAway mode");
        }
        else { s.take_screenshot();
            logger.info("Failed: Fire emergency is NOT sent in ArmAway mode");
        }
        TimeUnit.SECONDS.sleep(2);
        emergency.Cancel_Emergency.click();
        s.enter_default_user_code();
        TimeUnit.SECONDS.sleep(5);

        logger.info("**********************TAMPER**********************");
        logger.info("Disarm state tamper Smoke Sensor -> Expected result = Disarm");
        MySensors.sendTamper_allSensors_selectedGroup(MySensors.smoke_detector_zones, 26);
        TimeUnit.SECONDS.sleep(5);
        s.verify_sensor_is_tampered(list.Smoke15);
        s.verify_disarm(UDID_);
        TimeUnit.SECONDS.sleep(3);
        MySensors.sendPacket_allSensors_selectedGroup(MySensors.smoke_detector_zones, 26,Idle);
        TimeUnit.SECONDS.sleep(5);

        logger.info("********************************************************");
        logger.info("ArmStay state tamper Smoke Sensor -> Expected result = ArmStay");
        s.ARM_STAY();
        TimeUnit.SECONDS.sleep(5);
        MySensors.sendTamper_allSensors_selectedGroup(MySensors.smoke_detector_zones, 26);
        TimeUnit.SECONDS.sleep(5);
        s.verify_sensor_is_tampered(list.Smoke15);
        s.verify_armstay(UDID_);
        TimeUnit.SECONDS.sleep(3);
        MySensors.sendPacket_allSensors_selectedGroup(MySensors.smoke_detector_zones, 26,Idle);
        home_page.DISARM.click();
        s.enter_default_user_code();
        TimeUnit.SECONDS.sleep(5);

        logger.info("********************************************************");
        logger.info("ArmAway state tamper Smoke Sensor -> Expected result = Instant Alarm ");
        s.ARM_AWAY(Long_Exit_Delay);
        MySensors.sendTamper_allSensors_selectedGroup(MySensors.smoke_detector_zones, 26);
        TimeUnit.SECONDS.sleep(5);
        s.verify_sensor_is_tampered(list.Smoke15);
        s.verify_status_tampered();
        s.verify_in_alarm();
        TimeUnit.SECONDS.sleep(3);
        MySensors.sendPacket_allSensors_selectedGroup(MySensors.smoke_detector_zones, 26,Idle);
        s.enter_default_user_code();
        TimeUnit.SECONDS.sleep(5);

        s.autoStaySetting();
        TimeUnit.SECONDS.sleep(2);
        contact_us.acknowledge_all_alerts();
        logger.info("Deleting all sensors");
        MySensors.deleteAllSensors1(UDID_);
        TimeUnit.SECONDS.sleep(3);
    }
    @AfterClass
    public void tearDown () throws IOException, InterruptedException {
        s.log.endTestCase(page_name);
        s.getDriver().quit();
    }
}