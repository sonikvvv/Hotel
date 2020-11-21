package logic.operations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import base_classes.DBConnection;
import base_classes.classes.AdditServices;
import base_classes.classes.ClientUsedServices;
import base_classes.classes.Clients;
import base_classes.classes.Country;
import base_classes.classes.Raiting;
import base_classes.classes.ServiceCategory;
import base_classes.classes.emuns.ServiceType;


public class ClientOperations {

    public static List<Clients> getClientsInfo(DBConnection db, List<String> data) {
        List<Clients> clientList = new ArrayList<>();
        Clients c = new Clients("name", LocalDate.now(), false, "passport_number", LocalDate.now(), "car_number",
                new Country("Testivile"), "client_note", "vaucher");
        clientList.add(c);
        return clientList;
    }

    public static List<ClientUsedServices> getUsedServices(DBConnection db, List<String> data) {
        List<ClientUsedServices> cusList = new ArrayList<>();
        ServiceCategory sc = new ServiceCategory("category_title", ServiceType.NEGATIVE);
        AdditServices ads = new AdditServices("title", sc, 20);
        ClientUsedServices cus = new ClientUsedServices(ads, 5, "note");
        cusList.add(cus);
        return cusList; //db.getUsedServiceFromDate(data.get(0), data.get(1));
    }
    
    public static List<Clients> getClientRaiting(DBConnection db, List<String> data) { // TODO: figure the raiting
        List<Clients> clientList = new ArrayList<>();
        Clients c = new Clients("name", LocalDate.now(), false, "passport_number", LocalDate.now(), "car_number", null,
                "client_note", "vaucher");
        Raiting r = new Raiting(6.1);
        List<Raiting> rl = new ArrayList<>();
        rl.add(r);
        c.setRait(rl);

        clientList.add(c);

        return clientList; 
    }
}
