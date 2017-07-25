package ADC_Sanity_Test;

import ADC.ADC;
import Panel.*;
import Sensors.Sensors;
import jxl.read.biff.BiffException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;

public class Misc extends Setup{

    Sensors sensors = new Sensors();
    ADC adc = new ADC();
    String keyfobAway = "04 04";
    String keyfobStay = "04 01";
    String keyfobDisarm = "08 01";

    private int Normal_Exit_Delay = 30000;
    private String open = "06 00";
    private String close = "04 00";

    public Misc() throws IOException, BiffException {}

    public String getAccountId () throws IOException {
        String accountId = null;
        if (get_UDID().equals("8ebdbc76")) {
            accountId = "4679473";
            return accountId;
        } else if (get_UDID().equals("8ebdbcf6")) {
            accountId = "5432189";
            return accountId;
        }
        return  accountId;
    }

    @BeforeTest
    public void capabilities_setup() throws Exception {
        setup_driver(get_UDID(),"http://127.0.1.1", "4723");
}
    @BeforeMethod
    public  void webDriver(){
        adc.webDriverSetUp();
    }
// Customer ID 5432189 //

    public void AutoStay_Setting () throws InterruptedException {
       Security_Arming_Page arming = PageFactory.initElements(driver, Security_Arming_Page.class);
       Advanced_Settings_Page adv = PageFactory.initElements(driver, Advanced_Settings_Page.class);
       Installation_Page inst = PageFactory.initElements(driver, Installation_Page.class);
       adv.INSTALLATION.click();
       inst.SECURITY_AND_ARMING.click();
       Thread.sleep(3000);
       swipe_vertical();
       arming.Auto_Stay.click();
       Thread.sleep(2000);
   }


    @Test
    public void addSensors() throws IOException, InterruptedException {
        Thread.sleep(2000);
        sensors.add_primary_call(38, 1, 6619386, 102);
        sensors.add_primary_call(39, 6, 6619387, 102);
        sensors.add_primary_call(40, 4, 6619388, 102);

        adc.New_ADC_session(getAccountId());
        Thread.sleep(2000);
        adc.driver1.findElement(By.partialLinkText("Sensors")).click();
        Thread.sleep(2000);
        adc.Request_equipment_list();
        Thread.sleep(2000);
    }
    ////////***** ARM AWAY BY KEY FOB *****/////////
    @Test (dependsOnMethods = {"addSensors"}, retryAnalyzer = RetryAnalizer.class)
    public void ArmAway_by_keyfob_group_1() throws Exception {
        Home_Page home = PageFactory.initElements(driver, Home_Page.class);
        logger.info("****************************KEYFOB****************************");
        logger.info("Keyfob group 1: can ArmStay-ArmAway-Disarm, panic = Police");
        logger.info("Arm Away by keyfob");
        Thread.sleep(10000);
        sensors.primary_call("65 00 AF", keyfobAway);
        Thread.sleep(3000);
        verify_armaway();
        Thread.sleep(3000);
        home.ArwAway_State.click();
        enter_default_user_code();

        /*** ADC website verification ***/

       adc.New_ADC_session(getAccountId());
        adc.driver1.findElement(By.partialLinkText("History")).click();
        Thread.sleep(10000);
        WebElement history_message = adc.driver1.findElement(By.xpath("//*[contains(text(), 'Event Reporting Code Message : Keyfob 38 Arm Away')]"));
        try {
            Assert.assertTrue(history_message.isDisplayed());{
                System.out.println("Pass: message is displayed " + history_message.getText());
            }
        }finally {System.out.println("Everything is BAD!!!!");}
    }

    @Test (dependsOnMethods = {"addSensors"}, retryAnalyzer = RetryAnalizer.class)
    public void ArmAway_by_keyfob_group_6() throws Exception {
        logger.info("****************************KEYFOB****************************");
        logger.info("Keyfob group 1: can ArmStay-ArmAway-Disarm, panic = Police");
        logger.info("Arm Away by keyfob");
        Thread.sleep(10000);
        sensors.primary_call("65 00 BF", keyfobAway);
        Thread.sleep(4000);
        verify_armaway();

        /*** ADC website verification ***/

        adc.New_ADC_session(getAccountId());
        adc.driver1.findElement(By.partialLinkText("History")).click();
        Thread.sleep(10000);
        WebElement history_message = adc.driver1.findElement(By.xpath("//*[contains(text(), 'Panel Armed Away by Keyfob(39)')]"));
        try {
            Assert.assertTrue(history_message.isDisplayed());{
                System.out.println("Pass: message is displayed " + history_message.getText());
            }
        }finally {
        }
        DISARM();
    }

    @Test (dependsOnMethods = {"addSensors"}, retryAnalyzer = RetryAnalizer.class)
    public void ArmAway_by_keyfob_group_4() throws Exception {
        logger.info("****************************KEYFOB****************************");
        logger.info("Keyfob group 1: can ArmStay-ArmAway-Disarm, panic = Police");
        logger.info("Arm Away by keyfob");
        Thread.sleep(10000);
        sensors.primary_call("65 00 CF", keyfobAway);
        Thread.sleep(4000);
        verify_armaway();

        /*** ADC website verification ***/

        adc.New_ADC_session(getAccountId());
        adc.driver1.findElement(By.partialLinkText("History")).click();
        Thread.sleep(10000);
        WebElement history_message = adc.driver1.findElement(By.xpath("//*[contains(text(), 'Panel Armed Away by Keyfob(40)')]"));
        try {
            Assert.assertTrue(history_message.isDisplayed());{
                System.out.println("Pass: message is displayed " + history_message.getText());
            }
        }finally {
        }
        DISARM();
    }

////////***** ARM STAY BY KEY FOB *****/////////

