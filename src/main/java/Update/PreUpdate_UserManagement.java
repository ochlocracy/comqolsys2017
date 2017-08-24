package Update;

import ADC.ADC;
import Panel.*;
import jxl.read.biff.BiffException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PreUpdate_UserManagement extends Setup {

    String page_name = "PreUpdate User Management set up";
    Logger logger = Logger.getLogger(page_name);
    ADC adc = new ADC();
    PanelInfo_ServiceCalls servcall = new PanelInfo_ServiceCalls();

    public PreUpdate_UserManagement() throws IOException, BiffException {}

    public void navigateToUserManagementPage() throws InterruptedException {
        Advanced_Settings_Page advanced =  PageFactory.initElements(driver, Advanced_Settings_Page.class);
        navigate_to_Advanced_Settings_page();
        advanced.USER_MANAGEMENT.click();
        Thread.sleep(1000);
    }
    public void addUser(String Name, String UserCode) throws InterruptedException {
        User_Management_Page user_m =  PageFactory.initElements(driver, User_Management_Page.class);
        user_m.Add_User_Name_field.sendKeys(Name);
        user_m.Add_User_Code_field.sendKeys(UserCode);
        user_m.Add_Confirm_User_Code_field.sendKeys(UserCode);
        try{
            driver.hideKeyboard();
        } catch (Exception e) {}
    }
    public void checkCodes(List<String> exp) throws InterruptedException {
        logger.info("Checking user codes on ADC");
        adc.navigate_to_user_site("Gen2-8334", "qolsys1234");
        adc.getDriver1().findElement(By.id("Users")).click();
        adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_HeaderLinks1_imgReload"))).click();
        Thread.sleep(3000);
        adc.getDriver1().findElement(By.xpath("/html/body/div/form/div[4]/div[2]/div/div[3]/div[3]/div[1]/div/div/" +
                "div[2]/div/div[2]/a[1]/span[1]/span")).click();
        Thread.sleep(3000);
        List<WebElement> li = adc.getDriver1().findElements(By.id("pinCode"));

        int i, size;
        size = li.size();

        for(i = 2; i < size; i++){
            String s1 = li.get(i).getText().split(" ")[0];
            String s2 = exp.get(i-2);
            if(s1.equals(s2))
                logger.info("Pass: User Code " + s2 + " is displayed.");
            else
                logger.info("Fail: " + s1 + " is displayed. Expected " + s2);
        }
    }
    public void editCodes(List<WebElement> li, List<String> exp, int size) throws InterruptedException {
        int i;

        for(i = 1; i < size; i++){
            li.get(i).click();
            String code = exp.get(i-1);
            Thread.sleep(3000);

            WebElement user_code = driver.findElement(By.id("com.qolsys:id/user_pin"));
            WebElement user_code_confirm = driver.findElement(By.id("com.qolsys:id/confirm_pin"));

            user_code.click();
            user_code.clear();
            user_code.sendKeys(code);
            user_code_confirm.click();
            user_code_confirm.clear();
            user_code_confirm.sendKeys(code);

            try{
                driver.hideKeyboard();
            } catch (Exception e) {}

            driver.findElement(By.id("com.qolsys:id/Save")).click();
            Thread.sleep(3000);
        }
    }

    @BeforeTest
    public void capabilities_setup() throws Exception {
        setup_driver(get_UDID(),"http://127.0.1.1", "4723");
        setup_logger(page_name);
    }

    @BeforeMethod
    public void webDriver() {
        adc.webDriverSetUp();
    }

    @Test
    public void addUserUnlimited() throws InterruptedException {
        logger.info("Adding a new user NewUser with the user code 5643");
        User_Management_Page user_m =  PageFactory.initElements(driver, User_Management_Page.class);
        Home_Page home =  PageFactory.initElements(driver, Home_Page.class);
        navigateToUserManagementPage();
        user_m.Add_User.click();
        Thread.sleep(1000);
        addUser("NewUser", "5643");
        user_m.Add_User_add_page.click();
        home.Home_button.click();
        Thread.sleep(4000);
    }
    @Test (priority = 1)
    public void verifyNewUserCodeWorks() throws Exception {
        logger.info("Verifying a new user code is working correctly");
        Home_Page home =  PageFactory.initElements(driver, Home_Page.class);
        ARM_STAY();
        Thread.sleep(3000);
        home.DISARM.click();
        home.Five.click();
        home.Six.click();
        home.Four.click();
        home.Three.click();
        Thread.sleep(1000);
        verify_disarm();

    }
    @Test (priority = 2)
    public void addMasterUnlimited() throws InterruptedException {
        logger.info("Adding a new Master NewMaster with the code 3331");
        User_Management_Page user_m =  PageFactory.initElements(driver, User_Management_Page.class);
        Home_Page home =  PageFactory.initElements(driver, Home_Page.class);
        navigateToUserManagementPage();
        user_m.Add_User.click();
        Thread.sleep(1000);
        addUser("NewMaster", "3331");
        user_m.Add_User_Type_options.click();
        user_m.User_Type_Master.click();
        user_m.Add_User_add_page.click();
        home.Home_button.click();
        Thread.sleep(1000);
    }
    @Test (priority = 3)
    public void verifyNewMasterCodeWorks() throws Exception {
        logger.info("Verifying a new user code is working correctly");
        Home_Page home =  PageFactory.initElements(driver, Home_Page.class);
        ARM_STAY();
        Thread.sleep(3000);
        home.DISARM.click();
        home.Three.click();
        home.Three.click();
        home.Three.click();
        home.One.click();
        Thread.sleep(1000);
        verify_disarm();

    }
    @Test (priority = 4)
    public void addGuestUnlimited() throws InterruptedException {
        logger.info("Adding a new Guest NewGuest with the code 8800");
        User_Management_Page user_m =  PageFactory.initElements(driver, User_Management_Page.class);
        Home_Page home =  PageFactory.initElements(driver, Home_Page.class);
        navigateToUserManagementPage();
        user_m.Add_User.click();
        Thread.sleep(1000);
        addUser("NewGuest", "8800");
        user_m.Add_User_Type_options.click();
        user_m.User_Type_Guest.click();
        user_m.Add_User_add_page.click();
        home.Home_button.click();
        Thread.sleep(1000);
    }
    @Test (priority = 5)
    public void verifyNewGuestCodeWorks() throws Exception {
        logger.info("Verifying a new user code is working correctly");
        Home_Page home =  PageFactory.initElements(driver, Home_Page.class);
        ARM_STAY();
        Thread.sleep(3000);
        home.DISARM.click();
        home.Eight.click();
        home.Eight.click();
        home.Zero.click();
        home.Zero.click();
        Thread.sleep(1000);
        verify_disarm();
    }
    @Test (priority = 6)
    public void addDuress() throws IOException, InterruptedException {
        servcall.set_DURESS_AUTHENTICATION_enable();
    }
    @Test (priority = 7)
    public void verifyADC() throws InterruptedException {
        TimeUnit.MINUTES.sleep(3);
        List<String> exp = Arrays.asList("5643", "3331", "8800", "9998");
        checkCodes(exp);
    }
    @Test (priority = 8)
    public void editPanelCodes() throws InterruptedException {
        int size;

        Home_Page home =  PageFactory.initElements(driver, Home_Page.class);
        navigateToUserManagementPage();

        List<WebElement> li = driver.findElements(By.id("com.qolsys:id/editImg"));
        List<String> exp = Arrays.asList("5641", "3330", "8801", "9997");
        size = li.size();

        editCodes(li, exp, size);

        TimeUnit.MINUTES.sleep(3);
        checkCodes(exp);

        home.Home_button.click();
    }
    @Test (priority = 9)
    public void deleteNewUsers() throws Exception {
        logger.info("Deleting new users from panel");
        User_Management_Page user_m =  PageFactory.initElements(driver, User_Management_Page.class);
        Home_Page home =  PageFactory.initElements(driver, Home_Page.class);
        navigateToUserManagementPage();
        List<WebElement> delete = driver.findElements(By.id("com.qolsys:id/deleteImg"));
        for (int i=3; i>0; i--) {
            delete.get(1).click();
            user_m.User_Management_Delete_User_Ok.click();
        }
        Thread.sleep(1000);
        home.Home_button.click();
        servcall.set_DURESS_AUTHENTICATION_disable();
    }
    @Test (priority = 10)
    public void adcDelete() throws InterruptedException {
        logger.info("Deleting new users from ADC");
        adc.navigate_to_user_site("Gen2-8334", "qolsys1234");
        adc.getDriver1().findElement(By.id("Users")).click();
        Thread.sleep(3000);
        adc.getDriver1().findElement(By.id("ctl00_HeaderLinks1_imgReload")).click();
        Thread.sleep(3000);
        int i;
        for(i = 0; i < 4; i++) {
            //logger.info("deleting user code");
            adc.getDriver1().findElement(By.xpath("/html/body/div/form/div[4]/div[2]/div/div[3]/div[3]/div[1]/div/" +
                    "div/div[5]/div[2]/div[2]/div[1]/a")).click();
            //logger.info("confirming deletion");
            adc.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/form/div[4]/div[2]" +
                    "/div/div[3]/div[3]/div[1]/div/div/div[7]/div[1]/div/div/div[3]/button[1]"))).click();
            Thread.sleep(5000);
        }

        List<WebElement> li = adc.getDriver1().findElements(By.xpath("/html/body/div/form/div[4]/div[2]/div/div[3]/" +
                "div[3]/div[1]/div/div/div[5]/div[2]/div[2]/div[1]/a"));

        if(li.size() == 0)
            logger.info("Pass: User codes deleted successfully");
        else
            logger.info("Fail: Unsuccessful deletion of user codes");
    }

    @AfterMethod
    public void webDriverQuit() {
        adc.getDriver1().quit();
    }

    @AfterTest
    public void tearDown() throws IOException, InterruptedException {
        driver.quit();
    }
}
