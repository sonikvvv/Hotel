package logic.operations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import base_classes.DBConnection;
import base_classes.classes.ClientUsedServices;
import base_classes.classes.Clients;
import base_classes.classes.Hotel;
import base_classes.classes.Raiting;
import base_classes.classes.User;
import base_classes.classes.emuns.URE;


public class ClientOperations {

    public static List<Clients> getClientsInfo(DBConnection db, List<String> data) {
        List<Clients> clientList = new ArrayList<>();
        User user_now = UserOperations.getUser_now().get(0);
        if (user_now.getUser_role() == URE.ADMIN) {
            clientList = db.getAllClients();
        } else {
            for (Hotel hotel : user_now.getHotel()) {
                clientList.addAll(db.getClientsByHotel(hotel.getHotel_id()));
            }
        }

        LocalDateTime fromdate = DateOperations.toDateAndTime(data.get(0));
        LocalDateTime todate = DateOperations.toDateAndTime(data.get(1));
        List<Clients> result = new ArrayList<>();

        for (Clients clients : clientList) {
            if (DateOperations.compareDateTime(clients.getCheck_in(), fromdate, todate)) {
                result.add(clients);
            }
        }
        
        return result;
    }

    public static List<ClientUsedServices> getUsedServices(DBConnection db, List<String> data) {
        List<Clients> clientList = new ArrayList<>();
        User user_now = UserOperations.getUser_now().get(0);
        
        if (user_now.getUser_role() == URE.ADMIN) {
            clientList = db.getAllClients();
        } else {
            for (Hotel hotel : user_now.getHotel()) {
                clientList.addAll(db.getClientsByHotel(hotel.getHotel_id()));
            }
        }

        LocalDateTime fromdate = DateOperations.toDateAndTime(data.get(0));
        LocalDateTime todate = DateOperations.toDateAndTime(data.get(1));
        //List<ClientUsedServices> result = new ArrayList<>();
        List<ClientUsedServices> sortedByDate = new ArrayList<>();

        for (Clients clients : clientList) {
            List<ClientUsedServices> cus = clients.getCuds();
            for (ClientUsedServices cUsedServices : cus) {
                if (DateOperations.compareDateTime(cUsedServices.getPurchase_date(), fromdate, todate)) {
                    sortedByDate.add(cUsedServices);
                }
            }
            
        }
        // AdditServices ads;
        // for (ClientUsedServices clientUsedServices : sortedByDate) {// TODO: fix the sorting
        //     ads = clientUsedServices.getAddit_service();
        //     ClientUsedServices0
        //     for (ClientUsedServices clientUsedServices2 : sortedByDate) {
        //         if 
        //     }

        // }
        
        
        return sortedByDate;
    }
    
    public static List<Clients> getClientRaiting(DBConnection db, List<String> data) {
        List<Clients> clientList = new ArrayList<>();
        User user_now = UserOperations.getUser_now().get(0);

        if (user_now.getUser_role() == URE.ADMIN) {
            clientList = db.getAllClients();
        } else {
            for (Hotel hotel : user_now.getHotel()) {
                clientList.addAll(db.getClientsByHotel(hotel.getHotel_id()));
            }
        }

        LocalDateTime fromdate = DateOperations.toDateAndTime(data.get(0));
        LocalDateTime todate = DateOperations.toDateAndTime(data.get(1));

        List<Clients> result = new ArrayList<>();
        for (Clients clients : clientList) {
            Clients tmp = clients;
            List<Raiting> raits = new ArrayList<>();

            for (Raiting raiting : tmp.getRait()) {
                if (DateOperations.compareDateTime(raiting.getDate_made(), fromdate, todate)){
                    raits.add(raiting);
                }
            }

            Raiting max = raits.get(0);
            for (Raiting raiting : raits) {
                if(max.getRait_value() < raiting.getRait_value()){
                    max = raiting;
                }
            }

            tmp.getRait().set(0, max);
            result.add(tmp);
        }

        return clientList; 
    }
}
