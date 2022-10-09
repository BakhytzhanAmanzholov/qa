package kz.akvelon.services;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;

public class ReadParamFromExcel implements ReadParam {

    private String filePath;

    public ReadParamFromExcel(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String readParam(String key) {
        Sheet worksheet;
        try {
            FileInputStream inStream = new FileInputStream(filePath);

            Workbook workbook = WorkbookFactory.create(inStream);

            worksheet = workbook.getSheetAt(0);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        }

        Row row;
        //goto first cell
        Cell cell;

        int rowsCount = worksheet.getPhysicalNumberOfRows();
        //worksheet.getLastRowNum();

        System.out.println("Number of rows: " + rowsCount);
        //print all firstnames using a loop

        for (int rowNum = 0; rowNum < rowsCount; rowNum++) {
            row = worksheet.getRow(rowNum);
            cell = row.getCell(0);

            System.out.println(cell.getStringCellValue());
            if (cell.getStringCellValue().equals(key)) {
                return row.getCell(1).getStringCellValue();
            }
            System.out.println(rowNum + " - " + cell.toString());
        }

        return null;
    }
}
