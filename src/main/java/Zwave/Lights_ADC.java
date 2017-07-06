package Zwave;

import Panel.Setup;
import jxl.read.biff.BiffException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.*;

import java.io.IOException;

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
    public void start_ADC_session() throws InterruptedException {
        getDriver1().manage().window().maximize();
        String ADC_URL = "https://alarmadmin.alarm.com";
        getDriver1().get(ADC_URL);
        String login = "qnathan";
        String password = "Qnathan*";
        String customer_login = "Gen2-8334";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtUsername")));
        getDriver1().findElement(By.id("txtUsername")).sendKeys(login);
        getDriver1().findElement(By.id("txtPassword")).sendKeys(password);
        getDriver1().findElement(By.id("butLogin")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_phBody_ucsFindCustomer_txtLoginName")));
        getDriver1().findElement(By.id("ctl00_phBody_ucsFindCustomer_txtLoginName")).sendKeys(customer_login);
        getDriver1().findElement(By.id("ctl00_phBody_ucsFindCustomer_btnSearch")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_phBody_ucsFindCustomer_gridSearchResults_" +
                "ctl02_lnkCustomerId")));
        getDriver1().findElement(By.id("ctl00_phBody_ucsFindCustomer_gridSearchResults_ctl02_lnkCustomerId")).click();
    }

    @Test (priority = 1)
    public void commTest(){
        navigate_to_Advanced_Settings_page();

    }

    @AfterTest
    public void tearDown () throws IOException, InterruptedException {
        log.endTestCase(page_name);
        driver.quit();
        getDriver1().quit();
    }

}
