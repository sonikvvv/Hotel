package base_classes.classes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

import base_classes.classes.emuns.ClientsE;
import base_classes.classes.emuns.CountryE;

@Entity
public class Clients {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_generator")
    @SequenceGenerator(name = "client_generator", sequenceName = "client_seq", allocationSize = 50)
    private int client_id;
    private String client_name;

    @Temporal(TemporalType.DATE)
    private Date client_birth_date;

    @Type(type = "true_false")
    private boolean client_sex; // true -> male, false -> female
    private String client_passport_number;

    @Temporal(TemporalType.DATE)
    private Date client_passport_date;
    private String client_car_number;
    private double client_rating;

    @OneToOne(cascade = CascadeType.ALL)
    private Country client_country;
    private String client_note;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ClientUsedServices> cuds;
    private Date check_in;
    private Date check_out;
    private double total = 0;

    public Clients() {}

    public Clients(String name, Date birth_date, boolean sex, String passport_number, Date passport_date,
            String car_number, double rating, Country country, String client_note) {

        this.client_name = name;
        this.client_birth_date = birth_date;
        this.client_sex = sex;
        this.client_passport_number = passport_number;
        this.client_passport_date = passport_date;
        this.client_car_number = car_number;
        this.client_rating = rating;
        this.client_country = country;
        this.client_note = client_note;
        this.check_in = new Date();
    }

    public Date getClient_birth_date() {
        return client_birth_date;
    }

    public String getClient_car_number() {
        return client_car_number;
    }

    public Country getClient_country() {
        return client_country;
    }

    public int getClient_id() {
        return client_id;
    }

    public String getClient_name() {
        return client_name;
    }

    public Date getClient_passport_date() {
        return client_passport_date;
    }

    public String getClient_passport_number() {
        return client_passport_number;
    }

    public double getClient_rating() {
        return client_rating;
    }

    public boolean getClient_sex() {
        return client_sex;
    }

    public String getClient_note() {
        return client_note;
    }

    public List<ClientUsedServices> getClient_addit_serv() {
        return cuds;
    }

    public Date getCheck_in() {
        return check_in;
    }

    public Date getCheck_out() {
        return check_out;
    }

    public double getTotal() {
        return total;
    }



    public void setClient_birth_date(Date client_birth_date) {
        this.client_birth_date = client_birth_date;
    }

    public void setClient_car_number(String client_car_number) {
        this.client_car_number = client_car_number;
    }

    public void setClient_country(Country client_country) {
        this.client_country = client_country;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public void setClient_passport_date(Date client_passport_date) {
        this.client_passport_date = client_passport_date;
    }

    public void setClient_passport_number(String client_passport_number) {
        this.client_passport_number = client_passport_number;
    }

    public void setClient_rating(double client_rating) {
        this.client_rating = client_rating;
    }

    public void setClient_sex(boolean client_sex) {
        this.client_sex = client_sex;
    }

    public void setClient_note(String client_note) {
        this.client_note = client_note;
    }

    public void setClient_addit_serv(List<ClientUsedServices> clientUsedServices) {
        this.cuds = clientUsedServices;
    }

    public void setCheck_in(Date check_in) {
        this.check_in = check_in;
    }

    public void setCheck_out(Date check_out) {
        this.check_out = check_out;
    }

    public void addClient_addit_serv(ClientUsedServices clientUsedServices) {
        this.cuds.add(clientUsedServices);
        calcTotal();
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void checkOut() {
        this.check_out = new Date();
    }

    private void calcTotal() {
        double sum = 0;
        for (ClientUsedServices clientUsedServices : cuds) {
            if (clientUsedServices.getPaid() == false) {
                sum = sum + 
                    (clientUsedServices.getQuantity() + clientUsedServices.getAddit_service().getAddit_services_price());
            }
        }

        this.setTotal(sum);
    }

    public static String getTableName() {
        return "clients";
    }

    public static List<String> getFields() {
        List<String> ls = new ArrayList<>();
        ls.add("client_id");
        ls.add("client_name");
        ls.add("client_birth_date");
        ls.add("client_sex");
        ls.add("client_passport_number");
        ls.add("client_passport_date");
        ls.add("client_car_number");
        ls.add("client_rating");
        ls.add("client_country");
        ls.add("check_in");
        ls.add("check_out");
        return ls;
    }

    public static String search(ClientsE type) {
        String sqlString = "from " + getTableName() + " where ";
        List<String> fields = getFields();

        switch (type) {
            case ID:
                sqlString = sqlString + fields.get(0) + " = ";
                break;
            case NAME:
                sqlString = sqlString + fields.get(1) + " = ";
                break;
            case BIRTH_DATE:
                sqlString = sqlString + fields.get(2) + " = ";
                break;
            case SEX:
                sqlString = sqlString + fields.get(3) + " = ";
                break;
            case PASSPORT_NUMBER:
                sqlString = sqlString + fields.get(4) + " = ";
                break;
            case PASSPORT_DATE:
                sqlString = sqlString + fields.get(5) + " = ";
                break;
            case CAR_NUMBER:
                sqlString = sqlString + fields.get(6) + " = ";
                break;
            case RATING:
                sqlString = sqlString + fields.get(7) + " = ";
                break;
            case COUNTRY_ID:
                sqlString = sqlString + fields.get(8) + " = ";
                break;
            case COUNTRY_NAME:
                sqlString = sqlString + fields.get(8) + " = " + Country.search(CountryE.COUNTRY_NAME);
                break;
            case CHECK_IN:
                sqlString = sqlString + fields.get(9) + " = ";
                break;
            case CHECK_OUT:
                sqlString = sqlString + fields.get(10) + " = ";
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
        return "Client [ id = " + this.client_id + " name: " + this.client_name + " country: "
                + this.client_country.getCountry_name() + " birth date: "
                + this.client_birth_date + " sex: " + this.client_sex + " passport number: "
                + this.client_passport_number + " passport end date: " + this.client_passport_date + " car number: "
                + this.client_car_number + " rating: " + this.client_rating + " note: " + this.client_note 
                + " additional services: " + this.cuds + " ]";
    }
}
