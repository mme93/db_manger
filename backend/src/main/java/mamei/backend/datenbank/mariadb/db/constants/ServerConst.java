package mamei.backend.datenbank.mariadb.db.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ServerConst {

    @Value("${pi_maria_db.ip}")
    public static String pi_maria_db_ip;

    @Value("${pi_maria_db.user}")
    public static String pi_maria_db_userName;

    @Value("${pi_maria_db.password}")
    public static String pi_maria_db_password;

    @Value("${cloud_server_maria_db.ip}")
    public static String cloud_server_maria_db_ip;

    @Value("${cloud_server_maria_db.user}")
    public static String cloud_server_maria_db_userName;

    @Value("${cloud_server_maria_db.password}")
    public static String cloud_server_maria_db_password;

    @Value("${cloud_xxl_maria_db.ip}")
    public static String cloud_xxl_maria_db_ip;

    @Value("${cloud_xxl_maria_db.user}")
    public static String cloud_xxl_maria_db_userName;

    @Value("${cloud_xxl_maria_db.password}")
    public static String cloud_xxl_maria_db_password;

}
