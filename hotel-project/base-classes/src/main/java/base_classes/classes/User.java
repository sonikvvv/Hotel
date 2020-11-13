package base_classes.classes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import base_classes.classes.emuns.UE;
import base_classes.classes.emuns.URE;


@Entity(name = "app_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
    @SequenceGenerator(name = "user_generator", sequenceName = "user_seq", allocationSize = 50)
    private int user_id;
    private String user_name;
    private String user_password;

    @Enumerated(EnumType.STRING)
    private URE user_role;

    public User() {}

    public User(String name, String password, URE role) {
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

    public void setUser_role(URE user_role) {
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

    public URE getUser_role() {
        return user_role;
    }

    public static List<String> getFields() {
        List<String> ls = new ArrayList<>();
        ls.add("user_id");
        ls.add("user_name");
        ls.add("user_password");
        ls.add("user_role");
        return ls;
    }

    public static String getTableName() {
        return "app_user";
    }

    public static String search(UE type) {
        String sqlString = "from " + getTableName() + " t where t.";
        List<String> fields = getFields();

        switch (type) {
            case ID:
                sqlString = sqlString + fields.get(0) + " = ";
                break;
            case NAME:
                sqlString = sqlString + fields.get(1) + " = '";
                break;
            case ROLE:
                sqlString = sqlString + fields.get(3) + " = '";
                break;
            case ALL:
                sqlString = "from " + getTableName();
                break;
            default:
                break;
        }

        return sqlString;
    }

    @Override
    public String toString() {
        return "User [ id = " + this.user_id + " username: " + this.user_name + " role: " + this.user_role + " ]";
    }

    public static void main(String[] args) { // User [ id = 0 username: ves role: ADMIN ]
        User ur = new User("ves", "test", URE.ADMIN);
        System.out.println(ur);
    }
    
}
