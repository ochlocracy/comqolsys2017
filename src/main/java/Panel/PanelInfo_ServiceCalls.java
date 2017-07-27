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


}
