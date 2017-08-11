package Update;

import ADC.ADC;
import Panel.PanelInfo_ServiceCalls;
import Panel.Setup;
import Sensors.Sensors;
import jxl.read.biff.BiffException;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by nchortek on 8/10/17.
 */
public class PreUpdate_Settings extends Setup{
    String page_name = "Pre-Update Panel Settings";
    Logger logger = Logger.getLogger(page_name);
    PanelInfo_ServiceCalls servcall = new PanelInfo_ServiceCalls();

    public PreUpdate_Settings() throws IOException, BiffException {
    }

    @BeforeTest
    public void capabilities_setup() throws Exception {
        setup_driver( get_UDID(),"http://127.0.1.1", "4723");
        setup_logger(page_name);
    }

    @Test
    public void invertSettings() throws IOException, InterruptedException {
        int ON = 1;
        int OFF = 0;
        int one_sec = 1000;

        servcall.set_SPEAKER_VOLUME(1);
        Thread.sleep(one_sec);
        servcall.set_ALL_VOICE_PROMPTS(OFF);
        Thread.sleep(one_sec);
        servcall.set_ALL_CHIMES(ON);
        Thread.sleep(one_sec);
        servcall.set_ENABLE_ALL_TROUBLE_BEEPS(ON);
        Thread.sleep(one_sec);
        servcall.set_WiFi(ON);
        Thread.sleep(one_sec);
        servcall.set_SECURE_DELETE_IMAGES(OFF);
        Thread.sleep(one_sec);
        servcall.set_DISARM_PHOTO(OFF);
        Thread.sleep(one_sec);
        servcall.set_ALARM_VIDEOS(ON);
        Thread.sleep(one_sec);
        servcall.set_DURESS_AUTHENTICATION_enable();
        Thread.sleep(one_sec);
        servcall.set_SECURE_ARMING_enable();
        Thread.sleep(one_sec);
        servcall.set_NO_ARMING_ON_LOW_BATTERY_enable();
        Thread.sleep(one_sec);
        servcall.set_AUTO_BYPASS(OFF);
        Thread.sleep(one_sec);
        servcall.set_AUTO_STAY(OFF);
        Thread.sleep(one_sec);
        servcall.set_ARM_STAY_NO_DELAY_disable();
        Thread.sleep(one_sec);
        servcall.set_AUTO_EXIT_TIME_EXTENSION(OFF);

    }

    @AfterTest
    public void tearDown() throws IOException, InterruptedException {
        driver.quit();
    }
}
