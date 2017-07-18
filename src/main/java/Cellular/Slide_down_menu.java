package Cellular;

import Panel.Setup;
import Panel.Slide_Menu;
import jxl.read.biff.BiffException;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class Slide_down_menu extends Setup{
    public Slide_down_menu() throws IOException, BiffException {}

        String page_name = "Slide down Menu";
       Logger logger = Logger.getLogger(page_name);

      @BeforeMethod
    public void capabilities_setup() throws Exception {
        setup_driver(udid_, "http://127.0.1.1", "4723");
        setup_logger(page_name);
    }
    @Test
    public void Check_all_elements_on_Slide_Menu() throws Exception {
        Slide_Menu menu = PageFactory.initElements(driver, Slide_Menu.class);
        logger.info("Verifying elements on the page...");
        Thread.sleep(1000); Thread.sleep(1000);
        menu.Slide_menu_open.click();
        element_verification(menu.Cell_bar, "Cellular");
        menu.Cell_bar.click();
        Thread.sleep(1000);
        element_verification(menu.Cell_info, "Cellular info");

        String Carrier_name = (menu.Cell_info.getText()).split(":")[0];
        System.out.println(Carrier_name);
       String Cellular_Signal_Strength = (menu.Cell_info.getText()).split("\n")[0];



       //Cellular_Signal_Strength(new String(Cellular_Signal_Strength)).split("\\s")[0];



       // String Cellular_Signal_Strength = (menu.Cell_info.getText()).split("\\(\\w+\\)")[0];
       System.out.println(Cellular_Signal_Strength);

        element_verification(menu.Slide_menu_close, "Slide menu close");

        menu.Slide_menu_close.click();

/*
    public String split_method (str) {
        String a = Cellular_Signal_Strength.split("\\n")[1];
        return a.split("\\s")[0];

      //  public String method (String str) {
       //     return str.split("°")[0];
      //  } /**/

    }

    @AfterMethod
    public void tearDown () throws IOException, InterruptedException {
        log.endTestCase(page_name);
        driver.quit();
    }
}

