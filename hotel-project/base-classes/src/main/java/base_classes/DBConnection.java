package base_classes;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import base_classes.classes.*;
import base_classes.util.HibernateUtil;


public class DBConnection {
    private Session session = HibernateUtil.getSessionFactory().openSession();

    public void commit() {
        session.getTransaction().commit();
    }
    
    public void saveObject(Object object) {
        session.beginTransaction();
        session.save(object);
        commit();
    }


    public User getUser(String searchType, String value) {
        session.beginTransaction();
        Query<User> query = null;

        query = session.createQuery("queryString", User.class);

        List<User> res = query.list();

        System.out.println(res);
        
        return res.get(0);
    }

    public UserRoles getUserRoles(String searchType, String value) {
        session.beginTransaction();
        Query<UserRoles> query = null;
        String id = "from app_user where user_id = " + value;
        String user_name = "from app_user where role_title = " + value;

        if (searchType == "id") {
            query = session.createQuery(id, UserRoles.class);
        } else if (searchType == "name") {
            query = session.createQuery(user_name, UserRoles.class);
        }

        List<UserRoles> res = query.list();

        System.out.println(res);

        return res.get(0);
    }
    
    public RoomType getRoomType(String searchType, String value) {
        session.beginTransaction();
        Query<RoomType> query = null;
        String id = "from app_user where user_id = " + value;
        String user_name = "from app_user where user_name = " + value;

        if (searchType == "id") {
            query = session.createQuery(id, RoomType.class);
        } else if (searchType == "name") {
            query = session.createQuery(user_name, RoomType.class);
        }

        List<RoomType> res = query.list();

        System.out.println(res);

        return res.get(0);
    }

    public RoomStatus getRoomStatus(String searchType, String value) {
        session.beginTransaction();
        Query<RoomStatus> query = null;
        String id = "from app_user where user_id = " + value;
        String user_name = "from app_user where user_name = " + value;

        if (searchType == "id") {
            query = session.createQuery(id, RoomStatus.class);
        } else if (searchType == "name") {
            query = session.createQuery(user_name, RoomStatus.class);
        }

        List<RoomStatus> res = query.list();

        System.out.println(res);

        return res.get(0);
    }

    public Room getRoom(String searchType, String value) {
        session.beginTransaction();
        Query<Room> query = null;
        String id = "from app_user where user_id = " + value;
        String user_name = "from app_user where user_name = " + value;

        if (searchType == "id") {
            query = session.createQuery(id, Room.class);
        } else if (searchType == "name") {
            query = session.createQuery(user_name, Room.class);
        }

        List<Room> res = query.list();

        System.out.println(res);

        return res.get(0);
    }

    public ReservationType getReservationType(String searchType, String value) {
        session.beginTransaction();
        Query<ReservationType> query = null;
        String id = "from app_user where user_id = " + value;
        String user_name = "from app_user where user_name = " + value;

        if (searchType == "id") {
            query = session.createQuery(id, ReservationType.class);
        } else if (searchType == "name") {
            query = session.createQuery(user_name, ReservationType.class);
        }

        List<ReservationType> res = query.list();

        System.out.println(res);

        return res.get(0);
    }

    public ReservationStatus getReservationStatus(String searchType, String value) {
        session.beginTransaction();
        Query<ReservationStatus> query = null;
        String id = "from app_user where user_id = " + value;
        String user_name = "from app_user where user_name = " + value;

        if (searchType == "id") {
            query = session.createQuery(id, ReservationStatus.class);
        } else if (searchType == "name") {
            query = session.createQuery(user_name, ReservationStatus.class);
        }

        List<ReservationStatus> res = query.list();

        System.out.println(res);

        return res.get(0);
    }

    public ReservationForm getReservationForm(String searchType, String value) {
        session.beginTransaction();
        Query<ReservationForm> query = null;
        String id = "from app_user where user_id = " + value;
        String user_name = "from app_user where user_name = " + value;

        if (searchType == "id") {
            query = session.createQuery(id, ReservationForm.class);
        } else if (searchType == "name") {
            query = session.createQuery(user_name, ReservationForm.class);
        }

        List<ReservationForm> res = query.list();

        System.out.println(res);

        return res.get(0);
    }

    public ReservationCancelType getReservationCancelType(String searchType, String value) {
        session.beginTransaction();
        Query<ReservationCancelType> query = null;
        String id = "from app_user where user_id = " + value;
        String user_name = "from app_user where user_name = " + value;

        if (searchType == "id") {
            query = session.createQuery(id, ReservationCancelType.class);
        } else if (searchType == "name") {
            query = session.createQuery(user_name, ReservationCancelType.class);
        }

        List<ReservationCancelType> res = query.list();

        System.out.println(res);

        return res.get(0);
    }

        public Reservation getReservation(String searchType, String value) {
        session.beginTransaction();
        Query<Reservation> query = null;
        String id = "from app_user where user_id = " + value;
        String user_name = "from app_user where user_name = " + value;

        if (searchType == "id") {
            query = session.createQuery(id, Reservation.class);
        }else if (searchType == "name"){
            query = session.createQuery(user_name, Reservation.class);
        }

        List<Reservation> res = query.list();

        System.out.println(res);
        
        return res.get(0);
    }
}
