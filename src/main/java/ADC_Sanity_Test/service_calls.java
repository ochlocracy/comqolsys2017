package ADC_Sanity_Test;

import Panel.Configuration;
import Panel.Setup;
import jxl.read.biff.BiffException;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import java.io.IOException;

public class service_calls extends Setup{
    Configuration c = new Configuration();
    public String adbPath = c.getAdbPath();
    public Logger logger = Logger.getLogger(this.getClass().getName());
    public Runtime rt = Runtime.getRuntime();

    public service_calls() throws IOException, BiffException {
    }

public void getZWaveStatus() throws IOException, InterruptedException {
    String get_st = adbPath + " shell service call qservice 37 i32 0 i32 0 i32 71 i32 0 i32 0";
    rt.exec(get_st);
    String value = (execCmd(get_st)).toString();
    System.out.println(value);
    if (value.contains("00000001")) {
        System.out.println("Zwave is Enabled");
    } else if (value.contains("00000000")) {
        System.out.println("Zwave is Disabled, please Enable the setting");

    }}

    public void setZWaveEnabled() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 40 i32 0 i32 0 i32 71 i32 1 i32 0 i32 0";
        rt.exec(command);         }

    public void setZWaveDisabled() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 40 i32 0 i32 0 i32 71 i32 0 i32 0 i32 0";
        rt.exec(command);            }

    public void set_Remote_Node_add_Start() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 1 i32 0 i32 1560 i32 0 i32 0 i32 0 i32 0 i32 0 i32 0 i32 0";
        rt.exec(command);            }
    public void set_Remote_Node_add_Abort() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 1 i32 0 i32 1561 i32 0 i32 0 i32 0 i32 0 i32 0 i32 0 i32 0";
        rt.exec(command);            }

      /* NodeID = 0 ---> it will start Device clear Activity
                                   valied nodeID --> delete that nodeID in background */
    public void set_Remote_Node_Delete_Start() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 1 i32 0 i32 1562 i32 0 i32 0 i32 0 i32 nodeID i32 0 i32 0 i32 0";
        rt.exec(command);}



    public void set_Remote_Node_Delete_Abort() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 1 i32 0 i32 1563 i32 0 i32 0 i32 0 i32 0 i32 0 i32 0 i32 0";
        rt.exec(command);    }

    public void get_Zwave_network_ID_Home_ID() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 55 i32 0 i32 2 i32 50 i32 0 i32 0";
        rt.exec(command);
        String value = (execCmd(command)).toString();
        System.out.println(value + "*** If Network is enabled\n" +
                "Result: Parcel(00000000 00000004 a14bd9e3 '..........K.')\n" +
                "Home ID is --- e3d94ba1 and 4 is length of the byte array\n" +
                "\n" +
                "Else \n" +
                "Result: Parcel(00000000 ffffffff '........')***");}

    public void set_Rediscovery_Start() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 1 i32 0 i32 1557 i32 0 i32 0 i32 0 i32 0 i32 0 i32 0 i32 0";
        rt.exec(command);    }
    public void set_Rediscovery_abort() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 1 i32 0 i32 1558 i32 0 i32 0 i32 0 i32 0 i32 0 i32 0 i32 0";
        rt.exec(command);    }

    public void get_Zwave_Rediscovery_Result() throws IOException, InterruptedException {
        String command = adbPath + " shell service call qservice 68";
        rt.exec(command);
        String value = (execCmd(command)).toString();
        System.out.println(value); }

    public void get_valied_nodeID() throws IOException, InterruptedException {
    String command = adbPath + " shell service call qservice 37 i32 0 i32 2 i32 21 i32 nodeID i32 0";
    rt.exec(command);
    String value = (execCmd(command)).toString();
    System.out.println(value);
    if (value.contains("00000001")) {
        System.out.println("The nodeId is registered");
    } else if (value.contains("00000000")) {
        System.out.println("The nodeId is not registered");
    } }

}

