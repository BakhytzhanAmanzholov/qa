package kz.akvelon.services.repository;

import kz.akvelon.services.WriteResult;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalTime;

public class WriteResultToDB implements WriteResult {
    //language=SQL
    private static final String INSERT_DATA = "INSERT INTO data(execute,status,time,testName) values (?,?,?,?);";

    private final DataSource dataSource;

    public WriteResultToDB(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void writeResult(String expected, String actual, String testName, boolean pass) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_DATA, Statement.RETURN_GENERATED_KEYS)) {

            if (!pass) {
                statement.setString(1, "N");
                statement.setString(2, "Skip Requested");
            } else {
                statement.setString(1, "Y");
                if (expected.equals(actual)) {
                    statement.setString(2, "Pass");
                } else {
                    statement.setString(2, "Fail");
                }
            }
            statement.setTime(3, Time.valueOf(LocalTime.now()));
            statement.setString(4, testName);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }

    }
}
