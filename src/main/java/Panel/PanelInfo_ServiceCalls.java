package Panel;

import jxl.read.biff.BiffException;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import java.io.IOException;

public class PanelInfo_ServiceCalls extends Setup {
    Configuration c = new Configuration();
    public String adbPath = c.getAdbPath();
    public Logger logger = Logger.getLogger(this.getClass().getName());
    public Runtime rt = Runtime.getRuntime();


    public PanelInfo_ServiceCalls() throws IOException, BiffException {}
    public void get_PARTITION() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 36 i32 0 i32 0 i32 1 i32 0 i32 0";
        rt.exec(command);    }

    public void get_ZONES() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 36 i32 0 i32 0 i32 2 i32 0 i32 0";
        rt.exec(command);    }

    public void get_TIME() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 36 i32 0 i32 0 i32 3 i32 0 i32 0";
        rt.exec(command);    }
    public void set_TIME() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 39 i32 0 i32 0 i32 3 i32 109999 i32 0 i32 0";
        rt.exec(command);    }

    public void get_ARMING_LEVEL() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 36 i32 0 i32 0 i32 4 i32 0 i32 0";
        rt.exec(command);
    String value = (execCmd(command)).toString();
    System.out.println(value);

        if (value.contains("00000000 00000000 ")) {
            System.out.println("Disarm");
        } else if (value.contains("00000000 00000001 ")) {
            System.out.println("ARM STAY");}
       if (value.contains("00000000 00000002 ")) {
            System.out.println("ARM AWAY");}}

    public void get_Installer_Code() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 38 i32 0 i32 0 i32 5 i32 241 i32 0";
        rt.exec(command);
        String value = (execCmd(command)).toString();
        System.out.println("Installer code" + value);}

    public void set_Installer_Code(int UserCode) throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 41 i32 0 i32 0 i32 5 s16 " + UserCode + " i32 241 i32 0";
        rt.exec(command);}

    public void get_User_Code() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 38 i32 0 i32 0 i32 5 i32 240 i32 0";
        rt.exec(command);
        String value = (execCmd(command)).toString();
        System.out.println("User code" + value);}

    public void set_USER_CODE(int UserCode) throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 41 i32 0 i32 0 i32 5 s16 " + UserCode + " i32 240 i32 0";
        rt.exec(command);}

    public void get_Master_Code() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 38 i32 0 i32 0 i32 5 i32 1 i32 0";
        rt.exec(command);
        String value = (execCmd(command)).toString();
        System.out.println("Master code" + value);}

    public void set_Master_Code(int UserCode) throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 41 i32 0 i32 0 i32 5 s16 " + UserCode + "  i32 1 i32 0";
        rt.exec(command);}

    public void get_User_Valid(int USER_ID) throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 37 i32 0 i32 0 i32 9 i32 241 " + USER_ID + " i32 0";
        rt.exec(command);}

    public void get_User_Type(int USER_ID) throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 36 i32 0 i32 0 i32 10 i32 241 " + USER_ID + " i32 0";
        rt.exec(command);}

    public void set_User_Type() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 39 i32 0 i32 0 i32 10 i32 1 i32 0 i32 0";
        rt.exec(command);}

    public void get_USER_EXPIRATION_DATE(int USER_ID) throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 38 i32 0 i32 0 i32 11 i32 240 " + USER_ID + " i32 0";
        rt.exec(command);}

    public void set_USER_EXPIRATION_DATE(String DATE, int USER_ID) throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 41 i32 0 i32 0 i32 11 s16 " + DATE + " i32 1 " + USER_ID + " i32 0";
        rt.exec(command);}

    public void get_DIALER_DELAY() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 36 i32 0 i32 0 i32 12 i32 0 i32 0";
        rt.exec(command);}

    public void set_DIALER_DELAY(int VALUE) throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 39 i32 0 i32 0 i32 12 i32 20 " + VALUE + " i32 0 i32 0";
        rt.exec(command);}

    public void get_SIREN_TIMEOUT() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 36 i32 0 i32 0 i32 13 i32 0 i32 0";
        rt.exec(command);}

    public void set_SIREN_TIMEOUT(int Siren_TimeOut_Value) throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 39 i32 0 i32 0 i32 13 i32 " + Siren_TimeOut_Value + " i32 0 i32 0";
        rt.exec(command);}

    public void get_SIREN_DISABLE() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 37 i32 0 i32 0 i32 14 i32 0 i32 0";
        rt.exec(command);}

    public void set_SIREN_DISABLE(boolean booleanValue) throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 40 i32 0 i32 0 i32 14 i32 " + booleanValue + " i32 0 i32 0";
        rt.exec(command);}

    public void get_ENTRY_DELAY() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 36 i32 0 i32 0 i32 15 i32 0 i32 0";
        rt.exec(command);}

    public void set_NORMAL_ENTRY_DELAY(int sec) throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 39 i32 0 i32 0 i32 15 i32 "+ sec +" i32 0 i32 0";
        rt.exec(command);}

    public void get_EXIT_DELAY() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 36 i32 0 i32 0 i32 16 i32 0 i32 0";
        rt.exec(command);}

    public void set_NORMAL_EXIT_DELAY(int sec) throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 39 i32 0 i32 0 i32 16 i32 " +sec+ " i32 0 i32 0";
        rt.exec(command);}

    public void get_LONG_ENTRY_DELAY() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 36 i32 0 i32 0 i32 114 i32 0 i32 0";
        rt.exec(command);}

    public void set_LONG_ENTRY_DELAY (int sec) throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 39 i32 0 i32 0 i32 114 i32 " + sec + " i32 0 i32 0";
        rt.exec(command);}

    public void get_LONG_EXIT_DELAY() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 36 i32 0 i32 0 i32 115 i32 0 i32 0";
        rt.exec(command);}

    public void set_LONG_EXIT_DELAY (int sec) throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 39 i32 0 i32 0 i32 115 i32 " + sec + " i32 0 i32 0";
        rt.exec(command);}

    public void get_AUTO_BYPASS() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 37 i32 0 i32 0 i32 19 i32 0 i32 0";
        rt.exec(command);}

    public void set_AUTO_BYPASS() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 40 i32 0 i32 0 i32 19 i32 0 i32 0 i32 0";
        rt.exec(command);}

    public void get_AUTO_STAY() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 37 i32 0 i32 0 i32 20 i32 0 i32 0";
        rt.exec(command);}

        //State: 0 = disable; 1 = enable
    public void set_AUTO_STAY(int state) throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 40 i32 0 i32 0 i32 20 i32 "+state+" i32 0 i32 0";
        rt.exec(command);}

    public void get_ARM_STAY_NO_DELAY() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 37 i32 0 i32 0 i32 21 i32 0 i32 0";
        rt.exec(command);}

    public void set_ARM_STAY_NO_DELAY_disable() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 40 i32 0 i32 0 i32 21 i32 0 i32 0 i32 0";
        rt.exec(command);}

    public void set_ARM_STAY_NO_DELAY_enable() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 40 i32 0 i32 0 i32 21 i32 1 i32 0 i32 0";
        rt.exec(command);}

    public void get_KEYFOB_NO_DELAY() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 37 i32 0 i32 0 i32 22 i32 0 i32 0";
        rt.exec(command);}

    public void set_KEYFOB_NO_DELAY_disable() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 40 i32 0 i32 0 i32 22 i32 0 i32 0 i32 0";
        rt.exec(command);}

    public void set_KEYFOB_NO_DELAY_enable() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 40 i32 0 i32 0 i32 22 i32 1 i32 0 i32 0";
        rt.exec(command);}

    public void get_RF_JAM_DETECT() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 37 i32 0 i32 0 i32 25 i32 0 i32 0";
        rt.exec(command);}

    public void set_RF_JAM_DETECT_disable() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 40 i32 0 i32 0 i32 25 i32 0 i32 0 i32 0";
        rt.exec(command);}

    public void set_RF_JAM_DETECT_enable() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 40 i32 0 i32 0 i32 25 i32 1 i32 0 i32 0";
        rt.exec(command);}

    public void get_TEMPERATURE_SCALE() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 36 i32 0 i32 0 i32 28 i32 0 i32 0";
        rt.exec(command);}

    public void set_TEMPERATURE_SCALE() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 39 i32 0 i32 0 i32 28 i32 1 i32 0 i32 0";
        rt.exec(command);}

    public void get_LOSS_OF_SUPERVISORY_TIMEOUT() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 36 i32 0 i32 0 i32 31 i32 0 i32 0";
        rt.exec(command);}

    public void set_LOSS_OF_SUPERVISORY_TIMEOUTY_12h() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 39 i32 0 i32 0 i32 31 i32 12 i32 0 i32 0";
        rt.exec(command);}

    public void set_LOSS_OF_SUPERVISORY_TIMEOUTY_24h() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 39 i32 0 i32 0 i32 31 i32 24 i32 0 i32 0";
        rt.exec(command);}

    public void get_WiFi() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 37 i32 0 i32 0 i32 32 i32 0 i32 0";
        rt.exec(command);}

    public void set_WiFi() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 40 i32 0 i32 0 i32 32 i32 0 i32 0 i32 0";
        rt.exec(command);}

    public void get_WiFi_name() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 38 i32 0 i32 0 i32 33 i32 0 i32 0";
        rt.exec(command);}

    public void set_WiFi_name(String Name) throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 41 i32 0 i32 0 i32 33 s16 " + Name + " i32 0 i32 0";
        rt.exec(command);}

    public void get_SECURE_CAMERA() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 37 i32 0 i32 0 i32 34 i32 0 i32 0";
        rt.exec(command);}

    public void set_SECURE_CAMERA_enable() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 40 i32 0 i32 0 i32 34 i32 1 i32 0 i32 0";
        rt.exec(command);}

    public void set_SECURE_CAMERA_disable() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 40 i32 0 i32 0 i32 34 i32 0 i32 0 i32 0";
        rt.exec(command);}

    public void get_SECURE_ARMING() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 37 i32 0 i32 0 i32 35 i32 0 i32 0";
        rt.exec(command);}

    public void set_SECURE_ARMING_enable() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 40 i32 0 i32 0 i32 35 i32 1 i32 0 i32 0";
        rt.exec(command);}

    public void set_SECURE_ARMING_disable() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 40 i32 0 i32 0 i32 35 i32 0 i32 0 i32 0";
        rt.exec(command);}

    public void get_NO_ARMING_ON_LOW_BATTERY() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 37 i32 0 i32 0 i32 36 i32 0 i32 0";
        rt.exec(command);}

    public void set_NO_ARMING_ON_LOW_BATTERY_enable() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 40 i32 0 i32 0 i32 36 i32 1 i32 0 i32 0";
        rt.exec(command);}

    public void set_NO_ARMING_ON_LOW_BATTERY_disable() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 40 i32 0 i32 0 i32 36 i32 0 i32 0 i32 0";
        rt.exec(command);}

    public void get_SIA_LIMITS() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 37 i32 0 i32 0 i32 37 i32 0 i32 0";
        rt.exec(command);}

    public void set_SIA_LIMITS_enable() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 40 i32 0 i32 0 i32 37 i32 1 i32 0 i32 0";
        rt.exec(command);}

    public void set_SIA_LIMITS_disable() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 40 i32 0 i32 0 i32 37 i32 0 i32 0 i32 0";
        rt.exec(command);}

    public void get_SPEAKER_VOLUME() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 36 i32 0 i32 0 i32 39 i32 0 i32 0";
        rt.exec(command);}

    public void set_SPEAKER_VOLUME() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 39 i32 0 i32 0 i32 39 i32 20 i32 0 i32 0";
        rt.exec(command);}

    public void get_ALL_VOICE_PROMPTS() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 37 i32 0 i32 0 i32 42 i32 0 i32 0";
        rt.exec(command);}

    public void set_ALL_VOICE_PROMPTS() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 40 i32 0 i32 0 i32 42 i32 1 i32 0 i32 0";
        rt.exec(command);}

    public void get_SENSOR_VOICE_PROMPTS() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 37 i32 0 i32 0 i32 43 i32 0 i32 0";
        rt.exec(command);}

    public void set_SENSOR_VOICE_PROMPTS() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 40 i32 0 i32 0 i32 43 i32 1 i32 0 i32 0";
        rt.exec(command);}

    public void get_PANEL_VOICE_PROMPTS() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 37 i32 0 i32 0 i32 44 i32 0 i32 0";
        rt.exec(command);}

    public void set_PANEL_VOICE_PROMPTS() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 40 i32 0 i32 0 i32 44 i32 1 i32 0 i32 0";
        rt.exec(command);}

    public void get_SAFETY_SENSORS_VOICE_PROMPTS() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 37 i32 0 i32 0 i32 45 i32 0 i32 0";
        rt.exec(command);}

    public void set_SAFETY_SENSORS_VOICE_PROMPTS() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 40 i32 0 i32 0 i32 45 i32 1 i32 0 i32 0";
        rt.exec(command);}

    public void get_ALL_CHIMES() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 37 i32 0 i32 0 i32 46 i32 0 i32 0";
        rt.exec(command);}

    public void set_ALL_CHIMES() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 40 i32 0 i32 0 i32 46 i32 1 i32 0 i32 0";
        rt.exec(command);}

    public void get_SENSOR_CHIMES() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 37 i32 0 i32 0 i32 47 i32 0 i32 0";
        rt.exec(command);}

    public void set_SENSOR_CHIMES() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 40 i32 0 i32 0 i32 47 i32 1 i32 0 i32 0";
        rt.exec(command);}

    public void get_PANEL_CHIMES() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 37 i32 0 i32 0 i32 48 i32 0 i32 0";
        rt.exec(command);}

    public void set_PANEL_CHIMES() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 40 i32 0 i32 0 i32 48 i32 1 i32 0 i32 0";
        rt.exec(command);}

    public void get_SAFETY_SENSORS_CHIMES() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 37 i32 0 i32 0 i32 49 i32 0 i32 0";
        rt.exec(command);}

    public void set_SAFETY_SENSORS_CHIMES() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 40 i32 0 i32 0 i32 49 i32 1 i32 0 i32 0";
        rt.exec(command);}

    public void get_DISPLAY_BRIGHTNES() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 36 i32 0 i32 0 i32 50 i32 0 i32 0";
        rt.exec(command);}

    public void set_DISPLAY_BRIGHTNES() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 39 i32 0 i32 0 i32 50 i32 10 i32 0 i32 0";
        rt.exec(command);}

    public void get_DURESS_AUTHENTICATION() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 37 i32 0 i32 0 i32 61 i32 0 i32 0";
        rt.exec(command);}

    public void set_DURESS_AUTHENTICATION_disable() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 40 i32 0 i32 0 i32 61 i32 0 i32 0 i32 0";
        rt.exec(command);}

    public void set_DURESS_AUTHENTICATION_enable() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 40 i32 0 i32 0 i32 61 i32 1 i32 0 i32 0";
        rt.exec(command);}

    public void get_CUSTOMER_ACCOUNT() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 38 i32 0 i32 0 i32 62 i32 0 i32 0";
        rt.exec(command);}

    public void set_CUSTOMER_ACCOUNT(int BCBC) throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 41 i32 0 i32 0 i32 62 s16 " + BCBC +" i32 0 i32 0";
        rt.exec(command);}

    public void get_ZWAVE_ON_OFF() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 37 i32 0 i32 0 i32 71 i32 0 i32 0";
        rt.exec(command);}

    public void set_ZWAVE_ON_OFF() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 40 i32 0 i32 0 i32 71 i32 1 i32 0 i32 0";
        rt.exec(command);}

    public void get_POWER_MANAGEMENT_ON_OFF() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 37 i32 0 i32 0 i32 73 i32 0 i32 0";
        rt.exec(command);}

    public void set_POWER_MANAGEMENT_ON_OFF_enable() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 40 i32 0 i32 0 i32 73 i32 1 i32 0 i32 0";
        rt.exec(command);}

    public void set_POWER_MANAGEMENT_ON_OFF_disable() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 40 i32 0 i32 0 i32 73 i32 0 i32 0 i32 0";
        rt.exec(command);}

    public void get_AUTO_EXIT_TIME_EXTENSION() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 37 i32 0 i32 0 i32 84 i32 0 i32 0";
        rt.exec(command);}

    public void set_AUTO_EXIT_TIME_EXTENSION(boolean Value) throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 40 i32 0 i32 0 i32 84 i32 " + Value + " i32 0 i32 0";
        rt.exec(command);}

    public void get_DEVICE_LIMIT_SMART_SOCKET() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 36 i32 0 i32 0 i32 85 i32 0 i32 0";
        rt.exec(command);}

    public void set_DEVICE_LIMIT_SMART_SOCKET(int LIMIT) throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 39 i32 0 i32 0 i32 85 i32 10 " + LIMIT + " i32 0 i32 0";
        rt.exec(command);}

    public void get_DEVICE_LIMIT_THERMOSTAT() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 36 i32 0 i32 0 i32 86 i32 0 i32 0";
        rt.exec(command);}

    public void set_DEVICE_LIMIT_THERMOSTAT(int LIMIT) throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 39 i32 0 i32 0 i32 86 i32 5 " + LIMIT + " i32 0 i32 0";
        rt.exec(command);}

    public void get_DEVICE_LIMIT_DOORLOCK() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 36 i32 0 i32 0 i32 87 i32 0 i32 0";
        rt.exec(command);}

    public void set_DEVICE_LIMIT_DOORLOCK(int LIMIT) throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 39 i32 0 i32 0 i32 87 i32 4 " + LIMIT + " i32 0 i32 0";
        rt.exec(command);}

    public void get_DEVICE_LIMIT_DIMMER() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 36 i32 0 i32 0 i32 88 i32 0 i32 0";
        rt.exec(command);}

    public void set_DEVICE_LIMIT_DIMMER(int LIMIT) throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 39 i32 0 i32 0 i32 88 i32 4 " + LIMIT + " i32 0 i32 0";
        rt.exec(command);}

    public void get_AUTO_UPLOAD_LOGS() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 37 i32 0 i32 0 i32 90 i32 0 i32 0";
        rt.exec(command);}

    public void set_AUTO_UPLOAD_LOGS(boolean Value) throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 40 i32 0 i32 0 i32 90 i32 " + Value + " i32 0 i32 0";
        rt.exec(command);}
    public void get_FIRE_VERIFICATION() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 37 i32 0 i32 0 i32 100 i32 0 i32 0";
        rt.exec(command);}

    public void set_FIRE_VERIFICATION(boolean Value) throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 40 i32 0 i32 0 i32 100 i32 " + Value + " i32 0 i32 0";
        rt.exec(command);}

        ///////////////////////NETWORK_FAILURE_TIMER(Cell Signal Timeout)////////////////////////////////
    public void get_Cell_Signal_Timeout() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 36 i32 0 i32 0 i32 101 i32 0 i32 0";
        rt.exec(command);}

    public void set_Cell_Signal_Timeout(int Value) throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 39 i32 0 i32 0 i32 101 i32 " + Value + " i32 0 i32 0";
        rt.exec(command);}

    public void get_SEVERE_WEATHER_SIREN_WARNING() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 37 i32 0 i32 0 i32 103 i32 0 i32 0";
        rt.exec(command);}

    public void set_SEVERE_WEATHER_SIREN_WARNING(boolean Value) throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 40 i32 0 i32 0 i32 103 i32 " + Value + " i32 0 i32 0";
        rt.exec(command);}

    public void get_SECURE_DELETE_IMAGES() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 37 i32 0 i32 0 i32 104 i32 0 i32 0";
        rt.exec(command);}

    public void set_SECURE_DELETE_IMAGES(boolean Value) throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 40 i32 0 i32 0 i32 104 i32 " + Value + " i32 0 i32 0";
        rt.exec(command);}

    public void get_USER_NAME (int userID) throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 38 i32 0 i32 0 i32 126 i32 " + userID + " i32 0";
        rt.exec(command);}

    public void set_USER_NAME (String userName, int userID) throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 41 i32 0 i32 0 i32 126 s16 " + userName + " i32 " + userID + " i32 0";
        rt.exec(command);}

    public void get_DEVICE_LIMIT_OTHERDEVICES () throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 36 i32 0 i32 0 i32 125 i32 0 i32 0";
        rt.exec(command);}

    public void set_DEVICE_LIMIT_OTHERDEVICES( int LIMIT) throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 39 i32 0 i32 0 i32 125 i32 " + LIMIT + " i32 0 i32 0";
        rt.exec(command);}

    public void get_WATER_FREEZE_ALARM () throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 37 i32 0 i32 0 i32 122  i32 0 i32 0";
        rt.exec(command);}

    public void set_WATER_FREEZE_ALARM() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 40 i32 0 i32 0 i32 122  i32 1 i32 0";
        rt.exec(command);}

    public void get_SIREN_ANNUNCIATION () throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 37 i32 0 i32 0 i32 120 i32 0 i32 0";
        rt.exec(command);}

    public void set_SIREN_ANNUNCIATION(boolean Value) throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 40 i32 0 i32 0 i32 120 i32 1 " + Value + " i32 0 i32 0";
        rt.exec(command);}

    public void get_LOSS_OF_SUPERVISORY_EMERGENCY_TIMEOUT () throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 36 i32 0 i32 0 i32 118 i32 0 i32 0";
        rt.exec(command);}

    public void set_LOSS_OF_SUPERVISORY_EMERGENCY_TIMEOUT() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 39 i32 0 i32 0 i32 118 i32 12 i32 0 i32 0";
        rt.exec(command);}

    public void get_ZWAVE_REMOTE_VOICE_PROMPTS () throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 37 i32 0 i32 0 i32 117 i32 0 i32 0";
        rt.exec(command);}

    public void set_ZWAVE_REMOTE_VOICE_PROMPTS(boolean Value) throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 40 i32 0 i32 0 i32 117  i32 0 " + Value + " i32 0 i32 0";
        rt.exec(command);}

    public void get_ZWAVE_DEVICE_VOICE_PROMPTS () throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 37 i32 0 i32 0 i32 116  i32 0 i32 0";
        rt.exec(command);}

