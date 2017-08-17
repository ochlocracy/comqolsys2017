package Update;

import Panel.PanelInfo_ServiceCalls;
import Panel.Setup;
import jxl.read.biff.BiffException;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by nchortek on 8/10/17.
 */
public class PostUpdate_Settings extends Setup{
    String page_name = "Post-Update Panel Settings";
    Logger logger = Logger.getLogger(page_name);
    PanelInfo_ServiceCalls servcall = new PanelInfo_ServiceCalls();

    public PostUpdate_Settings() throws IOException, BiffException {
    }


    public void verify_setting(String setting, String call, String expected) throws IOException {
        String result = execCmd(adbPath + " shell service call qservice " + call).split(" ")[2];
        if(result.equals(expected))
            logger.info("[Pass] " + setting + " has value: " + expected);
        else
            logger.info("[Fail] " + setting + " has value: " + result + ". Expected:" + expected);
    }

    @BeforeTest
    public void capabilities_setup() throws Exception {
        setup_driver( get_UDID(),"http://127.0.1.1", "4723");
        setup_logger(page_name);
    }

    @Test
    public void checkSettings() throws IOException, InterruptedException {
        String ON = "00000001";
        String OFF = "00000000";
        int one_sec = 1000;

        verify_setting("Media Volume", "36 i32 0 i32 0 i32 39 i32 0 i32 0", ON);
        Thread.sleep(one_sec);
        verify_setting("All Voice Prompts", "37 i32 0 i32 0 i32 42 i32 0 i32 0", OFF);
        Thread.sleep(one_sec);
        verify_setting("All Chimes", "37 i32 0 i32 0 i32 46 i32 0 i32 0", ON);
        Thread.sleep(one_sec);
        verify_setting("All Trouble Beeps", "37 i32 0 i32 0 i32 111 i32 0 i32 0", ON);
        Thread.sleep(one_sec);
        verify_setting("Fire Safety Device", "37 i32 0 i32 0 i32 146 i32 0 i32 0", ON);
        Thread.sleep(one_sec);
        verify_setting("WiFi", "37 i32 0 i32 0 i32 32 i32 0 i32 0", ON);
        Thread.sleep(one_sec);
        verify_setting("Secure Delete Images", "37 i32 0 i32 0 i32 104 i32 0 i32 0", OFF);
        Thread.sleep(one_sec);
        verify_setting("Disarm Photo", "37 i32 0 i32 0 i32 102 i32 0 i32 0", OFF);
        Thread.sleep(one_sec);
        //verify_setting("Alarm Videos", "37 i32 0 i32 0 i32 109 i32 0 i32 0", ON);
        //Thread.sleep(one_sec);
        verify_setting("Alarm Photos", "37 i32 0 i32 0 i32 109 i32 0 i32 0", OFF);
        Thread.sleep(one_sec);
        verify_setting("Settings Photos", "37 i32 0 i32 0 i32 143 i32 0 i32 0", ON);
        Thread.sleep(one_sec);
        verify_setting("Duress Authentication", "37 i32 0 i32 0 i32 61 i32 0 i32 0", ON);
        Thread.sleep(one_sec);
        verify_setting("Secure Arming", "37 i32 0 i32 0 i32 35 i32 0 i32 0", ON);
        Thread.sleep(one_sec);
        verify_setting("No Arming On Low Battery", "37 i32 0 i32 0 i32 36 i32 0 i32 0", ON);
        Thread.sleep(one_sec);
        verify_setting("Auto Bypass", "37 i32 0 i32 0 i32 19 i32 0 i32 0", OFF);
        Thread.sleep(one_sec);
        verify_setting("Auto Stay", "37 i32 0 i32 0 i32 20 i32 0 i32 0", OFF);
        Thread.sleep(one_sec);
        verify_setting("Arm Stay No Delay", "37 i32 0 i32 0 i32 21 i32 0 i32 0", OFF);
        Thread.sleep(one_sec);
        verify_setting("Auto Exit Time Extension", "37 i32 0 i32 0 i32 84 i32 0 i32 0", OFF);
        Thread.sleep(one_sec);
        verify_setting("Keyfob Alarm Disarm", "37 i32 0 i32 0 i32 129 i32 0 i32 0", ON);
        Thread.sleep(one_sec);
        verify_setting("Keyfob Disarming", "37 i32 0 i32 0 i32 134 i32 0 i32 0", OFF);
        Thread.sleep(one_sec);
        verify_setting("Normal Entry Delay", "36 i32 0 i32 0 i32 15 i32 0 i32 0", "00000021");
        Thread.sleep(one_sec);
        verify_setting("Normal Exit Delay", "36 i32 0 i32 0 i32 16 i32 0 i32 0", "00000039");
        Thread.sleep(one_sec);
        verify_setting("Long Entry Delay", "36 i32 0 i32 0 i32 114 i32 0 i32 0", "00000071");
        Thread.sleep(one_sec);
        verify_setting("Long Exit Delay", "36 i32 0 i32 0 i32 115 i32 0 i32 0", "00000075");
        Thread.sleep(one_sec);
        verify_setting("Siren Disable", "37 i32 0 i32 0 i32 14 i32 0 i32 0", OFF);
        Thread.sleep(one_sec);
        verify_setting("Fire Verification", "37 i32 0 i32 0 i32 100 i32 0 i32 0", ON);
        Thread.sleep(one_sec);
        verify_setting("Severe Weather Siren Warning", "37 i32 0 i32 0 i32 103 i32 0 i32 0", OFF);
        Thread.sleep(one_sec);
        verify_setting("Dialer Delay", "36 i32 0 i32 0 i32 12 i32 0 i32 0", "00000014");
        Thread.sleep(one_sec);
        verify_setting("Siren Timeout", "36 i32 0 i32 0 i32 13 i32 0 i32 0", "000001a4");
        Thread.sleep(one_sec);
        verify_setting("Water Freeze Alarm", "37 i32 0 i32 0 i32 122  i32 0 i32 0", ON);
        Thread.sleep(one_sec);
        verify_setting("Police Panic", "37 i32 0 i32 0 i32 131 i32 0 i32 0", OFF);
        Thread.sleep(one_sec);
        verify_setting("Fire Panic", "37 i32 0 i32 0 i32 132 i32 0 i32 0", OFF);
        Thread.sleep(one_sec);
        verify_setting("Auxillary Panic", "37 i32 0 i32 0 i32 133 i32 0 i32 0", OFF);
        Thread.sleep(one_sec);
        verify_setting("Auto Upload Logs", "37 i32 0 i32 0 i32 90 i32 0 i32 0",  ON);
        Thread.sleep(one_sec);
        verify_setting("Power Management On/Off", "37 i32 0 i32 0 i32 73 i32 0 i32 0", OFF);
        Thread.sleep(one_sec);
        verify_setting("SIA Power Restoration", "37 i32 0 i32 0 i32 74 i32 0 i32 0", ON);
        Thread.sleep(one_sec);
        verify_setting("Loss of Supervisory Signals for Non-emergency Sensors", "36 i32 0 i32 0 i32 31 i32 0 i32 0", "0000000c");
        Thread.sleep(one_sec);
        verify_setting("Loss of Supervisory Signals for Emergency Sensors", "36 i32 0 i32 0 i32 118 i32 0 i32 0", "0000000c");
        Thread.sleep(one_sec);
        verify_setting("Cell Signal Timeout", "36 i32 0 i32 0 i32 101 i32 0 i32 0", "00000019");
        Thread.sleep(one_sec);
        verify_setting("SIA Limits", "37 i32 0 i32 0 i32 37 i32 0 i32 0", OFF);
        Thread.sleep(one_sec);
        verify_setting("RF Jam Detect", "37 i32 0 i32 0 i32 25 i32 0 i32 0", ON );
        Thread.sleep(one_sec);
        verify_setting("Open/Close Reports for Auto Learn", "37 i32 0 i32 0 i32 127 i32 0 i32 0", OFF);
        Thread.sleep(one_sec);
        verify_setting("Bluetooth", "37 i32 0 i32 0 i32 142 i32 0 i32 0", ON);
        Thread.sleep(one_sec);
        verify_setting("Bluetooth Disarm", "37 i32 0 i32 0 i32 138 i32 0 i32 0", ON);
        Thread.sleep(one_sec);
        verify_setting("Bluetooth Disarm Timeout", "36 i32 0 i32 0 i32 139 i32 0 i32 0", "0000001e");
        Thread.sleep(one_sec);
        verify_setting("Allow Master Code to Access Camera Settings", "37 i32 0 i32 0 i32 107 i32 0 i32 0", ON);
        Thread.sleep(one_sec);
        verify_setting("Allow Master Code to Access Security and Arming Settings", "37 i32 0 i32 0 i32 106 i32 0 i32 0", ON);
        Thread.sleep(one_sec);
        verify_setting("Allow Master Code to Access Siren and Alarms Settings", "37 i32 0 i32 0 i32 105 i32 0 i32 0", ON);
        Thread.sleep(one_sec);

    }

    @AfterTest
    public void tearDown() throws IOException, InterruptedException {
        driver.quit();
    }
}
