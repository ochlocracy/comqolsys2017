package Settings;

import Panel.*;
import jxl.read.biff.BiffException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;

public class Installer_Code_Test_Grid {
    Setup1 s = new Setup1();
    String page_name = "Installer Code change";
    Logger logger = Logger.getLogger(page_name);

    public Installer_Code_Test_Grid() throws IOException, BiffException {}
    @Parameters({"deviceName_", "applicationName_", "UDID_", "platformVersion_", "URL_", "PORT_" })
    @BeforeClass
    public void setUp(String deviceName_, String applicationName_, String UDID_, String platformVersion_, String URL_, String PORT_) throws Exception {
        s.setCapabilities(URL_);
        s.setup_logger(page_name, UDID_);
    }
    @Test
    public void Verify_Installer_Code_Change() throws Exception {
    Settings_Page settings =  PageFactory.initElements(s.getDriver(), Settings_Page.class);
    Security_Arming_Page arming = PageFactory.initElements(s.getDriver(), Security_Arming_Page.class);
    Advanced_Settings_Page adv = PageFactory.initElements(s.getDriver(), Advanced_Settings_Page.class);
    Installation_Page inst = PageFactory.initElements(s.getDriver(), Installation_Page.class);
    User_Management_Page user = PageFactory.initElements(s.getDriver(), User_Management_Page.class);
        s.navigate_to_Advanced_Settings_page();
        adv.INSTALLATION.click();
        inst.SECURITY_AND_ARMING.click();
        arming.Installer_Code.click();
        user.Add_User_Name_field.clear();
        logger.info("Changing Installer name");
        user.Add_User_Name_field.sendKeys("NewInstall");
        user.Add_User_Code_field.clear();
        logger.info("Changing Installer password");
        user.Add_User_Code_field.sendKeys("5555");
        s.getDriver().hideKeyboard();
        user.Add_Confirm_User_Code_field.click();
        user.Add_Confirm_User_Code.clear();
        user.Add_Confirm_User_Code.sendKeys("5555");
        user.User_Management_Save.click();
        Thread.sleep(2000);
        settings.Back_button.click();
        Thread.sleep(1000);
        settings.Back_button.click();
        Thread.sleep(2000);
        adv.USER_MANAGEMENT.click();
        logger.info("Verify Installer name changed");
        s.getDriver().findElement(By.xpath("//android.widget.TextView[@text='NewInstall']")).isDisplayed();
        Thread.sleep(2000);
        settings.Back_button.click();
        Thread.sleep(2000);
        settings.Back_button.click();
        Thread.sleep(2000);
        logger.info("Verify old Installer code does not work");
        settings.ADVANCED_SETTINGS.click();
        settings.One.click();
        settings.One.click();
        settings.One.click();
        settings.One.click();
        if(settings.Invalid_User_Code.isDisplayed()){
        logger.info("Pass: old Installer code does not work");}
        Thread.sleep(2000);
        logger.info("Verify new Installer code works");
        settings.Five.click();
        settings.Five.click();
        settings.Five.click();
        settings.Five.click();
        if(adv.INSTALLATION.isDisplayed()){
        logger.info("Pass: new Installer code works as expected");}
        adv.INSTALLATION.click();
        inst.SECURITY_AND_ARMING.click();
        Thread.sleep(1000);
        arming.Installer_Code.click();
        Thread.sleep(2000);
        user.Add_User_Name_field.clear();
        user.Add_User_Name_field.sendKeys("Installer");
        user.Add_User_Code_field.clear();
        user.Add_User_Code_field.sendKeys("1111");
        s.getDriver().hideKeyboard();
        user.Add_Confirm_User_Code_field.click();
        user.Add_Confirm_User_Code.clear();
        user.Add_Confirm_User_Code.sendKeys("1111");
        user.User_Management_Save.click();
        Thread.sleep(2000);
}
    @AfterClass
    public void tearDown () throws IOException, InterruptedException {
        s.log.endTestCase(page_name);
        s.getDriver().quit();
    }
}