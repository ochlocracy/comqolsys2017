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

    public void checkPanelUI(String state, Boolean first) throws Exception{
        if(first)
            swipe_left();

        File light_state = new File(projectPath + "/scr/" + state);
        Thread.sleep(10000);
        List<WebElement> status = driver.findElements(By.id("com.qolsys:id/statusButton"));

        checkAllStatus(light_state, status);
    }

    @BeforeTest
    public void capabilities_setup() throws Exception {
        setup_driver( get_UDID(),"http://127.0.1.1", "4723");
        webDriverSetUp();
        setup_logger(page_name);
    }

    @Test
    public void turnOnLights() throws Exception{
        //navigate to user site
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

        //individually turn on all 3 lights
        getDriver1().findElement(By.xpath("id('ctl00_phBody_ucLightDeviceRepeaterControl_SwitchesAndDimmers_rptDevices" +
                "_ctl00_btnDevicesViewDeviceOn')")).click();
        Thread.sleep(2000);
        getDriver1().findElement(By.xpath("id('ctl00_phBody_ucLightDeviceRepeaterControl_SwitchesAndDimmers_rptDevices" +
                "_ctl01_btnDevicesViewDeviceOn')")).click();
        Thread.sleep(2000);
        getDriver1().findElement(By.xpath("id('ctl00_phBody_ucLightDeviceRepeaterControl_SwitchesAndDimmers_rptDevices" +
                "_ctl02_btnDevicesViewDeviceOn')")).click();
        Thread.sleep(2000);

        checkPanelUI("light_on", true);
    }

    @Test (priority = 1)
    public void turnOffLights() throws Exception{
        //individually turn all 3 lights off
        getDriver1().findElement(By.xpath("id('ctl00_phBody_ucLightDeviceRepeaterControl_SwitchesAndDimmers_rptDevices_" +
                "ctl00_btnDevicesViewDeviceOff')")).click();
        Thread.sleep(2000);
        getDriver1().findElement(By.xpath("id('ctl00_phBody_ucLightDeviceRepeaterControl_SwitchesAndDimmers_rptDevices" +
                "_ctl01_btnDevicesViewDeviceOff')")).click();
        Thread.sleep(2000);
        getDriver1().findElement(By.xpath("id('ctl00_phBody_ucLightDeviceRepeaterControl_SwitchesAndDimmers_rptDevices" +
                "_ctl02_btnDevicesViewDeviceOff')")).click();
        Thread.sleep(2000);

        checkPanelUI("light_off", false);
    }

    @Test (priority = 2)
    public void createGroup() throws Exception{

        //begin creation of new group
        getDriver1().findElement(By.xpath("id('ctl00_phBody_lnkNewGroup')")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("id('ctl00_lblPageTitle')")));
        getDriver1().findElement(By.xpath("id('ctl00_phBody_txtName')")).sendKeys("test");
        getDriver1().findElement(By.xpath("id('ctl00_phBody_LinkButtonWithPermissionsChecker1')")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("id('ctl00_phBody_rptEditDevices_ctl00_" +
                "lblDevice')")));

        //add all 3 lights & save
        getDriver1().findElement(By.xpath("id('ctl00_phBody_rptEditDevices_ctl00_lblDevice')")).click();
        getDriver1().findElement(By.xpath("id('ctl00_phBody_rptEditDevices_ctl01_lblDevice')")).click();
        getDriver1().findElement(By.xpath("id('ctl00_phBody_rptEditDevices_ctl02_lblDevice')")).click();
        getDriver1().findElement(By.xpath("//button[contains(.,'Done')]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_phBody_btnSave")));
        Thread.sleep(2000);
        getDriver1().findElement(By.id("ctl00_phBody_btnSave")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("id('ctl00_phBody_ucLightGroupRepeater" +
                "Control_SwitchesAndDimmers_rptGroups_ctl00_lblGroupName')")));

        //turn on group
        getDriver1().findElement(By.xpath("id('ctl00_phBody_ucLightGroupRepeaterControl_SwitchesAndDimmers_" +
                "rptGroups_ctl00_btnGroupOn')")).click();
        Thread.sleep(3000);

        checkPanelUI("light_on", false);
    }

    @Test (priority = 3)
    public void groupOff() throws Exception{
        //turn off group
        getDriver1().findElement(By.xpath("id('ctl00_phBody_ucLightGroupRepeaterControl_SwitchesAndDimmers_" +
                "rptGroups_ctl00_btnGroupOff')")).click();
        getDriver1().findElement(By.id("ctl00_phBody_ucLightGroupRepeaterControl_SwitchesAndDimmers_rptGroups_ctl00" +
                "_lnkGroupEdit")).click();

        Thread.sleep(2000);
        checkPanelUI("light_off", false);
    }

    @AfterTest
    public void tearDown () throws IOException, InterruptedException {
        log.endTestCase(page_name);
        driver.quit();
        getDriver1().quit();
    }

}
