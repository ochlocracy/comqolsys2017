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

public class Duress_Authentication_Test_Grid {
    Setup1 s = new Setup1();
    String page_name = "Duress Authentication testing";
    Logger logger = Logger.getLogger(page_name);
    private int delay = 15;

    public Duress_Authentication_Test_Grid() throws IOException, BiffException {}
    @Parameters({"deviceName_", "applicationName_", "UDID_", "platformVersion_", "URL_", "PORT_" })
    @BeforeClass
    public void setUp(String deviceName_, String applicationName_, String UDID_, String platformVersion_, String URL_, String PORT_) throws Exception {
        s.setCapabilities(URL_);
        s.setup_logger(page_name);
    }
    @Test
    public void Verify_Duress_Authentication_works() throws Exception {
        Settings_Page settings = PageFactory.initElements(s.getDriver(), Settings_Page.class);
        Security_Arming_Page arming = PageFactory.initElements(s.getDriver(), Security_Arming_Page.class);
        Advanced_Settings_Page adv = PageFactory.initElements(s.getDriver(), Advanced_Settings_Page.class);
        Installation_Page inst = PageFactory.initElements(s.getDriver(), Installation_Page.class);
        Home_Page home = PageFactory.initElements(s.getDriver(), Home_Page.class);
        Thread.sleep(2000);
        logger.info("Verify system can not be DISARMED with Duress code when the setting is disabled");
        s.ARM_STAY();
        Thread.sleep(2000);
        home.DISARM.click();
        home.Nine.click();
        home.Nine.click();
        home.Nine.click();
        home.Eight.click();
        if(settings.Invalid_User_Code.isDisplayed()){
            logger.info("Pass: Duress code does not work");}
        Thread.sleep(1000);
        s.enter_default_user_code();
        s.navigate_to_Advanced_Settings_page();
        adv.INSTALLATION.click();
        inst.SECURITY_AND_ARMING.click();
        arming.Duress_Authentication.click();
        s.swipe_vertical();
        Thread.sleep(1000);
        arming.Auto_Stay.click();
        Thread.sleep(3000);
        home.Back_button.click();
        Thread.sleep(3000);
        home.Back_button.click();
        Thread.sleep(3000);
        adv.USER_MANAGEMENT.click();
        logger.info("Verify Duress User appeared");
        s.getDriver().findElement(By.xpath("//android.widget.TextView[@text='Duress']")).isDisplayed();
        Thread.sleep(2000);
        settings.Home_button.click();
        Thread.sleep(2000);
        logger.info("Verify system can be DISARMED with Duress code when the setting is enabled");
        logger.info("Arm Stay the system");
        s.ARM_STAY();
        Thread.sleep(2000);
        home.DISARM.click();
        home.Nine.click();
        home.Nine.click();
        home.Nine.click();
        home.Eight.click();
        s.verify_disarm();
        Thread.sleep(1000);
        logger.info("Arm Away the system");
        s.ARM_AWAY(delay);
        home.ArwAway_State.click();
        home.Nine.click();
        home.Nine.click();
        home.Nine.click();
        home.Eight.click();
        s.verify_disarm();
        Thread.sleep(2000);
        s.navigate_to_Advanced_Settings_page();
        adv.INSTALLATION.click();
        inst.SECURITY_AND_ARMING.click();
        arming.Duress_Authentication.click();
        s.swipe_vertical();
        Thread.sleep(1000);
        arming.Auto_Stay.click();
        Thread.sleep(2000);
    }
    @AfterClass
    public void tearDown () throws IOException, InterruptedException {
        s.log.endTestCase(page_name);
        s.getDriver().quit();
    }
}