package Panel;

import com.sun.org.apache.xerces.internal.xs.StringList;
import jxl.read.biff.BiffException;
import org.junit.Before;
import org.junit.Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.io.IOException;

/**
 * Created by qolsys on 7/18/17.
 */
public class MyTest extends Setup {

    public MyTest() throws IOException, BiffException {
    }

    @BeforeMethod
    public void capabilities_setup() throws Exception {
        setup_driver(get_UDID(),"http://127.0.1.1", "4723");
    }

    @Test
    public void Test1() {

    }

    {
    ARM_STAY();
    System.out.println("arm stayed");
}

    @AfterMethod
    public void Test2() {

    }
}


