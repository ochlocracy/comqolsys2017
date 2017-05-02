//package ADC;
//
//import Panel.Setup1;
//import jxl.read.biff.BiffException;
//import org.apache.log4j.Logger;
//import org.openqa.selenium.Alert;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.testng.annotations.AfterClass;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Parameters;
//import org.testng.annotations.Test;
//
//import java.io.IOException;
//import java.util.concurrent.TimeUnit;
//
//public class WebSite_Grid_Status_Change {
//    Setup1 s = new Setup1();
//    ADC myADC = new ADC();
//    String page_name = "Checking status change on the website";
//    Logger logger = Logger.getLogger(page_name);
//    String open = "06";
//    String activate = "02";
//    WebDriver driver1;
//
//
//    public WebSite_Grid_Status_Change() throws IOException, BiffException {
//    }
//    @Parameters({"deviceName_", "applicationName_", "UDID_", "platformVersion_", "URL_", "PORT_" })
//    @BeforeClass
//    public void setUp(String deviceName_, String applicationName_, String UDID_, String platformVersion_, String URL_, String PORT_) throws Exception {
//        s.setCapabilities(URL_);
//        s.setup_logger(page_name);
//    }
//    @Parameters({"UDID_" })
//    @Test
//    public void Test6(String UDID_) throws IOException, InterruptedException {
//        logger.info("********************************************************");
//        logger.info("In Disarm state open/activate sensors");
//        myADC.send_event_to_sensor_grid(UDID_, myADC.door_window_DLID, open);
//        TimeUnit.SECONDS.sleep(3);
//        myADC.send_event_to_sensor_grid(UDID_, myADC.motion_DLID, activate);
//        TimeUnit.SECONDS.sleep(3);
//        myADC.send_event_to_sensor_grid(UDID_, myADC.glassbreak_DLID, activate);
//        TimeUnit.SECONDS.sleep(3);
//        myADC.send_event_to_sensor_grid(UDID_, myADC.shock_other_DLID, activate);
//        TimeUnit.SECONDS.sleep(3);
//        myADC.send_event_to_sensor_grid(UDID_, myADC.tilt_DLID, open);
//        TimeUnit.SECONDS.sleep(3);
//        myADC.send_event_to_sensor_grid(UDID_, myADC.doorbell_DLID, open);
//        TimeUnit.SECONDS.sleep(3);
//        myADC.send_event_to_sensor_grid(UDID_, myADC.occupancy_DLID, open);
//        TimeUnit.SECONDS.sleep(3);
//    }
//
//    @Test
//    public void Test7() throws InterruptedException {
//        logger.info("********************************************************");
//        logger.info("Verify new sensor name, group, type, reporting type, status are displayed correctly on the dealer website");
//        myADC.New_ADC_session();
//        TimeUnit.SECONDS.sleep(3);
//        myADC.driver1.findElement(By.partialLinkText("Sensors")).click();
//        myADC.driver1.findElement(By.xpath("//input[@value='Request Sensor Names']")).click();
//        Alert alert = myADC.driver1.switchTo().alert();
//        myADC.driver1.switchTo().alert();
//        alert.accept();
//        TimeUnit.SECONDS.sleep(5);
//        myADC.driver1.findElement(By.id("ctl00_refresh_sensors_button_btnRefreshPage")).click();
//        TimeUnit.SECONDS.sleep(10);
//        myADC.Request_equipment_list();
//        myADC.Sensor_verification_full(myADC.new_dw_name, "10", "Door/Window", "Entry/Exit", "Open",2);
//        myADC.Sensor_verification_full(myADC.new_motion_name, "17", "Motion", "Interior", "Activated", 3);
//        myADC.Sensor_verification_full(myADC.new_smoke_name, "26", "Smoke Detector", "Fire", "OK", 4);
//        myADC.Sensor_verification_full(myADC.new_co_name, "34", "CO Detector", "Carbon Monoxide", "OK", 5);
//        myADC.Sensor_verification_full(myADC.new_glassbreak_name, "13", "Glass Break", "Perimeter", "OK", 6);
//        myADC.Sensor_verification_full(myADC.new_tilt_name, "12", "Tilt", "Entry/Exit", "Open", 7);
//        myADC.Sensor_verification_full(myADC.new_shock_other_name, "13", "Shock: Others", "Perimeter", "Closed", 8);
//        myADC.Sensor_verification_full(myADC.new_freeze_name, "52", "Freeze", "Low temperature", "OK", 9);
//        myADC.Sensor_verification_full(myADC.new_heat_name, "26", "IQ Smoke: Multi-function", "Fire", "OK", 10);
//        myADC.Sensor_verification_full(myADC.new_water_flood_name, "38", "Water: Multi-Function", "Water", "OK",11);
//        myADC.Sensor_verification_full(myADC.new_med_pendant_name, "6", "Medical Pendant", "Emergency/Medical", "OK",12);
//        myADC.Sensor_verification_full(myADC.new_doorbell_name, "25", "Door Bell", "Non-Reporting", "Open", 13);
//        myADC.Sensor_verification_full(myADC.new_occupancy_name, "25", "Occupancy", "Non-Reporting", "Vacant",14);
//    }
//    @Test
//    public void Test8() throws InterruptedException {
//        driver1 = myADC.getDriver1();
//        logger.info("********************************************************");
//        logger.info("Renaming sensors from the ADC dealer website");
//        myADC.New_ADC_session();
//        TimeUnit.SECONDS.sleep(3);
//        driver1.findElement(By.partialLinkText("Sensors")).click();
//        TimeUnit.SECONDS.sleep(3);
//        driver1.findElement(By.partialLinkText("Change Sensor Names")).click();
//        TimeUnit.SECONDS.sleep(3);
//        WebElement name_field1 = myADC.driver1.findElement(By.name("ctl00$phBody$dgDevices$ctl02$UcEligibleSensorName1$txtSN"));
//        name_field1.sendKeys("WebName_DoorWindow1");
//        TimeUnit.SECONDS.sleep(2);
//        WebElement name_field2 = myADC.driver1.findElement(By.name("ctl00$phBody$dgDevices$ctl03$UcEligibleSensorName1$txtSN"));
//        name_field2.sendKeys("WebName_Motion1");
//        TimeUnit.SECONDS.sleep(2);
//        WebElement name_field3 = myADC.driver1.findElement(By.name("ctl00$phBody$dgDevices$ctl04$UcEligibleSensorName1$txtSN"));
//        name_field3.sendKeys("WebName_Smoke1");
//        TimeUnit.SECONDS.sleep(2);
//        WebElement name_field4 = myADC.driver1.findElement(By.name("ctl00$phBody$dgDevices$ctl05$UcEligibleSensorName1$txtSN"));
//        name_field4.sendKeys("WebName_CODetector1");
//        TimeUnit.SECONDS.sleep(2);
//        WebElement name_field5 = myADC.driver1.findElement(By.name("ctl00$phBody$dgDevices$ctl06$UcEligibleSensorName1$txtSN"));
//        name_field5.sendKeys("WebName_Glassbreak1");
//        TimeUnit.SECONDS.sleep(2);
//        WebElement name_field6 = myADC.driver1.findElement(By.name("ctl00$phBody$dgDevices$ctl07$UcEligibleSensorName1$txtSN"));
//        name_field6.sendKeys("WebName_Tilt1");
//        TimeUnit.SECONDS.sleep(2);
//        WebElement name_field7 = myADC.driver1.findElement(By.name("ctl00$phBody$dgDevices$ctl08$UcEligibleSensorName1$txtSN"));
//        name_field7.sendKeys("WebName_OtherShock1");
//        TimeUnit.SECONDS.sleep(2);
//        WebElement name_field8 = myADC.driver1.findElement(By.name("ctl00$phBody$dgDevices$ctl09$UcEligibleSensorName1$txtSN"));
//        name_field8.sendKeys("WebName_Freeze1");
//        TimeUnit.SECONDS.sleep(2);
//        WebElement name_field9 = myADC.driver1.findElement(By.name("ctl00$phBody$dgDevices$ctl10$UcEligibleSensorName1$txtSN"));
//        name_field9.sendKeys("WebName_Heat1");
//        TimeUnit.SECONDS.sleep(2);
//        WebElement name_field10 = myADC.driver1.findElement(By.name("ctl00$phBody$dgDevices$ctl11$UcEligibleSensorName1$txtSN"));
//        name_field10.sendKeys("WebName_Water1");
//        TimeUnit.SECONDS.sleep(2);
//        WebElement name_field11 = myADC.driver1.findElement(By.name("ctl00$phBody$dgDevices$ctl12$UcEligibleSensorName1$txtSN"));
//        name_field11.sendKeys("WebName_Pendant1");
//        TimeUnit.SECONDS.sleep(2);
//        WebElement name_field12 = myADC.driver1.findElement(By.name("ctl00$phBody$dgDevices$ctl13$UcEligibleSensorName1$txtSN"));
//        name_field12.sendKeys("WebName_DoorBell1");
//        TimeUnit.SECONDS.sleep(2);
//        WebElement name_field13 = myADC.driver1.findElement(By.name("ctl00$phBody$dgDevices$ctl14$UcEligibleSensorName1$txtSN"));
//        name_field13.sendKeys("WebName_Occupancy1");
//        WebElement name_field14 = myADC.driver1.findElement(By.name("ctl00$phBody$dgDevices$ctl15$UcEligibleSensorName1$txtSN"));
//        name_field14.sendKeys("WebName_IQShock1");
//        TimeUnit.SECONDS.sleep(3);
//        driver1.findElement(By.id("ctl00_phBody_btnSubmit")).click();
//        Alert alert = myADC.driver1.switchTo().alert();
//        driver1.switchTo().alert();
//        alert.accept();
//        TimeUnit.SECONDS.sleep(10);
//    }
//    @AfterClass
//    public void tearDown() {
//        driver1.quit();
//    }
//}
