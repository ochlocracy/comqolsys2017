package Zwave;

import Panel.Setup;
import jxl.read.biff.BiffException;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeTest;

import java.io.IOException;

/**
 * Created by nchortek on 7/27/17.
 */
public class Device_Renaming extends Setup{

    String page_name = "Lights_Page";
    Logger logger = Logger.getLogger(page_name);


    public Device_Renaming() throws IOException, BiffException {
    }


    @BeforeTest
    public void capabilities_setup() throws Exception {
        setup_driver( get_UDID(),"http://127.0.1.1", "4723");
        setup_logger(page_name);
    }




}