    @Test (dependsOnMethods = {"addSensors"}, retryAnalyzer = RetryAnalizer.class)
    public void ArmStay_by_keyfob_group_1() throws Exception {
        logger.info("****************************KEYFOB****************************");
        logger.info("Keyfob group 1: can ArmStay-ArmAway-Disarm, panic = Police");
        logger.info("Arm Stay by keyfob");
        Thread.sleep(10000);
        sensors.primary_call("65 00 AF", keyfobStay);
        Thread.sleep(4000);
        verify_armaway();

        /*** ADC website verification ***/

        adc.New_ADC_session(getAccountId());
        adc.driver1.findElement(By.partialLinkText("History")).click();
        Thread.sleep(10000);
        WebElement history_message = adc.driver1.findElement(By.xpath("//*[contains(text(), 'Panel Armed Stay by Keyfob(38)')]"));
        try {
            Assert.assertTrue(history_message.isDisplayed());{
                System.out.println("Pass: message is displayed " + history_message.getText());
            }
        }finally {
        }
        DISARM();
    }
    @Test (dependsOnMethods = {"addSensors"}, retryAnalyzer = RetryAnalizer.class)
    public void ArmStay_by_keyfob_group_6() throws Exception {
        logger.info("****************************KEYFOB****************************");
        logger.info("Keyfob group 1: can ArmStay-ArmAway-Disarm, panic = Police");
        logger.info("Arm Stay by keyfob");
        Thread.sleep(10000);
        sensors.primary_call("65 00 BF", keyfobStay);
        Thread.sleep(4000);
        verify_armaway();

        /*** ADC website verification ***/

        adc.New_ADC_session(getAccountId());
        adc.driver1.findElement(By.partialLinkText("History")).click();
        Thread.sleep(10000);
        WebElement history_message = adc.driver1.findElement(By.xpath("//*[contains(text(), 'Panel Armed Stay by Keyfob(39)')]"));
        try {
            Assert.assertTrue(history_message.isDisplayed());{
                System.out.println("Pass: message is displayed " + history_message.getText());
            }
        }finally {
        }
        DISARM();
    }

    @Test (dependsOnMethods = {"addSensors"}, retryAnalyzer = RetryAnalizer.class)
    public void ArmStay_by_keyfob_group_4() throws Exception {
        logger.info("****************************KEYFOB****************************");
        logger.info("Keyfob group 1: can ArmStay-ArmAway-Disarm, panic = Police");
        logger.info("Arm Stay by keyfob");
        Thread.sleep(10000);
        sensors.primary_call("65 00 CF", keyfobStay);
        Thread.sleep(4000);
        verify_armaway();

        /*** ADC website verification ***/

        adc.New_ADC_session(getAccountId());
        adc.driver1.findElement(By.partialLinkText("History")).click();
        Thread.sleep(10000);
        WebElement history_message = adc.driver1.findElement(By.xpath("//*[contains(text(), 'Panel Armed Stay by Keyfob(40)')]"));
        try {
            Assert.assertTrue(history_message.isDisplayed());{
                System.out.println("Pass: message is displayed " + history_message.getText());
            }
        }finally {
        }
        DISARM();
    }
////////***** DISARM BY KEY FOB *****/////////
   /* @Test (dependsOnMethods = {"addSensors"}, retryAnalyzer = RetryAnalizer.class)
    public void Disarm_by_keyfob_group_1 () throws Exception {
        logger.info("Disarm DISARM BY 1-group KEY FOB");
        ARM_AWAY();
        Thread.sleep(10000);
        Thread.sleep(2000);
        verify_armaway();

        /*** ADC website verification ***/
       /* adc.New_ADC_session(getAccountId());
        adc.driver1.findElement(By.partialLinkText("History")).click();
        Thread.sleep(10000);
        WebElement history_message = adc.driver1.findElement(By.xpath("//*[contains(text(), 'Panel Armed Away')]"));
        try {
           Assert.assertTrue(history_message.isDisplayed());{
                System.out.println("Pass: message is displayed " + history_message.getText());
           }
        }finally {
        }
        logger.info("Press disarm button");
        sensors.primary_call("65 00 AF", keyfobDisarm);
        Thread.sleep(2000);
        verify_armstay();

        /*** ADC website verification ***/
       /* adc.New_ADC_session(getAccountId());
        adc.driver1.findElement(By.partialLinkText("History")).click();
        Thread.sleep(10000);
        WebElement history_message1 = adc.driver1.findElement(By.xpath("//*[contains(text(), 'Panel Disarmed by Keyfob(38)')]"));
        try {
            Assert.assertTrue(history_message1.isDisplayed());{
                System.out.println("Pass: message is displayed " + history_message.getText());
            }
        }finally {
        }

       Thread.sleep(2000);
   } */
 //}




        @AfterTest
        public void tearDown() throws IOException, InterruptedException {
            driver.quit();

        }
    @AfterMethod
    public void webDriverQuit(){
        adc.driver1.quit();
    }
    }




