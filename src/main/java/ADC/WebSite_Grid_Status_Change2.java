package ADC;


import Panel.Setup1;
import jxl.read.biff.BiffException;
import org.apache.log4j.Logger;
import org.testng.annotations.*;

import java.io.IOException;


public class WebSite_Grid_Status_Change2 {
    Setup1 s = new Setup1();
    ADC myADC = new ADC();
    String page_name = "Checking status change on the website";
    Logger logger = Logger.getLogger(page_name);

    public WebSite_Grid_Status_Change2() throws IOException, BiffException {
    }
    @Parameters({"deviceName_", "applicationName_", "UDID_", "platformVersion_", "URL_", "PORT_" })
    @BeforeClass
    public void setUp(String deviceName_, String applicationName_, String UDID_, String platformVersion_, String URL_, String PORT_) throws Exception {
        s.setCapabilities(URL_);
        s.setup_logger(page_name);
    }
    @Parameters({"UDID_" })
    @Test
    public void Test9(String UDID_) throws IOException {
        logger.info("********************************************************");
        logger.info("Deleting all sensors");
        myADC.delete_all_grid(UDID_);
        System.out.println("Sensors deleted successfully");
    }
    @AfterClass
    public void tearDown() {
        s.getDriver().quit();
    }
}