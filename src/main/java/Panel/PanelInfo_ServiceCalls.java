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


    public PanelInfo_ServiceCalls() throws IOException, BiffException {
    }
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

    public void set_ENTRY_DELAY() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 39 i32 0 i32 0 i32 15 i32 10 i32 0 i32 0";
        rt.exec(command);}

    public void get_EXIT_DELAY() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 36 i32 0 i32 0 i32 16 i32 0 i32 0";
        rt.exec(command);}

    public void set_EXIT_DELAY() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 39 i32 0 i32 0 i32 16 i32 10 i32 0 i32 0";
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

    public void set_AUTO_STAY() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 40 i32 0 i32 0 i32 20 i32 1 i32 0 i32 0";
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

    public void get_LONG_ENTRY_DELAY() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 36 i32 0 i32 0 i32 114 i32 0 i32 0";
        rt.exec(command);}

    public void set_LONG_ENTRY_DELAY (int LIMIT) throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 39 i32 0 i32 0 i32 114 i32 " + LIMIT + " i32 0 i32 0";
        rt.exec(command);}

    public void get_LONG_EXIT_DELAY() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 36 i32 0 i32 0 i32 115 i32 0 i32 0";
        rt.exec(command);}

    public void set_LONG_EXIT_DELAY (int LIMIT) throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 39 i32 0 i32 0 i32 115 i32 " + LIMIT + " i32 0 i32 0";
        rt.exec(command);}








}
