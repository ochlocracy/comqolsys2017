package ADC_Sanity_Test;

import ADC.ADC;

import Panel.Setup;
import Sensors.Sensors;
import jxl.read.biff.BiffException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;

 /*** PRECONDITIONS: Disable SIA limits, set Entry-Exit Delay time to 30, 31, 32, 33 sec; Disable ArmStay No-Delay setting ***/

public class ArmedStay_Contact extends Setup {

    String page_name = "Arm Stay mode";
    Logger logger = Logger.getLogger(page_name);
    Sensors sensors = new Sensors();
    ADC adc = new ADC();
    String AccountID = adc.getAccountId();

    private int Normal_Exit_Delay = 30000;
    private String open = "06 00";
    private String close = "04 00";
    private String tamper = "01 00";

    public ArmedStay_Contact() throws IOException, BiffException {}

    public void add_primary_call(int zone, int group, int sensor_dec, int sensor_type) throws IOException {
        String add_primary = " shell service call qservice 50 i32 " + zone + " i32 " + group + " i32 " + sensor_dec + " i32 " + sensor_type;
        rt.exec(adbPath + add_primary);
        // shell service call qservice 50 i32 2 i32 10 i32 6619296 i32 1
    }
     public void delete_from_primary(int zone) throws IOException, InterruptedException {
         String deleteFromPrimary = " shell service call qservice 51 i32 " + zone;
         rt.exec(adbPath + deleteFromPrimary);
         System.out.println(deleteFromPrimary);}

    @BeforeTest
    public void capabilities_setup() throws Exception {
        setup_driver(get_UDID(),"http://127.0.1.1", "4723");
        setup_logger(page_name);
        setArmStay_NoDelay("Disable");
        setAutoStay("Disable");
    }
    @BeforeMethod
    public  void webDriver(){
        adc.webDriverSetUp();
    }

