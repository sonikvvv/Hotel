package base_classes;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;

import base_classes.classes.AdditServices;
import base_classes.classes.ClientUsedServices;
import base_classes.classes.Clients;
import base_classes.classes.Country;
import base_classes.classes.Hotel;
import base_classes.classes.Raiting;
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
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            LOGGER.debug("Session opened succesfuly.");
        } catch (Exception e) {
            LOGGER.error("Exeption while opening session -> {}.", e);
        }
        LOGGER.info("Inititalized DB connection");
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public void saveObject(Object object) {
        try {
            LOGGER.debug("Starting save.");
            if (session.getTransaction().isActive()) {
                session.getTransaction();
            } else
                session.beginTransaction();
            session.save(object.getClass().toString(), object);
            LOGGER.debug("Saved object: {}", object);
        } catch (Exception e) {
            LOGGER.error("Fail to begin transaction or save failure {}: {}", object, e);
            session.getTransaction().rollback();
        }finally {
            session.getTransaction().commit();
            LOGGER.debug("Save -> end with commit.");
        }
    }

    public void updateObject(Object object) {
        try {
            LOGGER.debug("Starting update.");
            if (session.getTransaction().isActive()){
                session.getTransaction();
            } else
                session.beginTransaction();
            session.update(object.getClass().toString(), object);
            LOGGER.debug("Updated object: {}", object);
        } catch (Exception e) {
            LOGGER.error("Fail to begin transaction or update failure {}: {}", object, e);
            session.getTransaction().rollback();
        }finally {
            session.getTransaction().commit();
            LOGGER.debug("Update -> end with commit.");
        }
    }

    public void deleteObject(Object object) {
        try {
            LOGGER.debug("Starting delete.");
            if (session.getTransaction().isActive()) {
                session.getTransaction();
            } else
                session.beginTransaction();
            session.delete(object.getClass().toString(), object);
            LOGGER.debug("Deleted object: {}", object);
        } catch (Exception e) {
            LOGGER.error("Fail to begin transaction or deleting {}: {}", object, e);
            session.getTransaction().rollback();
        } finally {
            session.getTransaction().commit();
            LOGGER.debug("Delete -> end with commit.");
        }
    }

    public User getUserByUsername(String data) {
        LOGGER.debug("Starting getUserByUsername -> {}", data);
        Query<User> query = null;
        String sql = "from " + User.getTableName() + "  where " + User.getFields().get(1) + " = :name";

        LOGGER.debug("SQL -> {}", sql);
        try {
            query = session.createQuery(sql, User.class);
            query.setParameter("name", data);
            User result = query.uniqueResult();
            return result;
        } catch (Exception e) {
            LOGGER.error("No unique user with this name: {} -> {}", data, e);
            return null;
        }
    }

    public User getUserByID(int id) {
        try {
            LOGGER.debug("Starting get user by id -> {}", id);
            return session.get(User.class, id);
        } catch (Exception e) {
            LOGGER.error("User with id not found {" + id + "} -> {} ", e);
            return null;
        }
    }

    public List<User> getAllUsers(){
        LOGGER.debug("getUserByUsername");
        Query<User> query = null;
        String sql_comand = "from " + User.getTableName();

        try {
            LOGGER.debug("SQL -> {}", sql_comand);
            query = session.createQuery(sql_comand, User.class);
            return query.list();
        } catch (Exception e) {
            LOGGER.error("Problem with getting all users. {}", e);
            return null;
        }
    }

    public List<User> getUserByRole(URE type) {
        LOGGER.debug("Starting get user by role -> {}", type);
        Query<User> query = null;
        String sql_comand = "from " + User.getTableName() + " where " + User.getFields().get(3) + " = :role";

        try {
            LOGGER.debug("SQL -> {}");
            query = session.createQuery(sql_comand, User.class);
            query.setParameter("role", type);
            return query.list();
        } catch (Exception e) {
            LOGGER.error("Problem with getting users by role: {} -> {}", type, e);
            return null;
        }
    }

    public List<User> getUserByHotel(int hotel_id) {
        LOGGER.debug("Starting get user by hotel id -> {}", hotel_id);
        Query<User> query = null;
        String sql_comand = "select t from " + User.getTableName() + " t join t." + User.getFields().get(4) +
                " h where h." + Hotel.getFields().get(0) + " = :hotel_id";

        try {
            LOGGER.debug("SQL -> {}", sql_comand);
            query = session.createQuery(sql_comand, User.class);
            query.setParameter("hotel_id", hotel_id);
            return query.list();
        } catch (Exception e) {
            LOGGER.error("Problem with getting users by hotel id: {} -> {}", hotel_id, e);
            return null;
        }
    }

    public List<AdditServices> getAdditServicesByHotel(int hotel_id) {
        LOGGER.debug("Starting get additional services by hotel id -> {}", hotel_id);
        Query<AdditServices> query = null;
        String sql_comand = "select t from " + AdditServices.getTableName() + " t join t." + AdditServices.getFields().get(4)
                + " h where h." + Hotel.getFields().get(0) + " = :hotel_id";

        try {
            LOGGER.debug("SQL -> {}", sql_comand);
            query = session.createQuery(sql_comand, AdditServices.class);
            query.setParameter("hotel_id", hotel_id);
            return query.list();
        } catch (Exception e) {
            LOGGER.error("Problem with getting additional services by hotel id: {} -> {}", hotel_id, e);
            return null;
        }
    }

    public List<AdditServices> getAllAdditServices() {
        LOGGER.debug("Starting get all additional services.");
        Query<AdditServices> query = null;
        String sql_comand = "from " + AdditServices.getTableName();

        try {
            LOGGER.debug("SQL -> {}", sql_comand);
            query = session.createQuery(sql_comand, AdditServices.class);
            return query.list();
        } catch (Exception e) {
            LOGGER.error("Problem with getting all additional services. -> {}", e);
            return null;
        }

    }

    public List<ServiceCategory> getAllServiceCategories() {
        LOGGER.debug("Starting get all service categories.");
        Query<ServiceCategory> query = null;
        String sql_comand = "from " + ServiceCategory.getTableName() ;

        try {
            LOGGER.debug("SQL -> {}", sql_comand);
            query = session.createQuery(sql_comand, ServiceCategory.class);
            return query.list();
        } catch (Exception e) {
            LOGGER.error("Problem with getting all service categories -> {}", e);
            return null;
        }
    }

    public Hotel getHotelById(int hotel_id) {
        try {
            LOGGER.debug("Starting get hotel by id -> {}", hotel_id);
            return session.get(Hotel.class, hotel_id);
        } catch (Exception e) {
            LOGGER.error("Problem with getting hotel by id: {} -> {}", hotel_id, e);
            return null;
        }
    }

    public Hotel getHotelByName(String name) {
        LOGGER.debug("Starting get hotel by name -> {}", name);
        Query<Hotel> query = null;
        String sql_comand = "from " + Hotel.getTableName() + " where " + Hotel.getFields().get(1) + " = :name";

        try {
            LOGGER.debug("SQL -> {}", sql_comand);
            query = session.createQuery(sql_comand, Hotel.class);
            query.setParameter("name", name);
            return query.uniqueResult();
        } catch (Exception e) {
            LOGGER.error("Problem with getting hotel by name: {} -> {}", name, e);
            return null;
        }
    }

    public List<Hotel> getAllHotels() {
        LOGGER.debug("Starting get all hotels.");
        Query<Hotel> query = null;
        String sql_comand = "from " + Hotel.getTableName();

        try {
            LOGGER.debug("SQL -> {}", sql_comand);
            query = session.createQuery(sql_comand, Hotel.class);
            return query.list();
        } catch (Exception e) {
            LOGGER.error("Problem with getting all hotels -> {}", e);
            return null;
        }
    }

    public List<Reservation> getAllReservationsByHotel(int hotel_id) {
        LOGGER.debug("Starting get all reservations by hotel id -> {}", hotel_id);
        Query<Reservation> query = null;
        String sql_comand = "select t from " + Reservation.getTableName() + " t join t."
                + Reservation.getFields().get(4) + " h where h." + Hotel.getFields().get(0) + " = :hotel_id";

        try {
            LOGGER.debug("SQL -> {}", sql_comand);
            query = session.createQuery(sql_comand, Reservation.class);
            query.setParameter("hotel_id", hotel_id);
            return query.list();
        } catch (Exception e) {
            LOGGER.error("Problem getting reservations by hotel id: {} -> {}", hotel_id, e);
            return null;
        }
    }

    public Room getRoomByID(int id) {
        try {
            LOGGER.debug("Starting get room by id -> {}", id);
            return session.get(Room.class, id);
        } catch (Exception e) {
            LOGGER.error("Problem getting room by id: {} -> {}", id, e);
            return null;
        }
    }

    public List<Room> getRoomsByHotel(int hotel_id) {
        LOGGER.debug("Starting get rooms by hotel id -> {}", hotel_id);
        Query<Room> query = null;
        String sql_comand = "select t from " + Room.getTableName() + " t join t."
                + Room.getFields().get(4) + " h where h." + Hotel.getFields().get(0) + " = :hotel_id";

        try {
            LOGGER.debug("SQL -> {}", sql_comand);
            query = session.createQuery(sql_comand, Room.class);
            query.setParameter("hotel_id", hotel_id);
            return query.list();
        } catch (Exception e) {
            LOGGER.error("Problem getting rooms by hotel id: {} -> {}", hotel_id, e);
            return null;
        }

    }

    public List<Room> getAllRooms() {
        LOGGER.debug("Starting get all rooms.");
        Query<Room> query = null;
        String sql_comand = "from " + Room.getTableName();

        try {
            LOGGER.debug("SQL -> {}", sql_comand);
            query = session.createQuery(sql_comand, Room.class);
            return query.list();
        } catch (Exception e) {
            LOGGER.error("Problem getting all rooms -> {}", e);
            return null;
        }
    }

    public Clients getClientByID(int id) {
        try {
            LOGGER.debug("Starting get client by id -> {}", id);
            return session.get(Clients.class, id);
        } catch (Exception e) {
            LOGGER.error("Problem getting client by id: {} -> {}", id, e);
            return null;
        }
    }

    public List<Clients> getClientsByHotel(int hotel_id) {
        LOGGER.debug("Starting gey clients by hotel id -> {}", hotel_id);
        Query<Clients> query = null;
        String sql_comand = "select t from " + Clients.getTableName() + " t join t." + Clients.getFields().get(11)
                + " h where h." + Hotel.getFields().get(0) + " = :hotel_id";

        try {
            LOGGER.debug("SQL -> {}", sql_comand);
            query = session.createQuery(sql_comand, Clients.class);
            query.setParameter("hotel_id", hotel_id);
            return query.list();
        } catch (Exception e) {
            LOGGER.error("Problem getting clients by hotel id: {} -> {}", hotel_id, e);
            return null;
        }
        
    }

    public List<Clients> getAllClients() {
        LOGGER.debug("Starting get all clients.");
        Query<Clients> query = null;
        String sql_comand = "from " + Clients.getTableName();

        try {
            LOGGER.debug("SQL -> {}", sql_comand);
            query = session.createQuery(sql_comand, Clients.class);
            return query.list();
        } catch (Exception e) {
            LOGGER.error("Problem getting all clients -> {}", e);
            return null;
        }
    }

    public List<Reservation> getAllReservations() {
        LOGGER.debug("Starting get all reservations.");
        Query<Reservation> query = null;
        String sql_comand = "from " + Reservation.getTableName();

        try {
            LOGGER.debug("SQL -> {}", sql_comand);
            query = session.createQuery(sql_comand, Reservation.class);
            return query.list();
        } catch (Exception e) {
            LOGGER.error("Problem getting all reservations -> {}", e);
            return null;
        }
        
    }

    // select DISTINCT r_type, count(*) from room group by r_type;
    public List<String> getDistinctRoomTypes() {
        LOGGER.debug("Starting get distinct room types.");
        String sql = "select DISTINCT r_type from room"; 
        try {
            LOGGER.debug("SQL -> {}", sql);
            Query<?> q = session.createQuery(sql);
            List<?> result = q.list();
            List<String> distinct_roomTypes = new ArrayList<>();
            for (Object object : result) {
                String tmp = (String) object;
                distinct_roomTypes.add(tmp);
            }
            LOGGER.debug("Result -> {}", distinct_roomTypes);
            return distinct_roomTypes;
        } catch (Exception e) {
            LOGGER.error("Problem getting distinct room types -> {}", e);
            return null;
        }
    }

    public List<String> getDistinctAdditionalServices() {
        LOGGER.debug("Starting get distinct additional services.");
        String sql = "select DISTINCT title from add_serv";
        try {
            LOGGER.debug("SQL -> {}", sql);
            Query<?> q = session.createQuery(sql);
            List<?> result = q.list();
            List<String> distinct_services = new ArrayList<>();
            for (Object object : result) {
                String tmp = (String) object;
                distinct_services.add(tmp);
            }
            LOGGER.debug("Result -> {}", distinct_services);
            return distinct_services;
        } catch (Exception e) {
            LOGGER.error("Problem getting distinct additional services -> {}", e);
            return null;
        }
    }

    public ServiceCategory getServiceCategoryByID(int id) {
        try {
            LOGGER.debug("Starting get service categories by id -> {}", id);
            return session.get(ServiceCategory.class, id);
        } catch (Exception e) {
            LOGGER.error("Problem getting service category by id: {} -> {}", id, e);
            return null;
        }
    }

    public AdditServices getAdditServicesByID(int id) {
        try {
            LOGGER.debug("Starting get additional services by id -> {}", id);
            return session.get(AdditServices.class, id);
        } catch (Exception e) {
            LOGGER.error("Problem getting additional services by id: {} -> {}", id, e);
            return null;
        }
    }

    public ClientUsedServices getClientUsedServicesByID(int id) {
        try {
            LOGGER.debug("Starting get client used services by id -> {}", id);
            return session.get(ClientUsedServices.class, id);
        } catch (Exception e) {
            LOGGER.error("Problem getting client used services by id: {} -> {}", id, e);
            return null;
        }
    }

    public Country getCountryByID(int id) {
        try {
            LOGGER.debug("Starting get country by id -> {}", id);
            return session.get(Country.class, id);
        } catch (Exception e) {
            LOGGER.error("Problem getting country by id: {} -> {}", id, e);
            return null;
        }
    }

    public Reservation getReservationByID(int id) {
        try {
            LOGGER.debug("Starting get reservation by id -> {}", id);
            return session.get(Reservation.class, id);
        } catch (Exception e) {
            LOGGER.error("Problem getting reservation by id: {} -> {}", id, e);
            return null;
        }
    }

    public void truncateAllTables() {
        LOGGER.debug("Starting truncate all tables.");
        List<String> table_names = new ArrayList<>();
        table_names.add(Clients.getTableName());
        table_names.add(ClientUsedServices.getTableName());
        table_names.add(AdditServices.getTableName());
        table_names.add(Country.getTableName());
        table_names.add(Reservation.getTableName());
        table_names.add(Room.getTableName());
        table_names.add(Raiting.getTableName());
        table_names.add(ServiceCategory.getTableName());
        table_names.add(User.getTableName());
        table_names.add(Hotel.getTableName());

        Query<?> q = null;
        for (String string : table_names) {
            String sql = " delete from ";
            sql += string;
            try {
                LOGGER.debug("Sql command -> {}", sql);
                session.beginTransaction();
                q = session.createQuery(sql);
                q.executeUpdate();
                session.getTransaction().commit();
                LOGGER.debug("Succesfuly truncated table -> {}", string);
            } catch (Exception e) {
                LOGGER.error("Error while truncating tables -> {}", e);
            }
        }
    }
}
