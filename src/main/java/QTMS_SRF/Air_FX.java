package QTMS_SRF;

import ADC.ADC;
import ADC_Sanity_Test.Remote_Toolkit_Variables;
import Panel.*;
import Sensors.Sensors;
import jxl.read.biff.BiffException;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.Test;

import javax.mail.internet.NewsAddress;

public class Air_FX extends Setup{

    String page_name = "Air_FX";
    Logger logger = Logger.getLogger(page_name);
    ADC adc = new ADC();
    String accountID = adc.getAccountId();
    PanelInfo_ServiceCalls servcall = new PanelInfo_ServiceCalls();
    Sensors sensors = new Sensors();

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

    public void SensorTypeDropDown(String linkText) {
        WebElement toolkit_options = (new WebDriverWait(adc.driver1, 20))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("ctl00_phBody_drpDeviceType")));
        Select Stoolkit_options = new Select(toolkit_options);
        Stoolkit_options.selectByVisibleText(linkText);
    }

    public void SensorGroupDropDown(String linkText) {
        WebElement toolkit_options = (new WebDriverWait(adc.driver1, 20))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("ctl00_phBody_ddlSensorGroup")));
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
    public void ADC_Add_A_Sensor() throws InterruptedException, IOException, BiffException {
        Remote_Toolkit_Variables remote = PageFactory.initElements(adc.driver1, Remote_Toolkit_Variables.class);

        String SensorDL = "6500A1";
        String SensorID = "1";
        String SensorName = "Door Window Sensor";

        logger.info("Upload Logs Test begin");
        Thread.sleep(3000);
        remote.Add_Sensors.click();
        adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_phBody_ucsAddSensor_txtID"))).sendKeys(SensorID);
        adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_phBody_ucsAddSensor_UcEligibleSensorName1_txtSN"))).sendKeys(SensorName);
        adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_phBody_ucsAddSensor_txtDL"))).sendKeys(SensorDL);
        remote.Add_Sensor_Change.click();
        Thread.sleep(2000);

    }
    @Test (dependsOnMethods = {"ADC_Add_A_Sensor"}, priority =2)
    public void Check_Panel_For_Added_Sensor() throws InterruptedException, IOException, BiffException {
        Home_Page home_page = PageFactory.initElements(driver, Home_Page.class);

        Navigate_To_Edit_Sensor_Page();
        Assert.assertTrue(driver.findElement(By.id("com.qolsys:id/textView5")).getText().contains("Door Window"));
        Assert.assertTrue(driver.findElement(By.id("com.qolsys:id/textView6")).getText().contains("10-Entry-Exit-Normal Delay"));

    }

    @Test (dependsOnMethods = {"Navigate_Sensor_Control_Page"}, priority =3)
    public void ADC_Change_Sensor_Data() throws InterruptedException, IOException, BiffException {
        Remote_Toolkit_Variables remote = PageFactory.initElements(adc.driver1, Remote_Toolkit_Variables.class);

        String NewSensorName = "Cool Door Window";

        //adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Click here to visit the Equipment List screen"))).click();
        remote.Remote_Back.click();
        Thread.sleep(2000);
        remote.Sensor_Change_Page.click();
        Sensor_Data_Change("Change Sensor Name");
        adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_phBody_UcEligibleSensorName1_txtSN"))).sendKeys(NewSensorName);
        remote.Send_Command_Change.click();
        Alert simpleAlert = adc.driver1.switchTo().alert();
        String alertText = simpleAlert.getText();
        System.out.println("Alert text is " + alertText);
        simpleAlert.accept();
        Sensor_Data_Change("Change Sensor Type");
        SensorTypeDropDown("Orientation");
        remote.Send_Command_Update.click();
        Thread.sleep(3000);
        adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Back To Equipment List"))).click();
        remote.Sensor_Change_Page.click();


        Sensor_Data_Change("Change Sensor Group");
        adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Back To Equipment List"))).click();
        remote.Sensor_Change_Page.click();
        SensorGroupDropDown("12");
        remote.Sensor_Change_Page.click();

        Sensor_Data_Change("Change Sensor Activity Monitoring Status");
        adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Back To Equipment List"))).click();
        remote.Sensor_Change_Page.click();
        adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_phBody_rbtMonitoring_1"))).click();
    }


    @Test (dependsOnMethods = {"ADC_Add_A_Sensor"}, priority =4)
    public void Check_Panel_For_Updated_Sensor() throws InterruptedException, IOException, BiffException {
        Navigate_To_Edit_Sensor_Page();
        Assert.assertTrue(driver.findElement(By.id("com.qolsys:id/textView5")).getText().contains("Cool Door Window"));
        Assert.assertTrue(driver.findElement(By.id("com.qolsys:id/textView6")).getText().contains("12-Entry-Exit-Long Delay"));
    }

    @Test (dependsOnMethods = {"Navigate_Sensor_Control_Page"}, priority =5)
    public void ADC_Delete_A_Sensor() throws InterruptedException, IOException, BiffException {
        Remote_Toolkit_Variables remote = PageFactory.initElements(adc.driver1, Remote_Toolkit_Variables.class);

        remote.Sensor_Change_Page.click();

        Sensor_Data_Change("Delete Sensor");
        remote.Send_Command_Change.click();
        Alert simpleAlert = adc.driver1.switchTo().alert();
        String alertText = simpleAlert.getText();
        System.out.println("Alert text is " + alertText);
        simpleAlert.accept();
    }

    @AfterClass
    public void tearDown() throws IOException, InterruptedException {
        adc.delete_sensor(1);
        adc.driver1.quit();
    }


}

/*
Verify the sensor can be added from Dealer site CHECK
Verify sensor can be deleted from dealer site CHECK
Verify sensor group can be changed from dealer site CHECK
Verify sensor type can be changed from dealer site CHECK

Verify sensor name can be changed from ADC dealer site and synced to panel
(in disarm and arm stay and away) CHECK

Verify Sensor activity monitor can be changed from ADC dealersite CHECK
(1. go to ADC  Airfx sensor page to change a sensor activity monitoring  2 After command is being sent, check for sensor actvity monitoring )
should be reflected on the user site too.

Verify Auto-bypass can be enable/disable from ADC dealersite

Verifiy Turn On/Off Auto Stay command can be send to panel from ADC dealer site

*/

