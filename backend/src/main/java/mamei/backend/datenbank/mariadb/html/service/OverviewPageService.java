package mamei.backend.datenbank.mariadb.html.service;

import mamei.backend.datenbank.mariadb.html.model.ServerNameObj;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OverviewPageService {

    public List<ServerNameObj> getServerNameList(){
        List<ServerNameObj> serverNameList =  new ArrayList();
        serverNameList.add(new ServerNameObj("No server selected", "empty"));
        serverNameList.add(new ServerNameObj("pi_maria_db", "Raspberry Pi"));
        serverNameList.add(new ServerNameObj("cloud_server_maria_db", "Cloud Server"));
        serverNameList.add(new ServerNameObj("cloud_xxl_maria_db", "Cloud XXL Server"));
        return serverNameList;
    }

}
