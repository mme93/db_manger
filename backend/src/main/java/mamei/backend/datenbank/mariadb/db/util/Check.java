package mamei.backend.datenbank.mariadb.db.util;

public class Check {

    public static boolean isEmptyOrNull(String value) {
        return value.isBlank() || value.isEmpty() || value == null;
    }
}
