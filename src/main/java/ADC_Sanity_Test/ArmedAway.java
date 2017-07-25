package ADC_Sanity_Test;


import ADC.ADC;
import Panel.Setup;
import Sensors.Sensors;
import jxl.read.biff.BiffException;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.io.IOException;

public class ArmedAway extends Setup{

    String page_name = "Arm Stay mode";
    Logger logger = Logger.getLogger(page_name);
    Sensors sensors = new Sensors();
    ADC adc = new ADC();

    public ArmedAway() throws IOException, BiffException {}

    private int Normal_Exit_Delay = 30000;
    private String open = "06 00";
    private String close = "04 00";

    @BeforeTest
    public void capabilities_setup() throws Exception {
        setup_driver(get_UDID(),"http://127.0.1.1", "4723");
        setup_logger(page_name);
    }
    @BeforeMethod
    public  void webDriver(){
        adc.webDriverSetUp();
    }

}
