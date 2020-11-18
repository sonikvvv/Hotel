package base_classes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import base_classes.classes.ClientUsedServices;
import base_classes.classes.Clients;
import base_classes.classes.Hotel;
import base_classes.classes.Reservation;
import base_classes.classes.Room;
import base_classes.classes.User;
import base_classes.classes.emuns.HE;
import base_classes.classes.emuns.UE;
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

    private LocalDate toDate(String value) {
        String[] tmp = value.split("-");
        return LocalDate.of(Integer.valueOf(tmp[0]), Integer.valueOf(tmp[1]), Integer.valueOf(tmp[2]) + 1);
    }

    private LocalDateTime toDateAndTime(String value) {
        String[] tmp = value.split("-");
        return LocalDateTime.of(Integer.valueOf(tmp[0]), Integer.valueOf(tmp[1]), Integer.valueOf(tmp[2]) + 1, 0, 0, 0);
    }

    public List<User> getUserList(UE type, String value) {
        Query<User> query = null;
        String sql_comand = User.search(type);

        query = session.createQuery(sql_comand, User.class);
        query.setParameter("value", value);
        List<User> res = query.list(); 

        if (res.isEmpty()) {
            return null;
        } else {
            return res;
        }
    }

    public List<Hotel> getHotelInfoList(HE type, String value) {
        Query<Hotel> query = null;
        String sql_comand = "";

        switch (type) {
            case ID:
                sql_comand = Hotel.search(type) + value;
                break;
            case NAME:
                sql_comand = Hotel.search(type) + value + "'";
                break;
            case ALL:
                sql_comand = "select t from " + User.getTableName() + " t";
                break;
            default:
                break;
        }

        query = session.createQuery(sql_comand, Hotel.class);
        List<Hotel> res = query.list();

        if (res.isEmpty()) {
            return null;
        } else {
            return res;
        }
    }

    public List<Clients> getClientByDateList(List<String> value) {
        Query<Clients> query = null;
        String sql_comand = "from clients t where t.hotel_id = :id and t.check_in between :start and :end";
        int id = Integer.valueOf(value.get(2));

        query = session.createQuery(sql_comand, Clients.class);
        query.setParameter("id", id);
        query.setParameter("start", toDateAndTime(value.get(0)));
        query.setParameter("end", toDateAndTime(value.get(1)));

        List<Clients> res = query.list();

        if (res.isEmpty()) {
            return null;
        } else {
            return res;
        }
    }

    public List<Clients> getClientRaitingByDate(List<String> value) {
        Query<Clients> query = null;
        String sql_comand = "from clients t where t.hotel_id = :id and t.rait.date_made between :start and :end";
        int id = Integer.valueOf(value.get(2));

        query = session.createQuery(sql_comand, Clients.class);
        query.setParameter("id", id);
        query.setParameter("start", toDateAndTime(value.get(0)));
        query.setParameter("end", toDateAndTime(value.get(1)));

        List<Clients> res = query.list();

        if (res.isEmpty()) {
            return null;
        } else {
            return res;
        }
    }

    public List<ClientUsedServices> getClientServicesByDate(List<String> value) {
        Query<ClientUsedServices> query = null;
        String sql_comand = "select u from clients t join t.cuds u where t.hotel_id = :id and u.purchase_date between :start and :end";
        int id = Integer.valueOf(value.get(2));

        query = session.createQuery(sql_comand, ClientUsedServices.class);
        query.setParameter("id", id);
        query.setParameter("start", toDateAndTime(value.get(0)));
        query.setParameter("end", toDateAndTime(value.get(1)));

        List<ClientUsedServices> res = query.list();

        if (res.isEmpty()) {
            return null;
        } else {
            return res;
        }
    }

    public List<Reservation> getReservationsByDate(List<String> value) {
        Query<Reservation> query = null;
        String sql_comand = "from " + Reservation.getTableName()
                + " t where t.hotel_id = :id and t.date_made between :start and :end";
        int id = Integer.valueOf(value.get(2));

        query = session.createQuery(sql_comand, Reservation.class);
        query.setParameter("id", id);
        query.setParameter("start", toDate(value.get(0)));
        query.setParameter("end", toDate(value.get(1)));

        List<Reservation> res = query.list();

        if (res.isEmpty()) {
            return null;
        } else {
            return res;
        }
    }

    public List<Reservation> getReceptionistMadeReservations(List<String> value) {
        Query<Reservation> query = null;
        String sql_comand = "select t from " + Reservation.getTableName()
                + " t join t." + Reservation.getFields().get(3) + " u where u.user_id = :id and t.date_made between :start and :end";
        int id = Integer.valueOf(value.get(2));

        query = session.createQuery(sql_comand, Reservation.class);
        query.setParameter("id", id);
        query.setParameter("start", toDate(value.get(0)));
        query.setParameter("end", toDate(value.get(1)));

        List<Reservation> res = query.list();

        if (res.isEmpty()) {
            return null;
        } else {
            return res;
        }
    }

    public List<Room> getRoomsRaitByDate(List<String> value) {
        Query<Room> query = null;
        String sql_comand = "from " + Room.getTableName()
                + " t where t.hotel_id = :id and t.rait.date_made between :start and :end";
        int id = Integer.valueOf(value.get(2));

        query = session.createQuery(sql_comand, Room.class);
        query.setParameter("id", id);
        query.setParameter("start", toDateAndTime(value.get(0)));
        query.setParameter("end", toDateAndTime(value.get(1)));

        List<Room> res = query.list();

        if (res.isEmpty()) {
            return null;
        } else {
            return res;
        }
    }




}
