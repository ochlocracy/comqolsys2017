package Sensors;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SensorsList {
    @FindBy(xpath = "//android.widget.TextView[@text='Door/Window 2']")
    public WebElement Door2;
    @FindBy(xpath = "//android.widget.TextView[@text='Door/Window 3']")
    public WebElement Door3;
    @FindBy(xpath = "//android.widget.TextView[@text='Door/Window 4']")
    public WebElement Door4;
    @FindBy(xpath = "//android.widget.TextView[@text='Door/Window 5']")
    public WebElement Door5;
    @FindBy(xpath = "//android.widget.TextView[@text='Door/Window 6']")
    public WebElement Door6;
    @FindBy(xpath = "//android.widget.TextView[@text='Door/Window 7']")
    public WebElement Door7;
    @FindBy(xpath = "//android.widget.TextView[@text='Door/Window 8']")
    public WebElement Door8;
    @FindBy(xpath = "//android.widget.TextView[@text='Door/Window 9']")
    public WebElement Door9;
    @FindBy(xpath = "//android.widget.TextView[@text='Motion 10']")
    public WebElement Motion10;
    @FindBy(xpath = "//android.widget.TextView[@text='Motion 11']")
    public WebElement Motion11;
    @FindBy(xpath = "//android.widget.TextView[@text='Motion 12']")
    public WebElement Motion12;
    @FindBy(xpath = "//android.widget.TextView[@text='Motion 13']")
    public WebElement Motion13;
    @FindBy(xpath = "//android.widget.TextView[@text='Motion 14']")
    public WebElement Motion14;
    @FindBy(xpath = "//android.widget.TextView[@text='Freeze 27']")
    public WebElement Freeze27;
    @FindBy(xpath ="//android.widget.TextView[@text='Smoke-M 26']")
    public WebElement SmokeM26;
    @FindBy(xpath ="//android.widget.TextView[@text='Multi-Function-1 18']")
    public WebElement Water18;
    @FindBy(xpath ="//android.widget.TextView[@text='Glass Break 19']")
    public WebElement Glassbreak19;
    @FindBy(xpath ="//android.widget.TextView[@text='Glass Break 20']")
    public WebElement Glassbreak20;
    @FindBy(xpath ="//android.widget.TextView[@text='Tilt 21']")
    public WebElement Tilt21;
    @FindBy(xpath ="//android.widget.TextView[@text='Tilt 22']")
    public WebElement Tilt22;
    @FindBy(xpath ="//android.widget.TextView[@text='Tilt 23']")
    public WebElement Tilt23;
}