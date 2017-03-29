package Panel;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WiFi_Devices_Page {
    @FindBy(xpath = "//android.widget.TextView[@text='Associated Wifi Clients']")
    public WebElement Associated_Wifi_Clients;
    @FindBy(xpath = "//android.widget.TextView[@text='Qolsys Access Point']")
    public WebElement Qolsys_Access_Point;
    @FindBy(xpath = "//android.widget.TextView[@text='Wifi Access Point']")
    public WebElement Wifi_Access_Point;
    @FindBy(xpath = "//android.widget.TextView[@text='Wifi Access Point Disabled']")
    public WebElement Wifi_Access_Point_summery;
    @FindBy(xpath = "//android.widget.TextView[@text='Wifi Access Point Enabled']")
    public WebElement Wifi_Access_Point_summery_Enabled;
    @FindBy(xpath = "//android.widget.TextView[@text='SSID']")
    public WebElement SSID;
    @FindBy(className = "//android.widget.EditText']")
    public WebElement SSID_txt_field;
    @FindBy(xpath = "//android.widget.TextView[@text='DHCP Range']")
    public WebElement DHCP_Range;
    @FindBy(className = "//android.widget.EditText']")
    public WebElement DHCP_Range_txt_field;
    @FindBy(xpath = "//android.widget.TextView[@text='DHCP Range is set to 50']")
    public WebElement DHCP_Range_summery;
    @FindBy(xpath = "//android.widget.TextView[@text='Change Password']")
    public WebElement Change_Password;
    @FindBy(xpath = "//android.widget.TextView[@text='Password Changes']")
    public WebElement Change_Password_title;
    @FindBy(id= "com.qolsys:id/uiEdit")
    public WebElement New_Password_field;
    @FindBy(id= "com.qolsys:id/uichngepwd")
    public WebElement Confirm_Password_field;
    @FindBy(id = "com.qolsys:id/positive_button")
    public WebElement Access_Point_OK_Button;
    @FindBy(id = "com.qolsys:id/negative_button")
    public WebElement Access_Point_Cancel_Button;
    @FindBy(xpath = "//android.widget.TextView[@text='AP MODE']")
    public WebElement AP_MODE;
    @FindBy(xpath = "//android.widget.TextView[@text='Ap Mode set to HIDDEN']")
    public WebElement AP_MODE_summery;
    @FindBy(xpath = "//android.widget.TextView[@text='BROADCAST']")
    public WebElement AP_MODE_BROADCAST;
    @FindBy(xpath = "//android.widget.TextView[@text='HIDDEN']")
    public WebElement AP_MODE_HIDDEN;
    @FindBy(xpath = "//android.widget.TextView[@text='WPS PUSH BUTTON']")
    public WebElement WPS_PUSH_BUTTON;
    @FindBy(xpath = "//android.widget.TextView[@text='AP PASSWORD']")
    public WebElement AP_PASSWORD;





}
