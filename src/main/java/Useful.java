
import Panel.Setup;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class Useful extends Setup{

    @BeforeMethod
    public void capabilitiesSetup() throws Exception {
        setup_driver(udid_, "http://127.0.1.1", "4723");
    }

    @Test
    public void Test1() throws Exception {
        delete_all_camera_photos();
    }

    @AfterMethod
    public void tearDown () throws IOException, InterruptedException {
        driver.quit();
    }
}
