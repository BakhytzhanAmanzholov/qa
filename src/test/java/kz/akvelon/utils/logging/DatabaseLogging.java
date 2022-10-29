package kz.akvelon.utils.logging;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import kz.akvelon.services.ReadParam;
import kz.akvelon.services.WriteResult;
import kz.akvelon.services.repository.ReadParamFromDB;
import kz.akvelon.services.repository.WriteResultToDB;

public class DatabaseLogging extends CreatorLogging{

    private static HikariDataSource dataSource;

    public DatabaseLogging() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/qa");
        config.setUsername("postgres");
        config.setPassword("postgre");
        config.setMaximumPoolSize(20);

        dataSource = new HikariDataSource(config);
    }

    @Override
    public WriteResult createWriteResult() {
        return new WriteResultToDB(dataSource);
    }

    @Override
    public ReadParam createReadParam() {
        return new ReadParamFromDB(dataSource);
    }

    @Override
    public void closeLogging() {
        dataSource.close();
    }
}
