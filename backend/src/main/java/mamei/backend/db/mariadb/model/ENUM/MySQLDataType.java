package mamei.backend.db.mariadb.model.ENUM;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * ENUM for MySQL Data-Type.
 */
public enum MySQLDataType {
    INT,
    DECIMAL,
    FLOAT,
    DOUBLE,
    VARCHAR,
    CHAR,
    DATE,
    TIME,
    DATETIME,
    TIMESTAMP,
    ENUM,
    SET,
    BLOB,
    TEXT,
    BOOL,
    BOOLEAN,
    JSON,
    GEOMETRY
}
