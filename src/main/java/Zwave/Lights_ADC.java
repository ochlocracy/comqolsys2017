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
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_phBody_ucLightGroupRepeaterControl_" +
                "SwitchesAndDimmers_rptGroups_ctl00_btnGroupOn")));
        getDriver1().findElement(By.id("ctl00_phBody_ucLightGroupRepeaterControl_" +
                "SwitchesAndDimmers_rptGroups_ctl00_btnGroupOn")).click();
    }

    @Test (priority = 1)
    public void checkPanelUI() throws Exception{
        swipe_left();
        File light_on = new File(projectPath + "/scr/light_on");
        Thread.sleep(10000);
        logger.info("pulling light icons...");
        List<WebElement> status = driver.findElements(By.id("com.qolsys:id/statusButton"));

        checkStatus(light_on, status.get(0));
        checkStatus(light_on, status.get(1));
        checkStatus(light_on, status.get(2));

    }

    @AfterTest
    public void tearDown () throws IOException, InterruptedException {
        log.endTestCase(page_name);
        driver.quit();
        getDriver1().quit();
    }

}
