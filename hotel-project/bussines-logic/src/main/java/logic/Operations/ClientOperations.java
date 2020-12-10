package logic.operations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import base_classes.DBConnection;
import base_classes.classes.AdditServices;
import base_classes.classes.ClientUsedServices;
import base_classes.classes.Clients;
import base_classes.classes.Hotel;
import base_classes.classes.Raiting;
import base_classes.classes.User;
import base_classes.classes.emuns.URE;

public class ClientOperations {
    private static final Logger LOGGER = LogManager.getLogger(ClientOperations.class);

    public static List<Clients> getClientsInfo(DBConnection db, List<String> data) {
        LOGGER.debug("Starting getClientsInfo with data {}", data);
        List<Clients> clientList = new ArrayList<>();
        User user_now = UserOperations.getUser_now().get(0);
        if (user_now.getUser_role() == URE.ADMIN) {
            clientList = db.getAllClients();
            LOGGER.debug("Getting clients from all hotels.");
        } else {
            for (Hotel hotel : user_now.getHotel()) {
                clientList.addAll(db.getClientsByHotel(hotel.getHotel_id()));
                LOGGER.debug("Getting clients from hotel id: {}.", hotel.getHotel_id());
            }
        }

        LocalDateTime fromdate = DateOperations.toDateAndTime(data.get(0));
        LocalDateTime todate = DateOperations.toDateAndTimeEndOfDay(data.get(1));
        List<Clients> result = new ArrayList<>();

        for (Clients clients : clientList) {
            if (DateOperations.compareDateTime(clients.getCheck_in(), fromdate, todate)) {
                result.add(clients);
            }
        }
        
        LOGGER.debug("Sorted clients by dates: {} - {}.", fromdate.toString(), todate.toString());
        LOGGER.debug("Result. getClientsInfo {} ", result.toString());
        return result;
    }

    public static List<ClientUsedServices> getUsedServices(DBConnection db, List<String> data) {
        LOGGER.debug("Starting getUsedServices with data {}", data);
        List<Clients> clientList = new ArrayList<>();
        User user_now = UserOperations.getUser_now().get(0);
        
        if (user_now.getUser_role() == URE.ADMIN) {
            clientList = db.getAllClients();
            LOGGER.debug("Getting clients from all hotels.");
        } else {
            for (Hotel hotel : user_now.getHotel()) {
                clientList.addAll(db.getClientsByHotel(hotel.getHotel_id()));
                LOGGER.debug("Getting clients from hotel id: {}.", hotel.getHotel_id());
            }
        }

        LocalDateTime fromdate = DateOperations.toDateAndTime(data.get(0));
        LocalDateTime todate = DateOperations.toDateAndTimeEndOfDay(data.get(1));
        List<ClientUsedServices> result = new ArrayList<>();
        List<ClientUsedServices> sortedByDate = new ArrayList<>();
        List<String> distinct_services = db.getDistinctAdditionalServices();

        for (Clients clients : clientList) {
            List<ClientUsedServices> cus = clients.getCuds();
            for (ClientUsedServices cUsedServices : cus) {
                if (DateOperations.compareDateTime(cUsedServices.getPurchase_date(), fromdate, todate)) {
                    sortedByDate.add(cUsedServices);
                }
            }
            
        }

        LOGGER.debug("Sorted client used services by dates: {} - {}.", fromdate.toString(),
                todate.toString());

        for (String service_name : distinct_services) {
            AdditServices service = null;
            boolean flag = false;
            int quantity = 0;
            for (ClientUsedServices cus : sortedByDate) {
                if (service_name.equals(cus.getAddit_service().getTitle())) {
                    if (flag == false){
                        service = cus.getAddit_service();
                        flag = true;
                    }
                    quantity += cus.getQuantity();
                }
            }
            if(quantity != 0) {
                ClientUsedServices grouped = new ClientUsedServices();
                grouped.setAddit_service(service);
                grouped.setQuantity(quantity);
                result.add(grouped);
            }
        }
     
        LOGGER.debug("Created new clients used services grouped by additional service. ");
        LOGGER.debug("Result. {} ", result.toString());
        
        return result;
    }
    
    public static List<Clients> getClientRaiting(DBConnection db, List<String> data) {
        LOGGER.debug("Starting getClientRaiting with data {}", data);
        List<Clients> clientList = new ArrayList<>();
        User user_now = UserOperations.getUser_now().get(0);

        if (user_now.getUser_role() == URE.ADMIN) {
            clientList = db.getAllClients();
            LOGGER.debug("Getting clients from all hotels.");
        } else {
            for (Hotel hotel : user_now.getHotel()) {
                clientList.addAll(db.getClientsByHotel(hotel.getHotel_id()));
                LOGGER.debug("Getting clients from hotel id: {}.", hotel.getHotel_id());
            }
        }

        LocalDateTime fromdate = DateOperations.toDateAndTime(data.get(0));
        LocalDateTime todate = DateOperations.toDateAndTimeEndOfDay(data.get(1));

        LOGGER.debug("Sorting by date the ratings and getting the biggest by value from this period.");
        List<Clients> result = new ArrayList<>();
        for (Clients clients : clientList) {
            Clients tmp = clients;
            List<Raiting> raits = new ArrayList<>();

            for (Raiting raiting : tmp.getRait()) {
                if (DateOperations.compareDateTime(raiting.getDate_made(), fromdate, todate)){
                    raits.add(raiting);
                }
            }

            Raiting max = null;
            if (raits != null && raits.size() != 0) {
                max = raits.get(0);
                for (Raiting raiting : raits) {
                    if(max.getRait_value() < raiting.getRait_value()){
                        max = raiting;
                    }
                }
                tmp.getRait().set(0, max);
                result.add(tmp);
            }
        }

        LOGGER.debug("Result. - {}", result);

        return result; 
    }
}
