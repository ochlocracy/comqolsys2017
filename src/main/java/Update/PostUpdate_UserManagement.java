package Update;

import Panel.Advanced_Settings_Page;
import Panel.Setup;
import jxl.read.biff.BiffException;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.IOException;

public class PostUpdate_UserManagement extends Setup {

    public PostUpdate_UserManagement() throws IOException, BiffException {}

    String page_name = "PostUpdate User Management check up";
    Logger logger = Logger.getLogger(page_name);

    public void navigateToUserManagementPage() throws InterruptedException {
        Advanced_Settings_Page advanced =  PageFactory.initElements(driver, Advanced_Settings_Page.class);
        navigate_to_Advanced_Settings_page();
        advanced.USER_MANAGEMENT.click();
        Thread.sleep(1000);
    }
    @BeforeTest
    public void capabilities_setup() throws Exception {
        setup_driver(get_UDID(),"http://127.0.1.1", "4723");
        setup_logger(page_name);
    }




    @AfterTest
    public void tearDown() throws IOException, InterruptedException {
        driver.quit();
    }
}
