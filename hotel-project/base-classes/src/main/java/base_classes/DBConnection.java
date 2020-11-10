package base_classes;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import base_classes.classes.*;
import base_classes.classes.emuns.UE;
import base_classes.util.HibernateUtil;


public class DBConnection {
    private Session session;

    public DBConnection(){
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
    }

    public void saveObject(Object object) {
        try {
            //session.beginTransaction();
            session.save(object);
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public User getUser(UE type, String value) {
        //session.beginTransaction();
        Query<User> query = null;
        if (type == UE.NAME || type == UE.ROLE)
            query = session.createQuery(User.search(type) + "'" + value + "'", User.class);
        else 
            query = session.createQuery(User.search(type), User.class);

        List<User> res = query.list(); 

        if (res.isEmpty()) {
            return null;
        }else {
            return res.get(0);
        }
    }


}
