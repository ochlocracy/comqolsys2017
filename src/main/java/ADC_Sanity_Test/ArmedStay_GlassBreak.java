package ADC_Sanity_Test;

import Panel.Setup;
import Sensors.Sensors;

import java.util.logging.Logger;

/**
 * Exit Delay
 * 1) trigger group 13 glass break sensor during exit delay
 * 2) trigger group 17 glass break sensor during exit delay
 *
 * Tamper
 * 1) tamper group 13 glass break
 * 2) tamper group 17 glass break
 */
public class ArmedStay_GlassBreak extends Setup{
    String page_name = "Arm Stay mode";
    Logger logger = new Logger.getLogger();
    private int Normal_Exit_Delay = 30000;

}

