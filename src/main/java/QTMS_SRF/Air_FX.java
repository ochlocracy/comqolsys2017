package QTMS_SRF;

import ADC.ADC;
import ADC_Sanity_Test.Remote_Toolkit_Variables;
import Panel.*;
import jxl.read.biff.BiffException;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.Test;

public class Air_FX extends Setup{

    String page_name = "Air_FX";
    Logger logger = Logger.getLogger(page_name);
    ADC adc = new ADC();
    String accountID = adc.getAccountId();
    PanelInfo_ServiceCalls servcall = new PanelInfo_ServiceCalls();

    /*** If you want to run tests only on the panel, please set ADCexecute value to false ***/
    String ADCexecute = "true";

    public Air_FX() throws IOException, BiffException {
    }

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
        adc.driver1.get("https://alarmadmin.alarm.com/Support/Commands/GetSensorNames.aspx");
    }



    public void Sensor_Data_Change(String linkText) {
        WebElement toolkit_options = (new WebDriverWait(adc.driver1, 20))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("ctl00_phBody_ddlActions")));
        Select Stoolkit_options = new Select(toolkit_options);
        Stoolkit_options.selectByVisibleText(linkText);
    }

    @BeforeClass
    public void webDriver() {
        adc.webDriverSetUp();
    }

    @Test
    public void Navigate_Sensor_Control_Page() throws java.lang.Exception {
        New_ADC_session(accountID);
        adc.getDriver1().manage().window().maximize();
        Thread.sleep(3000);
    }

    @Test (dependsOnMethods = {"Navigate_Sensor_Control_Page"}, priority =1)
    public void Add_A_Sensor() throws InterruptedException, IOException, BiffException {
        Remote_Toolkit_Variables remote = PageFactory.initElements(adc.driver1, Remote_Toolkit_Variables.class);

        String SensorDL = "6500A1";
        String SensorID = "1";
        String SensorName = "Door Window";

        logger.info("Upload Logs Test begin");
        Thread.sleep(3000);
        remote.Add_Sensors.click();
        adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_phBody_ucsAddSensor_txtID"))).sendKeys(SensorID);
        adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_phBody_ucsAddSensor_UcEligibleSensorName1_txtSN"))).sendKeys(SensorName);
        adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_phBody_ucsAddSensor_txtDL"))).sendKeys(SensorDL);
        remote.Add_Sensor_Change.click();
        remote.Remote_Back.click();
        Thread.sleep(2000);
        remote.Sensor_Change_Page.click();
        Sensor_Data_Change("Change Sensor Name");

        Sensor_Data_Change("Change Sensor Type");

        Sensor_Data_Change("Change Sensor Group");

        Sensor_Data_Change("Change Sensor Activity Monitoring Status");

        Sensor_Data_Change("Delete Sensor");

/*
        adc.driver1.navigate().back();
        Thread.sleep(5000);
        remote.Delete_Sensors.click();
        remote.Check_Box_Delete.click();
        remote.Send_Command_FX.click();
        Alert simpleAlert = adc.driver1.switchTo().alert();
        String alertText = simpleAlert.getText();
        System.out.println("Alert text is " + alertText);
        simpleAlert.accept();

        */
    }


    @AfterClass
    public void tearDown() throws IOException, InterruptedException {
        adc.driver1.quit();}

}
/*
Verify the sensor can be added from Dealer site
Verify sensor can be deleted from dealer site
Verify sensor group can be changed from dealer site
Verify sensor type can be changed from dealer site

Verify sensor name can be changed from ADC dealer site and synced to panel
(in disarm and arm stay and away)

Verify Sensor activity monitor can be changed from ADC dealersite
(1. go to ADC  Airfx sensor page to change a sensor activity monitoring  2 After command is being sent, check for sensor actvity monitoring )
should be reflected on the user site too.

Verify Auto-bypass can be enable/disable from ADC dealersite

Verifiy Turn On/Off Auto Stay command can be send to panel from ADC dealer site

*/

