package Cellular;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class System_Tests_page {
    @FindBy(xpath = "//android.widget.TextView[@text='Wi-Fi Test']")
    public WebElement WIFI_TEST;
    @FindBy(xpath = "//android.widget.TextView[@text='Sensor Test']")
    public WebElement SENSOR_TEST;
    @FindBy(xpath = "//android.widget.TextView[@text='Cellular Test']")
    public WebElement CELLULAR_TEST;
    @FindBy(xpath = "//android.widget.TextView[@text='Image Sensor Config']")
    public WebElement IMAGE_SENSOR_TEST;
    @FindBy(xpath = "//android.widget.TextView[@text='Z-Wave Tests']")
    public WebElement ZWAVE_TEST;
    @FindBy(xpath = "//android.widget.TextView[@text='Wi-fi Devices Test']")
    public WebElement WIFI_DEVICES_TEST;
    @FindBy(xpath = "//android.widget.TextView[@text='PANEL GLASS BREAK TEST']")
    public WebElement PANEL_GLASS_BREAK_TEST;
    @FindBy(xpath = "//android.widget.TextView[@text='Dual Path Test']")
    public WebElement DUAL_PATH_TEST;
    @FindBy(xpath = "//android.widget.TextView[@text='Daughter Cards Test']")
    public WebElement DAUGHTER_CARDS_TEST;
    @FindBy(xpath = "//android.widget.TextView[@text='Panel Test']")
    public WebElement PANEL_TEST;
    @FindBy(id = "com.qolsys:id/ft_back")
    public WebElement Back_button;


}
