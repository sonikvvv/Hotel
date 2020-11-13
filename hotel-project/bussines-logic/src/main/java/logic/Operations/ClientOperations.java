package logic.operations;

import java.util.List;

import base_classes.DBConnection;
import base_classes.classes.ClientUsedServices;
import base_classes.classes.Clients;


public class ClientOperations {
    public static List<Clients> getClientsInfo(DBConnection db, List<String> data) {
        return db.getClientFromDate(data.get(0), data.get(1));
    }

    public static List<ClientUsedServices> getUsedServices(DBConnection db, List<String> data) {
        return db.getUsedServiceFromDate(data.get(0), data.get(1));
    }
    
    @SuppressWarnings("rawtypes")
    public static List getClientRaiting(DBConnection db, List<String> data) { // TODO: figure the raiting
        return null; 
    }
}
