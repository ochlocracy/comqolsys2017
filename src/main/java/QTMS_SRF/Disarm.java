package QTMS_SRF;

import ADC.ADC;
import Panel.*;
import Sensors.Sensors;
import jxl.read.biff.BiffException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class Disarm extends Setup {

    public Disarm() throws IOException, BiffException { }

    private int Normal_Exit_Delay = 10;
    private int Normal_Entry_Delay = 11;
    private int Long_Exit_Delay = 12;
    private int Long_Entry_Delay = 13;
    private String open = "06 00";
    private String close = "04 00";
    private String tamper = "01 01";
    String door_window10 = "65 00 0A";
    String door_window12 = "65 00 1A";
    String door_window13 = "65 00 2A";
    String door_window14 = "65 00 3A";
    String door_window16 = "65 00 4A";
    String door_window25 = "65 00 5A";
    String door_window8 = "65 00 6A";
    String door_window9 = "65 00 7A";
    String motion15 = "55 00 44";
    String motion17 = "55 00 54";
    String motion20 = "55 00 64";
    String motion35 = "55 00 74";
    String keyfob1 = "65 00 AF";
    String keyfob2 = "65 00 BF";
    String keyfob3 = "65 00 CF";
    String newName = "NewSensorName";

    String page_name = "SRF Disarm";
    Logger logger = Logger.getLogger(page_name);
    Sensors sensors = new Sensors();
    ADC adc = new ADC();
    PanelInfo_ServiceCalls servcall = new PanelInfo_ServiceCalls();
    String AccountID = adc.getAccountId();

    public void navigate_to_Security_Sensors_page() {
        Advanced_Settings_Page adv = PageFactory.initElements(driver, Advanced_Settings_Page.class);
        Installation_Page inst = PageFactory.initElements(driver, Installation_Page.class);
        Devices_Page dev = PageFactory.initElements(driver, Devices_Page.class);
        navigate_to_Advanced_Settings_page();
        adv.INSTALLATION.click();
        inst.DEVICES.click();
        dev.Security_Sensors.click();
    }

    /*** If you want to run tests only on the panel, please set ADCexecute value to false ***/
    String ADCexecute = "true";
    public void add_primary_call(int zone, int group, int sensor_dec, int sensor_type) throws IOException {
        String add_primary = " shell service call qservice 50 i32 " + zone + " i32 " + group + " i32 " + sensor_dec + " i32 " + sensor_type;
        rt.exec(adbPath + add_primary);
        // shell service call qservice 50 i32 2 i32 10 i32 6619296 i32 1
    }
    public void delete_from_primary(int zone) throws IOException, InterruptedException {
        String deleteFromPrimary = " shell service call qservice 51 i32 " + zone;
        rt.exec(adbPath + deleteFromPrimary);
        System.out.println(deleteFromPrimary);}
    public void ADC_verification (String string) throws IOException, InterruptedException {
        String[] message = {string};

        if (ADCexecute.equals("true")) {
        //    adc.New_ADC_session(AccountID);
            adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("History"))).click();
            Thread.sleep(10000);
            for (int i =0; i< message.length; i++) {
                try {
                    WebElement history_message = adc.driver1.findElement(By.xpath(message[i]));
                    Assert.assertTrue(history_message.isDisplayed());
                    {
                        System.out.println("Pass: message is displayed " + history_message.getText());
                    }
                } catch (Exception e) {
                    System.out.println("***No such element found!***");
                }
            }
        }else{
            System.out.println("Set execute to TRUE to run ADC verification part");
        }
        Thread.sleep(2000);
    }

    @BeforeTest
    public void capabilities_setup() throws Exception {
        setup_driver(get_UDID(),"http://127.0.1.1", "4723");
        setup_logger(page_name);
        servcall.set_NORMAL_ENTRY_DELAY(Normal_Exit_Delay);
        Thread.sleep(1000);
        servcall.set_NORMAL_EXIT_DELAY(Normal_Entry_Delay);
        Thread.sleep(1000);
        servcall.set_LONG_ENTRY_DELAY(Long_Exit_Delay);
        Thread.sleep(1000);
        servcall.set_LONG_EXIT_DELAY(Long_Entry_Delay);

        servcall.set_AUTO_STAY(0);
        servcall.set_ARM_STAY_NO_DELAY_disable();
    }
    @BeforeMethod
    public  void webDriver(){
        adc.webDriverSetUp();
    }

    public void sensor_status_check( String DLID, String Status, String Status2 ) throws InterruptedException, IOException {
        Home_Page home = PageFactory.initElements(driver, Home_Page.class);
        Settings_Page sett = PageFactory.initElements(driver, Settings_Page.class);
        navigate_to_Settings_page();
        sett.STATUS.click();
        sensors.primary_call(DLID, open);
        List<WebElement> li_status1 = driver.findElements(By.id("com.qolsys:id/textView3"));
        if (li_status1.get(1).getText().equals(Status)){
            logger.info("Pass: sensor status is displayed correctly: ***" + li_status1.get(1).getText()+ "***");
        }else {logger.info("Failed: sensor status is displayed in correct: ***" + li_status1.get(1).getText()+ "***");}
        Thread.sleep(1000);
        li_status1.clear();
        sensors.primary_call(DLID, close);
        Thread.sleep(1000);
        List<WebElement> li_status2 = driver.findElements(By.id("com.qolsys:id/textView3"));
        if (li_status2.get(1).getText().equals(Status2)){
            logger.info("Pass: sensor status is displayed correctly: ***" + li_status2.get(1).getText()+ "***");
        }else {logger.info("Failed: sensor status is displayed in correct: ***" + li_status2.get(1).getText()+ "***");}
        Thread.sleep(1000);
        home.Home_button.click();
    }
    @Test
    public void Disb_01_DW10() throws IOException, InterruptedException {
        logger.info("*Disb_01* Open/Close event is displayed in panel history for sensor group 10");
        add_primary_call(1, 10, 6619296, 1);
        Thread.sleep(1000);
        sensor_status_check(door_window10, "Open", "Closed");
        delete_from_primary(1);
        Thread.sleep(1000);
    }
    @Test (priority = 1)
    public void Disb_02_DW12() throws IOException, InterruptedException {
        logger.info("*Disb_02* Open/Close event is displayed in panel history for sensor group 12");
        add_primary_call(1, 12, 6619297, 1);
        Thread.sleep(1000);
        sensor_status_check(door_window12, "Open", "Closed");
        delete_from_primary(1);
        Thread.sleep(1000);
    }
    @Test (priority = 2)
    public void Disb_03_DW13() throws IOException, InterruptedException {
        logger.info("*Disb_03* Open/Close event is displayed in panel history for sensor group 13");
        add_primary_call(1, 13, 6619298, 1);
        Thread.sleep(1000);
        sensor_status_check(door_window13, "Open", "Closed");
        delete_from_primary(1);
        Thread.sleep(1000);
    }
    @Test (priority = 3)
    public void Disb_04_DW14() throws IOException, InterruptedException {
        logger.info("*Disb_04* Open/Close event is displayed in panel history for sensor group 14");
        add_primary_call(1, 14, 6619299, 1);
        Thread.sleep(1000);
        sensor_status_check(door_window14, "Open", "Closed");
        delete_from_primary(1);
        Thread.sleep(1000);
    }
    @Test (priority = 4)
    public void Disb_05_DW16() throws IOException, InterruptedException {
        logger.info("*Disb_05* Open/Close event is displayed in panel history for sensor group 14");
        add_primary_call(1, 16, 6619300, 1);
        Thread.sleep(1000);
        sensor_status_check(door_window16, "Open", "Closed");
        delete_from_primary(1);
        Thread.sleep(1000);
    }
    @Test (priority = 5)
    public void Disb_06_M15() throws IOException, InterruptedException {
        logger.info("*Disb_06* Activate event is displayed in panel history for motion sensor group 15");
        add_primary_call(1, 15, 5570628, 2);
        Thread.sleep(1000);
        sensor_status_check(motion15, "Activated", "Idle");
        delete_from_primary(1);
        Thread.sleep(1000);
    }
    @Test (priority = 6)
    public void Disb_07_M17() throws IOException, InterruptedException {
        logger.info("*Disb_07* Activate event is displayed in panel history for motion sensor group 17");
        add_primary_call(1, 17, 5570629, 2);
        Thread.sleep(1000);
        sensor_status_check(motion17, "Activated", "Idle");
        delete_from_primary(1);
        Thread.sleep(1000);
    }
    @Test (priority = 7)
    public void Disb_08_M20() throws IOException, InterruptedException {
        logger.info("*Disb_08* Activate event is displayed in panel history for motion sensor group 20");
        add_primary_call(1, 20, 5570630, 2);
        Thread.sleep(1000);
        sensor_status_check(motion20, "Activated", "Idle");
        delete_from_primary(1);
        Thread.sleep(1000);
    }
    @Test (priority = 8)
    public void Disb_09_M35() throws IOException, InterruptedException {
        logger.info("*Disb_09* Activate event is displayed in panel history for motion sensor group 35");
        add_primary_call(1, 35, 5570631, 2);
        Thread.sleep(1000);
        sensor_status_check(motion35, "Activated", "Idle");
        delete_from_primary(1);
        Thread.sleep(1000);
    }
    @Test (priority = 9)
    public void Disb_19_DW25() throws IOException, InterruptedException {
        logger.info("*Disb_19* Open/Close event is displayed in panel history for sensor group 25");
        add_primary_call(1, 25, 6619301, 1);
        Thread.sleep(1000);
        sensor_status_check(door_window25, "Open", "Closed");
        delete_from_primary(1);
        Thread.sleep(1000);
    }
    @Test (priority = 10)
    public void Disb_21_DW8() throws Exception {
        Settings_Page sett = PageFactory.initElements(driver, Settings_Page.class);
        Home_Page home = PageFactory.initElements(driver, Home_Page.class);
        logger.info("*Disb_21* Open/Close event is displayed in panel history for sensor group 8");
        add_primary_call(1, 8, 6619302, 1);
        Thread.sleep(1000);
        sensors.primary_call(door_window8, open);
        Thread.sleep(1000);
        verify_in_alarm();
        enter_default_user_code();
        sensors.primary_call(door_window8, close);
        navigate_to_Settings_page();
        sett.STATUS.click();
        List<WebElement> li_status1 = driver.findElements(By.id("com.qolsys:id/textView3"));
        if (li_status1.get(1).getText().equals("Closed")){
            logger.info("Pass: sensor status is displayed correctly: ***" + li_status1.get(1).getText()+ "***");
        }else {logger.info("Failed: sensor status is displayed in correct: ***" + li_status1.get(1).getText()+ "***");}
        Thread.sleep(1000);
        home.Home_button.click();
        delete_from_primary(1);
        Thread.sleep(1000);
    }
