package ADC_Sanity_Test;

import ADC.ADC;
import Panel.*;
import com.google.common.base.Function;
import com.thoughtworks.selenium.SeleniumException;
import jxl.read.biff.BiffException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;
import javax.swing.plaf.InternalFrameUI;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.Test;


public class Remote_Toolkit extends Setup {

    String page_name = "Remote Toolkit";
    Logger logger = Logger.getLogger(page_name);
    ADC adc = new ADC();
    String accountID = adc.getAccountId();
    PanelInfo_ServiceCalls servcall = new PanelInfo_ServiceCalls();

    public void NewWindowLoadWait() throws IOException, BiffException, NullPointerException {

        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)

                .withTimeout(60, TimeUnit.SECONDS)

                .pollingEvery(2, TimeUnit.SECONDS)

                .ignoring(NoSuchElementException.class);

        WebElement FLOO = adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_responsiveBody_ucCommands_ddlNewValue")));
    }


    /*** If you want to run tests only on the panel, please set ADCexecute value to false ***/
    String ADCexecute = "true";

    public Remote_Toolkit() throws IOException, BiffException {}

    public void New_ADC_session(String accountID) throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        adc.getDriver1().manage().window().maximize();
        String ADC_URL = "https://alarmadmin.alarm.com/Support/CustomerInfo.aspx?customer_Id=" + accountID;
        adc.getDriver1().get(ADC_URL);
        String login = "qautomation";
        String password = "Qolsys123";
        Thread.sleep(2000);
        adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtUsername")));
        adc.getDriver1().findElement(By.id("txtUsername")).sendKeys(login);
        adc.getDriver1().findElement(By.id("txtPassword")).sendKeys(password);
        adc.getDriver1().findElement(By.id("butLogin")).click();
        Thread.sleep(1000);
        adc.driver1.get("https://alarmadmin.alarm.com/Support/RemoteToolkit.aspx");
    }

    public void clickAnElementByLinkText(String linkText) {
        WebElement toolkit_options = (new WebDriverWait(adc.driver1, 20))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("ctl00_responsiveBody_ucCommands_ddlNewValue")));
        Select Stoolkit_options= new Select (toolkit_options);
        Stoolkit_options.selectByVisibleText(linkText);
    }

    @BeforeTest
    public void capabilities_setup() throws Exception {
   //     setup_driver(get_UDID(), "http://127.0.1.1", "4723");
        setup_logger(page_name);
            adc.webDriverSetUp();
    }

//    @BeforeMethod
//    public void webDriver() {
//        adc.webDriverSetUp();
//    }

    @Test
    public void Get_ToRemoteKitPage() throws java.lang.Exception {
        New_ADC_session(accountID);
        adc.getDriver1().manage().window().maximize();
        Thread.sleep(3000);
    }

    @Test (dependsOnMethods = {"Get_ToRemoteKitPage"}, priority =1)
    public void AdvancedPanelSettings() throws InterruptedException, IOException, BiffException {
        Remote_Toolkit_Variables remote = PageFactory.initElements(adc.driver1, Remote_Toolkit_Variables.class);
        logger.info("Upload Logs Test begin");
        Thread.sleep(3000);
        remote.Advanced_Panel_Settings_Dropdown.click();
        remote.Auto_Upload_logs.click();
        System.out.println("TRY NEW STAFF");

        clickAnElementByLinkText("On");
        Thread.sleep(2000);
        remote.Change.click();
        Thread.sleep(2000);
        remote.Advanced_Panel_Settings_Dropdown.click();
        Thread.sleep(2000);
        remote.Auto_Upload_logs.click();
        Thread.sleep(2000);

        clickAnElementByLinkText("Off");
        Thread.sleep(2000);
        remote.Change.click();
        Thread.sleep(2000);
        logger.info("Upload Logs Test finished");

        remote.Advanced_Panel_Settings_Dropdown.click();
        remote.Log_Level.click();
        clickAnElementByLinkText("No Log Output");
        remote.Change.click();
        Thread.sleep(2000);
        remote.Advanced_Panel_Settings_Dropdown.click();
        remote.Log_Level.click();
        clickAnElementByLinkText("Error");
        remote.Change.click();
        Thread.sleep(2000);
        remote.Advanced_Panel_Settings_Dropdown.click();
        remote.Log_Level.click();
        clickAnElementByLinkText("Fatal");
        remote.Change.click();
        Thread.sleep(2000);
        remote.Advanced_Panel_Settings_Dropdown.click();
        remote.Log_Level.click();
        clickAnElementByLinkText("Warn");
        remote.Change.click();
        Thread.sleep(2000);
        remote.Advanced_Panel_Settings_Dropdown.click();
        remote.Log_Level.click();
        clickAnElementByLinkText("Info");
        remote.Change.click();
        Thread.sleep(2000);
        remote.Advanced_Panel_Settings_Dropdown.click();
        remote.Log_Level.click();
        clickAnElementByLinkText("Debug");
        remote.Change.click();
        Thread.sleep(2000);
        remote.Advanced_Panel_Settings_Dropdown.click();
        remote.Log_Level.click();
        clickAnElementByLinkText("Verbose");
        remote.Change.click();
        Thread.sleep(2000);
        logger.info("Log Level Test finished");
    }
