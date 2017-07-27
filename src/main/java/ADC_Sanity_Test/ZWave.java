package ADC_Sanity_Test;

import Panel.Setup;
import jxl.read.biff.BiffException;
import org.testng.annotations.Test;

import java.io.IOException;

public class ZWave extends Setup{

    private String transmitter_z = "42964b0";
    private String dut = "8ebdbc76";
    String remoteNodeAdd = " shell service call qservice 1 i32 0 i32 1560 i32 0 i32 0 i32 0 i32 0 i32 0 i32 0 i32 0";
    String remoteNodeAbort = " shell service call qservice 1 i32 0 i32 1561 i32 0 i32 0 i32 0 i32 0 i32 0 i32 0 i32 0";

    public ZWave() throws IOException, BiffException {}


    // bridge will be included to the DUT an nodeID 2
    public void includeBridge() throws IOException, InterruptedException {
        Thread.sleep(3000);
        rt.exec(adbPath + " -s " + dut + remoteNodeAdd);
        System.out.println(adbPath + " -s " + dut + remoteNodeAdd);
        Thread.sleep(3000);

        rt.exec(adbPath + " -s " + transmitter_z + " shell service call zwavetransmitservice 2");
        Thread.sleep(30000);
        rt.exec(adbPath + " -s " + dut + remoteNodeAbort);
        System.out.println(adbPath + " -s " + dut + remoteNodeAbort);
    }
    public void addLights(int Lights_number) throws IOException, InterruptedException {
        Thread.sleep(3000);
        rt.exec(adbPath + " -s " + dut + remoteNodeAdd);
        System.out.println(adbPath + " -s " + dut + remoteNodeAdd);
        Thread.sleep(3000);

        for (int i = Lights_number; i>0; i--){
        rt.exec(adbPath + " -s " + transmitter_z + " shell service call zwavetransmitservice 3 i32 1");
        System.out.println(adbPath + " -s " + transmitter_z + " shell service call zwavetransmitservice 3 i32 1");
        Thread.sleep(20000);
        }
        rt.exec(adbPath + " -s " + dut + remoteNodeAbort);
        System.out.println(adbPath + " -s " + dut + remoteNodeAbort);

    }
    public void addThermostat() throws IOException {
        rt.exec(adbPath + " -s " + transmitter_z + " shell service call zwavetransmitservice 3 i32 3");
    }

    @Test
    public void Test1 () throws IOException, InterruptedException {
       includeBridge();
       addLights(2);

    }

}
