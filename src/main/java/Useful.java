
import Panel.Setup;
import jxl.read.biff.BiffException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class Useful extends Setup{

    public Useful() throws IOException, BiffException {
    }

    @BeforeMethod
    public void capabilitiesSetup() throws Exception {
        setup_driver(get_UDID(),"http://127.0.1.1", "4723");
    }

    @Test
    public void Test1() throws Exception {
        delete_all_camera_photos();
    }

    @Test
    public void afterMasterReset() throws InterruptedException {
        setDelay(); //11, 12, 13, 13
        enableWiFi();

    }

    @AfterMethod
    public void tearDown () throws IOException, InterruptedException {
        driver.quit();
    }
}
