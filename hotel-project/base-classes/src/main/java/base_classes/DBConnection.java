package base_classes;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    private static final Logger LOGGER = LogManager.getLogger(DBConnection.class);

    public DBConnection() {
        LOGGER.debug("---Opening session---");
        session = HibernateUtil.getSessionFactory().openSession();
        LOGGER.info("Inititalized DB connection");
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public void saveOrUpdateObject(Object object) {
        LOGGER.debug("Save Or Update");
        try {
            session.beginTransaction();
            session.saveOrUpdate(object.getClass().toString(), object);
            LOGGER.trace("Saved object: {}", object);
        } catch (Exception e) {
            LOGGER.error("Fail to begin transaction or saving or updating {}: {}", object, e);
            session.getTransaction().rollback();
        }finally {
            session.getTransaction().commit();
            LOGGER.debug("Save Or Update -> end with commit.");
        }
    }

    public void deleteObject(Object object) {
        try {
            session.beginTransaction();
            session.delete(object.getClass().toString(), object);
        } catch (Exception e) {
            LOGGER.error("Fail to begin transaction or deleting {}: {}", object, e);
            session.getTransaction().rollback();
        } finally {
            session.getTransaction().commit();
        }
    }

    public User getUserByUsername(String data) {
        LOGGER.debug("getUserByUsername");
        Query<User> query = null;
        String sql = "from " + User.getTableName() + "  where " + User.getFields().get(1) + " = :name";

        try {
            query = session.createQuery(sql, User.class);
            query.setParameter("name", data);
            User result = query.uniqueResult();
            return result;
        } catch (Exception e) {
            LOGGER.error("No unique user with this name: {} -: {}", data, e);
            return null;
        }
    }

    public User getUserByID(int id) {
        try {
            return session.get(User.class, id);
        } catch (Exception e) {
            LOGGER.error("User with id not found {" + id + "} -: {} ", e);
            return null;
        }
    }

    public List<User> getAllUsers(){
        LOGGER.debug("getUserByUsername");
        Query<User> query = null;
        String sql_comand = "from " + User.getTableName();

        try {
            query = session.createQuery(sql_comand, User.class);
            return query.list();
        } catch (Exception e) {
            LOGGER.error("Problem with getting all users. {}", e);
            return null;
        }
    }

    public List<User> getUserByRole(URE type) {
        Query<User> query = null;
        String sql_comand = "from " + User.getTableName() + " where " + User.getFields().get(3) + " = :role";

        try {
            query = session.createQuery(sql_comand, User.class);
            query.setParameter("role", type);
            return query.list();
        } catch (Exception e) {
            LOGGER.error("Problem with getting users by role: {} -: {}", type, e);
            return null;
        }
    }

    public List<User> getUserByHotel(int hotel_id) {
        Query<User> query = null;
        String sql_comand = "select t from " + User.getTableName() + " t join t." + User.getFields().get(4) +
                " h where h." + Hotel.getFields().get(0) + " = :hotel_id";

        try {
            query = session.createQuery(sql_comand, User.class);
            query.setParameter("hotel_id", hotel_id);
            return query.list();
        } catch (Exception e) {
            LOGGER.error("Problem with getting users by hotel id: {} -: {}", hotel_id, e);
            return null;
        }
    }

    public List<AdditServices> getAdditServicesByHotel(int hotel_id) {
        Query<AdditServices> query = null;
        String sql_comand = "select t from " + AdditServices.getTableName() + " t join t." + AdditServices.getFields().get(4)
                + " h where h." + Hotel.getFields().get(0) + " = :hotel_id";

        try {
            query = session.createQuery(sql_comand, AdditServices.class);
            query.setParameter("hotel_id", hotel_id);
            return query.list();
        } catch (Exception e) {
            LOGGER.error("Problem with getting additional services by hotel id: {} -: {}", hotel_id, e);
            return null;
        }
    }

    public List<AdditServices> getAllAdditServices() {
        Query<AdditServices> query = null;
        String sql_comand = "from " + AdditServices.getTableName();

        try {
            query = session.createQuery(sql_comand, AdditServices.class);
            return query.list();
        } catch (Exception e) {
            LOGGER.error("Problem with getting all additional services. -: {}", e);
            return null;
        }

    }

    public List<ServiceCategory> getAllServiceCategories() {
        Query<ServiceCategory> query = null;
        String sql_comand = "from " + ServiceCategory.getTableName() ;

        try {
            query = session.createQuery(sql_comand, ServiceCategory.class);
            return query.list();
        } catch (Exception e) {
            LOGGER.error("Problem with getting all service categories -: {}", e);
            return null;
        }
    }

    public Hotel getHotelById(int hotel_id) {
        try {
            return session.get(Hotel.class, hotel_id);
        } catch (Exception e) {
            LOGGER.error("Problem with getting hotel by id: {} -: {}", hotel_id, e);
            return null;
        }
    }

    public Hotel getHotelByName(String name) {
        Query<Hotel> query = null;
        String sql_comand = "from " + Hotel.getTableName() + " where " + Hotel.getFields().get(1) + " = :name";

        try {
            query = session.createQuery(sql_comand, Hotel.class);
            query.setParameter("name", name);
            return query.uniqueResult();
        } catch (Exception e) {
            LOGGER.error("Problem with getting hotel by name: {} -: {}", name, e);
            return null;
        }
    }

    public List<Hotel> getAllHotels() {
        Query<Hotel> query = null;
        String sql_comand = "from " + Hotel.getTableName();

        try {
            query = session.createQuery(sql_comand, Hotel.class);
            return query.list();
        } catch (Exception e) {
            LOGGER.error("Problem with getting all hotels -: {}", e);
            return null;
        }
    }

    public List<Reservation> getAllReservationsByHotel(int hotel_id) {
        Query<Reservation> query = null;
        String sql_comand = "select t from " + Reservation.getTableName() + " t join t."
                + Reservation.getFields().get(4) + " h where h." + Hotel.getFields().get(0) + " = :hotel_id";

        try {
            query = session.createQuery(sql_comand, Reservation.class);
            query.setParameter("hotel_id", hotel_id);
            return query.list();
        } catch (Exception e) {
            LOGGER.error("Problem getting reservations by hotel id: {} -: {}", hotel_id, e);
            return null;
        }
    }

    public Room getRoomByID(int id) {
        try {
            return session.get(Room.class, id);
        } catch (Exception e) {
            LOGGER.error("Problem getting room by id: {} -: {}", id, e);
            return null;
        }
    }

    public List<Room> getRoomsByHotel(int hotel_id) {
        Query<Room> query = null;
        String sql_comand = "select t from " + Room.getTableName() + " t join t."
                + Room.getFields().get(4) + " h where h." + Hotel.getFields().get(0) + " = :hotel_id";

        try {
            query = session.createQuery(sql_comand, Room.class);
            query.setParameter("hotel_id", hotel_id);
            return query.list();
        } catch (Exception e) {
            LOGGER.error("Problem getting rooms by hotel id: {} -: {}", hotel_id, e);
            return null;
        }

    }

    public List<Room> getAllRooms() {
        Query<Room> query = null;
        String sql_comand = "from " + Room.getTableName();

        try {
            query = session.createQuery(sql_comand, Room.class);
            return query.list();
        } catch (Exception e) {
            LOGGER.error("Problem getting all rooms -: {}", e);
            return null;
        }
    }

    public Clients getClientByID(int id) {
        try {
            return session.get(Clients.class, id);
        } catch (Exception e) {
            LOGGER.error("Problem getting client by id: {} -: {}", id, e);
            return null;
        }
    }

    public List<Clients> getClientsByHotel(int hotel_id) {
        Query<Clients> query = null;
        String sql_comand = "select t from " + Clients.getTableName() + " t join t." + Clients.getFields().get(11)
                + " h where h." + Hotel.getFields().get(0) + " = :hotel_id";

        try {
            query = session.createQuery(sql_comand, Clients.class);
            query.setParameter("hotel_id", hotel_id);
            return query.list();
        } catch (Exception e) {
            LOGGER.error("Problem getting clients by hotel id: {} -: {}", hotel_id, e);
            return null;
        }
        
    }

    public List<Clients> getAllClients() {
        Query<Clients> query = null;
        String sql_comand = "from " + Clients.getTableName();

        try {
            query = session.createQuery(sql_comand, Clients.class);
            return query.list();
        } catch (Exception e) {
            LOGGER.error("Problem getting all clients -: {}", e);
            return null;
        }
    }

    public List<Reservation> getAllReservations() {
        Query<Reservation> query = null;
        String sql_comand = "from " + Reservation.getTableName();

        try {
            query = session.createQuery(sql_comand, Reservation.class);
            return query.list();
        } catch (Exception e) {
            LOGGER.error("Problem getting all reservations -: {}", e);
            return null;
        }
        
    }

    public List<String> getDistinctRoomTypes() { // single 7 double 11
        String sql = "select DISTINCT r_type from room"; 
        try {
            Query<?> q = session.createQuery(sql);
            List<?> result = q.list();
            List<String> distinct_roomTypes = new ArrayList<>();
            for (Object object : result) {
                String tmp = (String) object;
                distinct_roomTypes.add(tmp);
            }
            return distinct_roomTypes;
        } catch (Exception e) {
            LOGGER.error("Problem getting distinct room types -: {}", e);
            return null;
        }
    }

    public List<String> getDistinctAdditionalServices() {
        String sql = "select DISTINCT title from add_serv";
        try {
            Query<?> q = session.createQuery(sql);
            List<?> result = q.list();
            List<String> distinct_services = new ArrayList<>();
            for (Object object : result) {
                String tmp = (String) object;
                distinct_services.add(tmp);
            }
            return distinct_services;
        } catch (Exception e) {
            LOGGER.error("Problem getting distinct additional services -: {}", e);
            return null;
        }
    }
}
