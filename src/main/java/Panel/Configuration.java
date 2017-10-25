package Panel;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.File;
import java.io.IOException;

public class Configuration {
    private String config = "/home/qolsys/Documents/Intellij_Workspace/Qolsys/comqolsys2017/confignew.xls";
    public String adbPath = getAdbPath();
    public File appDir = new File ("scr");
    public String udid_ = getudid_();
    public String transmitterudid = gettransmitterudid();
    public Configuration() throws IOException, BiffException {}

    public String getAdbPath() throws IOException, BiffException {
        Workbook wb =Workbook.getWorkbook(new File(config));
        Sheet sh = wb.getSheet(0);
        String CellGetContent = sh.getCell(1,1).getContents();
        return CellGetContent;
    }

    public String getProjectPath() throws IOException, BiffException{
        Workbook wb =Workbook.getWorkbook(new File(config));
        Sheet sh = wb.getSheet(0);
        String CellGetContent = sh.getCell(1,2).getContents();
        return CellGetContent;
    }
    public String getudid_() throws IOException, BiffException {
        Workbook wb = Workbook.getWorkbook(new File(config));
        Sheet sh = wb.getSheet(0);
        String CellGetContent = sh.getCell(1, 3).getContents();
        return CellGetContent;
    }
    public String gettransmitterudid() throws IOException, BiffException{
        Workbook wb =Workbook.getWorkbook(new File(config));
        Sheet sh = wb.getSheet(0);
        String CellGetContent = sh.getCell(1,4).getContents();
        return CellGetContent;
    }

}