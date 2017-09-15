package ADC_Sanity_Test;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class Remote_Toolkit_Variables {
    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_butChange")
    public WebElement Change;

    @FindBy (xpath = "//*[@id=ctl00_responsiveBody_ucCommands_txtNewValue")
    public WebElement Txt_New_Value;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_ddlNewValue")
    public WebElement New_Value;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl00_lblCategoryName")
    public WebElement Advanced_Panel_Settings_Dropdown;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl00_rptSettingsCommands_ctl00_lbtnCommandSetting")
    public WebElement Auto_Upload_logs;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl00_rptSettingsCommands_ctl01_lbtnCommandSetting")
    public WebElement Log_Level;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl01_lblCategoryName")
    public WebElement Alarm_Settings_Dropdown;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl01_rptSettingsCommands_ctl00_lbtnCommandSetting")
    public WebElement Alarm_Photos;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl01_rptSettingsCommands_ctl01_lbtnCommandSetting")
    public WebElement Disarm_Photos;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl01_rptSettingsCommands_ctl02_lbtnCommandSetting")
    public WebElement Fire_Panic;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl01_rptSettingsCommands_ctl03_lbtnCommandSetting")
    public WebElement Fire_Verification;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl01_rptSettingsCommands_ctl04_lbtnCommandSetting")
    public WebElement Police_Panic;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl01_rptSettingsCommands_ctl05_lbtnCommandSetting")
    public WebElement RF_Jam_Detection_Alarm;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl01_rptSettingsCommands_ctl06_lbtnCommandSetting")
    public WebElement Siren_Timeout;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl02_lblCategoryName")
    public WebElement Arming_Setting_Dropdown;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl02_rptSettingsCommands_ctl00_lbtnCommandSetting")
    public WebElement Auto_Stay;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl02_rptSettingsCommands_ctl01_lbtnCommandSetting")
    public WebElement Dialer_Delay;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl02_rptSettingsCommands_ctl02_lbtnCommandSetting")
    public WebElement Entry_Delay;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl02_rptSettingsCommands_ctl03_lbtnCommandSetting")
    public WebElement Exit_Delay;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl02_rptSettingsCommands_ctl04_lbtnCommandSetting")
    public WebElement Refuse_Arming_When_Battery_Low;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl02_rptSettingsCommands_ctl05_lbtnCommandSetting")
    public WebElement Secure_Arming;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl02_rptSettingsCommands_ctl06_lbtnCommandSetting")
    public WebElement Secure_Arming_Photos;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl02_rptSettingsCommands_ctl07_lbtnCommandSetting")
    public WebElement Secure_Delete_Images;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl03_lblCategoryName")
    public WebElement Beeps_And_Speakers_Dropdown;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl03_rptSettingsCommands_ctl00_lbtnCommandSetting")
    public WebElement All_Chimes;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl03_rptSettingsCommands_ctl01_lbtnCommandSetting")
    public WebElement All_Trouble_Beeps;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl03_rptSettingsCommands_ctl02_lbtnCommandSetting")
    public WebElement All_Voice_Prompts;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl03_rptSettingsCommands_ctl03_lbtnCommandSetting")
    public WebElement Beeps_And_Chimes_Volume;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl03_rptSettingsCommands_ctl04_lbtnCommandSetting")
    public WebElement Media_Volume;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl03_rptSettingsCommands_ctl05_lbtnCommandSetting")
    public WebElement Panel_Chimes;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl03_rptSettingsCommands_ctl06_lbtnCommandSetting")
    public WebElement Panel_Siren;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl03_rptSettingsCommands_ctl07_lbtnCommandSetting")
    public WebElement Panel_Tamper_Trouble_Beep;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl03_rptSettingsCommands_ctl08_lbtnCommandSetting")
    public WebElement Panel_Voice_Prompts;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl03_rptSettingsCommands_ctl09_lbtnCommandSetting")
    public WebElement Safety_Sensor_Chimes;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl03_rptSettingsCommands_ctl10_lbtnCommandSetting")
    public WebElement Safety_Sensor_Voice_Prompts;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl03_rptSettingsCommands_ctl11_lbtnCommandSetting")
    public WebElement Sensor_Chimes;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl03_rptSettingsCommands_ctl12_lbtnCommandSetting")
    public WebElement Sensor_Low_Battery_Trouble_Beep;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl03_rptSettingsCommands_ctl13_lbtnCommandSetting")
    public WebElement Sensor_Tamper_Trouble_Beep;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl03_rptSettingsCommands_ctl14_lbtnCommandSetting")
    public WebElement Sensor_Voice_Prompts;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl03_rptSettingsCommands_ctl15_lbtnCommandSetting")
    public WebElement Severe_Weather_Siren_Warning;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl03_rptSettingsCommands_ctl16_lbtnCommandSetting")
    public WebElement Siren_Annunciation;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl03_rptSettingsCommands_ctl17_lbtnCommandSetting")
    public WebElement Touch_Sounds;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl03_rptSettingsCommands_ctl18_lbtnCommandSetting")
    public WebElement Trouble_Beeps_Timeout;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl03_rptSettingsCommands_ctl19_lbtnCommandSetting")
    public WebElement Turn_On_Off_Trouble_Beeps;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl03_rptSettingsCommands_ctl20_lbtnCommandSetting")
    public WebElement Voices_Volume;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl03_rptSettingsCommands_ctl21_lbtnCommandSetting")
    public WebElement Water_And_Freeze_Siren;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl04_lblCategoryName")
    public WebElement Broadband_Settings_Dropdown;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl04_rptSettingsCommands_ctl00_lbtnCommandSetting")
    public WebElement Bluetooth_Disarming_Feature;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl04_rptSettingsCommands_ctl01_lbtnCommandSetting")
    public WebElement Wi_Fi;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl04_rptSettingsCommands_ctl02_lbtnCommandSetting")
    public WebElement Wi_Fi_Network_Name;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl05_lblCategoryName")
    public WebElement Communication_Dropdown;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl05_rptSettingsCommands_ctl00_lbtnCommandSetting")
    public WebElement Dual_Path_Communication_settings;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl05_rptSettingsCommands_ctl01_lbtnCommandSetting")
    public WebElement Hard_Reset;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl05_rptSettingsCommands_ctl02_lbtnCommandSetting")
    public WebElement Ping_Dual_Path_System;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl05_rptSettingsCommands_ctl03_lbtnCommandSetting")
    public WebElement Ping_Module;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl05_rptSettingsCommands_ctl04_lbtnCommandSetting")
    public WebElement Request_Firmware_Version;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl05_rptSettingsCommands_ctl05_lbtnCommandSetting")
    public WebElement Date_and_Time_Dropdown;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl06_rptSettingsCommands_ctl00_lbtnCommandSetting")
    public WebElement Location;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl06_rptSettingsCommands_ctl01_lbtnCommandSetting")
    public WebElement Request_Panel_Time;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl06_rptSettingsCommands_ctl02_lbtnCommandSetting")
    public WebElement Set_Panel_Time;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl08_lblCategoryName")
    public WebElement General_Dropdown;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl08_rptSettingsCommands_ctl00_lbtnCommandSetting")
    public WebElement Automatic_Upgrade;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl08_rptSettingsCommands_ctl01_lbtnCommandSetting")
    public WebElement Auxiliary_panic;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl08_rptSettingsCommands_ctl02_lbtnCommandSetting")
    public WebElement Bluetooth;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl08_rptSettingsCommands_ctl03_lbtnCommandSetting")
    public WebElement Bluetooth_Disarm_Timeout;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl08_rptSettingsCommands_ctl04_lbtnCommandSetting")
    public WebElement Request_Updated_Equipment_List;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl08_rptSettingsCommands_ctl05_lbtnCommandSetting")
    public WebElement Resend_Panel_Location;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl08_rptSettingsCommands_ctl06_lbtnCommandSetting")
    public WebElement Send_Weather_Info;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl09_lblCategoryName")
    public WebElement Image_Sensor_Dropdown;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl09_rptSettingsCommands_ctl00_lbtnCommandSetting")
    public WebElement Change_Extended_Range;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl09_rptSettingsCommands_ctl01_lbtnCommandSetting")
    public WebElement Disable_Automatic_Image_Uploads;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl09_rptSettingsCommands_ctl02_lbtnCommandSetting")
    public WebElement Request_Latest_Image_Sensor_Info;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl09_rptSettingsCommands_ctl03_lbtnCommandSetting")
    public WebElement Set_Trouble_Report_at_Panel;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl09_rptSettingsCommands_ctl04_lbtnCommandSetting")
    public WebElement Verify_Daughterboard_Attachment;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl10_lblCategoryName")
    public WebElement Keypad_Screen_Setting_Dropdown;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl10_rptSettingsCommands_ctl00_lbtnCommandSetting")
    public WebElement Automatically_Turn_Off_display;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl10_rptSettingsCommands_ctl01_lbtnCommandSetting")
    public WebElement Automatically_Turn_On_display;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl10_rptSettingsCommands_ctl02_lbtnCommandSetting")
    public WebElement Brightness;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl10_rptSettingsCommands_ctl03_lbtnCommandSetting")
    public WebElement Country;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl10_rptSettingsCommands_ctl04_lbtnCommandSetting")
    public WebElement Display_Type;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl10_rptSettingsCommands_ctl05_lbtnCommandSetting")
    public WebElement Font_Size;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl10_rptSettingsCommands_ctl06_lbtnCommandSetting")
    public WebElement Home_Screen;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl10_rptSettingsCommands_ctl07_lbtnCommandSetting")
    public WebElement Language;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl10_rptSettingsCommands_ctl08_lbtnCommandSetting")
    public WebElement Photo_Frame_Duration;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl10_rptSettingsCommands_ctl09_lbtnCommandSetting")
    public WebElement Photo_Frame_Shuffle;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl10_rptSettingsCommands_ctl10_lbtnCommandSetting")
    public WebElement Photo_Frame_Start_Time;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl10_rptSettingsCommands_ctl11_lbtnCommandSetting")
    public WebElement Setting_Photos;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl10_rptSettingsCommands_ctl12_lbtnCommandSetting")
    public WebElement Transition_Effect;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl14_lblCategoryName")
    public WebElement Panel_Information_Dropdown;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl14_rptSettingsCommands_ctl00_lbtnCommandSetting")
    public WebElement Account_Number;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl14_rptSettingsCommands_ctl01_lbtnCommandSetting")
    public WebElement Hardware_Version;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl14_rptSettingsCommands_ctl02_lbtnCommandSetting")
    public WebElement Partitions_Count;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl14_rptSettingsCommands_ctl03_lbtnCommandSetting")
    public WebElement Power_Management;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl14_rptSettingsCommands_ctl04_lbtnCommandSetting")
    public WebElement RF_Jam_Detection;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl14_rptSettingsCommands_ctl05_lbtnCommandSetting")
    public WebElement Secondary_Panels;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl15_lblCategoryName")
    public WebElement Sensors_Dropdown;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl15_rptSettingsCommands_ctl00_lbtnCommandSetting")
    public WebElement Change_Sensor_Name;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl15_rptSettingsCommands_ctl01_lbtnCommandSetting")
    public WebElement Request_Sensor_Names;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl15_rptSettingsCommands_ctl02_lbtnCommandSetting")
    public WebElement Update_System_And_Sensor_Status;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl15_rptSettingsCommands_ctl03_lbtnCommandSetting")
    public WebElement Zones_Count;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl16_lblCategoryName")
    public WebElement Timers_Dropdown;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl16_rptSettingsCommands_ctl00_lbtnCommandSetting")
    public WebElement Arm_Stay_No_Delay;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl16_rptSettingsCommands_ctl01_lbtnCommandSetting")
    public WebElement Auto_Bypass;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl16_rptSettingsCommands_ctl02_lbtnCommandSetting")
    public WebElement Auto_Exit_Time_Extension;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl16_rptSettingsCommands_ctl03_lbtnCommandSetting")
    public WebElement Keyfob_No_Delay;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl16_rptSettingsCommands_ctl04_lbtnCommandSetting")
    public WebElement Long_Entry_Delay_Toolkit;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl16_rptSettingsCommands_ctl05_lbtnCommandSetting")
    public WebElement Long_Exit_Delay_Toolkit;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl16_rptSettingsCommands_ctl06_lbtnCommandSetting")
    public WebElement Network_Failure_Timeout;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl16_rptSettingsCommands_ctl07_lbtnCommandSetting")
    public WebElement SIA_Limits;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl16_rptSettingsCommands_ctl08_lbtnCommandSetting")
    public WebElement SIA_Power_Restoration;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl17_lblCategoryName")
    public WebElement Trouble_Condition_Settings_Dropdown;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl17_rptSettingsCommands_ctl00_lbtnCommandSetting")
    public WebElement Loss_Of_Supervisory_Signals_Emergency_only;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl17_rptSettingsCommands_ctl01_lbtnCommandSetting")
    public WebElement Loss_Of_Supervisory_Signals_Non_Emergency_Sensors;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl17_rptSettingsCommands_ctl02_lbtnCommandSetting")
    public WebElement Panel_Communication_Test_Frequency;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl19_lblCategoryName")
    public WebElement User_Codes_Dropdown;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl19_rptSettingsCommands_ctl00_lbtnCommandSetting")
    public WebElement Allow_Master_Code_to_Access_Image_Settings;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl19_rptSettingsCommands_ctl01_lbtnCommandSetting")
    public WebElement Allow_Master_Code_to_Access_Security_and_Arming;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl19_rptSettingsCommands_ctl02_lbtnCommandSetting")
    public WebElement Allow_Master_Code_to_Access_Siren_and_Alarms;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl19_rptSettingsCommands_ctl03_lbtnCommandSetting")
    public WebElement Allow_Master_Code_ZWave_Management;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl19_rptSettingsCommands_ctl04_lbtnCommandSetting")
    public WebElement Allow_Master_Code_ZWave_Settings;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl19_rptSettingsCommands_ctl05_lbtnCommandSetting")
    public WebElement Dealer_Code;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl19_rptSettingsCommands_ctl06_lbtnCommandSetting")
    public WebElement Duress_Authentication;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl19_rptSettingsCommands_ctl07_lbtnCommandSetting")
    public WebElement Installer_Code;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl19_rptSettingsCommands_ctl08_lbtnCommandSetting")
    public WebElement Request_User_Code_Names;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl21_lblCategoryName")
    public WebElement Z_Wave_Dropdown;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl21_rptSettingsCommands_ctl00_lbtnCommandSetting")
    public WebElement Door_Lock_Limit;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl21_rptSettingsCommands_ctl01_lbtnCommandSetting")
    public WebElement Garage_Doors;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl21_rptSettingsCommands_ctl02_lbtnCommandSetting")
    public WebElement Get_Equipment_List;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl21_rptSettingsCommands_ctl03_lbtnCommandSetting")
    public WebElement Light_Limit;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl21_rptSettingsCommands_ctl04_lbtnCommandSetting")
    public WebElement Local_Z_Wave_Voice_Prompts;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl21_rptSettingsCommands_ctl05_lbtnCommandSetting")
    public WebElement Other_Z_Wave_Device_Limit;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl21_rptSettingsCommands_ctl06_lbtnCommandSetting")
    public WebElement Rediscover_Network;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl21_rptSettingsCommands_ctl07_lbtnCommandSetting")
    public WebElement Remote_Z_Wave_Voice_Prompts;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl21_rptSettingsCommands_ctl08_lbtnCommandSetting")
    public WebElement Smart_Socket_Limit;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl21_rptSettingsCommands_ctl09_lbtnCommandSetting")
    public WebElement Temperature;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl21_rptSettingsCommands_ctl10_lbtnCommandSetting")
    public WebElement Thermostat_Limit;

    @FindBy(xpath = "//*[@id=ctl00_responsiveBody_ucCommands_rptCommandCategories_ctl21_rptSettingsCommands_ctl11_lbtnCommandSetting")
    public WebElement Z_Wave;

}
