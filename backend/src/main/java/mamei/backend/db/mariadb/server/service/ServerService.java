package mamei.backend.db.mariadb.server.service;

import mamei.backend.db.mariadb.html.model.DataBaseObj;
import mamei.backend.db.mariadb.html.model.ServerNameObj;
import mamei.backend.db.mariadb.html.model.ServerObj;
import mamei.backend.db.mariadb.html.model.TableObj;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

@Service
public class ServerService {

    public List<ServerNameObj> getServerNames(){
        List<ServerNameObj> serverNameList =  new ArrayList();
        serverNameList.add(new ServerNameObj("No server selected", "empty"));
        serverNameList.add(new ServerNameObj("pi_maria_db", "Raspberry Pi"));
        serverNameList.add(new ServerNameObj("cloud_server_maria_db", "Cloud Server"));
        serverNameList.add(new ServerNameObj("cloud_xxl_maria_db", "Cloud XXL Server"));
        return serverNameList;
    }

    public List<ServerObj> getDabases(){
        List<ServerObj>serverList= new ArrayList<>();
        List<TableObj> tableList= new ArrayList<>();
        tableList.add(new TableObj("Autos"));
        tableList.add(new TableObj("Preisliste"));
        tableList.add(new TableObj("Marke"));
        serverList.add(new ServerObj("pi_maria_db", "Raspberry Pi",asList(new DataBaseObj("Mameie",tableList))));
        List<TableObj> tableList2= new ArrayList<>();
        tableList2.add(new TableObj("Flugzeuge"));
        tableList2.add(new TableObj("Preisliste"));
        tableList2.add(new TableObj("Marke"));
        serverList.add(new ServerObj("cloud_server_maria_db", "Cloud Server",asList(new DataBaseObj("Good Obj",tableList2))));
        serverList.add(new ServerObj("cloud_xxl_maria_db", "Cloud XXL Server", new ArrayList<>()));
        return serverList;
    }
}
