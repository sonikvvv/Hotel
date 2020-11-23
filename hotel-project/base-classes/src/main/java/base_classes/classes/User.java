package base_classes.classes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

import base_classes.classes.emuns.URE;


@Entity(name = "app_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
    @SequenceGenerator(name = "user_generator", sequenceName = "user_seq", allocationSize = 1)
    private int user_id;
    private String user_name;
    private String user_password;
    private String name;
    private String phone;
    private String email;

    @Enumerated(EnumType.STRING)
    private URE user_role;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Hotel> hotel = new ArrayList<>();

    public User() {}

    public User(String name, String password, URE role) {
        this.user_name = name;
        this.user_password = password;
        this.user_role = role;
    }
    
    public User(String user_name, String user_password, String name, String phone, URE user_role) {
        this.user_name = user_name;
        this.user_password = user_password;
        this.name = name;
        this.phone = phone;
        this.user_role = user_role;
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

    public void setHotel(List<Hotel> hotel) {
        this.hotel = hotel;
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

    public List<Hotel> getHotel() {
        return hotel;
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

    public void addToHotel(Hotel h) {
        this.hotel.add(h);
    }

    public static List<String> getFields() {
        List<String> ls = new ArrayList<>();
        ls.add("user_id");
        ls.add("user_name");
        ls.add("user_password");
        ls.add("user_role");
        ls.add("hotel");
        return ls;
    }

    public static String getTableName() {
        return "app_user";
    }

    @Override
    public String toString() {
        return "User [ id = " + this.user_id + " username: " + this.user_name + " role: " + this.user_role + " ]";
    }    
}
