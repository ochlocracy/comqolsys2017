package Zwave;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
//import android.widget.SeekBar;
//import android.widget.TextView;

/**
 * Created by nchortek on 6/22/17.
 */
public class Lights_Page_beta {

    @FindBy(id = "com.qolsys:id/allOn")
    public WebElement On_Button;
    @FindBy(id = "com.qolsys:id/allOff")
    public WebElement Off_Button;
    @FindBy(xpath = "//android.widget.TextView[@text='LIGHT1']")
    public WebElement Light1;
    @FindBy(xpath = "//android.widget.TextView[@text='LIGHT2']")
    public WebElement Light2;
    @FindBy(id = "com.qolsys:id/lightSelect")
    public WebElement Light_Select;

    //@FindBy(id = "com.qolsys:id/dimmer_seek_bar")
    //public WebElement Dimmer_Bar;



}
