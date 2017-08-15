package QTMS_Settings;

import ADC.ADC;
import Cellular.Dual_path_page;
import Cellular.Dual_path_page_elements;
import Cellular.System_Tests_page;
import Cellular.WiFi_setting_page_elements;
import Panel.Advanced_Settings_Page;
import Panel.PanelInfo_ServiceCalls;
import Panel.Settings_Page;
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
import java.util.List;

public class Dual_path extends Setup{
    public Dual_path() throws IOException, BiffException {}
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
    /*** SystemTest_DualPath_WiFi On and Dual Path ON ***/
    @Test
    public void SASST_027() throws Exception {
        Advanced_Settings_Page adv = PageFactory.initElements(driver, Advanced_Settings_Page.class);
        System_Tests_page sys = PageFactory.initElements(driver, System_Tests_page.class);
        Dual_path_page_elements dual = PageFactory.initElements(driver, Dual_path_page_elements.class);
        navigate_to_Advanced_Settings_page();
        adv.SYSTEM_TESTS.click();
        sys.DUAL_PATH_TEST.click();
        dual.start_button.click();
        Thread.sleep(6000);
        element_verification(dual.Test_result, "Dual_path_Test_result_text");
        logger.info("SASST_027 Pass:Dual Path Wi-Fi test pass if Dual-Path control is enabled and Wi-Fi is connected.");}

    /*** SystemTest_DualPath_WiFi On and Dual Path Off ***/
    @Test (priority = 2)
    public void SASST_028() throws Exception {
        servcall.get_WiFi_name();
        Advanced_Settings_Page adv = PageFactory.initElements(driver, Advanced_Settings_Page.class);
        System_Tests_page sys = PageFactory.initElements(driver, System_Tests_page.class);
        Dual_path_page_elements dual = PageFactory.initElements(driver, Dual_path_page_elements.class);
        navigate_to_Advanced_Settings_page();
        adv.SYSTEM_TESTS.click();
        sys.DUAL_PATH_TEST.click();
        dual.Dual_path_Control_check_box.click();
        element_verification(dual.chkbox_result_text, "Dual_path_checkbox_text");
        dual.start_button.click();
        Thread.sleep(6000);
        element_verification(dual.Test_result, "Dual_path_Test_result_text");
        logger.info("SASST_028 Pass:Dual Path Wi-Fi test won't pass if Dual-Path control is disabled and Wi-Fi is connected.");}

    @Test (priority = 1)
    public void SASST_019() throws Exception {
        Advanced_Settings_Page adv = PageFactory.initElements(driver, Advanced_Settings_Page.class);
        System_Tests_page sys = PageFactory.initElements(driver, System_Tests_page.class);
        Dual_path_page_elements dual = PageFactory.initElements(driver, Dual_path_page_elements.class);
        navigate_to_Advanced_Settings_page();
        adv.SYSTEM_TESTS.click();
        sys.DUAL_PATH_TEST.click();
        dual.start_button.click();
        Thread.sleep(1000);
        dual.cancel_button.click();
        element_verification(dual.Test_result, "Dual_path_Test_result_text");
        logger.info("SASST_019 Pass:Wi-Fi communication test canceled. The message is shown.");}

        @Test(priority = 3)
    public void SASST_020() throws Exception {
        Advanced_Settings_Page adv = PageFactory.initElements(driver, Advanced_Settings_Page.class);
        Settings_Page set = PageFactory.initElements(driver, Settings_Page.class);
        System_Tests_page sys = PageFactory.initElements(driver, System_Tests_page.class);
        Dual_path_page_elements dual = PageFactory.initElements(driver, Dual_path_page_elements.class);
        navigate_to_Settings_page();
        set.ADVANCED_SETTINGS.click();
        enter_default_user_code();
        adv.SYSTEM_TESTS.click();
        sys.DUAL_PATH_TEST.click();
        Thread.sleep(1000);
        dual.Dual_path_Control_check_box.click();
        element_verification(dual.chkbox_result_text, "Dual_path_checkbox_text");
        Thread.sleep(1000);
        dual.Dual_path_Control_check_box.click();
        element_verification(dual.chkbox_result_text, "Dual_path_checkbox_text");
        dual.Dual_path_Control_check_box.click();
        logger.info("SASST_020 Pass: User can enable/disable dual-path control.\n" +
                "Enable message 'Dual-path is enabled; using cellular and Wi-Fi.'\n" +
                "Disable message 'Dual-path is disabled; using cellular only.'");}

