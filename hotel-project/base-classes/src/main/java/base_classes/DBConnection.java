package base_classes;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import base_classes.classes.*;
import base_classes.classes.emuns.*;
import base_classes.util.HibernateUtil;

public class DBConnection {
    private Session session;

    public DBConnection() {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public void saveObject(Object object) {
        try {
            session.clear();
            session.save(object);
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    public void updateObject(Object object) {
        session.update(object);
    }

    public List<User> getUserList(UE type, String value) {
        Query<User> query = null;
        String sql_comand = "";

        switch (type) {
            case ID:
                sql_comand = User.search(type) + value;
                break;
            case NAME:
            case ROLE:
                sql_comand = User.search(type) + value + "'";
                break;
            case ALL:
                sql_comand = User.search(type);
                break;
            default:
                break;
        }

        query = session.createQuery(sql_comand, User.class);
        List<User> res = query.list(); 

        // session.close(); session.transa
        if (res.isEmpty()) {
            return null;
        } else {
            return res;
        }
    }

    public List<Clients> getClientsList(ClientsE type, String value) {
        Query<Clients> query = null;
        String sql_comand = "";
        switch (type) {
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
                sql_comand = Clients.search(type) + value + "'";
                break;
            case ALL:
                sql_comand = "select t from " + Clients.getTableName() + " t";
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
                sql_comand = ClientUsedServices.search(type) + value + "'";
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
                sql_comand = Country.search(type) + value + "')";
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

    public List<Room> getRoomList(RoomE type, String value) { // ? TODO: look whats the result from the combined quereis
        Query<Room> query = null;
        String sql_comand = "";
        List<Room> res = null;

        switch (type) {
            case ID:
            case RAITING:
                sql_comand = Room.search(type) + value;
                break;
            case NUMBER:
            case ROOM_TYPE:
            case ROOM_STATUS:
                sql_comand = Room.search(type) + value + "'";
                break;
            case ALL:
                sql_comand = Room.search(RoomE.ALL);
                break;
            default:
                break;
        }

        query = session.createQuery(sql_comand, Room.class);
        res= query.getResultList();

        if (res.isEmpty()) {
            return null;
        } else {
            return res;
        }
    }

    public List<Clients> getClientFromDate(String from_date, String to_date) {
        Query<Clients> query = null;
        String sql_comand = "";
        List<Clients> res = null;

        sql_comand = "from " + Clients.getTableName() + "t where t." + Clients.getFields().get(9) + " BETWEEN to_date('"
                + from_date + "', 'YYYY-MM-DD') AND to_date('" + to_date + "', 'YYYY-MM-DD')";

        query = session.createQuery(sql_comand, Clients.class);
        res = query.getResultList();

        if (res.isEmpty()) {
            return null;
        } else {
            return res;
        }
    }

    public List<ClientUsedServices> getUsedServiceFromDate(String from_date, String to_date) {
        Query<ClientUsedServices> query = null;
        String sql_comand = "";
        List<ClientUsedServices> res = null;

        sql_comand = "from " + ClientUsedServices.getTableName() + "t where t." + ClientUsedServices.getFields().get(3) 
                + " BETWEEN to_date('" + from_date + "', 'YYYY-MM-DD') AND to_date('" + to_date + "', 'YYYY-MM-DD')";

        query = session.createQuery(sql_comand, ClientUsedServices.class);
        res = query.getResultList();

        if (res.isEmpty()) {
            return null;
        } else {
            return res;
        }
    }

    public void getReservationFormList() { // ? TODO: look whats the result from the combined quereis

    }

    public void getReservationList() { // ? TODO: look whats the result from the combined quereis

    }

    public List<Reservation> getReservationFromDate(String from_date, String to_date) {
        Query<Reservation> query = null;
        String sql_comand = "";
        List<Reservation> res = null;

        sql_comand = "from " + Reservation.getTableName() + "t where t." + Reservation.getFields().get(3) + "."
                + ReservationForm.getFields().get(4) + " BETWEEN to_date('" + from_date
                + "', 'YYYY-MM-DD') AND to_date('" + to_date + "', 'YYYY-MM-DD')";

        query = session.createQuery(sql_comand, Reservation.class);
        res = query.getResultList();

        if (res.isEmpty()) {
            return null;
        } else {
            return res;
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public List getRceptionistReservations(String recept_name) {
        Query<User> query = null;
        String sql_comand = "";
        List res = new ArrayList<>();

        sql_comand = User.search(UE.NAME) + recept_name + "'";

        query = session.createQuery(sql_comand, User.class);
        User recept = query.getSingleResult();

        sql_comand = Reservation.search(ReservE.RECEPTIONIST_ID) + recept.getUser_id();
        query = session.createQuery(sql_comand, User.class);
        res = query.getResultList();

        res.add(recept);

        if (res.isEmpty()) {
            return null;
        } else {
            return res;
        }
    }
}
