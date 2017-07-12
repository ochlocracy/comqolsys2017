package Zwave;

import Panel.Setup;
import jxl.read.biff.BiffException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by nchortek on 7/6/17.
 */
public class Lights_ADC extends Setup{
    String page_name = "Lights_ADC";
    Logger logger = Logger.getLogger(page_name);

    public Lights_ADC() throws IOException, BiffException{
    }

    public void checkLights(String state) throws Exception{
        File light_state = new File(projectPath + "/scr/" + state);
        Thread.sleep(10000);
        List<WebElement> status = driver.findElements(By.id("com.qolsys:id/statusButton"));

        checkAllStatus(light_state, status);
    }

    @BeforeTest
    public void capabilities_setup() throws Exception {
        setup_driver(udid_, "http://127.0.1.1", "4723");
        webDriverSetUp();
        setup_logger(page_name);
    }

    @Test
    public void turnOnLights() throws InterruptedException {
        getDriver1().manage().window().maximize();
        String ADC_URL = "https://alarm.com";
        getDriver1().get(ADC_URL);
        String login = "Gen2-8334";
        String password = "qolsys1234";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("LOGIN")));
        getDriver1().findElement(By.partialLinkText("LOGIN")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_ContentPlaceHolder1_loginform_" +
                "txtUserName")));
        getDriver1().findElement(By.id("ctl00_ContentPlaceHolder1_loginform_txtUserName")).sendKeys(login);
        getDriver1().findElement(By.className("password")).sendKeys(password);
        getDriver1().findElement(By.id("ctl00_ContentPlaceHolder1_loginform_signInButton")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("emPower")));
        getDriver1().findElement(By.partialLinkText("emPower")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Lights")));
        getDriver1().findElement(By.id("Lights")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("id('ctl00_phBody_ucLightDeviceRepeater" +
                "Control_SwitchesAndDimmers_rptDevices_ctl00_btnDevicesViewDeviceOn')")));

        getDriver1().findElement(By.xpath("id('ctl00_phBody_ucLightDeviceRepeaterControl_SwitchesAndDimmers_rptDevices" +
                "_ctl00_btnDevicesViewDeviceOn')")).click();
        Thread.sleep(2000);
        getDriver1().findElement(By.xpath("id('ctl00_phBody_ucLightDeviceRepeaterControl_SwitchesAndDimmers_rptDevices" +
                "_ctl01_btnDevicesViewDeviceOn')")).click();
        Thread.sleep(2000);
        getDriver1().findElement(By.xpath("id('ctl00_phBody_ucLightDeviceRepeaterControl_SwitchesAndDimmers_rptDevices" +
                "_ctl02_btnDevicesViewDeviceOn')")).click();
        Thread.sleep(2000);
    }

    @Test (priority = 1)
    public void checkPanelUI() throws Exception{
        swipe_left();
        checkLights("light_on");
    }

    @Test (priority = 2)
    public void turnOffLights() throws InterruptedException{
        getDriver1().findElement(By.xpath("id('ctl00_phBody_ucLightDeviceRepeaterControl_SwitchesAndDimmers_rptDevices_" +
                "ctl00_btnDevicesViewDeviceOff')")).click();
        Thread.sleep(2000);
        getDriver1().findElement(By.xpath("id('ctl00_phBody_ucLightDeviceRepeaterControl_SwitchesAndDimmers_rptDevices" +
                "_ctl01_btnDevicesViewDeviceOff')")).click();
        Thread.sleep(2000);
        getDriver1().findElement(By.xpath("id('ctl00_phBody_ucLightDeviceRepeaterControl_SwitchesAndDimmers_rptDevices" +
                "_ctl02_btnDevicesViewDeviceOff')")).click();
        Thread.sleep(2000);
    }

    @Test (priority = 3)
    public void checkPanelUI2() throws Exception{
        checkLights("light_off");
    }

    @Test (priority = 4)
    public void createGroup() throws InterruptedException{
        getDriver1().findElement(By.xpath("id('ctl00_phBody_lnkNewGroup')")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("id('ctl00_lblPageTitle')")));
        getDriver1().findElement(By.xpath("id('ctl00_phBody_txtName')")).sendKeys("test");
        getDriver1().findElement(By.xpath("id('ctl00_phBody_LinkButtonWithPermissionsChecker1')")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("id('ctl00_phBody_rptEditDevices_ctl00_" +
                "lblDevice')")));
        getDriver1().findElement(By.xpath("id('ctl00_phBody_rptEditDevices_ctl00_lblDevice')")).click();
        getDriver1().findElement(By.xpath("id('ctl00_phBody_rptEditDevices_ctl01_lblDevice')")).click();
        getDriver1().findElement(By.xpath("id('ctl00_phBody_rptEditDevices_ctl02_lblDevice')")).click();
        getDriver1().findElement(By.xpath("id('modalEditDevices')/x:div/x:div/x:div[3]/x:button")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("id('deviceNameDiv')/x:div/x:span")));
        getDriver1().findElement(By.xpath("id('ctl00_phBody_btnSave')")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("id('ctl00_phBody_ucLightGroupRepeater" +
                "Control_SwitchesAndDimmers_rptGroups_ctl00_lblGroupName')")));
        getDriver1().findElement(By.xpath("id('ctl00_phBody_ucLightGroupRepeaterControl_SwitchesAndDimmers_" +
                "rptGroups_ctl00_btnGroupOn')")).click();
        Thread.sleep(3000);
    }

    @Test (priority = 5)
    public void checkPanelUI3() throws Exception{
        checkLights("light_on");
    }

    @Test (priority = 6)
    public void groupOff() throws InterruptedException{
        getDriver1().findElement(By.xpath("id('ctl00_phBody_ucLightGroupRepeaterControl_SwitchesAndDimmers_" +
                "rptGroups_ctl00_btnGroupOff')")).click();
    }

    @Test (priority = 7)
    public void checkPanelUI4() throws Exception{
        checkLights("light_off");
    }


    @AfterTest
    public void tearDown () throws IOException, InterruptedException {
        log.endTestCase(page_name);
        driver.quit();
        getDriver1().quit();
    }

}
