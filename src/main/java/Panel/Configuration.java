package Panel;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.File;
import java.io.IOException;

public class Configuration {
    private String config = "/home/nchortek/comqolsys2017/config.xls";
    public String adbPath = getAdbPath();
    public File appDir = new File ("scr");
    public String udid_ = getudid_();
    //public String udid = "8ebdbc27";
    public Configuration() throws IOException, BiffException {}

    public String getAdbPath() throws IOException, BiffException {
        Workbook wb =Workbook.getWorkbook(new File(config));
        Sheet sh = wb.getSheet(0);
        String CellGetContent = sh.getCell(0,0).getContents();
        return CellGetContent;
    }
    public String getudid_() throws IOException, BiffException {
        Workbook wb =Workbook.getWorkbook(new File(config));
        Sheet sh = wb.getSheet(0);
        String CellGetContent = sh.getCell(1,0).getContents();
        return CellGetContent;
    }
    public String getProjectPath() throws IOException, BiffException{
        Workbook wb =Workbook.getWorkbook(new File(config));
        Sheet sh = wb.getSheet(0);
        String CellGetContent = sh.getCell(1,1).getContents();
        return CellGetContent;
    }
}