    @Test
    public void addSensors() throws IOException, InterruptedException {
        Thread.sleep(2000);
        add_primary_call(1, 10, 6619296, 1);
        add_primary_call(2, 12, 6619297, 1);
        add_primary_call(3, 13, 6619298, 1);
        add_primary_call(4, 14, 6619299, 1);
        add_primary_call(5, 16, 6619300, 1);
        add_primary_call(6, 8, 6619301, 1);
        add_primary_call(7, 9, 6619302, 1);
        add_primary_call(8, 25, 6619303, 1);

        adc.New_ADC_session(adc.getAccountId());
        Thread.sleep(2000);
        adc.driver1.findElement(By.partialLinkText("Sensors")).click();
        Thread.sleep(2000);
        adc.Request_equipment_list();
    }
     public void ArmStay_Open_Close_sensor_during_Exit_Delay(int group, String DLID, String element_to_verify ) throws Exception {
         logger.info("ArmStay -Open/Close Group " +group + " contact sensor during exit delay");
         ARM_STAY();
         Thread.sleep(10000);
         logger.info("Open/Close a sensor");
         sensors.primary_call(DLID, open);
         Thread.sleep(2000);
         sensors.primary_call(DLID, close);
         Thread.sleep(Normal_Exit_Delay);
         verify_armstay();
         Thread.sleep(10000);
         DISARM();
         /*** ADC website verification ***/
         adc.New_ADC_session(AccountID);
         adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("History"))).click();
         Thread.sleep(10000);
         try {
             WebElement history_message = adc.driver1.findElement(By.xpath(element_to_verify));
             Assert.assertTrue(history_message.isDisplayed());{
                 logger.info("Pass: message is displayed " + history_message.getText());
             }
         }catch (Exception e){
             logger.info("***No such element found!***");
         }
         Thread.sleep(5000);
     }
     public void ArmStay_Open_Close_sensor_during_Exit_Delay_Alarm(int group, String DLID, String element_to_verify ) throws Exception {
         logger.info("ArmStay -Open/Close Group " +group + " contact sensor during exit delay");
         ARM_STAY();
         Thread.sleep(10000);
         logger.info("Open/Close a sensor");
         sensors.primary_call(DLID, open);
         Thread.sleep(2000);
         sensors.primary_call(DLID, close);
         Thread.sleep(Normal_Exit_Delay);
         verify_in_alarm();
         Thread.sleep(2000);
         enter_default_user_code();
         /*** ADC website verification ***/
         adc.New_ADC_session(AccountID);
         adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("History"))).click();
         Thread.sleep(10000);
         try {
             WebElement history_message = adc.driver1.findElement(By.xpath(element_to_verify));
             Assert.assertTrue(history_message.isDisplayed());{
                 logger.info("Pass: message is displayed " + history_message.getText());
             }
         }catch (Exception e){
             logger.info("***No such element found!***");
         }
         Thread.sleep(5000);
     }
     public void ArmStay_Open_Close_sensor(int group, String DLID, String element_to_verify, String element_to_verify1) throws Exception {
         logger.info("ArmStay -Open/Close Group " +group + " contact sensor during exit delay");
         ARM_STAY();
         Thread.sleep(35000);
         logger.info("Open/Close a sensor");
         sensors.primary_call(DLID, open);
         Thread.sleep(2000);
         sensors.primary_call(DLID, close);
         Thread.sleep(5000);
         enter_default_user_code();
         verify_disarm();
         /*** ADC website verification ***/
         adc.New_ADC_session(AccountID);
         adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("History"))).click();
         Thread.sleep(10000);
         try {
             WebElement history_message = adc.driver1.findElement(By.xpath(element_to_verify));
             Assert.assertTrue(history_message.isDisplayed());{
                 logger.info("Pass: message is displayed " + history_message.getText());
             }
         }catch (Exception e){
             logger.info("***No such element found!***");
         }
         try {
             WebElement history_message = adc.driver1.findElement(By.xpath(element_to_verify1));
             Assert.assertTrue(history_message.isDisplayed());{
                 logger.info("Pass: message is displayed " + history_message.getText());
             }
         }catch (Exception e){
             logger.info("***No such element found!***");
         }
         Thread.sleep(5000);
     }
     public void ArmStay_Open_Close_sensor_ArmStay(int group, String DLID, String element_to_verify, String element_to_verify1) throws Exception {
         logger.info("ArmStay -Open/Close Group " +group + " contact sensor during exit delay");
         ARM_STAY();
         Thread.sleep(35000);
         logger.info("Open/Close a sensor");
         sensors.primary_call(DLID, open);
         Thread.sleep(2000);
         sensors.primary_call(DLID, close);
         Thread.sleep(5000);
         verify_armstay();
         Thread.sleep(2000);
         DISARM();
         /*** ADC website verification ***/
         adc.New_ADC_session(AccountID);
         adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("History"))).click();
         Thread.sleep(10000);
         try {
             WebElement history_message = adc.driver1.findElement(By.xpath(element_to_verify));
             Assert.assertTrue(history_message.isDisplayed());{
                 logger.info("Pass: message is displayed " + history_message.getText());
             }
         }catch (Exception e){
             logger.info("***No such element found!***");
         }
         try {
             WebElement history_message = adc.driver1.findElement(By.xpath(element_to_verify1));
             Assert.assertTrue(history_message.isDisplayed());{
                 logger.info("Pass: message is displayed " + history_message.getText());
             }
         }catch (Exception e){
             logger.info("***No such element found!***");
         }
         Thread.sleep(5000);
     }
     public void ArmStay_Tamper_sensor(int group, String DLID, String element_to_verify, String element_to_verify1) throws InterruptedException, IOException {
         logger.info("ArmStay -Open/Close Group " +group + " contact sensor during exit delay");
         ARM_STAY();
         Thread.sleep(35000);
         logger.info("Tamper a sensor");
         sensors.primary_call(DLID, tamper);
         Thread.sleep(4000);
         sensors.primary_call(DLID, close);
         Thread.sleep(5000);
         enter_default_user_code();
         /*** ADC website verification ***/
         adc.New_ADC_session(AccountID);
         adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("History"))).click();
         Thread.sleep(10000);
         try {
             WebElement history_message = adc.driver1.findElement(By.xpath(element_to_verify));
             Assert.assertTrue(history_message.isDisplayed());{
                 logger.info("Pass: message is displayed " + history_message.getText());
             }
         }catch (Exception e){
             logger.info("***No such element found!***");
         }
         try {
             WebElement history_message = adc.driver1.findElement(By.xpath(element_to_verify1));
             Assert.assertTrue(history_message.isDisplayed());{
                 logger.info("Pass: message is displayed " + history_message.getText());
             }
         }catch (Exception e){
             logger.info("***No such element found!***");
         }
         Thread.sleep(5000);
     }
     public void ArmStay_Tamper_sensor_ArmStay(int group, String DLID, String element_to_verify, String element_to_verify1) throws Exception {
         logger.info("ArmStay -Open/Close Group " +group + " contact sensor during exit delay");
         ARM_STAY();
         Thread.sleep(35000);
         logger.info("Tamper a sensor");
         sensors.primary_call(DLID, tamper);
         Thread.sleep(4000);
         sensors.primary_call(DLID, close);
         Thread.sleep(5000);
         verify_armstay();
         DISARM();
         /*** ADC website verification ***/
         adc.New_ADC_session(AccountID);
         adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("History"))).click();
         Thread.sleep(10000);
         try {
             WebElement history_message = adc.driver1.findElement(By.xpath(element_to_verify));
             Assert.assertTrue(history_message.isDisplayed());{
                 logger.info("Pass: message is displayed " + history_message.getText());
             }
         }catch (Exception e){
             logger.info("***No such element found!***");
         }
         try {
             WebElement history_message = adc.driver1.findElement(By.xpath(element_to_verify1));
             Assert.assertTrue(history_message.isDisplayed());{
                 logger.info("Pass: message is displayed " + history_message.getText());
             }
         }catch (Exception e){
             logger.info("***No such element found!***");
         }
         Thread.sleep(5000);
     }

     /*** Open-Close sensor During Exit Delay ***/

    @Test (dependsOnMethods = {"addSensors"}, retryAnalyzer = RetryAnalizer.class)
    public void ArmStayExitDelay_10() throws Exception {
        ArmStay_Open_Close_sensor_during_Exit_Delay(10, "65 00 0A", "//*[contains(text(), 'Door/Window 1  (Sensor 1) Opened/Closed')]");
    }
    @Test  (priority = 1, retryAnalyzer = RetryAnalizer.class)
    public  void ArmStayExitDelay_12() throws Exception {
        ArmStay_Open_Close_sensor_during_Exit_Delay(12,"65 00 1A", "//*[contains(text(), 'Door/Window 2  (Sensor 2) Opened/Closed')]");
    }
    @Test (priority = 2, retryAnalyzer = RetryAnalizer.class)
    public void ArmStayExitDelay_13() throws Exception {
        ArmStay_Open_Close_sensor_during_Exit_Delay_Alarm(13,"65 00 2A", "//*[contains(text(), 'Door/Window 3  (Sensor 3) Opened/Closed')]");
    }
    @Test (priority = 3, retryAnalyzer = RetryAnalizer.class)
    public void ArmStayExitDelay_14() throws Exception {
        ArmStay_Open_Close_sensor_during_Exit_Delay(14,"65 00 3A", "//*[contains(text(), 'Door/Window 4  (Sensor 4) Opened/Closed')]");
    }
    @Test  (priority = 4, retryAnalyzer = RetryAnalizer.class)
    public void ArmStayExitDelay_16() throws Exception {
        ArmStay_Open_Close_sensor_during_Exit_Delay(16,"65 00 4A", "//*[contains(text(), 'Door/Window 5  (Sensor 5) Opened/Closed')]");
    }
    @Test (priority = 5, retryAnalyzer = RetryAnalizer.class)
    public void ArmStayExitDelay_8() throws Exception {
        ArmStay_Open_Close_sensor_during_Exit_Delay_Alarm(8, "65 00 5A", "//*[contains(text(), 'Door/Window 6 (Sensor 6) Alarm')]");
    }
    @Test  (priority = 6, retryAnalyzer = RetryAnalizer.class)
    public void ArmStayExitDelay_9() throws Exception {
        ArmStay_Open_Close_sensor_during_Exit_Delay_Alarm(9,"65 00 6A", "//*[contains(text(), 'Delayed alarm on sensor 7 in partition 1')]");
    }
    @Test  (priority = 7, retryAnalyzer = RetryAnalizer.class)
    public void ArmStayExitDelay_25() throws Exception {
        ArmStay_Open_Close_sensor_during_Exit_Delay(25,"65 00 7A", "//*[contains(text(), 'Door/Window 8  (Sensor 8) Opened/Closed')]");
    }

    /*** Open-Close sensor in Arm Stay mode ***/

    @Test(priority = 8, retryAnalyzer = RetryAnalizer.class)
    public void ArmStay_Open_Close_sensor_10() throws Exception {
        ArmStay_Open_Close_sensor(10,"65 00 0A", "//*[contains(text(), 'Door/Window 1  (Sensor 1) Opened/Closed')]", "//*[contains(text(), 'Armed Stay')]");
    }
    @Test (priority = 9,retryAnalyzer = RetryAnalizer.class )
    public void ArmStat_Open_Close_sensor_12() throws Exception {
        ArmStay_Open_Close_sensor(12, "65 00 1A","//*[contains(text(), 'Door/Window 2  (Sensor 2) Opened/Closed')]", "//*[contains(text(), 'Armed Stay')]");
    }
    @Test (priority = 10,retryAnalyzer = RetryAnalizer.class )
     public void ArmStay_Open_Close_sensor_13() throws Exception {
        ArmStay_Open_Close_sensor(13, "65 00 2A","//*[contains(text(), 'Door/Window 3  (Sensor 3) Opened/Closed')]", "//*[contains(text(), 'Pending Alarm')]");
     }
     @Test (priority = 11, retryAnalyzer = RetryAnalizer.class )
     public void ArmStay_Open_Close_sensor_14() throws Exception {
        ArmStay_Open_Close_sensor(14, "65 00 3A","//*[contains(text(), 'Door/Window 4  (Sensor 4) Opened/Closed')]", "//*[contains(text(), 'Pending Alarm')]");
     }
     @Test (priority = 12, retryAnalyzer = RetryAnalizer.class )
     public void ArmStay_Open_Close_sensor_16() throws Exception {
         ArmStay_Open_Close_sensor_ArmStay(16, "65 00 4A","//*[contains(text(), 'Door/Window 5  (Sensor 5) Opened/Closed')]", "//*[contains(text(), 'Armed Stay')]");
     }
     @Test (priority = 13, retryAnalyzer = RetryAnalizer.class )
     public void ArmStay_Open_Close_sensor_8() throws Exception {
         ArmStay_Open_Close_sensor(8, "65 00 5A","//*[contains(text(), 'Delayed alarm')]", "//*[contains(text(), 'Pending Alarm')]");
     }
     @Test (priority = 14, retryAnalyzer = RetryAnalizer.class )
     public void ArmStay_Open_Close_sensor_9() throws Exception {
         ArmStay_Open_Close_sensor(9, "65 00 6A","//*[contains(text(), 'Entry delay')]", "//*[contains(text(), 'Armed Stay')]");
     }
     @Test (priority = 15, retryAnalyzer = RetryAnalizer.class )
     public void ArmStay_Open_Close_sensor_25() throws Exception {
         ArmStay_Open_Close_sensor_ArmStay(25, "65 00 7A","//*[contains(text(), 'Door/Window 8  (Sensor 8) Opened/Closed')]", "//*[contains(text(), 'Armed Stay')]");
     }

     /*** Tamper sensor in Arm Stay mode ***/

     @Test (priority = 16, retryAnalyzer = RetryAnalizer.class )
     public void ArmStay_Tamper_sensor_10 () throws IOException, InterruptedException {
         ArmStay_Tamper_sensor(10,"65 00 0A","//*[contains(text(), 'Sensor 1 Tamper**')]","//*[contains(text(), 'Sensor 1 End of tamper')]");
     }
     @Test (priority = 17, retryAnalyzer = RetryAnalizer.class )
     public void ArmStay_Tamper_sensor_12 () throws IOException, InterruptedException {
         ArmStay_Tamper_sensor(12,"65 00 1A","//*[contains(text(), 'Sensor 2 Tamper**')]","//*[contains(text(), 'Sensor 2 End of tamper')]");
     }
     @Test (priority = 18, retryAnalyzer = RetryAnalizer.class )
     public void ArmStay_Tamper_sensor_13 () throws IOException, InterruptedException {
         ArmStay_Tamper_sensor(13,"65 00 2A","//*[contains(text(), 'Sensor 3 Tamper**')]","//*[contains(text(), 'Sensor 3 End of tamper')]");
     }
     @Test (priority = 19, retryAnalyzer = RetryAnalizer.class )
     public void ArmStay_Tamper_sensor_14 () throws IOException, InterruptedException {
         ArmStay_Tamper_sensor(14,"65 00 3A","//*[contains(text(), 'Sensor 4 Tamper**')]","//*[contains(text(), 'Sensor 4 End of tamper')]");
     }
     @Test (priority = 20, retryAnalyzer = RetryAnalizer.class )
     public void ArmStay_Tamper_sensor_16 () throws Exception {
         ArmStay_Tamper_sensor_ArmStay(14,"65 00 4A","//*[contains(text(), 'Sensor 5 Tamper**')]","//*[contains(text(), 'Sensor 5 End of tamper')]");
     }
     @Test (priority = 21, retryAnalyzer = RetryAnalizer.class )
     public void ArmStay_Tamper_sensor_8 () throws Exception {
         ArmStay_Tamper_sensor(8,"65 00 5A","//*[contains(text(), 'Sensor 6 Tamper**')]","//*[contains(text(), 'Sensor 6 End of tamper')]");
     }
     @Test (priority = 22, retryAnalyzer = RetryAnalizer.class )
     public void ArmStay_Tamper_sensor_9 () throws Exception {
         ArmStay_Tamper_sensor(9,"65 00 6A","//*[contains(text(), 'Sensor 7 Tamper**')]","//*[contains(text(), 'Sensor 7 End of tamper')]");
     }
     @Test (priority = 23, retryAnalyzer = RetryAnalizer.class )
     public void ArmStay_Tamper_sensor_25 () throws Exception {
         ArmStay_Tamper_sensor_ArmStay(25,"65 00 7A","//*[contains(text(), 'Sensor 8 Tamper**')]","//*[contains(text(), 'Sensor 8 End of tamper')]");
     }

