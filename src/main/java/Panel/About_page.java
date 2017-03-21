package Panel;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class About_page {

    @FindBy(xpath = "//android.widget.TextView[@text='Battery']")
    public WebElement Battery;
    @FindBy(xpath = "//android.widget.TextView[@text='Software']")
    public WebElement Software;
    @FindBy(xpath = "//android.widget.TextView[@text='Hardware']")
    public WebElement Hardware;
    @FindBy(xpath = "//android.widget.TextView[@text='Patches']")
    public WebElement Patches;
    @FindBy(xpath = "//android.widget.TextView[@text='Panel']")
    public WebElement Panel_About;
    @FindBy(xpath = "//android.widget.TextView[@text='Cellular']")
    public WebElement Cellular;
    @FindBy(xpath = "//android.widget.TextView[@text='Z-Wave']")
    public WebElement ZWave;
    @FindBy(xpath = "//android.widget.TextView[@text='WI-FI Information']")
    public WebElement WI_FI_Info;
    @FindBy(xpath = "//android.widget.TextView[@text='Internal storage']")
    public WebElement Internal_storage;

    @FindBy(xpath = "//android.widget.TextView[@text='Software Version']")
    public WebElement Software_Version;
}
