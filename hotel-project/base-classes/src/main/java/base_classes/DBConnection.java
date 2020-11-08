package base_classes;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import base_classes.classes.*;
import base_classes.classes.emuns.UE;
import base_classes.util.HibernateUtil;


public class DBConnection {
    private Session session = HibernateUtil.getSessionFactory().openSession();

    private void commit() {
        session.getTransaction().commit();
    }
    
    public void saveObject(Object object) {
        session.beginTransaction();
        session.save(object);
        commit();
    }

    public Boolean login(String name, String pas){

        
        return false;
    }


    public User getUser(UE type, String value) {
        session.getTransaction();
        Query<User> query = null;

        query = session.createQuery(User.search(type) + value, User.class);

        List<User> res = query.list(); 

        System.out.println(res);
        
        return res.get(0);
    }

    

}
