package IQRemote;

import Panel.Configuration;
import Panel.Setup;
import Sensors.EventConstants;
import io.appium.java_client.android.AndroidDriver;
import jxl.read.biff.BiffException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.*;
import java.net.URL;
import java.util.List;

public class Setup_Remote {

    Setup setup = new Setup();
    public AndroidDriver<WebElement> driver;
    public Runtime rt = Runtime.getRuntime();
    Configuration c = new Configuration();
    public String adbPath = c.getAdbPath();
   String logcat = "/home/qolsys/IdeaProjects/comqolsys2017/log/test.txt";

    public Setup_Remote() throws IOException, BiffException {}

    public void setup_driver(String udid_, String url_, String port_) throws Exception {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("deviceName", "IQPanel2");
        cap.setCapability("BROWSER_NAME", "Android");
        cap.setCapability("udid", udid_ );
        cap.setCapability("appPackage", "com.qolsys");
        cap.setCapability("appActivity", "com.qolsys.activites.MainActivity");
        driver = new AndroidDriver<WebElement>(new URL(url_+":"+port_+"/wd/hub"), cap);
    }

    public void eventLogsGenerating(String fileName,String[] findEvent, int length) throws Exception{
        List<LogEntry> logEntries = driver.manage().logs().get("logcat").getAll();
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
        for(int i=0; i<logEntries.size(); i++) {
            String log = logEntries.get(i).getMessage();
            bw.write(log);
            bw.newLine();
            displayingEvent(log,findEvent,length);
        }
        bw.close();
    }
    private void displayingEvent(String log, String[] findEvent, int length){
        for(int j=0;j<length;j++){
            if (log.contains(findEvent[j])) {
                System.out.println(findEvent[j]+" RECEIVED");
            }
        }
    }
    protected void deleteLogFile(String fileName) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(fileName);
        writer.print("");
        writer.close();
    }
    public void LogcatClear() throws Exception{
        rt.exec(adbPath+" logcat -c &");
    }

    public void killLogcat() throws Exception{
        rt.exec(adbPath+" shell busybox pkill logcat");
    }




    @Test
    public void Test1 () throws Exception {
        deleteLogFile(logcat);
        setup.ARM_STAY();
        eventLogsGenerating(logcat, new String[]{
                EventConstants.EVENT_TO_ADC_STAY,
                "*******  ArmingLevelChange: Level: 2, adc_device_class: 14, User: 0, normal_closing_ack: 1"},2); // arm stay
        //ArmingLevelChange: Level: 1, adc_device_class: 14, User: 1, normal_closing_ack: 0  // disarm
        Thread.sleep(2000);
        setup.DISARM();
    }


}
