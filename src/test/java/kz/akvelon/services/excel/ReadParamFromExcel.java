package kz.akvelon.services.excel;

import kz.akvelon.services.ReadParam;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class ReadParamFromExcel implements ReadParam {

    private final Sheet worksheet;

    public ReadParamFromExcel(Sheet worksheet) {
        this.worksheet = worksheet;
    }

    @Override
    public String readParam(String key) {
        Row row;
        Cell cell;

        int rowsCount = worksheet.getPhysicalNumberOfRows();

        for (int rowNum = 1; rowNum < rowsCount; rowNum++) {
            row = worksheet.getRow(rowNum);
            cell = row.getCell(0);

            if (cell.getStringCellValue().equals(key)) {
                return row.getCell(1).getStringCellValue();
            }
        }
        return null;
    }
}
