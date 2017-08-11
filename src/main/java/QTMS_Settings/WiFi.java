package QTMS_Settings;


import Cellular.WiFi_setting_page_elements;
import Panel.Advanced_Settings_Page;
import Panel.Settings_Page;
import Panel.Setup;
import Panel.Home_Page;
import jxl.read.biff.BiffException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import Panel.PanelInfo_ServiceCalls;
import java.io.IOException;
import java.util.List;

public class WiFi extends Setup{
    public WiFi() throws IOException, BiffException {}

    String page_name = "QTMS WiFI testing";
    Logger logger = Logger.getLogger(page_name);
    PanelInfo_ServiceCalls servcall = new PanelInfo_ServiceCalls();

    public void swipe_vertical1() throws InterruptedException {
        int starty = 534;
        int endy = 250;
        int startx = 540;
        driver.swipe(startx, starty, startx, endy, 3000);
        Thread.sleep(2000);}

    @BeforeTest
    public void capabilities_setup() throws Exception {
        setup_driver(get_UDID(), "http://127.0.1.1", "4723");
        setup_logger(page_name);  }

    /*** WiFi is connected ***/
    @Test
    public void SASW_004_SASW_005_SASW_006_SASW_009SASW_0010_SASW_011_SASW_012() throws Exception {
        Advanced_Settings_Page adv = PageFactory.initElements(driver, Advanced_Settings_Page.class);
        WiFi_setting_page_elements wifi = PageFactory.initElements(driver, WiFi_setting_page_elements.class);
        Home_Page home =PageFactory.initElements(driver, Home_Page.class);
        navigate_to_Advanced_Settings_page();
        element_verification(adv.WI_FI, "WiFi icon");
        adv.WI_FI.click();
        logger.info("SASW_004 Pass: WI-FI icon is present and enabled under the Advanced settings page");
        logger.info("SASW_005 Pass: User can access Wi-Fi page in Disarm mode");
        Thread.sleep(1000);
        List<WebElement> li = driver.findElements(By.id("android:id/title"));
        String value1 = li.get(0).getText();
        logger.info("WiFi network name: " + value1);
        li.get(0).click();
        Thread.sleep(1000);
        List<WebElement> li11 = driver.findElements(By.id("com.qolsys:id/name"));
        for (int i = 0; i < li11.size(); i++){}
        List<WebElement> li1 = driver.findElements(By.id("com.qolsys:id/value"));
        for (int i = 0; i < li1.size(); i++) {
        logger.info(li11.get(i).getText()+ " : " + li1.get(i).getText());}
        swipe_vertical1();
        Thread.sleep(1000);
        List<WebElement> n_li11 = driver.findElements(By.id("com.qolsys:id/name"));
        for (int i = 0; i < n_li11.size(); i++) { }
        List<WebElement> n_li1 = driver.findElements(By.id("com.qolsys:id/value"));
        logger.info(n_li11.get(4).getText()+ " : " + n_li1.get(4).getText());
         logger.info("SASW_006 Pass: IP Address, Status, Signal Strength, Link Speed, Frequency, and Security information are displayed");
        wifi.DONE.click();
        element_verification(wifi.Home_button, "Home Button");
        element_verification(wifi.Back_button, "Back Button");
        element_verification(wifi.Emergency_Button,"Emergency_Button");
        logger.info("SASW_007 Pass: 'Back', 'Emergency' and 'Home' buttons are present on the footer");
        wifi.Emergency_Button.click();
        element_verification(wifi.Emergency_page,"Emergency_page");
        logger.info("SASW_011 Pass: User is taken to the Emergency page");
        swipe_left();
        navigate_to_Advanced_Settings_page();
        adv.WI_FI.click();
        wifi.Home_button.click();
        element_verification(home.Disarmed_text,"Home page");
        logger.info("SASW_012 Pass: User is taken to the Home page");
        navigate_to_Advanced_Settings_page();
        adv.WI_FI.click();
        wifi.Back_button.click();
        element_verification(adv.WI_FI, "WiFi icon");
        logger.info("SASW_010 Pass: User is taken back to the previous page."); }

/*** WiFi setting is disabled, One network has known ***/
@Test(priority = 2)
public void Connect_toKnown_WiFiNetwork_SASW_001() throws Exception {
    Advanced_Settings_Page adv = PageFactory.initElements(driver, Advanced_Settings_Page.class);
    WiFi_setting_page_elements wifi = PageFactory.initElements(driver, WiFi_setting_page_elements.class);
    Settings_Page settings = PageFactory.initElements(driver, Settings_Page.class);
    navigate_to_Settings_page();
    Thread.sleep(1000);
  if (wifi.Warning_message.isDisplayed()){
      logger.info("Warning message: " + wifi.Warning_message.getText());
      wifi.OK.click();
   }
      else if (!wifi.Warning_message.isDisplayed()){
         System.out.println("Incorrect precondition");}
    settings.ADVANCED_SETTINGS.click();
    enter_default_user_code();
    adv.WI_FI.click();
    Thread.sleep(1000);
    element_verification(wifi.Checkbox, "Checkbox");
    wifi.Checkbox.click();
    if (wifi.Checkbox.isEnabled()){
        logger.info("Pass: Checkbox is enabled");
            }
    else if (!wifi.Checkbox.isEnabled()) {
        System.out.println("Incorrect precondition");}
    Thread.sleep(4000);
    servcall.get_WiFi();
    List<WebElement> li = driver.findElements(By.id("android:id/title"));
    String value1 = li.get(0).getText();
    logger.info("WiFi network name: " + value1);
    logger.info("SASW 001 Pass: User is able to connect to the known wi-fi network. ");
 }
    /*** WiFi is connected ***/
    @Test (priority = 1)
    public void WiFiCheckbox_uncheck_SASW_002() throws Exception {
        Advanced_Settings_Page adv = PageFactory.initElements(driver, Advanced_Settings_Page.class);
        WiFi_setting_page_elements wifi = PageFactory.initElements(driver, WiFi_setting_page_elements.class);
        Settings_Page settings = PageFactory.initElements(driver, Settings_Page.class);
        navigate_to_Settings_page();
        Thread.sleep(1000);
        settings.ADVANCED_SETTINGS.click();
        enter_default_user_code();
        adv.WI_FI.click();
        Thread.sleep(1000);
        wifi.Checkbox.click();
        Thread.sleep(5000);
        servcall.get_WiFi();
        logger.info("SASW 002 Pass: user can not connect to the wifi network, network notification grayed out");}

    /*** WiFi is connected ***/
    @Test// (priority = 3)
    public void Forgot_wifi_credentials_SASW_003() throws Exception {
        Advanced_Settings_Page adv = PageFactory.initElements(driver, Advanced_Settings_Page.class);
        WiFi_setting_page_elements wifi = PageFactory.initElements(driver, WiFi_setting_page_elements.class);
        Settings_Page settings = PageFactory.initElements(driver, Settings_Page.class);
        navigate_to_Settings_page();
        Thread.sleep(1000);
        settings.ADVANCED_SETTINGS.click();
        enter_default_user_code();
        adv.WI_FI.click();
        Thread.sleep(1000);
        List<WebElement> li = driver.findElements(By.id("android:id/title"));
        String value1 = li.get(0).getText();
        logger.info("WiFi network name: " + value1);
        li.get(0).click();
        logger.info("Press Forget button");
        wifi.FORGET.click();
        Thread.sleep(5000);
        servcall.get_WiFi_name();
        logger.info(" SASW 003 Pass: user is able to forget the selected network");
    }
    @AfterTest
    public void tearDown () throws IOException, InterruptedException {
        log.endTestCase(page_name);
        driver.quit();}

}
