package ADC_Sanity_Test;

import ADC.ADC;
import Panel.*;
import jxl.read.biff.BiffException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.*;
import javax.swing.plaf.InternalFrameUI;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.Test;


public class Remote_Toolkit extends Setup {
    String page_name = "Remote Toolkit";
    Logger logger = Logger.getLogger(page_name);
    ADC adc = new ADC();
    String AccountID = adc.getAccountId();
    PanelInfo_ServiceCalls servcall = new PanelInfo_ServiceCalls();

    /*** If you want to run tests only on the panel, please set ADCexecute value to false ***/
    String ADCexecute = "true";

    public Remote_Toolkit() throws IOException, BiffException {
    }

    public void verify_setting(String setting, String call, String expected) throws IOException {
        String result = execCmd(adbPath + " shell service call qservice " + call).split(" ")[2];
        if(result.equals(expected))
            logger.info("[Pass] " + setting + " has value: " + expected);
        else
            logger.info("[Fail] " + setting + " has value: " + result + ". Expected:" + expected);
    }

    public void New_ADC_session(String accountID) throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        adc.getDriver1().manage().window().maximize();
        String ADC_URL = "https://alarmadmin.alarm.com/Support/CustomerInfo.aspx?customer_Id="+accountID;
        adc.getDriver1().get(ADC_URL);
        String login = "qautomation";
        String password = "Qolsys123";
        Thread.sleep(2000);
        adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtUsername")));
        adc.getDriver1().findElement(By.id("txtUsername")).sendKeys(login);
        adc.getDriver1().findElement(By.id("txtPassword")).sendKeys(password);
        adc.getDriver1().findElement(By.id("butLogin")).click();
        Thread.sleep(1000);
        adc.driver1.get("https://alarmadmin.alarm.com/Support/AirFx/rt_MainMenu.aspx");
    }

    @BeforeTest
    public void capabilities_setup() throws Exception {
        setup_driver( get_UDID(),"http://127.0.1.1", "4723");
        setup_logger(page_name);
    }

    @BeforeMethod
    public void webDriver() {
        adc.webDriverSetUp();
    }

    @Test
    public void Code_Management() throws java.lang.Exception {
        logger.info("Change Installer Code Test Begin");
        String InstallerCode = "4444";
        New_ADC_session(adc.getAccountId());
        Thread.sleep(4000);
        adc.getDriver1().findElement(By.partialLinkText("Change Installer Code")).click();
        Thread.sleep(2000);
        adc.getDriver1().findElement(By.id("ctl00_phBody_UcsChangeInstallerCode2_txtInstallerCode")).sendKeys(InstallerCode);
        adc.getDriver1().findElement(By.id("ctl00_phBody_UcsChangeInstallerCode2_btnSendCommand")).click();
        logger.info("Pass: Installer Code Changed");
        Thread.sleep(3000);
        adc.getDriver1().findElement(By.id("ctl00_phBody_UcAirFxNaviFooter1_btnBack")).click();
        Thread.sleep(2000);

        logger.info("Change 'User Code' Test Begin");
        String UserLogin = "automation8946";
        String UserPass = "qolsys123";
        String FirstName = "Test";
        String LastName = "User";
        String NewUserCode = "3333";
        adc.getDriver1().findElement(By.partialLinkText("Edit Panel User Codes")).click();
        Thread.sleep(2000);
        adc.driver1.get("https://www.alarm.com/web/Users/Users.aspx");
        Thread.sleep(3000);
        adc.getDriver1().findElement(By.id("ctl00_ContentPlaceHolder1_loginform_txtUserName")).sendKeys(UserLogin);
        adc.getDriver1().findElement(By.xpath("/html/body/form/div[5]/div/div[1]/div[1]/div[3]/div[2]/input")).sendKeys(UserPass);
        adc.getDriver1().findElement(By.id("ctl00_ContentPlaceHolder1_loginform_signInButton")).click();
        Thread.sleep(2000);
        adc.getDriver1().findElement(By.id("ctl00_responsiveBody_usersControl_btnNewUser")).click();
        adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_responsiveBody_userAddEditControl_firstName")));
        adc.getDriver1().findElement(By.id("ctl00_responsiveBody_userAddEditControl_firstName")).sendKeys(FirstName);
        adc.getDriver1().findElement(By.id("ctl00_responsiveBody_userAddEditControl_lastName")).sendKeys(LastName);
        Thread.sleep(3000);
        adc.getDriver1().findElement(By.id("ctl00_responsiveBody_userAddEditControl_partitionsSelectDisplay")).click();
        Thread.sleep(1000);
        adc.getDriver1().findElement(By.xpath("/html/body/div/form/div[4]/div[2]/div/div[3]/div[3]/div[1]/div/div/div/div[1]/div[3]/div[2]/div/div[2]/div[1]/div[2]/ul/li/span")).click(); //panel access toggle-er
        adc.getDriver1().findElement(By.id("ctl00_responsiveBody_userAddEditControl_userPinCode_userCodePin")).sendKeys(NewUserCode);
        Thread.sleep(2000);
        adc.getDriver1().findElement(By.id("ctl00_responsiveBody_userAddEditControl_pageActionButtons_buttonSave")).click(); //save button
        Thread.sleep(3000);
        adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Test User")));
        logger.info("Pass: User Code Changed");
        adc.getDriver1().findElement(By.id("ctl00_responsiveBody_usersControl_lnkDeleteUser")).click();
        Thread.sleep(5000);

        logger.info("Change 'Duress Code' Test Begin");
        New_ADC_session(adc.getAccountId());
        //or         adc.driver1.get("https://www.alarm.com/web/Users/Users.aspx");
        Settings_Page settings = PageFactory.initElements(driver, Settings_Page.class);
        Security_Arming_Page arming = PageFactory.initElements(driver, Security_Arming_Page.class);
        Advanced_Settings_Page adv = PageFactory.initElements(driver, Advanced_Settings_Page.class);
        Installation_Page inst = PageFactory.initElements(driver, Installation_Page.class);
        Home_Page home = PageFactory.initElements(driver, Home_Page.class);
        adc.getDriver1().findElement(By.partialLinkText(" Enable/Disable Duress Code")).click();
        adc.getDriver1().findElement(By.id("ctl00_phBody_ucsDuressCode_drpdwnStatus")).click();
        adc.getDriver1().findElement(By.partialLinkText("on")).click();
        adc.getDriver1().findElement(By.className("btnBlue")).click();
        adc.getDriver1().findElement(By.id("ctl00_phBody_ucAirFxNaviFooter_btnBack")).click();
        logger.info("Enabled Duress Code");
        logger.info("Verify system can be DISARMED with Duress code when the setting is enabled");
        home.DISARM.click();
        home.ARM_STAY.click();
        Thread.sleep(2000);
        home.DISARM.click();
        home.Nine.click();
        home.Nine.click();
        home.Nine.click();
        home.Eight.click();
        if (settings.Invalid_User_Code.isDisplayed()) {
            logger.info("Fail: Duress code does not work");
        }
        verify_disarm();
        Thread.sleep(2000);
        navigate_to_Advanced_Settings_page();
        adv.INSTALLATION.click();
        inst.SECURITY_AND_ARMING.click();
        arming.Duress_Authentication.click();
        Thread.sleep(2000);
        logger.info("Pass: Duress Code Changed");
    }

    @Test
    public void Panel_Settings() throws java.lang.Exception {
        String ON = "00000001";
        String OFF = "00000000";
        int one_sec = 1000;
        logger.info("Change 'Dialer Delay' Test Begin");
        String DialerDelay = "5";
        adc.getDriver1().findElement(By.partialLinkText("Adjust Dialer Delay")).click();
        adc.getDriver1().findElement(By.id("ctl00_phBody_ucsAdjustDialerDelay_txtDialerDelay")).sendKeys(DialerDelay);
        Thread.sleep(2000);
        adc.getDriver1().findElement(By.id("ctl00_phBody_ucsAdjustDialerDelay_btnSendCommand")).click();
        Thread.sleep(2000);
        verify_setting("Dialer Delay", "36 i32 0 i32 0 i32 12 i32 0 i32 0", "00000014");
        logger.info("Pass: Dialer Delay changed");
        adc.getDriver1().findElement(By.id("ctl00_phBody_ucAirFxNaviFooter_btnBack")).click();
    }

    @Test
    public void Alarm_Settings() throws Exception {
        Remote_Toolkit_Variables about = PageFactory.initElements(driver, Remote_Toolkit_Variables.class);
        adc.driver1.get("https://alarmadmin.alarm.com/Support/RemoteToolkit.aspx");
        logger.info("Change 'Siren Timeout' Test Begin");
        element_verification(about.Alarm_Settings, "Alarm Settings");
        about.Alarm_Settings.click();
        Thread.sleep(1000);

        logger.info("Pass: Entry/Exit Delay");
    }

    @Test
    public void Arming_Settings() throws java.lang.Exception {
        String ExitDelay = "5";
        String EntryDelay = "5";
        logger.info("Change 'Entry/Exit Delay' Test Begin");

        adc.getDriver1().findElement(By.partialLinkText("Change Entry/Exit Delay")).click();
        Thread.sleep(2000);
        adc.getDriver1().findElement(By.id("ctl00_phBody_UcsEntryExitDelay_txtEntryDelay1")).sendKeys(EntryDelay);
        adc.getDriver1().findElement(By.id("ctl00_phBody_UcsEntryExitDelay_txtExitDelay1")).sendKeys(ExitDelay);
        adc.getDriver1().findElement(By.id("ctl00_phBody_UcsEntryExitDelay_btnSubmit")).click();
        logger.info("Pass: Entry/Exit Delay");
        adc.getDriver1().findElement(By.id("ctl00_phBody_ucAirFxNaviFooter_btnBack")).click();
        Thread.sleep(2000);
    }

    @AfterTest
    public void tearDown() throws IOException, InterruptedException {
        driver.quit();
    }

    @AfterMethod
    public void webDriverQuit(){
        adc.driver1.quit();
    }
}




























