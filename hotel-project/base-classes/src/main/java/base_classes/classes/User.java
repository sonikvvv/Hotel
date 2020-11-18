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
    private String name;
    private String phone;
    private String email;

    @Enumerated(EnumType.STRING)
    private URE user_role;

    private int hotel_id;

    public User() {}

    public User(String name, String password, URE role) {
        this.user_name = name;
        this.user_password = password;
        this.user_role = role;
    }
    
    public User(String name, String password, URE role, int hotel) {
        this.user_name = name;
        this.user_password = password;
        this.user_role = role;
        this.hotel_id = hotel;
    }

    public User(String user_name, String user_password, String name, String phone, URE user_role, int hotel_id) {
        this.user_name = user_name;
        this.user_password = user_password;
        this.name = name;
        this.phone = phone;
        this.user_role = user_role;
        this.hotel_id = hotel_id;
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

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getEmail() {
        return email;
    }
    public String getName() {
        return name;
    }
    public String getPhone() {
        return phone;
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

    public int getHotel_id() {
        return hotel_id;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public static List<String> getFields() {
        List<String> ls = new ArrayList<>();
        ls.add("user_id");
        ls.add("user_name");
        ls.add("user_password");
        ls.add("user_role");
        ls.add("hotel_id");
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
                sqlString = sqlString + fields.get(0) + " = :value";
                break;
            case NAME:
                sqlString = sqlString + fields.get(1) + " = :value";
                break;
            case ROLE:
                sqlString = sqlString + fields.get(3) + " = :value";
                break;
            case HOTEL_ID:
                sqlString = sqlString + fields.get(4) + " = :value";
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

    

    // public static void main(String[] args) { // User [ id = 0 username: ves role: ADMIN ]
    //     User ur = new User("ves", "test", URE.ADMIN);
    //     System.out.println(ur);
    // }
    
}
