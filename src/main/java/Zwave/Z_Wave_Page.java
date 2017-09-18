package Zwave;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by qolsysauto on 9/15/17.
 */
public class Z_Wave_Page {


    //Main Z-Wave Page
    @FindBy(xpath = "//android.widget.TextView[@text='Add Device']")
    public WebElement Add_Device_Z_Wave_Page;
    @FindBy(xpath = "//android.widget.TextView[@text='Edit Device']")
    public WebElement Edit_Device_Z_Wave_Page;
    @FindBy(xpath = "//android.widget.TextView[@text='Clear Device']")
    public WebElement Clear_Device_Z_Wave_Page;
    @FindBy(xpath = "//android.widget.TextView[@text='Delete Failed Device']")
    public WebElement Delete_Failed_Device_Z_Wave_Page;
    @FindBy(xpath = "//android.widget.TextView[@text='Remove All Devices']")
    public WebElement Remove_All_Devices_Z_Wave_Page;
    @FindBy(xpath = "//android.widget.TextView[@text='Z-wave Settings']")
    public WebElement Z_Wave_Settings_Z_Wave_Page;
    @FindBy(xpath = "//android.widget.TextView[@text='NWI']")
    public WebElement NWI_Z_Wave_Page;
    @FindBy(xpath = "//android.widget.TextView[@text='Replace Failed Node']")
    public WebElement Replace_Failed_Npde_Z_Wave_Page;
    @FindBy(xpath = "//android.widget.TextView[@text='Association']")
    public WebElement Association_Z_Wave_Page;
    @FindBy(xpath = "//android.widget.TextView[@text='set as sis']")
    public WebElement Set_As_SIS_Z_Wave_Page;

    //Add Device Page
    @FindBy(id ="com.qolsys:id/button")
    public WebElement Pair_Device_Z_Wave_Add_Device_Page;
    @FindBy(id ="com.qolsys:id/negative_button")
    public WebElement Add_Device_Cancel_Button_Z_Wave_Add_Device_Page;
    @FindBy(id ="com.qolsys:id/ok")
    public WebElement Add_Device_OK_Button_Z_Wave_Add_Device_Page;


    //Edit Device Page
    @FindBy(id ="com.qolsys:id/edit")
    public WebElement Edit_Device_Icon_Z_Wave_Edit_Device_Page;
    @FindBy(id ="com.qolsys:id/editButton")
    public WebElement Update_Device_Button_Z_Wave_Add_Device_Page;

    //Delete Failed Node Page
    @FindBy(id ="com.qolsys:id/btnDelete")
    public WebElement Delete_Button_Z_Wave_Delete_Failed_Node_Page;
    @FindBy(id ="com.qolsys:id/checkBox1")
    public WebElement Checkbox_One_Z_Wave_Delete_Failed_Device_Page;
    @FindBy(id ="com.qolsys:id/ok")
    public WebElement OK_Z_Wave_Delete_Failed_Device_Page;
    @FindBy(id ="com.qolsys:id/cancel")
    public WebElement Cancel_Z_Wave_Delete_Failed_Device_Page;

    //Remove all Devices
    @FindBy(id ="com.qolsys:id/ok")
    public WebElement OK_Z_Wave_Remove_All_Devices_Page;
    @FindBy(id ="com.qolsys:id/cancel")
    public WebElement Cancel_Z_Wave_Remove_All_Devices_Page;

    //NWI
    @FindBy(id ="com.qolsys:id/cancel")
    public WebElement Quit_NWI_Z_Wave_Page;

    //Replace Failed Node Page
    @FindBy(id ="com.qolsys:id/checkBox1")
    public WebElement Checkbox_One_Z_Wave_Replace_Failed_Node_Page;
    @FindBy(id ="com.qolsys:id/btnDelete")
    public WebElement Replace_Button_Z_Wave_Replace_Failed_Node_Page;
    @FindBy(id ="com.qolsys:id/ok")
    public WebElement OK_Z_Wave_Replace_Failed_Node_Page;
    @FindBy(id ="com.qolsys:id/cancel")
    public WebElement Cancel_Z_Wave_Replace_Failed_Node_Page;

    //Association Page
    @FindBy(id ="com.qolsys:id/uiBTNInfo")
    public WebElement View_Button_Z_Wave_Association_Page;



}
