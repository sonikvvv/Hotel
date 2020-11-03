package hotel.base_classes;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;



@Entity
public class User {
    @Id
    private int user_id;
    private String user_name;
    private String user_password;

    @OneToMany // TODO: make the mapping
    private UserRoles user_role;

    public User() {}

    public User(String name, String password, UserRoles role) {
        this.user_name = name;
        this.user_password = password;
        this.user_role = role;
    }
    
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public void setUser_role(UserRoles user_role) {
        this.user_role = user_role;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public UserRoles getUser_role() {
        return user_role;
    }

    @Override
    public String toString() {
        return "User [ id = " + this.user_id + " username: " + this.user_name + " role: " + this.user_role.getRole_title() + " ]";
    }
    
}
