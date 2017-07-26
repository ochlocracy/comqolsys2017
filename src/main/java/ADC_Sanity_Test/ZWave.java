package ADC_Sanity_Test;

import Panel.Setup;
import jxl.read.biff.BiffException;

import java.io.IOException;

public class ZWave extends Setup{

    private String transmitter_z = "42964b0";
    private String dut = "8ebdbc76";
    String remoteNodeAdd = " shell service call qservice 1 i32 0 i32 1560 i32 0 i32 0 i32 0 i32 0 i32 0 i32 0 i32 0";
    String remoteNodeAbort = " shell service call qservice 1 i32 0 i32 1561 i32 0 i32 0 i32 0 i32 0 i32 0 i32 0 i32 0";

    public ZWave() throws IOException, BiffException {}


    public void includeBridge() throws IOException {
        rt.exec(adbPath + " -s " + dut + remoteNodeAdd);


    }

}
