package base_classes.classes;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

@Entity
public class Clients {
    @Id
    private int client_id;
    private String client_name;

    @Temporal(TemporalType.DATE)
    private Date client_birth_date;

    @Type(type = "true_false")
    private boolean client_sex; // 1 -> male, 0 -> female TODO: see the mapping
    private String client_passport_number;

    @Temporal(TemporalType.DATE)
    private Date client_passport_date;
    private String client_car_number;
    private double client_rating;

    @OneToMany
    private Country client_country;
    private String client_note;

    
    private List<AdditServices> client_addit_serv; //TODO: fix it
    private Date check_in;
    private Date check_out;

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

    public List<AdditServices> getClient_addit_serv() {
        return client_addit_serv;
    }

    public Date getCheck_in() {
        return check_in;
    }

    public Date getCheck_out() {
        return check_out;
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

    public void setClient_addit_serv(List<AdditServices> client_addit_serv) {
        this.client_addit_serv = client_addit_serv;
    }

    public void setCheck_in(Date check_in) {
        this.check_in = check_in;
    }

    public void setCheck_out(Date check_out) {
        this.check_out = check_out;
    }


    public void addClient_addit_serv(AdditServices additServices) {
        this.client_addit_serv.add(additServices);
    }

    public void checkOut() {
        this.check_out = new Date();
    }


    @Override
    public String toString() {
        return "Client [ id = " + this.client_id + " name: " + this.client_name + " country: "
                + this.client_country.getCountry_name() + " birth date: "
                + this.client_birth_date + " sex: " + this.client_sex + " passport number: "
                + this.client_passport_number + " passport end date: " + this.client_passport_date + " car number: "
                + this.client_car_number + " rating: " + this.client_rating + " note: " + this.client_note 
                + " additional services: " + this.client_addit_serv + " ]";
    }
}
