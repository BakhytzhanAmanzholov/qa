package kz.akvelon.services.excel;

import kz.akvelon.services.WriteResult;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.sql.Time;
import java.time.LocalTime;

public class WriteResultToExcel implements WriteResult {

    private final Sheet worksheet;

    public WriteResultToExcel(Sheet worksheet) {
        this.worksheet = worksheet;
    }

    @Override
    public void writeResult(String expected, String actual, String testName, boolean pass) {
        Row executeRow = worksheet.createRow(worksheet.getPhysicalNumberOfRows());


        Cell executeCell = executeRow.createCell(0);
        Cell statusCell = executeRow.createCell(1);
        Cell timeCell = executeRow.createCell(2);
        Cell nameCell = executeRow.createCell(3);


        executeCell.setCellValue("Y");
        if (expected.equals(actual)) {
            statusCell.setCellValue("Pass");
        } else if (!pass) {
            statusCell.setCellValue("Skip Requested");
            executeCell.setCellValue("N");
        } else {
            statusCell.setCellValue("Fail");
        }

        timeCell.setCellValue(String.valueOf(Time.valueOf(LocalTime.now())));

        nameCell.setCellValue(testName);
    }
}
