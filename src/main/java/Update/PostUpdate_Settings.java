package Update;

import Panel.PanelInfo_ServiceCalls;
import Panel.Setup;
import jxl.read.biff.BiffException;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by nchortek on 8/10/17.
 */
public class PostUpdate_Settings extends Setup{
    String page_name = "Post-Update Panel Settings";
    Logger logger = Logger.getLogger(page_name);
    PanelInfo_ServiceCalls servcall = new PanelInfo_ServiceCalls();

    public PostUpdate_Settings() throws IOException, BiffException {
    }

    @BeforeTest
    public void capabilities_setup() throws Exception {
        setup_driver( get_UDID(),"http://127.0.1.1", "4723");
        setup_logger(page_name);
    }

    @Test
    public void checkSettings() throws IOException, InterruptedException {
        servcall.get_WiFi();
        Thread.sleep(2000);
    }

    @AfterTest
    public void tearDown() throws IOException, InterruptedException {
        driver.quit();
    }
}