//    @Test (dependsOnMethods = {"addSensors"}, retryAnalyzer = RetryAnalizer.class)
//    public void ArmStay_Open_Close_group_10_during_Exit_Delay() throws Exception {
//        logger.info("ArmStay -Open/Close Group 10 contact sensor during exit delay");
//        ARM_STAY();
//        Thread.sleep(10000);
//        logger.info("Open/Close a sensor");
//        sensors.primary_call("65 00 0A", open);
//        Thread.sleep(2000);
//        sensors.primary_call("65 00 0A", close);
//        Thread.sleep(Normal_Exit_Delay);
//        verify_armstay();
//        Thread.sleep(10000);
//        DISARM();
//
//        /*** ADC website verification ***/
//
//        adc.New_ADC_session(AccountID);
//        adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("History"))).click();
//        Thread.sleep(10000);
//        try {
//            WebElement history_message = adc.driver1.findElement(By.xpath("//*[contains(text(), 'Door/Window 1  (Sensor 1) Opened/Closed')]"));
//            Assert.assertTrue(history_message.isDisplayed());{
//                logger.info("Pass: message is displayed " + history_message.getText());
//            }
//        }catch (Exception e){
//            logger.info("***No such element found!***");
//        }
//        Thread.sleep(5000);
//    }
//
//    @Test(priority = 1, retryAnalyzer = RetryAnalizer.class)
//    public void ArmStay_Open_Close_group_12_during_Exit_Delay() throws Exception {
//        logger.info("ArmStay -Open/Close Group 12 contact sensor during exit delay");
//        ARM_STAY();
//        Thread.sleep(10000);
//        logger.info("Open/Close a sensor");
//        sensors.primary_call("65 00 1A", open);
//        Thread.sleep(2000);
//        sensors.primary_call("65 00 1A", close);
//        Thread.sleep(Normal_Exit_Delay);
//        verify_armstay();
//        Thread.sleep(3000);
//        DISARM();
//
//        adc.New_ADC_session(adc.getAccountId());
//        Thread.sleep(2000);
//        adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("History"))).click();
//        Thread.sleep(10000);
//        try{
//            WebElement history_message = adc.driver1.findElement(By.xpath("//*[contains(text(), 'Door/Window 2  (Sensor 2) Opened/Closed')]"));
//            Assert.assertTrue(history_message.isDisplayed());
//            logger.info("Pass: message is displayed "+history_message.getText());
//        } catch (Exception e){
//            logger.info("***No such element found!***");
//        }
//        Thread.sleep(2000);
//    }
//    @Test(priority = 2, retryAnalyzer = RetryAnalizer.class)
//    public void ArmStay_Open_Close_group_13_during_Exit_Delay() throws Exception {
//        logger.info("ArmStay -Open/Close Group 13 contact sensor during exit delay");
//        ARM_STAY();
//        Thread.sleep(10000);
//        logger.info("Open/Close a sensor");
//        sensors.primary_call("65 00 2A", open);
//        Thread.sleep(2000);
//        sensors.primary_call("65 00 2A", close);
//        Thread.sleep(Normal_Exit_Delay);
//        verify_in_alarm();
//        Thread.sleep(2000);
//        enter_default_user_code();
//
//        /*** ADC website verification ***/
//
//        adc.New_ADC_session(adc.getAccountId());
//        Thread.sleep(2000);
//        adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("History"))).click();
//        adc.driver1.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        try {
//            WebElement history_message = adc.driver1.findElement(By.xpath("//*[contains(text(), 'Door/Window 3  (Sensor 3) Opened/Closed')]"));
//            WebElement history_message_alarm = adc.driver1.findElement(By.xpath("//*[contains(text(), 'Door/Window 3 (Sensor 3) Alarm')]"));
//            Assert.assertTrue(history_message.isDisplayed() && history_message_alarm.isDisplayed()); {
//               logger.info("Pass: message is displayed " + history_message.getText()+" " + history_message_alarm.getText());
//            }
//            }catch (Exception e){
//            logger.info("***No such element found!***");
//        }
//            Thread.sleep(2000);
//        }

