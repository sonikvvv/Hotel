package base_classes.classes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity(name = "raiting")
public class Raiting {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rait_generator")
    @SequenceGenerator(name = "rait_generator", sequenceName = "rait_seq", allocationSize = 1)
    private int rait_id;
    private int rait_value;
    private LocalDateTime date_made;

    public Raiting() {
        this.rait_value = 5;
        date_made = LocalDateTime.now();
    }

    public Raiting(int rait_value) {
        this.rait_value = rait_value;
        this.date_made = LocalDateTime.now();
    }

    public LocalDateTime getDate_made() {
        return date_made;
    }

    public int getRait_id() {
        return rait_id;
    }
    public int getRait_value() {
        return rait_value;
    }

    public void setDate_made(LocalDateTime date_made) {
        this.date_made = date_made;
    }
    public void setRait_id(int rait_id) {
        this.rait_id = rait_id;
    }
    public void setRait_value(int rait_value) {
        this.rait_value = rait_value;
    }

    public static List<String> getFields() {
        List<String> ls = new ArrayList<>();
        ls.add("rait_id");
        ls.add("rait_value");
        ls.add("date_made");
        return ls;
    }

    public static String getTableName() {
        return "raiting";
    }

    @Override
    public String toString() {
        return " Raiting [ id = " + this.rait_id + " value: " + this.rait_value + " date: " + this.date_made + " ]";
    }

    
    
}
