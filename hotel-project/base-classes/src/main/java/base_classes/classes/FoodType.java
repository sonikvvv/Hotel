package base_classes.classes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import base_classes.classes.emuns.FTE;

@Entity(name = "food_type")
public class FoodType {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ft_generator")
    @SequenceGenerator(name = "ft_generator", sequenceName = "ft_seq", allocationSize = 50)
    private int ft_id;
    private String food_type;

    public FoodType() {}

    public FoodType(String type) {
        this.food_type = type;
    }

    public String getFood_type() {
        return food_type;
    }
    public int getFood_type_id() {
        return ft_id;
    }

    public void setFood_type(String food_type) {
        this.food_type = food_type;
    }

    public void setFood_type_id(int food_type_id) {
        this.ft_id = food_type_id;
    }
    
    public static List<String> getFields() {
        List<String> ls = new ArrayList<>();
        ls.add("ft_id");
        ls.add("food_type");
        return ls;
    }

    public static String getTableName() {
        return "food_type";
    }

    public String search(FTE type) {
        String sqlString = "from " + getTableName() + " where ";
        List<String> fields = getFields();

        switch (type) {
            case ID:
                sqlString = sqlString + fields.get(0) + " = ";
                break;
            case TYPE:
                sqlString = sqlString + fields.get(1) + " = ";
                break;
            default:
                break;
        }

        return sqlString;
    }

    @Override
    public String toString() {
        return "Food type [ id = " + this.ft_id + " type: " + this.food_type + " ]";
    }
}
