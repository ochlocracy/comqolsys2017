package IQRemote;


import jxl.read.biff.BiffException;

import java.io.IOException;

public class DisarmPhotos extends  Setup_Remote{

    public DisarmPhotos() throws IOException, BiffException {
    }
}
//The idea is to be able to send the event from the IQRemote panel
//and do the verification on the Primary panel and on the ADC dealer website.

//shell commands, see how many files their are
//max of 20 images will be stored.
//creating 21st will overwrite.

//run test. then wait for remote to disconnect and connect again.

//verify on remote




//eventually
//will be on gen 2
//will read logs and compare. log check / library needs to be written
//checking event was sent to adc