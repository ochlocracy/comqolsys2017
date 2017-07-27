package ADC_Sanity_Test;

import ADC.ADC;
import Panel.Setup;
import jxl.read.biff.BiffException;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by nchortek on 7/25/17.
 */
public class Zwave_ADC extends Setup {

    public Zwave_ADC() throws IOException, BiffException{
    }

    ADC adc = new ADC();

    String page_name = "Zwave_ADC Sanity";
    Logger logger = Logger.getLogger(page_name);

    //ADC Credentials
    String user_login = "Gen2-8334";
    String user_password = "qolsys1234";

    String accountID = adc.getAccountId();

    @BeforeTest
    public void capabilities_setup() throws Exception {
        setup_driver( get_UDID(),"http://127.0.1.1", "4723");
        setup_logger(page_name);
    }

    @BeforeMethod
    public  void webDriver(){
        adc.webDriverSetUp();
    }

    //******************************************
    //************* SMOKE TEST *****************
    //******************************************

    @Test
    public void smoke_sensors() throws InterruptedException {
        logger.info("navigating to dealer site...");
        adc.New_ADC_session(accountID);
        Thread.sleep(5000);
    }

    @AfterMethod
    public void tearDown () throws IOException, InterruptedException {
        log.endTestCase(page_name);
        driver.quit();
        getDriver1().quit();
    }

}
