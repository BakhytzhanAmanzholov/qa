package kz.akvelon.services.repository;

import kz.akvelon.models.Key;
import kz.akvelon.services.ReadParam;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Function;


public class ReadParamFromDB implements ReadParam {

    //language=SQL
    private static final String SQL_SELECT_BY_KEY = "SELECT * FROM keys where key = ?;";

    private final DataSource dataSource;

    public ReadParamFromDB(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private static final Function<ResultSet, Key> keyRowMapper = row -> {
        try {
            Key key = new Key();
            key.setId(row.getLong("id"));
            key.setKey(row.getString("key"));
            key.setValue(row.getString("value"));
            return key;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    };


    @Override
    public String readParam(String key) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_KEY)) {

            statement.setString(1, key);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return keyRowMapper.apply(resultSet).getValue();
                } else {
                    return null;
                }
            }

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }

    }
}
