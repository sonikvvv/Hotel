package hotel.base_classes;

public class UserRoles {
    private int role_id;
    private String role_title;

    public UserRoles() {}

    public UserRoles(String title){
        this.role_title = title;
    }

    public int getRole_id() {
        return role_id;
    }
    
    public String getRole_title() {
        return role_title;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public void setRole_title(String role_title) {
        this.role_title = role_title;
    }

    @Override
    public String toString() {
        return "Role [ id = " + this.role_id + " role: " + this.role_title + " ]";
    }
}