        /*** WiFi is connected***/
        @Test (priority = 4)
        public void SASST_021022() throws Exception {
            servcall.get_WiFi();
            Advanced_Settings_Page adv = PageFactory.initElements(driver, Advanced_Settings_Page.class);
            System_Tests_page sys = PageFactory.initElements(driver, System_Tests_page.class);
            Dual_path_page_elements dual = PageFactory.initElements(driver, Dual_path_page_elements.class);
            navigate_to_Advanced_Settings_page();
            adv.SYSTEM_TESTS.click();
            sys.DUAL_PATH_TEST.click();
            Thread.sleep(1000);
            element_verification(dual.WiFi_status, "WiFi status");
            logger.info("SASST_021022 Pass:Wi-Fi status shows 'Connected' when Wi-Fi is connected to a router/hotspot network.");}
    @Test(priority = 5)
    public void SASST_023() throws Exception {
        servcall.Wifi_disable();
        Thread.sleep(1000);
        servcall.get_WiFi();
        Advanced_Settings_Page adv = PageFactory.initElements(driver, Advanced_Settings_Page.class);
        System_Tests_page sys = PageFactory.initElements(driver, System_Tests_page.class);
        Dual_path_page_elements dual = PageFactory.initElements(driver, Dual_path_page_elements.class);
        WiFi_setting_page_elements wifi = PageFactory.initElements(driver, WiFi_setting_page_elements.class);
        Settings_Page set = PageFactory.initElements(driver, Settings_Page.class);
        navigate_to_Settings_page();
        wifi.OK.click();
        set.ADVANCED_SETTINGS.click();
        enter_default_user_code();
        adv.SYSTEM_TESTS.click();
        sys.DUAL_PATH_TEST.click();
        Thread.sleep(1000);
        element_verification(dual.WiFi_status, "WiFi status");
        logger.info("SASST_023 Pass: Wi-Fi status shows 'Disabled' when Wi-Fi is disabled.");
        //servcall.Wifi_enable();
        }
        /** Dual path is enabled**/
    @Test(priority = 6)
    public void SASST_026() throws Exception {
        servcall.Wifi_disable();
        Thread.sleep(1000);
        servcall.get_WiFi();
        Advanced_Settings_Page adv = PageFactory.initElements(driver, Advanced_Settings_Page.class);
        System_Tests_page sys = PageFactory.initElements(driver, System_Tests_page.class);
        Dual_path_page_elements dual = PageFactory.initElements(driver, Dual_path_page_elements.class);
        WiFi_setting_page_elements wifi = PageFactory.initElements(driver, WiFi_setting_page_elements.class);
        Settings_Page set = PageFactory.initElements(driver, Settings_Page.class);
        navigate_to_Settings_page();
        wifi.OK.click();
        set.ADVANCED_SETTINGS.click();
        enter_default_user_code();
        adv.SYSTEM_TESTS.click();
        sys.DUAL_PATH_TEST.click();
        Thread.sleep(1000);
        element_verification(dual.WiFi_status, "WiFi status");
        dual.Dual_path_Control_check_box.click();
        Thread.sleep(2000);
        dual.Dual_path_Control_check_box.click();
        element_verification(dual.warning_message, "Warning message");
        System.out.println("Press 'OK'");
        dual.warning_message_OK_button.click();
        logger.info("SASST_026 Pass:There is a warning message \n" +
        " 'Network connection failed.' shows after user try to check Dual-Path control checkbox when Wi-Fi is disabled.\n" +
                "WiFi is disabled by service call");
        servcall.Wifi_enable();
        dual.Back_button.click();
        sys.DUAL_PATH_TEST.click();
        Thread.sleep(2000);
        dual.Dual_path_Control_check_box.click();
        dual.warning_message_OK_button.click();
        dual.Dual_path_Control_check_box.click();
        element_verification(dual.WiFi_status, "WiFi status");
      }

    @Test(priority = 7)
    public void SASST_026a() throws Exception {
        servcall.Wifi_disable();
        Thread.sleep(1000);
        servcall.get_WiFi();
        Advanced_Settings_Page adv = PageFactory.initElements(driver, Advanced_Settings_Page.class);
        System_Tests_page sys = PageFactory.initElements(driver, System_Tests_page.class);
        Dual_path_page_elements dual = PageFactory.initElements(driver, Dual_path_page_elements.class);
        WiFi_setting_page_elements wifi = PageFactory.initElements(driver, WiFi_setting_page_elements.class);
        Settings_Page set = PageFactory.initElements(driver, Settings_Page.class);
        navigate_to_Settings_page();
        wifi.OK.click();
        set.ADVANCED_SETTINGS.click();
        enter_default_user_code();
        adv.SYSTEM_TESTS.click();
        sys.DUAL_PATH_TEST.click();
        Thread.sleep(1000);
        element_verification(dual.WiFi_status, "WiFi status");
        dual.Dual_path_Control_check_box.click();
        Thread.sleep(2000);
        dual.Dual_path_Control_check_box.click();
        element_verification(dual.warning_message, "Warning message");
        System.out.println("Press 'Cancel'");
        dual.warning_message_cancell_button.click();
        logger.info("SASST_026 Pass:There is a warning message \n" +
                " 'Network connection failed.' shows after user try to check Dual-Path control checkbox when Wi-Fi is disabled.\n" +
                "WiFi is disabled by service call");
        System.out.println("Turning back WiFi Connection");
        servcall.Wifi_enable();
        Thread.sleep(6000);
        servcall.get_WiFi_name();
        dual.Dual_path_Control_check_box.click();
        element_verification(dual.WiFi_status, "WiFi status");
    }

