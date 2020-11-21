package logic.operations;

import java.util.ArrayList;
import java.util.List;

import base_classes.DBConnection;
import base_classes.classes.AdditServices;
import base_classes.classes.ServiceCategory;
import base_classes.classes.emuns.ServiceType;

public class AdditionalServicesOperations {
    private static List<AdditServices> adsl = new ArrayList<>();

    public static List<AdditServices> getAllAdditionalServices(DBConnection db) {
        if (adsl.size() != 0) {
            return adsl;
        }else {
            AdditServices as = new AdditServices("title", new ServiceCategory("category_title", ServiceType.PROSITIVE), 13);
            AdditServices as1 = new AdditServices("title", new ServiceCategory("category_title", ServiceType.PROSITIVE),
                    15);
            AdditServices as2 = new AdditServices("title", new ServiceCategory("category_title", ServiceType.PROSITIVE),
                    16);
            
            adsl.add(as);
            adsl.add(as1);
            adsl.add(as2);
            
            return adsl;
        }
    }
}
