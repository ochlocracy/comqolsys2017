package ADC_Sanity_Test;

import Panel.Setup;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import jxl.read.biff.BiffException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by nchortek on 7/25/17.
 */
public class Zwave extends Setup {

    public Zwave() throws IOException, BiffException{
    }

    String page_name = "Zwave Sanity";
    Logger logger = Logger.getLogger(page_name);

    //ADC Credentials
    String login = "Gen2-8334";
    String password = "qolsys1234";

    @BeforeTest
    public void capabilities_setup() throws Exception {
        setup_driver( get_UDID(),"http://127.0.1.1", "4723");
        webDriverSetUp();
        setup_logger(page_name);
    }

    //******************************************
    //************* SMOKE TEST *****************
    //******************************************

    @Test
    public void smoke_sensors() throws InterruptedException {
        logger.info("navigating to user site...");
        navigate_to_user_site(login, password);
        Thread.sleep(5000);
    }

    @AfterMethod
    public void tearDown () throws IOException, InterruptedException {
        log.endTestCase(page_name);
        driver.quit();
        getDriver1().quit();
    }

}