//    @Test(priority = 11)
//    public void Disb_22_DW8() throws Exception {
//        logger.info("*Disb_21* system will disarm from Police Alarm from the User Site, dw 8");
//        add_primary_call(1, 8, 6619302, 1);
//        Thread.sleep(1000);
//        sensors.primary_call(door_window8, open);
//        Thread.sleep(35000);
//        adc.New_ADC_session_User("mypanel01", "qolsys123");
//        adc.driver1.navigate().refresh();
//        try{
//        //    driver1.findElement()
//        }catch ( org.openqa.selenium.NoSuchElementException e){}
//        //finish here!!!
//        delete_from_primary(1);
//        Thread.sleep(5000);
//    }
    @Test (priority = 12)
    public void Disb_23_DW9() throws Exception {
        Settings_Page sett = PageFactory.initElements(driver, Settings_Page.class);
        logger.info("*Disb_23* Open/Close event is displayed in panel history for sensor group 9, system goes into alarm at the end of entry delay");
        add_primary_call(1, 9, 6619303, 1);
        navigate_to_Settings_page();
        sett.STATUS.click();
        sensors.primary_call(door_window9, open);
        TimeUnit.SECONDS.sleep(Long_Entry_Delay);
        Thread.sleep(2);
        sensors.primary_call(door_window9, close);
        verify_in_alarm();
        enter_default_user_code();
        delete_from_primary(1);
        Thread.sleep(1000);
    }
    @Test (priority = 13)
    public void Disb_24_DW8() throws Exception {
        logger.info("*Disb_24* system goes into alarm after sensor tamper, dw8");
        add_primary_call(1, 8, 6619302, 1);
        Thread.sleep(2000);
        sensors.primary_call(door_window8, tamper);
        Thread.sleep(4000);
        sensors.primary_call(door_window8, close);
        verify_in_alarm();
        enter_default_user_code();
        delete_from_primary(1);
        Thread.sleep(1000);
    }
    @Test (priority = 14)
    public void Disb_25_DW9() throws Exception {
        logger.info("*Disb_25* system goes into alarm after sensor tamper, dw9");
        add_primary_call(1, 9, 6619303, 1);
        Thread.sleep(2000);
        sensors.primary_call(door_window9, tamper);
        Thread.sleep(4000);
        sensors.primary_call(door_window9, close);
        verify_in_alarm();
        enter_default_user_code();
        delete_from_primary(1);
        Thread.sleep(1000);
    }
    @Test (priority = 15)
    public void Disb_26_DW10() throws Exception {
        Security_Sensors_Page sen = PageFactory.initElements(driver, Security_Sensors_Page.class);
        Home_Page home = PageFactory.initElements(driver, Home_Page.class);
        logger.info("*Disb_26* sensor name can be edited and changes will be reflected on the panel and website");
        add_primary_call(1, 10, 6619296, 1);
        navigate_to_Security_Sensors_page();
        sen.Edit_Sensor.click();
        sen.Edit_Img.click();
        sen.Custom_Description.clear();
        sen.Custom_Description.sendKeys(newName);
        try{
            driver.hideKeyboard();
        } catch (Exception e) {}
        sen.Save.click();
        home.Home_button.click();
        Thread.sleep(2000);
        home.All_Tab.click();
        Thread.sleep(2000);
        logger.info("Verify new name is displayed");
        WebElement newSensorName = driver.findElement(By.xpath("//android.widget.TextView[@text='"+newName+"']"));
        Assert.assertTrue(newSensorName.isDisplayed());
        Thread.sleep(1000);

        adc.New_ADC_session(AccountID);
        Thread.sleep(10000);
        adc.driver1.findElement(By.partialLinkText("Sensors")).click();
        Thread.sleep(2000);
        adc.Request_equipment_list();
        Thread.sleep(2000);

        WebElement webname = adc.driver1.findElement(By.xpath("/html/body/form/table/tbody/tr/td[2]/div/div[2]/div[7]/div[2]/div[2]/table/tbody/tr[2]/td[2]"));
        Assert.assertTrue(webname.getText().equals(newName));
        logger.info("Pass: The name is displayed correctly " + webname.getText());
    }
    @Test (dependsOnMethods = {"Disb_26_DW10"}, priority = 16)
    public void Disb_27_DW10() throws Exception {
        Security_Sensors_Page sen = PageFactory.initElements(driver, Security_Sensors_Page.class);
        Home_Page home = PageFactory.initElements(driver, Home_Page.class);
        logger.info("*Disb_27* sensor can be deleted, change is reflected on the website");
        navigate_to_Security_Sensors_page();
        sen.Delete_Sensor.click();
        driver.findElement(By.id("com.qolsys:id/checkBox1")).click();
        sen.Delete.click();
        sen.OK.click();
        home.Home_button.click();
        Thread.sleep(2000);
        home.All_Tab.click();
        Thread.sleep(2000);
        try{
            WebElement newSensorName = driver.findElement(By.xpath("//android.widget.TextView[@text='"+newName+"']"));
            Assert.assertTrue(newSensorName.isDisplayed());
            logger.info("Failed:");
        } catch (Exception e) {}
        logger.info("Sensor is deleted successfully");
        Thread.sleep(1000);
    }
    @Test(priority = 17)
    public void Disb_28_DW10() throws Exception {
        Security_Sensors_Page sen = PageFactory.initElements(driver, Security_Sensors_Page.class);
        Home_Page home = PageFactory.initElements(driver, Home_Page.class);
        logger.info("*Disb_28* readd same sensor from panel");
        navigate_to_Security_Sensors_page();
        sen.Add_Sensor.click();
        logger.info("Adding sensor with DLID 1234A5");
        sen.Sensor_DLID.sendKeys("1234A5");
        try{
        driver.hideKeyboard();}
        catch (WebDriverException e) {}
        sen.Save.click();
        Thread.sleep(1000);
        home.Back_button.click();
        Thread.sleep(1000);
        sen.Edit_Sensor.click();
        try {
            WebElement sensor = driver.findElement(By.id("com.qolsys:id/textView2"));
            Assert.assertTrue(sensor.getText().equals("1234A5"));
            logger.info("Pass: sensor is displayed");
        }catch (NoSuchElementException e){}
        home.Back_button.click();
        sen.Delete_Sensor.click();
        logger.info("Deleting sensor with DLID 1234A5");
        driver.findElement(By.id("com.qolsys:id/checkBox1")).click();
        sen.Delete.click();
        sen.OK.click();
        Thread.sleep(2000);
        home.Back_button.click();
        logger.info("Readding sensor with DLID 1234A5");
        sen.Add_Sensor.click();
        sen.Sensor_DLID.sendKeys("1234A5");
        try{
            driver.hideKeyboard();}
        catch (WebDriverException e) {}
        sen.Save.click();
        Thread.sleep(1000);
        home.Back_button.click();
        sen.Edit_Sensor.click();
        try {
            WebElement sensor = driver.findElement(By.id("com.qolsys:id/textView2"));
            Assert.assertTrue(sensor.getText().equals("1234A5"));
            logger.info("Pass: sensor is displayed");
        }catch (NoSuchElementException e){}
        Thread.sleep(1000);
        delete_from_primary(1);
    }
    @Test (priority = 18)
    public void Disb_33_DW10() throws Exception {
        logger.info("*Disb-33* System does not allow entry delay. Immediate alarm after triggering a sensor");
        add_primary_call(1, 10, 6619296, 1);
        Thread.sleep(1000);
        logger.info("Arm Stay from User website, select No Entry Delay");
        adc.New_ADC_session_User("mypanel01", "qolsys123");
        adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_phBody_ArmingStateWidget_btnArmStay")));
        adc.driver1.findElement(By.id("ctl00_phBody_ArmingStateWidget_btnArmStay")).click();
        Thread.sleep(5000);
        WebElement No_Entry_Delay_CheckBox = adc.driver1.findElement(By.id("ctl00_phBody_ArmingStateWidget_cbArmOptionNoEntryDelay"));
        try{
            if(!No_Entry_Delay_CheckBox.getAttribute("checked").equals("true")) {
                System.out.println("setting is not selected");}
        } catch (NullPointerException e) {
            No_Entry_Delay_CheckBox.click();
            e.printStackTrace();
        }
        Thread.sleep(2000);
        adc.driver1.findElement(By.id("ctl00_phBody_ArmingStateWidget_btnArmOptionStay")).click();
        Thread.sleep(5000);

        verify_armstay();
        sensors.primary_call(door_window10, open);
        Thread.sleep(2000);
        verify_in_alarm();
        enter_default_user_code();
        delete_from_primary(1);
    }
    @Test(priority =19 )
    public void Disb_34_DW12() throws Exception {
        logger.info("*Disb-34* System does not allow entry delay. Immediate alarm after triggering a sensor");
        add_primary_call(1, 12, 6619297, 1);
        Thread.sleep(1000);
        logger.info("Arm Stay from User website, select No Entry Delay");
        adc.New_ADC_session_User("mypanel01", "qolsys123");
        adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_phBody_ArmingStateWidget_btnArmStay")));
        adc.driver1.findElement(By.id("ctl00_phBody_ArmingStateWidget_btnArmStay")).click();
        Thread.sleep(5000);
        WebElement No_Entry_Delay_CheckBox = adc.driver1.findElement(By.id("ctl00_phBody_ArmingStateWidget_cbArmOptionNoEntryDelay"));
        try{
        if(!No_Entry_Delay_CheckBox.getAttribute("checked").equals("true")) {
            System.out.println("setting is not selected");}
        } catch (NullPointerException e) {
            No_Entry_Delay_CheckBox.click();
            e.printStackTrace();
        }
        Thread.sleep(2000);
        adc.driver1.findElement(By.id("ctl00_phBody_ArmingStateWidget_btnArmOptionStay")).click();
        Thread.sleep(5000);

        verify_armstay();
        sensors.primary_call(door_window12, open);
        Thread.sleep(2000);
        verify_in_alarm();
        enter_default_user_code();
        delete_from_primary(1);
    }
    @Test(priority =20)
    public void Disb_39_DW14_DW16() throws Exception {
        logger.info("*Disb-39* System will ArmStay at the end of delay if DW sensors in 16 and 14 group are tampered");
        add_primary_call(1, 14, 6619299, 1);
        add_primary_call(2, 16, 6619300, 1);
        Thread.sleep(1000);
        sensors.primary_call(door_window14, tamper);
        ARM_STAY();
        TimeUnit.SECONDS.sleep(Long_Exit_Delay/2);
        sensors.primary_call(door_window16, tamper);
        TimeUnit.SECONDS.sleep(Long_Exit_Delay);
        verify_armstay();
        DISARM();
        delete_from_primary(1);
        delete_from_primary(2);
        Thread.sleep(1000);
    }
    @Test(priority =21 )
    public void Disb_41_KF1() throws Exception {
        logger.info("*Disb-41* System will ArmStay at the end of exit delay from feyfob group 1");
        servcall.set_KEYFOB_NO_DELAY_disable();
        add_primary_call(1, 1, 6619386, 102);
        Thread.sleep(1000);
        sensors.primary_call(keyfob1, "04 01");
        TimeUnit.SECONDS.sleep(Long_Exit_Delay);
        verify_armstay();
        Thread.sleep(1000);
        DISARM();
        servcall.set_KEYFOB_NO_DELAY_enable();
        delete_from_primary(1);
        Thread.sleep(1000);
    }
    @Test(priority =22 )
    public void Disb_42_KF1() throws Exception {
        logger.info("*Disb-42* System will Disarm while count down by keyfob group 1, while armed from keyfob");
        servcall.set_KEYFOB_NO_DELAY_disable();
        add_primary_call(1, 1, 6619386, 102);
        Thread.sleep(1000);
        sensors.primary_call(keyfob1, "04 01");
        TimeUnit.SECONDS.sleep(Long_Exit_Delay/2);
        sensors.primary_call(keyfob1, "08 01");
        Thread.sleep(1000);
        verify_disarm();
        servcall.set_KEYFOB_NO_DELAY_enable();
        delete_from_primary(1);
        Thread.sleep(1000);
    }
    @Test(priority =23 )
    public void Disb_43_KF1() throws Exception {
        logger.info("*Disb-43* System will ArmStay after count down disarmed by keyfob group 1 while armed from panel");
        add_primary_call(1, 1, 6619386, 102);
        Thread.sleep(1000);
        ARM_STAY();
        TimeUnit.SECONDS.sleep(Long_Exit_Delay/2);
        sensors.primary_call(keyfob1, "08 01");
        TimeUnit.SECONDS.sleep(Long_Exit_Delay);
        verify_armstay();
        DISARM();
        delete_from_primary(1);
        Thread.sleep(1000);
    }
    @Test(priority =24 )
    public void Disb_45_KF4() throws Exception {
        logger.info("*Disb-45* System will Disarm while count down by keyfob group 4, while armed from keyfob");
        servcall.set_KEYFOB_NO_DELAY_disable();
        add_primary_call(1, 4, 6619387, 102);
        Thread.sleep(1000);
        sensors.primary_call(keyfob2, "04 01");
        TimeUnit.SECONDS.sleep(Long_Exit_Delay/2);
        sensors.primary_call(keyfob2, "08 01");
        Thread.sleep(1000);
        verify_disarm();
        servcall.set_KEYFOB_NO_DELAY_enable();
        delete_from_primary(1);
        Thread.sleep(1000);
    }
    @Test(priority =25 )
    public void Disb_46_KF4() throws Exception {
        logger.info("*Disb-46* System will ArmStay after count down disarmed by keyfob group 4 while armed from panel");
        add_primary_call(1, 4, 6619387, 102);
        Thread.sleep(1000);
        ARM_STAY();
        TimeUnit.SECONDS.sleep(Long_Exit_Delay/2);
        sensors.primary_call(keyfob2, "08 01");
        TimeUnit.SECONDS.sleep(Long_Exit_Delay);
        verify_armstay();
        DISARM();
        delete_from_primary(1);
        Thread.sleep(1000);
    }
    @Test(priority =26 )
    public void Disb_47_KF6() throws Exception {
        logger.info("*Disb-47* System will ArmStay instantly armed by keyfob 6");
        add_primary_call(1, 6, 6619388, 102);
        Thread.sleep(1000);
        sensors.primary_call(keyfob3, "04 01");
        Thread.sleep(1000);
        verify_armstay();
        DISARM();
        delete_from_primary(1);
        Thread.sleep(1000);
    }
    @Test(priority =27 )
    public void Disb_48_KF6() throws Exception {
        logger.info("*Disb-48* System will ArmStay after exit delay armed by keyfob 6");
        servcall.set_KEYFOB_NO_DELAY_disable();
        add_primary_call(1, 6, 6619388, 102);
        Thread.sleep(1000);
        sensors.primary_call(keyfob3, "04 01");
        TimeUnit.SECONDS.sleep(Long_Exit_Delay);
        Thread.sleep(1000);
        verify_armstay();
        DISARM();
        servcall.set_KEYFOB_NO_DELAY_enable();
        delete_from_primary(1);
        Thread.sleep(1000);
    }
    @Test(priority =28 )
    public void Disb_49_KF6() throws Exception {
        logger.info("*Disb-49* System will Disarm while count down by keyfob group 6, while armed from keyfob");
        servcall.set_KEYFOB_NO_DELAY_disable();
        add_primary_call(1, 6, 6619388, 102);
        Thread.sleep(1000);
        sensors.primary_call(keyfob3, "04 01");
        TimeUnit.SECONDS.sleep(Long_Exit_Delay/2);
        sensors.primary_call(keyfob3, "08 01");
        Thread.sleep(1000);
        verify_disarm();
        servcall.set_KEYFOB_NO_DELAY_enable();
        delete_from_primary(1);
        Thread.sleep(1000);
    }
    @Test(priority =29 )
    public void Disb_50_KF6() throws Exception {
        logger.info("*Disb-50* System will ArmStay after count down disarmed by keyfob group 6 while armed from panel");
        add_primary_call(1, 6, 6619388, 102);
        Thread.sleep(1000);
        ARM_STAY();
        TimeUnit.SECONDS.sleep(Long_Exit_Delay/2);
        sensors.primary_call(keyfob3, "08 01");
        TimeUnit.SECONDS.sleep(Long_Exit_Delay);
        verify_armstay();
        DISARM();
        delete_from_primary(1);
        Thread.sleep(1000);
    }
    @Test(priority =30 )
    public void Disb_52_DW10() throws Exception {
        logger.info("*Disb-52* System will go into immediate alarm at the end of exit delay after tampering contact sensor group 10");
        add_primary_call(1, 10, 6619296, 1);
        ARM_STAY();
        TimeUnit.SECONDS.sleep(Long_Exit_Delay/2);
        sensors.primary_call(door_window10, tamper);
        TimeUnit.SECONDS.sleep(Long_Exit_Delay);
        verify_in_alarm();
        enter_default_user_code();
        delete_from_primary(1);
        Thread.sleep(1000);
    }
    @Test(priority =31 )
    public void Disb_53_DW12() throws Exception {
        logger.info("*Disb-53* System will go into immediate alarm at the end of exit delay after tampering contact sensor group 12");
        add_primary_call(1, 12, 6619297, 1);
        ARM_STAY();
        TimeUnit.SECONDS.sleep(Long_Exit_Delay/2);
        sensors.primary_call(door_window12, tamper);
        TimeUnit.SECONDS.sleep(Long_Exit_Delay);
        verify_in_alarm();
        enter_default_user_code();
        delete_from_primary(1);
        Thread.sleep(1000);
    }
    @Test(priority =32 )
    public void Disb_54_DW13() throws Exception {
        logger.info("*Disb-54* System will go into immediate alarm after tampering contact sensor group 13");
        add_primary_call(1, 13, 6619298, 1);
        ARM_STAY();
        TimeUnit.SECONDS.sleep(Long_Exit_Delay/2);
        sensors.primary_call(door_window13, tamper);
        Thread.sleep(1000);
        verify_in_alarm();
        enter_default_user_code();
        delete_from_primary(1);
        Thread.sleep(1000);
    }
    @Test(priority =33 )
    public void Disb_55_DW14() throws Exception {
        logger.info("*Disb-55* System will go into alarm at the end of exit delay after tampering contact sensor group 13");
        add_primary_call(1, 14, 6619299, 1);
        ARM_STAY();
        TimeUnit.SECONDS.sleep(Long_Exit_Delay/2);
        sensors.primary_call(door_window14, tamper);
        TimeUnit.SECONDS.sleep(Long_Exit_Delay);
        verify_in_alarm();
        enter_default_user_code();
        delete_from_primary(1);
        Thread.sleep(1000);
    }
    @Test(priority =34 )
    public void Disb_56_DW16() throws Exception {
        logger.info("*Disb-56* System will ArmStay after contact sensor group 16 tamper while exit delay");
        add_primary_call(1, 16, 6619300, 1);
        ARM_STAY();
        TimeUnit.SECONDS.sleep(Long_Exit_Delay/2);
        sensors.primary_call(door_window16, tamper);
        TimeUnit.SECONDS.sleep(Long_Exit_Delay);
        verify_armstay();
        DISARM();
        delete_from_primary(1);
        Thread.sleep(1000);
    }
    @Test(priority =35 )
    public void Disb_61_DW10() throws Exception {
        logger.info("*Disb-61* Arm Away from User website, select No Entry Delay");
        add_primary_call(1, 10, 6619296, 1);
        Thread.sleep(1000);
        adc.New_ADC_session_User("mypanel01", "qolsys123");
        adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_phBody_ArmingStateWidget_btnArmAway")));
        adc.driver1.findElement(By.id("ctl00_phBody_ArmingStateWidget_btnArmAway")).click();
        Thread.sleep(5000);
        WebElement No_Entry_Delay_CheckBox = adc.driver1.findElement(By.id("ctl00_phBody_ArmingStateWidget_cbArmOptionNoEntryDelay"));
        try{
            if(!No_Entry_Delay_CheckBox.getAttribute("checked").equals("true")) {
                System.out.println("setting is not selected");}
        } catch (NullPointerException e) {
            No_Entry_Delay_CheckBox.click();
            e.printStackTrace();
        }
        Thread.sleep(2000);
        adc.driver1.findElement(By.id("ctl00_phBody_ArmingStateWidget_btnArmOptionAway")).click();
        Thread.sleep(5000);

        verify_armaway();
        sensors.primary_call(door_window10, open);
        Thread.sleep(2000);
        verify_in_alarm();
        enter_default_user_code();
        delete_from_primary(1);
    }
    @Test(priority =36 )
    public void Disb_62_DW10() throws Exception {
        logger.info("*Disb-62* System will go into alarm when Arm Away from panel, select Entry Delay Off, activate a sensor group 10");
        Home_Page home = PageFactory.initElements(driver, Home_Page.class);
        add_primary_call(1, 10, 6619296, 1);
        home.DISARM.click();
        home.System_state_expand.click();
        Thread.sleep(1000);
        home.Entry_Delay.click();
        Thread.sleep(1000);
        home.ARM_AWAY.click();
        TimeUnit.SECONDS.sleep(Long_Exit_Delay);
        Thread.sleep(1000);
        sensors.primary_call(door_window10, open);
        Thread.sleep(1000);
        verify_in_alarm();
        enter_default_user_code();
        delete_from_primary(1);
    }
    @Test(priority =37 )
    public void Disb_72_KF1() throws Exception {
        logger.info("*Disb-72* System ArmAway instantly from keyfob group 1");
        Home_Page home = PageFactory.initElements(driver, Home_Page.class);
        sensors.add_primary_call(1, 4, 6619386, 102);
        Thread.sleep(1000);
        sensors.primary_call(keyfob1, "04 04");
        Thread.sleep(1000);
        verify_armaway();
        home.ArwAway_State.click();
        enter_default_user_code();
        delete_from_primary(1);
    }




    @AfterTest
    public void tearDown() throws IOException, InterruptedException {
        driver.quit();
//        for (int i= 10; i>0; i--) {
//            delete_from_primary(i);
//        }
    }
    @AfterMethod
    public void webDriverQuit(){
        adc.driver1.quit();
    }
}
