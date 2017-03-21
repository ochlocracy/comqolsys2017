package Panel;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Panel_Camera_Page {
    @FindBy(id ="com.qolsys:id/delete_img")
    public WebElement camera_delete;
    @FindBy(xpath = "//android.widget.TextView[@text='ARE YOU SURE YOU \n" + "WANT TO DELETE THIS?']")
    public  WebElement camera_delete_title;
    @FindBy (id ="com.qolsys:id/yesBt")
    public WebElement camera_delete_yes;
    @FindBy (id ="com.qolsys:id/noBt")
    public WebElement camera_delete_no;
}
