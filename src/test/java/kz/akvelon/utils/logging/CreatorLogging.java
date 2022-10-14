package kz.akvelon.utils.logging;

import kz.akvelon.services.ConfProperties;
import kz.akvelon.services.ReadParam;
import kz.akvelon.services.WriteResult;

public abstract class CreatorLogging {
    public abstract WriteResult createWriteResult();

    public abstract ReadParam createReadParam();

    public abstract void closeLogging();

    public static CreatorLogging getLogging() {
        if (ConfProperties.getProperty("logging").equals("excel")) {
            return new ExcelLogging();
        } else {
            return new DatabaseLogging();
        }
    }
}
