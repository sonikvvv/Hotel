package base_classes;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import base_classes.classes.AdditServices;
import base_classes.classes.Clients;
import base_classes.classes.Hotel;
import base_classes.classes.Reservation;
import base_classes.classes.Room;
import base_classes.classes.ServiceCategory;
import base_classes.classes.User;
import base_classes.classes.emuns.URE;
import base_classes.util.HibernateUtil;

public class DBConnection {
    private Session session;

    public DBConnection() {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public void saveOrUpdateObject(Object object) {
        try {
            session.beginTransaction();
            session.saveOrUpdate(object.getClass().toString(), object);
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            session.getTransaction().commit();
        }
    }

    public void deleteObject(Object object) {
        try {
            session.beginTransaction();
            session.delete(object.getClass().toString(), object);
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.getTransaction().commit();
        }
    }

    public User getUserByUsername(String data) {
        Query<User> query = null;
        String sql = "from " + User.getTableName() + "  where " + User.getFields().get(1) + " = :name";

        query = session.createQuery(sql, User.class);
        query.setParameter("name", data);
        User result = query.uniqueResult();

        return result;
    }

    public User getUserByID(int id) {
        User result = session.get(User.class, id);
        return result;
    }

    public List<User> getAllUsers(){
        Query<User> query = null;
        String sql_comand = "from " + User.getTableName();

        query = session.createQuery(sql_comand, User.class);
        List<User> res = query.list();

        if (res.isEmpty()) {
            return null;
        } else {
            return res;
        }
    }

    public List<User> getUserByRole(URE type) {
        Query<User> query = null;
        String sql_comand = "from " + User.getTableName() + " where " + User.getFields().get(3) + " = :role";

        query = session.createQuery(sql_comand, User.class);
        query.setParameter("role", type);
        List<User> res = query.list();

        if (res.isEmpty()) {
            return null;
        } else {
            return res;
        }
    
    }

    public List<User> getUserByHotel(int hotel_id) {
        Query<User> query = null;
        String sql_comand = "select t from " + User.getTableName() + " t join t." + User.getFields().get(4) +
                " h where h." + Hotel.getFields().get(0) + " = :hotel_id";

        query = session.createQuery(sql_comand, User.class);
        query.setParameter("hotel_id", hotel_id);
        List<User> res = query.list();

        if (res.isEmpty()) {
            return null;
        } else {
            return res;
        }
    
    }

    public List<AdditServices> getAdditServicesByHotel(int hotel_id) {
        Query<AdditServices> query = null;
        String sql_comand = "select t from " + AdditServices.getTableName() + " t join t." + AdditServices.getFields().get(4)
                + " h where h." + Hotel.getFields().get(0) + " = :hotel_id";

        query = session.createQuery(sql_comand, AdditServices.class);
        query.setParameter("hotel_id", hotel_id);
        List<AdditServices> res = query.list();

        if (res.isEmpty()) {
            return null;
        } else {
            return res;
        }
    }

    public List<ServiceCategory> getAllServiceCategories() {
        Query<ServiceCategory> query = null;
        String sql_comand = "from " + ServiceCategory.getTableName() ;

        query = session.createQuery(sql_comand, ServiceCategory.class);
        List<ServiceCategory> res = query.list();

        if (res.isEmpty()) {
            return null;
        } else {
            return res;
        }
    }

    public Hotel getHotelById(int hotel_id) {
        Hotel result = session.get(Hotel.class, hotel_id);
        return result;
    }

    public Hotel getHotelByName(String name) {
        Query<Hotel> query = null;
        String sql_comand = "from " + Hotel.getTableName() + " where " + Hotel.getFields().get(1) + " = :name";

        query = session.createQuery(sql_comand, Hotel.class);
        query.setParameter("name", name);
        Hotel res = query.uniqueResult();
        return res;
    }

    public List<Hotel> getAllHotels() {
        Query<Hotel> query = null;
        String sql_comand = "from " + Hotel.getTableName();

        query = session.createQuery(sql_comand, Hotel.class);
        List<Hotel> res = query.list();
        return res;
    }

    public List<Reservation> getAllReservationsByHotel(int hotel_id) {
        Query<Reservation> query = null;
        String sql_comand = "select t from " + Reservation.getTableName() + " t join t."
                + Reservation.getFields().get(4) + " h where h." + Hotel.getFields().get(0) + " = :hotel_id";

        query = session.createQuery(sql_comand, Reservation.class);
        query.setParameter("hotel_id", hotel_id);
        List<Reservation> res = query.list();

        if (res.isEmpty()) {
            return null;
        } else {
            return res;
        }
    }

    public Room getRoomByID(int id) {
        Room result = session.get(Room.class, id);
        return result;
    }

    public List<Room> getRoomsByHotel(int hotel_id) {
        Query<Room> query = null;
        String sql_comand = "select t from " + Room.getTableName() + " t join t."
                + Room.getFields().get(4) + " h where h." + Hotel.getFields().get(0) + " = :hotel_id";

        query = session.createQuery(sql_comand, Room.class);
        query.setParameter("hotel_id", hotel_id);
        List<Room> res = query.list();

        if (res.isEmpty()) {
            return null;
        } else {
            return res;
        }
    }

    public Clients getClientByID(int id) {
        Clients result = session.get(Clients.class, id);
        return result;
    }

    public List<Clients> getClientsByHotel(int hotel_id) {
        Query<Clients> query = null;
        String sql_comand = "select t from " + Clients.getTableName() + " t join t." + Clients.getFields().get(11)
                + " h where h." + Hotel.getFields().get(0) + " = :hotel_id";

        query = session.createQuery(sql_comand, Clients.class);
        query.setParameter("hotel_id", hotel_id);
        List<Clients> res = query.list();

        if (res.isEmpty()) {
            return null;
        } else {
            return res;
        }
    }
}
