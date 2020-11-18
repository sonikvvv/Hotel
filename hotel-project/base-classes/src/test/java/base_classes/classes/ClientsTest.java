package base_classes.classes;

import java.util.Date;

public class ClientsTest {
    private int c_id;
    private String c_name;
    private Date c_bd;
    private boolean c_sex;
    private String c_passport_number;
    private Date c_passport_date;
    private String c_car_number;
    private String c_note;
    private Date check_in;
    private Date check_out;
    private double total;
    private String vaucher;
    private Country country1 = null;

    public ClientsTest() {
        country1 = new Country("Testivile");
        c_id = 1;
        c_name = "Laney Dickens";
        c_bd = new Date();
        c_sex = false;
        c_passport_number = "50127";
        c_passport_date = new Date();
        c_car_number = "";
        c_note = "";
        check_in = new Date();
        check_out = new Date();
        total = 1000.5;
        vaucher = "ivory";

    }

    
}
