package kz.akvelon.services;

import org.apache.poi.ss.usermodel.Sheet;

public interface WriteResult {
    void writeResult(Sheet worksheet, String expected, String actual, String testName, boolean pass);
}
