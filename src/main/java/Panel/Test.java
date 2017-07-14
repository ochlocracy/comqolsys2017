package Panel;

import jxl.read.biff.BiffException;
import org.testng.annotations.BeforeClass;

import java.io.IOException;

public class Test extends Setup{
    public Test() throws IOException, BiffException {}

    public static String execCmd(String cmd) throws java.io.IOException {
        Process proc = Runtime.getRuntime().exec(cmd);
        java.io.InputStream is = proc.getInputStream();
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        String val = "";
        if (s.hasNext()) {
            val = s.next();
        }
        else {
            val = "";
        }
        return val;
    }
    public String split_method (  String str) {
        String a = str.split("\\n")[1];
        return a.split("\\s")[0];
    }

    public String get_UDID() throws IOException {
        String command = "adb devices";
        rt.exec(command);
        String panel_UDID = split_method(execCmd(command));
        return panel_UDID;
    }

    @BeforeClass
    public void capabilities_setup() throws Exception {
        setup_driver(get_UDID(), "http://127.0.1.1", "4723");

    }

    @org.testng.annotations.Test
    public void Test1() throws Exception {
        swipeFromRighttoLeft();
    }
}
