package Panel;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Security_Sensors_Page {
    @FindBy(xpath = "//android.widget.TextView[@text='Auto Learn Sensor']")
    public WebElement Auto_Learn_Sensor;
    @FindBy(xpath = "//android.widget.TextView[@text='Add Sensor']")
    public WebElement Add_Sensor;
    @FindBy(xpath = "//android.widget.TextView[@text='Edit Sensor']")
    public WebElement Edit_Sensor;
    @FindBy(xpath = "//android.widget.TextView[@text='Delete Sensor']")
    public WebElement Delete_Sensor;
    @FindBy(xpath = "//android.widget.TextView[@text='Sensor Status']")
    public WebElement Sensor_Status;
    @FindBy(xpath = "//android.widget.TextView[@text='Sensor Group']")
    public WebElement Sensor_Group;
}