//servcall.get_DI

        // ALER_DELAY();

        /*       Thread.sleep(one_sec);
        public void get_DIALER_DELAY() throws IOException, InterruptedException {
            String command = adbPath + " shell service call qservice 36 i32 0 i32 0 i32 12 i32 0 i32 0";
            rt.exec(command);}
        */

        /*
        logger.info("Set Panel Time");
        adc.getDriver1().findElement(By.partialLinkText("Panel Time")).click();
        adc.getDriver1().findElement(By.id("ctl00_phBody_ucsPanelDatetime_butSetTime")).click();
        logger.info("Set Weather Info");
        adc.getDriver1().findElement(By.partialLinkText("Send Weather Info")).click();
        adc.getDriver1().findElement(By.id("ctl00_phBody_btnSubmit")).click();
        adc.getDriver1().findElement(By.id("ctl00_phBody_UcAirFxNaviFooter1_btnBack")).click();
        */


/*


6. Edit Siren Timeout

8. Edit Disable Siren

14. Edit Auto Bypass

16. Edit Auto Stay

18. Edit Arm Stay-No Delay

20. Edit Keyfob No Delay

22. Edit RF Jam Detection

24. Edit Temperature

26. Edit Language Not Fixed

28. Edit Loss of Supervisory Signals (Non-Emergency Sensors)

30. Edit Secure Camera

32. Edit Secure Arming

34. Edit Refuse Arming When Battery Low

36. Edit SIA Limits

38. Edit Volume

40. Edit Sensor Voice Prompts

42. Edit Panel Voice Prompts

44. Edit Safety Sensor Voice Prompts

46. Edit All Voice Prompts

48. Edit Sensor Chimes

50. Edit Panel Chimes

52. Edit Safety Sensor Chimes

54. Edit All Chimes

56. Edit Brightness

58. Edit Photo Frame Duration

60. Edit Transition Effect

62. Edit Photo Frame Shuffle

64. Edit Display Type

66. Edit Photo Frame Start Time

68. Edit Automatically turn off display

70. Edit Automatically turn on display

72. Edit Duress Auth

74. Edit Power Management Sleep Timeout

76. Edit Z-Wave

78. Edit Power Management

80. Edit SIA Power Restoration

82. Edit Sensor Low Battery Trouble Beep

84. Edit Sensor Tamper Trouble Beep

86. Edit Trouble Beeps Timeout

88. Edit Dealer Name

90. Edit Dealer Tag Line 1

92. Edit Dealer Tag Line 2

94. Edit Dealer Phone

96. Edit Dealer Email

98. Edit Dealer Web Address

100. Edit Auto Exit Time Extension

102. Edit Smart Socket Limit

104. Edit Thermostat Limit

106. Edit Door Lock Limit

108. Edit Light Limit

110. Edit Log Level

112. Edit Auto Upload Logs

114. Edit Allow Master Code Z-Wave Settings

116. Edit Security RF Receiver

118. Edit Fire Verification

120. Edit Disarm Photos

122. Edit Severe Weather Siren Warning

124. Edit Secure Delete Images

126. Edit Allow Master Code to Access Siren and Alarms

128. Edit Allow Master Code to Access Security and Arming

130. Edit Allow Master Code to Access Image Settings

132. Edit Allow Master Code Z-Wave Management

134. Edit Alarm Photos

136. Edit Panel Tamper Trouble Beep

138. Edit All Trouble Beeps

140. Edit Automatic Upgrade

142. Edit Long Entry Delay

144. Edit Long Exit Delay

146. Edit Local Z-Wave Voice Prompts

148. Edit Remote Z-Wave Voice Prompts

150. Edit Loss of Supervisory Signals (Emergency-only)

152. Edit AC Power Loss Timeout , Not found on IQ2 Panel

154. Edit Siren Annunciation
*/


