package Update;

import Panel.Contact_Us;
import Panel.Emergency_Page;
import Panel.Home_Page;
import Panel.Setup;
import Sensors.Sensors;
import jxl.read.biff.BiffException;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class PreUpdate_Sensors extends Setup {

    String page_name = "Add Sensors + sensors activity";
    Logger logger = Logger.getLogger(page_name);
    Sensors sensors = new Sensors();
    private String open = "06 00";
    private String close = "04 00";
    private String activate = "02 01";

    private String restore = "00 01";

    public PreUpdate_Sensors() throws IOException, BiffException {}
    public void add_primary_call(int zone, int group, int sensor_dec, int sensor_type) throws IOException {
        String add_primary = " shell service call qservice 50 i32 " + zone + " i32 " + group + " i32 " + sensor_dec + " i32 " + sensor_type;
        rt.exec(adbPath + add_primary);
        // shell service call qservice 50 i32 2 i32 10 i32 6619296 i32 1
    }
    public void delete_from_primary(int zone) throws IOException, InterruptedException {
        String deleteFromPrimary = " shell service call qservice 51 i32 " + zone;
        rt.exec(adbPath + deleteFromPrimary);
        System.out.println(deleteFromPrimary);}

    @BeforeTest
    public void capabilities_setup() throws Exception {
        setup_driver(get_UDID(),"http://127.0.1.1", "4723");
        setup_logger(page_name);
    }

    @Test
    public void  addSensors() throws IOException, InterruptedException {
        logger.info("Adding a list of sensors");
        add_primary_call(1, 10, 6619296, 1); // 65 00 A0
        add_primary_call(2, 12, 6619297, 1); // 65 00 A1
        add_primary_call(3, 13, 6619298, 1); // 65 00 A2
        add_primary_call(4, 14, 6619299, 1); // 65 00 A3
        add_primary_call(5, 16, 6619300, 1); // 65 00 A4
        add_primary_call(6, 8, 6619301, 1); // 65 00 A5
        add_primary_call(7, 9, 6619302, 1); // 65 00 A6
        add_primary_call(8, 25, 6619303, 1); // 65 00 A7
        Thread.sleep(1000);
        add_primary_call(9, 15, 5570628, 2); // 55 00 44
        add_primary_call(10, 17, 5570629, 2); // 55 00 45
        add_primary_call(11, 20, 5570630, 2); // 55 00 46
        add_primary_call(12, 25, 5570631, 2); // 55 00 47
        add_primary_call(13, 35, 5570632, 2); // 55 00 48
        Thread.sleep(1000);
        add_primary_call(14, 26, 6750242, 5); // 67 00 22
        add_primary_call(15, 34, 7667882, 6); // 75 00 AA
        add_primary_call(16,13,6750361,19); // 67 00 99
        add_primary_call(17,17,6750355,19); // 67 00 93
        Thread.sleep(1000);
        add_primary_call(18, 10, 6488238, 16); // 63 00 AE
        add_primary_call(19, 12, 6488239, 16); // 63 00 AF
        add_primary_call(20, 25, 6488224, 16); // 63 00 A0
        add_primary_call(21, 13, 6684828, 107); // 66 00 9C
        add_primary_call(22, 17, 6684829, 107); // 66 00 9D
        add_primary_call(23, 52, 7536801, 17); // 73 00 A1
        Thread.sleep(1000);
        add_primary_call(24, 26, 7667810, 111); // 75 00 62
        add_primary_call(25, 38, 7672224, 22); // 75 11 A0
        add_primary_call(26, 1, 6619386, 102); // 65 00 FA
        add_primary_call(27, 6, 6619387, 102); // 65 00 FB
        add_primary_call(28, 4, 6619388, 102); // 65 00 FC
        add_primary_call(29, 0, 8716538, 104); // 85 00 FA
        add_primary_call(30, 2, 8716539, 104); // 85 00 FB
        Thread.sleep(1000);
        add_primary_call(31, 25, 6405546, 109); // 61 BD AA
        add_primary_call(32, 6, 6361649, 21); // 61 12 31
        add_primary_call(33, 0, 6361650, 21); // 61 12 32
    }
    public void open_close(String DLID) throws InterruptedException, IOException {
        sensors.primary_call(DLID, open);
        Thread.sleep(500);
        sensors.primary_call(DLID, close);
        Thread.sleep(500);
    }

    @Test (dependsOnMethods = {"addSensors"})
    public void sensorsCheck() throws Exception {
        logger.info("Open-Close contact sensors");
        Emergency_Page emergency =  PageFactory.initElements(driver, Emergency_Page.class);
        Contact_Us contact =  PageFactory.initElements(driver, Contact_Us.class);
        Home_Page home =  PageFactory.initElements(driver, Home_Page.class);
        for (int i=15; i>0; i--) {
            open_close("65 00 0A");
            open_close("65 00 1A");
            open_close("65 00 2A");
            open_close("65 00 3A");
            open_close("65 00 4A");
            open_close("65 00 5A");
            enter_default_user_code();
            Thread.sleep(1000);
            open_close("65 00 6A");
            enter_default_user_code();
            Thread.sleep(1000);
            open_close("65 00 7A");
            Thread.sleep(1000);

            logger.info("Activate motion sensors");
            sensors.primary_call("55 00 44", activate);
            Thread.sleep(1000);
            sensors.primary_call("55 00 54", activate);
            Thread.sleep(1000);
            sensors.primary_call("55 00 64", activate);
            Thread.sleep(1000);
            sensors.primary_call("55 00 74", activate);
            Thread.sleep(1000);
            sensors.primary_call("55 00 84", activate);
            Thread.sleep(1000);

            logger.info("Activate smoke sensors");
            sensors.primary_call("67 00 22", activate);
            Thread.sleep(1000);
            emergency.Cancel_Emergency.click();
            enter_default_user_code();
            Thread.sleep(1000);

            logger.info("Activate CO sensors");
            sensors.primary_call("75 00 AA", activate);
            Thread.sleep(1000);
            enter_default_user_code();
            Thread.sleep(1000);

            logger.info("Activate glassbreak sensors");
            sensors.primary_call("67 00 99", activate);
            sensors.primary_call("67 00 99", restore);
            Thread.sleep(1000);
            sensors.primary_call("67 00 39", activate);
            sensors.primary_call("67 00 39", restore);
            Thread.sleep(1000);

            logger.info("Open/close tilt sensors");
            open_close("63 00 EA");
            open_close("63 00 FA");
            open_close("63 00 0A");
            Thread.sleep(1000);

            logger.info("Activate IQShock sensors");
            sensors.primary_call("66 00 C9", activate);
            sensors.primary_call("66 00 C9", restore);
            Thread.sleep(1000);
            sensors.primary_call("66 00 D9", activate);
            sensors.primary_call("66 00 D9", restore);
            Thread.sleep(1000);

            logger.info("Activate freeze sensors");
            sensors.primary_call("73 00 1A", activate);
            sensors.primary_call("73 00 1A", restore);
            Thread.sleep(1000);
            enter_default_user_code();
            Thread.sleep(1000);

            logger.info("Activate heat sensors");
            sensors.primary_call("75 00 26", activate);
            Thread.sleep(1000);
            emergency.Cancel_Emergency.click();
            enter_default_user_code();
            Thread.sleep(1000);

            logger.info("Activate water sensors");
            sensors.primary_call("75 11 0A", open);
            Thread.sleep(1000);
            enter_default_user_code();
            Thread.sleep(1000);

            logger.info("Activate keyfob sensors");
            sensors.primary_call("65 00 AF", open);
            Thread.sleep(1000);
            emergency.Cancel_Emergency.click();
            enter_default_user_code();
            Thread.sleep(1000);

            sensors.primary_call("65 00 BF", open);
            Thread.sleep(1000);
            emergency.Cancel_Emergency.click();
            enter_default_user_code();
            Thread.sleep(1000);

            sensors.primary_call("65 00 CF", open);
            Thread.sleep(1000);
            emergency.Cancel_Emergency.click();
            enter_default_user_code();
            Thread.sleep(1000);

            logger.info("Activate keypad sensors");
            sensors.primary_call("85 00 AF", open);
            Thread.sleep(1000);
            emergency.Cancel_Emergency.click();
            enter_default_user_code();
            Thread.sleep(1000);

            sensors.primary_call("85 00 BF", "04 04");
            Thread.sleep(2000);
            verify_armaway();
            sensors.primary_call("85 00 BF", "08 01");
            Thread.sleep(1000);

            logger.info("Activate medical pendant sensors");
            sensors.primary_call("61 12 13", "03 01");
            Thread.sleep(1000);
            emergency.Cancel_Emergency.click();
            enter_default_user_code();
            Thread.sleep(1000);

            sensors.primary_call("61 12 23", "03 01");
            Thread.sleep(1000);
            emergency.Cancel_Emergency.click();
            enter_default_user_code();
            Thread.sleep(1000);

            logger.info("Activate doorbell sensors");
            open_close("61 BD AA");
            Thread.sleep(1000);

            contact.acknowledge_all_alerts();
            swipe_left();
            Thread.sleep(1000);
        }
    }
//    @Test
//    public void deleteSensors() throws IOException, InterruptedException {
//        for (int i = 33; i>0; i--) {
//            delete_from_primary(i);
//        }
//    }
    @AfterTest
    public void tearDown() throws IOException, InterruptedException {
        driver.quit();
    }
}
