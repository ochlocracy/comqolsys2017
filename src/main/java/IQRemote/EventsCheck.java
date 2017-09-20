package IQRemote;

import jxl.read.biff.BiffException;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class EventsCheck extends Setup_Remote {

    public EventsCheck() throws IOException, BiffException {}
    EventContains EventContaints = new EventContains();
    String logcat = "/home/qolsys/IdeaProjects/comqolsys2017/log/test.txt";

    @BeforeMethod
    public void Setup () throws Exception {
        setup_driver("6NJUM1H24Z", "http://127.0.1.1", "4723");
        deleteLogFile(logcat);
    }

    @Test
    public void Test1 () throws Exception {
        System.out.println("Press pair button:");
        Thread.sleep(3000);
        rt.exec(adbPath+ " now=$(date +\"%b_%d_%Y_%k_%M\"");
        rt.exec(adbPath+ " shell logcat -c");
        rt.exec(adbPath+ " logcat|tee $now");

        driver.findElement(By.id("com.qolsys:id/pair_to_primary_button")).click();
        Thread.sleep(25000);
        eventLogsGenerating(logcat, new String[]{
                EventContaints.client_crt,
                EventContaints.ca_crt,
                EventContaints.Certificate_exchange_complete,
                EventContaints.Initialization,
                EventContaints.Attempt_to_connect,
                EventContaints.DB_sync},6);
        Thread.sleep(2000);
    }
    @AfterMethod
    public void TearDown(){
        driver.quit();
    }

}
