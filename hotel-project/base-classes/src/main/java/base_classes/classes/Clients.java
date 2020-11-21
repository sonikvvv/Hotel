package base_classes.classes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.Type;

import base_classes.classes.emuns.ClientsE;

@Entity(name = "clients")
public class Clients {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_generator")
    @SequenceGenerator(name = "client_generator", sequenceName = "client_seq", allocationSize = 50)
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
    private double total = 0;
    private String vaucher;
    private int hotel_id;

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
    }

    public Clients(String name, LocalDate birth_date, boolean sex, String passport_number, LocalDate passport_date,
            String car_number, Country country, String client_note, String vaucher, int hotel_id) {

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
        this.hotel_id = hotel_id;
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
    public int getHotel_id() {
        return hotel_id;
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
    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
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
        for (ClientUsedServices clientUsedServices : cuds) {
            if (clientUsedServices.getPaid() == false) {
                sum = sum + 
                    (clientUsedServices.getQuantity() * clientUsedServices.getAddit_service().getPrice());
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
        ls.add("hotel_id");
        ls.add("rait");
        return ls;
    }

    public static String search(ClientsE type) {
        String sqlString = "from " + getTableName() + " t where t.";
        List<String> fields = getFields();

        switch (type) {
            case ID:
                sqlString = sqlString + fields.get(0) + " = ";
                break;
            case NAME:
                sqlString = sqlString + "lower(" + fields.get(1) + ")" + " = ";
                break;
            case BIRTH_DATE:
                sqlString = sqlString + fields.get(2) + " like to_date('";
                break;
            case SEX:
                sqlString = sqlString + fields.get(3) + " = ";
                break;
            case PASSPORT_NUMBER:
                sqlString = sqlString + fields.get(4) + " = ";
                break;
            case PASSPORT_DATE:
                sqlString = sqlString + fields.get(5) + " like to_date('";
                break;
            case CAR_NUMBER:
                sqlString = sqlString + fields.get(6) + " = ";
                break;
            case COUNTRY_ID:
                sqlString = sqlString + fields.get(8) + " = ";
                break;
            case COUNTRY_NAME:
                sqlString =  "from " + getTableName() + " t where lower(t." 
                    + fields.get(8) + "." + Country.getFields().get(1) + ") = '";
                break;
            case CHECK_IN:
                sqlString = sqlString + fields.get(9) + " like to_date('";
                break;
            case CHECK_OUT:
                sqlString = sqlString + fields.get(10) + " like to_date('";
                break;
            case VAUCHER:
                sqlString = sqlString + fields.get(11) + " = '";
                break;
            case HOTEL_ID:
                sqlString = sqlString + fields.get(12) + " = ";
                break;
            default:
                break;
        }

        return sqlString;
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