// language  ---> "es"
    public void set_LANGUAGE(String language) throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 41 i32 0 i32 0 i32 30 s16 " + language +" i32 0 i32 0";
        rt.exec(command);}

    public void get_LANGUAGE () throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 38 i32 0 i32 0 i32 30 i32 0 i32 0";
        rt.exec(command);}

    public void get_TROUBLE_BEEPS_TIMEOUT() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 36 i32 0 i32 0 i32 77 i32 0 i32 0";
        rt.exec(command);}

    public void sets_to_10__TROUBLE_BEEPS_TIMEOUT () throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 39 i32 0 i32 0 i32 77 i32 10 i32 0 i32 0";
        rt.exec(command);}

    public void sets_to_15__TROUBLE_BEEPS_TIMEOUT () throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 39 i32 0 i32 0 i32 77 i32 20 i32 0 i32 0";
        rt.exec(command);}

    public void get_TROUBLE_BEEPS_TAMPERED() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 37 i32 0 i32 0 i32 76 i32 0 i32 0";
        rt.exec(command);}

    public void set_TROUBLE_BEEPS_TAMPERED_enable () throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 40 i32 0 i32 0 i32 76 i32 1 i32 0 i32 0";
        rt.exec(command);}

    public void set_TROUBLE_BEEPS_TAMPERED_disable () throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 40 i32 0 i32 0 i32 76 i32 0 i32 0 i32 0";
        rt.exec(command);}

    public void get_TROUBLE_BEEPS_LOW_BATTERY() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 37 i32 0 i32 0 i32 75 i32 0 i32 0";
        rt.exec(command);}

    public void set_TROUBLE_BEEPS_LOW_BATTERY_enable () throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 40 i32 0 i32 0 i32 75 i32 1 i32 0 i32 0";
        rt.exec(command);}

    public void set_TROUBLE_BEEPS_LOW_BATTERY_disable () throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 40 i32 0 i32 0 i32 75 i32 0 i32 0 i32 0";
        rt.exec(command);}

    public void get_TROUBLE_BEEPS_LOW_BATTESIA_POWER_RESTORATIONRY() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 37 i32 0 i32 0 i32 74 i32 0 i32 0";
        rt.exec(command);}

    public void set_SIA_POWER_RESTORATION_enable () throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 40 i32 0 i32 0 i32 74 i32 1 i32 0 i32 0";
        rt.exec(command);}

    public void set_SIA_POWER_RESTORATION_disable () throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 40 i32 0 i32 0 i32 74 i32 0 i32 0 i32 0";
        rt.exec(command);}

    public void get_LCD_CALIBRATE() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 40 i32 0 i32 0 i32 72 i32 1 i32 0 i32 0";
        rt.exec(command);}

    ///////////// Photo Frame Settings ////////////////

    public void get_TRANSITION_EFFECT_DURATION() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 36 i32 0 i32 0 i32 51 i32 0 i32 0";
        rt.exec(command);}

    public void set_TRANSITION_EFFECT_DURATION (int Seconds) throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 39 i32 0 i32 0 i32 51 i32 10 " + Seconds + " i32 0 i32 0";
        rt.exec(command);}

    public void get_TRANSITION_EFFECT() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 36 i32 0 i32 0 i32 52 i32 0 i32 0";
        rt.exec(command);}

    public void set_dissolve_TRANSITION_EFFECT() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 39 i32 0 i32 0 i32 52 i32 0 i32 0 i32 0";
        rt.exec(command);}

    public void set_fade_to_black_effect_TRANSITION_EFFECT() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 39 i32 0 i32 0 i32 52 i32 1 i32 0 i32 0";
        rt.exec(command);}

    public void get_TRANSITION_EFFECT_SHUFFLE() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 37 i32 0 i32 0 i32 53 i32 0 i32 0";
        rt.exec(command);}

    public void set_TRANSITION_EFFECT_SHUFFLE_enable () throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 40 i32 0 i32 0 i32 53 i32 1 i32 0 i32 0";
        rt.exec(command);}

    public void set_TRANSITION_EFFECT_SHUFFLE_disable () throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 40 i32 0 i32 0 i32 53 i32 0 i32 0 i32 0";
        rt.exec(command);}

    public void get_SCREEN_SAVER_TYPE() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 36 i32 0 i32 0 i32 54 i32 0 i32 0";
        rt.exec(command);}

    public void set_blank_SCREEN_SAVER_TYPE () throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 39 i32 0 i32 0 i32 54 i32 0 i32 0 i32 0";
        rt.exec(command);}

    public void set_photo_frame_SCREEN_SAVER_TYPE () throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 39 i32 0 i32 0 i32 54 i32 1 i32 0 i32 0";
        rt.exec(command);}

    public void get_SCREEN_SAVER_IDLE_TIME() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 36 i32 0 i32 0 i32 55 i32 0 i32 0";
        rt.exec(command);}

    public void set_10minutes_SCREEN_SAVER_IDLE_TIME () throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 39 i32 0 i32 0 i32 55 i32 600 i32 0 i32 0";
        rt.exec(command);}


    public void get_SCREEN_SAVER_PHOTO_FRAME_OFF() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 38 i32 0 i32 0 i32 56 i32 0 i32 0";
        rt.exec(command);}

