package base_classes.classes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import base_classes.classes.emuns.UE;


@Entity(name = "app_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int user_id;
    private String user_name;
    private String user_password;

    @OneToOne(cascade = CascadeType.ALL)
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

    public static List<String> getFields() {
        List<String> ls = new ArrayList<>();
        ls.add("user_id");
        ls.add("user_name");
        ls.add("user_password");
        return ls;
    }

    public static String getTableName() {
        return "app_user";
    }

    public String search(UE type) {
        String sqlString = "from " + getTableName() + " where ";
        List<String> fields = getFields();

        switch (type) {
            case ID:
                sqlString = sqlString + fields.get(0) + " = ";
                break;
            case NAME:
                sqlString = sqlString + fields.get(1) + " = ";
                break;
            default:
                break;
        }

        return sqlString;
    }

    @Override
    public String toString() {
        return "User [ id = " + this.user_id + " username: " + this.user_name + " role: " + this.user_role.getRole_title() + " ]";
    }
    
}
