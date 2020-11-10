package base_classes;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import base_classes.classes.*;
import base_classes.classes.emuns.*;
import base_classes.util.HibernateUtil;


public class DBConnection {
    private Session session;

    public DBConnection(){
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
    }

    public void saveObject(Object object) {
        try {
            session.save(object);
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public List<User> getUserList(UE type, String value) {
        Query<User> query = null;
        String sql_comand = "";

        switch (type) {
            case ID:
            case ROLE_ID:
                sql_comand = User.search(type) + value;
                break;
            case NAME:
            case ROLE:
                sql_comand = User.search(type) + "'" + value + "'";
                break;
            case ALL:
                sql_comand = User.search(type);
                break;
            default:
                break;
        }
        
        query = session.createQuery(sql_comand, User.class);
        List<User> res = query.list(); 

        if (res.isEmpty()) {
            return null;
        }else {
            return res;
        }
    }

    public List<UserRoles> getUserRolesList(URE type, String value) {
        Query<UserRoles> query = null;
        String sql_comand = "";

        switch (type) {
            case ID:
                sql_comand = UserRoles.search(type) + value;
                break;
            case ROLE:
                sql_comand = UserRoles.search(type) + "'" + value + "'";
                break;
            case ALL:
                sql_comand = UserRoles.search(type);
                break;
            default:
                break;
        }
        
        query = session.createQuery(sql_comand, UserRoles.class);
        List<UserRoles> res = query.list(); 

        if (res.isEmpty()) {
            return null;
        }else {
            return res;
        }
    }

    public List<Clients> getClientsList(ClientsE type, String value){
        Query<Clients> query = null;
        String sql_comand = "";
        switch (type ) {
            case ID: 
            case RATING:
            case COUNTRY_ID:
                sql_comand = Clients.search(type) + value;
                break;
            case NAME:
            case BIRTH_DATE:
            case SEX:
            case PASSPORT_NUMBER:
            case PASSPORT_DATE:
            case CAR_NUMBER:
            case COUNTRY_NAME:
            case CHECK_IN:
            case CHECK_OUT:
                sql_comand = Clients.search(type) + "'" + value + "'";
                break;
            case ALL:
                sql_comand = Clients.search(type);
                break;
        
            default:
                break;
        }
        
        query = session.createQuery(sql_comand, Clients.class);
        List<Clients> res = query.list();

        if (res.isEmpty()) {
            return null;
        } else {
            return res;
        }
    }

    public List<AdditServices> getAdditServicesList(ADServicesE type, String value) {
        Query<AdditServices> query = null;
        String sql_comand = "";
        switch (type) {
            case ID:
            case PRICE:
                sql_comand = AdditServices.search(type) + value;
                break;
            case TITLE:
                sql_comand = AdditServices.search(type) + "'" + value + "'";
                break;
            case ALL:
                sql_comand = AdditServices.search(type);
                break;

            default:
                break;
        }

        query = session.createQuery(sql_comand, AdditServices.class);
        List<AdditServices> res = query.list();

        if (res.isEmpty()) {
            return null;
        } else {
            return res;
        }
    }

    public List<ClientUsedServices> getClientUsedServicesList(CUSe type, String value) {
        Query<ClientUsedServices> query = null;
        String sql_comand = "";
        switch (type) {
            case ID:
            case ADDIT_SERVICE_ID:
                sql_comand = ClientUsedServices.search(type) + value;
                break;
            case ADDIT_SERVICE_NAME:
            case DATE:
                sql_comand = ClientUsedServices.search(type) + "'" + value + "'";
                break;
            case ALL:
                sql_comand = ClientUsedServices.search(type);
                break;

            default:
                break;
        }

        query = session.createQuery(sql_comand, ClientUsedServices.class);
        List<ClientUsedServices> res = query.list();

        if (res.isEmpty()) {
            return null;
        } else {
            return res;
        }
    }

    public List<Country> getCoutryList(CountryE type, String value) {
        Query<Country> query = null;
        String sql_comand = "";
        switch (type) {
            case ID:
                sql_comand = Country.search(type) + value;
                break;
            case COUNTRY_NAME:
                sql_comand = Country.search(type) + "'" + value + "'";
                break;
            case ALL:
                sql_comand = Country.search(type);
                break;

            default:
                break;
        }

        query = session.createQuery(sql_comand, Country.class);
        List<Country> res = query.list();

        if (res.isEmpty()) {
            return null;
        } else {
            return res;
        }
    }

    public List<FoodType> getFoodTypeList(FTE type, String value) {
        Query<FoodType> query = null;
        String sql_comand = "";
        switch (type) {
            case ID:
                sql_comand = FoodType.search(type) + value;
                break;
            case TYPE:
                sql_comand = FoodType.search(type) + "'" + value + "'";
                break;
            case ALL:
                sql_comand = FoodType.search(type);
                break;
            default:
                break;
        }

        query = session.createQuery(sql_comand, FoodType.class);
        List<FoodType> res = query.list();

        if (res.isEmpty()) {
            return null;
        } else {
            return res;
        }
    }

    public List<RoomType> getRoomTypeList(RTE type, String value) {
        Query<RoomType> query = null;
        String sql_comand = "";
        switch (type) {
            case ID:
                sql_comand = RoomType.search(type) + value;
                break;
            case TYPE:
                sql_comand = RoomType.search(type) + "'" + value + "'";
                break;
            case ALL:
                sql_comand = RoomType.search(type);
                break;
            default:
                break;
        }

        query = session.createQuery(sql_comand, RoomType.class);
        List<RoomType> res = query.list();

        if (res.isEmpty()) {
            return null;
        } else {
            return res;
        }
    }
    
    public List<RoomStatus> getRoomStatusList(SE type, String value) {
        Query<RoomStatus> query = null;
        String sql_comand = "";
        switch (type) {
            case ID:
                sql_comand = RoomStatus.search(type) + value;
                break;
            case STATUS:
                sql_comand = RoomStatus.search(type) + "'" + value + "'";
                break;
            case ALL:
                sql_comand = RoomStatus.search(type);
                break;
            default:
                break;
        }

        query = session.createQuery(sql_comand, RoomStatus.class);
        List<RoomStatus> res = query.list();

        if (res.isEmpty()) {
            return null;
        } else {
            return res;
        }
    }

    public List<Room> getRoomList(RoomE type, String value) { //? TODO: look whats the result from the combined quereis
        Query<Room> query = null;
        String sql_comand = "";
        switch (type) {
            case ID:
            case NUMBER:
            case RAITING:
                sql_comand = Room.search(type) + value;
                break;
            case ROOM_TYPE:
                sql_comand = Room.search(type) + "'" + value + "'";
                break;
            case ROOM_STATUS:
                sql_comand = Room.search(type) + "'" + value + "'";
                break;
            case ALL:
                sql_comand = Room.search(type);
                break;
            default:
                break;
        }

        query = session.createQuery(sql_comand, Room.class);
        List<Room> res = query.list();

        if (res.isEmpty()) {
            return null;
        } else {
            return res;
        }
    }

    public List<ReservationType> getReservationTypeList(RTE type, String value) {
        Query<ReservationType> query = null;
        String sql_comand = "";
        switch (type) {
            case ID:
                sql_comand = ReservationType.search(type) + value;
                break;
            case TYPE:
                sql_comand = ReservationType.search(type) + "'" + value + "'";
                break;
            case ALL:
                sql_comand = ReservationType.search(type);
                break;
            default:
                break;
        }

        query = session.createQuery(sql_comand, ReservationType.class);
        List<ReservationType> res = query.list();

        if (res.isEmpty()) {
            return null;
        } else {
            return res;
        }
    }

    public List<ReservationStatus> getReservationStatusList(SE type, String value) {
        Query<ReservationStatus> query = null;
        String sql_comand = "";
        switch (type) {
            case ID:
                sql_comand = ReservationStatus.search(type) + value;
                break;
            case STATUS:
                sql_comand = ReservationStatus.search(type) + "'" + value + "'";
                break;
            case ALL:
                sql_comand = ReservationStatus.search(type);
                break;
            default:
                break;
        }

        query = session.createQuery(sql_comand, ReservationStatus.class);
        List<ReservationStatus> res = query.list();

        if (res.isEmpty()) {
            return null;
        } else {
            return res;
        }
    }

    public List<ReservationCancelType> getReservationCancelTypeList(RCTE type, String value) {
        Query<ReservationCancelType> query = null;
        String sql_comand = "";
        switch (type) {
            case ID:
                sql_comand = ReservationCancelType.search(type) + value;
                break;
            case CANCEL_TYPE:
                sql_comand = ReservationCancelType.search(type) + "'" + value + "'";
                break;
            case ALL:
                sql_comand = ReservationCancelType.search(type);
                break;
            default:
                break;
        }

        query = session.createQuery(sql_comand, ReservationCancelType.class);
        List<ReservationCancelType> res = query.list();

        if (res.isEmpty()) {
            return null;
        } else {
            return res;
        }
    }

    public void getReservationFormList() { // ? TODO: look whats the result from the combined quereis
        
    }
}