////service call qservice 41 i32 0 i32 0 i32 56 s16 "11:00" i32 0 i32 0
    public void set_SCREEN_SAVER_PHOTO_FRAME_OFF (String time) throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 41 i32 0 i32 0 i32 56 s16 "+ time + " i32 0 i32 0 ";
        rt.exec(command);}

    public void get_SCREEN_SAVER_PHOTO_FRAME_on() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 38 i32 0 i32 0 i32 57 i32 0 i32 0";
        rt.exec(command);}

    ////service call qservice 41 i32 0 i32 0 i32 56 s16 "11:00" i32 0 i32 0
    public void set_SCREEN_SAVER_PHOTO_FRAME_On (String time) throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 41 i32 0 i32 0 i32 57 s16 "+ time + " i32 0 i32 0";
        rt.exec(command);}

/////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void get_POWER_MANAGEMENT_SLEEP_TIMEOUT() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 36 i32 0 i32 0 i32 70 i32 0 i32 0";
        rt.exec(command);}

    public void set_POWER_MANAGEMENT_SLEEP_TIMEOUTn () throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 39 i32 0 i32 0 i32 70 i32 10 i32 0 i32 0";
        rt.exec(command);}

    public void get_DEALER_NAME() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 38 i32 0 i32 0 i32 78 i32 0 i32 0";
        rt.exec(command);}

    public void set_DEALER_NAME (String Dealer_Name) throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 41 i32 0 i32 0 i32 78 s16 " + Dealer_Name + " i32 0 i32 0";
        rt.exec(command);}

        //////////////////////////// Contact Info Dealer info ///////////////////////////////////////////
    public void get_DEALER_CONTACT_TAG_LINE_1() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 38 i32 0 i32 0 i32 79 i32 0 i32 0";
        rt.exec(command);}

    public void set_DEALER_CONTACT_TAG_LINE_1 (String tag_line1) throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 41 i32 0 i32 0 i32 79 s16 " + tag_line1 + " i32 0 i32 0";
        rt.exec(command);}

    public void get_DEALER_CONTACT_TAG_LINE_2() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 38 i32 0 i32 0 i32 80 i32 0 i32 0";
        rt.exec(command);}

    public void set_DEALER_CONTACT_TAG_LINE_2 (String tag_line2) throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 41 i32 0 i32 0 i32 80 s16 " + tag_line2 + " i32 0 i32 0";
        rt.exec(command);}

    public void get_DEALER_PHONE_NUMBER() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 38 i32 0 i32 0 i32 81 i32 0 i32 0";
        rt.exec(command);}

    public void set_DEALER_PHONE_NUMBER (int Phone) throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 41 i32 0 i32 0 i32 81 s16 " + Phone + " i32 0 i32 0";
        rt.exec(command);}

    public void get_DEALER_EMAIL_ADDRESS() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 38 i32 0 i32 0 i32 82 i32 0 i32 0";
        rt.exec(command);}

    public void set_DEALER_EMAIL_ADDRESS (String email) throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 41 i32 0 i32 0 i32 82 s16 " + email + " i32 0 i32 0";
        rt.exec(command);}

    public void get_DEALER_WEB_ADDRESS() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 38 i32 0 i32 0 i32 83 i32 0 i32 0";
        rt.exec(command);}

    public void set_DEALER_WEB_ADDRESS (String web) throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 41 i32 0 i32 0 i32 83 s16 " + web + " i32 0 i32 0";
        rt.exec(command);}
