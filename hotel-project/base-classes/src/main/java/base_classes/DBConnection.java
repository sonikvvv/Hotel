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
        String id = "from app_user where user_id = " + value;
        String user_name = "from app_user where user_name = " + value;

        if (searchType == "id") {
            query = session.createQuery(id, User.class);
        }else if (searchType == "name"){
            query = session.createQuery(user_name, User.class);
        }

        List<User> res = query.list();

        System.out.println(res);
        
        return res.get(0);
    }
}
