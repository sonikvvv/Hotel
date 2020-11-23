package base_classes.classes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.Type;

import base_classes.classes.emuns.ServiceType;

@Entity(name = "clients")
public class Clients {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_generator")
    @SequenceGenerator(name = "client_generator", sequenceName = "client_seq", allocationSize = 1)
    private int c_id;
    private String c_name;
    private LocalDate c_bd;

    @Type(type = "true_false")
    private boolean c_sex; // true -> male, false -> female
    private String c_passport_number;
    private LocalDate c_passport_date;
    private String c_car_number;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "country_id")
    private Country country;
    private String c_note;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(unique = false)
    private List<ClientUsedServices> cuds = new ArrayList<>();

    private LocalDateTime check_in;
    private LocalDateTime check_out;

    @Column(columnDefinition = "Number(10,2)")
    private double total = 0;
    private String vaucher;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
    

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(unique = false)
    private List<Raiting> rait = new ArrayList<>();


    public Clients() {}

    public Clients(String name, LocalDate birth_date, boolean sex, String passport_number, LocalDate passport_date,
            String car_number, Country country, String client_note) {

        this.c_name = name;
        this.c_bd = birth_date;
        this.c_sex = sex;
        this.c_passport_number = passport_number;
        this.c_passport_date = passport_date;
        this.c_car_number = car_number;
        this.country = country;
        this.c_note = client_note;
        this.check_in = LocalDateTime.now();
        this.rait.add(new Raiting());
    }

    public Clients(String name, LocalDate birth_date, boolean sex, String passport_number, LocalDate passport_date,
            String car_number, Country country, String client_note, String vaucher) {

        this.c_name = name;
        this.c_bd = birth_date;
        this.c_sex = sex;
        this.c_passport_number = passport_number;
        this.c_passport_date = passport_date;
        this.c_car_number = car_number;
        this.country = country;
        this.c_note = client_note;
        this.check_in = LocalDateTime.now();
        this.vaucher = vaucher;
        this.rait.add(new Raiting());
    }

    public Clients(String name, LocalDate birth_date, boolean sex, String passport_number, LocalDate passport_date,
            String car_number, Country country, String client_note, String vaucher, Hotel hotel) {

        this.c_name = name;
        this.c_bd = birth_date;
        this.c_sex = sex;
        this.c_passport_number = passport_number;
        this.c_passport_date = passport_date;
        this.c_car_number = car_number;
        this.country = country;
        this.c_note = client_note;
        this.check_in = LocalDateTime.now();
        this.vaucher = vaucher;
        this.hotel = hotel;
    }

    public LocalDate getC_bd() {
        return c_bd;
    }
    public String getC_car_number() {
        return c_car_number;
    }
    public int getC_id() {
        return c_id;
    }
    public String getC_name() {
        return c_name;
    }
    public String getC_note() {
        return c_note;
    }
    public LocalDate getC_passport_date() {
        return c_passport_date;
    }
    public String getC_passport_number() {
        return c_passport_number;
    }
    public LocalDateTime getCheck_in() {
        return check_in;
    }
    public LocalDateTime getCheck_out() {
        return check_out;
    }
    public Country getCountry() {
        return country;
    }
    public List<ClientUsedServices> getCuds() {
        return cuds;
    }
    public List<Raiting> getRait() {
        return rait;
    }
    public double getTotal() {
        return total;
    }
    public String getVaucher() {
        return vaucher;
    }
    public Hotel getHotel() {
        return hotel;
    }
    public String getC_sex() {
        return this.c_sex == true? "M":"F";
    }
    


    public void setC_bd(LocalDate c_bd) {
        this.c_bd = c_bd;
    }
    public void setC_car_number(String c_car_number) {
        this.c_car_number = c_car_number;
    }
    public void setC_id(int c_id) {
        this.c_id = c_id;
    }
    public void setC_name(String c_name) {
        this.c_name = c_name;
    }
    public void setC_note(String c_note) {
        this.c_note = c_note;
    }
    public void setC_passport_date(LocalDate c_passport_date) {
        this.c_passport_date = c_passport_date;
    }
    public void setC_passport_number(String c_passport_number) {
        this.c_passport_number = c_passport_number;
    }
    public void setC_sex(boolean c_sex) {
        this.c_sex = c_sex;
    }
    public void setCheck_in(LocalDateTime check_in) {
        this.check_in = check_in;
    }
    public void setCheck_out(LocalDateTime check_out) {
        this.check_out = check_out;
    }
    public void setCountry(Country country) {
        this.country = country;
    }
    public void setCuds(List<ClientUsedServices> cuds) {
        this.cuds = cuds;
    }
    public void setRait(List<Raiting> rait) {
        this.rait = rait;
    }
    public void setTotal(double total) {
        this.total = total;
    }
    public void setVaucher(String vaucher) {
        this.vaucher = vaucher;
    }
    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public void checkOut() {
        this.check_out = LocalDateTime.now();
    }


    public void addToUsedServices(ClientUsedServices cus) {
        this.cuds.add(cus);
        calcTotal();
    }

    private void calcTotal() {
        double sum = 0;
        Raiting last = this.rait.get(this.rait.size() - 1);
        for (ClientUsedServices clientUsedServices : cuds) {
            if (clientUsedServices.getPaid() == false) {
                sum = sum + 
                    (clientUsedServices.getQuantity() * clientUsedServices.getAddit_service().getPrice());
                if(clientUsedServices.getAddit_service().getCategory().getType() == ServiceType.PROSITIVE){
                    
                    if (last.getRait_value() < 10) {
                        this.rait.add(new Raiting(last.getRait_value() + 1));
                    }else {
                        this.rait.add(new Raiting(10));
                    }
                }else {
                    if (last.getRait_value() > 0) {
                        this.rait.add(new Raiting(last.getRait_value() - 1));
                    } else {
                        this.rait.add(new Raiting(0));
                    }
                }
            }
        }
        this.setTotal(sum);
    }

    public static String getTableName() {
        return "clients";
    }

    public static List<String> getFields() {
        List<String> ls = new ArrayList<>();
        ls.add("c_id");
        ls.add("c_name");
        ls.add("c_bd");
        ls.add("c_sex");
        ls.add("c_passport_number");
        ls.add("c_passport_date");
        ls.add("c_car_number");
        ls.add("country");
        ls.add("check_in");
        ls.add("check_out");
        ls.add("vaucher");
        ls.add("hotel");
        ls.add("rait");
        return ls;
    }

    @Override
    public String toString() {
        return "Client [ id = " + this.c_id + " name: " + this.c_name + " country: "
                + this.country.getCountry_name() + " birth date: "
                + this.c_bd + " sex: " + this.c_sex + " passport number: "
                + this.c_passport_number + " passport end date: " + this.c_passport_date + " car number: "
                + this.c_car_number + " note: " + this.c_note 
                + " additional services: " + this.cuds + " ]";
    }
}
