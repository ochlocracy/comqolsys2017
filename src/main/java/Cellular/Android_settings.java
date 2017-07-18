package Cellular;

import Panel.Setup;
import io.appium.java_client.android.AndroidDriver;
import jxl.read.biff.BiffException;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.io.IOException;
import java.net.URL;

public class Android_settings {

    public AndroidDriver<WebElement> driver;
    String page_name = "Native Android setting";
    Logger logger = Logger.getLogger(page_name);
    Setup s = new Setup();

    public Android_settings() throws IOException, BiffException {
    }

    public void setup_driver_and(String getUdid, String url_, String port_) throws Exception {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("deviceName", "IQPanel2");
        cap.setCapability("BROWSER_NAME", "Android");
        cap.setCapability("udid", getUdid);
        cap.setCapability("appPackage", "com.android.settings/.SubSettings");
        cap.setCapability("appActivity", "com.qolsys.developerapp/com.qolsys.developerapp.activites.MainActivity");
        driver = new AndroidDriver<WebElement>(new URL(url_+":"+port_+"/wd/hub"), cap);
    }



   @BeforeMethod
   public void setUp () throws Exception {
        setup_driver_and(s.get_UDID(),"http://127.0.1.1", "4723");
   }


    @Test
    public void open_wireless_and_networks() throws IOException {
        String open_spage = "adb shell am start -a android.settings.WIRELESS_SETTINGS";
        s.rt.exec(s.adbPath + open_spage);
        System.out.println(open_spage); }



    @AfterClass
    public void tearDown () throws IOException, InterruptedException {
        s.log.endTestCase(page_name);
        driver.quit();
    }

        }


