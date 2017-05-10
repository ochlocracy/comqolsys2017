package IQRemote;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ElementsList {

    @FindBy(id = "com.qolsys:id/wire_less_toggle")
    public WebElement WiFi;
    @FindBy(id = "android:id/summary")
    public WebElement Connected;

}
