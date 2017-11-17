package IQRemote;


import Panel.Panel_Camera_Page;
import Panel.Setup;
import io.appium.java_client.android.AndroidDriver;
import jxl.read.biff.BiffException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class DisarmPhotos extends  Setup_Remote {

    public DisarmPhotos() throws IOException, BiffException {
    }

    EventContains EventContaints = new EventContains();
    String logcat = "/home/qolsys/IdeaProjects/comqolsys2017/log/test.txt";

    @BeforeMethod
    public void setup_driver() throws Exception {
        setup_driver("6NJUMEQPGZ", "http://127.0.1.1", "4723");
        // deleteLogFile(logcat);
    }

    @Test (priority = 0)
    public void Delete_All_Photos() throws Exception {
        Panel_Camera_Page camera = PageFactory.initElements(driver, Panel_Camera_Page.class);
        System.out.println("Delete_All_Photos Begin");
        Thread.sleep(15000);

        swipeFromLefttoRight(); //check
        Thread.sleep(3000);
        try {
            while (camera.Photo_lable.isDisplayed()){
                camera.Camera_delete.click();
                camera.Camera_delete_yes.click();
                enter_default_user_code();}
        }catch (Exception e) {
            System.out.println("No photos left to delete...");
        } finally {
        }
        swipeFromRighttoLeft();
        Thread.sleep(3000);
        rt.exec(adbPath + " ls -l /storage/sdcard0/DisarmPhotos | busybox1.11  wc -l");
    }

    @Test(priority = 1)
    public void Add_Disarm_Photos() throws Exception {
        System.out.println("Add_All_Photos Begin");

        Thread.sleep(15000);
        driver.findElement(By.id("com.qolsys:id/t3_img_disarm")).click();
        driver.findElement(By.id("com.qolsys:id/img_arm_stay")).click();
        Thread.sleep(10000);
        driver.findElement(By.id("com.qolsys:id/t3_img_disarm")).click();
        Thread.sleep(3000);
        driver.findElement(By.id("com.qolsys:id/tv_keyOne")).click();
        driver.findElement(By.id("com.qolsys:id/tv_keyTwo")).click();
        driver.findElement(By.id("com.qolsys:id/tv_keyThree")).click();
        driver.findElement(By.id("com.qolsys:id/tv_keyFour")).click();
        Thread.sleep(5000);



    }
    }

//precondition; delete all photos
//verify from ui and from internal SD

//The idea is to be able to send the event from the IQRemote panel
//and do the verification on the Primary panel and on the ADC dealer website.

//shell commands, see how many files their are
//max of 20 images will be stored.
//creating 21st will overwrite.

//run test. then wait for remote to disconnect and connect again.

//verify on remote




//eventually
//will be on gen 2
//will read logs and compare. log check / library needs to be written
//checking event was sent to adc