////////////////////////////////// HOME OWNER ACCESS //////////////////////////////////////////////

    public void get_HOME_OWNER_ACCESS() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 37 i32 0 i32 0 i32 91 i32 0 i32 0";
        rt.exec(command);}

    public void set_HOME_OWNER_ACCESS (boolean value) throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 40 i32 0 i32 0 i32 91 i32 " + value + " i32 0 i32 0";
        rt.exec(command);}

    public void get_DISARM_PHOTO() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 37 i32 0 i32 0 i32 102 i32 0 i32 0";
        rt.exec(command);}

    public void set_DISARM_PHOTO (boolean value) throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 40 i32 0 i32 0 i32 102 i32 " + value + " i32 0 i32 0";
        rt.exec(command);}

    public void get_HOME_OWNER_SIREN_AND_ALARMS() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 37 i32 0 i32 0 i32 105 i32 0 i32 0";
        rt.exec(command);}

    public void set_HOME_OWNER_SIREN_AND_ALARMS (boolean value) throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 40 i32 0 i32 0 i32 105 i32 " + value + " i32 0 i32 0";
        rt.exec(command);}

    public void get_HOME_OWNER_SECURITY_AND_ARMING() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 37 i32 0 i32 0 i32 106 i32 0 i32 0";
        rt.exec(command);}

    public void set_HOME_OWNER_SECURITY_AND_ARMING (boolean value) throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 40 i32 0 i32 0 i32 106 i32 " + value + " i32 0 i32 0";
        rt.exec(command);}

    public void get_HOME_OWNER_IMAGE_SETTINGS() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 37 i32 0 i32 0 i32 107 i32 0 i32 0";
        rt.exec(command);}

    public void set_HOME_OWNER_IMAGE_SETTINGS (boolean value) throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 40 i32 0 i32 0 i32 107 i32 " + value + " i32 0 i32 0";
        rt.exec(command);}

        ////Used for ALARM PHOTOS in gen1
    public void get_ALARM_VIDEOS() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 37 i32 0 i32 0 i32 109 i32 0 i32 0";
        rt.exec(command);}

    public void set_ALARM_VIDEOS (boolean value) throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 40 i32 0 i32 0 i32 109 i32 " + value + " i32 0 i32 0";
        rt.exec(command);}

    public void get_VIDEO_MEMOS() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 37 i32 0 i32 0 i32 110 i32 0 i32 0";
        rt.exec(command);}

    public void set_VIDEO_MEMOS (boolean value) throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 40 i32 0 i32 0 i32 110 i32 " + value + " i32 0 i32 0";
        rt.exec(command);}

    public void get_ENABLE_ALL_TROUBLE_BEEPS() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 37 i32 0 i32 0 i32 111 i32 0 i32 0";
        rt.exec(command);}

    public void set_ENABLE_ALL_TROUBLE_BEEPS (boolean value) throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 40 i32 0 i32 0 i32 111 i32 " + value + " i32 0 i32 0";
        rt.exec(command);}

    public void get_PANEL_TAMPER_TROUBLE_BEEP() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 37 i32 0 i32 0 i32 112 i32 0 i32 0";
        rt.exec(command);}

    public void set_PANEL_TAMPER_TROUBLE_BEEP (boolean value) throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 40 i32 0 i32 0 i32 112 i32 1 " + value + " i32 0 i32 0";
        rt.exec(command);}

    public void get_PANEL_AUTO_UPDATE() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 37 i32 0 i32 0 i32 113 i32 0 i32 0";
        rt.exec(command);}

    public void set_PANEL_AUTO_UPDATE (boolean value) throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 40 i32 0 i32 0 i32 113 i32 " + value + " i32 0 i32 0";
        rt.exec(command);}

    public void get_LOSS_OF_AC_POWER_TIMEOUT() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 36 i32 0 i32 0 i32 119 i32 0 i32 0";
        rt.exec(command);}

    public void set_LOSS_OF_AC_POWER_TIMEOUT (int value) throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 39 i32 0 i32 0 i32 119 i32 " + value + " i32 0 i32 0";
        rt.exec(command);}