//
//    @Test
//    public void Remote_Alarm_Settings() throws java.lang.Exception {
//        Remote_Toolkit_Variables remote = PageFactory.initElements(driver, Remote_Toolkit_Variables.class);
//        Select toolkit_options = new Select(adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_responsiveBody_ucCommands_ddlNewValue"))));
//
//        logger.info("Alarm Settings Test on/off begin");
//        remote.Alarm_Settings_Dropdown.click();
//        remote.Alarm_Photos.click();
//        toolkit_options.selectByVisibleText("On");
//        remote.Change.click();
//        Thread.sleep(2000);
//        remote.Alarm_Settings_Dropdown.click();
//        remote.Alarm_Photos.click();
//        toolkit_options.selectByVisibleText("Of");
//        remote.Change.click();
//        Thread.sleep(2000);
//        logger.info("Alarm Settings Test on/off finished");
//
//        logger.info("Disarm Photos Test on/off begin");
//        remote.Alarm_Settings_Dropdown.click();
//        remote.Disarm_Photos.click();
//        toolkit_options.selectByVisibleText("On");
//        remote.Change.click();
//        Thread.sleep(2000);
//        remote.Alarm_Settings_Dropdown.click();
//        remote.Disarm_Photos.click();
//        toolkit_options.selectByVisibleText("Off");
//        remote.Change.click();
//        Thread.sleep(2000);
//        logger.info("Disarm Photos Test on/off finished");
//
//        logger.info("Fire Panic Test on/off begin");
//        remote.Alarm_Settings_Dropdown.click();
//        remote.Fire_Panic.click();
//        toolkit_options.selectByVisibleText("On");
//        remote.Change.click();
//        Thread.sleep(2000);
//        remote.Alarm_Settings_Dropdown.click();
//        remote.Fire_Panic.click();
//        toolkit_options.selectByVisibleText("Off");
//        remote.Change.click();
//        Thread.sleep(2000);
//        logger.info("Fire Panic Test on/off finished");
//
//        logger.info("Fire Verification Test on/off begin");
//        remote.Alarm_Settings_Dropdown.click();
//        remote.Fire_Verification.click();
//        toolkit_options.selectByVisibleText("On");
//        remote.Change.click();
//        Thread.sleep(2000);
//        remote.Alarm_Settings_Dropdown.click();
//        remote.Fire_Verification.click();
//        toolkit_options.selectByVisibleText("Off");
//        remote.Change.click();
//        Thread.sleep(2000);
//        logger.info("Fire Verification Test on/off finished");
//
//        logger.info("Police Panic Test on/off begin");
//        remote.Alarm_Settings_Dropdown.click();
//        remote.Police_Panic.click();
//        toolkit_options.selectByVisibleText("On");
//        remote.Change.click();
//        Thread.sleep(2000);
//        remote.Alarm_Settings_Dropdown.click();
//        remote.Police_Panic.click();
//        toolkit_options.selectByVisibleText("Off");
//        remote.Change.click();
//        Thread.sleep(2000);
//        logger.info("Police Panic Test on/off finish");
//
//        logger.info("RF Jam Detection Alarm Test on/off begin");
//        remote.Alarm_Settings_Dropdown.click();
//        remote.RF_Jam_Detection_Alarm.click();
//        toolkit_options.selectByVisibleText("On");
//        remote.Change.click();
//        Thread.sleep(2000);
//        remote.Alarm_Settings_Dropdown.click();
//        remote.RF_Jam_Detection_Alarm.click();
//        toolkit_options.selectByVisibleText("Off");
//        remote.Change.click();
//        Thread.sleep(2000);
//        logger.info("RF Jam Detection Alarm Test on/off finished");
//
//        logger.info("Siren Timeout Test 4-8 min begin");
//        remote.Alarm_Settings_Dropdown.click();
//        remote.Siren_Timeout.click();
//        toolkit_options.selectByVisibleText("4 min");
//        remote.Change.click();
//        Thread.sleep(2000);
//        remote.Alarm_Settings_Dropdown.click();
//        remote.Siren_Timeout.click();
//        toolkit_options.selectByVisibleText("5 min");
//        remote.Change.click();
//        Thread.sleep(2000);
//        remote.Alarm_Settings_Dropdown.click();
//        remote.Siren_Timeout.click();
//        toolkit_options.selectByVisibleText("6 min");
//        remote.Change.click();
//        Thread.sleep(2000);
//        remote.Alarm_Settings_Dropdown.click();
//        remote.Siren_Timeout.click();
//        toolkit_options.selectByVisibleText("7 min");
//        remote.Change.click();
//        Thread.sleep(2000);
//        remote.Alarm_Settings_Dropdown.click();
//        remote.Siren_Timeout.click();
//        toolkit_options.selectByVisibleText("8 min");
//        remote.Change.click();
//        Thread.sleep(2000);
//        logger.info("Siren Timeout Test 4-8 min finished");
//    }
//
//    @Test
//    public void Remote_Arming_Settings() throws java.lang.Exception {
//        Remote_Toolkit_Variables remote = PageFactory.initElements(driver, Remote_Toolkit_Variables.class);
//        Select toolkit_options = new Select(adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_responsiveBody_ucCommands_ddlNewValue"))));
//
//        String Dialer_Delay = "9";
//        String Entry_Delay = "9";
//        String Exit_Delay = "9";
//
//        logger.info("Auto Stay Test on/off begin");
//        remote.Arming_Setting_Dropdown.click();
//        remote.Auto_Stay.click();
//        toolkit_options.selectByVisibleText("On");
//        remote.Change.click();
//        Thread.sleep(2000);
//        remote.Arming_Setting_Dropdown.click();
//        remote.Auto_Stay.click();
//        toolkit_options.selectByVisibleText("Off");
//        remote.Change.click();
//        Thread.sleep(2000);
//        logger.info("Auto Stay Test on/off finish");
//
//        logger.info("Dialer_Delay Test begin");
//        remote.Arming_Setting_Dropdown.click();
//        remote.Dialer_Delay.click();
//        remote.Txt_New_Value.sendKeys(Dialer_Delay);
//        remote.Change.click();
//        Thread.sleep(2000);
//        logger.info("Dialer_Delay Test finish");
//
//        logger.info("Entry_Delay Test begin");
//        remote.Arming_Setting_Dropdown.click();
//        remote.Entry_Delay.click();
//        remote.Txt_New_Value.sendKeys(Entry_Delay);
//        remote.Change.click();
//        Thread.sleep(2000);
//        logger.info("Entry_Delay Test finish");
//
//        logger.info("Exit_Delay Test begin");
//        remote.Arming_Setting_Dropdown.click();
//        remote.Entry_Delay.click();
//        remote.Exit_Delay.sendKeys(Exit_Delay);
//        remote.Change.click();
//        Thread.sleep(2000);
//        logger.info("Exit_Delay Test finish");
//
//        logger.info("Refuse_Arming_When_Battery_Low Test on/off begin");
//        remote.Arming_Setting_Dropdown.click();
//        remote.Refuse_Arming_When_Battery_Low.click();
//        toolkit_options.selectByVisibleText("On");
//        remote.Change.click();
//        Thread.sleep(2000);
//        remote.Arming_Setting_Dropdown.click();
//        remote.Refuse_Arming_When_Battery_Low.click();
//        toolkit_options.selectByVisibleText("Off");
//        remote.Change.click();
//        Thread.sleep(2000);
//        logger.info("Refuse_Arming_When_Battery_Low Test on/off finish");
//
//        logger.info("Secure_Arming Test on/off begin");
//        remote.Arming_Setting_Dropdown.click();
//        remote.Secure_Arming.click();
//        toolkit_options.selectByVisibleText("On");
//        remote.Change.click();
//        Thread.sleep(2000);
//        remote.Arming_Setting_Dropdown.click();
//        remote.Secure_Arming.click();
//        toolkit_options.selectByVisibleText("Off");
//        remote.Change.click();
//        Thread.sleep(2000);
//        logger.info("Secure_Arming Test on/off finish");
//
//        logger.info("Secure_Arming_Photos Test on/off begin");
//        remote.Arming_Setting_Dropdown.click();
//        remote.Secure_Arming_Photos.click();
//        toolkit_options.selectByVisibleText("On");
//        remote.Change.click();
//        Thread.sleep(2000);
//        remote.Arming_Setting_Dropdown.click();
//        remote.Secure_Arming_Photos.click();
//        toolkit_options.selectByVisibleText("Off");
//        remote.Change.click();
//        Thread.sleep(2000);
//        logger.info("Secure_Arming_Photos Test on/off finish");
//
//        logger.info("Secure_Delete_Images Test on/off begin");
//        remote.Arming_Setting_Dropdown.click();
//        remote.Secure_Delete_Images.click();
//        toolkit_options.selectByVisibleText("On");
//        remote.Change.click();
//        Thread.sleep(2000);
//        remote.Arming_Setting_Dropdown.click();
//        remote.Secure_Delete_Images.click();
//        toolkit_options.selectByVisibleText("Off");
//        remote.Change.click();
//        Thread.sleep(2000);
//        logger.info("Secure_Delete_Images Test on/off finish");
//    }
//
//    @Test
//    public void Remote_Beeps_and_Speaker_Settings() throws java.lang.Exception {
//        Remote_Toolkit_Variables remote = PageFactory.initElements(driver, Remote_Toolkit_Variables.class);
//        Select toolkit_options = new Select(adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_responsiveBody_ucCommands_ddlNewValue"))));
//
//        String Trouble_Beeps = "5";
//
//       /*
//        Panel_Chimes
//        Panel_Siren (enable . disable)
//        Panel_Tamper_Trouble_Beep
//        Panel_Voice_Prompts
//        Safety_Sensor_Chimes
//        Safety_Sensor_Voice_Prompts
//        Sensor_Chimes
//        Sensor_Low Battery_Trouble_Beep
//        Sensor_Tamper_Trouble_Beep
//        Sensor_Voice_Prompts
//        Severe_Weather_Siren_Warning
//        Siren_Annunciation ( enable disable)
//        Touch_Sounds  ( enable disable)
//        Trouble_Beeps_Timeout STRING
//        Turn_On_Off_Trouble_Beeps
//        Voices_Volume (0-15)
//        Water_Freeze_Siren ( Enable disable)
//
//*/
//
//        logger.info("All_Chimes Test on/off begin");
//        remote.Beeps_And_Speakers_Dropdown.click();
//        remote.All_Chimes.click();
//        toolkit_options.selectByVisibleText("On");
//        remote.Change.click();
//        Thread.sleep(2000);
//        remote.Beeps_And_Speakers_Dropdown.click();
//        remote.All_Chimes.click();
//        toolkit_options.selectByVisibleText("Off");
//        remote.Change.click();
//        Thread.sleep(2000);
//        logger.info("All_Chimes Test on/off finish");
//
//        logger.info("All_Trouble_Beeps Test on/off begin");
//        remote.Beeps_And_Speakers_Dropdown.click();
//        remote.All_Trouble_Beeps.click();
//        toolkit_options.selectByVisibleText("On");
//        remote.Change.click();
//        Thread.sleep(2000);
//        remote.Beeps_And_Speakers_Dropdown.click();
//        remote.All_Trouble_Beeps.click();
//        toolkit_options.selectByVisibleText("Off");
//        remote.Change.click();
//        Thread.sleep(2000);
//        logger.info("All_Trouble_Beeps Test on/off finish");
//
//        logger.info("All_Voice_Prompts Test on/off begin");
//        remote.Beeps_And_Speakers_Dropdown.click();
//        remote.All_Voice_Prompts.click();
//        toolkit_options.selectByVisibleText("On");
//        remote.Change.click();
//        Thread.sleep(2000);
//        remote.Beeps_And_Speakers_Dropdown.click();
//        remote.All_Voice_Prompts.click();
//        toolkit_options.selectByVisibleText("Off");
//        remote.Change.click();
//        Thread.sleep(2000);
//        logger.info("All_Voice_Prompts Test on/off finish");
//
//        logger.info("Beeps_And_Chimes_Volume Test 0-7 lvl begin");
//        remote.Beeps_And_Speakers_Dropdown.click();
//        remote.Beeps_And_Chimes_Volume.click();
//        toolkit_options.selectByVisibleText("0");
//        remote.Change.click();
//        Thread.sleep(2000);
//        remote.Beeps_And_Speakers_Dropdown.click();
//        remote.Beeps_And_Chimes_Volume.click();
//        toolkit_options.selectByVisibleText("1");
//        remote.Change.click();
//        Thread.sleep(2000);
//        remote.Beeps_And_Speakers_Dropdown.click();
//        remote.Beeps_And_Chimes_Volume.click();
//        toolkit_options.selectByVisibleText("2");
//        remote.Change.click();
//        Thread.sleep(2000);
//        remote.Beeps_And_Speakers_Dropdown.click();
//        remote.Beeps_And_Chimes_Volume.click();
//        toolkit_options.selectByVisibleText("3");
//        remote.Change.click();
//        Thread.sleep(2000);
//        remote.Beeps_And_Speakers_Dropdown.click();
//        remote.Beeps_And_Chimes_Volume.click();
//        toolkit_options.selectByVisibleText("4");
//        remote.Change.click();
//        Thread.sleep(2000);
//        remote.Beeps_And_Speakers_Dropdown.click();
//        remote.Beeps_And_Chimes_Volume.click();
//        toolkit_options.selectByVisibleText("5");
//        remote.Change.click();
//        Thread.sleep(2000);
//        remote.Beeps_And_Speakers_Dropdown.click();
//        remote.Beeps_And_Chimes_Volume.click();
//        toolkit_options.selectByVisibleText("6");
//        remote.Change.click();
//        Thread.sleep(2000);
//        remote.Beeps_And_Speakers_Dropdown.click();
//        remote.Beeps_And_Chimes_Volume.click();
//        toolkit_options.selectByVisibleText("7");
//        remote.Change.click();
//        Thread.sleep(2000);
//        logger.info("Beeps_And_Chimes_Volume Test 0-7 lvl finished");
//
//        logger.info("Media_Volume Test 0-7 lvl begin");
//        remote.Beeps_And_Speakers_Dropdown.click();
//        remote.Media_Volume.click();
//        toolkit_options.selectByVisibleText("0");
//        remote.Change.click();
//        Thread.sleep(2000);
//        remote.Beeps_And_Speakers_Dropdown.click();
//        remote.Media_Volume.click();
//        toolkit_options.selectByVisibleText("1");
//        remote.Change.click();
//        Thread.sleep(2000);
//        remote.Beeps_And_Speakers_Dropdown.click();
//        remote.Media_Volume.click();
//        toolkit_options.selectByVisibleText("2");
//        remote.Change.click();
//        Thread.sleep(2000);
//        remote.Beeps_And_Speakers_Dropdown.click();
//        remote.Media_Volume.click();
//        toolkit_options.selectByVisibleText("3");
//        remote.Change.click();
//        Thread.sleep(2000);
//        remote.Beeps_And_Speakers_Dropdown.click();
//        remote.Media_Volume.click();
//        toolkit_options.selectByVisibleText("4");
//        remote.Change.click();
//        Thread.sleep(2000);
//        remote.Beeps_And_Speakers_Dropdown.click();
//        remote.Media_Volume.click();
//        toolkit_options.selectByVisibleText("5");
//        remote.Change.click();
//        Thread.sleep(2000);
//        remote.Beeps_And_Speakers_Dropdown.click();
//        remote.Media_Volume.click();
//        toolkit_options.selectByVisibleText("6");
//        remote.Change.click();
//        Thread.sleep(2000);
//        remote.Beeps_And_Speakers_Dropdown.click();
//        remote.Media_Volume.click();
//        toolkit_options.selectByVisibleText("7");
//        remote.Change.click();
//        Thread.sleep(2000);
//        logger.info("Media_Volume Test 0-7 lvl finished");
//
//    }

    @AfterTest
    public void tearDown() throws IOException, InterruptedException {
        adc.driver1.quit();
    }

//    @AfterMethod
//    public void webDriverQuit(){
//        adc.driver1.quit();
//    }
}

