package kz.akvelon.services;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.sql.Time;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class WriteResultToExcel implements WriteResult {

    @Override
    public void writeResult(Sheet worksheet, String expected, String actual, String testName, boolean pass) {
        Row executeRow = worksheet.createRow(worksheet.getPhysicalNumberOfRows());
//        Row expectedRow = worksheet.createRow(0);
//        Row actualRow = worksheet.createRow(0);
//        Row statusRow = worksheet.createRow(0);
//        Row timeRow = worksheet.createRow(0);


        Cell executeCell = executeRow.createCell(0);
//        Cell expectedCell = executeRow.createCell(1);
//        Cell actualCell = executeRow.createCell(2);
        Cell statusCell = executeRow.createCell(1);
        Cell timeCell = executeRow.createCell(2);
        Cell nameCell = executeRow.createCell(3);


        executeCell.setCellValue("Y");
//        expectedCell.setCellValue(expected);
//        actualCell.setCellValue(actual);
        if (expected.equals(actual)) {
            statusCell.setCellValue("Pass");
        } else if(!pass){
            statusCell.setCellValue("Skip Requested");
            executeCell.setCellValue("N");
        } else {
            statusCell.setCellValue("Fail");
        }

        timeCell.setCellValue(String.valueOf(Time.valueOf(LocalTime.now())));

        nameCell.setCellValue(testName);
    }
}