///////////////////////////////// PANEL_INFO_SECONDARY_PANEL /////////////////////////////////////////

    public void get_SECONDARY_PANEL() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 37 i32 0 i32 0 i32 123 i32 0 i32 0";
        rt.exec(command);}

    public void set_SECONDARY_PANEL (boolean value) throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 40 i32 0 i32 0 i32 123 i32 1 " + value + " i32 0 i32 0";
        rt.exec(command);}

////////////////////////////////////ADC/////////////////////////////////////////////

    // service call qservice 1 i32 0 i32 1536 i32 0 i32 0 i32 0 i32 5 i32 1313 i32 0 i32 0

   public void set_ADC_USER_ADD (int user_code) throws IOException, InterruptedException {
    String command = adbPath + " shell service call qservice 1 i32 0 i32 1536 i32 0 i32 0 i32 0 i32 5 i32 " + user_code + "1313 i32 0 i32 0";
    rt.exec(command);}

   // service call qservice 1 i32 0 i32 1537 i32 0 i32 0 i32 0 i32 5 i32 1313 i32 0 i32 0
    public void set_ADC_USER_DELETE (int user_code) throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 1 i32 0 i32 1537 i32 0 i32 0 i32 0 i32 5 i32 " + user_code + "1313 i32 0 i32 0";
        rt.exec(command);}

    public void EVENT_REPORT_GSM_SIGNAL_STRENGTH () throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 1 i32 0 i32 1539 i32 0 i32 0 i32 0 i32 6 i32 5 i32 0 i32 0";
        rt.exec(command);}

    public void EVENT_ADC_SIREN_OFF () throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 1 i32 0 i32 1540 i32 6 i32 0 i32 0 i32 0 i32 0 i32 0 i32 0";
        rt.exec(command);}

    public void EVENT_ADC_SIREN_ON () throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 1 i32 0 i32 1540 i32 6 i32 0 i32 0 i32 1 i32 0 i32 0 i32 0";
        rt.exec(command);}

