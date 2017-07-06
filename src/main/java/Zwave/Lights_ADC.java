package Zwave;

import Panel.Setup;
import jxl.read.biff.BiffException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by nchortek on 7/6/17.
 */
public class Lights_ADC extends Setup{
    String page_name = "Lights_ADC";
    Logger logger = Logger.getLogger(page_name);

    public Lights_ADC() throws IOException, BiffException{
    }

    @BeforeMethod
    public void capabilities_setup() throws Exception {
        setup_driver(udid_, "http://127.0.1.1", "4723");
        webDriverSetUp();
        setup_logger(page_name);
    }

    @Test
    public void start_ADC_session() throws InterruptedException {
        getDriver1().manage().window().maximize();
        String ADC_URL = "https://alarmadmin.alarm.com";
        getDriver1().get(ADC_URL);
        String login = "qnathan";
        String password = "Qnathan*";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtUsername")));
        getDriver1().findElement(By.id("txtUsername")).sendKeys(login);
        getDriver1().findElement(By.id("txtPassword")).sendKeys(password);
        getDriver1().findElement(By.id("butLogin")).click();
    }

    @AfterMethod
    public void tearDown () throws IOException, InterruptedException {
        log.endTestCase(page_name);
        driver.quit();
        getDriver1().quit();
    }

}
