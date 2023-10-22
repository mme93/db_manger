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
        serverNameList.add(new ServerNameObj("PI", "Raspberry Pi"));
        serverNameList.add(new ServerNameObj("CLOUD_SERVER", "Cloud Server"));
        serverNameList.add(new ServerNameObj("CLOUD_XXL", "Cloud XXL Server"));
        return serverNameList;
    }

}
