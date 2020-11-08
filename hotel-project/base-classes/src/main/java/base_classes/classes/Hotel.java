package base_classes.classes;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ads_generator")
    @SequenceGenerator(name = "ads_generator", sequenceName = "ads_seq", allocationSize = 50)
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

    
}
