package QTMS_SRF;

import ADC.ADC;
import Panel.Home_Page;
import Panel.PanelInfo_ServiceCalls;
import Panel.Settings_Page;
import Panel.Setup;
import Sensors.Sensors;
import jxl.read.biff.BiffException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.List;

public class Disarm extends Setup {

    public Disarm() throws IOException, BiffException { }

    private int Normal_Exit_Delay = 10;
    private int Normal_Entry_Delay = 11;
    private int Long_Exit_Delay = 13;
    private int Long_Entry_Delay = 12;
    private String open = "06 00";
    private String close = "04 00";
    String door_window10 = "65 00 0A";
    String door_window12 = "65 00 1A";
    String door_window13 = "65 00 2A";
    String door_window14 = "65 00 3A";
    String door_window16 = "65 00 4A";
    String door_window25 = "65 00 5A";
    String door_window8 = "65 00 6A";
    String motion15 = "55 00 44";
    String motion17 = "55 00 54";
    String motion20 = "55 00 64";
    String motion35 = "55 00 74";

    String page_name = "SRF Disarm";
    Logger logger = Logger.getLogger(page_name);
    Sensors sensors = new Sensors();
    ADC adc = new ADC();
    PanelInfo_ServiceCalls servcall = new PanelInfo_ServiceCalls();
    String AccountID = adc.getAccountId();

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
    @Test(priority = 11)
    public void Disb_22_DW8() throws Exception {
        Settings_Page sett = PageFactory.initElements(driver, Settings_Page.class);
        Home_Page home = PageFactory.initElements(driver, Home_Page.class);
        logger.info("*Disb_21* Open/Close event is displayed in panel history for sensor group 8");
        add_primary_call(1, 8, 6619302, 1);
        Thread.sleep(1000);
        //      sensors.primary_call(door_window8, open);
        //     Thread.sleep(35000);
        adc.driver1.manage().window().maximize();
        adc.driver1.get("https://www.alarm.com/web/Security/SystemSummary.aspx");
        adc.driver1.findElement(By.id("ctl00_ContentPlaceHolder1_loginform_txtUserName")).sendKeys("mypanel01");
        adc.driver1.findElement(By.className("password")).sendKeys("qolsys123");
        Thread.sleep(1000);
        adc.driver1.findElement(By.id("ctl00_ContentPlaceHolder1_loginform_signInButton")).click();
        Thread.sleep(1000);
        try {
            if (adc.driver1.findElement(By.id("ctl00_responsiveBody_pageInfoActions_buttonSave")).isDisplayed()) {
                adc.driver1.findElement(By.id("ctl00_responsiveBody_pageInfoActions_buttonSave")).click();
            }
        } catch (Exception e) {
        }

        Thread.sleep(5000);
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
