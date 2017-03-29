package Panel;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Access_Point_Test extends  Setup{

    String page_name = "Qolsys Access Point testing";
    Logger logger = Logger.getLogger(page_name);

    @BeforeMethod
    public void capabilities_setup() throws Exception {
        setup_driver(udid_,"http://127.0.1.1", "4723");
        setup_logger(page_name);}

    @Test
    public void Verify_elements_on_Access_Point_page() throws Exception {
        WiFi_Devices_Page access = PageFactory.initElements(driver, WiFi_Devices_Page.class);
        Advanced_Settings_Page adv = PageFactory.initElements(driver, Advanced_Settings_Page.class);
        Devices_Page devices = PageFactory.initElements(driver, Devices_Page.class);
        Installation_Page inst = PageFactory.initElements(driver, Installation_Page.class);
        navigate_to_Advanced_Settings_page();
        adv.INSTALLATION.click();
        inst.DEVICES.click();
        devices.WiFi_Devices.click();
        access.Qolsys_Access_Point.click();

        element_verification(access.Wifi_Access_Point_summery,"Access Point default summery"); //????
        access.Wifi_Access_Point.click();
        TimeUnit.SECONDS.sleep(1);
        element_verification(access.Wifi_Access_Point_summery_Enabled,"Access Point summery when Enabled");
        element_verification(access.SSID,"SSID");
        element_verification(access.DHCP_Range_summery,"DHCP Range default summery");
        element_verification(access.Change_Password,"Change Password");
        element_verification(access.AP_MODE_summery,"AP Mode default summery");
        access.AP_MODE.click();
        TimeUnit.SECONDS.sleep(1);
        element_verification(access.AP_MODE_BROADCAST,"AP Mode Broadcast");
        element_verification(access.AP_MODE_HIDDEN,"AP Mode Hidden");
        access.Access_Point_Cancel_Button.click();
        swipe_vertical();
        element_verification(access.WPS_PUSH_BUTTON,"WPS Push Button");
        element_verification(access.AP_PASSWORD,"AP Password");
        swipe_vertical_up();
        access.Wifi_Access_Point.click();
        take_screenshot();
    }
    @AfterMethod
    public void tearDown () throws IOException, InterruptedException {
        log.endTestCase(page_name);
        driver.quit();
    }

}
