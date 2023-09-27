package bussinesslayer.entity.user;

public class Manager extends User{
    private String gender;

    public Manager(int id, String name, int age, String email, String password, String phone_number, String address, String gender) {
        super(id, name, age, email, password, phone_number, address);
        this.gender = gender;
    }

    public Manager(String name, int age, String email, String password, String phoneNumber, String address, String gender) {
        super(name, age, email, password, phoneNumber, address);
        this.gender = gender;
    }

    public Manager() {

    }

    public String isGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
