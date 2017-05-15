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

}