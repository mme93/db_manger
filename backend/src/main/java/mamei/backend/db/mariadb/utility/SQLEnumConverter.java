package mamei.backend.db.mariadb.utility;

import mamei.backend.db.mariadb.model.ENUM.MySQLDataType;

public class SQLEnumConverter {

    public String getSQLTypAsStringFromEnum(MySQLDataType mySQLDataType, String settingValue){
        if(mySQLDataType.equals(MySQLDataType.INT)){
            return "bigint("+settingValue+")";
        }else  if(mySQLDataType.equals(MySQLDataType.DECIMAL)){
            return "bigint("+settingValue+")";
        }else  if(mySQLDataType.equals(MySQLDataType.FLOAT)){
            return "bigint("+settingValue+")";
        }else  if(mySQLDataType.equals(MySQLDataType.DOUBLE)){
            return "bigint("+settingValue+")";
        }else  if(mySQLDataType.equals(MySQLDataType.VARCHAR)){
            return "bigint("+settingValue+")";
        }else  if(mySQLDataType.equals(MySQLDataType.CHAR)){
            return "bigint("+settingValue+")";
        }else  if(mySQLDataType.equals(MySQLDataType.DATE)){
            return "bigint("+settingValue+")";
        }else  if(mySQLDataType.equals(MySQLDataType.TIME)){
            return "bigint("+settingValue+")";
        }else  if(mySQLDataType.equals(MySQLDataType.DATETIME)){
            return "bigint("+settingValue+")";
        }else  if(mySQLDataType.equals(MySQLDataType.TIMESTAMP)){
            return "bigint("+settingValue+")";
        }else  if(mySQLDataType.equals(MySQLDataType.ENUM)){
            return "bigint("+settingValue+")";
        }else  if(mySQLDataType.equals(MySQLDataType.SET)){
            return "bigint("+settingValue+")";
        }else  if(mySQLDataType.equals(MySQLDataType.BLOB)){
            return "bigint("+settingValue+")";
        }else  if(mySQLDataType.equals(MySQLDataType.TEXT)){
            return "bigint("+settingValue+")";
        }else  if(mySQLDataType.equals(MySQLDataType.BOOL)){
            return "bigint("+settingValue+")";
        }else  if(mySQLDataType.equals(MySQLDataType.BOOLEAN)){
            return "bigint("+settingValue+")";
        }else  if(mySQLDataType.equals(MySQLDataType.JSON)){
            return "bigint("+settingValue+")";
        }else  if(mySQLDataType.equals(MySQLDataType.GEOMETRY)){
            return "bigint("+settingValue+")";
        }
        throw new RuntimeException("No Enum found by Name: "+mySQLDataType);
    }

}
