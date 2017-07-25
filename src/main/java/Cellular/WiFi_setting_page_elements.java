package Cellular;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WiFi_setting_page_elements {
    @FindBy(id = "com.qolsys:id/wire_less_toggle")
    public WebElement Checkbox;
    @FindBy(xpath = "//android.widget.TextView[@text='Connected']")
    public WebElement Network_connection_status;
}
