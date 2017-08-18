package Cellular;

import ADC.ADC;
import Panel.Advanced_Settings_Page;
import Panel.PanelInfo_ServiceCalls;
import Panel.Setup;
import jxl.read.biff.BiffException;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class Cell extends Setup{
    public Cell() throws IOException, BiffException { }
    String page_name = "QTMS SystemTest_DualPath test cases";
    Logger logger = Logger.getLogger(page_name);
    PanelInfo_ServiceCalls servcall = new PanelInfo_ServiceCalls();

    @BeforeTest
    public void capabilities_setup() throws Exception {
        setup_driver(get_UDID(), "http://127.0.1.1", "4723");
        setup_logger(page_name);}



    @Test
    public void SASST_031() throws Exception {
servcall.DTMF();
Thread.sleep(2000);
servcall.logcat_stop();
        logger.info("DTMF");


    }

    @AfterTest
    public void tearDown () throws IOException, InterruptedException {
        log.endTestCase(page_name);
        driver.quit(); }






}
