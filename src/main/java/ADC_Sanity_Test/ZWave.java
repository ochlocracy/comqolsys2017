package ADC_Sanity_Test;

import Panel.Configuration;
import Panel.Setup;
import jxl.read.biff.BiffException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class ZWave extends Setup{
    Configuration c = new Configuration();
    private String transmitter = transmitterUDID;
    private String Gen2 = gen2UDID;

    String remoteNodeAdd = " shell service call qservice 1 i32 0 i32 1560 i32 0 i32 0 i32 0 i32 0 i32 0 i32 0 i32 0";
    String remoteNodeAbort = " shell service call qservice 1 i32 0 i32 1561 i32 0 i32 0 i32 0 i32 0 i32 0 i32 0 i32 0";

    public ZWave() throws IOException, BiffException {}


    // bridge will be included to the Gen2 an nodeID 2
    public void includeBridge() throws IOException, InterruptedException {
        Thread.sleep(3000);
        rt.exec(adbPath + " -s " + Gen2 + remoteNodeAdd);
        System.out.println(adbPath + " -s " + Gen2 + remoteNodeAdd);
        Thread.sleep(3000);

        rt.exec(adbPath + " -s " + transmitter + " shell service call zwavetransmitservice 2");
        Thread.sleep(50000);
        rt.exec(adbPath + " -s " + Gen2 + remoteNodeAbort);
        System.out.println(adbPath + " -s " + Gen2 + remoteNodeAbort);
    }
    public void addLights(int Lights_number) throws IOException, InterruptedException {
        Thread.sleep(3000);
        rt.exec(adbPath + " -s " + Gen2 + remoteNodeAdd);
        System.out.println(adbPath + " -s " + Gen2 + remoteNodeAdd);
        Thread.sleep(3000);

        for (int i = Lights_number; i>0; i--){
        rt.exec(adbPath + " -s " + transmitter + " shell service call zwavetransmitservice 3 i32 1");
        System.out.println(adbPath + " -s " + transmitter + " shell service call zwavetransmitservice 3 i32 1");
        Thread.sleep(20000);
        }
        rt.exec(adbPath + " -s " + Gen2 + remoteNodeAbort);
        System.out.println(adbPath + " -s " + Gen2 + remoteNodeAbort);

    }
    public void addThermostat() throws IOException {
        rt.exec(adbPath + " -s " + transmitter + " shell service call zwavetransmitservice 3 i32 3");
    }

    @BeforeMethod
    public void capabilities_setup() throws Exception {
        setup_driver(gen2UDID, "http://127.0.1.1", "4723");
////        setup_logger(page_name);
    }


    @Test
    public void addTranmitter () throws IOException, InterruptedException {
       includeBridge();

    }
    @Test
    public void addLigth() throws IOException, InternalError{

    }

}
