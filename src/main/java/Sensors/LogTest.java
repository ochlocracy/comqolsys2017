package Sensors;

import Panel.Setup;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class LogTest extends Setup {

    @BeforeMethod
    public void capabilities_setup() throws Exception {
        setup_driver(udid_, "http://127.0.1.1", "4723");
    }

    @Test
    public void Test1 () throws Exception {
        ARM_STAY();
        eventLogsGenerating("/home/qolsys/Desktop/comqolsysPOM/log/test.txt",new String[]{
                EventConstants.EVENT_TO_ADC_STAY,
                "*******  ArmingLevelChange: Level: 2, adc_device_class: 14, User: 0, normal_closing_ack: 1"},2);
        Thread.sleep(2000);
        LogcatClear();

    }

    @AfterMethod
    public void tearDown () throws IOException, InterruptedException {
        driver.quit();
    }
}
