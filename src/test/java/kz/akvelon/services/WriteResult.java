package kz.akvelon.services;

public interface WriteResult {
    void writeResult(String expected, String actual, String testName, boolean pass);
}
