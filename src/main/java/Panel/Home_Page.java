package Panel;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Home_Page {

    @FindBy(id ="com.qolsys:id/t3_img_disarm")
    public WebElement DISARM;
    @FindBy(id="com.qolsys:id/img_arm_stay")
    public WebElement ARM_STAY;
    @FindBy(xpath = "//android.widget.TextView[@text='ARM STAY']")
    public WebElement ARM_STAY_text;
    @FindBy(id="com.qolsys:id/img_arm_away")
    public WebElement ARM_AWAY;
    @FindBy(xpath = "//android.widget.TextView[@text='ARM AWAY']")
    public WebElement ARM_AWAY_text;
    @FindBy(id="com.qolsys:id/img_weather")
    public WebElement Weather_img;
    @FindBy(id="com.qolsys:id/text_high_temp")
    public WebElement High_temp;
    @FindBy(id="com.qolsys:id/text_low_temp")
    public WebElement Low_temp;
    @FindBy(id="com.qolsys:id/text_time")
    public WebElement Time;
    @FindBy(id="com.qolsys:id/text_date")
    public WebElement Date;
    @FindBy(id="com.qolsys:id/img_contact_us")
    public WebElement Contact_Us;
    @FindBy(id="com.qolsys:id/uiTab_active")
    public WebElement Active_Tab;
    @FindBy(id="com.qolsys:id/uiTab_all")
    public WebElement All_Tab;
    @FindBy(id="com.qolsys:id/t3_open_tv_active")
    public WebElement Active_Tab_expand;
    @FindBy(id="com.qolsys:id/t3_open_tv_all")
    public WebElement All_Tab_expand;
    @FindBy(id="com.qolsys:id/t3_emergency_icon")
    public WebElement Emergency_Button;
    @FindBy(id="com.qolsys:id/t3_tv_disarm")
    public WebElement Disarmed_text;
    @FindBy(id="com.qolsys:id/tvSystemStatus")
    public WebElement System_status;
    @FindBy(id="com.qolsys:id/img_expand")
    public WebElement System_state_expand;
    @FindBy(id="com.qolsys:id/img_collapse")
    public WebElement System_state_collapse;
    @FindBy(id="com.qolsys:id/uiExitSoundText")
    public WebElement Exit_Sounds_title;
    @FindBy(id="com.qolsys:id/img_exit_sound")
    public WebElement Exit_Sounds;
    @FindBy(id="com.qolsys:id/uiExitSoundValue")
    public WebElement Exit_Sounds_value;
    @FindBy(id="com.qolsys:id/uiEntryDelayText")
    public WebElement Entry_Delay_title;
    @FindBy(id="com.qolsys:id/img_entry_delay")
    public WebElement Entry_Delay;
    @FindBy(id="com.qolsys:id/entryDelayTvValue")
    public WebElement Entry_Delay_value;
    @FindBy(id="com.qolsys:id/tv_bypass")
    public WebElement Bypass_Tab;
    @FindBy(id="com.qolsys:id/main")
    public WebElement ArwAway_State;
    @FindBy(xpath = "//android.widget.TextView[@text='ALARM']")
    public WebElement ALARM;
    @FindBy(id="com.qolsys:id/tv_keyOne")
    public WebElement One;
    @FindBy(id="com.qolsys:id/tv_keyTwo")
    public WebElement Two;
    @FindBy(id="com.qolsys:id/tv_keyThree")
    public WebElement Three;
    @FindBy(id="com.qolsys:id/tv_keyFour")
    public WebElement Four;
    @FindBy(id="com.qolsys:id/tv_keyEight")
    public WebElement Eight;
    @FindBy(id="com.qolsys:id/tv_keyNine")
    public WebElement Nine;
    @FindBy(id="com.qolsys:id/tv_name")
    public WebElement Red_banner_sensor_name;
    @FindBy(id="com.qolsys:id/tv_status")
    public WebElement Red_banner_sensor_status;
    @FindBy(xpath = "//android.widget.TextView[@text='Open Sensor']")
    public WebElement Bypass_message;
    @FindBy(id = "com.qolsys:id/cancel")
    public WebElement Bypass_Cancel;
    @FindBy(id = "com.qolsys:id/ok")
    public WebElement Bypass_OK;
    @FindBy(id ="com.qolsys:id/txt_title")
    public WebElement Enter_Code_to_Access_the_Area;
    @FindBy(id = "com.qolsys:id/ft_back")
    public WebElement Back_button;
    @FindBy(id = "com.qolsys:id/ft_emergency")
    public WebElement Emergency_button;
    @FindBy(id = "com.qolsys:id/ft_home_button")
    public WebElement Home_button;
}