    @Test(priority = 8)
    public void SASST_026b() throws Exception {
        Advanced_Settings_Page adv = PageFactory.initElements(driver, Advanced_Settings_Page.class);
        System_Tests_page sys = PageFactory.initElements(driver, System_Tests_page.class);
        Dual_path_page_elements dual = PageFactory.initElements(driver, Dual_path_page_elements.class);
        WiFi_setting_page_elements wifi = PageFactory.initElements(driver, WiFi_setting_page_elements.class);
        navigate_to_Advanced_Settings_page();
        adv.WI_FI.click();
        wifi.Checkbox.click();
        Thread.sleep(2000);
        servcall.get_WiFi();
        wifi.Back_button.click();
        adv.SYSTEM_TESTS.click();
        sys.DUAL_PATH_TEST.click();
        Thread.sleep(2000);
        element_verification(dual.WiFi_status, "WiFi status");
        dual.Dual_path_Control_check_box.click();
        Thread.sleep(2000);
        dual.Dual_path_Control_check_box.click();
        element_verification(dual.warning_message, "Warning message");
        dual.warning_message_OK_button.click();
        logger.info("SASST_026 Pass:There is a warning message \n" +
                " 'Network connection failed.' shows after user try to check Dual-Path control checkbox when Wi-Fi is disabled.\n" +
                "WiFi is disabled by unchecked the wifi setting checkbox");
        Thread.sleep(1000);
        dual.Back_button.click();
        sys.Back_button.click();
        adv.WI_FI.click();
        wifi.Checkbox.click();
        Thread.sleep(6000);
        servcall.get_WiFi();
        wifi.Back_button.click();
        adv.SYSTEM_TESTS.click();
        sys.DUAL_PATH_TEST.click();
        Thread.sleep(1000);
        System.out.println("Turning back WiFi Connection");
        servcall.get_WiFi_name();
        dual.Dual_path_Control_check_box.click();
        element_verification(dual.WiFi_status, "WiFi status");
        }
    @Test(priority = 9)
    public void SASST_026c() throws Exception {
        Advanced_Settings_Page adv = PageFactory.initElements(driver, Advanced_Settings_Page.class);
        System_Tests_page sys = PageFactory.initElements(driver, System_Tests_page.class);
        Dual_path_page_elements dual = PageFactory.initElements(driver, Dual_path_page_elements.class);
        WiFi_setting_page_elements wifi = PageFactory.initElements(driver, WiFi_setting_page_elements.class);
        navigate_to_Advanced_Settings_page();
        adv.WI_FI.click();
        List<WebElement> li = driver.findElements(By.id("android:id/title"));
        String value1 = li.get(0).getText();
        logger.info("WiFi network name: " + value1);
        li.get(0).click();
        logger.info("Press Forget button");
        wifi.FORGET.click();
        Thread.sleep(5000);
        servcall.get_WiFi_name();
        servcall.get_WiFi();
        wifi.Back_button.click();
        adv.SYSTEM_TESTS.click();
        sys.DUAL_PATH_TEST.click();
        Thread.sleep(1000);
        element_verification(dual.WiFi_status, "WiFi status");
        dual.Dual_path_Control_check_box.click();
        Thread.sleep(2000);
        dual.Dual_path_Control_check_box.click();
        element_verification(dual.warning_message, "Warning message");
        dual.warning_message_OK_button.click();
        logger.info("SASST_026 Pass:There is a warning message \n" +
                " 'Network connection failed.' shows after user try to check Dual-Path control checkbox when Wi-Fi is disabled.\n" +
                "WiFi is disabled by click to forget wifi credentials ");
                }
    /*** SystemTest_DualPath_WiFi Off and Dual Path Off ***/

    @Test(priority = 10)
    public void SASST_029() throws Exception {
        servcall.Wifi_disable();
        Thread.sleep(1000);
        servcall.get_WiFi();
        WiFi_setting_page_elements wifi = PageFactory.initElements(driver, WiFi_setting_page_elements.class);
        Advanced_Settings_Page adv = PageFactory.initElements(driver, Advanced_Settings_Page.class);
        System_Tests_page sys = PageFactory.initElements(driver, System_Tests_page.class);
        Dual_path_page_elements dual = PageFactory.initElements(driver, Dual_path_page_elements.class);
        Settings_Page set = PageFactory.initElements(driver, Settings_Page.class);
        navigate_to_Settings_page();
        wifi.OK.click();
        set.ADVANCED_SETTINGS.click();
        enter_default_user_code();
        adv.SYSTEM_TESTS.click();
        sys.DUAL_PATH_TEST.click();
        Thread.sleep(1000);
        element_verification(dual.chkbox_result_text, "Dual_path_checkbox_text");
        Thread.sleep(6000);
        dual.start_button.click();
        element_verification(dual.Test_result, "Dual_path_Test_result_text");
        logger.info("SASST_029 Pass:Dual Path Wi-Fi test won't pass if Dual-Path control is disabled and Wi-Fi is disconnected.");}


    @AfterTest
    public void tearDown () throws IOException, InterruptedException {
        log.endTestCase(page_name);
        driver.quit(); }

    @AfterMethod
    public void webDriverQuit(){
        adc.driver1.quit();
    }
    }
