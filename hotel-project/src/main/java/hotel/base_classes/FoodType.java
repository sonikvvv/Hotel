package hotel.base_classes;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FoodType {
    @Id
    private int food_type_id;
    private String food_type;

    public FoodType() {}

    public FoodType(String type) {
        this.food_type = type;
    }

    public String getFood_type() {
        return food_type;
    }
    public int getFood_type_id() {
        return food_type_id;
    }

    public void setFood_type(String food_type) {
        this.food_type = food_type;
    }

    public void setFood_type_id(int food_type_id) {
        this.food_type_id = food_type_id;
    }

    @Override
    public String toString() {
        return "Food type [ id = " + this.food_type_id + " type: " + this.food_type + " ]";
    }
}
