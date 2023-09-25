package bussinesslayer.entity.user;

public class Manager extends User{
    private String role;
    private String gender;

    public Manager(int id, String name, int age, String email, String password, String phone_number, String address, String role, String gender) {
        super(id, name, age, email, password, phone_number, address);
        this.role = role;
        this.gender = gender;
    }

    public Manager() {

    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String isGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
