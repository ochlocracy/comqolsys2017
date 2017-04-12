package GridTesting;

import Panel.Log;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import jxl.read.biff.BiffException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;
import ADC.*;

    public class Test1 {

        AppiumDriver driver = null;
        ADC myADC = new ADC();
        public String adbPath = "/home/qolsys/android-sdk-linux/platform-tools/adb";
        private String  config = "/home/qolsys/Desktop/config.xls";
        private String panel = "8ebdbc76 ";
        private String panel1 = "8ebdbc7f ";
        private String panel2 = "8ebdbce0 ";
        public Runtime rt = Runtime.getRuntime();
        public Log log = new Log();
        public Logger logger = Logger.getLogger("String");

        public Test1() throws IOException, BiffException {
        }

        public void add_primary_call(int zone, int group, int sencor_dec, int sensor_type, String panel) throws IOException {
            String add_primary = "shell service call qservice 50 i32 " + zone + " i32 " + group + " i32 " + sencor_dec + " i32 " + sensor_type;
            rt.exec(adbPath + " -s " + panel + add_primary);
            // shell service call qservice 50 i32 2 i32 10 i32 6619296 i32 1
        }


        @Parameters({"deviceName_", "applicationName_", "UDID_", "platformVersion_", "URL_", "PORT_" })
        @BeforeTest(alwaysRun=true)

        public void setup(String deviceName_, String applicationName_, String UDID_, String platformVersion_, String URL_, String PORT_ ) throws MalformedURLException {

            DesiredCapabilities caps = new DesiredCapabilities();

            caps.setCapability("BROWSER_NAME", "Android");
            caps.setCapability("deviceName", deviceName_ );
            caps.setCapability("UDID", UDID_);
            caps.setVersion(platformVersion_);
            caps.setCapability("applicationName", applicationName_);
            caps.setCapability("appPackage", "com.qolsys");
            caps.setCapability("appActivity", "com.qolsys.activites.Theme3HomeActivity");
            caps.setCapability("PORT", PORT_);
            driver = new AndroidDriver<WebElement>(new URL(URL_), caps);
        }

        @Test(threadPoolSize = 3)
        public void  Test1() throws Exception {
            TimeUnit.SECONDS.sleep(3);
            driver.findElement(By.id("com.qolsys:id/btn_drop")).click();
            driver.findElement(By.id("com.qolsys:id/tv_tray_settings")).click();
            WebElement a = driver.findElement(By.id("com.qolsys:id/gridview"));
            List<WebElement> li = a.findElements(By.className("android.widget.TextView"));
            li.get(8).click();
            driver.findElement(By.id("com.qolsys:id/tv_keyOne")).click();
            driver.findElement(By.id("com.qolsys:id/tv_keyOne")).click();
            driver.findElement(By.id("com.qolsys:id/tv_keyOne")).click();
            driver.findElement(By.id("com.qolsys:id/tv_keyOne")).click();
            li.get(2).click();
            driver.findElement(By.xpath("//android.widget.TextView[@text='Software']")).click();
            WebElement soft_version = driver.findElement(By.id("com.qolsys:id/summary"));
            String current_version = soft_version.getText();
            System.out.println(current_version);
            driver.findElement(By.id("com.qolsys:id/ft_home_button")).click();

            TimeUnit.SECONDS.sleep(3);
            add_primary_call(1, 10, 6619305, myADC.door_window, panel); //default name Door/Window 1
            add_primary_call(1, 10, 6619305, myADC.door_window, panel1);
            add_primary_call(1, 10, 6619305, myADC.door_window, panel2);
            add_primary_call(2, 17, 5570628, myADC.motion, panel); //default name Motion 2
            add_primary_call(2, 17, 5570628, myADC.motion, panel1);
            add_primary_call(2, 17, 5570628, myADC.motion, panel2);
            add_primary_call(3, 26, 6750242, myADC.smoke_detector, panel);
            add_primary_call(3, 26, 6750242, myADC.smoke_detector, panel1);
            add_primary_call(3, 26, 6750242, myADC.smoke_detector, panel2);
            add_primary_call(4, 34, 7667882, myADC.co_detector, panel); //default name CO Detector 4
            add_primary_call(4, 34, 7667882, myADC.co_detector, panel1);
            add_primary_call(3, 26, 6750242, myADC.smoke_detector, panel2);

            TimeUnit.SECONDS.sleep(3);
            System.out.println("Go to the settings");
            driver.findElement(By.id("com.qolsys:id/btn_drop")).click();
            driver.findElement(By.id("com.qolsys:id/tv_tray_settings")).click();
            WebElement element = driver.findElement(By.xpath("//android.widget.TextView[@text='ADVANCED SETTINGS']"));
            element.click();
            driver.findElement(By.id("com.qolsys:id/tv_keyTwo")).click();
            driver.findElement(By.id("com.qolsys:id/tv_keyTwo")).click();
            driver.findElement(By.id("com.qolsys:id/tv_keyTwo")).click();
            driver.findElement(By.id("com.qolsys:id/tv_keyTwo")).click();
            li.get(0).click();
            li.get(0).click();
            li.get(0).click();
            li.get(2).click();
            TimeUnit.SECONDS.sleep(5);

            Rename_Sensor(0, myADC.new_dw_name);
            logger.info("Renaming motion sensor, new name: "+ myADC.new_motion_name);
            Rename_Sensor(1, myADC.new_motion_name);
            logger.info("Renaming smoke detector, new name: "+ myADC.new_smoke_name);
            Rename_Sensor(2, myADC.new_smoke_name);
            logger.info("Renaming co_detector, new name: "+ myADC.new_co_name);
            Rename_Sensor(3, myADC.new_co_name);
        }

        public void Rename_Sensor(int number, String new_name) {
            WebElement b = driver.findElement(By.className("android.widget.LinearLayout"));
            List<WebElement> li1 = b.findElements(By.id("com.qolsys:id/imageView1"));
            li1.get(number).click();
            driver.findElement(By.id("com.qolsys:id/sensorDescText")).clear();
            driver.findElement(By.id("com.qolsys:id/sensorDescText")).sendKeys(new_name);
            driver.hideKeyboard();
            driver.findElement(By.id("com.qolsys:id/addsensor")).click();
        }

        @AfterMethod
        public void tearDown () throws IOException, InterruptedException {
            driver.quit();
        }
    }