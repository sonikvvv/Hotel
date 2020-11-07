package base_classes.classes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import base_classes.classes.emuns.URE;

@Entity(name = "user_roles")
public class UserRoles {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
    @SequenceGenerator(name = "user_generator", sequenceName = "user_seq", allocationSize = 50)
    private int role_id;
    private String role_title;

    public UserRoles() {}

    public UserRoles(String title){
        this.role_title = title;
    }

    public int getRole_id() {
        return role_id;
    }
    
    public String getRole_title() {
        return role_title;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public void setRole_title(String role_title) {
        this.role_title = role_title;
    }

    public static List<String> getFields() {
        List<String> ls = new ArrayList<>();
        ls.add("role_id");
        ls.add("role_title");
        return ls;
    }

    public static String getTableName() {
        return "user_roles";
    }

    public String search(URE type) {
        String sqlString = "from " + getTableName() + " where ";
        List<String> fields = getFields();

        switch (type) {
            case ID:
                sqlString = sqlString + fields.get(0) + " = ";
                break;
            case ROLE:
                sqlString = sqlString + fields.get(1) + " = ";
                break;
            default:
                break;
        }

        return sqlString;
    }

    @Override
    public String toString() {
        return "Role [ id = " + this.role_id + " role: " + this.role_title + " ]";
    }
}
