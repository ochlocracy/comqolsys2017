package Panel;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Siren_Alarms_Page {
    @FindBy(xpath = "//android.widget.TextView[@text='Disable Siren']")
    public WebElement Disable_Siren;
    @FindBy(xpath = "//android.widget.TextView[@text='Enables the siren for the intrusion alarm siren, all other sirens still sound']")
    public WebElement Disable_Siren_summery;
    @FindBy(xpath = "//android.widget.TextView[@text='Disables the siren for the intrusion alarm siren, all other sirens still sound']")
    public WebElement Disable_Siren_summery_enabled;
    @FindBy(xpath = "//android.widget.TextView[@text='Siren Annunciation']")
    public WebElement Siren_Annunciation;
    @FindBy(xpath = "//android.widget.TextView[@text='Siren Annunciation is Disabled']")
    public WebElement Siren_Annunciation_summery;
    @FindBy(xpath = "//android.widget.TextView[@text='Siren Annunciation is Enabled']")
    public WebElement Siren_Annunciation_summery_enabled;
    @FindBy(xpath = "//android.widget.TextView[@text='Fire Verification']")
    public WebElement Fire_Verification;
    @FindBy(xpath = "//android.widget.TextView[@text='Fire (Two Trip) Verification is Disabled']")
    public WebElement Fire_Verification_summery;
    @FindBy(xpath = "//android.widget.TextView[@text='Fire (Two Trip) Verification is Enabled']")
    public WebElement Fire_Verification_summery_enabled;
    @FindBy(xpath = "//android.widget.TextView[@text='Severe Weather Siren Warning']")
    public WebElement Severe_Weather_Siren_Warning;
    @FindBy(xpath = "//android.widget.TextView[@text='Severe Weather Siren Warning is Enabled']")
    public WebElement Severe_Weather_Siren_Warning_summery;
    @FindBy(xpath = "//android.widget.TextView[@text='Severe Weather Siren Warning is Disabled']")
    public WebElement Severe_Weather_Siren_Warning_summery_disabled;
    @FindBy(xpath = "//android.widget.TextView[@text='Dialer Delay']")
    public WebElement Dialer_Delay;
    @FindBy(xpath = "//android.widget.TextView[@text='Wait 30 seconds before attempting to call the central station after an alarm event is triggered']")
    public WebElement Dialer_Delay_summery;
    @FindBy(xpath = "//android.widget.TextView[@text='Set value in seconds']")
    public WebElement Set_value_title;
    @FindBy(id ="com.qolsys:id/Set")
    public WebElement Set;
    @FindBy(id ="com.qolsys:id/Cancel")
    public WebElement Cancel;
    @FindBy(xpath = "//android.widget.TextView[@text='Siren Timeout']")
    public WebElement Siren_Timeout;
    @FindBy(xpath = "//android.widget.TextView[@text='Siren stops sounding after 4 minutes during an alarm event']")
    public WebElement Siren_Timeout_summery;
    @FindBy(id= "com.qolsys:id/title")
    public WebElement Siren_Timeout_title;
    @FindBy(id="com.qolsys:id/negative_button")
    public WebElement Siren_Timeout_Cancel;
    @FindBy(xpath = "//android.widget.TextView[@text='Water/Freeze Siren']")
    public WebElement Water_Freeze_Siren;
    @FindBy(xpath = "//android.widget.TextView[@text='Water/Freeze Siren is Disabled']")
    public WebElement Water_Freeze_Siren_summery;
    @FindBy(xpath = "//android.widget.TextView[@text='Water/Freeze Siren is Enabled']")
    public WebElement Water_Freeze_Siren_summery_enabled;
    @FindBy(xpath = "//android.widget.TextView[@text='Police Panic']")
    public WebElement Police_Panic;
    @FindBy(xpath = "//android.widget.TextView[@text='Police Panic is Enabled']")
    public WebElement Police_Panic_summery;
    @FindBy(xpath = "//android.widget.TextView[@text='Police Panic is Disabled']")
    public WebElement Police_Panic_summery_disabled;
    @FindBy(xpath = "//android.widget.TextView[@text='Fire Panic']")
    public WebElement Fire_Panic;
    @FindBy(xpath = "//android.widget.TextView[@text='Fire Panic is Enabled']")
    public WebElement Fire_Panic_summery;
    @FindBy(xpath = "//android.widget.TextView[@text='Fire Panic is Disabled']")
    public WebElement Fire_Panic_summery_disabled;
    @FindBy(xpath = "//android.widget.TextView[@text='Auxiliary Panic']")
    public WebElement Auxiliary_Panic;
    @FindBy(xpath = "//android.widget.TextView[@text='Auxiliary Panic is Enabled']")
    public WebElement Auxiliary_Panic_summery;
    @FindBy(xpath = "//android.widget.TextView[@text='Auxiliary Panic is Disabled']")
    public WebElement Auxiliary_Panic_summery_disabled;
    @FindBy(xpath = "//android.widget.TextView[@text='Allow Master Code To Access Siren and Alarms']")
    public WebElement Allow_Master_Code_To_Access_Siren_and_Alarms;
    @FindBy(xpath = "//android.widget.TextView[@text='Allowing Master Code to access Siren and Alarms settings is Disabled']")
    public WebElement Allow_Master_Code_To_Access_Siren_and_Alarms_summery;
    @FindBy(xpath = "//android.widget.TextView[@text='Allowing Master Code to access Siren and Alarms settings is Enabled']")
    public WebElement Allow_Master_Code_To_Access_Siren_and_Alarms_summery_enabled;
}