//    @Test(priority = 3, retryAnalyzer = RetryAnalizer.class)
//    public void  ArmStay_Open_Close_group_14_during_Exit_Delay() throws Exception {
//        logger.info("ArmStay -Open/Close Group 14 contact sensor during exit delay");
//        ARM_STAY();
//        Thread.sleep(10000);
//        logger.info("Open/Close a sensor");
//        sensors.primary_call("65 00 3A", open);
//        Thread.sleep(2000);
//        sensors.primary_call("65 00 3A", close);
//        Thread.sleep(Normal_Exit_Delay);
//        verify_armstay();
//        Thread.sleep(3000);
//        DISARM();
//
//        /*** ADC website verification ***/
//
//        adc.New_ADC_session(adc.getAccountId());
//        Thread.sleep(2000);
//        adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("History"))).click();
//        Thread.sleep(10000);
//        try {
//            WebElement history_message = adc.driver1.findElement(By.xpath("//*[contains(text(), 'Door/Window 4  (Sensor 4) Opened/Closed')]"));
//            Assert.assertTrue(history_message.isDisplayed());
//               logger.info("Pass: message is displayed " + history_message.getText());
//            } catch (Exception e){
//            logger.info("***No such element found!***");
//        }
//        Thread.sleep(2000);
//    }
//    @Test(priority = 4, retryAnalyzer = RetryAnalizer.class)
//    public void  ArmStay_Open_Close_group_16_during_Exit_Delay() throws Exception {
//        logger.info("ArmStay -Open/Close Group 16 contact sensor during exit delay");
//        ARM_STAY();
//        Thread.sleep(10000);
//        logger.info("Open/Close a sensor");
//        sensors.primary_call("65 00 4A", open);
//        Thread.sleep(2000);
//        sensors.primary_call("65 00 4A", close);
//        Thread.sleep(Normal_Exit_Delay);
//        verify_armstay();
//        Thread.sleep(3000);
//        DISARM();
//
//        /*** ADC website verification ***/
//
//        adc.New_ADC_session(adc.getAccountId());
//        Thread.sleep(2000);
//        adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("History"))).click();
//        Thread.sleep(10000);
//        try {
//            WebElement history_message = adc.driver1.findElement(By.xpath("//*[contains(text(), 'Door/Window 5  (Sensor 5) Opened/Closed')]"));
//            Assert.assertTrue(history_message.isDisplayed());
//            {
//                logger.info("Pass: message is displayed " + history_message.getText());
//            }
//        }catch (Exception e){
//            logger.info("***No such element found!***");
//        }
//        Thread.sleep(2000);
//    }
//    @Test(priority = 5, retryAnalyzer = RetryAnalizer.class)
//    public void  ArmStay_Open_Close_group_8_during_Exit_Delay() throws Exception {
//        logger.info("ArmStay -Open/Close Group 8 contact sensor during exit delay");
//        ARM_STAY();
//        Thread.sleep(10000);
//        logger.info("Open/Close a sensor");
//        sensors.primary_call("65 00 5A", open);
//        Thread.sleep(2000);
//        sensors.primary_call("65 00 5A", close);
//        Thread.sleep(2000);
//        verify_in_alarm();
//        Thread.sleep(30000);
//        enter_default_user_code();
//
//        /*** ADC website verification ***/
//
//        adc.New_ADC_session(adc.getAccountId());
//        Thread.sleep(2000);
//        adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("History"))).click();
//        Thread.sleep(10000);
//        try {
//            WebElement history_message_alarm = adc.driver1.findElement(By.xpath("//*[contains(text(), 'Door/Window 6 (Sensor 6) Alarm')]"));
//            Assert.assertTrue( history_message_alarm.isDisplayed());{
//                logger.info("Pass: message is displayed " + " " +history_message_alarm.getText());
//            }
//        }catch (Exception e){
//            logger.info("***No such element found!***");
//        }
//        Thread.sleep(2000);
//    }
//    @Test(priority = 6, retryAnalyzer = RetryAnalizer.class)
//    public void  ArmStay_Open_Close_group_9_during_Exit_Delay() throws Exception {
//        logger.info("ArmStay -Open/Close Group 9 contact sensor during exit delay");
//        ARM_STAY();
//        Thread.sleep(10000);
//        logger.info("Open/Close a sensor");
//        sensors.primary_call("65 00 6A", open);
//        Thread.sleep(2000);
//        sensors.primary_call("65 00 6A", close);
//        Thread.sleep(30000);
//        verify_in_alarm();
//        Thread.sleep(5000);
//        enter_default_user_code();
//
//        /*** ADC website verification ***/
//
//        adc.New_ADC_session(adc.getAccountId());
//        Thread.sleep(3000);
//        adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("History"))).click();
//        Thread.sleep(10000);
//        try {
//            WebElement history_message_alarm = adc.driver1.findElement(By.xpath("//*[contains(text(), 'Delayed alarm on sensor 7 in partition 1')]"));
//            Assert.assertTrue( history_message_alarm.isDisplayed());{
//               logger.info("Pass: message is displayed " + " " +history_message_alarm.getText());
//            }
//        }catch (Exception e){
//            logger.info("***No such element found!***");
//        }
//        Thread.sleep(2000);
//    }
//    @Test(priority = 7, retryAnalyzer = RetryAnalizer.class)
//    public void  ArmStay_Open_Close_group_25_during_Exit_Delay() throws Exception {
//        logger.info("ArmStay -Open/Close Group 25 contact sensor during exit delay");
//        ARM_STAY();
//        Thread.sleep(10000);
//        logger.info("Open/Close a sensor");
//        sensors.primary_call("65 00 7A", open);
//        Thread.sleep(2000);
//        sensors.primary_call("65 00 7A", close);
//        Thread.sleep(30000);
//        verify_armstay();
//        Thread.sleep(5000);
//        DISARM();
//
//        /*** ADC website verification ***/
//
//        adc.New_ADC_session(adc.getAccountId());
//        Thread.sleep(3000);
//        adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("History"))).click();
//        Thread.sleep(10000);
//        try {
//            WebElement history_message_alarm = adc.driver1.findElement(By.xpath("//*[contains(text(), 'Door/Window 8  (Sensor 8) Opened/Closed')]"));
//            Assert.assertTrue( history_message_alarm.isDisplayed());{
//                logger.info("Pass: message is displayed " + " " +history_message_alarm.getText());
//            }
//        }catch (Exception e){
//            logger.info("***No such element found!***");
//        }
//    }
    @AfterTest
    public void tearDown() throws IOException, InterruptedException {
        driver.quit();
//        for (int i= 8; i>0; i--) {
//            delete_from_primary(i);
//        }
    }
    @AfterMethod
    public void webDriverQuit(){
        adc.driver1.quit();
    }
}