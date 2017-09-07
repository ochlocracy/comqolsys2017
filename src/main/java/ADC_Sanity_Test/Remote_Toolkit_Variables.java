package ADC_Sanity_Test;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class  Remote_Toolkit_Variables {
    @FindBy(xpath = "")
    public WebElement Battery;
    @FindBy(xpath = "//android.widget.TextView[@text='Battery Status']")
    public WebElement Battery_Status;
