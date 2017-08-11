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

        //sets media volume to 1
        servcall.set_SPEAKER_VOLUME(ON);
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
        Thread.sleep(one_sec);
        servcall.set_NORMAL_ENTRY_DELAY(33);
        Thread.sleep(one_sec);
        servcall.set_NORMAL_EXIT_DELAY(57);
        Thread.sleep(one_sec);
        servcall.set_LONG_ENTRY_DELAY(113);
        Thread.sleep(one_sec);
        servcall.set_LONG_EXIT_DELAY(117);
        Thread.sleep(one_sec);
        servcall.set_SIREN_DISABLE(OFF);
        Thread.sleep(one_sec);
        servcall.set_FIRE_VERIFICATION(ON);
        Thread.sleep(one_sec);
        servcall.set_SEVERE_WEATHER_SIREN_WARNING(OFF);
        Thread.sleep(one_sec);
        servcall.set_DIALER_DELAY(23);
        Thread.sleep(one_sec);
        servcall.set_SIREN_TIMEOUT(7);
        Thread.sleep(one_sec);
        servcall.set_WATER_FREEZE_ALARM();
        Thread.sleep(one_sec);
        servcall.set_AUTO_UPLOAD_LOGS(ON);
        Thread.sleep(one_sec);
        servcall.set_POWER_MANAGEMENT_ON_OFF_disable();
        Thread.sleep(one_sec);
        servcall.set_SIA_POWER_RESTORATION_enable();
        Thread.sleep(one_sec);
        //non-emergency sensors
        servcall.set_LOSS_OF_SUPERVISORY_TIMEOUTY_12h();
        Thread.sleep(one_sec);
        //emergency sensors, sets to 12 hours
        servcall.set_LOSS_OF_SUPERVISORY_EMERGENCY_TIMEOUT();
        Thread.sleep(one_sec);
        servcall.set_Cell_Signal_Timeout(25);
        Thread.sleep(one_sec);
        servcall.set_SIA_LIMITS_disable();
        Thread.sleep(one_sec);
        servcall.set_RF_JAM_DETECT_enable();
        Thread.sleep(one_sec);


    }

    @AfterTest
    public void tearDown() throws IOException, InterruptedException {
        driver.quit();
    }
}
