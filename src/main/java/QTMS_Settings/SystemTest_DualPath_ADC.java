package QTMS_Settings;

import ADC.ADC;
import Cellular.Dual_path_page_elements;
import Cellular.System_Tests_page;
import Panel.Advanced_Settings_Page;
import Panel.PanelInfo_ServiceCalls;
import Panel.Setup;
import jxl.read.biff.BiffException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;

public class SystemTest_DualPath_ADC extends Setup{
    public SystemTest_DualPath_ADC() throws IOException, BiffException {}

    String page_name = "Dual-Path QTMS test cases";
    Logger logger = Logger.getLogger(page_name);
    ADC adc = new ADC();
    PanelInfo_ServiceCalls servcall = new PanelInfo_ServiceCalls();
    String AccountID = adc.getAccountId();
    String ADCexecute = "true";
    public void ADC_verification (String string, String string1, String string3) throws IOException, InterruptedException {
        String[] message = {string, string1, string3};

        if (ADCexecute.equals("true")) {
            adc.New_ADC_session(AccountID);
            adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("History"))).click();
            Thread.sleep(10000);
            for (int i =0; i< message.length; i++) {
                try {
                    WebElement history_message = adc.driver1.findElement(By.xpath(message[i]));
                    Assert.assertTrue(history_message.isDisplayed());
                    {
                        System.out.println("Dealer Site History: " + history_message.getText());
                    }
                } catch (Exception e) {
                    System.out.println("***No such element found!***");
                }
            }
        }else{
            System.out.println("Set execute to TRUE to run ADC verification part");
        }
        Thread.sleep(2000);
    }

    @BeforeTest
    public void capabilities_setup() throws Exception {
        setup_driver(get_UDID(), "http://127.0.1.1", "4723");
        setup_logger(page_name);}

    @BeforeMethod
    public void webDriver() {
        adc.webDriverSetUp();
    }



    @Test
    public void SASST_030() throws Exception {
        servcall.get_WiFi();
        Advanced_Settings_Page adv = PageFactory.initElements(driver, Advanced_Settings_Page.class);
        System_Tests_page sys = PageFactory.initElements(driver, System_Tests_page.class);
        Dual_path_page_elements dual = PageFactory.initElements(driver, Dual_path_page_elements.class);
        navigate_to_Advanced_Settings_page();
        adv.SYSTEM_TESTS.click();
        sys.DUAL_PATH_TEST.click();
        dual.start_button.click();
        Thread.sleep(5000);
        element_verification(dual.Test_result, "Test result");
        servcall.set_ARM_STAY_NO_DELAY_enable();
        servcall.EVENT_ARM_STAY();
        logger.info("SASST_030 Pass.");
        servcall.EVENT_DISARM();
    }
    @Test
    public void SASST_031() throws Exception {

        logger.info("SASST_031 Pass.");

    }

    @AfterTest
    public void tearDown () throws IOException, InterruptedException {
        log.endTestCase(page_name);
        driver.quit(); }

    @AfterMethod
    public void webDriverQuit(){
        adc.driver1.quit();

}






























}
