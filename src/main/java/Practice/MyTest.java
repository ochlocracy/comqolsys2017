package Practice;

import Panel.*;
import jxl.read.biff.BiffException;
import org.junit.AfterClass;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Sleeper;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.IOException;

/**
 * Created by qolsys on 7/18/17.
 */
public class MyTest extends Setup {

    public MyTest () throws IOException, BiffException{

    }

    @BeforeMethod
    public void capabilities_setup() throws Exception {
        setup_driver(get_UDID(),"http://127.0.1.1", "4723");
    }

    @Test
    public void Test1(){
        Settings_Page settings =  PageFactory.initElements(driver, Settings_Page.class);
        Security_Arming_Page arming = PageFactory.initElements(driver, Security_Arming_Page.class);
        Advanced_Settings_Page adv = PageFactory.initElements(driver, Advanced_Settings_Page.class);
        Installation_Page inst = PageFactory.initElements(driver, Installation_Page.class);
        User_Management_Page user = PageFactory.initElements(driver, User_Management_Page.class);
     //   navigate_to_Advanced_Settings_page();





        System.out.println("***************Disarming panel ***************");
        //DISARM();

    }





    @AfterMethod
    public void tearDown () throws IOException, InterruptedException {
        driver.quit();
    }

}
