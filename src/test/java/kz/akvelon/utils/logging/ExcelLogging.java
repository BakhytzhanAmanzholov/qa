package kz.akvelon.utils.logging;

import kz.akvelon.services.ConfProperties;
import kz.akvelon.services.ReadParam;
import kz.akvelon.services.WriteResult;
import kz.akvelon.services.excel.ReadParamFromExcel;
import kz.akvelon.services.excel.WriteResultToExcel;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelLogging extends CreatorLogging {
    private static Sheet sheetForData;
    private static Sheet sheetForParam;

    private static Workbook workbook;
    private static FileInputStream inStream;
    private static FileOutputStream outStream;


    public ExcelLogging() {
        try {
            inStream =
                    new FileInputStream(ConfProperties.getProperty("shopkz.testdata.path"));
            workbook = WorkbookFactory.create(inStream);
            sheetForData = workbook.getSheetAt(0);
            sheetForParam = workbook.getSheetAt(1);
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public WriteResult createWriteResult() {
        return new WriteResultToExcel(sheetForData);
    }

    @Override
    public ReadParam createReadParam() {
        return new ReadParamFromExcel(sheetForParam);
    }

    @Override
    public void closeLogging() {
        try {
            outStream = new FileOutputStream(
                    ConfProperties.getProperty("shopkz.testdata.path"));
            workbook.write(outStream);
            outStream.close();
            inStream.close();
            workbook.close();
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }
}
