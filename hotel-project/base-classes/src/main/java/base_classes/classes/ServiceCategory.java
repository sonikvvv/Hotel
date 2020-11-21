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

import base_classes.classes.emuns.SCE;
import base_classes.classes.emuns.ServiceType;

@Entity(name = "service_category")
public class ServiceCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_cat_generator")
    @SequenceGenerator(name = "s_cat_generator", sequenceName = "s_cat_seq", allocationSize = 50)
    private int category_id;
    private String category_title;

    @Enumerated(EnumType.STRING)
    private ServiceType type;

    public ServiceCategory() {}

    public ServiceCategory(String category_title, ServiceType type) {
        this.category_title = category_title;
        this.type = type;
    }

    public int getCategory_id() {
        return category_id;
    }
    public String getCategory_title() {
        return category_title;
    }
    public ServiceType getType() {
        return type;
    }


    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }
    public void setCategory_title(String category_title) {
        this.category_title = category_title;
    }
    public void setType(ServiceType type) {
        this.type = type;
    }

    public static String getTableName() {
        return "service_category";
    }

    public static List<String> getFields() {
        List<String> ls = new ArrayList<>();
        ls.add("category_id");
        ls.add("category_title");
        ls.add("type");
        return ls;
    }

    public static String search(SCE type) {
        String sqlString = "from " + getTableName() + " t where t.";
        switch (type) {
            case ID:
                sqlString = sqlString + getFields().get(0) + " = ";
                break;
            case NAME:
                sqlString = sqlString + getFields().get(1) + " = '";
                break;
            default:
                break;
        }
        return sqlString;
    }

    @Override
    public String toString() {
        return "Service category [ id = " + this.category_id + " title: " + this.category_title + " ]";
    }
}
