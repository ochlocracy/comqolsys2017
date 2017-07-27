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

    public void get_InstallerUSER_CODE() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 38 i32 0 i32 0 i32 5 i32 241 i32 0";
        rt.exec(command);
        String value = (execCmd(command)).toString();
        System.out.println("Installer code" + value);}

    public void set_USER_CODE(int UserCode) throws IOException, InterruptedException {
        String command = adbPath + " service call qservice 41 i32 0 i32 0 i32 5 s16 " + UserCode + " i32 241 i32 0";
        rt.exec(command);}





}