/*  EVENT_ADC_SYSTEM_RESET
ADC can restart the QolSys apps: */

    public void ADC_restarts_Qolsys_app () throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 1 i32 0 i32 1552 i32 6 i32 0 i32 0 i32 1 i32 0 i32 0 i32 0";
        rt.exec(command);}
//QOLSYS_RESET_SECONDARY_PANEL
    public void RESET_SECONDARY_PANEL () throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 1 i32 0 i32 1552 i32 6 i32 0 i32 0 i32 7 i32 65 i32 0 i32 0";
        rt.exec(command);}

    public void EVENT_ADC_PANEL_SELF_TEST_START () throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 1 i32 0 i32 1565 i32 0 i32 0 i32 0 i32 262143 i32 0 i32 0 i32 0";
        rt.exec(command);}
    public void EVENT_ADC_PANEL_SELF_TEST_END () throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 1 i32 0 i32 1566 i32 0 i32 0 i32 0 i32 262143 i32 0 i32 0 i32 0";
        rt.exec(command);}

    public void EVENT_ADC_DATETIME_UPDATE () throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 1 i32 0 i32 1538 i32 6 i32 0 i32 0 i32 789456123 i32 0 i32 0 i32 1";
        rt.exec(command);}

      /////////////////////////ADC CONNECTIVITY//////////////////////////
    public void EVENT_ADC_CONNECTIVITY_REGISTRATION_UPDATE () throws IOException, InterruptedException {
          String command = adbPath + " shell service call qservice 1 i32 0 i32 1548 i32 0 i32 0 i32 0 i32 0 i32 0 i32 0 i32 0";
          rt.exec(command);}
    public void EVENT_ADC_CONNECTIVITY_REGISTRATION_UPDATE1 () throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 1 i32 0 i32 1548 i32 0 i32 0 i32 0 i32 1 i32 0 i32 0 i32 0";
        rt.exec(command);}
    public void EVENT_ADC_CONNECTIVITY_REGISTRATION_UPDATE2 () throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 1 i32 0 i32 1548 i32 0 i32 0 i32 0 i32 2 i32 0 i32 0 i32 0";
        rt.exec(command);}
    public void EVENT_ADC_CONNECTIVITY_REGISTRATION_UPDATE3 () throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 1 i32 0 i32 1548 i32 0 i32 0 i32 0 i32 3 i32 0 i32 0 i32 0";
        rt.exec(command);}
    public void EVENT_ADC_CONNECTIVITY_REGISTRATION_UPDATE5 () throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 1 i32 0 i32 1548 i32 0 i32 0 i32 0 i32 5 i32 0 i32 0 i32 0";
        rt.exec(command);}
    public void EVENT_ADC_CONNECTIVITY_CONNECTION_UPDATE () throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 1 i32 0 i32 1549 i32 0 i32 0 i32 0 i32 0 i32 0 i32 0 i32 0";
        rt.exec(command);}

    public void EVENT_ADC_CONNECTIVITY_CONNECTION_UPDATE1 () throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 1 i32 0 i32 1549 i32 0 i32 0 i32 0 i32 1 i32 0 i32 0 i32 0";
        rt.exec(command);}
    public void EVENT_ADC_CONNECTIVITY_CONNECTION_UPDATE2 () throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 1 i32 0 i32 1549 i32 0 i32 0 i32 0 i32 2 i32 0 i32 0 i32 0";
        rt.exec(command);}
    public void EVENT_ADC_CONNECTIVITY_CONNECTION_UPDATE3 () throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 1 i32 0 i32 1549 i32 0 i32 0 i32 0 i32 3 i32 0 i32 0 i32 0";
        rt.exec(command);}
    public void EVENT_ADC_CONNECTIVITY_CONNECTION_UPDATE4 () throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 1 i32 0 i32 1549 i32 0 i32 0 i32 0 i32 4 i32 0 i32 0 i32 0";
        rt.exec(command);}
    public void EVENT_ADC_CONNECTIVITY_CONNECTION_UPDATE5 () throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 1 i32 0 i32 1549 i32 0 i32 0 i32 0 i32 5 i32 0 i32 0 i32 0";
        rt.exec(command);}
    public void EVENT_ADC_CONNECTIVITY_CONNECTION_UPDATE6 () throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 1 i32 0 i32 1549 i32 0 i32 0 i32 0 i32 6 i32 0 i32 0 i32 0";
        rt.exec(command);}
    public void EVENT_ADC_CONNECTIVITY_CONNECTION_UPDATE7 () throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 1 i32 0 i32 1549 i32 0 i32 0 i32 0 i32 7 i32 0 i32 0 i32 0";
        rt.exec(command);}
    public void EVENT_ADC_CONNECTIVITY_CONNECTION_UPDATE8 () throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 1 i32 0 i32 1549 i32 0 i32 0 i32 0 i32 8 i32 0 i32 0 i32 0";
        rt.exec(command);}

    public void EVENT_ADC_CONNECTIVITY_ERROR_UPDATE1 () throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 1 i32 0 i32 1550 i32 0 i32 0 i32 0 i32 1 i32 0 i32 0 i32 0";
        rt.exec(command);}
    public void EVENT_ADC_CONNECTIVITY_ERROR_UPDATE2 () throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 1 i32 0 i32 1550 i32 0 i32 0 i32 0 i32 2 i32 0 i32 0 i32 0";
        rt.exec(command);}
    public void EVENT_ADC_CONNECTIVITY_ERROR_UPDATE4 () throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 1 i32 0 i32 1550 i32 0 i32 0 i32 0 i32 4 i32 0 i32 0 i32 0";
        rt.exec(command);}
    public void EVENT_ADC_CONNECTIVITY_ERROR_UPDATE8 () throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 1 i32 0 i32 1550 i32 0 i32 0 i32 0 i32 8 i32 0 i32 0 i32 0";
        rt.exec(command);}
    public void EVENT_ADC_CONNECTIVITY_ERROR_UPDATE16 () throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 1 i32 0 i32 1550 i32 0 i32 0 i32 0 i32 16 i32 0 i32 0 i32 0";
        rt.exec(command);}
    public void EVENT_ADC_CONNECTIVITY_ERROR_UPDATE32 () throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 1 i32 0 i32 1550 i32 0 i32 0 i32 0 i32 32 i32 0 i32 0 i32 0";
        rt.exec(command);}
    public void EVENT_ADC_CONNECTIVITY_ERROR_UPDATE64 () throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 1 i32 0 i32 1550 i32 0 i32 0 i32 0 i32 64 i32 0 i32 0 i32 0";
        rt.exec(command);}
    public void EVENT_ADC_CONNECTIVITY_ERROR_UPDATE128 () throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 1 i32 0 i32 1550 i32 0 i32 0 i32 0 i32 128 i32 0 i32 0 i32 0";
        rt.exec(command);}
    public void EVENT_ADC_CONNECTIVITY_ERROR_UPDATE256 () throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 1 i32 0 i32 1550 i32 0 i32 0 i32 0 i32 256 i32 0 i32 0 i32 0";
        rt.exec(command);}
    public void EVENT_ADC_CONNECTIVITY_ERROR_UPDATE512 () throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 1 i32 0 i32 1550 i32 0 i32 0 i32 0 i32 512 i32 0 i32 0 i32 0";
        rt.exec(command);}
    public void EVENT_ADC_CONNECTIVITY_ERROR_UPDATE1024 () throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 1 i32 0 i32 1550 i32 0 i32 0 i32 0 i32 1024 i32 0 i32 0 i32 0";
        rt.exec(command);}
    public void EVENT_ADC_CONNECTIVITY_ERROR_UPDATE2048 () throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 1 i32 0 i32 1550 i32 0 i32 0 i32 0 i32 2048 i32 0 i32 0 i32 0";
        rt.exec(command);}
    public void EVENT_ADC_CONNECTIVITY_ERROR_UPDATE4096 () throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 1 i32 0 i32 1550 i32 0 i32 0 i32 0 i32 4096 i32 0 i32 0 i32 0";
        rt.exec(command);}
    public void EVENT_ADC_CONNECTIVITY_ERROR_UPDATE8192 () throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 1 i32 0 i32 1550 i32 0 i32 0 i32 0 i32 8192 i32 0 i32 0 i32 0";
        rt.exec(command);}
   }