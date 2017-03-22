package Panel;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Panel_Camera_Page {
    @FindBy(id ="com.qolsys:id/tv_name")
    public WebElement Panel_camera_page_title;
    @FindBy(id ="com.qolsys:id/camera_arm_disarm_filter")
    public  WebElement Disarm_photos;
    @FindBy(id ="com.qolsys:id/camera_settings_filter")
    public  WebElement Settings_photos;
    @FindBy(id ="com.qolsys:id/camera_alarms_filter")
    public  WebElement Alarms_photo;
    @FindBy(id ="com.qolsys:id/camera_all_filter")
    public  WebElement All_photos;
    @FindBy(id ="com.qolsys:id/relativeLy")
    public  WebElement Photo_lable;

    @FindBy(id ="com.qolsys:id/delete_img")
    public WebElement Camera_delete;
    @FindBy(xpath = "//android.widget.TextView[@text='ARE YOU SURE YOU \n" + "WANT TO DELETE THIS?']")
    public  WebElement Camera_delete_title;
    @FindBy (id ="com.qolsys:id/yesBt")
    public WebElement Camera_delete_yes;
    @FindBy (id ="com.qolsys:id/noBt")
    public WebElement Camera_delete_no;
}
