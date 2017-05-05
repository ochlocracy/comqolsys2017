package Sensors;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SensorsList {
    @FindBy(xpath = "//*[contains(text(), 'Door/Window 4')]")
    public WebElement Door4;
}
