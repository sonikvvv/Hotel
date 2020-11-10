package base_classes.classes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import base_classes.classes.emuns.HE;
import base_classes.classes.emuns.UE;

@Entity(name = "hotel")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hotel_generator")
    @SequenceGenerator(name = "hotel_generator", sequenceName = "hotel_seq", allocationSize = 50)
    private int hotel_id;
    private String hotel_name;
    @OneToMany(cascade = CascadeType.ALL)
    private List<User> owners;
    @OneToMany(cascade = CascadeType.ALL)
    private List<User> managers;
    @OneToMany(cascade = CascadeType.ALL)
    private List<User> receptionists;

    public Hotel(int hotel_id, String hotel_name, List<User> owners, List<User> managers, List<User> receptionists) {
        this.hotel_id = hotel_id;
        this.setHotel_name(hotel_name);
        this.setOwners(owners);
        this.setManagers(managers);
        this.setReceptionists(receptionists);
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public List<User> getReceptionists() {
        return receptionists;
    }

    public void setReceptionists(List<User> receptionists) {
        this.receptionists = receptionists;
    }

    public List<User> getManagers() {
        return managers;
    }

    public void setManagers(List<User> managers) {
        this.managers = managers;
    }

    public List<User> getOwners() {
        return owners;
    }

    public void setOwners(List<User> owners) {
        this.owners = owners;
    }

    public String getHotel_name() {
        return hotel_name;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public void addTOWner(User owner) {
        this.owners.add(owner);
    }

    public void addToManager(User manager) {
        this.owners.add(manager);
    }

    public void addToRecept(User recept) {
        this.owners.add(recept);
    }

    public static List<String> getFields() {
        List<String> ls = new ArrayList<>();
        ls.add("hotel_id");
        ls.add("hotel_name");
        ls.add("owners");
        ls.add("managers");
        ls.add("receptionists");
        return ls;
    }

    public static String getTableName() {
        return "hotel";
    }

    public static String search(HE type) {
        String sqlString = "from " + getTableName() + " where ";
        List<String> fields = getFields();

        switch (type) {
            case ID:
                sqlString = sqlString + fields.get(0) + " = ";
                break;
            case NAME:
                sqlString = sqlString + fields.get(1) + " = ";
                break;
            case OWNER:
                sqlString = sqlString + fields.get(2) + " = " + User.search(UE.NAME);
                break;
            case MANAGER:
                sqlString = sqlString + fields.get(3) + " = " + User.search(UE.NAME);
                break;
            case RECEPTIONIST:
                sqlString = sqlString + fields.get(4) + " = " + User.search(UE.NAME);
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
        return "Hotel [ id = " + this.hotel_id + " owners = " + this.owners + " managers = " + this.managers
            + " receptionists = " + this.receptionists + " ]";
    }
}
