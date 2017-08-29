package ADC_Sanity_Test;

import ADC.ADC;
import Panel.Home_Page;
import Panel.PanelInfo_ServiceCalls;
import jxl.read.biff.BiffException;
import org.apache.log4j.Logger;
import Panel.Setup;
import Sensors.Sensors;
import org.apache.tools.ant.types.resources.First;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.*;

import javax.swing.plaf.InternalFrameUI;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class AirFX extends Setup {
    String page_name = "AirFX";
    Logger logger = Logger.getLogger(page_name);
    ADC adc = new ADC();
    String AccountID = adc.getAccountId();

    /*** If you want to run tests only on the panel, please set ADCexecute value to false ***/
    String ADCexecute = "true";

    public AirFX() throws IOException, BiffException {
    }

    public void New_ADC_session(String accountID) throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        getDriver1().manage().window().maximize();
        String ADC_URL = "https://alarmadmin.alarm.com/Support/CustomerInfo.aspx?customer_Id="+accountID;
        getDriver1().get(ADC_URL);
        String login = "qautomation";
        String password = "Qolsys123";
        Thread.sleep(2000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtUsername")));
        getDriver1().findElement(By.id("txtUsername")).sendKeys(login);
        getDriver1().findElement(By.id("txtPassword")).sendKeys(password);
        getDriver1().findElement(By.id("butLogin")).click();
    }

   /* @BeforeTest
    public void capabilities_setup() throws Exception {
        setup_driver(get_UDID(), "http://127.0.1.1", "4723");
        setup_logger(page_name);
    }

    @BeforeMethod
    public void webDriver() {
        adc.webDriverSetUp();
    }
*/

    @Test
    public void navigate_to_AirFX_page() throws InterruptedException, IOException {
        getDriver1().findElement(By.partialLinkText("AirFx Remote Toolkit")).click();
    }

    @Test
    public void User_Code_Management() throws InterruptedException {
        String InstallerCode = "4444";
        getDriver1().findElement(By.partialLinkText("Change Installer Code")).click();
        Thread.sleep(2000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Enter desired Installer Code")));
        getDriver1().findElement(By.id("Enter desired Installer Code")).sendKeys(InstallerCode);
        logger.info("Installer Code Changed");

        String FirstName = "Test";
        String LastName = "User";
        String NewUserCode = "3333";
        getDriver1().findElement(By.partialLinkText("Edit Panel User Codes")).click();
        Thread.sleep(2000);
        getDriver1().findElement(By.partialLinkText("Manager User Codes")).click();
        getDriver1().findElement(By.partialLinkText("Add User")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("First Name")));
        getDriver1().findElement(By.id("First Name")).sendKeys(FirstName);
        getDriver1().findElement(By.id("Last Name")).sendKeys(LastName);
        getDriver1().findElement(By.className("adc-disarmed device-icon")).click();
        getDriver1().findElement(By.className("list-item-description text-ellipsis")).click();
        getDriver1().findElement(By.className("userCodePin")).sendKeys(NewUserCode);
        getDriver1().findElement(By.className("btn btn-primary action-save btn-add-edit-user")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("Test User")));
        logger.info("User Code Changed");


        getDriver1().findElement(By.id("ctl00_phBody_ucAirFxNaviFooter_btnBack")).click();



        getDriver1().findElement(By.partialLinkText(" Enable/Disable Duress Code")).click();
        getDriver1().findElement(By.id("ctl00_phBody_ucsDuressCode_drpdwnStatus")).click();
        getDriver1().findElement(By.partialLinkText("on")).click();
        getDriver1().findElement(By.className("btnBlue")).click();
        getDriver1().findElement(By.id("ctl00_phBody_ucAirFxNaviFooter_btnBack")).click();
        logger.info("Enabled Duress Code");

        // test duress code


    }



/*

2. Edit Installer Code

4. Edit Dialer Delay

6. Edit Siren Timeout

8. Edit Disable Siren

10. Edit Entry Delay

12. Edit Exit Delay

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


  }
