package mamei.backend.db.mariadb.table.utils;

import mamei.backend.db.mariadb.table.model.ENUM.MySQLDataType;

import java.util.Locale;

public class SQLEnumConverter {

    public static String getSQLTypAsStringFromEnum(MySQLDataType mySQLDataType, String settingValue){
        if(mySQLDataType.equals(MySQLDataType.INT)){
            return "BIGINT("+settingValue+")";
        }else  if(mySQLDataType.equals(MySQLDataType.DECIMAL)){
            return "DECIMAL("+settingValue+")";
        }else  if(mySQLDataType.equals(MySQLDataType.FLOAT)){
            return "FLOAT ("+settingValue+")";
        }else  if(mySQLDataType.equals(MySQLDataType.DOUBLE)){
            return "DOUBLE("+settingValue+")";
        }else  if(mySQLDataType.equals(MySQLDataType.VARCHAR)){
            return "VARCHAR("+settingValue+")";
        }else  if(mySQLDataType.equals(MySQLDataType.CHAR)){
            return "CHAR("+settingValue+")";
        }else  if(mySQLDataType.equals(MySQLDataType.DATE)){
            return "DATE("+settingValue+")";
        }else  if(mySQLDataType.equals(MySQLDataType.TIME)){
            return "TIME("+settingValue+")";
        }else  if(mySQLDataType.equals(MySQLDataType.DATETIME)){
            return "DATETIME("+settingValue+")";
        }else  if(mySQLDataType.equals(MySQLDataType.TIMESTAMP)){
            return "TIMESTAMP("+settingValue+")";
        }else  if(mySQLDataType.equals(MySQLDataType.ENUM)){
            return "ENUM("+settingValue+")";
        }else  if(mySQLDataType.equals(MySQLDataType.SET)){
            return "SET("+settingValue+")";
        }else  if(mySQLDataType.equals(MySQLDataType.BLOB)){
            return "BLOB("+settingValue+")";
        }else  if(mySQLDataType.equals(MySQLDataType.TEXT)){
            return "TEXT("+settingValue+")";
        }else  if(mySQLDataType.equals(MySQLDataType.BOOL)){
            return "BOOl("+settingValue+")";
        }else  if(mySQLDataType.equals(MySQLDataType.BOOLEAN)){
            return "BOOLEAN("+settingValue+")";
        }else  if(mySQLDataType.equals(MySQLDataType.JSON)){
            return "JSON("+settingValue+")";
        }else  if(mySQLDataType.equals(MySQLDataType.GEOMETRY)){
            return "GEOMETRY("+settingValue+")";
        }
        throw new RuntimeException("No SQLType found by Enum: "+mySQLDataType);
    }

    public static MySQLDataType getEnumFROMSQLTypString(String sqlType){
        sqlType=sqlType.toUpperCase(Locale.ROOT);
        if(sqlType.contains("BIGINT")){
            return MySQLDataType.INT;
        }else  if(sqlType.contains("DECIMAL")){
            return MySQLDataType.DECIMAL;
        }else  if(sqlType.contains("FLOAT")){
            return MySQLDataType.FLOAT;
        }else  if(sqlType.contains("DOUBLE")){
            return MySQLDataType.DOUBLE;
        }else  if(sqlType.contains("VARCHAR")){
            return MySQLDataType.VARCHAR;
        }else  if(sqlType.contains("CHAR")){
            return MySQLDataType.CHAR;
        }else  if(sqlType.contains("DATE")){
            return MySQLDataType.DATE;
        }else  if(sqlType.contains("TIME")){
            return MySQLDataType.TIME;
        }else  if(sqlType.contains("DATETIME")){
            return MySQLDataType.DATETIME;
        }else  if(sqlType.contains("TIMESTAMP")){
            return MySQLDataType.TIMESTAMP;
        }else  if(sqlType.contains("ENUM")){
            return MySQLDataType.ENUM;
        }else  if(sqlType.contains("SET")){
            return MySQLDataType.SET;
        }else  if(sqlType.contains("BLOB")){
            return MySQLDataType.BLOB;
        }else  if(sqlType.contains("TEXT")){
            return MySQLDataType.TEXT;
        }else  if(sqlType.contains("BOOl")){
            return MySQLDataType.BOOL;
        }else  if(sqlType.contains("BOOLEAN")){
            return MySQLDataType.BOOLEAN;
        }else  if(sqlType.contains("JSON")){
            return MySQLDataType.JSON;
        }else  if(sqlType.contains("GEOMETRY")){
            return MySQLDataType.GEOMETRY;
        }
        throw new RuntimeException("No Enum found by SQLType: "+sqlType);
    }

}
