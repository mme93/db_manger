package mamei.backend.db.mariadb.server.service;

import mamei.backend.db.mariadb.html.model.ServerNameObj;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServerService {

    public List<ServerNameObj> getServerNameList() {
        List<ServerNameObj> serverList = new ArrayList<>();
        serverList.add(new ServerNameObj("pi_maria_db", "Raspberry Pi"));
        serverList.add(new ServerNameObj("cloud_server_maria_db", "Cloud Server"));
        serverList.add(new ServerNameObj("cloud_xxl_maria_db", "Cloud XXL Server"));
        return serverList;
    }
}
