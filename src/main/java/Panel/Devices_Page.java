package Panel;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Devices_Page {
    @FindBy(xpath = "//android.widget.TextView[@text='Security Sensors']")
    public WebElement Security_Sensors;
    @FindBy(xpath = "//android.widget.TextView[@text='Wi-Fi Devices']")
    public WebElement WiFi_Devices;
    @FindBy(xpath = "//android.widget.TextView[@text='Z-wave Devices']")
    public WebElement Zwave_Devices;
    @FindBy(xpath = "//android.widget.TextView[@text='Bluetooth Devices']")
    public WebElement Bluetooth_Devices;
    @FindBy(xpath = "//android.widget.TextView[@text='Z-wave Devices Unsupported']")
    public WebElement Zwave_Devices_Unsupported;
}
