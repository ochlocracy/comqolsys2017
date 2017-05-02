package Panel;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.File;
import java.io.IOException;

public class Configuration {

    private String  config = "/home/qolsys/Desktop/config.xls";
    public String adbPath = getAdbPath();   //"/home/qolsys/android-sdk-linux/platform-tools/adb";
    public File appDir = new File("src");
    public String udid_ = getudid_(); //"8ebdbc76";

    public Configuration() throws IOException, BiffException {
    }

    public String getAdbPath () throws IOException, BiffException {
        Workbook wb = Workbook.getWorkbook(new File(config));
        Sheet sh = wb.getSheet(0);
        String CellGetContent = sh.getCell(0,0).getContents();
        return  CellGetContent;
    }
    public String getudid_ () throws IOException, BiffException {
        Workbook wb = Workbook.getWorkbook(new File(config));
        Sheet sh = wb.getSheet(0);
        String CellGetContent = sh.getCell(1, 0).getContents();
        return CellGetContent;
    }
}