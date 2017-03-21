package Panel;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.IOException;

public class Security_Arming_Page_Test extends Setup{

    String page_name = "Security and Arming page testing";
    Logger logger = Logger.getLogger(page_name);

    @BeforeMethod
    public void capabilities_setup() throws Exception {
        setup_driver("8ebdbc76", "http://127.0.1.1", "4723");
        setup_logger(page_name);
    }

    @Test
    public void Check_all_elements_on_Security_Arming_page() throws Exception {
        Security_Arming_Page arming = PageFactory.initElements(driver, Security_Arming_Page.class);
        Advanced_Settings_Page adv = PageFactory.initElements(driver, Advanced_Settings_Page.class);
        Installation_Page inst = PageFactory.initElements(driver, Installation_Page.class);
        navigate_to_Advanced_Settings_page();
        adv.INSTALLATION.click();
        inst.SECURITY_AND_ARMING.click();

        logger.info("Verifying elements on the page...");
        Thread.sleep(1000);
        if (arming.Dealer_Code_summery.isDisplayed()) {
            logger.info("Pass: Correct Dealer Code summery");
        }
        if (arming.Installer_Code_summery.isDisplayed()) {
            logger.info("Pass: Correct Installer Code summery");
        }

        if (arming.Duress_Authentication_summery.isDisplayed()) {   //DISABLED by default
            logger.info("Pass: Correct Duress Authentication summery when disabled");
        }
        arming.Duress_Authentication.click();
        if (arming.Duress_Authentication_summery_enabled.isDisplayed()) {
            logger.info("Pass: Correct Duress Authentication summery when enabled");
        }
        arming.Duress_Authentication.click();

        if (arming.Secure_Arming_summery.isDisplayed()) {  //DISABLED by default
            logger.info("Pass: Correct Secure Arming summery when disabled");
        }
        arming.Secure_Arming.click();
        if (arming.Secure_Arming_summery.isDisplayed()) {
            logger.info("Pass: Correct Secure Arming summery when enabled");
        }
        arming.Secure_Arming.click();
        swipe_vertical();

        if (arming.Refuse_Arming_When_Battery_Low_summery.isDisplayed()) {  //DISABLED by default
            logger.info("Pass: Correct Refuse Arming When Battery Low summery when disabled");
        }
        arming.Refuse_Arming_When_Battery_Low.click();
        if (arming.Refuse_Arming_When_Battery_Low_summery.isDisplayed()) {
            logger.info("Pass: Correct Refuse Arming When Battery Low summery when enabled");
        }
        arming.Refuse_Arming_When_Battery_Low.click();

        if (arming.Auto_Bypass_summery.isDisplayed()) {  //ENABLED by default
            logger.info("Pass: Correct Auto Bypass summery when enabled");
        }
        arming.Auto_Bypass.click();
        if (arming.Auto_Bypass_summery.isDisplayed()) {
            logger.info("Pass: Correct Auto Bypass summery when disabled");
        }
        arming.Auto_Bypass.click();

        if (arming.Auto_Stay_summery.isDisplayed()) {  //ENABLED by default
            logger.info("Pass: Correct Auto Stay summery when enabled");
        }
        arming.Auto_Stay.click();
        if (arming.Auto_Stay_summery.isDisplayed()) {
            logger.info("Pass: Correct Auto Stay summery when disabled");
        }
        arming.Auto_Stay.click();
        swipe_vertical();

        if (arming.Arm_Stay_No_Delay_summery.isDisplayed()) {  //ENABLED by default
            logger.info("Pass: Correct Auto Stay No Delay summery when enabled");
        }
        arming.Arm_Stay_No_Delay.click();
        if (arming.Arm_Stay_No_Delay_summery.isDisplayed()) {
            logger.info("Pass: Correct Auto Stay No Delay summery when disabled");
        }
        arming.Arm_Stay_No_Delay.click();

        if (arming.Auto_Exit_Time_Extension_summery.isDisplayed()) {  //ENABLED by default
            logger.info("Pass: Correct Auto Exit Time Extension summery when enabled");
        }
        arming.Auto_Exit_Time_Extension.click();
        if (arming.Auto_Exit_Time_Extension_summery.isDisplayed()) {
            logger.info("Pass: Correct Auto Exit Time Extension summery when disabled");
        }
        arming.Auto_Exit_Time_Extension.click();

        if (arming.Keyfob_Instant_Arming_summery.isDisplayed()) {  //ENABLED by default
            logger.info("Pass: Correct Keyfob Instant Arming summery when enabled");
        }
        arming.Keyfob_Instant_Arming.click();
        if (arming.Keyfob_Instant_Arming_summery.isDisplayed()) {
            logger.info("Pass: Correct Keyfob Instant Arming summery when disabled");
        }
        arming.Keyfob_Instant_Arming.click();
        swipe_vertical();

        if (arming.Keyfob_Alarm_Disarm_summery.isDisplayed()) {  //ENABLED by default
            logger.info("Pass: Correct Keyfob Alarm Disarm summery when disabled");
        }
        arming.Keyfob_Alarm_Disarm.click();
        if (arming.Keyfob_Alarm_Disarm_summery_enabled.isDisplayed()) {
            logger.info("Pass: Correct Keyfob Alarm Disarm summery when enabled");
        }
        arming.Keyfob_Alarm_Disarm.click();

        if (arming.Keyfob_Disarming_summery.isDisplayed()) { //ENABLED by default
            logger.info("Pass: Correct Keyfob Disarming summery when enabled");
        }
        arming.Keyfob_Disarming.click();
        if (arming.Keyfob_Disarming_summery.isDisplayed()) {
            logger.info("Pass: Correct Keyfob Disarming summery when disabled");
        }
        arming.Keyfob_Disarming.click();

        if (arming.Allow_Master_Code_To_Access_Security_and_Arming_summery.isDisplayed()) { //DISABLED by default
            logger.info("Pass: Correct Allow Master Code To Access Security and Arming summery when disabled");
        }
        arming.Allow_Master_Code_To_Access_Security_and_Arming.click();
        if (arming.Allow_Master_Code_To_Access_Security_and_Arming_summery_enabled.isDisplayed()) {
            logger.info("Pass: Correct Allow Master Code To Access Security and Arming summery when enabled");
        }
        arming.Allow_Master_Code_To_Access_Security_and_Arming.click();
        swipe_vertical();

        if (arming.Normal_Entry_Delay.isDisplayed()) {
            logger.info("Pass: Normal Entry Delay is present");
        }
        if (arming.Normal_Exit_Delay.isDisplayed()) {
            logger.info("Pass: Normal Exit Delay is present");
        }
        if (arming.Long_Entry_Delay.isDisplayed()) {
            logger.info("Pass: Long Entry Delay is present");
        }
        if (arming.Long_Exit_Delay.isDisplayed()) {
            logger.info("Pass: Long Exit Delay is present");
        }
    }
    @AfterMethod
    public void tearDown () throws IOException, InterruptedException {
        log.endTestCase(page_name);
        driver.quit();
    }
}
